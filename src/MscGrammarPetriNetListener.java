import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class MscGrammarPetriNetListener implements MScGrammarListener {
    private static final String INITIAL_PLACE = "pn_initial";
    private static final String FINAL_PLACE = "pn_final";

    private final Map<String, List<Activity>> andSubProcesses = new HashMap<>();
    private final Map<String, List<Activity>> orSubProcesses = new HashMap<>();
    private final Set<String> places = new HashSet<>();
    private final Set<String> transitions = new HashSet<>();
    private final Set<Flow> flows = new HashSet<>();

    private StatementMetadata currentStatement;
    private int statementNumber = 0;

    @Override
    public void enterClosingStatement(MScGrammarParser.ClosingStatementContext ctx) {
        places.add(FINAL_PLACE);
    }

    @Override
    public void exitClosingStatement(MScGrammarParser.ClosingStatementContext ctx) {
        flows.add(new Flow(HelperFunctions.getActivityTextEnd(ctx.activity().WORD()), FINAL_PLACE));
    }

    @Override
    public void enterAndPostActivityExpression(MScGrammarParser.AndPostActivityExpressionContext ctx) {
        currentStatement.setPostActivityType(PostActivityType.AND);
    }

    @Override
    public void exitAndPostActivityExpression(MScGrammarParser.AndPostActivityExpressionContext ctx) {
        handleAndExpression(ctx.activity(), ctx.ospId());
    }

    @Override
    public void enterAndPreActivityExpression(MScGrammarParser.AndPreActivityExpressionContext ctx) {
        currentStatement.setPreActivityType(PreActivityType.IMMEDIATELY_AND);
    }

    @Override
    public void exitAndPreActivityExpression(MScGrammarParser.AndPreActivityExpressionContext ctx) {
        handleAndExpression(ctx.activity(), ctx.ospId());
    }

    @Override
    public void enterOrPostActivityExpression(MScGrammarParser.OrPostActivityExpressionContext ctx) {
        currentStatement.setPostActivityType(PostActivityType.OR);
    }

    @Override
    public void exitOrPostActivityExpression(MScGrammarParser.OrPostActivityExpressionContext ctx) {
        handleOrExpression(ctx.activity(), ctx.aspId());
    }

    @Override
    public void enterOrPreActivityExpression(MScGrammarParser.OrPreActivityExpressionContext ctx) {
        currentStatement.setPreActivityType(PreActivityType.IMMEDIATELY_OR);
    }

    @Override
    public void exitOrPreActivityExpression(MScGrammarParser.OrPreActivityExpressionContext ctx) {
        handleOrExpression(ctx.activity(), ctx.aspId());
    }

    @Override
    public void enterPostActivityExpression(MScGrammarParser.PostActivityExpressionContext ctx) {
        currentStatement.setCurrentContextType(ContextType.POST_ACTIVITY);
    }

    @Override
    public void enterSequencePostActivityExpression(MScGrammarParser.SequencePostActivityExpressionContext ctx) {
        currentStatement.setPostActivityType(PostActivityType.SEQUENCE);
    }

    @Override
    public void exitSequencePostActivityExpression(MScGrammarParser.SequencePostActivityExpressionContext ctx) {
        handleSequenceExpression(ctx.activity());
    }

    @Override
    public void enterStatement(MScGrammarParser.StatementContext ctx) {
        currentStatement = new StatementMetadata();
        statementNumber += 1;
    }

    @Override
    public void enterAfterStatement(MScGrammarParser.AfterStatementContext ctx) {
        currentStatement.setStatementType(StatementType.AFTER);
    }

    @Override
    public void exitAfterStatement(MScGrammarParser.AfterStatementContext ctx) {
        if (currentStatement.getPostActivityType() == PostActivityType.SEQUENCE) {
            switch (currentStatement.getPreActivityType()) {
                case IMMEDIATELY_SEQUENCE -> handlePreSequencePostSequence();
                case IMMEDIATELY_AND -> handlePreAndPostSequence();
                case IMMEDIATELY_OR -> handlePreOrPostSequence();
                case EVENTUALLY_SEQUENCE -> handePreEventuallyPostSequence();
                default -> throw new IllegalStateException("Cannot handle " + currentStatement.getPreActivityType());
            }
        } else if (currentStatement.getPostActivityType() == PostActivityType.AND) {
            switch (currentStatement.getPreActivityType()) {
                case IMMEDIATELY_SEQUENCE -> handlePreSequencePostAnd();
                case IMMEDIATELY_AND -> handlePreAndPostAnd();
                case IMMEDIATELY_OR -> handlePreOrPostAnd();
                case EVENTUALLY_SEQUENCE -> handePreEventuallyPostAnd();
                default -> throw new IllegalStateException("Cannot handle " + currentStatement.getPreActivityType());
            }
        } else if (currentStatement.getPostActivityType() == PostActivityType.OR) {
            switch (currentStatement.getPreActivityType()) {
                case IMMEDIATELY_SEQUENCE -> handlePreSequencePostOr();
                case IMMEDIATELY_AND -> handlePreAndPostOr();
                case IMMEDIATELY_OR -> handlePreOrPostOr();
                case EVENTUALLY_SEQUENCE -> handePreEventuallyPostOr();
                default -> throw new IllegalStateException("Cannot handle " + currentStatement.getPreActivityType());
            }
        } else {
            throw new IllegalStateException("Cannot handle " + currentStatement.getPostActivityType());
        }
    }

    @Override
    public void enterAsp(MScGrammarParser.AspContext ctx) {
        currentStatement.setStatementType(StatementType.AND_SUBPROCESS);
    }

    @Override
    public void exitAsp(MScGrammarParser.AspContext ctx) {
        List<Activity> aspActivities = ctx.activity().stream().map(a -> new Activity(HelperFunctions.getActivityText(a.WORD()), ActivityType.ACTIVITY)).toList();
        andSubProcesses.put(HelperFunctions.getActivityText(ctx.aspId().WORD()), aspActivities);
    }

    @Override
    public void enterOsp(MScGrammarParser.OspContext ctx) {
        currentStatement.setStatementType(StatementType.OR_SUBPROCESS);
    }

    @Override
    public void exitOsp(MScGrammarParser.OspContext ctx) {
        List<Activity> ospActivities = ctx.activity().stream().map(a -> new Activity(HelperFunctions.getActivityText(a.WORD()), ActivityType.ACTIVITY)).toList();
        orSubProcesses.put(HelperFunctions.getActivityText(ctx.ospId().WORD()), ospActivities);
    }

    @Override
    public void enterImmediatelyExpression(MScGrammarParser.ImmediatelyExpressionContext ctx) {
        currentStatement.setCurrentContextType(ContextType.PRE_ACTIVITY_IMMEDIATELY);
    }

    @Override
    public void enterEventuallyExpression(MScGrammarParser.EventuallyExpressionContext ctx) {
        currentStatement.setCurrentContextType(ContextType.PRE_ACTIVITY_EVENTUALLY);
    }

    @Override
    public void enterSequencePreActivityExpression(MScGrammarParser.SequencePreActivityExpressionContext ctx) {
        if (currentStatement.getCurrentContextType() == ContextType.PRE_ACTIVITY_EVENTUALLY) {
            currentStatement.setPreActivityType(PreActivityType.EVENTUALLY_SEQUENCE);
        } else if (currentStatement.getCurrentContextType() == ContextType.PRE_ACTIVITY_IMMEDIATELY) {
            currentStatement.setPreActivityType(PreActivityType.IMMEDIATELY_SEQUENCE);
        }
    }

    @Override
    public void exitSequencePreActivityExpression(MScGrammarParser.SequencePreActivityExpressionContext ctx) {
        handleSequenceExpression(ctx.activity());
    }

    @Override
    public void enterInitialStatement(MScGrammarParser.InitialStatementContext ctx) {
        places.add(INITIAL_PLACE);
    }

    @Override
    public void exitInitialStatement(MScGrammarParser.InitialStatementContext ctx) {
        String activityIntermediatePlaceText = HelperFunctions.getActivityIntermediatePlaceText(ctx.activity().WORD());

        flows.add(new Flow(INITIAL_PLACE, HelperFunctions.getActivityTextStart(ctx.activity().WORD())));
        flows.add(new Flow(HelperFunctions.getActivityTextStart(ctx.activity().WORD()), activityIntermediatePlaceText));
        flows.add(new Flow(activityIntermediatePlaceText, HelperFunctions.getActivityTextEnd(ctx.activity().WORD())));
    }

    @Override
    public void exitActivity(MScGrammarParser.ActivityContext ctx) {
        transitions.add(HelperFunctions.getActivityTextStart(ctx.WORD()));
        transitions.add(HelperFunctions.getActivityTextEnd(ctx.WORD()));
        places.add(HelperFunctions.getActivityIntermediatePlaceText(ctx.WORD()));
    }

    @Override
    public void visitErrorNode(ErrorNode node) {
        System.out.println("Visit error node: " + node);
    }

    @Override
    public void exitDescription(MScGrammarParser.DescriptionContext ctx) {
        System.out.println("Places: " + places);
        System.out.println("Transitions: " + transitions);
        System.out.println("Flows: " + flows);

        PrintWriter printWriter;
        try {
            printWriter = new PrintWriter(new FileWriter("./petri_net.tpn"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (String place : places) {
            printWriter.printf("place %s init %d;\n", place, place.equals(INITIAL_PLACE) ? 1 : 0);
        }
        printWriter.println();

        for (String transition : transitions) {
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

    private void handleSequenceExpression(MScGrammarParser.ActivityContext activityContext) {
        currentStatement.addActivity(new Activity(HelperFunctions.getActivityText(activityContext.WORD()), ActivityType.ACTIVITY));
    }

    private void handleAndExpression(List<MScGrammarParser.ActivityContext> activities, List<MScGrammarParser.OspIdContext> ospIdContexts) {
        for (MScGrammarParser.ActivityContext activity : activities) {
            currentStatement.addActivity(new Activity(HelperFunctions.getActivityText(activity.WORD()), ActivityType.ACTIVITY));
        }
        for (MScGrammarParser.OspIdContext ospId : ospIdContexts) {
            currentStatement.addActivity(new Activity(HelperFunctions.getActivityText(ospId.WORD()), ActivityType.OR_SUBPROCESS));
        }
    }

    private void handleOrExpression(List<MScGrammarParser.ActivityContext> activities, List<MScGrammarParser.AspIdContext> aspIdContexts) {
        for (MScGrammarParser.ActivityContext activity : activities) {
            currentStatement.addActivity(new Activity(HelperFunctions.getActivityText(activity.WORD()), ActivityType.ACTIVITY));
        }
        for (MScGrammarParser.AspIdContext aspId : aspIdContexts) {
            currentStatement.addActivity(new Activity(HelperFunctions.getActivityText(aspId.WORD()), ActivityType.AND_SUBPROCESS));
        }
    }

    private void handlePreSequencePostSequence() {
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

    private void handlePreAndPostSequence() {
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
                    flows.addAll(HelperFunctions.getFlowsBetweenActivityAndOsp(fromActivity, orSubProcesses.get(toActivity), intermediatePlace));
                }
            }
        }
    }

    private void handlePreOrPostSequence() {
        String xorPlace = HelperFunctions.getXorPlace(currentStatement.getPostActivities(), currentStatement.getPreActivities());
        places.add(xorPlace);

        for (Activity postActivity : currentStatement.getPostActivities()) {
            String fromActivity = postActivity.getName();
            flows.add(new Flow(fromActivity + HelperFunctions.END_SUFFIX, xorPlace));

            handlePreOr(xorPlace);
        }
    }

    private void handePreEventuallyPostSequence() {
        handlePreSequencePostSequence();
    }


    private void handlePreSequencePostAnd() {
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
                    flows.addAll(HelperFunctions.getFlowsFromOsp(orSubProcesses.get(fromActivity), intermediatePlace));
                }
                flows.addAll(HelperFunctions.getFlowsToActivity(toActivity, intermediatePlace));
            }
        }
    }

    private void handlePreAndPostAnd() {
        for (Activity postActivity : currentStatement.getPostActivities()) {
            String fromActivity = postActivity.getName();
            for (Activity preActivity : currentStatement.getPreActivities()) {
                String toActivity = preActivity.getName();
                String intermediatePlace = HelperFunctions.getPlaceBetweenActivities(fromActivity, toActivity);
                places.add(intermediatePlace);

                if (postActivity.getType() == ActivityType.ACTIVITY) {
                    flows.add(new Flow(fromActivity + HelperFunctions.END_SUFFIX, intermediatePlace));
                } else if (postActivity.getType() == ActivityType.OR_SUBPROCESS) {
                    flows.addAll(HelperFunctions.getFlowsFromOsp(orSubProcesses.get(fromActivity), intermediatePlace));
                }

                if (preActivity.getType() == ActivityType.ACTIVITY) {
                    flows.addAll(HelperFunctions.getFlowsToActivity(toActivity, intermediatePlace));
                } else if (preActivity.getType() == ActivityType.OR_SUBPROCESS) {
                    List<Activity> toOsp = orSubProcesses.get(toActivity);
                    for (Activity activity : toOsp) {
                        flows.addAll(HelperFunctions.getFlowsToActivity(activity.getName(), intermediatePlace));
                    }
                }
            }
        }
    }

    private void handlePreOrPostAnd() {
        for (Activity postActivity : currentStatement.getPostActivities()) {
            String fromActivity = postActivity.getName();
            String xorPlace = HelperFunctions.getXorPlace(List.of(postActivity), currentStatement.getPreActivities());
            places.add(xorPlace);

            if (postActivity.getType() == ActivityType.ACTIVITY) {
                flows.add(new Flow(fromActivity + HelperFunctions.END_SUFFIX, xorPlace));
            } else if (postActivity.getType() == ActivityType.OR_SUBPROCESS) {
                flows.addAll(HelperFunctions.getFlowsFromOsp(orSubProcesses.get(fromActivity), xorPlace));
            }
            handlePreOr(xorPlace);
        }
    }

    private void handePreEventuallyPostAnd() {
        handlePreSequencePostAnd();
    }

    private void handlePreSequencePostOr() {
        for (Activity preActivity : currentStatement.getPreActivities()) {
            String toActivity = preActivity.getName();
            String xorPlace = HelperFunctions.getXorPlace(currentStatement.getPostActivities(), List.of(preActivity));
            places.add(xorPlace);
            flows.addAll(HelperFunctions.getFlowsToActivity(toActivity, xorPlace));

            handlePostOr(xorPlace);
        }
    }

    private void handlePreAndPostOr() {
        for (Activity preActivity : currentStatement.getPreActivities()) {
            String toActivity = preActivity.getName();
            String xorPlace = HelperFunctions.getXorPlace(currentStatement.getPostActivities(), List.of(preActivity));
            places.add(xorPlace);
            if (preActivity.getType() == ActivityType.ACTIVITY) {
                flows.addAll(HelperFunctions.getFlowsToActivity(toActivity, xorPlace));
            } else if (preActivity.getType() == ActivityType.OR_SUBPROCESS) {
                List<Activity> toOsp = orSubProcesses.get(toActivity);
                for (Activity activity : toOsp) {
                    flows.addAll(HelperFunctions.getFlowsToActivity(activity.getName(), xorPlace));
                }
            }

            handlePostOr(xorPlace);
        }
    }

    private void handlePreOrPostOr() {
        String xorPlace = HelperFunctions.getXorPlace(currentStatement.getPostActivities(), currentStatement.getPreActivities());
        places.add(xorPlace);

        handlePostOr(xorPlace);
        handlePreOr(xorPlace);
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

    private void handePreEventuallyPostOr() {
        handlePreSequencePostOr();
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

    private void handleAspInPreActivity(String toActivity, String xorPlace) {
        String silentTransition = getSilentTransition(toActivity);
        flows.add(new Flow(xorPlace, silentTransition));
        flows.addAll(HelperFunctions.getFlowsBetweenActivityAndAsp(silentTransition, andSubProcesses.get(toActivity)));
    }

    private void handleAspInPostActivity(String fromActivity, String xorPlace) {
        String silentTransition = getSilentTransition(fromActivity);
        flows.add(new Flow(silentTransition, xorPlace));
        flows.addAll(HelperFunctions.getFlowsBetweenAspAndActivity(silentTransition, andSubProcesses.get(fromActivity)));
    }

    private String getSilentTransition(String activityName) {
        String silentTransition = HelperFunctions.getSilentTransitionName(activityName, statementNumber);
        transitions.add(silentTransition);
        return silentTransition;
    }


    // -----------------------------------------------------------------------------------------------
    // Methods below this line are unused
    // -----------------------------------------------------------------------------------------------

    @Override
    public void enterDescription(MScGrammarParser.DescriptionContext ctx) {
    }

    @Override
    public void enterLeadingStatement(MScGrammarParser.LeadingStatementContext ctx) {
    }

    @Override
    public void exitLeadingStatement(MScGrammarParser.LeadingStatementContext ctx) {
    }

    @Override
    public void enterStatementList(MScGrammarParser.StatementListContext ctx) {
    }

    @Override
    public void exitStatementList(MScGrammarParser.StatementListContext ctx) {
    }

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {
    }

    @Override
    public void exitEveryRule(ParserRuleContext ctx) {
    }

    @Override
    public void enterActivity(MScGrammarParser.ActivityContext ctx) {
    }

    @Override
    public void enterAspId(MScGrammarParser.AspIdContext ctx) {
    }

    @Override
    public void exitAspId(MScGrammarParser.AspIdContext ctx) {
    }

    @Override
    public void enterOspId(MScGrammarParser.OspIdContext ctx) {
    }

    @Override
    public void exitOspId(MScGrammarParser.OspIdContext ctx) {
    }

    @Override
    public void visitTerminal(TerminalNode terminalNode) {
    }

    @Override
    public void exitPostActivityExpression(MScGrammarParser.PostActivityExpressionContext ctx) {
    }

    @Override
    public void exitEventuallyExpression(MScGrammarParser.EventuallyExpressionContext ctx) {
    }

    @Override
    public void exitImmediatelyExpression(MScGrammarParser.ImmediatelyExpressionContext ctx) {
    }

    @Override
    public void exitStatement(MScGrammarParser.StatementContext ctx) {
    }
}
