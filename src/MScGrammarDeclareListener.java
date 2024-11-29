import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MScGrammarDeclareListener implements SentenceParser {
    private final Set<DeclareConstraint> constraints = new HashSet<>();

    private String inputFileName;
    private StatementMetadata currentStatement;
    private final SharedModelStorage modelStorage;

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
    public void handleClosingStatement(List<TerminalNode> closingTransition) {

    }

    @Override
    public void handleActivity(List<TerminalNode> activityText) {
        modelStorage.addTransition(HelperFunctions.getActivityText(activityText));
    }

    @Override
    public void handleAspDeclaration(String aspId) {
        modelStorage.addTransition(aspId);
    }

    @Override
    public void handleOspDeclaration(String ospId) {
        modelStorage.addTransition(ospId);
    }

    @Override
    public void handlePreSequencePostSequence() {
        String fromActivity = currentStatement.getPostActivities().get(0).getName();
        String toActivity = currentStatement.getPreActivities().get(0).getName();

        constraints.add(new DeclareConstraint(DeclareConstraintType.CHAIN_SUCCESSION, fromActivity, toActivity));
    }

    @Override
    public void handlePreAndPostSequence() {
        String fromActivity = currentStatement.getPostActivities().get(0).getName();
        constraints.add(new DeclareConstraint(DeclareConstraintType.EXACTLY_ONE, fromActivity, null));

        handlePreAnd(fromActivity, false);
    }

    @Override
    public void handlePreRepeatSincePostSequence() {
        handlePreSequencePostSequence();
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
        handlePostAndExactlyOne();

        for (Activity preActivity: currentStatement.getPreActivities()) {
            String toActivity = preActivity.getName();
            for (Activity postActivity : currentStatement.getPostActivities()) {
                String fromActivity = postActivity.getName();
                constraints.add(new DeclareConstraint(DeclareConstraintType.ALTERNATE_SUCCESSION, fromActivity, toActivity));
            }
        }
    }

    @Override
    public void handlePreAndPostAnd() {
        handlePostAndExactlyOne();

        for (Activity preActivity: currentStatement.getPreActivities()) {
            String toActivity = preActivity.getName();
            if (preActivity.getType() == ActivityType.OR_SUBPROCESS) {
                constraints.add(new DeclareConstraint(DeclareConstraintType.EXACTLY_ONE, toActivity, null));
                constraints.addAll(HelperFunctions.getConstraintsForOspPreActivity(toActivity, modelStorage.getOrSubProcessNames(toActivity)));
            }
            for (Activity postActivity : currentStatement.getPostActivities()) {
                String fromActivity = postActivity.getName();
                constraints.add(new DeclareConstraint(DeclareConstraintType.ALTERNATE_SUCCESSION, fromActivity, toActivity));
            }
        }
    }

    @Override
    public void handlePreRepeatSincePostAnd() {
        handlePreSequencePostAnd();
    }

    @Override
    public void handlePreOrPostAnd() {
        handlePostAndExactlyOne();

        String silentActivity = getSilentTransition("and_to_or");
        currentStatement.getPostActivities().forEach(a -> constraints.add(new DeclareConstraint(DeclareConstraintType.ALTERNATE_SUCCESSION, a.getName(), silentActivity)));

        handlePreOr(silentActivity);
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
        String silentActivity = getSilentTransition("or_to_and");

        handlePostOr(silentActivity);
        handlePreAnd(silentActivity, true);
    }

    @Override
    public void handlePreRepeatSincePostOr() {
        handlePreSequencePostOr();
    }

    @Override
    public void handlePreOrPostOr() {
        String silentActivity = getSilentTransition("or_to_or");

        handlePostOr(silentActivity);
        handlePreOr(silentActivity);
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

        for (String activity: modelStorage.getTransitions()) {
            printWriter.printf("activity %s\n", activity);
        }

        for (DeclareConstraint constraint: constraints) {
            printWriter.printf("%s\n", constraint.getRumString());
        }

        printWriter.close();
    }

    private String getSilentTransition(String name) {
        String silentActivity = HelperFunctions.getSilentTransitionName(name, currentStatement.getStatementNumber());
        modelStorage.addTransition(silentActivity);
        return silentActivity;
    }

    private void handlePreAnd(String fromActivity, boolean addExactlyOneForOr) {
        for (Activity preActivity: currentStatement.getPreActivities()) {
            String toActivity = preActivity.getName();
            constraints.add(new DeclareConstraint(DeclareConstraintType.ALTERNATE_SUCCESSION, fromActivity, toActivity));
            if (preActivity.getType() == ActivityType.OR_SUBPROCESS) {
                if (addExactlyOneForOr) {
                    constraints.add(new DeclareConstraint(DeclareConstraintType.EXACTLY_ONE, toActivity, null));
                }
                constraints.addAll(HelperFunctions.getConstraintsForOspPreActivity(toActivity, modelStorage.getOrSubProcessNames(toActivity)));
            }
        }
    }

    private void handlePreOr(String previousActivity) {
        constraints.addAll(HelperFunctions.getNotCoExistenceConstraintsForOr(currentStatement.getPreActivities().stream().map(Activity::getName).toList()));
        for (Activity preActivity: currentStatement.getPreActivities()) {
            String toActivity = preActivity.getName();
            constraints.add(new DeclareConstraint(DeclareConstraintType.ALTERNATE_PRECEDENCE, previousActivity, toActivity));
            if (preActivity.getType() == ActivityType.AND_SUBPROCESS) {
                constraints.addAll(HelperFunctions.getConstraintsForAspPreActivity(toActivity, modelStorage.getAndSubProcessNames(toActivity)));
            }
        }
    }

    private void handlePostOr(String nextActivity) {
        constraints.add(new DeclareConstraint(DeclareConstraintType.EXACTLY_ONE, nextActivity, null));

        constraints.addAll(HelperFunctions.getNotCoExistenceConstraintsForOr(currentStatement.getPostActivities().stream().map(Activity::getName).toList()));
        for (Activity postActivity : currentStatement.getPostActivities()) {
            String fromActivity = postActivity.getName();
            constraints.add(new DeclareConstraint(DeclareConstraintType.ALTERNATE_RESPONSE, fromActivity, nextActivity));
            if (postActivity.getType() == ActivityType.AND_SUBPROCESS) {
                constraints.addAll(HelperFunctions.getConstraintsForAspPostActivity(fromActivity, modelStorage.getAndSubProcessNames(fromActivity)));
            }
        }
    }

    private void handlePostAndExactlyOne() {
        currentStatement.getPostActivities().forEach(a -> {
            constraints.add(new DeclareConstraint(DeclareConstraintType.EXACTLY_ONE, a.getName(), null));
            if (a.getType() == ActivityType.OR_SUBPROCESS) {
                constraints.addAll(HelperFunctions.getConstraintsForOspPostActivity(a.getName(), modelStorage.getOrSubProcessNames(a.getName())));
            }
        });
    }
}