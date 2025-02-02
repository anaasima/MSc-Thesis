import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

public class MScGrammarDeclareListener implements SentenceParser {
    private final Set<DeclareConstraint> constraints = new HashSet<>();

    private String inputFileName;
    private StatementMetadata currentStatement;
    private final SharedModelStorage modelStorage;
    private Map<String, Set<String>> activitiesToXORs = new HashMap<>();
    private Map<String, String> xorTagToActivities = new HashMap<>();

    public MScGrammarDeclareListener() {
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
        constraints.add(new DeclareConstraint(DeclareConstraintType.INIT, HelperFunctions.getActivityText(initialTransition), null));
    }

    @Override
    public void handleClosingStatementSequence() { }

    @Override
    public void handleClosingStatementAnd() { }

    @Override
    public void handleClosingStatementOr() { }

    @Override
    public void handleActivity(List<TerminalNode> activityText) {
        modelStorage.addTransition(HelperFunctions.getActivityText(activityText));
    }

    @Override
    public void handleAspDeclaration(String aspId) {
        // we're not using silent transitions in declare
    }

    @Override
    public void handleOspDeclaration(String ospId) {
        // we're not using silent transitions in declare
    }

    @Override
    public void handlePreSequencePostSequence() {
        String fromActivity = currentStatement.getPostActivities().get(0).getName();
        String toActivity = currentStatement.getPreActivities().get(0).getName();

        constraints.add(new DeclareConstraint(DeclareConstraintType.CHAIN_SUCCESSION, fromActivity, toActivity));
        transferTagsToNextActivity(fromActivity, toActivity);
    }

    @Override
    public void handlePreAndPostSequence() {
        String fromActivity = currentStatement.getPostActivities().get(0).getName();
        constraints.add(new DeclareConstraint(DeclareConstraintType.EXACTLY_ONE, fromActivity, null));

        handlePreAnd(fromActivity);
    }

    @Override
    public void handlePreRepeatSincePostSequence() {
        handlePreOrPostSequence();
    }

    @Override
    public void handlePreOrPostSequence() {
        String fromActivity = currentStatement.getPostActivities().get(0).getName();
        constraints.add(new DeclareConstraint(DeclareConstraintType.EXACTLY_ONE, fromActivity, null));

        handlePreOr(fromActivity);
    }

    @Override
    public void handePreEventuallyPostSequence() {
        String fromActivity = currentStatement.getPostActivities().get(0).getName();
        String toActivity = currentStatement.getPreActivities().get(0).getName();

        constraints.add(new DeclareConstraint(DeclareConstraintType.SUCCESSION, fromActivity, toActivity));
    }

    @Override
    public void handlePreSequencePostAnd() {
        for (Activity preActivity : currentStatement.getPreActivities()) {
            String toActivity = preActivity.getName();
            constraints.add(new DeclareConstraint(DeclareConstraintType.EXACTLY_ONE, toActivity, null));
            for (Activity postActivity : currentStatement.getPostActivities()) {
                String fromActivity = postActivity.getName();
                if (postActivity.getType() == ActivityType.ACTIVITY) {
                    constraints.add(new DeclareConstraint(DeclareConstraintType.ALTERNATE_SUCCESSION, fromActivity, toActivity));
                } else if (postActivity.getType() == ActivityType.OR_SUBPROCESS) {
                    constraints.addAll(HelperFunctions.getConstraintsForOspPostActivity(toActivity, modelStorage.getOrSubProcessNames(fromActivity)));
                }
            }
        }
    }

    @Override
    public void handlePreAndPostAnd() {
        // not going to implement anymore
    }

    @Override
    public void handlePreRepeatSincePostAnd() {
        handlePreOrPostAnd();
    }

    @Override
    public void handlePreOrPostAnd() {
        // not going to implement anymore
    }

    @Override
    public void handePreEventuallyPostAnd() {
        handlePostAndExactlyOne();

        String toActivity = currentStatement.getPreActivities().get(0).getName();
        constraints.add(new DeclareConstraint(DeclareConstraintType.EXACTLY_ONE, toActivity, null));
        for (Activity postActivity : currentStatement.getPostActivities()) {
            String fromActivity = postActivity.getName();
            constraints.add(new DeclareConstraint(DeclareConstraintType.SUCCESSION, fromActivity, toActivity));
        }
    }

    @Override
    public void handlePreSequencePostOr() {
        String toActivity = currentStatement.getPreActivities().get(0).getName();
        handlePostOr(toActivity);
    }

    @Override
    public void handlePreAndPostOr() {
        // not going to implement anymore
    }

    @Override
    public void handlePreRepeatSincePostOr() {
        handlePreOrPostOr();
    }

    @Override
    public void handlePreOrPostOr() {
        // not going to implement anymore
    }

    @Override
    public void handePreEventuallyPostOr() {
        handlePreSequencePostOr();
    }

    @Override
    public void printAndSaveModel() {
        System.out.println("Activities: " + modelStorage.getTransitions());
        System.out.println("Constraints: " + constraints);

        PrintWriter printWriter;
        try {
            printWriter = new PrintWriter(new FileWriter(Main.FOLDER_PATH + "output\\declare_model_" + inputFileName + ".decl"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (String activity : modelStorage.getTransitions()) {
            printWriter.printf("activity %s\n", activity);
        }

        for (DeclareConstraint constraint : constraints) {
            printWriter.printf("%s\n", constraint.getRumString());
        }

        printWriter.close();
    }

    private void handlePreAnd(String fromActivity) {
        List<String> andActivities = new ArrayList<>(); // here we'll store all single pre activities. This means all except the OR subprocess

        for (Activity preActivity : currentStatement.getPreActivities()) {
            String toActivity = preActivity.getName();
            if (preActivity.getType() == ActivityType.ACTIVITY) {
                constraints.add(new DeclareConstraint(DeclareConstraintType.ALTERNATE_SUCCESSION, fromActivity, toActivity));
                andActivities.add(toActivity);
            } else if (preActivity.getType() == ActivityType.OR_SUBPROCESS) {
                constraints.addAll(HelperFunctions.getConstraintsForOspPreActivity(fromActivity, modelStorage.getOrSubProcessNames(toActivity)));
            }
        }

//        constraints.addAll(HelperFunctions.getCoExistenceConstraintsForAnd(andActivities));
    }

    // XOR split
    private void handlePreOr(String previousActivity) {
        String xorTag = "xor_" + currentStatement.getStatementNumber();
        xorTagToActivities.put(xorTag, previousActivity);

        List<String> orBranchesAndOneRepresentativeForEachAndBranch = currentStatement.getPreActivities()
                .stream()
                .filter(a -> a.getType() == ActivityType.ACTIVITY)
                .map(Activity::getName).collect(Collectors.toList());
        currentStatement.getPreActivities().forEach(a -> {
            if (a.getType() == ActivityType.AND_SUBPROCESS) {
                List<String> andSubBranches = new ArrayList<>(modelStorage.getAndSubProcessNames(a.getName()));
                orBranchesAndOneRepresentativeForEachAndBranch.add(andSubBranches.get(0)); // we only need one branch from all that are part of the AND group
                constraints.addAll(HelperFunctions.getCoExistenceConstraintsForAnd(andSubBranches));
            }
        });

        constraints.addAll(HelperFunctions.getNotCoExistenceConstraintsForOr(orBranchesAndOneRepresentativeForEachAndBranch));

        for (Activity preActivity : currentStatement.getPreActivities()) {
            String toActivity = preActivity.getName();

            if (activitiesToXORs.containsKey(toActivity)) {
                activitiesToXORs.get(toActivity).add(xorTag);
            } else {
                activitiesToXORs.put(toActivity, Set.of(xorTag));
            }

            if (preActivity.getType() == ActivityType.ACTIVITY) {
                constraints.add(new DeclareConstraint(DeclareConstraintType.ALTERNATE_PRECEDENCE, previousActivity, toActivity));
            } else if (preActivity.getType() == ActivityType.AND_SUBPROCESS) {
                constraints.addAll(HelperFunctions.getConstraintsForAspPreActivity(previousActivity, modelStorage.getAndSubProcessNames(toActivity)));
            }
        }
    }

    // XOR join
    private void handlePostOr(String nextActivity) {
        constraints.add(new DeclareConstraint(DeclareConstraintType.EXACTLY_ONE, nextActivity, null));

        String activityWithTag = currentStatement.getPostActivities().stream().filter(a -> activitiesToXORs.containsKey(a.getName())).findFirst().orElseThrow().getName();
        List<String> xorTagsToRemove = new ArrayList<>();
        for (String xorTag : activitiesToXORs.get(activityWithTag)) {
            boolean isTagUsedByAllPostActivities = currentStatement.getPostActivities().stream().allMatch(a ->
                    activitiesToXORs.containsKey(a.getName()) && activitiesToXORs.get(a.getName()).contains(xorTag));

            if (isTagUsedByAllPostActivities) {
                int numberOfActivitiesWithTag = (int) activitiesToXORs.entrySet().stream().filter(keyValue -> keyValue.getValue().contains(xorTag)).count();
                boolean setOfPostActivitiesIsEqualToSetOfActivitiesWithTag = numberOfActivitiesWithTag == currentStatement.getPostActivities().size();
                if (setOfPostActivitiesIsEqualToSetOfActivitiesWithTag) {
                    // if we are here, then all post activities have the current xor tag and the xor tag is only used by the post activities and no other activities
                    String tagStartActivity = xorTagToActivities.get(xorTag);
                    constraints.add(new DeclareConstraint(DeclareConstraintType.NOT_CHAIN_SUCCESSION, tagStartActivity, nextActivity));
                    constraints.add(new DeclareConstraint(DeclareConstraintType.NOT_CHAIN_SUCCESSION, nextActivity, tagStartActivity));

                    // cleanup
                    xorTagToActivities.remove(xorTag);
                    xorTagsToRemove.add(xorTag);
                }
            }
        }

        for (Map.Entry<String, Set<String>> activityToXOR : activitiesToXORs.entrySet()) {
            Set<String> newXors = activityToXOR.getValue().stream().filter(xor -> !xorTagsToRemove.contains(xor)).collect(Collectors.toSet());
            activityToXOR.setValue(newXors);
        }

        List<String> orBranchesAndRepresentativesForAndBranches = new ArrayList<>();
        orBranchesAndRepresentativesForAndBranches = currentStatement.getPostActivities()
                .stream()
                .filter(a -> a.getType() == ActivityType.ACTIVITY)
                .map(Activity::getName)
                .collect(Collectors.toList());
        for (Activity a : currentStatement.getPostActivities()) {
            if (a.getType() == ActivityType.ACTIVITY) {
                String fromActivity = a.getName();
                constraints.add(new DeclareConstraint(DeclareConstraintType.ALTERNATE_RESPONSE, fromActivity, nextActivity));
            } else if (a.getType() == ActivityType.AND_SUBPROCESS) {
                List<String> subProcessNames = modelStorage.getAndSubProcessNames(a.getName());
                subProcessNames.forEach(fromActivity -> constraints.add(new DeclareConstraint(DeclareConstraintType.ALTERNATE_RESPONSE, fromActivity, nextActivity)));
                constraints.addAll(HelperFunctions.getCoExistenceConstraintsForAnd(subProcessNames));
                orBranchesAndRepresentativesForAndBranches.add(subProcessNames.getFirst());
            }
        }
        constraints.addAll(HelperFunctions.getNotCoExistenceConstraintsForOr(orBranchesAndRepresentativesForAndBranches));
    }

    private void transferTagsToNextActivity(String fromActivity, String toActivity) {
        if (activitiesToXORs.containsKey(fromActivity)) {
            if (activitiesToXORs.containsKey(toActivity)) {
                activitiesToXORs.get(toActivity).addAll(activitiesToXORs.get(fromActivity));
            } else {
                activitiesToXORs.put(toActivity, activitiesToXORs.get(fromActivity));
            }
            activitiesToXORs.remove(fromActivity);
        }
    }

    private void transferTagsToAllActivities() {
        String fromActivity = currentStatement.getPostActivities().get(0).getName();
        for (Activity preActivity : currentStatement.getPreActivities()) {
            String toActivity = preActivity.getName();
            transferTagsToNextActivity(fromActivity, toActivity);
        }
    }

    private void handlePostAndExactlyOne() {
        for (Activity a : currentStatement.getPostActivities()) {
            if (a.getType() == ActivityType.ACTIVITY) {
                constraints.add(new DeclareConstraint(DeclareConstraintType.EXACTLY_ONE, a.getName(), null));
                return;
            }
        }
    }
}