// Generated from C:/Users/anton/IdeaProjects/MSc-Thesis/src/MScGrammar.g4 by ANTLR 4.13.2
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
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, WORD=23, SPACE=24, NEWLINE=25;
	public static final int
		RULE_description = 0, RULE_leadingStatement = 1, RULE_initialStatement = 2, 
		RULE_closingStatement = 3, RULE_statementList = 4, RULE_statement = 5, 
		RULE_afterStatement = 6, RULE_asp = 7, RULE_osp = 8, RULE_immediatelyExpression = 9, 
		RULE_eventuallyExpression = 10, RULE_sequencePreActivityExpression = 11, 
		RULE_andPreActivityExpression = 12, RULE_orPreActivityExpression = 13, 
		RULE_postActivityExpression = 14, RULE_sequencePostActivityExpression = 15, 
		RULE_andPostActivityExpression = 16, RULE_orPostActivityExpression = 17, 
		RULE_activity = 18, RULE_aspId = 19, RULE_ospId = 20;
	private static String[] makeRuleNames() {
		return new String[] {
			"description", "leadingStatement", "initialStatement", "closingStatement", 
			"statementList", "statement", "afterStatement", "asp", "osp", "immediatelyExpression", 
			"eventuallyExpression", "sequencePreActivityExpression", "andPreActivityExpression", 
			"orPreActivityExpression", "postActivityExpression", "sequencePostActivityExpression", 
			"andPostActivityExpression", "orPostActivityExpression", "activity", 
			"aspId", "ospId"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'The following textual description follows the closed-world assumption, meaning that only the activities specified can be executed in the specified order. Any possible activity and execution that is not specified is considered impossible.'", 
			"'Initially start '", "'.'", "'After '", "' ends, the process finishes.'", 
			"', '", "': '", "' and '", "' or '", "'immediately '", "'eventually '", 
			"'start '", "' and start '", "'either start '", "' or start '", "' ends'", 
			"' ends and '", "'either '", "' ends or '", "'\"'", "'('", "')'", null, 
			"' '"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, "WORD", 
			"SPACE", "NEWLINE"
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
			setState(42);
			leadingStatement();
			setState(43);
			initialStatement();
			setState(45);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				setState(44);
				statementList();
				}
				break;
			}
			setState(47);
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
		public List<TerminalNode> NEWLINE() { return getTokens(MScGrammarParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(MScGrammarParser.NEWLINE, i);
		}
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(49);
			match(T__0);
			setState(53);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(50);
				match(NEWLINE);
				}
				}
				setState(55);
				_errHandler.sync(this);
				_la = _input.LA(1);
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
	public static class InitialStatementContext extends ParserRuleContext {
		public ActivityContext activity() {
			return getRuleContext(ActivityContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(MScGrammarParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(MScGrammarParser.NEWLINE, i);
		}
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
		enterRule(_localctx, 4, RULE_initialStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			match(T__1);
			setState(57);
			activity();
			setState(58);
			match(T__2);
			setState(62);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(59);
				match(NEWLINE);
				}
				}
				setState(64);
				_errHandler.sync(this);
				_la = _input.LA(1);
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
	public static class ClosingStatementContext extends ParserRuleContext {
		public ActivityContext activity() {
			return getRuleContext(ActivityContext.class,0);
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
		enterRule(_localctx, 6, RULE_closingStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
			match(T__3);
			setState(66);
			activity();
			setState(67);
			match(T__4);
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
		enterRule(_localctx, 8, RULE_statementList);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			statement();
			setState(73);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(70);
					statement();
					}
					} 
				}
				setState(75);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
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
		public AfterStatementContext afterStatement() {
			return getRuleContext(AfterStatementContext.class,0);
		}
		public AspContext asp() {
			return getRuleContext(AspContext.class,0);
		}
		public OspContext osp() {
			return getRuleContext(OspContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(MScGrammarParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(MScGrammarParser.NEWLINE, i);
		}
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(76);
				afterStatement();
				}
				break;
			case 2:
				{
				setState(77);
				asp();
				}
				break;
			case 3:
				{
				setState(78);
				osp();
				}
				break;
			}
			setState(84);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(81);
				match(NEWLINE);
				}
				}
				setState(86);
				_errHandler.sync(this);
				_la = _input.LA(1);
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
	public static class AfterStatementContext extends ParserRuleContext {
		public PostActivityExpressionContext postActivityExpression() {
			return getRuleContext(PostActivityExpressionContext.class,0);
		}
		public ImmediatelyExpressionContext immediatelyExpression() {
			return getRuleContext(ImmediatelyExpressionContext.class,0);
		}
		public EventuallyExpressionContext eventuallyExpression() {
			return getRuleContext(EventuallyExpressionContext.class,0);
		}
		public AfterStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_afterStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MScGrammarListener ) ((MScGrammarListener)listener).enterAfterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MScGrammarListener ) ((MScGrammarListener)listener).exitAfterStatement(this);
		}
	}

	public final AfterStatementContext afterStatement() throws RecognitionException {
		AfterStatementContext _localctx = new AfterStatementContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_afterStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			match(T__3);
			setState(88);
			postActivityExpression();
			setState(89);
			match(T__5);
			setState(92);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__9:
				{
				setState(90);
				immediatelyExpression();
				}
				break;
			case T__10:
				{
				setState(91);
				eventuallyExpression();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(94);
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
	public static class AspContext extends ParserRuleContext {
		public AspIdContext aspId() {
			return getRuleContext(AspIdContext.class,0);
		}
		public List<ActivityContext> activity() {
			return getRuleContexts(ActivityContext.class);
		}
		public ActivityContext activity(int i) {
			return getRuleContext(ActivityContext.class,i);
		}
		public AspContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_asp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MScGrammarListener ) ((MScGrammarListener)listener).enterAsp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MScGrammarListener ) ((MScGrammarListener)listener).exitAsp(this);
		}
	}

	public final AspContext asp() throws RecognitionException {
		AspContext _localctx = new AspContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_asp);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			aspId();
			setState(97);
			match(T__6);
			setState(98);
			activity();
			setState(99);
			match(T__7);
			setState(100);
			activity();
			setState(105);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(101);
					match(T__7);
					setState(102);
					activity();
					}
					} 
				}
				setState(107);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			setState(108);
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
	public static class OspContext extends ParserRuleContext {
		public OspIdContext ospId() {
			return getRuleContext(OspIdContext.class,0);
		}
		public List<ActivityContext> activity() {
			return getRuleContexts(ActivityContext.class);
		}
		public ActivityContext activity(int i) {
			return getRuleContext(ActivityContext.class,i);
		}
		public OspContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_osp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MScGrammarListener ) ((MScGrammarListener)listener).enterOsp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MScGrammarListener ) ((MScGrammarListener)listener).exitOsp(this);
		}
	}

	public final OspContext osp() throws RecognitionException {
		OspContext _localctx = new OspContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_osp);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			ospId();
			setState(111);
			match(T__6);
			setState(112);
			activity();
			setState(113);
			match(T__8);
			setState(114);
			activity();
			setState(119);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(115);
					match(T__8);
					setState(116);
					activity();
					}
					} 
				}
				setState(121);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			}
			setState(122);
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
	public static class ImmediatelyExpressionContext extends ParserRuleContext {
		public SequencePreActivityExpressionContext sequencePreActivityExpression() {
			return getRuleContext(SequencePreActivityExpressionContext.class,0);
		}
		public AndPreActivityExpressionContext andPreActivityExpression() {
			return getRuleContext(AndPreActivityExpressionContext.class,0);
		}
		public OrPreActivityExpressionContext orPreActivityExpression() {
			return getRuleContext(OrPreActivityExpressionContext.class,0);
		}
		public ImmediatelyExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_immediatelyExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MScGrammarListener ) ((MScGrammarListener)listener).enterImmediatelyExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MScGrammarListener ) ((MScGrammarListener)listener).exitImmediatelyExpression(this);
		}
	}

	public final ImmediatelyExpressionContext immediatelyExpression() throws RecognitionException {
		ImmediatelyExpressionContext _localctx = new ImmediatelyExpressionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_immediatelyExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			match(T__9);
			setState(128);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(125);
				sequencePreActivityExpression();
				}
				break;
			case 2:
				{
				setState(126);
				andPreActivityExpression();
				}
				break;
			case 3:
				{
				setState(127);
				orPreActivityExpression();
				}
				break;
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
	public static class EventuallyExpressionContext extends ParserRuleContext {
		public SequencePreActivityExpressionContext sequencePreActivityExpression() {
			return getRuleContext(SequencePreActivityExpressionContext.class,0);
		}
		public EventuallyExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_eventuallyExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MScGrammarListener ) ((MScGrammarListener)listener).enterEventuallyExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MScGrammarListener ) ((MScGrammarListener)listener).exitEventuallyExpression(this);
		}
	}

	public final EventuallyExpressionContext eventuallyExpression() throws RecognitionException {
		EventuallyExpressionContext _localctx = new EventuallyExpressionContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_eventuallyExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
			match(T__10);
			setState(131);
			sequencePreActivityExpression();
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
	public static class SequencePreActivityExpressionContext extends ParserRuleContext {
		public ActivityContext activity() {
			return getRuleContext(ActivityContext.class,0);
		}
		public SequencePreActivityExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sequencePreActivityExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MScGrammarListener ) ((MScGrammarListener)listener).enterSequencePreActivityExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MScGrammarListener ) ((MScGrammarListener)listener).exitSequencePreActivityExpression(this);
		}
	}

	public final SequencePreActivityExpressionContext sequencePreActivityExpression() throws RecognitionException {
		SequencePreActivityExpressionContext _localctx = new SequencePreActivityExpressionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_sequencePreActivityExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
			match(T__11);
			setState(134);
			activity();
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
	public static class AndPreActivityExpressionContext extends ParserRuleContext {
		public List<ActivityContext> activity() {
			return getRuleContexts(ActivityContext.class);
		}
		public ActivityContext activity(int i) {
			return getRuleContext(ActivityContext.class,i);
		}
		public List<OspIdContext> ospId() {
			return getRuleContexts(OspIdContext.class);
		}
		public OspIdContext ospId(int i) {
			return getRuleContext(OspIdContext.class,i);
		}
		public AndPreActivityExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_andPreActivityExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MScGrammarListener ) ((MScGrammarListener)listener).enterAndPreActivityExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MScGrammarListener ) ((MScGrammarListener)listener).exitAndPreActivityExpression(this);
		}
	}

	public final AndPreActivityExpressionContext andPreActivityExpression() throws RecognitionException {
		AndPreActivityExpressionContext _localctx = new AndPreActivityExpressionContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_andPreActivityExpression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(136);
			match(T__11);
			setState(139);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__19:
				{
				setState(137);
				activity();
				}
				break;
			case T__20:
				{
				setState(138);
				ospId();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(141);
			match(T__12);
			setState(144);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__19:
				{
				setState(142);
				activity();
				}
				break;
			case T__20:
				{
				setState(143);
				ospId();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(153);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(146);
					match(T__12);
					setState(149);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case T__19:
						{
						setState(147);
						activity();
						}
						break;
					case T__20:
						{
						setState(148);
						ospId();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					} 
				}
				setState(155);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
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
	public static class OrPreActivityExpressionContext extends ParserRuleContext {
		public List<ActivityContext> activity() {
			return getRuleContexts(ActivityContext.class);
		}
		public ActivityContext activity(int i) {
			return getRuleContext(ActivityContext.class,i);
		}
		public List<AspIdContext> aspId() {
			return getRuleContexts(AspIdContext.class);
		}
		public AspIdContext aspId(int i) {
			return getRuleContext(AspIdContext.class,i);
		}
		public OrPreActivityExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orPreActivityExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MScGrammarListener ) ((MScGrammarListener)listener).enterOrPreActivityExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MScGrammarListener ) ((MScGrammarListener)listener).exitOrPreActivityExpression(this);
		}
	}

	public final OrPreActivityExpressionContext orPreActivityExpression() throws RecognitionException {
		OrPreActivityExpressionContext _localctx = new OrPreActivityExpressionContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_orPreActivityExpression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
			match(T__13);
			setState(159);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__19:
				{
				setState(157);
				activity();
				}
				break;
			case T__20:
				{
				setState(158);
				aspId();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(161);
			match(T__14);
			setState(164);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__19:
				{
				setState(162);
				activity();
				}
				break;
			case T__20:
				{
				setState(163);
				aspId();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(173);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(166);
					match(T__14);
					setState(169);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case T__19:
						{
						setState(167);
						activity();
						}
						break;
					case T__20:
						{
						setState(168);
						aspId();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					} 
				}
				setState(175);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
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
	public static class PostActivityExpressionContext extends ParserRuleContext {
		public SequencePostActivityExpressionContext sequencePostActivityExpression() {
			return getRuleContext(SequencePostActivityExpressionContext.class,0);
		}
		public AndPostActivityExpressionContext andPostActivityExpression() {
			return getRuleContext(AndPostActivityExpressionContext.class,0);
		}
		public OrPostActivityExpressionContext orPostActivityExpression() {
			return getRuleContext(OrPostActivityExpressionContext.class,0);
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
		enterRule(_localctx, 28, RULE_postActivityExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(179);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				{
				setState(176);
				sequencePostActivityExpression();
				}
				break;
			case 2:
				{
				setState(177);
				andPostActivityExpression();
				}
				break;
			case 3:
				{
				setState(178);
				orPostActivityExpression();
				}
				break;
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
	public static class SequencePostActivityExpressionContext extends ParserRuleContext {
		public ActivityContext activity() {
			return getRuleContext(ActivityContext.class,0);
		}
		public SequencePostActivityExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sequencePostActivityExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MScGrammarListener ) ((MScGrammarListener)listener).enterSequencePostActivityExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MScGrammarListener ) ((MScGrammarListener)listener).exitSequencePostActivityExpression(this);
		}
	}

	public final SequencePostActivityExpressionContext sequencePostActivityExpression() throws RecognitionException {
		SequencePostActivityExpressionContext _localctx = new SequencePostActivityExpressionContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_sequencePostActivityExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(181);
			activity();
			setState(182);
			match(T__15);
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
	public static class AndPostActivityExpressionContext extends ParserRuleContext {
		public List<ActivityContext> activity() {
			return getRuleContexts(ActivityContext.class);
		}
		public ActivityContext activity(int i) {
			return getRuleContext(ActivityContext.class,i);
		}
		public List<OspIdContext> ospId() {
			return getRuleContexts(OspIdContext.class);
		}
		public OspIdContext ospId(int i) {
			return getRuleContext(OspIdContext.class,i);
		}
		public AndPostActivityExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_andPostActivityExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MScGrammarListener ) ((MScGrammarListener)listener).enterAndPostActivityExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MScGrammarListener ) ((MScGrammarListener)listener).exitAndPostActivityExpression(this);
		}
	}

	public final AndPostActivityExpressionContext andPostActivityExpression() throws RecognitionException {
		AndPostActivityExpressionContext _localctx = new AndPostActivityExpressionContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_andPostActivityExpression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(186);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__19:
				{
				setState(184);
				activity();
				}
				break;
			case T__20:
				{
				setState(185);
				ospId();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(188);
			match(T__16);
			setState(197);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(191);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case T__19:
						{
						setState(189);
						activity();
						}
						break;
					case T__20:
						{
						setState(190);
						ospId();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(193);
					match(T__16);
					}
					} 
				}
				setState(199);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			}
			setState(202);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__19:
				{
				setState(200);
				activity();
				}
				break;
			case T__20:
				{
				setState(201);
				ospId();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(204);
			match(T__15);
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
	public static class OrPostActivityExpressionContext extends ParserRuleContext {
		public List<ActivityContext> activity() {
			return getRuleContexts(ActivityContext.class);
		}
		public ActivityContext activity(int i) {
			return getRuleContext(ActivityContext.class,i);
		}
		public List<AspIdContext> aspId() {
			return getRuleContexts(AspIdContext.class);
		}
		public AspIdContext aspId(int i) {
			return getRuleContext(AspIdContext.class,i);
		}
		public OrPostActivityExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orPostActivityExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MScGrammarListener ) ((MScGrammarListener)listener).enterOrPostActivityExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MScGrammarListener ) ((MScGrammarListener)listener).exitOrPostActivityExpression(this);
		}
	}

	public final OrPostActivityExpressionContext orPostActivityExpression() throws RecognitionException {
		OrPostActivityExpressionContext _localctx = new OrPostActivityExpressionContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_orPostActivityExpression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(206);
			match(T__17);
			setState(209);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__19:
				{
				setState(207);
				activity();
				}
				break;
			case T__20:
				{
				setState(208);
				aspId();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(211);
			match(T__18);
			setState(220);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(214);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case T__19:
						{
						setState(212);
						activity();
						}
						break;
					case T__20:
						{
						setState(213);
						aspId();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(216);
					match(T__18);
					}
					} 
				}
				setState(222);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			}
			setState(225);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__19:
				{
				setState(223);
				activity();
				}
				break;
			case T__20:
				{
				setState(224);
				aspId();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(227);
			match(T__15);
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
		enterRule(_localctx, 36, RULE_activity);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(229);
			match(T__19);
			setState(230);
			match(WORD);
			setState(235);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(231);
					match(SPACE);
					setState(232);
					match(WORD);
					}
					} 
				}
				setState(237);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			}
			setState(238);
			match(T__19);
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
	public static class AspIdContext extends ParserRuleContext {
		public List<TerminalNode> WORD() { return getTokens(MScGrammarParser.WORD); }
		public TerminalNode WORD(int i) {
			return getToken(MScGrammarParser.WORD, i);
		}
		public List<TerminalNode> SPACE() { return getTokens(MScGrammarParser.SPACE); }
		public TerminalNode SPACE(int i) {
			return getToken(MScGrammarParser.SPACE, i);
		}
		public AspIdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aspId; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MScGrammarListener ) ((MScGrammarListener)listener).enterAspId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MScGrammarListener ) ((MScGrammarListener)listener).exitAspId(this);
		}
	}

	public final AspIdContext aspId() throws RecognitionException {
		AspIdContext _localctx = new AspIdContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_aspId);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(240);
			match(T__20);
			setState(241);
			match(WORD);
			setState(246);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(242);
					match(SPACE);
					setState(243);
					match(WORD);
					}
					} 
				}
				setState(248);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			}
			setState(249);
			match(T__21);
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
	public static class OspIdContext extends ParserRuleContext {
		public List<TerminalNode> WORD() { return getTokens(MScGrammarParser.WORD); }
		public TerminalNode WORD(int i) {
			return getToken(MScGrammarParser.WORD, i);
		}
		public List<TerminalNode> SPACE() { return getTokens(MScGrammarParser.SPACE); }
		public TerminalNode SPACE(int i) {
			return getToken(MScGrammarParser.SPACE, i);
		}
		public OspIdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ospId; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MScGrammarListener ) ((MScGrammarListener)listener).enterOspId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MScGrammarListener ) ((MScGrammarListener)listener).exitOspId(this);
		}
	}

	public final OspIdContext ospId() throws RecognitionException {
		OspIdContext _localctx = new OspIdContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_ospId);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(251);
			match(T__20);
			setState(252);
			match(WORD);
			setState(257);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
			while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(253);
					match(SPACE);
					setState(254);
					match(WORD);
					}
					} 
				}
				setState(259);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
			}
			setState(260);
			match(T__21);
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
		"\u0004\u0001\u0019\u0107\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007"+
		"\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007"+
		"\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0003\u0000.\b\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0001\u0001\u0001\u0005\u00014\b\u0001\n\u0001\f\u00017\t\u0001\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0005\u0002=\b\u0002\n\u0002"+
		"\f\u0002@\t\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0004\u0001\u0004\u0005\u0004H\b\u0004\n\u0004\f\u0004K\t\u0004\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0003\u0005P\b\u0005\u0001\u0005\u0005"+
		"\u0005S\b\u0005\n\u0005\f\u0005V\t\u0005\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0003\u0006]\b\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0005\u0007h\b\u0007\n\u0007\f\u0007k\t\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0005\bv\b\b\n\b\f\by\t\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0003\t\u0081\b\t\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\f\u0001\f\u0001\f\u0003\f\u008c\b\f\u0001\f\u0001\f\u0001"+
		"\f\u0003\f\u0091\b\f\u0001\f\u0001\f\u0001\f\u0003\f\u0096\b\f\u0005\f"+
		"\u0098\b\f\n\f\f\f\u009b\t\f\u0001\r\u0001\r\u0001\r\u0003\r\u00a0\b\r"+
		"\u0001\r\u0001\r\u0001\r\u0003\r\u00a5\b\r\u0001\r\u0001\r\u0001\r\u0003"+
		"\r\u00aa\b\r\u0005\r\u00ac\b\r\n\r\f\r\u00af\t\r\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0003\u000e\u00b4\b\u000e\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u0010\u0001\u0010\u0003\u0010\u00bb\b\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0003\u0010\u00c0\b\u0010\u0001\u0010\u0001\u0010\u0005\u0010"+
		"\u00c4\b\u0010\n\u0010\f\u0010\u00c7\t\u0010\u0001\u0010\u0001\u0010\u0003"+
		"\u0010\u00cb\b\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0003\u0011\u00d2\b\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0003"+
		"\u0011\u00d7\b\u0011\u0001\u0011\u0001\u0011\u0005\u0011\u00db\b\u0011"+
		"\n\u0011\f\u0011\u00de\t\u0011\u0001\u0011\u0001\u0011\u0003\u0011\u00e2"+
		"\b\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0005\u0012\u00ea\b\u0012\n\u0012\f\u0012\u00ed\t\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0005\u0013"+
		"\u00f5\b\u0013\n\u0013\f\u0013\u00f8\t\u0013\u0001\u0013\u0001\u0013\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0005\u0014\u0100\b\u0014\n"+
		"\u0014\f\u0014\u0103\t\u0014\u0001\u0014\u0001\u0014\u0001\u0014\nIiw"+
		"\u0099\u00ad\u00c5\u00dc\u00eb\u00f6\u0101\u0000\u0015\u0000\u0002\u0004"+
		"\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \""+
		"$&(\u0000\u0000\u0112\u0000*\u0001\u0000\u0000\u0000\u00021\u0001\u0000"+
		"\u0000\u0000\u00048\u0001\u0000\u0000\u0000\u0006A\u0001\u0000\u0000\u0000"+
		"\bE\u0001\u0000\u0000\u0000\nO\u0001\u0000\u0000\u0000\fW\u0001\u0000"+
		"\u0000\u0000\u000e`\u0001\u0000\u0000\u0000\u0010n\u0001\u0000\u0000\u0000"+
		"\u0012|\u0001\u0000\u0000\u0000\u0014\u0082\u0001\u0000\u0000\u0000\u0016"+
		"\u0085\u0001\u0000\u0000\u0000\u0018\u0088\u0001\u0000\u0000\u0000\u001a"+
		"\u009c\u0001\u0000\u0000\u0000\u001c\u00b3\u0001\u0000\u0000\u0000\u001e"+
		"\u00b5\u0001\u0000\u0000\u0000 \u00ba\u0001\u0000\u0000\u0000\"\u00ce"+
		"\u0001\u0000\u0000\u0000$\u00e5\u0001\u0000\u0000\u0000&\u00f0\u0001\u0000"+
		"\u0000\u0000(\u00fb\u0001\u0000\u0000\u0000*+\u0003\u0002\u0001\u0000"+
		"+-\u0003\u0004\u0002\u0000,.\u0003\b\u0004\u0000-,\u0001\u0000\u0000\u0000"+
		"-.\u0001\u0000\u0000\u0000./\u0001\u0000\u0000\u0000/0\u0003\u0006\u0003"+
		"\u00000\u0001\u0001\u0000\u0000\u000015\u0005\u0001\u0000\u000024\u0005"+
		"\u0019\u0000\u000032\u0001\u0000\u0000\u000047\u0001\u0000\u0000\u0000"+
		"53\u0001\u0000\u0000\u000056\u0001\u0000\u0000\u00006\u0003\u0001\u0000"+
		"\u0000\u000075\u0001\u0000\u0000\u000089\u0005\u0002\u0000\u00009:\u0003"+
		"$\u0012\u0000:>\u0005\u0003\u0000\u0000;=\u0005\u0019\u0000\u0000<;\u0001"+
		"\u0000\u0000\u0000=@\u0001\u0000\u0000\u0000><\u0001\u0000\u0000\u0000"+
		">?\u0001\u0000\u0000\u0000?\u0005\u0001\u0000\u0000\u0000@>\u0001\u0000"+
		"\u0000\u0000AB\u0005\u0004\u0000\u0000BC\u0003$\u0012\u0000CD\u0005\u0005"+
		"\u0000\u0000D\u0007\u0001\u0000\u0000\u0000EI\u0003\n\u0005\u0000FH\u0003"+
		"\n\u0005\u0000GF\u0001\u0000\u0000\u0000HK\u0001\u0000\u0000\u0000IJ\u0001"+
		"\u0000\u0000\u0000IG\u0001\u0000\u0000\u0000J\t\u0001\u0000\u0000\u0000"+
		"KI\u0001\u0000\u0000\u0000LP\u0003\f\u0006\u0000MP\u0003\u000e\u0007\u0000"+
		"NP\u0003\u0010\b\u0000OL\u0001\u0000\u0000\u0000OM\u0001\u0000\u0000\u0000"+
		"ON\u0001\u0000\u0000\u0000PT\u0001\u0000\u0000\u0000QS\u0005\u0019\u0000"+
		"\u0000RQ\u0001\u0000\u0000\u0000SV\u0001\u0000\u0000\u0000TR\u0001\u0000"+
		"\u0000\u0000TU\u0001\u0000\u0000\u0000U\u000b\u0001\u0000\u0000\u0000"+
		"VT\u0001\u0000\u0000\u0000WX\u0005\u0004\u0000\u0000XY\u0003\u001c\u000e"+
		"\u0000Y\\\u0005\u0006\u0000\u0000Z]\u0003\u0012\t\u0000[]\u0003\u0014"+
		"\n\u0000\\Z\u0001\u0000\u0000\u0000\\[\u0001\u0000\u0000\u0000]^\u0001"+
		"\u0000\u0000\u0000^_\u0005\u0003\u0000\u0000_\r\u0001\u0000\u0000\u0000"+
		"`a\u0003&\u0013\u0000ab\u0005\u0007\u0000\u0000bc\u0003$\u0012\u0000c"+
		"d\u0005\b\u0000\u0000di\u0003$\u0012\u0000ef\u0005\b\u0000\u0000fh\u0003"+
		"$\u0012\u0000ge\u0001\u0000\u0000\u0000hk\u0001\u0000\u0000\u0000ij\u0001"+
		"\u0000\u0000\u0000ig\u0001\u0000\u0000\u0000jl\u0001\u0000\u0000\u0000"+
		"ki\u0001\u0000\u0000\u0000lm\u0005\u0003\u0000\u0000m\u000f\u0001\u0000"+
		"\u0000\u0000no\u0003(\u0014\u0000op\u0005\u0007\u0000\u0000pq\u0003$\u0012"+
		"\u0000qr\u0005\t\u0000\u0000rw\u0003$\u0012\u0000st\u0005\t\u0000\u0000"+
		"tv\u0003$\u0012\u0000us\u0001\u0000\u0000\u0000vy\u0001\u0000\u0000\u0000"+
		"wx\u0001\u0000\u0000\u0000wu\u0001\u0000\u0000\u0000xz\u0001\u0000\u0000"+
		"\u0000yw\u0001\u0000\u0000\u0000z{\u0005\u0003\u0000\u0000{\u0011\u0001"+
		"\u0000\u0000\u0000|\u0080\u0005\n\u0000\u0000}\u0081\u0003\u0016\u000b"+
		"\u0000~\u0081\u0003\u0018\f\u0000\u007f\u0081\u0003\u001a\r\u0000\u0080"+
		"}\u0001\u0000\u0000\u0000\u0080~\u0001\u0000\u0000\u0000\u0080\u007f\u0001"+
		"\u0000\u0000\u0000\u0081\u0013\u0001\u0000\u0000\u0000\u0082\u0083\u0005"+
		"\u000b\u0000\u0000\u0083\u0084\u0003\u0016\u000b\u0000\u0084\u0015\u0001"+
		"\u0000\u0000\u0000\u0085\u0086\u0005\f\u0000\u0000\u0086\u0087\u0003$"+
		"\u0012\u0000\u0087\u0017\u0001\u0000\u0000\u0000\u0088\u008b\u0005\f\u0000"+
		"\u0000\u0089\u008c\u0003$\u0012\u0000\u008a\u008c\u0003(\u0014\u0000\u008b"+
		"\u0089\u0001\u0000\u0000\u0000\u008b\u008a\u0001\u0000\u0000\u0000\u008c"+
		"\u008d\u0001\u0000\u0000\u0000\u008d\u0090\u0005\r\u0000\u0000\u008e\u0091"+
		"\u0003$\u0012\u0000\u008f\u0091\u0003(\u0014\u0000\u0090\u008e\u0001\u0000"+
		"\u0000\u0000\u0090\u008f\u0001\u0000\u0000\u0000\u0091\u0099\u0001\u0000"+
		"\u0000\u0000\u0092\u0095\u0005\r\u0000\u0000\u0093\u0096\u0003$\u0012"+
		"\u0000\u0094\u0096\u0003(\u0014\u0000\u0095\u0093\u0001\u0000\u0000\u0000"+
		"\u0095\u0094\u0001\u0000\u0000\u0000\u0096\u0098\u0001\u0000\u0000\u0000"+
		"\u0097\u0092\u0001\u0000\u0000\u0000\u0098\u009b\u0001\u0000\u0000\u0000"+
		"\u0099\u009a\u0001\u0000\u0000\u0000\u0099\u0097\u0001\u0000\u0000\u0000"+
		"\u009a\u0019\u0001\u0000\u0000\u0000\u009b\u0099\u0001\u0000\u0000\u0000"+
		"\u009c\u009f\u0005\u000e\u0000\u0000\u009d\u00a0\u0003$\u0012\u0000\u009e"+
		"\u00a0\u0003&\u0013\u0000\u009f\u009d\u0001\u0000\u0000\u0000\u009f\u009e"+
		"\u0001\u0000\u0000\u0000\u00a0\u00a1\u0001\u0000\u0000\u0000\u00a1\u00a4"+
		"\u0005\u000f\u0000\u0000\u00a2\u00a5\u0003$\u0012\u0000\u00a3\u00a5\u0003"+
		"&\u0013\u0000\u00a4\u00a2\u0001\u0000\u0000\u0000\u00a4\u00a3\u0001\u0000"+
		"\u0000\u0000\u00a5\u00ad\u0001\u0000\u0000\u0000\u00a6\u00a9\u0005\u000f"+
		"\u0000\u0000\u00a7\u00aa\u0003$\u0012\u0000\u00a8\u00aa\u0003&\u0013\u0000"+
		"\u00a9\u00a7\u0001\u0000\u0000\u0000\u00a9\u00a8\u0001\u0000\u0000\u0000"+
		"\u00aa\u00ac\u0001\u0000\u0000\u0000\u00ab\u00a6\u0001\u0000\u0000\u0000"+
		"\u00ac\u00af\u0001\u0000\u0000\u0000\u00ad\u00ae\u0001\u0000\u0000\u0000"+
		"\u00ad\u00ab\u0001\u0000\u0000\u0000\u00ae\u001b\u0001\u0000\u0000\u0000"+
		"\u00af\u00ad\u0001\u0000\u0000\u0000\u00b0\u00b4\u0003\u001e\u000f\u0000"+
		"\u00b1\u00b4\u0003 \u0010\u0000\u00b2\u00b4\u0003\"\u0011\u0000\u00b3"+
		"\u00b0\u0001\u0000\u0000\u0000\u00b3\u00b1\u0001\u0000\u0000\u0000\u00b3"+
		"\u00b2\u0001\u0000\u0000\u0000\u00b4\u001d\u0001\u0000\u0000\u0000\u00b5"+
		"\u00b6\u0003$\u0012\u0000\u00b6\u00b7\u0005\u0010\u0000\u0000\u00b7\u001f"+
		"\u0001\u0000\u0000\u0000\u00b8\u00bb\u0003$\u0012\u0000\u00b9\u00bb\u0003"+
		"(\u0014\u0000\u00ba\u00b8\u0001\u0000\u0000\u0000\u00ba\u00b9\u0001\u0000"+
		"\u0000\u0000\u00bb\u00bc\u0001\u0000\u0000\u0000\u00bc\u00c5\u0005\u0011"+
		"\u0000\u0000\u00bd\u00c0\u0003$\u0012\u0000\u00be\u00c0\u0003(\u0014\u0000"+
		"\u00bf\u00bd\u0001\u0000\u0000\u0000\u00bf\u00be\u0001\u0000\u0000\u0000"+
		"\u00c0\u00c1\u0001\u0000\u0000\u0000\u00c1\u00c2\u0005\u0011\u0000\u0000"+
		"\u00c2\u00c4\u0001\u0000\u0000\u0000\u00c3\u00bf\u0001\u0000\u0000\u0000"+
		"\u00c4\u00c7\u0001\u0000\u0000\u0000\u00c5\u00c6\u0001\u0000\u0000\u0000"+
		"\u00c5\u00c3\u0001\u0000\u0000\u0000\u00c6\u00ca\u0001\u0000\u0000\u0000"+
		"\u00c7\u00c5\u0001\u0000\u0000\u0000\u00c8\u00cb\u0003$\u0012\u0000\u00c9"+
		"\u00cb\u0003(\u0014\u0000\u00ca\u00c8\u0001\u0000\u0000\u0000\u00ca\u00c9"+
		"\u0001\u0000\u0000\u0000\u00cb\u00cc\u0001\u0000\u0000\u0000\u00cc\u00cd"+
		"\u0005\u0010\u0000\u0000\u00cd!\u0001\u0000\u0000\u0000\u00ce\u00d1\u0005"+
		"\u0012\u0000\u0000\u00cf\u00d2\u0003$\u0012\u0000\u00d0\u00d2\u0003&\u0013"+
		"\u0000\u00d1\u00cf\u0001\u0000\u0000\u0000\u00d1\u00d0\u0001\u0000\u0000"+
		"\u0000\u00d2\u00d3\u0001\u0000\u0000\u0000\u00d3\u00dc\u0005\u0013\u0000"+
		"\u0000\u00d4\u00d7\u0003$\u0012\u0000\u00d5\u00d7\u0003&\u0013\u0000\u00d6"+
		"\u00d4\u0001\u0000\u0000\u0000\u00d6\u00d5\u0001\u0000\u0000\u0000\u00d7"+
		"\u00d8\u0001\u0000\u0000\u0000\u00d8\u00d9\u0005\u0013\u0000\u0000\u00d9"+
		"\u00db\u0001\u0000\u0000\u0000\u00da\u00d6\u0001\u0000\u0000\u0000\u00db"+
		"\u00de\u0001\u0000\u0000\u0000\u00dc\u00dd\u0001\u0000\u0000\u0000\u00dc"+
		"\u00da\u0001\u0000\u0000\u0000\u00dd\u00e1\u0001\u0000\u0000\u0000\u00de"+
		"\u00dc\u0001\u0000\u0000\u0000\u00df\u00e2\u0003$\u0012\u0000\u00e0\u00e2"+
		"\u0003&\u0013\u0000\u00e1\u00df\u0001\u0000\u0000\u0000\u00e1\u00e0\u0001"+
		"\u0000\u0000\u0000\u00e2\u00e3\u0001\u0000\u0000\u0000\u00e3\u00e4\u0005"+
		"\u0010\u0000\u0000\u00e4#\u0001\u0000\u0000\u0000\u00e5\u00e6\u0005\u0014"+
		"\u0000\u0000\u00e6\u00eb\u0005\u0017\u0000\u0000\u00e7\u00e8\u0005\u0018"+
		"\u0000\u0000\u00e8\u00ea\u0005\u0017\u0000\u0000\u00e9\u00e7\u0001\u0000"+
		"\u0000\u0000\u00ea\u00ed\u0001\u0000\u0000\u0000\u00eb\u00ec\u0001\u0000"+
		"\u0000\u0000\u00eb\u00e9\u0001\u0000\u0000\u0000\u00ec\u00ee\u0001\u0000"+
		"\u0000\u0000\u00ed\u00eb\u0001\u0000\u0000\u0000\u00ee\u00ef\u0005\u0014"+
		"\u0000\u0000\u00ef%\u0001\u0000\u0000\u0000\u00f0\u00f1\u0005\u0015\u0000"+
		"\u0000\u00f1\u00f6\u0005\u0017\u0000\u0000\u00f2\u00f3\u0005\u0018\u0000"+
		"\u0000\u00f3\u00f5\u0005\u0017\u0000\u0000\u00f4\u00f2\u0001\u0000\u0000"+
		"\u0000\u00f5\u00f8\u0001\u0000\u0000\u0000\u00f6\u00f7\u0001\u0000\u0000"+
		"\u0000\u00f6\u00f4\u0001\u0000\u0000\u0000\u00f7\u00f9\u0001\u0000\u0000"+
		"\u0000\u00f8\u00f6\u0001\u0000\u0000\u0000\u00f9\u00fa\u0005\u0016\u0000"+
		"\u0000\u00fa\'\u0001\u0000\u0000\u0000\u00fb\u00fc\u0005\u0015\u0000\u0000"+
		"\u00fc\u0101\u0005\u0017\u0000\u0000\u00fd\u00fe\u0005\u0018\u0000\u0000"+
		"\u00fe\u0100\u0005\u0017\u0000\u0000\u00ff\u00fd\u0001\u0000\u0000\u0000"+
		"\u0100\u0103\u0001\u0000\u0000\u0000\u0101\u0102\u0001\u0000\u0000\u0000"+
		"\u0101\u00ff\u0001\u0000\u0000\u0000\u0102\u0104\u0001\u0000\u0000\u0000"+
		"\u0103\u0101\u0001\u0000\u0000\u0000\u0104\u0105\u0005\u0016\u0000\u0000"+
		"\u0105)\u0001\u0000\u0000\u0000\u001e-5>IOT\\iw\u0080\u008b\u0090\u0095"+
		"\u0099\u009f\u00a4\u00a9\u00ad\u00b3\u00ba\u00bf\u00c5\u00ca\u00d1\u00d6"+
		"\u00dc\u00e1\u00eb\u00f6\u0101";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}