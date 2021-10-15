// Generated from F:/WorkSpace/@Java/Mxx-Compiler/src/parser\MxxLexer.g4 by ANTLR 4.9.1
package parser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MxxLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		BLOCK_COMMENT=1, LINE_COMMENT=2, PAREN_L=3, PAREN_R=4, BRACE_L=5, BRACE_R=6, 
		BRACK_L=7, BRACK_R=8, SEMI=9, COMMA=10, DOT=11, BANG=12, ASSIGN=13, EQ=14, 
		NOT_EQ=15, LT=16, LE=17, GT=18, GE=19, AND=20, OR=21, ADD=22, SUB=23, 
		MUL=24, DIV=25, MOD=26, INC=27, DEC=28, TILDE=29, BIT_AND=30, BIT_OR=31, 
		CARET=32, SHIFT_L=33, SHIFT_R=34, CLASS=35, THIS=36, NEW=37, VOID=38, 
		BOOL=39, INT=40, STRING=41, NULL=42, TRUE=43, FALSE=44, IF=45, ELSE=46, 
		FOR=47, WHILE=48, CONTINUE=49, BREAK=50, RETURN=51, WHITE_SPACE=52, NEW_LINE=53, 
		IDENTIFIER=54, DECIMAL_INTEGER=55, STRING_CONSTANT=56;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"BLOCK_COMMENT", "LINE_COMMENT", "PAREN_L", "PAREN_R", "BRACE_L", "BRACE_R", 
			"BRACK_L", "BRACK_R", "SEMI", "COMMA", "DOT", "BANG", "ASSIGN", "EQ", 
			"NOT_EQ", "LT", "LE", "GT", "GE", "AND", "OR", "ADD", "SUB", "MUL", "DIV", 
			"MOD", "INC", "DEC", "TILDE", "BIT_AND", "BIT_OR", "CARET", "SHIFT_L", 
			"SHIFT_R", "CLASS", "THIS", "NEW", "VOID", "BOOL", "INT", "STRING", "NULL", 
			"TRUE", "FALSE", "IF", "ELSE", "FOR", "WHILE", "CONTINUE", "BREAK", "RETURN", 
			"WHITE_SPACE", "NEW_LINE", "IDENTIFIER", "DECIMAL_INTEGER", "STRING_CONSTANT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, "'('", "')'", "'{'", "'}'", "'['", "']'", "';'", "','", 
			"'.'", "'!'", "'='", "'=='", "'!='", "'<'", "'<='", "'>'", "'>='", "'&&'", 
			"'||'", "'+'", "'-'", "'*'", "'/'", "'%'", "'++'", "'--'", "'~'", "'&'", 
			"'|'", "'^'", "'<<'", "'>>'", "'class'", "'this'", "'new'", "'void'", 
			"'bool'", "'int'", "'string'", "'null'", "'true'", "'false'", "'if'", 
			"'else'", "'for'", "'while'", "'continue'", "'break'", "'return'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "BLOCK_COMMENT", "LINE_COMMENT", "PAREN_L", "PAREN_R", "BRACE_L", 
			"BRACE_R", "BRACK_L", "BRACK_R", "SEMI", "COMMA", "DOT", "BANG", "ASSIGN", 
			"EQ", "NOT_EQ", "LT", "LE", "GT", "GE", "AND", "OR", "ADD", "SUB", "MUL", 
			"DIV", "MOD", "INC", "DEC", "TILDE", "BIT_AND", "BIT_OR", "CARET", "SHIFT_L", 
			"SHIFT_R", "CLASS", "THIS", "NEW", "VOID", "BOOL", "INT", "STRING", "NULL", 
			"TRUE", "FALSE", "IF", "ELSE", "FOR", "WHILE", "CONTINUE", "BREAK", "RETURN", 
			"WHITE_SPACE", "NEW_LINE", "IDENTIFIER", "DECIMAL_INTEGER", "STRING_CONSTANT"
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


	public MxxLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "MxxLexer.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2:\u0161\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\3\2\3\2\3\2\3\2\7\2x\n\2"+
		"\f\2\16\2{\13\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\7\3\u0086\n\3\f\3"+
		"\16\3\u0089\13\3\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3"+
		"\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3\20\3\20"+
		"\3\20\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\25"+
		"\3\26\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34"+
		"\3\34\3\34\3\35\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3\""+
		"\3#\3#\3#\3$\3$\3$\3$\3$\3$\3%\3%\3%\3%\3%\3&\3&\3&\3&\3\'\3\'\3\'\3\'"+
		"\3\'\3(\3(\3(\3(\3(\3)\3)\3)\3)\3*\3*\3*\3*\3*\3*\3*\3+\3+\3+\3+\3+\3"+
		",\3,\3,\3,\3,\3-\3-\3-\3-\3-\3-\3.\3.\3.\3/\3/\3/\3/\3/\3\60\3\60\3\60"+
		"\3\60\3\61\3\61\3\61\3\61\3\61\3\61\3\62\3\62\3\62\3\62\3\62\3\62\3\62"+
		"\3\62\3\62\3\63\3\63\3\63\3\63\3\63\3\63\3\64\3\64\3\64\3\64\3\64\3\64"+
		"\3\64\3\65\6\65\u0134\n\65\r\65\16\65\u0135\3\65\3\65\3\66\3\66\5\66\u013c"+
		"\n\66\3\66\5\66\u013f\n\66\3\66\3\66\3\67\3\67\7\67\u0145\n\67\f\67\16"+
		"\67\u0148\13\67\38\58\u014b\n8\38\38\78\u014f\n8\f8\168\u0152\138\38\5"+
		"8\u0155\n8\39\39\39\39\79\u015b\n9\f9\169\u015e\139\39\39\4y\u015c\2:"+
		"\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20"+
		"\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37"+
		"= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67m8o"+
		"9q:\3\2\b\4\2\f\f\17\17\4\2\13\13\"\"\4\2C\\c|\6\2\62;C\\aac|\3\2\63;"+
		"\3\2\62;\2\u016b\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13"+
		"\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2"+
		"\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2"+
		"!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3"+
		"\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2"+
		"\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E"+
		"\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2"+
		"\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2"+
		"\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k"+
		"\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\3s\3\2\2\2\5\u0081\3\2\2\2\7"+
		"\u008c\3\2\2\2\t\u008e\3\2\2\2\13\u0090\3\2\2\2\r\u0092\3\2\2\2\17\u0094"+
		"\3\2\2\2\21\u0096\3\2\2\2\23\u0098\3\2\2\2\25\u009a\3\2\2\2\27\u009c\3"+
		"\2\2\2\31\u009e\3\2\2\2\33\u00a0\3\2\2\2\35\u00a2\3\2\2\2\37\u00a5\3\2"+
		"\2\2!\u00a8\3\2\2\2#\u00aa\3\2\2\2%\u00ad\3\2\2\2\'\u00af\3\2\2\2)\u00b2"+
		"\3\2\2\2+\u00b5\3\2\2\2-\u00b8\3\2\2\2/\u00ba\3\2\2\2\61\u00bc\3\2\2\2"+
		"\63\u00be\3\2\2\2\65\u00c0\3\2\2\2\67\u00c2\3\2\2\29\u00c5\3\2\2\2;\u00c8"+
		"\3\2\2\2=\u00ca\3\2\2\2?\u00cc\3\2\2\2A\u00ce\3\2\2\2C\u00d0\3\2\2\2E"+
		"\u00d3\3\2\2\2G\u00d6\3\2\2\2I\u00dc\3\2\2\2K\u00e1\3\2\2\2M\u00e5\3\2"+
		"\2\2O\u00ea\3\2\2\2Q\u00ef\3\2\2\2S\u00f3\3\2\2\2U\u00fa\3\2\2\2W\u00ff"+
		"\3\2\2\2Y\u0104\3\2\2\2[\u010a\3\2\2\2]\u010d\3\2\2\2_\u0112\3\2\2\2a"+
		"\u0116\3\2\2\2c\u011c\3\2\2\2e\u0125\3\2\2\2g\u012b\3\2\2\2i\u0133\3\2"+
		"\2\2k\u013e\3\2\2\2m\u0142\3\2\2\2o\u014a\3\2\2\2q\u0156\3\2\2\2st\7\61"+
		"\2\2tu\7,\2\2uy\3\2\2\2vx\13\2\2\2wv\3\2\2\2x{\3\2\2\2yz\3\2\2\2yw\3\2"+
		"\2\2z|\3\2\2\2{y\3\2\2\2|}\7,\2\2}~\7\61\2\2~\177\3\2\2\2\177\u0080\b"+
		"\2\2\2\u0080\4\3\2\2\2\u0081\u0082\7\61\2\2\u0082\u0083\7\61\2\2\u0083"+
		"\u0087\3\2\2\2\u0084\u0086\n\2\2\2\u0085\u0084\3\2\2\2\u0086\u0089\3\2"+
		"\2\2\u0087\u0085\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u008a\3\2\2\2\u0089"+
		"\u0087\3\2\2\2\u008a\u008b\b\3\2\2\u008b\6\3\2\2\2\u008c\u008d\7*\2\2"+
		"\u008d\b\3\2\2\2\u008e\u008f\7+\2\2\u008f\n\3\2\2\2\u0090\u0091\7}\2\2"+
		"\u0091\f\3\2\2\2\u0092\u0093\7\177\2\2\u0093\16\3\2\2\2\u0094\u0095\7"+
		"]\2\2\u0095\20\3\2\2\2\u0096\u0097\7_\2\2\u0097\22\3\2\2\2\u0098\u0099"+
		"\7=\2\2\u0099\24\3\2\2\2\u009a\u009b\7.\2\2\u009b\26\3\2\2\2\u009c\u009d"+
		"\7\60\2\2\u009d\30\3\2\2\2\u009e\u009f\7#\2\2\u009f\32\3\2\2\2\u00a0\u00a1"+
		"\7?\2\2\u00a1\34\3\2\2\2\u00a2\u00a3\7?\2\2\u00a3\u00a4\7?\2\2\u00a4\36"+
		"\3\2\2\2\u00a5\u00a6\7#\2\2\u00a6\u00a7\7?\2\2\u00a7 \3\2\2\2\u00a8\u00a9"+
		"\7>\2\2\u00a9\"\3\2\2\2\u00aa\u00ab\7>\2\2\u00ab\u00ac\7?\2\2\u00ac$\3"+
		"\2\2\2\u00ad\u00ae\7@\2\2\u00ae&\3\2\2\2\u00af\u00b0\7@\2\2\u00b0\u00b1"+
		"\7?\2\2\u00b1(\3\2\2\2\u00b2\u00b3\7(\2\2\u00b3\u00b4\7(\2\2\u00b4*\3"+
		"\2\2\2\u00b5\u00b6\7~\2\2\u00b6\u00b7\7~\2\2\u00b7,\3\2\2\2\u00b8\u00b9"+
		"\7-\2\2\u00b9.\3\2\2\2\u00ba\u00bb\7/\2\2\u00bb\60\3\2\2\2\u00bc\u00bd"+
		"\7,\2\2\u00bd\62\3\2\2\2\u00be\u00bf\7\61\2\2\u00bf\64\3\2\2\2\u00c0\u00c1"+
		"\7\'\2\2\u00c1\66\3\2\2\2\u00c2\u00c3\7-\2\2\u00c3\u00c4\7-\2\2\u00c4"+
		"8\3\2\2\2\u00c5\u00c6\7/\2\2\u00c6\u00c7\7/\2\2\u00c7:\3\2\2\2\u00c8\u00c9"+
		"\7\u0080\2\2\u00c9<\3\2\2\2\u00ca\u00cb\7(\2\2\u00cb>\3\2\2\2\u00cc\u00cd"+
		"\7~\2\2\u00cd@\3\2\2\2\u00ce\u00cf\7`\2\2\u00cfB\3\2\2\2\u00d0\u00d1\7"+
		">\2\2\u00d1\u00d2\7>\2\2\u00d2D\3\2\2\2\u00d3\u00d4\7@\2\2\u00d4\u00d5"+
		"\7@\2\2\u00d5F\3\2\2\2\u00d6\u00d7\7e\2\2\u00d7\u00d8\7n\2\2\u00d8\u00d9"+
		"\7c\2\2\u00d9\u00da\7u\2\2\u00da\u00db\7u\2\2\u00dbH\3\2\2\2\u00dc\u00dd"+
		"\7v\2\2\u00dd\u00de\7j\2\2\u00de\u00df\7k\2\2\u00df\u00e0\7u\2\2\u00e0"+
		"J\3\2\2\2\u00e1\u00e2\7p\2\2\u00e2\u00e3\7g\2\2\u00e3\u00e4\7y\2\2\u00e4"+
		"L\3\2\2\2\u00e5\u00e6\7x\2\2\u00e6\u00e7\7q\2\2\u00e7\u00e8\7k\2\2\u00e8"+
		"\u00e9\7f\2\2\u00e9N\3\2\2\2\u00ea\u00eb\7d\2\2\u00eb\u00ec\7q\2\2\u00ec"+
		"\u00ed\7q\2\2\u00ed\u00ee\7n\2\2\u00eeP\3\2\2\2\u00ef\u00f0\7k\2\2\u00f0"+
		"\u00f1\7p\2\2\u00f1\u00f2\7v\2\2\u00f2R\3\2\2\2\u00f3\u00f4\7u\2\2\u00f4"+
		"\u00f5\7v\2\2\u00f5\u00f6\7t\2\2\u00f6\u00f7\7k\2\2\u00f7\u00f8\7p\2\2"+
		"\u00f8\u00f9\7i\2\2\u00f9T\3\2\2\2\u00fa\u00fb\7p\2\2\u00fb\u00fc\7w\2"+
		"\2\u00fc\u00fd\7n\2\2\u00fd\u00fe\7n\2\2\u00feV\3\2\2\2\u00ff\u0100\7"+
		"v\2\2\u0100\u0101\7t\2\2\u0101\u0102\7w\2\2\u0102\u0103\7g\2\2\u0103X"+
		"\3\2\2\2\u0104\u0105\7h\2\2\u0105\u0106\7c\2\2\u0106\u0107\7n\2\2\u0107"+
		"\u0108\7u\2\2\u0108\u0109\7g\2\2\u0109Z\3\2\2\2\u010a\u010b\7k\2\2\u010b"+
		"\u010c\7h\2\2\u010c\\\3\2\2\2\u010d\u010e\7g\2\2\u010e\u010f\7n\2\2\u010f"+
		"\u0110\7u\2\2\u0110\u0111\7g\2\2\u0111^\3\2\2\2\u0112\u0113\7h\2\2\u0113"+
		"\u0114\7q\2\2\u0114\u0115\7t\2\2\u0115`\3\2\2\2\u0116\u0117\7y\2\2\u0117"+
		"\u0118\7j\2\2\u0118\u0119\7k\2\2\u0119\u011a\7n\2\2\u011a\u011b\7g\2\2"+
		"\u011bb\3\2\2\2\u011c\u011d\7e\2\2\u011d\u011e\7q\2\2\u011e\u011f\7p\2"+
		"\2\u011f\u0120\7v\2\2\u0120\u0121\7k\2\2\u0121\u0122\7p\2\2\u0122\u0123"+
		"\7w\2\2\u0123\u0124\7g\2\2\u0124d\3\2\2\2\u0125\u0126\7d\2\2\u0126\u0127"+
		"\7t\2\2\u0127\u0128\7g\2\2\u0128\u0129\7c\2\2\u0129\u012a\7m\2\2\u012a"+
		"f\3\2\2\2\u012b\u012c\7t\2\2\u012c\u012d\7g\2\2\u012d\u012e\7v\2\2\u012e"+
		"\u012f\7w\2\2\u012f\u0130\7t\2\2\u0130\u0131\7p\2\2\u0131h\3\2\2\2\u0132"+
		"\u0134\t\3\2\2\u0133\u0132\3\2\2\2\u0134\u0135\3\2\2\2\u0135\u0133\3\2"+
		"\2\2\u0135\u0136\3\2\2\2\u0136\u0137\3\2\2\2\u0137\u0138\b\65\2\2\u0138"+
		"j\3\2\2\2\u0139\u013b\7\17\2\2\u013a\u013c\7\f\2\2\u013b\u013a\3\2\2\2"+
		"\u013b\u013c\3\2\2\2\u013c\u013f\3\2\2\2\u013d\u013f\7\f\2\2\u013e\u0139"+
		"\3\2\2\2\u013e\u013d\3\2\2\2\u013f\u0140\3\2\2\2\u0140\u0141\b\66\2\2"+
		"\u0141l\3\2\2\2\u0142\u0146\t\4\2\2\u0143\u0145\t\5\2\2\u0144\u0143\3"+
		"\2\2\2\u0145\u0148\3\2\2\2\u0146\u0144\3\2\2\2\u0146\u0147\3\2\2\2\u0147"+
		"n\3\2\2\2\u0148\u0146\3\2\2\2\u0149\u014b\7/\2\2\u014a\u0149\3\2\2\2\u014a"+
		"\u014b\3\2\2\2\u014b\u0154\3\2\2\2\u014c\u0150\t\6\2\2\u014d\u014f\t\7"+
		"\2\2\u014e\u014d\3\2\2\2\u014f\u0152\3\2\2\2\u0150\u014e\3\2\2\2\u0150"+
		"\u0151\3\2\2\2\u0151\u0155\3\2\2\2\u0152\u0150\3\2\2\2\u0153\u0155\7\62"+
		"\2\2\u0154\u014c\3\2\2\2\u0154\u0153\3\2\2\2\u0155p\3\2\2\2\u0156\u015c"+
		"\7$\2\2\u0157\u0158\7^\2\2\u0158\u015b\13\2\2\2\u0159\u015b\13\2\2\2\u015a"+
		"\u0157\3\2\2\2\u015a\u0159\3\2\2\2\u015b\u015e\3\2\2\2\u015c\u015d\3\2"+
		"\2\2\u015c\u015a\3\2\2\2\u015d\u015f\3\2\2\2\u015e\u015c\3\2\2\2\u015f"+
		"\u0160\7$\2\2\u0160r\3\2\2\2\16\2y\u0087\u0135\u013b\u013e\u0146\u014a"+
		"\u0150\u0154\u015a\u015c\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}