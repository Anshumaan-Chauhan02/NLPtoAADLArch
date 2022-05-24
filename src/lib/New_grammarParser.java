// Generated from .\New_grammar.g4 by ANTLR 4.9.2

    package lib;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class New_grammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		With=1, Comma=2, And=3, Form=4, End=5, It=6, To=7, Them=8, From=9, Struct_verb=10, 
		Connect_verb=11, Func_verb=12, Energy=13, Adj_value=14, Struct_noun=15, 
		State=16, Signal=17, WS=18;
	public static final int
		RULE_nlparch = 0, RULE_sentences = 1, RULE_sentence = 2, RULE_structural_stmts = 3, 
		RULE_structural_stmt = 4, RULE_connection_stmt = 5, RULE_functional_stmts = 6, 
		RULE_functional_stmt = 7, RULE_struct_multinoun = 8, RULE_multi_flow = 9, 
		RULE_flow = 10, RULE_states = 11;
	private static String[] makeRuleNames() {
		return new String[] {
			"nlparch", "sentences", "sentence", "structural_stmts", "structural_stmt", 
			"connection_stmt", "functional_stmts", "functional_stmt", "struct_multinoun", 
			"multi_flow", "flow", "states"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'with'", "','", "'and'", "'form'", "'.'", "'it'", "'to'", "'them'", 
			"'from'", "'consists'", "'connected'", null, "'energy'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "With", "Comma", "And", "Form", "End", "It", "To", "Them", "From", 
			"Struct_verb", "Connect_verb", "Func_verb", "Energy", "Adj_value", "Struct_noun", 
			"State", "Signal", "WS"
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
	public String getGrammarFileName() { return "New_grammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public New_grammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class NlparchContext extends ParserRuleContext {
		public List<SentencesContext> sentences() {
			return getRuleContexts(SentencesContext.class);
		}
		public SentencesContext sentences(int i) {
			return getRuleContext(SentencesContext.class,i);
		}
		public NlparchContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nlparch; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof New_grammarListener ) ((New_grammarListener)listener).enterNlparch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof New_grammarListener ) ((New_grammarListener)listener).exitNlparch(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof New_grammarVisitor ) return ((New_grammarVisitor<? extends T>)visitor).visitNlparch(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NlparchContext nlparch() throws RecognitionException {
		NlparchContext _localctx = new NlparchContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_nlparch);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(25); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(24);
				sentences();
				}
				}
				setState(27); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==Struct_noun );
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

	public static class SentencesContext extends ParserRuleContext {
		public List<SentenceContext> sentence() {
			return getRuleContexts(SentenceContext.class);
		}
		public SentenceContext sentence(int i) {
			return getRuleContext(SentenceContext.class,i);
		}
		public SentencesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sentences; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof New_grammarListener ) ((New_grammarListener)listener).enterSentences(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof New_grammarListener ) ((New_grammarListener)listener).exitSentences(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof New_grammarVisitor ) return ((New_grammarVisitor<? extends T>)visitor).visitSentences(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SentencesContext sentences() throws RecognitionException {
		SentencesContext _localctx = new SentencesContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_sentences);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(30); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(29);
					sentence();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(32); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class SentenceContext extends ParserRuleContext {
		public Structural_stmtsContext structural_stmts() {
			return getRuleContext(Structural_stmtsContext.class,0);
		}
		public Functional_stmtsContext functional_stmts() {
			return getRuleContext(Functional_stmtsContext.class,0);
		}
		public SentenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sentence; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof New_grammarListener ) ((New_grammarListener)listener).enterSentence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof New_grammarListener ) ((New_grammarListener)listener).exitSentence(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof New_grammarVisitor ) return ((New_grammarVisitor<? extends T>)visitor).visitSentence(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SentenceContext sentence() throws RecognitionException {
		SentenceContext _localctx = new SentenceContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_sentence);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(36);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				{
				setState(34);
				structural_stmts();
				}
				}
				break;
			case 2:
				{
				{
				setState(35);
				functional_stmts();
				}
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

	public static class Structural_stmtsContext extends ParserRuleContext {
		public TerminalNode End() { return getToken(New_grammarParser.End, 0); }
		public Structural_stmtContext structural_stmt() {
			return getRuleContext(Structural_stmtContext.class,0);
		}
		public Connection_stmtContext connection_stmt() {
			return getRuleContext(Connection_stmtContext.class,0);
		}
		public Structural_stmtsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_structural_stmts; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof New_grammarListener ) ((New_grammarListener)listener).enterStructural_stmts(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof New_grammarListener ) ((New_grammarListener)listener).exitStructural_stmts(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof New_grammarVisitor ) return ((New_grammarVisitor<? extends T>)visitor).visitStructural_stmts(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Structural_stmtsContext structural_stmts() throws RecognitionException {
		Structural_stmtsContext _localctx = new Structural_stmtsContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_structural_stmts);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(40);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				{
				setState(38);
				structural_stmt();
				}
				}
				break;
			case 2:
				{
				{
				setState(39);
				connection_stmt();
				}
				}
				break;
			}
			setState(42);
			match(End);
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

	public static class Structural_stmtContext extends ParserRuleContext {
		public TerminalNode Struct_noun() { return getToken(New_grammarParser.Struct_noun, 0); }
		public TerminalNode Struct_verb() { return getToken(New_grammarParser.Struct_verb, 0); }
		public Struct_multinounContext struct_multinoun() {
			return getRuleContext(Struct_multinounContext.class,0);
		}
		public Structural_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_structural_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof New_grammarListener ) ((New_grammarListener)listener).enterStructural_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof New_grammarListener ) ((New_grammarListener)listener).exitStructural_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof New_grammarVisitor ) return ((New_grammarVisitor<? extends T>)visitor).visitStructural_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Structural_stmtContext structural_stmt() throws RecognitionException {
		Structural_stmtContext _localctx = new Structural_stmtContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_structural_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44);
			match(Struct_noun);
			setState(45);
			match(Struct_verb);
			setState(46);
			struct_multinoun();
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

	public static class Connection_stmtContext extends ParserRuleContext {
		public TerminalNode Struct_noun() { return getToken(New_grammarParser.Struct_noun, 0); }
		public TerminalNode Connect_verb() { return getToken(New_grammarParser.Connect_verb, 0); }
		public TerminalNode To() { return getToken(New_grammarParser.To, 0); }
		public Struct_multinounContext struct_multinoun() {
			return getRuleContext(Struct_multinounContext.class,0);
		}
		public Connection_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_connection_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof New_grammarListener ) ((New_grammarListener)listener).enterConnection_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof New_grammarListener ) ((New_grammarListener)listener).exitConnection_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof New_grammarVisitor ) return ((New_grammarVisitor<? extends T>)visitor).visitConnection_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Connection_stmtContext connection_stmt() throws RecognitionException {
		Connection_stmtContext _localctx = new Connection_stmtContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_connection_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
			match(Struct_noun);
			setState(49);
			match(Connect_verb);
			setState(50);
			match(To);
			setState(51);
			struct_multinoun();
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

	public static class Functional_stmtsContext extends ParserRuleContext {
		public TerminalNode End() { return getToken(New_grammarParser.End, 0); }
		public Functional_stmtContext functional_stmt() {
			return getRuleContext(Functional_stmtContext.class,0);
		}
		public Functional_stmtsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functional_stmts; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof New_grammarListener ) ((New_grammarListener)listener).enterFunctional_stmts(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof New_grammarListener ) ((New_grammarListener)listener).exitFunctional_stmts(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof New_grammarVisitor ) return ((New_grammarVisitor<? extends T>)visitor).visitFunctional_stmts(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Functional_stmtsContext functional_stmts() throws RecognitionException {
		Functional_stmtsContext _localctx = new Functional_stmtsContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_functional_stmts);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			{
			setState(53);
			functional_stmt();
			}
			setState(54);
			match(End);
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

	public static class Functional_stmtContext extends ParserRuleContext {
		public List<TerminalNode> Struct_noun() { return getTokens(New_grammarParser.Struct_noun); }
		public TerminalNode Struct_noun(int i) {
			return getToken(New_grammarParser.Struct_noun, i);
		}
		public TerminalNode Func_verb() { return getToken(New_grammarParser.Func_verb, 0); }
		public List<Multi_flowContext> multi_flow() {
			return getRuleContexts(Multi_flowContext.class);
		}
		public Multi_flowContext multi_flow(int i) {
			return getRuleContext(Multi_flowContext.class,i);
		}
		public TerminalNode From() { return getToken(New_grammarParser.From, 0); }
		public TerminalNode To() { return getToken(New_grammarParser.To, 0); }
		public Struct_multinounContext struct_multinoun() {
			return getRuleContext(Struct_multinounContext.class,0);
		}
		public List<FlowContext> flow() {
			return getRuleContexts(FlowContext.class);
		}
		public FlowContext flow(int i) {
			return getRuleContext(FlowContext.class,i);
		}
		public TerminalNode With() { return getToken(New_grammarParser.With, 0); }
		public TerminalNode Form() { return getToken(New_grammarParser.Form, 0); }
		public Functional_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functional_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof New_grammarListener ) ((New_grammarListener)listener).enterFunctional_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof New_grammarListener ) ((New_grammarListener)listener).exitFunctional_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof New_grammarVisitor ) return ((New_grammarVisitor<? extends T>)visitor).visitFunctional_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Functional_stmtContext functional_stmt() throws RecognitionException {
		Functional_stmtContext _localctx = new Functional_stmtContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_functional_stmt);
		int _la;
		try {
			setState(87);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				{
				setState(56);
				match(Struct_noun);
				setState(57);
				match(Func_verb);
				setState(58);
				multi_flow();
				}
				setState(68);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
				case 1:
					{
					{
					setState(60);
					match(From);
					setState(61);
					match(Struct_noun);
					setState(62);
					match(To);
					setState(63);
					match(Struct_noun);
					}
					}
					break;
				case 2:
					{
					{
					setState(64);
					_la = _input.LA(1);
					if ( !(_la==To || _la==From) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(65);
					struct_multinoun();
					}
					}
					break;
				case 3:
					{
					{
					setState(66);
					match(To);
					setState(67);
					multi_flow();
					}
					}
					break;
				}
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(70);
				match(Struct_noun);
				setState(71);
				match(Func_verb);
				}
				setState(85);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
				case 1:
					{
					{
					setState(73);
					flow();
					setState(74);
					match(With);
					setState(75);
					flow();
					setState(76);
					match(To);
					setState(77);
					match(Form);
					setState(78);
					flow();
					}
					}
					break;
				case 2:
					{
					{
					setState(80);
					flow();
					setState(81);
					match(To);
					setState(82);
					match(Form);
					setState(83);
					multi_flow();
					}
					}
					break;
				}
				}
				break;
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

	public static class Struct_multinounContext extends ParserRuleContext {
		public List<TerminalNode> Struct_noun() { return getTokens(New_grammarParser.Struct_noun); }
		public TerminalNode Struct_noun(int i) {
			return getToken(New_grammarParser.Struct_noun, i);
		}
		public TerminalNode Comma() { return getToken(New_grammarParser.Comma, 0); }
		public Struct_multinounContext struct_multinoun() {
			return getRuleContext(Struct_multinounContext.class,0);
		}
		public TerminalNode And() { return getToken(New_grammarParser.And, 0); }
		public Struct_multinounContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_struct_multinoun; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof New_grammarListener ) ((New_grammarListener)listener).enterStruct_multinoun(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof New_grammarListener ) ((New_grammarListener)listener).exitStruct_multinoun(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof New_grammarVisitor ) return ((New_grammarVisitor<? extends T>)visitor).visitStruct_multinoun(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Struct_multinounContext struct_multinoun() throws RecognitionException {
		Struct_multinounContext _localctx = new Struct_multinounContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_struct_multinoun);
		try {
			setState(96);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(89);
				match(Struct_noun);
				setState(90);
				match(Comma);
				setState(91);
				struct_multinoun();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(92);
				match(Struct_noun);
				setState(93);
				match(And);
				setState(94);
				match(Struct_noun);
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(95);
				match(Struct_noun);
				}
				}
				break;
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

	public static class Multi_flowContext extends ParserRuleContext {
		public List<FlowContext> flow() {
			return getRuleContexts(FlowContext.class);
		}
		public FlowContext flow(int i) {
			return getRuleContext(FlowContext.class,i);
		}
		public TerminalNode And() { return getToken(New_grammarParser.And, 0); }
		public TerminalNode Comma() { return getToken(New_grammarParser.Comma, 0); }
		public Multi_flowContext multi_flow() {
			return getRuleContext(Multi_flowContext.class,0);
		}
		public Multi_flowContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multi_flow; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof New_grammarListener ) ((New_grammarListener)listener).enterMulti_flow(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof New_grammarListener ) ((New_grammarListener)listener).exitMulti_flow(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof New_grammarVisitor ) return ((New_grammarVisitor<? extends T>)visitor).visitMulti_flow(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Multi_flowContext multi_flow() throws RecognitionException {
		Multi_flowContext _localctx = new Multi_flowContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_multi_flow);
		try {
			setState(107);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(98);
				flow();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(99);
				flow();
				setState(100);
				match(And);
				setState(101);
				flow();
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(103);
				flow();
				setState(104);
				match(Comma);
				setState(105);
				multi_flow();
				}
				}
				break;
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

	public static class FlowContext extends ParserRuleContext {
		public TerminalNode Adj_value() { return getToken(New_grammarParser.Adj_value, 0); }
		public TerminalNode Energy() { return getToken(New_grammarParser.Energy, 0); }
		public StatesContext states() {
			return getRuleContext(StatesContext.class,0);
		}
		public TerminalNode Signal() { return getToken(New_grammarParser.Signal, 0); }
		public FlowContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_flow; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof New_grammarListener ) ((New_grammarListener)listener).enterFlow(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof New_grammarListener ) ((New_grammarListener)listener).exitFlow(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof New_grammarVisitor ) return ((New_grammarVisitor<? extends T>)visitor).visitFlow(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FlowContext flow() throws RecognitionException {
		FlowContext _localctx = new FlowContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_flow);
		try {
			setState(113);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(109);
				match(Adj_value);
				setState(110);
				match(Energy);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(111);
				states();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(112);
				match(Signal);
				}
				break;
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

	public static class StatesContext extends ParserRuleContext {
		public TerminalNode State() { return getToken(New_grammarParser.State, 0); }
		public List<TerminalNode> Adj_value() { return getTokens(New_grammarParser.Adj_value); }
		public TerminalNode Adj_value(int i) {
			return getToken(New_grammarParser.Adj_value, i);
		}
		public StatesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_states; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof New_grammarListener ) ((New_grammarListener)listener).enterStates(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof New_grammarListener ) ((New_grammarListener)listener).exitStates(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof New_grammarVisitor ) return ((New_grammarVisitor<? extends T>)visitor).visitStates(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatesContext states() throws RecognitionException {
		StatesContext _localctx = new StatesContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_states);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Adj_value) {
				{
				{
				setState(115);
				match(Adj_value);
				}
				}
				setState(120);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(121);
			match(State);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\24~\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\4\r\t\r\3\2\6\2\34\n\2\r\2\16\2\35\3\3\6\3!\n\3\r\3\16\3\"\3\4"+
		"\3\4\5\4\'\n\4\3\5\3\5\5\5+\n\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3"+
		"\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t"+
		"G\n\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t"+
		"X\n\t\5\tZ\n\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\nc\n\n\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\5\13n\n\13\3\f\3\f\3\f\3\f\5\ft\n\f\3\r\7\r"+
		"w\n\r\f\r\16\rz\13\r\3\r\3\r\3\r\2\2\16\2\4\6\b\n\f\16\20\22\24\26\30"+
		"\2\3\4\2\t\t\13\13\2\u0081\2\33\3\2\2\2\4 \3\2\2\2\6&\3\2\2\2\b*\3\2\2"+
		"\2\n.\3\2\2\2\f\62\3\2\2\2\16\67\3\2\2\2\20Y\3\2\2\2\22b\3\2\2\2\24m\3"+
		"\2\2\2\26s\3\2\2\2\30x\3\2\2\2\32\34\5\4\3\2\33\32\3\2\2\2\34\35\3\2\2"+
		"\2\35\33\3\2\2\2\35\36\3\2\2\2\36\3\3\2\2\2\37!\5\6\4\2 \37\3\2\2\2!\""+
		"\3\2\2\2\" \3\2\2\2\"#\3\2\2\2#\5\3\2\2\2$\'\5\b\5\2%\'\5\16\b\2&$\3\2"+
		"\2\2&%\3\2\2\2\'\7\3\2\2\2(+\5\n\6\2)+\5\f\7\2*(\3\2\2\2*)\3\2\2\2+,\3"+
		"\2\2\2,-\7\7\2\2-\t\3\2\2\2./\7\21\2\2/\60\7\f\2\2\60\61\5\22\n\2\61\13"+
		"\3\2\2\2\62\63\7\21\2\2\63\64\7\r\2\2\64\65\7\t\2\2\65\66\5\22\n\2\66"+
		"\r\3\2\2\2\678\5\20\t\289\7\7\2\29\17\3\2\2\2:;\7\21\2\2;<\7\16\2\2<="+
		"\5\24\13\2=F\3\2\2\2>?\7\13\2\2?@\7\21\2\2@A\7\t\2\2AG\7\21\2\2BC\t\2"+
		"\2\2CG\5\22\n\2DE\7\t\2\2EG\5\24\13\2F>\3\2\2\2FB\3\2\2\2FD\3\2\2\2FG"+
		"\3\2\2\2GZ\3\2\2\2HI\7\21\2\2IJ\7\16\2\2JW\3\2\2\2KL\5\26\f\2LM\7\3\2"+
		"\2MN\5\26\f\2NO\7\t\2\2OP\7\6\2\2PQ\5\26\f\2QX\3\2\2\2RS\5\26\f\2ST\7"+
		"\t\2\2TU\7\6\2\2UV\5\24\13\2VX\3\2\2\2WK\3\2\2\2WR\3\2\2\2XZ\3\2\2\2Y"+
		":\3\2\2\2YH\3\2\2\2Z\21\3\2\2\2[\\\7\21\2\2\\]\7\4\2\2]c\5\22\n\2^_\7"+
		"\21\2\2_`\7\5\2\2`c\7\21\2\2ac\7\21\2\2b[\3\2\2\2b^\3\2\2\2ba\3\2\2\2"+
		"c\23\3\2\2\2dn\5\26\f\2ef\5\26\f\2fg\7\5\2\2gh\5\26\f\2hn\3\2\2\2ij\5"+
		"\26\f\2jk\7\4\2\2kl\5\24\13\2ln\3\2\2\2md\3\2\2\2me\3\2\2\2mi\3\2\2\2"+
		"n\25\3\2\2\2op\7\20\2\2pt\7\17\2\2qt\5\30\r\2rt\7\23\2\2so\3\2\2\2sq\3"+
		"\2\2\2sr\3\2\2\2t\27\3\2\2\2uw\7\20\2\2vu\3\2\2\2wz\3\2\2\2xv\3\2\2\2"+
		"xy\3\2\2\2y{\3\2\2\2zx\3\2\2\2{|\7\22\2\2|\31\3\2\2\2\r\35\"&*FWYbmsx";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}