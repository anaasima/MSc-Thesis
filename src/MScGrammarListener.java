// Generated from C:/Users/Ana-Maria/IdeaProjects/MSc-Thesis/src/MScGrammar.g4 by ANTLR 4.13.2
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
	 * Enter a parse tree produced by {@link MScGrammarParser#preActivityExpression}.
	 * @param ctx the parse tree
	 */
	void enterPreActivityExpression(MScGrammarParser.PreActivityExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MScGrammarParser#preActivityExpression}.
	 * @param ctx the parse tree
	 */
	void exitPreActivityExpression(MScGrammarParser.PreActivityExpressionContext ctx);
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
}