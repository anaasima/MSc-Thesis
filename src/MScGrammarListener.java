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
	 * Enter a parse tree produced by {@link MScGrammarParser#leadingStatement}.
	 * @param ctx the parse tree
	 */
	void enterLeadingStatement(MScGrammarParser.LeadingStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MScGrammarParser#leadingStatement}.
	 * @param ctx the parse tree
	 */
	void exitLeadingStatement(MScGrammarParser.LeadingStatementContext ctx);
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
	 * Enter a parse tree produced by {@link MScGrammarParser#asp}.
	 * @param ctx the parse tree
	 */
	void enterAsp(MScGrammarParser.AspContext ctx);
	/**
	 * Exit a parse tree produced by {@link MScGrammarParser#asp}.
	 * @param ctx the parse tree
	 */
	void exitAsp(MScGrammarParser.AspContext ctx);
	/**
	 * Enter a parse tree produced by {@link MScGrammarParser#osp}.
	 * @param ctx the parse tree
	 */
	void enterOsp(MScGrammarParser.OspContext ctx);
	/**
	 * Exit a parse tree produced by {@link MScGrammarParser#osp}.
	 * @param ctx the parse tree
	 */
	void exitOsp(MScGrammarParser.OspContext ctx);
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
	 * Enter a parse tree produced by {@link MScGrammarParser#sequencePreActivityExpression}.
	 * @param ctx the parse tree
	 */
	void enterSequencePreActivityExpression(MScGrammarParser.SequencePreActivityExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MScGrammarParser#sequencePreActivityExpression}.
	 * @param ctx the parse tree
	 */
	void exitSequencePreActivityExpression(MScGrammarParser.SequencePreActivityExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MScGrammarParser#andPreActivityExpression}.
	 * @param ctx the parse tree
	 */
	void enterAndPreActivityExpression(MScGrammarParser.AndPreActivityExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MScGrammarParser#andPreActivityExpression}.
	 * @param ctx the parse tree
	 */
	void exitAndPreActivityExpression(MScGrammarParser.AndPreActivityExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MScGrammarParser#orPreActivityExpression}.
	 * @param ctx the parse tree
	 */
	void enterOrPreActivityExpression(MScGrammarParser.OrPreActivityExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MScGrammarParser#orPreActivityExpression}.
	 * @param ctx the parse tree
	 */
	void exitOrPreActivityExpression(MScGrammarParser.OrPreActivityExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MScGrammarParser#repeatSincePreActivityExpression}.
	 * @param ctx the parse tree
	 */
	void enterRepeatSincePreActivityExpression(MScGrammarParser.RepeatSincePreActivityExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MScGrammarParser#repeatSincePreActivityExpression}.
	 * @param ctx the parse tree
	 */
	void exitRepeatSincePreActivityExpression(MScGrammarParser.RepeatSincePreActivityExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MScGrammarParser#postActivityExpression}.
	 * @param ctx the parse tree
	 */
	void enterPostActivityExpression(MScGrammarParser.PostActivityExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MScGrammarParser#postActivityExpression}.
	 * @param ctx the parse tree
	 */
	void exitPostActivityExpression(MScGrammarParser.PostActivityExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MScGrammarParser#sequencePostActivityExpression}.
	 * @param ctx the parse tree
	 */
	void enterSequencePostActivityExpression(MScGrammarParser.SequencePostActivityExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MScGrammarParser#sequencePostActivityExpression}.
	 * @param ctx the parse tree
	 */
	void exitSequencePostActivityExpression(MScGrammarParser.SequencePostActivityExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MScGrammarParser#andPostActivityExpression}.
	 * @param ctx the parse tree
	 */
	void enterAndPostActivityExpression(MScGrammarParser.AndPostActivityExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MScGrammarParser#andPostActivityExpression}.
	 * @param ctx the parse tree
	 */
	void exitAndPostActivityExpression(MScGrammarParser.AndPostActivityExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MScGrammarParser#orPostActivityExpression}.
	 * @param ctx the parse tree
	 */
	void enterOrPostActivityExpression(MScGrammarParser.OrPostActivityExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MScGrammarParser#orPostActivityExpression}.
	 * @param ctx the parse tree
	 */
	void exitOrPostActivityExpression(MScGrammarParser.OrPostActivityExpressionContext ctx);
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
	 * Enter a parse tree produced by {@link MScGrammarParser#aspId}.
	 * @param ctx the parse tree
	 */
	void enterAspId(MScGrammarParser.AspIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link MScGrammarParser#aspId}.
	 * @param ctx the parse tree
	 */
	void exitAspId(MScGrammarParser.AspIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link MScGrammarParser#ospId}.
	 * @param ctx the parse tree
	 */
	void enterOspId(MScGrammarParser.OspIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link MScGrammarParser#ospId}.
	 * @param ctx the parse tree
	 */
	void exitOspId(MScGrammarParser.OspIdContext ctx);
}