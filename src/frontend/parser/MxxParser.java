package frontend.parser;
// Generated from E:/WinWorkSpace/@Java/Mxx-Compiler/grammar\MxxParser.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MxxParser extends Parser {
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
	public static final int
		RULE_program = 0, RULE_programSection = 1, RULE_classDefine = 2, RULE_functionDefine = 3, 
		RULE_argumentList = 4, RULE_variableDefine = 5, RULE_variableTerm = 6, 
		RULE_type = 7, RULE_bracket = 8, RULE_suite = 9, RULE_statement = 10, 
		RULE_expression = 11, RULE_expressionList = 12, RULE_atom = 13;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "programSection", "classDefine", "functionDefine", "argumentList", 
			"variableDefine", "variableTerm", "type", "bracket", "suite", "statement", 
			"expression", "expressionList", "atom"
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

	@Override
	public String getGrammarFileName() { return "MxxParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MxxParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(MxxParser.EOF, 0); }
		public List<ProgramSectionContext> programSection() {
			return getRuleContexts(ProgramSectionContext.class);
		}
		public ProgramSectionContext programSection(int i) {
			return getRuleContext(ProgramSectionContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxxParserVisitor ) return ((MxxParserVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(31);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CLASS) | (1L << VOID) | (1L << BOOL) | (1L << INT) | (1L << STRING) | (1L << IDENTIFIER))) != 0)) {
				{
				{
				setState(28);
				programSection();
				}
				}
				setState(33);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(34);
			match(EOF);
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

	public static class ProgramSectionContext extends ParserRuleContext {
		public ClassDefineContext classDefine() {
			return getRuleContext(ClassDefineContext.class,0);
		}
		public FunctionDefineContext functionDefine() {
			return getRuleContext(FunctionDefineContext.class,0);
		}
		public VariableDefineContext variableDefine() {
			return getRuleContext(VariableDefineContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(MxxParser.SEMI, 0); }
		public ProgramSectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_programSection; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).enterProgramSection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).exitProgramSection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxxParserVisitor ) return ((MxxParserVisitor<? extends T>)visitor).visitProgramSection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramSectionContext programSection() throws RecognitionException {
		ProgramSectionContext _localctx = new ProgramSectionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_programSection);
		try {
			setState(41);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(36);
				classDefine();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(37);
				functionDefine();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(38);
				variableDefine();
				setState(39);
				match(SEMI);
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

	public static class ClassDefineContext extends ParserRuleContext {
		public TerminalNode CLASS() { return getToken(MxxParser.CLASS, 0); }
		public TerminalNode IDENTIFIER() { return getToken(MxxParser.IDENTIFIER, 0); }
		public TerminalNode BRACE_L() { return getToken(MxxParser.BRACE_L, 0); }
		public TerminalNode BRACE_R() { return getToken(MxxParser.BRACE_R, 0); }
		public List<TerminalNode> SEMI() { return getTokens(MxxParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(MxxParser.SEMI, i);
		}
		public List<FunctionDefineContext> functionDefine() {
			return getRuleContexts(FunctionDefineContext.class);
		}
		public FunctionDefineContext functionDefine(int i) {
			return getRuleContext(FunctionDefineContext.class,i);
		}
		public List<VariableDefineContext> variableDefine() {
			return getRuleContexts(VariableDefineContext.class);
		}
		public VariableDefineContext variableDefine(int i) {
			return getRuleContext(VariableDefineContext.class,i);
		}
		public ClassDefineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDefine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).enterClassDefine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).exitClassDefine(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxxParserVisitor ) return ((MxxParserVisitor<? extends T>)visitor).visitClassDefine(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassDefineContext classDefine() throws RecognitionException {
		ClassDefineContext _localctx = new ClassDefineContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_classDefine);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43);
			match(CLASS);
			setState(44);
			match(IDENTIFIER);
			setState(45);
			match(BRACE_L);
			setState(52);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << VOID) | (1L << BOOL) | (1L << INT) | (1L << STRING) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(50);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
				case 1:
					{
					setState(46);
					functionDefine();
					}
					break;
				case 2:
					{
					{
					setState(47);
					variableDefine();
					setState(48);
					match(SEMI);
					}
					}
					break;
				}
				}
				setState(54);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(55);
			match(BRACE_R);
			setState(56);
			match(SEMI);
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

	public static class FunctionDefineContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(MxxParser.IDENTIFIER, 0); }
		public TerminalNode PAREN_L() { return getToken(MxxParser.PAREN_L, 0); }
		public TerminalNode PAREN_R() { return getToken(MxxParser.PAREN_R, 0); }
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ArgumentListContext argumentList() {
			return getRuleContext(ArgumentListContext.class,0);
		}
		public FunctionDefineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDefine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).enterFunctionDefine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).exitFunctionDefine(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxxParserVisitor ) return ((MxxParserVisitor<? extends T>)visitor).visitFunctionDefine(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionDefineContext functionDefine() throws RecognitionException {
		FunctionDefineContext _localctx = new FunctionDefineContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_functionDefine);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(58);
				type();
				}
				break;
			}
			setState(61);
			match(IDENTIFIER);
			setState(62);
			match(PAREN_L);
			setState(64);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << VOID) | (1L << BOOL) | (1L << INT) | (1L << STRING) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(63);
				argumentList();
				}
			}

			setState(66);
			match(PAREN_R);
			setState(67);
			suite();
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

	public static class ArgumentListContext extends ParserRuleContext {
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<TerminalNode> IDENTIFIER() { return getTokens(MxxParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(MxxParser.IDENTIFIER, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MxxParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MxxParser.COMMA, i);
		}
		public ArgumentListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argumentList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).enterArgumentList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).exitArgumentList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxxParserVisitor ) return ((MxxParserVisitor<? extends T>)visitor).visitArgumentList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentListContext argumentList() throws RecognitionException {
		ArgumentListContext _localctx = new ArgumentListContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_argumentList);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			type();
			setState(70);
			match(IDENTIFIER);
			setState(77);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(71);
					match(COMMA);
					setState(72);
					type();
					setState(73);
					match(IDENTIFIER);
					}
					} 
				}
				setState(79);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			setState(81);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(80);
				match(COMMA);
				}
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

	public static class VariableDefineContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public List<VariableTermContext> variableTerm() {
			return getRuleContexts(VariableTermContext.class);
		}
		public VariableTermContext variableTerm(int i) {
			return getRuleContext(VariableTermContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MxxParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MxxParser.COMMA, i);
		}
		public VariableDefineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDefine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).enterVariableDefine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).exitVariableDefine(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxxParserVisitor ) return ((MxxParserVisitor<? extends T>)visitor).visitVariableDefine(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableDefineContext variableDefine() throws RecognitionException {
		VariableDefineContext _localctx = new VariableDefineContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_variableDefine);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			type();
			setState(84);
			variableTerm();
			setState(89);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(85);
					match(COMMA);
					setState(86);
					variableTerm();
					}
					} 
				}
				setState(91);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			}
			setState(93);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(92);
				match(COMMA);
				}
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

	public static class VariableTermContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(MxxParser.IDENTIFIER, 0); }
		public TerminalNode ASSIGN() { return getToken(MxxParser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public VariableTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableTerm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).enterVariableTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).exitVariableTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxxParserVisitor ) return ((MxxParserVisitor<? extends T>)visitor).visitVariableTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableTermContext variableTerm() throws RecognitionException {
		VariableTermContext _localctx = new VariableTermContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_variableTerm);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			match(IDENTIFIER);
			setState(98);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				setState(96);
				match(ASSIGN);
				setState(97);
				expression(0);
				}
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

	public static class TypeContext extends ParserRuleContext {
		public TerminalNode VOID() { return getToken(MxxParser.VOID, 0); }
		public TerminalNode BOOL() { return getToken(MxxParser.BOOL, 0); }
		public TerminalNode INT() { return getToken(MxxParser.INT, 0); }
		public TerminalNode STRING() { return getToken(MxxParser.STRING, 0); }
		public TerminalNode IDENTIFIER() { return getToken(MxxParser.IDENTIFIER, 0); }
		public List<BracketContext> bracket() {
			return getRuleContexts(BracketContext.class);
		}
		public BracketContext bracket(int i) {
			return getRuleContext(BracketContext.class,i);
		}
		public TerminalNode PAREN_L() { return getToken(MxxParser.PAREN_L, 0); }
		public TerminalNode PAREN_R() { return getToken(MxxParser.PAREN_R, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxxParserVisitor ) return ((MxxParserVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_type);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VOID:
				{
				setState(100);
				match(VOID);
				}
				break;
			case BOOL:
				{
				setState(101);
				match(BOOL);
				}
				break;
			case INT:
				{
				setState(102);
				match(INT);
				}
				break;
			case STRING:
				{
				setState(103);
				match(STRING);
				}
				break;
			case IDENTIFIER:
				{
				setState(104);
				match(IDENTIFIER);
				setState(107);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
				case 1:
					{
					setState(105);
					match(PAREN_L);
					setState(106);
					match(PAREN_R);
					}
					break;
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(114);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(111);
					bracket();
					}
					} 
				}
				setState(116);
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

	public static class BracketContext extends ParserRuleContext {
		public TerminalNode BRACK_L() { return getToken(MxxParser.BRACK_L, 0); }
		public TerminalNode BRACK_R() { return getToken(MxxParser.BRACK_R, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BracketContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bracket; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).enterBracket(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).exitBracket(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxxParserVisitor ) return ((MxxParserVisitor<? extends T>)visitor).visitBracket(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BracketContext bracket() throws RecognitionException {
		BracketContext _localctx = new BracketContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_bracket);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			match(BRACK_L);
			setState(119);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PAREN_L) | (1L << BRACK_L) | (1L << BANG) | (1L << ADD) | (1L << SUB) | (1L << INC) | (1L << DEC) | (1L << TILDE) | (1L << THIS) | (1L << NEW) | (1L << NULL) | (1L << TRUE) | (1L << FALSE) | (1L << IDENTIFIER) | (1L << DECIMAL_INTEGER) | (1L << STRING_CONSTANT))) != 0)) {
				{
				setState(118);
				expression(0);
				}
			}

			setState(121);
			match(BRACK_R);
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

	public static class SuiteContext extends ParserRuleContext {
		public TerminalNode BRACE_L() { return getToken(MxxParser.BRACE_L, 0); }
		public TerminalNode BRACE_R() { return getToken(MxxParser.BRACE_R, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public SuiteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_suite; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).enterSuite(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).exitSuite(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxxParserVisitor ) return ((MxxParserVisitor<? extends T>)visitor).visitSuite(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SuiteContext suite() throws RecognitionException {
		SuiteContext _localctx = new SuiteContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_suite);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123);
			match(BRACE_L);
			setState(127);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BRACE_L) | (1L << SEMI) | (1L << PAREN_L) | (1L << BRACK_L) | (1L << BANG) | (1L << ADD) | (1L << SUB) | (1L << INC) | (1L << DEC) | (1L << TILDE) | (1L << THIS) | (1L << NEW) | (1L << VOID) | (1L << BOOL) | (1L << INT) | (1L << STRING) | (1L << NULL) | (1L << TRUE) | (1L << FALSE) | (1L << IF) | (1L << FOR) | (1L << WHILE) | (1L << CONTINUE) | (1L << BREAK) | (1L << RETURN) | (1L << IDENTIFIER) | (1L << DECIMAL_INTEGER) | (1L << STRING_CONSTANT))) != 0)) {
				{
				{
				setState(124);
				statement();
				}
				}
				setState(129);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(130);
			match(BRACE_R);
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

	public static class StatementContext extends ParserRuleContext {
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	 
		public StatementContext() { }
		public void copyFrom(StatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ForStmtContext extends StatementContext {
		public ExpressionContext initialExpr;
		public ExpressionContext forCondExpr;
		public ExpressionContext stepExpr;
		public TerminalNode FOR() { return getToken(MxxParser.FOR, 0); }
		public TerminalNode PAREN_L() { return getToken(MxxParser.PAREN_L, 0); }
		public List<TerminalNode> SEMI() { return getTokens(MxxParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(MxxParser.SEMI, i);
		}
		public TerminalNode PAREN_R() { return getToken(MxxParser.PAREN_R, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public VariableDefineContext variableDefine() {
			return getRuleContext(VariableDefineContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ForStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).enterForStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).exitForStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxxParserVisitor ) return ((MxxParserVisitor<? extends T>)visitor).visitForStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VariableStmtContext extends StatementContext {
		public VariableDefineContext variableDefine() {
			return getRuleContext(VariableDefineContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(MxxParser.SEMI, 0); }
		public VariableStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).enterVariableStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).exitVariableStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxxParserVisitor ) return ((MxxParserVisitor<? extends T>)visitor).visitVariableStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class WhileStmtContext extends StatementContext {
		public TerminalNode WHILE() { return getToken(MxxParser.WHILE, 0); }
		public TerminalNode PAREN_L() { return getToken(MxxParser.PAREN_L, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode PAREN_R() { return getToken(MxxParser.PAREN_R, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public WhileStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).enterWhileStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).exitWhileStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxxParserVisitor ) return ((MxxParserVisitor<? extends T>)visitor).visitWhileStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfStmtContext extends StatementContext {
		public StatementContext trueBranch;
		public StatementContext falseBranch;
		public TerminalNode IF() { return getToken(MxxParser.IF, 0); }
		public TerminalNode PAREN_L() { return getToken(MxxParser.PAREN_L, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode PAREN_R() { return getToken(MxxParser.PAREN_R, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(MxxParser.ELSE, 0); }
		public IfStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).enterIfStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).exitIfStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxxParserVisitor ) return ((MxxParserVisitor<? extends T>)visitor).visitIfStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BreakStmtContext extends StatementContext {
		public TerminalNode BREAK() { return getToken(MxxParser.BREAK, 0); }
		public TerminalNode SEMI() { return getToken(MxxParser.SEMI, 0); }
		public BreakStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).enterBreakStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).exitBreakStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxxParserVisitor ) return ((MxxParserVisitor<? extends T>)visitor).visitBreakStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SingleExprStmtContext extends StatementContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(MxxParser.SEMI, 0); }
		public SingleExprStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).enterSingleExprStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).exitSingleExprStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxxParserVisitor ) return ((MxxParserVisitor<? extends T>)visitor).visitSingleExprStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EmptyStmtContext extends StatementContext {
		public TerminalNode SEMI() { return getToken(MxxParser.SEMI, 0); }
		public EmptyStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).enterEmptyStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).exitEmptyStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxxParserVisitor ) return ((MxxParserVisitor<? extends T>)visitor).visitEmptyStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ReturnStmtContext extends StatementContext {
		public TerminalNode RETURN() { return getToken(MxxParser.RETURN, 0); }
		public TerminalNode SEMI() { return getToken(MxxParser.SEMI, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).enterReturnStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).exitReturnStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxxParserVisitor ) return ((MxxParserVisitor<? extends T>)visitor).visitReturnStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ContinueStmtContext extends StatementContext {
		public TerminalNode CONTINUE() { return getToken(MxxParser.CONTINUE, 0); }
		public TerminalNode SEMI() { return getToken(MxxParser.SEMI, 0); }
		public ContinueStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).enterContinueStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).exitContinueStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxxParserVisitor ) return ((MxxParserVisitor<? extends T>)visitor).visitContinueStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SuiteStmtContext extends StatementContext {
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public SuiteStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).enterSuiteStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).exitSuiteStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxxParserVisitor ) return ((MxxParserVisitor<? extends T>)visitor).visitSuiteStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_statement);
		int _la;
		try {
			setState(180);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				_localctx = new SuiteStmtContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(132);
				suite();
				}
				break;
			case 2:
				_localctx = new IfStmtContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(133);
				match(IF);
				setState(134);
				match(PAREN_L);
				setState(135);
				expression(0);
				setState(136);
				match(PAREN_R);
				setState(137);
				((IfStmtContext)_localctx).trueBranch = statement();
				setState(140);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
				case 1:
					{
					setState(138);
					match(ELSE);
					setState(139);
					((IfStmtContext)_localctx).falseBranch = statement();
					}
					break;
				}
				}
				break;
			case 3:
				_localctx = new ForStmtContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(142);
				match(FOR);
				setState(143);
				match(PAREN_L);
				setState(146);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
				case 1:
					{
					{
					setState(144);
					((ForStmtContext)_localctx).initialExpr = expression(0);
					}
					}
					break;
				case 2:
					{
					setState(145);
					variableDefine();
					}
					break;
				}
				setState(148);
				match(SEMI);
				setState(150);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PAREN_L) | (1L << BRACK_L) | (1L << BANG) | (1L << ADD) | (1L << SUB) | (1L << INC) | (1L << DEC) | (1L << TILDE) | (1L << THIS) | (1L << NEW) | (1L << NULL) | (1L << TRUE) | (1L << FALSE) | (1L << IDENTIFIER) | (1L << DECIMAL_INTEGER) | (1L << STRING_CONSTANT))) != 0)) {
					{
					setState(149);
					((ForStmtContext)_localctx).forCondExpr = expression(0);
					}
				}

				setState(152);
				match(SEMI);
				setState(154);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PAREN_L) | (1L << BRACK_L) | (1L << BANG) | (1L << ADD) | (1L << SUB) | (1L << INC) | (1L << DEC) | (1L << TILDE) | (1L << THIS) | (1L << NEW) | (1L << NULL) | (1L << TRUE) | (1L << FALSE) | (1L << IDENTIFIER) | (1L << DECIMAL_INTEGER) | (1L << STRING_CONSTANT))) != 0)) {
					{
					setState(153);
					((ForStmtContext)_localctx).stepExpr = expression(0);
					}
				}

				setState(156);
				match(PAREN_R);
				setState(157);
				statement();
				}
				break;
			case 4:
				_localctx = new WhileStmtContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(158);
				match(WHILE);
				setState(159);
				match(PAREN_L);
				setState(160);
				expression(0);
				setState(161);
				match(PAREN_R);
				setState(162);
				statement();
				}
				break;
			case 5:
				_localctx = new ContinueStmtContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(164);
				match(CONTINUE);
				setState(165);
				match(SEMI);
				}
				break;
			case 6:
				_localctx = new BreakStmtContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(166);
				match(BREAK);
				setState(167);
				match(SEMI);
				}
				break;
			case 7:
				_localctx = new ReturnStmtContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(168);
				match(RETURN);
				setState(170);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PAREN_L) | (1L << BRACK_L) | (1L << BANG) | (1L << ADD) | (1L << SUB) | (1L << INC) | (1L << DEC) | (1L << TILDE) | (1L << THIS) | (1L << NEW) | (1L << NULL) | (1L << TRUE) | (1L << FALSE) | (1L << IDENTIFIER) | (1L << DECIMAL_INTEGER) | (1L << STRING_CONSTANT))) != 0)) {
					{
					setState(169);
					expression(0);
					}
				}

				setState(172);
				match(SEMI);
				}
				break;
			case 8:
				_localctx = new SingleExprStmtContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(173);
				expression(0);
				setState(174);
				match(SEMI);
				}
				break;
			case 9:
				_localctx = new VariableStmtContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(176);
				variableDefine();
				setState(177);
				match(SEMI);
				}
				break;
			case 10:
				_localctx = new EmptyStmtContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(179);
				match(SEMI);
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

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class NewExprContext extends ExpressionContext {
		public TerminalNode NEW() { return getToken(MxxParser.NEW, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public NewExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).enterNewExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).exitNewExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxxParserVisitor ) return ((MxxParserVisitor<? extends T>)visitor).visitNewExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UnaryExprContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode INC() { return getToken(MxxParser.INC, 0); }
		public TerminalNode DEC() { return getToken(MxxParser.DEC, 0); }
		public TerminalNode BANG() { return getToken(MxxParser.BANG, 0); }
		public TerminalNode TILDE() { return getToken(MxxParser.TILDE, 0); }
		public TerminalNode ADD() { return getToken(MxxParser.ADD, 0); }
		public TerminalNode SUB() { return getToken(MxxParser.SUB, 0); }
		public UnaryExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).enterUnaryExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).exitUnaryExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxxParserVisitor ) return ((MxxParserVisitor<? extends T>)visitor).visitUnaryExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrayExprContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<BracketContext> bracket() {
			return getRuleContexts(BracketContext.class);
		}
		public BracketContext bracket(int i) {
			return getRuleContext(BracketContext.class,i);
		}
		public ArrayExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).enterArrayExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).exitArrayExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxxParserVisitor ) return ((MxxParserVisitor<? extends T>)visitor).visitArrayExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LambdaExprContext extends ExpressionContext {
		public TerminalNode BRACK_L() { return getToken(MxxParser.BRACK_L, 0); }
		public TerminalNode BIT_AND() { return getToken(MxxParser.BIT_AND, 0); }
		public TerminalNode BRACK_R() { return getToken(MxxParser.BRACK_R, 0); }
		public List<TerminalNode> PAREN_L() { return getTokens(MxxParser.PAREN_L); }
		public TerminalNode PAREN_L(int i) {
			return getToken(MxxParser.PAREN_L, i);
		}
		public List<TerminalNode> PAREN_R() { return getTokens(MxxParser.PAREN_R); }
		public TerminalNode PAREN_R(int i) {
			return getToken(MxxParser.PAREN_R, i);
		}
		public TerminalNode ARROW() { return getToken(MxxParser.ARROW, 0); }
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public ArgumentListContext argumentList() {
			return getRuleContext(ArgumentListContext.class,0);
		}
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public LambdaExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).enterLambdaExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).exitLambdaExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxxParserVisitor ) return ((MxxParserVisitor<? extends T>)visitor).visitLambdaExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MemberExprContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode DOT() { return getToken(MxxParser.DOT, 0); }
		public TerminalNode IDENTIFIER() { return getToken(MxxParser.IDENTIFIER, 0); }
		public MemberExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).enterMemberExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).exitMemberExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxxParserVisitor ) return ((MxxParserVisitor<? extends T>)visitor).visitMemberExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SelfExprContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode INC() { return getToken(MxxParser.INC, 0); }
		public TerminalNode DEC() { return getToken(MxxParser.DEC, 0); }
		public SelfExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).enterSelfExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).exitSelfExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxxParserVisitor ) return ((MxxParserVisitor<? extends T>)visitor).visitSelfExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AtomExprContext extends ExpressionContext {
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public AtomExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).enterAtomExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).exitAtomExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxxParserVisitor ) return ((MxxParserVisitor<? extends T>)visitor).visitAtomExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BinaryExprContext extends ExpressionContext {
		public ExpressionContext lTerm;
		public Token op;
		public ExpressionContext rTerm;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode MUL() { return getToken(MxxParser.MUL, 0); }
		public TerminalNode DIV() { return getToken(MxxParser.DIV, 0); }
		public TerminalNode MOD() { return getToken(MxxParser.MOD, 0); }
		public TerminalNode ADD() { return getToken(MxxParser.ADD, 0); }
		public TerminalNode SUB() { return getToken(MxxParser.SUB, 0); }
		public TerminalNode SHIFT_L() { return getToken(MxxParser.SHIFT_L, 0); }
		public TerminalNode SHIFT_R() { return getToken(MxxParser.SHIFT_R, 0); }
		public TerminalNode GT() { return getToken(MxxParser.GT, 0); }
		public TerminalNode LT() { return getToken(MxxParser.LT, 0); }
		public TerminalNode GE() { return getToken(MxxParser.GE, 0); }
		public TerminalNode LE() { return getToken(MxxParser.LE, 0); }
		public TerminalNode EQ() { return getToken(MxxParser.EQ, 0); }
		public TerminalNode NOT_EQ() { return getToken(MxxParser.NOT_EQ, 0); }
		public TerminalNode BIT_AND() { return getToken(MxxParser.BIT_AND, 0); }
		public TerminalNode BIT_OR() { return getToken(MxxParser.BIT_OR, 0); }
		public TerminalNode CARET() { return getToken(MxxParser.CARET, 0); }
		public TerminalNode AND() { return getToken(MxxParser.AND, 0); }
		public TerminalNode OR() { return getToken(MxxParser.OR, 0); }
		public BinaryExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).enterBinaryExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).exitBinaryExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxxParserVisitor ) return ((MxxParserVisitor<? extends T>)visitor).visitBinaryExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignExprContext extends ExpressionContext {
		public ExpressionContext lValue;
		public ExpressionContext rValue;
		public TerminalNode ASSIGN() { return getToken(MxxParser.ASSIGN, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public AssignExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).enterAssignExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).exitAssignExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxxParserVisitor ) return ((MxxParserVisitor<? extends T>)visitor).visitAssignExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParenExprContext extends ExpressionContext {
		public TerminalNode PAREN_L() { return getToken(MxxParser.PAREN_L, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode PAREN_R() { return getToken(MxxParser.PAREN_R, 0); }
		public ParenExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).enterParenExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).exitParenExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxxParserVisitor ) return ((MxxParserVisitor<? extends T>)visitor).visitParenExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FunctionExprContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode PAREN_L() { return getToken(MxxParser.PAREN_L, 0); }
		public TerminalNode PAREN_R() { return getToken(MxxParser.PAREN_R, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public FunctionExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).enterFunctionExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).exitFunctionExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxxParserVisitor ) return ((MxxParserVisitor<? extends T>)visitor).visitFunctionExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 22;
		enterRecursionRule(_localctx, 22, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(212);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PAREN_L:
				{
				_localctx = new ParenExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(183);
				match(PAREN_L);
				setState(184);
				expression(0);
				setState(185);
				match(PAREN_R);
				}
				break;
			case THIS:
			case NULL:
			case TRUE:
			case FALSE:
			case IDENTIFIER:
			case DECIMAL_INTEGER:
			case STRING_CONSTANT:
				{
				_localctx = new AtomExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(187);
				atom();
				}
				break;
			case NEW:
				{
				_localctx = new NewExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(188);
				match(NEW);
				setState(189);
				type();
				}
				break;
			case INC:
			case DEC:
				{
				_localctx = new UnaryExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(190);
				((UnaryExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==INC || _la==DEC) ) {
					((UnaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(191);
				expression(14);
				}
				break;
			case BANG:
			case TILDE:
				{
				_localctx = new UnaryExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(192);
				((UnaryExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==BANG || _la==TILDE) ) {
					((UnaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(193);
				expression(13);
				}
				break;
			case ADD:
			case SUB:
				{
				_localctx = new UnaryExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(194);
				((UnaryExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==ADD || _la==SUB) ) {
					((UnaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(195);
				expression(12);
				}
				break;
			case BRACK_L:
				{
				_localctx = new LambdaExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(196);
				match(BRACK_L);
				setState(197);
				match(BIT_AND);
				setState(198);
				match(BRACK_R);
				setState(199);
				match(PAREN_L);
				setState(201);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << VOID) | (1L << BOOL) | (1L << INT) | (1L << STRING) | (1L << IDENTIFIER))) != 0)) {
					{
					setState(200);
					argumentList();
					}
				}

				setState(203);
				match(PAREN_R);
				setState(204);
				match(ARROW);
				setState(205);
				suite();
				setState(206);
				match(PAREN_L);
				setState(208);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PAREN_L) | (1L << BRACK_L) | (1L << BANG) | (1L << ADD) | (1L << SUB) | (1L << INC) | (1L << DEC) | (1L << TILDE) | (1L << THIS) | (1L << NEW) | (1L << NULL) | (1L << TRUE) | (1L << FALSE) | (1L << IDENTIFIER) | (1L << DECIMAL_INTEGER) | (1L << STRING_CONSTANT))) != 0)) {
					{
					setState(207);
					expressionList();
					}
				}

				setState(210);
				match(PAREN_R);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(263);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(261);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
					case 1:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).lTerm = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(214);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(215);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MUL) | (1L << DIV) | (1L << MOD))) != 0)) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(216);
						((BinaryExprContext)_localctx).rTerm = expression(12);
						}
						break;
					case 2:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).lTerm = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(217);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(218);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==ADD || _la==SUB) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(219);
						((BinaryExprContext)_localctx).rTerm = expression(11);
						}
						break;
					case 3:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).lTerm = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(220);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(221);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==SHIFT_L || _la==SHIFT_R) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(222);
						((BinaryExprContext)_localctx).rTerm = expression(10);
						}
						break;
					case 4:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).lTerm = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(223);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(224);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQ) | (1L << NOT_EQ) | (1L << LT) | (1L << LE) | (1L << GT) | (1L << GE))) != 0)) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(225);
						((BinaryExprContext)_localctx).rTerm = expression(9);
						}
						break;
					case 5:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).lTerm = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(226);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(227);
						((BinaryExprContext)_localctx).op = match(BIT_AND);
						setState(228);
						((BinaryExprContext)_localctx).rTerm = expression(8);
						}
						break;
					case 6:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).lTerm = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(229);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(230);
						((BinaryExprContext)_localctx).op = match(BIT_OR);
						setState(231);
						((BinaryExprContext)_localctx).rTerm = expression(7);
						}
						break;
					case 7:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).lTerm = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(232);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(233);
						((BinaryExprContext)_localctx).op = match(CARET);
						setState(234);
						((BinaryExprContext)_localctx).rTerm = expression(6);
						}
						break;
					case 8:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).lTerm = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(235);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(236);
						((BinaryExprContext)_localctx).op = match(AND);
						setState(237);
						((BinaryExprContext)_localctx).rTerm = expression(5);
						}
						break;
					case 9:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).lTerm = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(238);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(239);
						((BinaryExprContext)_localctx).op = match(OR);
						setState(240);
						((BinaryExprContext)_localctx).rTerm = expression(4);
						}
						break;
					case 10:
						{
						_localctx = new AssignExprContext(new ExpressionContext(_parentctx, _parentState));
						((AssignExprContext)_localctx).lValue = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(241);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(242);
						match(ASSIGN);
						setState(243);
						((AssignExprContext)_localctx).rValue = expression(2);
						}
						break;
					case 11:
						{
						_localctx = new MemberExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(244);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(245);
						match(DOT);
						setState(246);
						match(IDENTIFIER);
						}
						break;
					case 12:
						{
						_localctx = new ArrayExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(247);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(249); 
						_errHandler.sync(this);
						_alt = 1;
						do {
							switch (_alt) {
							case 1:
								{
								{
								setState(248);
								bracket();
								}
								}
								break;
							default:
								throw new NoViableAltException(this);
							}
							setState(251); 
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
						} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
						}
						break;
					case 13:
						{
						_localctx = new FunctionExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(253);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(254);
						match(PAREN_L);
						setState(256);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PAREN_L) | (1L << BRACK_L) | (1L << BANG) | (1L << ADD) | (1L << SUB) | (1L << INC) | (1L << DEC) | (1L << TILDE) | (1L << THIS) | (1L << NEW) | (1L << NULL) | (1L << TRUE) | (1L << FALSE) | (1L << IDENTIFIER) | (1L << DECIMAL_INTEGER) | (1L << STRING_CONSTANT))) != 0)) {
							{
							setState(255);
							expressionList();
							}
						}

						setState(258);
						match(PAREN_R);
						}
						break;
					case 14:
						{
						_localctx = new SelfExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(259);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(260);
						((SelfExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==INC || _la==DEC) ) {
							((SelfExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					}
					} 
				}
				setState(265);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ExpressionListContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MxxParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MxxParser.COMMA, i);
		}
		public ExpressionListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).enterExpressionList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).exitExpressionList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxxParserVisitor ) return ((MxxParserVisitor<? extends T>)visitor).visitExpressionList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionListContext expressionList() throws RecognitionException {
		ExpressionListContext _localctx = new ExpressionListContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_expressionList);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(266);
			expression(0);
			setState(271);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(267);
					match(COMMA);
					setState(268);
					expression(0);
					}
					} 
				}
				setState(273);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
			}
			setState(275);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(274);
				match(COMMA);
				}
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

	public static class AtomContext extends ParserRuleContext {
		public TerminalNode THIS() { return getToken(MxxParser.THIS, 0); }
		public TerminalNode IDENTIFIER() { return getToken(MxxParser.IDENTIFIER, 0); }
		public TerminalNode STRING_CONSTANT() { return getToken(MxxParser.STRING_CONSTANT, 0); }
		public TerminalNode DECIMAL_INTEGER() { return getToken(MxxParser.DECIMAL_INTEGER, 0); }
		public TerminalNode TRUE() { return getToken(MxxParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(MxxParser.FALSE, 0); }
		public TerminalNode NULL() { return getToken(MxxParser.NULL, 0); }
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).exitAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxxParserVisitor ) return ((MxxParserVisitor<? extends T>)visitor).visitAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_atom);
		int _la;
		try {
			setState(283);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case THIS:
				enterOuterAlt(_localctx, 1);
				{
				setState(277);
				match(THIS);
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(278);
				match(IDENTIFIER);
				}
				break;
			case STRING_CONSTANT:
				enterOuterAlt(_localctx, 3);
				{
				setState(279);
				match(STRING_CONSTANT);
				}
				break;
			case DECIMAL_INTEGER:
				enterOuterAlt(_localctx, 4);
				{
				setState(280);
				match(DECIMAL_INTEGER);
				}
				break;
			case TRUE:
			case FALSE:
				enterOuterAlt(_localctx, 5);
				{
				setState(281);
				_la = _input.LA(1);
				if ( !(_la==TRUE || _la==FALSE) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case NULL:
				enterOuterAlt(_localctx, 6);
				{
				setState(282);
				match(NULL);
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 11:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 11);
		case 1:
			return precpred(_ctx, 10);
		case 2:
			return precpred(_ctx, 9);
		case 3:
			return precpred(_ctx, 8);
		case 4:
			return precpred(_ctx, 7);
		case 5:
			return precpred(_ctx, 6);
		case 6:
			return precpred(_ctx, 5);
		case 7:
			return precpred(_ctx, 4);
		case 8:
			return precpred(_ctx, 3);
		case 9:
			return precpred(_ctx, 2);
		case 10:
			return precpred(_ctx, 19);
		case 11:
			return precpred(_ctx, 18);
		case 12:
			return precpred(_ctx, 17);
		case 13:
			return precpred(_ctx, 15);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3;\u0120\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\7\2 \n\2\f\2\16\2#\13\2\3"+
		"\2\3\2\3\3\3\3\3\3\3\3\3\3\5\3,\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4\65"+
		"\n\4\f\4\16\48\13\4\3\4\3\4\3\4\3\5\5\5>\n\5\3\5\3\5\3\5\5\5C\n\5\3\5"+
		"\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\7\6N\n\6\f\6\16\6Q\13\6\3\6\5\6T\n\6"+
		"\3\7\3\7\3\7\3\7\7\7Z\n\7\f\7\16\7]\13\7\3\7\5\7`\n\7\3\b\3\b\3\b\5\b"+
		"e\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\tn\n\t\5\tp\n\t\3\t\7\ts\n\t\f\t\16"+
		"\tv\13\t\3\n\3\n\5\nz\n\n\3\n\3\n\3\13\3\13\7\13\u0080\n\13\f\13\16\13"+
		"\u0083\13\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u008f\n\f\3"+
		"\f\3\f\3\f\3\f\5\f\u0095\n\f\3\f\3\f\5\f\u0099\n\f\3\f\3\f\5\f\u009d\n"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00ad\n"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00b7\n\f\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u00cc\n\r\3"+
		"\r\3\r\3\r\3\r\3\r\5\r\u00d3\n\r\3\r\3\r\5\r\u00d7\n\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\6\r\u00fc\n\r\r"+
		"\r\16\r\u00fd\3\r\3\r\3\r\5\r\u0103\n\r\3\r\3\r\3\r\7\r\u0108\n\r\f\r"+
		"\16\r\u010b\13\r\3\16\3\16\3\16\7\16\u0110\n\16\f\16\16\16\u0113\13\16"+
		"\3\16\5\16\u0116\n\16\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u011e\n\17\3"+
		"\17\2\3\30\20\2\4\6\b\n\f\16\20\22\24\26\30\32\34\2\t\3\2\36\37\4\2\17"+
		"\17  \3\2\31\32\3\2\33\35\3\2$%\3\2\21\26\3\2./\2\u0153\2!\3\2\2\2\4+"+
		"\3\2\2\2\6-\3\2\2\2\b=\3\2\2\2\nG\3\2\2\2\fU\3\2\2\2\16a\3\2\2\2\20o\3"+
		"\2\2\2\22w\3\2\2\2\24}\3\2\2\2\26\u00b6\3\2\2\2\30\u00d6\3\2\2\2\32\u010c"+
		"\3\2\2\2\34\u011d\3\2\2\2\36 \5\4\3\2\37\36\3\2\2\2 #\3\2\2\2!\37\3\2"+
		"\2\2!\"\3\2\2\2\"$\3\2\2\2#!\3\2\2\2$%\7\2\2\3%\3\3\2\2\2&,\5\6\4\2\'"+
		",\5\b\5\2()\5\f\7\2)*\7\7\2\2*,\3\2\2\2+&\3\2\2\2+\'\3\2\2\2+(\3\2\2\2"+
		",\5\3\2\2\2-.\7&\2\2./\79\2\2/\66\7\5\2\2\60\65\5\b\5\2\61\62\5\f\7\2"+
		"\62\63\7\7\2\2\63\65\3\2\2\2\64\60\3\2\2\2\64\61\3\2\2\2\658\3\2\2\2\66"+
		"\64\3\2\2\2\66\67\3\2\2\2\679\3\2\2\28\66\3\2\2\29:\7\6\2\2:;\7\7\2\2"+
		";\7\3\2\2\2<>\5\20\t\2=<\3\2\2\2=>\3\2\2\2>?\3\2\2\2?@\79\2\2@B\7\b\2"+
		"\2AC\5\n\6\2BA\3\2\2\2BC\3\2\2\2CD\3\2\2\2DE\7\t\2\2EF\5\24\13\2F\t\3"+
		"\2\2\2GH\5\20\t\2HO\79\2\2IJ\7\n\2\2JK\5\20\t\2KL\79\2\2LN\3\2\2\2MI\3"+
		"\2\2\2NQ\3\2\2\2OM\3\2\2\2OP\3\2\2\2PS\3\2\2\2QO\3\2\2\2RT\7\n\2\2SR\3"+
		"\2\2\2ST\3\2\2\2T\13\3\2\2\2UV\5\20\t\2V[\5\16\b\2WX\7\n\2\2XZ\5\16\b"+
		"\2YW\3\2\2\2Z]\3\2\2\2[Y\3\2\2\2[\\\3\2\2\2\\_\3\2\2\2][\3\2\2\2^`\7\n"+
		"\2\2_^\3\2\2\2_`\3\2\2\2`\r\3\2\2\2ad\79\2\2bc\7\20\2\2ce\5\30\r\2db\3"+
		"\2\2\2de\3\2\2\2e\17\3\2\2\2fp\7)\2\2gp\7*\2\2hp\7+\2\2ip\7,\2\2jm\79"+
		"\2\2kl\7\b\2\2ln\7\t\2\2mk\3\2\2\2mn\3\2\2\2np\3\2\2\2of\3\2\2\2og\3\2"+
		"\2\2oh\3\2\2\2oi\3\2\2\2oj\3\2\2\2pt\3\2\2\2qs\5\22\n\2rq\3\2\2\2sv\3"+
		"\2\2\2tr\3\2\2\2tu\3\2\2\2u\21\3\2\2\2vt\3\2\2\2wy\7\r\2\2xz\5\30\r\2"+
		"yx\3\2\2\2yz\3\2\2\2z{\3\2\2\2{|\7\16\2\2|\23\3\2\2\2}\u0081\7\5\2\2~"+
		"\u0080\5\26\f\2\177~\3\2\2\2\u0080\u0083\3\2\2\2\u0081\177\3\2\2\2\u0081"+
		"\u0082\3\2\2\2\u0082\u0084\3\2\2\2\u0083\u0081\3\2\2\2\u0084\u0085\7\6"+
		"\2\2\u0085\25\3\2\2\2\u0086\u00b7\5\24\13\2\u0087\u0088\7\60\2\2\u0088"+
		"\u0089\7\b\2\2\u0089\u008a\5\30\r\2\u008a\u008b\7\t\2\2\u008b\u008e\5"+
		"\26\f\2\u008c\u008d\7\61\2\2\u008d\u008f\5\26\f\2\u008e\u008c\3\2\2\2"+
		"\u008e\u008f\3\2\2\2\u008f\u00b7\3\2\2\2\u0090\u0091\7\62\2\2\u0091\u0094"+
		"\7\b\2\2\u0092\u0095\5\30\r\2\u0093\u0095\5\f\7\2\u0094\u0092\3\2\2\2"+
		"\u0094\u0093\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0096\3\2\2\2\u0096\u0098"+
		"\7\7\2\2\u0097\u0099\5\30\r\2\u0098\u0097\3\2\2\2\u0098\u0099\3\2\2\2"+
		"\u0099\u009a\3\2\2\2\u009a\u009c\7\7\2\2\u009b\u009d\5\30\r\2\u009c\u009b"+
		"\3\2\2\2\u009c\u009d\3\2\2\2\u009d\u009e\3\2\2\2\u009e\u009f\7\t\2\2\u009f"+
		"\u00b7\5\26\f\2\u00a0\u00a1\7\63\2\2\u00a1\u00a2\7\b\2\2\u00a2\u00a3\5"+
		"\30\r\2\u00a3\u00a4\7\t\2\2\u00a4\u00a5\5\26\f\2\u00a5\u00b7\3\2\2\2\u00a6"+
		"\u00a7\7\64\2\2\u00a7\u00b7\7\7\2\2\u00a8\u00a9\7\65\2\2\u00a9\u00b7\7"+
		"\7\2\2\u00aa\u00ac\7\66\2\2\u00ab\u00ad\5\30\r\2\u00ac\u00ab\3\2\2\2\u00ac"+
		"\u00ad\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae\u00b7\7\7\2\2\u00af\u00b0\5\30"+
		"\r\2\u00b0\u00b1\7\7\2\2\u00b1\u00b7\3\2\2\2\u00b2\u00b3\5\f\7\2\u00b3"+
		"\u00b4\7\7\2\2\u00b4\u00b7\3\2\2\2\u00b5\u00b7\7\7\2\2\u00b6\u0086\3\2"+
		"\2\2\u00b6\u0087\3\2\2\2\u00b6\u0090\3\2\2\2\u00b6\u00a0\3\2\2\2\u00b6"+
		"\u00a6\3\2\2\2\u00b6\u00a8\3\2\2\2\u00b6\u00aa\3\2\2\2\u00b6\u00af\3\2"+
		"\2\2\u00b6\u00b2\3\2\2\2\u00b6\u00b5\3\2\2\2\u00b7\27\3\2\2\2\u00b8\u00b9"+
		"\b\r\1\2\u00b9\u00ba\7\b\2\2\u00ba\u00bb\5\30\r\2\u00bb\u00bc\7\t\2\2"+
		"\u00bc\u00d7\3\2\2\2\u00bd\u00d7\5\34\17\2\u00be\u00bf\7(\2\2\u00bf\u00d7"+
		"\5\20\t\2\u00c0\u00c1\t\2\2\2\u00c1\u00d7\5\30\r\20\u00c2\u00c3\t\3\2"+
		"\2\u00c3\u00d7\5\30\r\17\u00c4\u00c5\t\4\2\2\u00c5\u00d7\5\30\r\16\u00c6"+
		"\u00c7\7\r\2\2\u00c7\u00c8\7!\2\2\u00c8\u00c9\7\16\2\2\u00c9\u00cb\7\b"+
		"\2\2\u00ca\u00cc\5\n\6\2\u00cb\u00ca\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc"+
		"\u00cd\3\2\2\2\u00cd\u00ce\7\t\2\2\u00ce\u00cf\7\f\2\2\u00cf\u00d0\5\24"+
		"\13\2\u00d0\u00d2\7\b\2\2\u00d1\u00d3\5\32\16\2\u00d2\u00d1\3\2\2\2\u00d2"+
		"\u00d3\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d4\u00d5\7\t\2\2\u00d5\u00d7\3\2"+
		"\2\2\u00d6\u00b8\3\2\2\2\u00d6\u00bd\3\2\2\2\u00d6\u00be\3\2\2\2\u00d6"+
		"\u00c0\3\2\2\2\u00d6\u00c2\3\2\2\2\u00d6\u00c4\3\2\2\2\u00d6\u00c6\3\2"+
		"\2\2\u00d7\u0109\3\2\2\2\u00d8\u00d9\f\r\2\2\u00d9\u00da\t\5\2\2\u00da"+
		"\u0108\5\30\r\16\u00db\u00dc\f\f\2\2\u00dc\u00dd\t\4\2\2\u00dd\u0108\5"+
		"\30\r\r\u00de\u00df\f\13\2\2\u00df\u00e0\t\6\2\2\u00e0\u0108\5\30\r\f"+
		"\u00e1\u00e2\f\n\2\2\u00e2\u00e3\t\7\2\2\u00e3\u0108\5\30\r\13\u00e4\u00e5"+
		"\f\t\2\2\u00e5\u00e6\7!\2\2\u00e6\u0108\5\30\r\n\u00e7\u00e8\f\b\2\2\u00e8"+
		"\u00e9\7\"\2\2\u00e9\u0108\5\30\r\t\u00ea\u00eb\f\7\2\2\u00eb\u00ec\7"+
		"#\2\2\u00ec\u0108\5\30\r\b\u00ed\u00ee\f\6\2\2\u00ee\u00ef\7\27\2\2\u00ef"+
		"\u0108\5\30\r\7\u00f0\u00f1\f\5\2\2\u00f1\u00f2\7\30\2\2\u00f2\u0108\5"+
		"\30\r\6\u00f3\u00f4\f\4\2\2\u00f4\u00f5\7\20\2\2\u00f5\u0108\5\30\r\4"+
		"\u00f6\u00f7\f\25\2\2\u00f7\u00f8\7\13\2\2\u00f8\u0108\79\2\2\u00f9\u00fb"+
		"\f\24\2\2\u00fa\u00fc\5\22\n\2\u00fb\u00fa\3\2\2\2\u00fc\u00fd\3\2\2\2"+
		"\u00fd\u00fb\3\2\2\2\u00fd\u00fe\3\2\2\2\u00fe\u0108\3\2\2\2\u00ff\u0100"+
		"\f\23\2\2\u0100\u0102\7\b\2\2\u0101\u0103\5\32\16\2\u0102\u0101\3\2\2"+
		"\2\u0102\u0103\3\2\2\2\u0103\u0104\3\2\2\2\u0104\u0108\7\t\2\2\u0105\u0106"+
		"\f\21\2\2\u0106\u0108\t\2\2\2\u0107\u00d8\3\2\2\2\u0107\u00db\3\2\2\2"+
		"\u0107\u00de\3\2\2\2\u0107\u00e1\3\2\2\2\u0107\u00e4\3\2\2\2\u0107\u00e7"+
		"\3\2\2\2\u0107\u00ea\3\2\2\2\u0107\u00ed\3\2\2\2\u0107\u00f0\3\2\2\2\u0107"+
		"\u00f3\3\2\2\2\u0107\u00f6\3\2\2\2\u0107\u00f9\3\2\2\2\u0107\u00ff\3\2"+
		"\2\2\u0107\u0105\3\2\2\2\u0108\u010b\3\2\2\2\u0109\u0107\3\2\2\2\u0109"+
		"\u010a\3\2\2\2\u010a\31\3\2\2\2\u010b\u0109\3\2\2\2\u010c\u0111\5\30\r"+
		"\2\u010d\u010e\7\n\2\2\u010e\u0110\5\30\r\2\u010f\u010d\3\2\2\2\u0110"+
		"\u0113\3\2\2\2\u0111\u010f\3\2\2\2\u0111\u0112\3\2\2\2\u0112\u0115\3\2"+
		"\2\2\u0113\u0111\3\2\2\2\u0114\u0116\7\n\2\2\u0115\u0114\3\2\2\2\u0115"+
		"\u0116\3\2\2\2\u0116\33\3\2\2\2\u0117\u011e\7\'\2\2\u0118\u011e\79\2\2"+
		"\u0119\u011e\7;\2\2\u011a\u011e\7:\2\2\u011b\u011e\t\b\2\2\u011c\u011e"+
		"\7-\2\2\u011d\u0117\3\2\2\2\u011d\u0118\3\2\2\2\u011d\u0119\3\2\2\2\u011d"+
		"\u011a\3\2\2\2\u011d\u011b\3\2\2\2\u011d\u011c\3\2\2\2\u011e\35\3\2\2"+
		"\2\"!+\64\66=BOS[_dmoty\u0081\u008e\u0094\u0098\u009c\u00ac\u00b6\u00cb"+
		"\u00d2\u00d6\u00fd\u0102\u0107\u0109\u0111\u0115\u011d";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}