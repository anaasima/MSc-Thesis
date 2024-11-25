import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.*;

public class MScGrammarContextListener implements MScGrammarListener {
    private final SentenceParser sentenceParser;
    private final SharedModelStorage modelStorage;

    private StatementMetadata currentStatement;
    private int statementNumber = 0;

    public MScGrammarContextListener(SentenceParser sentenceParser) {
        this.sentenceParser = sentenceParser;
        modelStorage = SharedModelStorage.getInstance();
    }

    @Override
    public void exitClosingStatement(MScGrammarParser.ClosingStatementContext ctx) {
        sentenceParser.handleClosingStatement(ctx.activity().WORD());
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
        statementNumber += 1;
        currentStatement = new StatementMetadata(statementNumber);
    }

    @Override
    public void enterAfterStatement(MScGrammarParser.AfterStatementContext ctx) {
        currentStatement.setStatementType(StatementType.AFTER);
    }

    @Override
    public void exitAfterStatement(MScGrammarParser.AfterStatementContext ctx) {
        sentenceParser.setStatementMetadata(currentStatement);

        if (currentStatement.getPostActivityType() == PostActivityType.SEQUENCE) {
            switch (currentStatement.getPreActivityType()) {
                case IMMEDIATELY_SEQUENCE -> sentenceParser.handlePreSequencePostSequence();
                case IMMEDIATELY_AND -> sentenceParser.handlePreAndPostSequence();
                case IMMEDIATELY_OR -> sentenceParser.handlePreOrPostSequence();
                case EVENTUALLY_SEQUENCE -> sentenceParser.handePreEventuallyPostSequence();
                default -> throw new IllegalStateException("Cannot handle " + currentStatement.getPreActivityType());
            }
        } else if (currentStatement.getPostActivityType() == PostActivityType.AND) {
            switch (currentStatement.getPreActivityType()) {
                case IMMEDIATELY_SEQUENCE -> sentenceParser.handlePreSequencePostAnd();
                case IMMEDIATELY_AND -> sentenceParser.handlePreAndPostAnd();
                case IMMEDIATELY_OR -> sentenceParser.handlePreOrPostAnd();
                case EVENTUALLY_SEQUENCE -> sentenceParser.handePreEventuallyPostAnd();
                default -> throw new IllegalStateException("Cannot handle " + currentStatement.getPreActivityType());
            }
        } else if (currentStatement.getPostActivityType() == PostActivityType.OR) {
            switch (currentStatement.getPreActivityType()) {
                case IMMEDIATELY_SEQUENCE -> sentenceParser.handlePreSequencePostOr();
                case IMMEDIATELY_AND -> sentenceParser.handlePreAndPostOr();
                case IMMEDIATELY_OR -> sentenceParser.handlePreOrPostOr();
                case EVENTUALLY_SEQUENCE -> sentenceParser.handePreEventuallyPostOr();
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
        String aspId = HelperFunctions.getActivityText(ctx.aspId().WORD());
        modelStorage.addAndSubProcess(aspId, aspActivities);
        sentenceParser.handleAspDeclaration(aspId);
    }

    @Override
    public void enterOsp(MScGrammarParser.OspContext ctx) {
        currentStatement.setStatementType(StatementType.OR_SUBPROCESS);
    }

    @Override
    public void exitOsp(MScGrammarParser.OspContext ctx) {
        List<Activity> ospActivities = ctx.activity().stream().map(a -> new Activity(HelperFunctions.getActivityText(a.WORD()), ActivityType.ACTIVITY)).toList();
        String ospId = HelperFunctions.getActivityText(ctx.ospId().WORD());
        modelStorage.addOrSubProcess(ospId, ospActivities);
        sentenceParser.handleOspDeclaration(ospId);
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
    public void exitInitialStatement(MScGrammarParser.InitialStatementContext ctx) {
        sentenceParser.handleInitialStatement(ctx.activity().WORD());
    }

    @Override
    public void exitActivity(MScGrammarParser.ActivityContext ctx) {
        sentenceParser.handleActivity((ctx.WORD()));
    }

    @Override
    public void visitErrorNode(ErrorNode node) {
        System.out.println("Visit error node: " + node);
    }

    @Override
    public void exitDescription(MScGrammarParser.DescriptionContext ctx) {
       sentenceParser.printAndSaveModel();
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
    public void enterActivity(MScGrammarParser.ActivityContext ctx) { }

    @Override
    public void enterAspId(MScGrammarParser.AspIdContext ctx) { }

    @Override
    public void exitAspId(MScGrammarParser.AspIdContext ctx) { }

    @Override
    public void enterOspId(MScGrammarParser.OspIdContext ctx) { }

    @Override
    public void exitOspId(MScGrammarParser.OspIdContext ctx) { }

    @Override
    public void visitTerminal(TerminalNode terminalNode) { }

    @Override
    public void exitPostActivityExpression(MScGrammarParser.PostActivityExpressionContext ctx) { }

    @Override
    public void exitEventuallyExpression(MScGrammarParser.EventuallyExpressionContext ctx) { }

    @Override
    public void exitImmediatelyExpression(MScGrammarParser.ImmediatelyExpressionContext ctx) { }

    @Override
    public void exitStatement(MScGrammarParser.StatementContext ctx) { }

    @Override
    public void enterInitialStatement(MScGrammarParser.InitialStatementContext ctx) { }

    @Override
    public void enterClosingStatement(MScGrammarParser.ClosingStatementContext ctx) { }
}
