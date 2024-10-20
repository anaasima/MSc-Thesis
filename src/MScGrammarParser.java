// Generated from C:/Users/Ana-Maria/IdeaProjects/MSc-Thesis/src/MScGrammar.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class MScGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		WORD=10, SPACE=11, NEWLINE=12;
	public static final int
		RULE_description = 0, RULE_leadingStatement = 1, RULE_closingStatement = 2, 
		RULE_postActivityExpression = 3, RULE_preActivityExpression = 4, RULE_statement = 5, 
		RULE_activity = 6, RULE_initialStatement = 7, RULE_statementList = 8;
	private static String[] makeRuleNames() {
		return new String[] {
			"description", "leadingStatement", "closingStatement", "postActivityExpression", 
			"preActivityExpression", "statement", "activity", "initialStatement", 
			"statementList"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'The following textual description follows the closed-world assumption, meaning that only the activities specified can be executed in the specified order. Any possible activity and execution that is not specified is considered impossible.'", 
			"'After '", "', the process finishes.'", "' ends'", "', '", "', immediately start '", 
			"'.'", "'\"'", "'Initially start '", null, "' '"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, "WORD", "SPACE", 
			"NEWLINE"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "MScGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MScGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DescriptionContext extends ParserRuleContext {
		public LeadingStatementContext leadingStatement() {
			return getRuleContext(LeadingStatementContext.class,0);
		}
		public InitialStatementContext initialStatement() {
			return getRuleContext(InitialStatementContext.class,0);
		}
		public ClosingStatementContext closingStatement() {
			return getRuleContext(ClosingStatementContext.class,0);
		}
		public StatementListContext statementList() {
			return getRuleContext(StatementListContext.class,0);
		}
		public DescriptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_description; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MScGrammarListener ) ((MScGrammarListener)listener).enterDescription(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MScGrammarListener ) ((MScGrammarListener)listener).exitDescription(this);
		}
	}

	public final DescriptionContext description() throws RecognitionException {
		DescriptionContext _localctx = new DescriptionContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_description);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(18);
			leadingStatement();
			setState(19);
			initialStatement();
			setState(21);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				setState(20);
				statementList();
				}
				break;
			}
			setState(23);
			closingStatement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LeadingStatementContext extends ParserRuleContext {
		public TerminalNode NEWLINE() { return getToken(MScGrammarParser.NEWLINE, 0); }
		public LeadingStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_leadingStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MScGrammarListener ) ((MScGrammarListener)listener).enterLeadingStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MScGrammarListener ) ((MScGrammarListener)listener).exitLeadingStatement(this);
		}
	}

	public final LeadingStatementContext leadingStatement() throws RecognitionException {
		LeadingStatementContext _localctx = new LeadingStatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_leadingStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(25);
			match(T__0);
			setState(26);
			match(NEWLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ClosingStatementContext extends ParserRuleContext {
		public PostActivityExpressionContext postActivityExpression() {
			return getRuleContext(PostActivityExpressionContext.class,0);
		}
		public ClosingStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_closingStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MScGrammarListener ) ((MScGrammarListener)listener).enterClosingStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MScGrammarListener ) ((MScGrammarListener)listener).exitClosingStatement(this);
		}
	}

	public final ClosingStatementContext closingStatement() throws RecognitionException {
		ClosingStatementContext _localctx = new ClosingStatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_closingStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28);
			match(T__1);
			setState(29);
			postActivityExpression();
			setState(30);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PostActivityExpressionContext extends ParserRuleContext {
		public List<ActivityContext> activity() {
			return getRuleContexts(ActivityContext.class);
		}
		public ActivityContext activity(int i) {
			return getRuleContext(ActivityContext.class,i);
		}
		public PostActivityExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_postActivityExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MScGrammarListener ) ((MScGrammarListener)listener).enterPostActivityExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MScGrammarListener ) ((MScGrammarListener)listener).exitPostActivityExpression(this);
		}
	}

	public final PostActivityExpressionContext postActivityExpression() throws RecognitionException {
		PostActivityExpressionContext _localctx = new PostActivityExpressionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_postActivityExpression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(32);
			activity();
			setState(33);
			match(T__3);
			setState(40);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(34);
					match(T__4);
					setState(35);
					activity();
					setState(36);
					match(T__3);
					}
					} 
				}
				setState(42);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PreActivityExpressionContext extends ParserRuleContext {
		public List<ActivityContext> activity() {
			return getRuleContexts(ActivityContext.class);
		}
		public ActivityContext activity(int i) {
			return getRuleContext(ActivityContext.class,i);
		}
		public PreActivityExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_preActivityExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MScGrammarListener ) ((MScGrammarListener)listener).enterPreActivityExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MScGrammarListener ) ((MScGrammarListener)listener).exitPreActivityExpression(this);
		}
	}

	public final PreActivityExpressionContext preActivityExpression() throws RecognitionException {
		PreActivityExpressionContext _localctx = new PreActivityExpressionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_preActivityExpression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(43);
			activity();
			setState(48);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(44);
					match(T__4);
					setState(45);
					activity();
					}
					} 
				}
				setState(50);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public PostActivityExpressionContext postActivityExpression() {
			return getRuleContext(PostActivityExpressionContext.class,0);
		}
		public PreActivityExpressionContext preActivityExpression() {
			return getRuleContext(PreActivityExpressionContext.class,0);
		}
		public TerminalNode NEWLINE() { return getToken(MScGrammarParser.NEWLINE, 0); }
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MScGrammarListener ) ((MScGrammarListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MScGrammarListener ) ((MScGrammarListener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			match(T__1);
			setState(52);
			postActivityExpression();
			setState(53);
			match(T__5);
			setState(54);
			preActivityExpression();
			setState(55);
			match(T__6);
			setState(56);
			match(NEWLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ActivityContext extends ParserRuleContext {
		public List<TerminalNode> WORD() { return getTokens(MScGrammarParser.WORD); }
		public TerminalNode WORD(int i) {
			return getToken(MScGrammarParser.WORD, i);
		}
		public List<TerminalNode> SPACE() { return getTokens(MScGrammarParser.SPACE); }
		public TerminalNode SPACE(int i) {
			return getToken(MScGrammarParser.SPACE, i);
		}
		public ActivityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_activity; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MScGrammarListener ) ((MScGrammarListener)listener).enterActivity(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MScGrammarListener ) ((MScGrammarListener)listener).exitActivity(this);
		}
	}

	public final ActivityContext activity() throws RecognitionException {
		ActivityContext _localctx = new ActivityContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_activity);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			match(T__7);
			setState(59);
			match(WORD);
			setState(64);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(60);
					match(SPACE);
					setState(61);
					match(WORD);
					}
					} 
				}
				setState(66);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			}
			setState(67);
			match(T__7);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InitialStatementContext extends ParserRuleContext {
		public PreActivityExpressionContext preActivityExpression() {
			return getRuleContext(PreActivityExpressionContext.class,0);
		}
		public TerminalNode NEWLINE() { return getToken(MScGrammarParser.NEWLINE, 0); }
		public InitialStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_initialStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MScGrammarListener ) ((MScGrammarListener)listener).enterInitialStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MScGrammarListener ) ((MScGrammarListener)listener).exitInitialStatement(this);
		}
	}

	public final InitialStatementContext initialStatement() throws RecognitionException {
		InitialStatementContext _localctx = new InitialStatementContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_initialStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			match(T__8);
			setState(70);
			preActivityExpression();
			setState(71);
			match(T__6);
			setState(72);
			match(NEWLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementListContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public StatementListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statementList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MScGrammarListener ) ((MScGrammarListener)listener).enterStatementList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MScGrammarListener ) ((MScGrammarListener)listener).exitStatementList(this);
		}
	}

	public final StatementListContext statementList() throws RecognitionException {
		StatementListContext _localctx = new StatementListContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_statementList);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			statement();
			setState(78);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(75);
					statement();
					}
					} 
				}
				setState(80);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\fR\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0001\u0000\u0001\u0000\u0001\u0000\u0003\u0000\u0016\b\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0005\u0003\'\b\u0003\n\u0003\f\u0003"+
		"*\t\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0005\u0004/\b\u0004\n\u0004"+
		"\f\u00042\t\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0005\u0006?\b\u0006\n\u0006\f\u0006B\t\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\b\u0001\b\u0005\bM\b\b\n\b\f\bP\t\b\u0001\b\u0004(0@N\u0000\t\u0000\u0002"+
		"\u0004\u0006\b\n\f\u000e\u0010\u0000\u0000M\u0000\u0012\u0001\u0000\u0000"+
		"\u0000\u0002\u0019\u0001\u0000\u0000\u0000\u0004\u001c\u0001\u0000\u0000"+
		"\u0000\u0006 \u0001\u0000\u0000\u0000\b+\u0001\u0000\u0000\u0000\n3\u0001"+
		"\u0000\u0000\u0000\f:\u0001\u0000\u0000\u0000\u000eE\u0001\u0000\u0000"+
		"\u0000\u0010J\u0001\u0000\u0000\u0000\u0012\u0013\u0003\u0002\u0001\u0000"+
		"\u0013\u0015\u0003\u000e\u0007\u0000\u0014\u0016\u0003\u0010\b\u0000\u0015"+
		"\u0014\u0001\u0000\u0000\u0000\u0015\u0016\u0001\u0000\u0000\u0000\u0016"+
		"\u0017\u0001\u0000\u0000\u0000\u0017\u0018\u0003\u0004\u0002\u0000\u0018"+
		"\u0001\u0001\u0000\u0000\u0000\u0019\u001a\u0005\u0001\u0000\u0000\u001a"+
		"\u001b\u0005\f\u0000\u0000\u001b\u0003\u0001\u0000\u0000\u0000\u001c\u001d"+
		"\u0005\u0002\u0000\u0000\u001d\u001e\u0003\u0006\u0003\u0000\u001e\u001f"+
		"\u0005\u0003\u0000\u0000\u001f\u0005\u0001\u0000\u0000\u0000 !\u0003\f"+
		"\u0006\u0000!(\u0005\u0004\u0000\u0000\"#\u0005\u0005\u0000\u0000#$\u0003"+
		"\f\u0006\u0000$%\u0005\u0004\u0000\u0000%\'\u0001\u0000\u0000\u0000&\""+
		"\u0001\u0000\u0000\u0000\'*\u0001\u0000\u0000\u0000()\u0001\u0000\u0000"+
		"\u0000(&\u0001\u0000\u0000\u0000)\u0007\u0001\u0000\u0000\u0000*(\u0001"+
		"\u0000\u0000\u0000+0\u0003\f\u0006\u0000,-\u0005\u0005\u0000\u0000-/\u0003"+
		"\f\u0006\u0000.,\u0001\u0000\u0000\u0000/2\u0001\u0000\u0000\u000001\u0001"+
		"\u0000\u0000\u00000.\u0001\u0000\u0000\u00001\t\u0001\u0000\u0000\u0000"+
		"20\u0001\u0000\u0000\u000034\u0005\u0002\u0000\u000045\u0003\u0006\u0003"+
		"\u000056\u0005\u0006\u0000\u000067\u0003\b\u0004\u000078\u0005\u0007\u0000"+
		"\u000089\u0005\f\u0000\u00009\u000b\u0001\u0000\u0000\u0000:;\u0005\b"+
		"\u0000\u0000;@\u0005\n\u0000\u0000<=\u0005\u000b\u0000\u0000=?\u0005\n"+
		"\u0000\u0000><\u0001\u0000\u0000\u0000?B\u0001\u0000\u0000\u0000@A\u0001"+
		"\u0000\u0000\u0000@>\u0001\u0000\u0000\u0000AC\u0001\u0000\u0000\u0000"+
		"B@\u0001\u0000\u0000\u0000CD\u0005\b\u0000\u0000D\r\u0001\u0000\u0000"+
		"\u0000EF\u0005\t\u0000\u0000FG\u0003\b\u0004\u0000GH\u0005\u0007\u0000"+
		"\u0000HI\u0005\f\u0000\u0000I\u000f\u0001\u0000\u0000\u0000JN\u0003\n"+
		"\u0005\u0000KM\u0003\n\u0005\u0000LK\u0001\u0000\u0000\u0000MP\u0001\u0000"+
		"\u0000\u0000NO\u0001\u0000\u0000\u0000NL\u0001\u0000\u0000\u0000O\u0011"+
		"\u0001\u0000\u0000\u0000PN\u0001\u0000\u0000\u0000\u0005\u0015(0@N";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}