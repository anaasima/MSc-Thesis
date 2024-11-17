import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class MScGrammarDeclareListener implements MScGrammarListener {
    private final Set<DeclareConstraint> constraints = new HashSet<>();
    private final Set<String> activities = new HashSet<>();

    private Set<String> preActivityTransitions;
    private Set<String> postActivityTransitions;

    @Override
    public void enterClosingStatement(MScGrammarParser.ClosingStatementContext ctx) {
    }

    @Override
    public void exitClosingStatement(MScGrammarParser.ClosingStatementContext ctx) {
        // constraints.add(new DeclareConstraint(DeclareConstraintType.END, HelperFunctions.getActivityText(ctx.postActivityExpression().activity(0).WORD()), null));
    }

    @Override
    public void enterAndPostActivityExpression(MScGrammarParser.AndPostActivityExpressionContext ctx) {

    }

    @Override
    public void exitAndPostActivityExpression(MScGrammarParser.AndPostActivityExpressionContext ctx) {
        for (MScGrammarParser.ActivityContext activity : ctx.activity()) {
            postActivityTransitions.add(HelperFunctions.getActivityText(activity.WORD()));
        }
    }

    @Override
    public void enterAndPreActivityExpression(MScGrammarParser.AndPreActivityExpressionContext ctx) {

    }

    @Override
    public void exitAndPreActivityExpression(MScGrammarParser.AndPreActivityExpressionContext ctx) {
        for (MScGrammarParser.ActivityContext activity : ctx.activity()) {
            preActivityTransitions.add(HelperFunctions.getActivityText(activity.WORD()));
        }
    }

    @Override
    public void enterOrPostActivityExpression(MScGrammarParser.OrPostActivityExpressionContext ctx) {

    }

    @Override
    public void exitOrPostActivityExpression(MScGrammarParser.OrPostActivityExpressionContext ctx) {

    }

    @Override
    public void enterOrPreActivityExpression(MScGrammarParser.OrPreActivityExpressionContext ctx) {

    }

    @Override
    public void exitOrPreActivityExpression(MScGrammarParser.OrPreActivityExpressionContext ctx) {

    }

    @Override
    public void enterPostActivityExpression(MScGrammarParser.PostActivityExpressionContext ctx) {
        postActivityTransitions = new HashSet<>();
    }

    @Override
    public void exitPostActivityExpression(MScGrammarParser.PostActivityExpressionContext ctx) {
    }

    @Override
    public void enterSequencePostActivityExpression(MScGrammarParser.SequencePostActivityExpressionContext ctx) {

    }

    @Override
    public void exitSequencePostActivityExpression(MScGrammarParser.SequencePostActivityExpressionContext ctx) {

    }

    @Override
    public void enterStatement(MScGrammarParser.StatementContext ctx) {
    }

    @Override
    public void exitStatement(MScGrammarParser.StatementContext ctx) {
        // chain succession for sequence
        if (postActivityTransitions.size() == 1 && preActivityTransitions.size() == 1) {
            String fromActivity = postActivityTransitions.stream().findFirst().orElse(null);
            String toActivity = preActivityTransitions.stream().findFirst().orElse(null);
            constraints.add(new DeclareConstraint(DeclareConstraintType.CHAIN_SUCCESSION, fromActivity, toActivity));
            return;
        }

        // coexistence + alternate response for others
        for (String postActivityTransition : postActivityTransitions) {
            for (String preActivityTransition : preActivityTransitions) {
                constraints.add(new DeclareConstraint(DeclareConstraintType.COEXISTENCE, postActivityTransition, preActivityTransition));
                constraints.add(new DeclareConstraint(DeclareConstraintType.ALTERNATE_RESPONSE, postActivityTransition, preActivityTransition));
            }
        }
    }

    @Override
    public void enterAfterStatement(MScGrammarParser.AfterStatementContext ctx) {

    }

    @Override
    public void exitAfterStatement(MScGrammarParser.AfterStatementContext ctx) {

    }

    @Override
    public void enterAsp(MScGrammarParser.AspContext ctx) {

    }

    @Override
    public void exitAsp(MScGrammarParser.AspContext ctx) {

    }

    @Override
    public void enterOsp(MScGrammarParser.OspContext ctx) {

    }

    @Override
    public void exitOsp(MScGrammarParser.OspContext ctx) {

    }

    @Override
    public void enterImmediatelyExpression(MScGrammarParser.ImmediatelyExpressionContext ctx) {

    }

    @Override
    public void exitImmediatelyExpression(MScGrammarParser.ImmediatelyExpressionContext ctx) {

    }

    @Override
    public void enterEventuallyExpression(MScGrammarParser.EventuallyExpressionContext ctx) {

    }

    @Override
    public void exitEventuallyExpression(MScGrammarParser.EventuallyExpressionContext ctx) {

    }

    @Override
    public void enterSequencePreActivityExpression(MScGrammarParser.SequencePreActivityExpressionContext ctx) {

    }

    @Override
    public void exitSequencePreActivityExpression(MScGrammarParser.SequencePreActivityExpressionContext ctx) {

    }

    @Override
    public void exitActivity(MScGrammarParser.ActivityContext ctx) {
        activities.add(HelperFunctions.getActivityText(ctx.WORD()));
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
    public void enterInitialStatement(MScGrammarParser.InitialStatementContext ctx) {
    }

    @Override
    public void exitInitialStatement(MScGrammarParser.InitialStatementContext ctx) {
        // constraints.add(new DeclareConstraint(DeclareConstraintType.INIT, HelperFunctions.getActivityText(ctx.preActivityExpression()..activity(0).WORD()), null));
    }

    @Override
    public void visitErrorNode(ErrorNode node) {
        System.out.println("Visit error node: " + node);
    }

    @Override
    public void exitDescription(MScGrammarParser.DescriptionContext ctx) {
        System.out.println("Activities: " + activities);
        System.out.println("Constraints: " + constraints);

        PrintWriter printWriter;
        try {
            printWriter = new PrintWriter(new FileWriter("./declare_model.decl"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (String activity: activities) {
            printWriter.printf("activity %s\n", activity);
        }

        for (DeclareConstraint constraint: constraints) {
            printWriter.printf("%s\n", constraint.getRumString());
        }

        printWriter.close();
    }

    // -----------------------------------------------------------------------------------------------
    // Methods below this line are unused
    // -----------------------------------------------------------------------------------------------

    @Override
    public void enterDescription(MScGrammarParser.DescriptionContext ctx) { }

    @Override
    public void enterLeadingStatement(MScGrammarParser.LeadingStatementContext ctx) { }

    @Override
    public void exitLeadingStatement(MScGrammarParser.LeadingStatementContext ctx) { }

    @Override
    public void enterStatementList(MScGrammarParser.StatementListContext ctx) { }

    @Override
    public void exitStatementList(MScGrammarParser.StatementListContext ctx) { }

    @Override
    public void enterEveryRule(ParserRuleContext ctx) { }

    @Override
    public void exitEveryRule(ParserRuleContext ctx) { }

    @Override
    public void visitTerminal(TerminalNode node) { }

    @Override
    public void enterActivity(MScGrammarParser.ActivityContext ctx) { }
}