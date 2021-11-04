package parser;
// Generated from //wsl$/Ubuntu/home/paperl/workspace/Mxx-Compiler/grammar\MxxLexer.g4 by ANTLR 4.9.1
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
		BLOCK_COMMENT=1, LINE_COMMENT=2, BRACE_L=3, BRACE_R=4, SEMI=5, PAREN_L=6, 
		PAREN_R=7, COMMA=8, DOT=9, ARROW=10, BRACK_L=11, BRACK_R=12, BANG=13, 
		ASSIGN=14, EQ=15, NOT_EQ=16, LT=17, LE=18, GT=19, GE=20, AND=21, OR=22, 
		ADD=23, SUB=24, MUL=25, DIV=26, MOD=27, INC=28, DEC=29, TILDE=30, BIT_AND=31, 
		BIT_OR=32, CARET=33, SHIFT_L=34, SHIFT_R=35, CLASS=36, THIS=37, NEW=38, 
		VOID=39, BOOL=40, INT=41, STRING=42, NULL=43, TRUE=44, FALSE=45, IF=46, 
		ELSE=47, FOR=48, WHILE=49, CONTINUE=50, BREAK=51, RETURN=52, WHITE_SPACE=53, 
		NEW_LINE=54, IDENTIFIER=55, DECIMAL_INTEGER=56, STRING_CONSTANT=57;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"BLOCK_COMMENT", "LINE_COMMENT", "BRACE_L", "BRACE_R", "SEMI", "PAREN_L", 
			"PAREN_R", "COMMA", "DOT", "ARROW", "BRACK_L", "BRACK_R", "BANG", "ASSIGN", 
			"EQ", "NOT_EQ", "LT", "LE", "GT", "GE", "AND", "OR", "ADD", "SUB", "MUL", 
			"DIV", "MOD", "INC", "DEC", "TILDE", "BIT_AND", "BIT_OR", "CARET", "SHIFT_L", 
			"SHIFT_R", "CLASS", "THIS", "NEW", "VOID", "BOOL", "INT", "STRING", "NULL", 
			"TRUE", "FALSE", "IF", "ELSE", "FOR", "WHILE", "CONTINUE", "BREAK", "RETURN", 
			"WHITE_SPACE", "NEW_LINE", "IDENTIFIER", "DECIMAL_INTEGER", "STRING_CONSTANT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, "'{'", "'}'", "';'", "'('", "')'", "','", "'.'", "'->'", 
			"'['", "']'", "'!'", "'='", "'=='", "'!='", "'<'", "'<='", "'>'", "'>='", 
			"'&&'", "'||'", "'+'", "'-'", "'*'", "'/'", "'%'", "'++'", "'--'", "'~'", 
			"'&'", "'|'", "'^'", "'<<'", "'>>'", "'class'", "'this'", "'new'", "'void'", 
			"'bool'", "'int'", "'string'", "'null'", "'true'", "'false'", "'if'", 
			"'else'", "'for'", "'while'", "'continue'", "'break'", "'return'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "BLOCK_COMMENT", "LINE_COMMENT", "BRACE_L", "BRACE_R", "SEMI", 
			"PAREN_L", "PAREN_R", "COMMA", "DOT", "ARROW", "BRACK_L", "BRACK_R", 
			"BANG", "ASSIGN", "EQ", "NOT_EQ", "LT", "LE", "GT", "GE", "AND", "OR", 
			"ADD", "SUB", "MUL", "DIV", "MOD", "INC", "DEC", "TILDE", "BIT_AND", 
			"BIT_OR", "CARET", "SHIFT_L", "SHIFT_R", "CLASS", "THIS", "NEW", "VOID", 
			"BOOL", "INT", "STRING", "NULL", "TRUE", "FALSE", "IF", "ELSE", "FOR", 
			"WHILE", "CONTINUE", "BREAK", "RETURN", "WHITE_SPACE", "NEW_LINE", "IDENTIFIER", 
			"DECIMAL_INTEGER", "STRING_CONSTANT"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2;\u0163\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\3\2\3\2\3\2\3\2\7"+
		"\2z\n\2\f\2\16\2}\13\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\7\3\u0088\n"+
		"\3\f\3\16\3\u008b\13\3\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b"+
		"\3\t\3\t\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3"+
		"\20\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3\25\3"+
		"\25\3\25\3\26\3\26\3\26\3\27\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3"+
		"\33\3\33\3\34\3\34\3\35\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3 \3 \3!\3"+
		"!\3\"\3\"\3#\3#\3#\3$\3$\3$\3%\3%\3%\3%\3%\3%\3&\3&\3&\3&\3&\3\'\3\'\3"+
		"\'\3\'\3(\3(\3(\3(\3(\3)\3)\3)\3)\3)\3*\3*\3*\3*\3+\3+\3+\3+\3+\3+\3+"+
		"\3,\3,\3,\3,\3,\3-\3-\3-\3-\3-\3.\3.\3.\3.\3.\3.\3/\3/\3/\3\60\3\60\3"+
		"\60\3\60\3\60\3\61\3\61\3\61\3\61\3\62\3\62\3\62\3\62\3\62\3\62\3\63\3"+
		"\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\64\3\64\3\64\3\64\3\64\3\64\3"+
		"\65\3\65\3\65\3\65\3\65\3\65\3\65\3\66\6\66\u0139\n\66\r\66\16\66\u013a"+
		"\3\66\3\66\3\67\3\67\5\67\u0141\n\67\3\67\5\67\u0144\n\67\3\67\3\67\3"+
		"8\38\78\u014a\n8\f8\168\u014d\138\39\39\79\u0151\n9\f9\169\u0154\139\3"+
		"9\59\u0157\n9\3:\3:\3:\3:\7:\u015d\n:\f:\16:\u0160\13:\3:\3:\4{\u015e"+
		"\2;\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35"+
		"\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36"+
		";\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67"+
		"m8o9q:s;\3\2\b\4\2\f\f\17\17\4\2\13\13\"\"\5\2C\\aac|\6\2\62;C\\aac|\3"+
		"\2\63;\3\2\62;\2\u016c\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2"+
		"\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25"+
		"\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2"+
		"\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2"+
		"\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3"+
		"\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2"+
		"\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2"+
		"Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3"+
		"\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2"+
		"\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\3u\3\2\2\2\5"+
		"\u0083\3\2\2\2\7\u008e\3\2\2\2\t\u0090\3\2\2\2\13\u0092\3\2\2\2\r\u0094"+
		"\3\2\2\2\17\u0096\3\2\2\2\21\u0098\3\2\2\2\23\u009a\3\2\2\2\25\u009c\3"+
		"\2\2\2\27\u009f\3\2\2\2\31\u00a1\3\2\2\2\33\u00a3\3\2\2\2\35\u00a5\3\2"+
		"\2\2\37\u00a7\3\2\2\2!\u00aa\3\2\2\2#\u00ad\3\2\2\2%\u00af\3\2\2\2\'\u00b2"+
		"\3\2\2\2)\u00b4\3\2\2\2+\u00b7\3\2\2\2-\u00ba\3\2\2\2/\u00bd\3\2\2\2\61"+
		"\u00bf\3\2\2\2\63\u00c1\3\2\2\2\65\u00c3\3\2\2\2\67\u00c5\3\2\2\29\u00c7"+
		"\3\2\2\2;\u00ca\3\2\2\2=\u00cd\3\2\2\2?\u00cf\3\2\2\2A\u00d1\3\2\2\2C"+
		"\u00d3\3\2\2\2E\u00d5\3\2\2\2G\u00d8\3\2\2\2I\u00db\3\2\2\2K\u00e1\3\2"+
		"\2\2M\u00e6\3\2\2\2O\u00ea\3\2\2\2Q\u00ef\3\2\2\2S\u00f4\3\2\2\2U\u00f8"+
		"\3\2\2\2W\u00ff\3\2\2\2Y\u0104\3\2\2\2[\u0109\3\2\2\2]\u010f\3\2\2\2_"+
		"\u0112\3\2\2\2a\u0117\3\2\2\2c\u011b\3\2\2\2e\u0121\3\2\2\2g\u012a\3\2"+
		"\2\2i\u0130\3\2\2\2k\u0138\3\2\2\2m\u0143\3\2\2\2o\u0147\3\2\2\2q\u0156"+
		"\3\2\2\2s\u0158\3\2\2\2uv\7\61\2\2vw\7,\2\2w{\3\2\2\2xz\13\2\2\2yx\3\2"+
		"\2\2z}\3\2\2\2{|\3\2\2\2{y\3\2\2\2|~\3\2\2\2}{\3\2\2\2~\177\7,\2\2\177"+
		"\u0080\7\61\2\2\u0080\u0081\3\2\2\2\u0081\u0082\b\2\2\2\u0082\4\3\2\2"+
		"\2\u0083\u0084\7\61\2\2\u0084\u0085\7\61\2\2\u0085\u0089\3\2\2\2\u0086"+
		"\u0088\n\2\2\2\u0087\u0086\3\2\2\2\u0088\u008b\3\2\2\2\u0089\u0087\3\2"+
		"\2\2\u0089\u008a\3\2\2\2\u008a\u008c\3\2\2\2\u008b\u0089\3\2\2\2\u008c"+
		"\u008d\b\3\2\2\u008d\6\3\2\2\2\u008e\u008f\7}\2\2\u008f\b\3\2\2\2\u0090"+
		"\u0091\7\177\2\2\u0091\n\3\2\2\2\u0092\u0093\7=\2\2\u0093\f\3\2\2\2\u0094"+
		"\u0095\7*\2\2\u0095\16\3\2\2\2\u0096\u0097\7+\2\2\u0097\20\3\2\2\2\u0098"+
		"\u0099\7.\2\2\u0099\22\3\2\2\2\u009a\u009b\7\60\2\2\u009b\24\3\2\2\2\u009c"+
		"\u009d\7/\2\2\u009d\u009e\7@\2\2\u009e\26\3\2\2\2\u009f\u00a0\7]\2\2\u00a0"+
		"\30\3\2\2\2\u00a1\u00a2\7_\2\2\u00a2\32\3\2\2\2\u00a3\u00a4\7#\2\2\u00a4"+
		"\34\3\2\2\2\u00a5\u00a6\7?\2\2\u00a6\36\3\2\2\2\u00a7\u00a8\7?\2\2\u00a8"+
		"\u00a9\7?\2\2\u00a9 \3\2\2\2\u00aa\u00ab\7#\2\2\u00ab\u00ac\7?\2\2\u00ac"+
		"\"\3\2\2\2\u00ad\u00ae\7>\2\2\u00ae$\3\2\2\2\u00af\u00b0\7>\2\2\u00b0"+
		"\u00b1\7?\2\2\u00b1&\3\2\2\2\u00b2\u00b3\7@\2\2\u00b3(\3\2\2\2\u00b4\u00b5"+
		"\7@\2\2\u00b5\u00b6\7?\2\2\u00b6*\3\2\2\2\u00b7\u00b8\7(\2\2\u00b8\u00b9"+
		"\7(\2\2\u00b9,\3\2\2\2\u00ba\u00bb\7~\2\2\u00bb\u00bc\7~\2\2\u00bc.\3"+
		"\2\2\2\u00bd\u00be\7-\2\2\u00be\60\3\2\2\2\u00bf\u00c0\7/\2\2\u00c0\62"+
		"\3\2\2\2\u00c1\u00c2\7,\2\2\u00c2\64\3\2\2\2\u00c3\u00c4\7\61\2\2\u00c4"+
		"\66\3\2\2\2\u00c5\u00c6\7\'\2\2\u00c68\3\2\2\2\u00c7\u00c8\7-\2\2\u00c8"+
		"\u00c9\7-\2\2\u00c9:\3\2\2\2\u00ca\u00cb\7/\2\2\u00cb\u00cc\7/\2\2\u00cc"+
		"<\3\2\2\2\u00cd\u00ce\7\u0080\2\2\u00ce>\3\2\2\2\u00cf\u00d0\7(\2\2\u00d0"+
		"@\3\2\2\2\u00d1\u00d2\7~\2\2\u00d2B\3\2\2\2\u00d3\u00d4\7`\2\2\u00d4D"+
		"\3\2\2\2\u00d5\u00d6\7>\2\2\u00d6\u00d7\7>\2\2\u00d7F\3\2\2\2\u00d8\u00d9"+
		"\7@\2\2\u00d9\u00da\7@\2\2\u00daH\3\2\2\2\u00db\u00dc\7e\2\2\u00dc\u00dd"+
		"\7n\2\2\u00dd\u00de\7c\2\2\u00de\u00df\7u\2\2\u00df\u00e0\7u\2\2\u00e0"+
		"J\3\2\2\2\u00e1\u00e2\7v\2\2\u00e2\u00e3\7j\2\2\u00e3\u00e4\7k\2\2\u00e4"+
		"\u00e5\7u\2\2\u00e5L\3\2\2\2\u00e6\u00e7\7p\2\2\u00e7\u00e8\7g\2\2\u00e8"+
		"\u00e9\7y\2\2\u00e9N\3\2\2\2\u00ea\u00eb\7x\2\2\u00eb\u00ec\7q\2\2\u00ec"+
		"\u00ed\7k\2\2\u00ed\u00ee\7f\2\2\u00eeP\3\2\2\2\u00ef\u00f0\7d\2\2\u00f0"+
		"\u00f1\7q\2\2\u00f1\u00f2\7q\2\2\u00f2\u00f3\7n\2\2\u00f3R\3\2\2\2\u00f4"+
		"\u00f5\7k\2\2\u00f5\u00f6\7p\2\2\u00f6\u00f7\7v\2\2\u00f7T\3\2\2\2\u00f8"+
		"\u00f9\7u\2\2\u00f9\u00fa\7v\2\2\u00fa\u00fb\7t\2\2\u00fb\u00fc\7k\2\2"+
		"\u00fc\u00fd\7p\2\2\u00fd\u00fe\7i\2\2\u00feV\3\2\2\2\u00ff\u0100\7p\2"+
		"\2\u0100\u0101\7w\2\2\u0101\u0102\7n\2\2\u0102\u0103\7n\2\2\u0103X\3\2"+
		"\2\2\u0104\u0105\7v\2\2\u0105\u0106\7t\2\2\u0106\u0107\7w\2\2\u0107\u0108"+
		"\7g\2\2\u0108Z\3\2\2\2\u0109\u010a\7h\2\2\u010a\u010b\7c\2\2\u010b\u010c"+
		"\7n\2\2\u010c\u010d\7u\2\2\u010d\u010e\7g\2\2\u010e\\\3\2\2\2\u010f\u0110"+
		"\7k\2\2\u0110\u0111\7h\2\2\u0111^\3\2\2\2\u0112\u0113\7g\2\2\u0113\u0114"+
		"\7n\2\2\u0114\u0115\7u\2\2\u0115\u0116\7g\2\2\u0116`\3\2\2\2\u0117\u0118"+
		"\7h\2\2\u0118\u0119\7q\2\2\u0119\u011a\7t\2\2\u011ab\3\2\2\2\u011b\u011c"+
		"\7y\2\2\u011c\u011d\7j\2\2\u011d\u011e\7k\2\2\u011e\u011f\7n\2\2\u011f"+
		"\u0120\7g\2\2\u0120d\3\2\2\2\u0121\u0122\7e\2\2\u0122\u0123\7q\2\2\u0123"+
		"\u0124\7p\2\2\u0124\u0125\7v\2\2\u0125\u0126\7k\2\2\u0126\u0127\7p\2\2"+
		"\u0127\u0128\7w\2\2\u0128\u0129\7g\2\2\u0129f\3\2\2\2\u012a\u012b\7d\2"+
		"\2\u012b\u012c\7t\2\2\u012c\u012d\7g\2\2\u012d\u012e\7c\2\2\u012e\u012f"+
		"\7m\2\2\u012fh\3\2\2\2\u0130\u0131\7t\2\2\u0131\u0132\7g\2\2\u0132\u0133"+
		"\7v\2\2\u0133\u0134\7w\2\2\u0134\u0135\7t\2\2\u0135\u0136\7p\2\2\u0136"+
		"j\3\2\2\2\u0137\u0139\t\3\2\2\u0138\u0137\3\2\2\2\u0139\u013a\3\2\2\2"+
		"\u013a\u0138\3\2\2\2\u013a\u013b\3\2\2\2\u013b\u013c\3\2\2\2\u013c\u013d"+
		"\b\66\2\2\u013dl\3\2\2\2\u013e\u0140\7\17\2\2\u013f\u0141\7\f\2\2\u0140"+
		"\u013f\3\2\2\2\u0140\u0141\3\2\2\2\u0141\u0144\3\2\2\2\u0142\u0144\7\f"+
		"\2\2\u0143\u013e\3\2\2\2\u0143\u0142\3\2\2\2\u0144\u0145\3\2\2\2\u0145"+
		"\u0146\b\67\2\2\u0146n\3\2\2\2\u0147\u014b\t\4\2\2\u0148\u014a\t\5\2\2"+
		"\u0149\u0148\3\2\2\2\u014a\u014d\3\2\2\2\u014b\u0149\3\2\2\2\u014b\u014c"+
		"\3\2\2\2\u014cp\3\2\2\2\u014d\u014b\3\2\2\2\u014e\u0152\t\6\2\2\u014f"+
		"\u0151\t\7\2\2\u0150\u014f\3\2\2\2\u0151\u0154\3\2\2\2\u0152\u0150\3\2"+
		"\2\2\u0152\u0153\3\2\2\2\u0153\u0157\3\2\2\2\u0154\u0152\3\2\2\2\u0155"+
		"\u0157\7\62\2\2\u0156\u014e\3\2\2\2\u0156\u0155\3\2\2\2\u0157r\3\2\2\2"+
		"\u0158\u015e\7$\2\2\u0159\u015a\7^\2\2\u015a\u015d\13\2\2\2\u015b\u015d"+
		"\13\2\2\2\u015c\u0159\3\2\2\2\u015c\u015b\3\2\2\2\u015d\u0160\3\2\2\2"+
		"\u015e\u015f\3\2\2\2\u015e\u015c\3\2\2\2\u015f\u0161\3\2\2\2\u0160\u015e"+
		"\3\2\2\2\u0161\u0162\7$\2\2\u0162t\3\2\2\2\r\2{\u0089\u013a\u0140\u0143"+
		"\u014b\u0152\u0156\u015c\u015e\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}