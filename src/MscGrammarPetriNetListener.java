import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class MscGrammarPetriNetListener implements SentenceParser {
    private static final String INITIAL_PLACE = "pn_initial";
    private static final String FINAL_PLACE = "pn_final";

    private final Set<String> places = new HashSet<>();
    private final Set<Flow> flows = new HashSet<>();

    private String inputFileName;
    private StatementMetadata currentStatement;
    private final SharedModelStorage modelStorage;

    public MscGrammarPetriNetListener() {
        places.add(INITIAL_PLACE);
        places.add(FINAL_PLACE);
        modelStorage = SharedModelStorage.getInstance();
    }

    @Override
    public void setInputFileName(String inputFileName) {
        this.inputFileName = inputFileName;
    }

    @Override
    public void setStatementMetadata(StatementMetadata statementMetadata) {
        currentStatement = statementMetadata;
    }

    @Override
    public void handleInitialStatement(List<TerminalNode> initialTransition) {
        String activityIntermediatePlaceText = HelperFunctions.getActivityIntermediatePlaceText(initialTransition);

        flows.add(new Flow(INITIAL_PLACE, HelperFunctions.getActivityTextStart(initialTransition)));
        flows.add(new Flow(HelperFunctions.getActivityTextStart(initialTransition), activityIntermediatePlaceText));
        flows.add(new Flow(activityIntermediatePlaceText, HelperFunctions.getActivityTextEnd(initialTransition)));
    }

    @Override
    public void handleClosingStatement(List<TerminalNode> closingTransition) {
        flows.add(new Flow(HelperFunctions.getActivityTextEnd(closingTransition), FINAL_PLACE));
    }

    @Override
    public void handleActivity(List<TerminalNode> activityText) {
        modelStorage.addTransition(HelperFunctions.getActivityTextStart(activityText));
        modelStorage.addTransition(HelperFunctions.getActivityTextEnd(activityText));
        places.add(HelperFunctions.getActivityIntermediatePlaceText(activityText));
    }

    @Override
    public void handleAspDeclaration(String aspId) { }

    @Override
    public void handleOspDeclaration(String ospId) { }

    @Override
    public void handlePreSequencePostSequence() {
        for (Activity postActivity : currentStatement.getPostActivities()) {
            String fromActivity = postActivity.getName();
            for (Activity preActivity : currentStatement.getPreActivities()) {
                // the post and pre activities here are always of the type activity and never asp/osp
                String toActivity = preActivity.getName();
                String intermediatePlace = HelperFunctions.getPlaceBetweenActivities(fromActivity, toActivity);

                places.add(intermediatePlace);
                flows.addAll(HelperFunctions.getFlowsBetweenActivities(fromActivity, toActivity, intermediatePlace));
            }
        }
    }

    @Override
    public void handlePreAndPostSequence() {
        for (Activity postActivity : currentStatement.getPostActivities()) {
            String fromActivity = postActivity.getName();
            for (Activity preActivity : currentStatement.getPreActivities()) {
                // the post activity here is always of the type activity and never asp/osp
                String toActivity = preActivity.getName();
                String intermediatePlace = HelperFunctions.getPlaceBetweenActivities(fromActivity, toActivity);
                places.add(intermediatePlace);

                if (preActivity.getType() == ActivityType.ACTIVITY) {
                    flows.addAll(HelperFunctions.getFlowsBetweenActivities(fromActivity, toActivity, intermediatePlace));
                } else if (preActivity.getType() == ActivityType.OR_SUBPROCESS) {
                    flows.addAll(HelperFunctions.getFlowsBetweenActivityAndOsp(fromActivity, modelStorage.getOrSubProcess(toActivity), intermediatePlace));
                }
            }
        }
    }

    @Override
    public void handlePreOrPostSequence() {
        String xorPlace = HelperFunctions.getXorPlace(currentStatement.getPostActivities(), currentStatement.getPreActivities());
        places.add(xorPlace);

        for (Activity postActivity : currentStatement.getPostActivities()) {
            String fromActivity = postActivity.getName();
            flows.add(new Flow(fromActivity + HelperFunctions.END_SUFFIX, xorPlace));

            handlePreOr(xorPlace);
        }
    }

    @Override
    public void handePreEventuallyPostSequence() {
        handlePreSequencePostSequence();
    }

    @Override
    public void handlePreSequencePostAnd() {
        for (Activity postActivity : currentStatement.getPostActivities()) {
            String fromActivity = postActivity.getName();
            for (Activity preActivity : currentStatement.getPreActivities()) {
                // the pre activity here is always of the type activity and never asp/osp
                String toActivity = preActivity.getName();
                String intermediatePlace = HelperFunctions.getPlaceBetweenActivities(fromActivity, toActivity);
                places.add(intermediatePlace);

                if (postActivity.getType() == ActivityType.ACTIVITY) {
                    flows.add(new Flow(fromActivity + HelperFunctions.END_SUFFIX, intermediatePlace));
                } else if (postActivity.getType() == ActivityType.OR_SUBPROCESS) {
                    flows.addAll(HelperFunctions.getFlowsFromOsp(modelStorage.getOrSubProcess(fromActivity), intermediatePlace));
                }
                flows.addAll(HelperFunctions.getFlowsToActivity(toActivity, intermediatePlace));
            }
        }
    }

    @Override
    public void handlePreAndPostAnd() {
        for (Activity postActivity : currentStatement.getPostActivities()) {
            String fromActivity = postActivity.getName();
            for (Activity preActivity : currentStatement.getPreActivities()) {
                String toActivity = preActivity.getName();
                String intermediatePlace = HelperFunctions.getPlaceBetweenActivities(fromActivity, toActivity);
                places.add(intermediatePlace);

                if (postActivity.getType() == ActivityType.ACTIVITY) {
                    flows.add(new Flow(fromActivity + HelperFunctions.END_SUFFIX, intermediatePlace));
                } else if (postActivity.getType() == ActivityType.OR_SUBPROCESS) {
                    flows.addAll(HelperFunctions.getFlowsFromOsp(modelStorage.getOrSubProcess(fromActivity), intermediatePlace));
                }

                if (preActivity.getType() == ActivityType.ACTIVITY) {
                    flows.addAll(HelperFunctions.getFlowsToActivity(toActivity, intermediatePlace));
                } else if (preActivity.getType() == ActivityType.OR_SUBPROCESS) {
                    List<Activity> toOsp = modelStorage.getOrSubProcess(toActivity);
                    for (Activity activity : toOsp) {
                        flows.addAll(HelperFunctions.getFlowsToActivity(activity.getName(), intermediatePlace));
                    }
                }
            }
        }
    }

    @Override
    public void handlePreOrPostAnd() {
        for (Activity postActivity : currentStatement.getPostActivities()) {
            String fromActivity = postActivity.getName();
            String xorPlace = HelperFunctions.getXorPlace(List.of(postActivity), currentStatement.getPreActivities());
            places.add(xorPlace);

            if (postActivity.getType() == ActivityType.ACTIVITY) {
                flows.add(new Flow(fromActivity + HelperFunctions.END_SUFFIX, xorPlace));
            } else if (postActivity.getType() == ActivityType.OR_SUBPROCESS) {
                flows.addAll(HelperFunctions.getFlowsFromOsp(modelStorage.getOrSubProcess(fromActivity), xorPlace));
            }
            handlePreOr(xorPlace);
        }
    }

    @Override
    public void handePreEventuallyPostAnd() {
        handlePreSequencePostAnd();
    }

    @Override
    public void handlePreSequencePostOr() {
        for (Activity preActivity : currentStatement.getPreActivities()) {
            String toActivity = preActivity.getName();
            String xorPlace = HelperFunctions.getXorPlace(currentStatement.getPostActivities(), List.of(preActivity));
            places.add(xorPlace);
            flows.addAll(HelperFunctions.getFlowsToActivity(toActivity, xorPlace));

            handlePostOr(xorPlace);
        }
    }

    @Override
    public void handlePreAndPostOr() {
        for (Activity preActivity : currentStatement.getPreActivities()) {
            String toActivity = preActivity.getName();
            String xorPlace = HelperFunctions.getXorPlace(currentStatement.getPostActivities(), List.of(preActivity));
            places.add(xorPlace);
            if (preActivity.getType() == ActivityType.ACTIVITY) {
                flows.addAll(HelperFunctions.getFlowsToActivity(toActivity, xorPlace));
            } else if (preActivity.getType() == ActivityType.OR_SUBPROCESS) {
                List<Activity> toOsp = modelStorage.getOrSubProcess(toActivity);
                for (Activity activity : toOsp) {
                    flows.addAll(HelperFunctions.getFlowsToActivity(activity.getName(), xorPlace));
                }
            }

            handlePostOr(xorPlace);
        }
    }

    @Override
    public void handlePreOrPostOr() {
        String xorPlace = HelperFunctions.getXorPlace(currentStatement.getPostActivities(), currentStatement.getPreActivities());
        places.add(xorPlace);

        handlePostOr(xorPlace);
        handlePreOr(xorPlace);
    }

    @Override
    public void handePreEventuallyPostOr() {
        handlePreSequencePostOr();
    }

    @Override
    public void printAndSaveModel() {
        System.out.println("Places: " + places);
        System.out.println("Transitions: " + modelStorage.getTransitions());
        System.out.println("Flows: " + flows);

        PrintWriter printWriter;
        try {
            printWriter = new PrintWriter(new FileWriter(Main.FOLDER_PATH + "output\\petri_net_" + inputFileName + ".tpn"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (String place : places) {
            printWriter.printf("place %s init %d;\n", place, place.equals(INITIAL_PLACE) ? 1 : 0);
        }
        printWriter.println();

        for (String transition : modelStorage.getTransitions()) {
            List<String> inPlaces = new ArrayList<>();
            List<String> outPlaces = new ArrayList<>();

            for (Flow flow : flows) {
                if (flow.getFrom().equals(transition)) {
                    outPlaces.add(flow.getTo());
                } else if (flow.getTo().equals(transition)) {
                    inPlaces.add(flow.getFrom());
                }
            }

            printWriter.printf("trans %s in %s out %s;\n", transition, getPlacesString(inPlaces), getPlacesString(outPlaces));
        }

        printWriter.close();
    }


    private String getPlacesString(List<String> places) {
        StringBuilder builder = new StringBuilder();
        for (String place : places) {
            builder.append(place);
            builder.append(" ");
        }
        return builder.toString().trim();
    }

    private void handlePostOr(String xorPlace) {
        for (Activity postActivity : currentStatement.getPostActivities()) {
            String fromActivity = postActivity.getName();
            if (postActivity.getType() == ActivityType.ACTIVITY) {
                flows.add(new Flow(fromActivity + HelperFunctions.END_SUFFIX, xorPlace));
            } else if (postActivity.getType() == ActivityType.AND_SUBPROCESS) {
                handleAspInPostActivity(fromActivity, xorPlace);
            }
        }
    }

    private void handlePreOr(String xorPlace) {
        for (Activity preActivity : currentStatement.getPreActivities()) {
            String toActivity = preActivity.getName();
            if (preActivity.getType() == ActivityType.ACTIVITY) {
                flows.addAll(HelperFunctions.getFlowsToActivity(toActivity, xorPlace));
            } else if (preActivity.getType() == ActivityType.AND_SUBPROCESS) {
                handleAspInPreActivity(toActivity, xorPlace);
            }
        }
    }

    private void handleAspInPreActivity(String toActivity, String xorPlace) {
        String silentTransition = getSilentTransition(toActivity);
        flows.add(new Flow(xorPlace, silentTransition));
        flows.addAll(HelperFunctions.getFlowsBetweenActivityAndAsp(silentTransition, modelStorage.getAndSubProcess(toActivity)));
    }

    private void handleAspInPostActivity(String fromActivity, String xorPlace) {
        String silentTransition = getSilentTransition(fromActivity);
        flows.add(new Flow(silentTransition, xorPlace));
        flows.addAll(HelperFunctions.getFlowsBetweenAspAndActivity(silentTransition, modelStorage.getAndSubProcess(fromActivity)));
    }

    private String getSilentTransition(String activityName) {
        String silentTransition = HelperFunctions.getSilentTransitionName(activityName, currentStatement.getStatementNumber());
        modelStorage.addTransition(silentTransition);
        return silentTransition;
    }
}
