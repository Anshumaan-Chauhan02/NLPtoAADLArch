// Generated from .\New_grammar.g4 by ANTLR 4.9.2

    package lib;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class New_grammarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		With=1, Comma=2, And=3, Form=4, End=5, It=6, To=7, Them=8, From=9, Struct_verb=10, 
		Connect_verb=11, Func_verb=12, Energy=13, Adj_value=14, Struct_noun=15, 
		State=16, Signal=17, WS=18;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"With", "Comma", "And", "Form", "End", "It", "To", "Them", "From", "Struct_verb", 
			"Connect_verb", "Func_verb", "Energy", "Adj_value", "Struct_noun", "State", 
			"Signal", "WS"
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


	public New_grammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "New_grammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\24\u0103\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3"+
		"\5\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n"+
		"\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u00d9\n\r\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\17\3\17\6\17\u00e4\n\17\r\17\16\17\u00e5\3\20\6\20\u00e9"+
		"\n\20\r\20\16\20\u00ea\3\20\7\20\u00ee\n\20\f\20\16\20\u00f1\13\20\3\21"+
		"\6\21\u00f4\n\21\r\21\16\21\u00f5\3\22\6\22\u00f9\n\22\r\22\16\22\u00fa"+
		"\3\23\6\23\u00fe\n\23\r\23\16\23\u00ff\3\23\3\23\2\2\24\3\3\5\4\7\5\t"+
		"\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23"+
		"%\24\3\2\7\3\2C\\\3\2c|\4\2C\\aa\6\2\62;C\\aac|\5\2\13\f\16\17\"\"\2\u0117"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2"+
		"\2\2\2%\3\2\2\2\3\'\3\2\2\2\5,\3\2\2\2\7.\3\2\2\2\t\62\3\2\2\2\13\67\3"+
		"\2\2\2\r9\3\2\2\2\17<\3\2\2\2\21?\3\2\2\2\23D\3\2\2\2\25I\3\2\2\2\27R"+
		"\3\2\2\2\31\u00d8\3\2\2\2\33\u00da\3\2\2\2\35\u00e1\3\2\2\2\37\u00e8\3"+
		"\2\2\2!\u00f3\3\2\2\2#\u00f8\3\2\2\2%\u00fd\3\2\2\2\'(\7y\2\2()\7k\2\2"+
		")*\7v\2\2*+\7j\2\2+\4\3\2\2\2,-\7.\2\2-\6\3\2\2\2./\7c\2\2/\60\7p\2\2"+
		"\60\61\7f\2\2\61\b\3\2\2\2\62\63\7h\2\2\63\64\7q\2\2\64\65\7t\2\2\65\66"+
		"\7o\2\2\66\n\3\2\2\2\678\7\60\2\28\f\3\2\2\29:\7k\2\2:;\7v\2\2;\16\3\2"+
		"\2\2<=\7v\2\2=>\7q\2\2>\20\3\2\2\2?@\7v\2\2@A\7j\2\2AB\7g\2\2BC\7o\2\2"+
		"C\22\3\2\2\2DE\7h\2\2EF\7t\2\2FG\7q\2\2GH\7o\2\2H\24\3\2\2\2IJ\7e\2\2"+
		"JK\7q\2\2KL\7p\2\2LM\7u\2\2MN\7k\2\2NO\7u\2\2OP\7v\2\2PQ\7u\2\2Q\26\3"+
		"\2\2\2RS\7e\2\2ST\7q\2\2TU\7p\2\2UV\7p\2\2VW\7g\2\2WX\7e\2\2XY\7v\2\2"+
		"YZ\7g\2\2Z[\7f\2\2[\30\3\2\2\2\\]\7k\2\2]^\7o\2\2^_\7r\2\2_`\7q\2\2`a"+
		"\7t\2\2ab\7v\2\2b\u00d9\7u\2\2cd\7g\2\2de\7z\2\2ef\7r\2\2fg\7q\2\2gh\7"+
		"t\2\2hi\7v\2\2i\u00d9\7u\2\2jk\7v\2\2kl\7t\2\2lm\7c\2\2mn\7p\2\2no\7u"+
		"\2\2op\7h\2\2pq\7g\2\2qr\7t\2\2r\u00d9\7u\2\2st\7i\2\2tu\7w\2\2uv\7k\2"+
		"\2vw\7f\2\2wx\7g\2\2x\u00d9\7u\2\2yz\7u\2\2z{\7w\2\2{|\7r\2\2|}\7r\2\2"+
		"}~\7n\2\2~\177\7k\2\2\177\u0080\7g\2\2\u0080\u00d9\7u\2\2\u0081\u0082"+
		"\7e\2\2\u0082\u0083\7q\2\2\u0083\u0084\7p\2\2\u0084\u0085\7x\2\2\u0085"+
		"\u0086\7g\2\2\u0086\u0087\7t\2\2\u0087\u0088\7v\2\2\u0088\u00d9\7u\2\2"+
		"\u0089\u008a\7o\2\2\u008a\u008b\7k\2\2\u008b\u008c\7z\2\2\u008c\u008d"+
		"\7g\2\2\u008d\u00d9\7u\2\2\u008e\u008f\7e\2\2\u008f\u0090\7q\2\2\u0090"+
		"\u0091\7w\2\2\u0091\u0092\7r\2\2\u0092\u0093\7n\2\2\u0093\u0094\7g\2\2"+
		"\u0094\u00d9\7u\2\2\u0095\u0096\7u\2\2\u0096\u0097\7g\2\2\u0097\u0098"+
		"\7r\2\2\u0098\u0099\7c\2\2\u0099\u009a\7t\2\2\u009a\u009b\7c\2\2\u009b"+
		"\u009c\7v\2\2\u009c\u009d\7g\2\2\u009d\u00d9\7u\2\2\u009e\u009f\7g\2\2"+
		"\u009f\u00a0\7p\2\2\u00a0\u00a1\7g\2\2\u00a1\u00a2\7t\2\2\u00a2\u00a3"+
		"\7i\2\2\u00a3\u00a4\7k\2\2\u00a4\u00a5\7|\2\2\u00a5\u00a6\7g\2\2\u00a6"+
		"\u00d9\7u\2\2\u00a7\u00a8\7f\2\2\u00a8\u00a9\7g\2\2\u00a9\u00aa\7g\2\2"+
		"\u00aa\u00ab\7p\2\2\u00ab\u00ac\7g\2\2\u00ac\u00ad\7t\2\2\u00ad\u00ae"+
		"\7i\2\2\u00ae\u00af\7k\2\2\u00af\u00b0\7|\2\2\u00b0\u00b1\7g\2\2\u00b1"+
		"\u00d9\7u\2\2\u00b2\u00b3\7u\2\2\u00b3\u00b4\7v\2\2\u00b4\u00b5\7q\2\2"+
		"\u00b5\u00b6\7t\2\2\u00b6\u00b7\7g\2\2\u00b7\u00d9\7u\2\2\u00b8\u00b9"+
		"\7u\2\2\u00b9\u00ba\7v\2\2\u00ba\u00bb\7q\2\2\u00bb\u00bc\7r\2\2\u00bc"+
		"\u00d9\7u\2\2\u00bd\u00be\7e\2\2\u00be\u00bf\7j\2\2\u00bf\u00c0\7c\2\2"+
		"\u00c0\u00c1\7p\2\2\u00c1\u00c2\7i\2\2\u00c2\u00c3\7g\2\2\u00c3\u00d9"+
		"\7u\2\2\u00c4\u00c5\7t\2\2\u00c5\u00c6\7g\2\2\u00c6\u00c7\7i\2\2\u00c7"+
		"\u00c8\7w\2\2\u00c8\u00c9\7n\2\2\u00c9\u00ca\7c\2\2\u00ca\u00cb\7v\2\2"+
		"\u00cb\u00cc\7g\2\2\u00cc\u00d9\7u\2\2\u00cd\u00ce\7f\2\2\u00ce\u00cf"+
		"\7k\2\2\u00cf\u00d0\7u\2\2\u00d0\u00d1\7v\2\2\u00d1\u00d2\7t\2\2\u00d2"+
		"\u00d3\7k\2\2\u00d3\u00d4\7d\2\2\u00d4\u00d5\7w\2\2\u00d5\u00d6\7v\2\2"+
		"\u00d6\u00d7\7g\2\2\u00d7\u00d9\7u\2\2\u00d8\\\3\2\2\2\u00d8c\3\2\2\2"+
		"\u00d8j\3\2\2\2\u00d8s\3\2\2\2\u00d8y\3\2\2\2\u00d8\u0081\3\2\2\2\u00d8"+
		"\u0089\3\2\2\2\u00d8\u008e\3\2\2\2\u00d8\u0095\3\2\2\2\u00d8\u009e\3\2"+
		"\2\2\u00d8\u00a7\3\2\2\2\u00d8\u00b2\3\2\2\2\u00d8\u00b8\3\2\2\2\u00d8"+
		"\u00bd\3\2\2\2\u00d8\u00c4\3\2\2\2\u00d8\u00cd\3\2\2\2\u00d9\32\3\2\2"+
		"\2\u00da\u00db\7g\2\2\u00db\u00dc\7p\2\2\u00dc\u00dd\7g\2\2\u00dd\u00de"+
		"\7t\2\2\u00de\u00df\7i\2\2\u00df\u00e0\7{\2\2\u00e0\34\3\2\2\2\u00e1\u00e3"+
		"\t\2\2\2\u00e2\u00e4\t\3\2\2\u00e3\u00e2\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5"+
		"\u00e3\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6\36\3\2\2\2\u00e7\u00e9\t\2\2"+
		"\2\u00e8\u00e7\3\2\2\2\u00e9\u00ea\3\2\2\2\u00ea\u00e8\3\2\2\2\u00ea\u00eb"+
		"\3\2\2\2\u00eb\u00ef\3\2\2\2\u00ec\u00ee\t\4\2\2\u00ed\u00ec\3\2\2\2\u00ee"+
		"\u00f1\3\2\2\2\u00ef\u00ed\3\2\2\2\u00ef\u00f0\3\2\2\2\u00f0 \3\2\2\2"+
		"\u00f1\u00ef\3\2\2\2\u00f2\u00f4\t\3\2\2\u00f3\u00f2\3\2\2\2\u00f4\u00f5"+
		"\3\2\2\2\u00f5\u00f3\3\2\2\2\u00f5\u00f6\3\2\2\2\u00f6\"\3\2\2\2\u00f7"+
		"\u00f9\t\5\2\2\u00f8\u00f7\3\2\2\2\u00f9\u00fa\3\2\2\2\u00fa\u00f8\3\2"+
		"\2\2\u00fa\u00fb\3\2\2\2\u00fb$\3\2\2\2\u00fc\u00fe\t\6\2\2\u00fd\u00fc"+
		"\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff\u00fd\3\2\2\2\u00ff\u0100\3\2\2\2\u0100"+
		"\u0101\3\2\2\2\u0101\u0102\b\23\2\2\u0102&\3\2\2\2\n\2\u00d8\u00e5\u00ea"+
		"\u00ef\u00f5\u00fa\u00ff\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}