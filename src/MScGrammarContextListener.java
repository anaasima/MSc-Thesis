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
    public void enterClosingStatement(MScGrammarParser.ClosingStatementContext ctx) {
        currentStatement.setStatementType(StatementType.CLOSING);
    }

    @Override
    public void exitClosingStatement(MScGrammarParser.ClosingStatementContext ctx) {
        sentenceParser.setStatementMetadata(currentStatement);
        if (currentStatement.getPostActivityType() == PostActivityType.SEQUENCE) {
            sentenceParser.handleClosingStatementSequence();
        } else if (currentStatement.getPostActivityType() == PostActivityType.AND) {
            sentenceParser.handleClosingStatementAnd();
        } else if (currentStatement.getPostActivityType() == PostActivityType.OR) {
            sentenceParser.handleClosingStatementOr();
        } else {
            throw new IllegalStateException("Cannot handle " + currentStatement.getPostActivityType());
        }
    }

    @Override
    public void enterAndEndActivityExpression(MScGrammarParser.AndEndActivityExpressionContext ctx) {
        currentStatement.setPostActivityType(PostActivityType.AND);
    }

    @Override
    public void exitAndEndActivityExpression(MScGrammarParser.AndEndActivityExpressionContext ctx) {
        handleAndExpression(ctx.activity(), ctx.orSubProcessId());
    }

    @Override
    public void enterAndStartActivityExpression(MScGrammarParser.AndStartActivityExpressionContext ctx) {
        currentStatement.setPreActivityType(PreActivityType.IMMEDIATELY_AND);
    }

    @Override
    public void exitAndStartActivityExpression(MScGrammarParser.AndStartActivityExpressionContext ctx) {
        handleAndExpression(ctx.activity(), ctx.orSubProcessId());
    }

    @Override
    public void enterOrEndActivityExpression(MScGrammarParser.OrEndActivityExpressionContext ctx) {
        currentStatement.setPostActivityType(PostActivityType.OR);
    }

    @Override
    public void exitOrEndActivityExpression(MScGrammarParser.OrEndActivityExpressionContext ctx) {
        handleOrExpression(ctx.activity(), ctx.andSubProcessId());
    }

    @Override
    public void enterOrStartActivityExpression(MScGrammarParser.OrStartActivityExpressionContext ctx) {
        currentStatement.setPreActivityType(PreActivityType.IMMEDIATELY_OR);
    }

    @Override
    public void exitOrStartActivityExpression(MScGrammarParser.OrStartActivityExpressionContext ctx) {
        handleOrExpression(ctx.activity(), ctx.andSubProcessId());
    }

    @Override
    public void enterRepeatSinceStartActivityExpression(MScGrammarParser.RepeatSinceStartActivityExpressionContext ctx) {
        currentStatement.setPreActivityType(PreActivityType.IMMEDIATELY_REPEAT_SINCE);
    }

    @Override
    public void exitRepeatSinceStartActivityExpression(MScGrammarParser.RepeatSinceStartActivityExpressionContext ctx) {
        currentStatement.addActivity(new Activity(HelperFunctions.getActivityText(ctx.activity(0).WORD()), ActivityType.REPEAT_SINCE_ACTIVITY));
        if (ctx.activity().size() > 1) {
            for (int i = 1; i < ctx.activity().size(); i++) {
                handleSequenceExpression(ctx.activity(i));
            }
        }
        for (int i = 0; i < ctx.andSubProcessId().size(); i++) {
            currentStatement.addActivity(new Activity(HelperFunctions.getActivityText(ctx.andSubProcessId(i).WORD()), ActivityType.AND_SUBPROCESS));
        }
    }

    @Override
    public void enterEndActivityExpression(MScGrammarParser.EndActivityExpressionContext ctx) {
        currentStatement.setCurrentContextType(ContextType.POST_ACTIVITY);
    }

    @Override
    public void enterSequenceEndActivityExpression(MScGrammarParser.SequenceEndActivityExpressionContext ctx) {
        currentStatement.setPostActivityType(PostActivityType.SEQUENCE);
    }

    @Override
    public void exitSequenceEndActivityExpression(MScGrammarParser.SequenceEndActivityExpressionContext ctx) {
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
                case IMMEDIATELY_REPEAT_SINCE -> sentenceParser.handlePreRepeatSincePostSequence();
                case EVENTUALLY_SEQUENCE -> sentenceParser.handePreEventuallyPostSequence();
                default -> throw new IllegalStateException("Cannot handle " + currentStatement.getPreActivityType());
            }
        } else if (currentStatement.getPostActivityType() == PostActivityType.AND) {
            switch (currentStatement.getPreActivityType()) {
                case IMMEDIATELY_SEQUENCE -> sentenceParser.handlePreSequencePostAnd();
                case IMMEDIATELY_AND -> sentenceParser.handlePreAndPostAnd();
                case IMMEDIATELY_OR -> sentenceParser.handlePreOrPostAnd();
                case IMMEDIATELY_REPEAT_SINCE -> sentenceParser.handlePreRepeatSincePostAnd();
                case EVENTUALLY_SEQUENCE -> sentenceParser.handePreEventuallyPostAnd();
                default -> throw new IllegalStateException("Cannot handle " + currentStatement.getPreActivityType());
            }
        } else if (currentStatement.getPostActivityType() == PostActivityType.OR) {
            switch (currentStatement.getPreActivityType()) {
                case IMMEDIATELY_SEQUENCE -> sentenceParser.handlePreSequencePostOr();
                case IMMEDIATELY_AND -> sentenceParser.handlePreAndPostOr();
                case IMMEDIATELY_OR -> sentenceParser.handlePreOrPostOr();
                case IMMEDIATELY_REPEAT_SINCE -> sentenceParser.handlePreRepeatSincePostOr();
                case EVENTUALLY_SEQUENCE -> sentenceParser.handePreEventuallyPostOr();
                default -> throw new IllegalStateException("Cannot handle " + currentStatement.getPreActivityType());
            }
        } else {
            throw new IllegalStateException("Cannot handle " + currentStatement.getPostActivityType());
        }
    }

    @Override
    public void enterAndSubProcess(MScGrammarParser.AndSubProcessContext ctx) {
        currentStatement.setStatementType(StatementType.AND_SUBPROCESS);
    }

    @Override
    public void exitAndSubProcess(MScGrammarParser.AndSubProcessContext ctx) {
        List<Activity> aspActivities = ctx.activity().stream().map(a -> new Activity(HelperFunctions.getActivityText(a.WORD()), ActivityType.ACTIVITY)).toList();
        String aspId = HelperFunctions.getActivityText(ctx.andSubProcessId().WORD());
        modelStorage.addAndSubProcess(aspId, aspActivities);
        sentenceParser.handleAspDeclaration(aspId);
    }

    @Override
    public void enterOrSubProcess(MScGrammarParser.OrSubProcessContext ctx) {
        currentStatement.setStatementType(StatementType.OR_SUBPROCESS);
    }

    @Override
    public void exitOrSubProcess(MScGrammarParser.OrSubProcessContext ctx) {
        List<Activity> ospActivities = ctx.activity().stream().map(a -> new Activity(HelperFunctions.getActivityText(a.WORD()), ActivityType.ACTIVITY)).toList();
        String ospId = HelperFunctions.getActivityText(ctx.orSubProcessId().WORD());
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
    public void enterSequenceStartActivityExpression(MScGrammarParser.SequenceStartActivityExpressionContext ctx) {
        if (currentStatement.getCurrentContextType() == ContextType.PRE_ACTIVITY_EVENTUALLY) {
            currentStatement.setPreActivityType(PreActivityType.EVENTUALLY_SEQUENCE);
        } else if (currentStatement.getCurrentContextType() == ContextType.PRE_ACTIVITY_IMMEDIATELY) {
            currentStatement.setPreActivityType(PreActivityType.IMMEDIATELY_SEQUENCE);
        }
    }

    @Override
    public void exitSequenceStartActivityExpression(MScGrammarParser.SequenceStartActivityExpressionContext ctx) {
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

    private void handleAndExpression(List<MScGrammarParser.ActivityContext> activities, List<MScGrammarParser.OrSubProcessIdContext> ospIdContexts) {
        for (MScGrammarParser.ActivityContext activity : activities) {
            currentStatement.addActivity(new Activity(HelperFunctions.getActivityText(activity.WORD()), ActivityType.ACTIVITY));
        }
        for (MScGrammarParser.OrSubProcessIdContext ospId : ospIdContexts) {
            currentStatement.addActivity(new Activity(HelperFunctions.getActivityText(ospId.WORD()), ActivityType.OR_SUBPROCESS));
        }
    }

    private void handleOrExpression(List<MScGrammarParser.ActivityContext> activities, List<MScGrammarParser.AndSubProcessIdContext> aspIdContexts) {
        for (MScGrammarParser.ActivityContext activity : activities) {
            currentStatement.addActivity(new Activity(HelperFunctions.getActivityText(activity.WORD()), ActivityType.ACTIVITY));
        }
        for (MScGrammarParser.AndSubProcessIdContext aspId : aspIdContexts) {
            currentStatement.addActivity(new Activity(HelperFunctions.getActivityText(aspId.WORD()), ActivityType.AND_SUBPROCESS));
        }
    }

    // -----------------------------------------------------------------------------------------------
    // Methods below this line are unused
    // -----------------------------------------------------------------------------------------------

    @Override
    public void enterDescription(MScGrammarParser.DescriptionContext ctx) { }

    @Override
    public void enterLeadingText(MScGrammarParser.LeadingTextContext ctx) { }

    @Override
    public void exitLeadingText(MScGrammarParser.LeadingTextContext ctx) { }

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
    public void enterAndSubProcessId(MScGrammarParser.AndSubProcessIdContext ctx) { }

    @Override
    public void exitAndSubProcessId(MScGrammarParser.AndSubProcessIdContext ctx) { }

    @Override
    public void enterOrSubProcessId(MScGrammarParser.OrSubProcessIdContext ctx) { }

    @Override
    public void exitOrSubProcessId(MScGrammarParser.OrSubProcessIdContext ctx) { }

    @Override
    public void visitTerminal(TerminalNode terminalNode) { }

    @Override
    public void exitEndActivityExpression(MScGrammarParser.EndActivityExpressionContext ctx) { }

    @Override
    public void exitEventuallyExpression(MScGrammarParser.EventuallyExpressionContext ctx) { }

    @Override
    public void exitImmediatelyExpression(MScGrammarParser.ImmediatelyExpressionContext ctx) { }

    @Override
    public void exitStatement(MScGrammarParser.StatementContext ctx) { }

    @Override
    public void enterInitialStatement(MScGrammarParser.InitialStatementContext ctx) { }
}
