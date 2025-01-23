// Generated from C:/Users/anton/IdeaProjects/MSc-Thesis/src/MScGrammar.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MScGrammarParser}.
 */
public interface MScGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MScGrammarParser#description}.
	 * @param ctx the parse tree
	 */
	void enterDescription(MScGrammarParser.DescriptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MScGrammarParser#description}.
	 * @param ctx the parse tree
	 */
	void exitDescription(MScGrammarParser.DescriptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MScGrammarParser#leadingText}.
	 * @param ctx the parse tree
	 */
	void enterLeadingText(MScGrammarParser.LeadingTextContext ctx);
	/**
	 * Exit a parse tree produced by {@link MScGrammarParser#leadingText}.
	 * @param ctx the parse tree
	 */
	void exitLeadingText(MScGrammarParser.LeadingTextContext ctx);
	/**
	 * Enter a parse tree produced by {@link MScGrammarParser#statementList}.
	 * @param ctx the parse tree
	 */
	void enterStatementList(MScGrammarParser.StatementListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MScGrammarParser#statementList}.
	 * @param ctx the parse tree
	 */
	void exitStatementList(MScGrammarParser.StatementListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MScGrammarParser#initialStatement}.
	 * @param ctx the parse tree
	 */
	void enterInitialStatement(MScGrammarParser.InitialStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MScGrammarParser#initialStatement}.
	 * @param ctx the parse tree
	 */
	void exitInitialStatement(MScGrammarParser.InitialStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MScGrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(MScGrammarParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MScGrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(MScGrammarParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MScGrammarParser#closingStatement}.
	 * @param ctx the parse tree
	 */
	void enterClosingStatement(MScGrammarParser.ClosingStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MScGrammarParser#closingStatement}.
	 * @param ctx the parse tree
	 */
	void exitClosingStatement(MScGrammarParser.ClosingStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MScGrammarParser#afterStatement}.
	 * @param ctx the parse tree
	 */
	void enterAfterStatement(MScGrammarParser.AfterStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MScGrammarParser#afterStatement}.
	 * @param ctx the parse tree
	 */
	void exitAfterStatement(MScGrammarParser.AfterStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MScGrammarParser#andSubProcess}.
	 * @param ctx the parse tree
	 */
	void enterAndSubProcess(MScGrammarParser.AndSubProcessContext ctx);
	/**
	 * Exit a parse tree produced by {@link MScGrammarParser#andSubProcess}.
	 * @param ctx the parse tree
	 */
	void exitAndSubProcess(MScGrammarParser.AndSubProcessContext ctx);
	/**
	 * Enter a parse tree produced by {@link MScGrammarParser#orSubProcess}.
	 * @param ctx the parse tree
	 */
	void enterOrSubProcess(MScGrammarParser.OrSubProcessContext ctx);
	/**
	 * Exit a parse tree produced by {@link MScGrammarParser#orSubProcess}.
	 * @param ctx the parse tree
	 */
	void exitOrSubProcess(MScGrammarParser.OrSubProcessContext ctx);
	/**
	 * Enter a parse tree produced by {@link MScGrammarParser#immediatelyExpression}.
	 * @param ctx the parse tree
	 */
	void enterImmediatelyExpression(MScGrammarParser.ImmediatelyExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MScGrammarParser#immediatelyExpression}.
	 * @param ctx the parse tree
	 */
	void exitImmediatelyExpression(MScGrammarParser.ImmediatelyExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MScGrammarParser#eventuallyExpression}.
	 * @param ctx the parse tree
	 */
	void enterEventuallyExpression(MScGrammarParser.EventuallyExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MScGrammarParser#eventuallyExpression}.
	 * @param ctx the parse tree
	 */
	void exitEventuallyExpression(MScGrammarParser.EventuallyExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MScGrammarParser#sequenceStartActivityExpression}.
	 * @param ctx the parse tree
	 */
	void enterSequenceStartActivityExpression(MScGrammarParser.SequenceStartActivityExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MScGrammarParser#sequenceStartActivityExpression}.
	 * @param ctx the parse tree
	 */
	void exitSequenceStartActivityExpression(MScGrammarParser.SequenceStartActivityExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MScGrammarParser#andStartActivityExpression}.
	 * @param ctx the parse tree
	 */
	void enterAndStartActivityExpression(MScGrammarParser.AndStartActivityExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MScGrammarParser#andStartActivityExpression}.
	 * @param ctx the parse tree
	 */
	void exitAndStartActivityExpression(MScGrammarParser.AndStartActivityExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MScGrammarParser#orStartActivityExpression}.
	 * @param ctx the parse tree
	 */
	void enterOrStartActivityExpression(MScGrammarParser.OrStartActivityExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MScGrammarParser#orStartActivityExpression}.
	 * @param ctx the parse tree
	 */
	void exitOrStartActivityExpression(MScGrammarParser.OrStartActivityExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MScGrammarParser#repeatSinceStartActivityExpression}.
	 * @param ctx the parse tree
	 */
	void enterRepeatSinceStartActivityExpression(MScGrammarParser.RepeatSinceStartActivityExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MScGrammarParser#repeatSinceStartActivityExpression}.
	 * @param ctx the parse tree
	 */
	void exitRepeatSinceStartActivityExpression(MScGrammarParser.RepeatSinceStartActivityExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MScGrammarParser#endActivityExpression}.
	 * @param ctx the parse tree
	 */
	void enterEndActivityExpression(MScGrammarParser.EndActivityExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MScGrammarParser#endActivityExpression}.
	 * @param ctx the parse tree
	 */
	void exitEndActivityExpression(MScGrammarParser.EndActivityExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MScGrammarParser#sequenceEndActivityExpression}.
	 * @param ctx the parse tree
	 */
	void enterSequenceEndActivityExpression(MScGrammarParser.SequenceEndActivityExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MScGrammarParser#sequenceEndActivityExpression}.
	 * @param ctx the parse tree
	 */
	void exitSequenceEndActivityExpression(MScGrammarParser.SequenceEndActivityExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MScGrammarParser#andEndActivityExpression}.
	 * @param ctx the parse tree
	 */
	void enterAndEndActivityExpression(MScGrammarParser.AndEndActivityExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MScGrammarParser#andEndActivityExpression}.
	 * @param ctx the parse tree
	 */
	void exitAndEndActivityExpression(MScGrammarParser.AndEndActivityExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MScGrammarParser#orEndActivityExpression}.
	 * @param ctx the parse tree
	 */
	void enterOrEndActivityExpression(MScGrammarParser.OrEndActivityExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MScGrammarParser#orEndActivityExpression}.
	 * @param ctx the parse tree
	 */
	void exitOrEndActivityExpression(MScGrammarParser.OrEndActivityExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MScGrammarParser#activity}.
	 * @param ctx the parse tree
	 */
	void enterActivity(MScGrammarParser.ActivityContext ctx);
	/**
	 * Exit a parse tree produced by {@link MScGrammarParser#activity}.
	 * @param ctx the parse tree
	 */
	void exitActivity(MScGrammarParser.ActivityContext ctx);
	/**
	 * Enter a parse tree produced by {@link MScGrammarParser#andSubProcessId}.
	 * @param ctx the parse tree
	 */
	void enterAndSubProcessId(MScGrammarParser.AndSubProcessIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link MScGrammarParser#andSubProcessId}.
	 * @param ctx the parse tree
	 */
	void exitAndSubProcessId(MScGrammarParser.AndSubProcessIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link MScGrammarParser#orSubProcessId}.
	 * @param ctx the parse tree
	 */
	void enterOrSubProcessId(MScGrammarParser.OrSubProcessIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link MScGrammarParser#orSubProcessId}.
	 * @param ctx the parse tree
	 */
	void exitOrSubProcessId(MScGrammarParser.OrSubProcessIdContext ctx);
}