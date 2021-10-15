// Generated from F:/WorkSpace/@Java/Mxx-Compiler/src/parser\MxxParser.g4 by ANTLR 4.9.1
package parser;
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
		BLOCK_COMMENT=1, LINE_COMMENT=2, PAREN_L=3, PAREN_R=4, BRACE_L=5, BRACE_R=6, 
		BRACK_L=7, BRACK_R=8, SEMI=9, COMMA=10, DOT=11, BANG=12, ASSIGN=13, EQ=14, 
		NOT_EQ=15, LT=16, LE=17, GT=18, GE=19, AND=20, OR=21, ADD=22, SUB=23, 
		MUL=24, DIV=25, MOD=26, INC=27, DEC=28, TILDE=29, BIT_AND=30, BIT_OR=31, 
		CARET=32, SHIFT_L=33, SHIFT_R=34, CLASS=35, THIS=36, NEW=37, VOID=38, 
		BOOL=39, INT=40, STRING=41, NULL=42, TRUE=43, FALSE=44, IF=45, ELSE=46, 
		FOR=47, WHILE=48, CONTINUE=49, BREAK=50, RETURN=51, WHITE_SPACE=52, NEW_LINE=53, 
		IDENTIFIER=54, DECIMAL_INTEGER=55, STRING_CONSTANT=56;
	public static final int
		RULE_program = 0, RULE_programSection = 1, RULE_globalVariableDefine = 2, 
		RULE_classDefine = 3, RULE_constructorDef = 4, RULE_functionDefine = 5, 
		RULE_functionType = 6, RULE_constructorDefine = 7, RULE_argumentList = 8, 
		RULE_argument = 9, RULE_variableDefine = 10, RULE_variableTerm = 11, RULE_type = 12, 
		RULE_notArrayType = 13, RULE_suite = 14, RULE_statement = 15, RULE_expression = 16, 
		RULE_atom = 17, RULE_expressionList = 18;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "programSection", "globalVariableDefine", "classDefine", "constructorDef", 
			"functionDefine", "functionType", "constructorDefine", "argumentList", 
			"argument", "variableDefine", "variableTerm", "type", "notArrayType", 
			"suite", "statement", "expression", "atom", "expressionList"
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
			setState(41);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CLASS) | (1L << VOID) | (1L << BOOL) | (1L << INT) | (1L << STRING) | (1L << IDENTIFIER))) != 0)) {
				{
				{
				setState(38);
				programSection();
				}
				}
				setState(43);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(44);
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
		public GlobalVariableDefineContext globalVariableDefine() {
			return getRuleContext(GlobalVariableDefineContext.class,0);
		}
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
			setState(49);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(46);
				classDefine();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(47);
				functionDefine();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(48);
				globalVariableDefine();
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

	public static class GlobalVariableDefineContext extends ParserRuleContext {
		public VariableDefineContext variableDefine() {
			return getRuleContext(VariableDefineContext.class,0);
		}
		public GlobalVariableDefineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_globalVariableDefine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).enterGlobalVariableDefine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).exitGlobalVariableDefine(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxxParserVisitor ) return ((MxxParserVisitor<? extends T>)visitor).visitGlobalVariableDefine(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GlobalVariableDefineContext globalVariableDefine() throws RecognitionException {
		GlobalVariableDefineContext _localctx = new GlobalVariableDefineContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_globalVariableDefine);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			variableDefine();
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
		public TerminalNode SEMI() { return getToken(MxxParser.SEMI, 0); }
		public List<FunctionDefineContext> functionDefine() {
			return getRuleContexts(FunctionDefineContext.class);
		}
		public FunctionDefineContext functionDefine(int i) {
			return getRuleContext(FunctionDefineContext.class,i);
		}
		public List<ConstructorDefineContext> constructorDefine() {
			return getRuleContexts(ConstructorDefineContext.class);
		}
		public ConstructorDefineContext constructorDefine(int i) {
			return getRuleContext(ConstructorDefineContext.class,i);
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
		enterRule(_localctx, 6, RULE_classDefine);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53);
			match(CLASS);
			setState(54);
			match(IDENTIFIER);
			setState(55);
			match(BRACE_L);
			setState(61);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << VOID) | (1L << BOOL) | (1L << INT) | (1L << STRING) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(59);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
				case 1:
					{
					setState(56);
					functionDefine();
					}
					break;
				case 2:
					{
					setState(57);
					constructorDefine();
					}
					break;
				case 3:
					{
					setState(58);
					variableDefine();
					}
					break;
				}
				}
				setState(63);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(64);
			match(BRACE_R);
			setState(65);
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

	public static class ConstructorDefContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(MxxParser.IDENTIFIER, 0); }
		public TerminalNode PAREN_L() { return getToken(MxxParser.PAREN_L, 0); }
		public TerminalNode PAREN_R() { return getToken(MxxParser.PAREN_R, 0); }
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public ArgumentListContext argumentList() {
			return getRuleContext(ArgumentListContext.class,0);
		}
		public ConstructorDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constructorDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).enterConstructorDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).exitConstructorDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxxParserVisitor ) return ((MxxParserVisitor<? extends T>)visitor).visitConstructorDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstructorDefContext constructorDef() throws RecognitionException {
		ConstructorDefContext _localctx = new ConstructorDefContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_constructorDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			match(IDENTIFIER);
			setState(68);
			match(PAREN_L);
			setState(70);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL) | (1L << INT) | (1L << STRING) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(69);
				argumentList();
				}
			}

			setState(72);
			match(PAREN_R);
			setState(73);
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

	public static class FunctionDefineContext extends ParserRuleContext {
		public FunctionTypeContext functionType() {
			return getRuleContext(FunctionTypeContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(MxxParser.IDENTIFIER, 0); }
		public TerminalNode PAREN_L() { return getToken(MxxParser.PAREN_L, 0); }
		public TerminalNode PAREN_R() { return getToken(MxxParser.PAREN_R, 0); }
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
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
		enterRule(_localctx, 10, RULE_functionDefine);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			functionType();
			setState(76);
			match(IDENTIFIER);
			setState(77);
			match(PAREN_L);
			setState(79);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL) | (1L << INT) | (1L << STRING) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(78);
				argumentList();
				}
			}

			setState(81);
			match(PAREN_R);
			setState(82);
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

	public static class FunctionTypeContext extends ParserRuleContext {
		public TerminalNode VOID() { return getToken(MxxParser.VOID, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public FunctionTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).enterFunctionType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).exitFunctionType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxxParserVisitor ) return ((MxxParserVisitor<? extends T>)visitor).visitFunctionType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionTypeContext functionType() throws RecognitionException {
		FunctionTypeContext _localctx = new FunctionTypeContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_functionType);
		try {
			setState(86);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VOID:
				enterOuterAlt(_localctx, 1);
				{
				setState(84);
				match(VOID);
				}
				break;
			case BOOL:
			case INT:
			case STRING:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(85);
				type(0);
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

	public static class ConstructorDefineContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(MxxParser.IDENTIFIER, 0); }
		public TerminalNode PAREN_L() { return getToken(MxxParser.PAREN_L, 0); }
		public TerminalNode PAREN_R() { return getToken(MxxParser.PAREN_R, 0); }
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public ArgumentListContext argumentList() {
			return getRuleContext(ArgumentListContext.class,0);
		}
		public ConstructorDefineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constructorDefine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).enterConstructorDefine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).exitConstructorDefine(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxxParserVisitor ) return ((MxxParserVisitor<? extends T>)visitor).visitConstructorDefine(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstructorDefineContext constructorDefine() throws RecognitionException {
		ConstructorDefineContext _localctx = new ConstructorDefineContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_constructorDefine);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			match(IDENTIFIER);
			setState(89);
			match(PAREN_L);
			setState(91);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL) | (1L << INT) | (1L << STRING) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(90);
				argumentList();
				}
			}

			setState(93);
			match(PAREN_R);
			setState(94);
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
		public List<ArgumentContext> argument() {
			return getRuleContexts(ArgumentContext.class);
		}
		public ArgumentContext argument(int i) {
			return getRuleContext(ArgumentContext.class,i);
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
		enterRule(_localctx, 16, RULE_argumentList);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			argument();
			setState(101);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(97);
					match(COMMA);
					setState(98);
					argument();
					}
					} 
				}
				setState(103);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			}
			setState(105);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(104);
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

	public static class ArgumentContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(MxxParser.IDENTIFIER, 0); }
		public ArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argument; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).enterArgument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).exitArgument(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxxParserVisitor ) return ((MxxParserVisitor<? extends T>)visitor).visitArgument(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentContext argument() throws RecognitionException {
		ArgumentContext _localctx = new ArgumentContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_argument);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			type(0);
			setState(108);
			match(IDENTIFIER);
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
		public TerminalNode SEMI() { return getToken(MxxParser.SEMI, 0); }
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
		enterRule(_localctx, 20, RULE_variableDefine);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			type(0);
			setState(111);
			variableTerm();
			setState(116);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(112);
					match(COMMA);
					setState(113);
					variableTerm();
					}
					} 
				}
				setState(118);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			}
			setState(120);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(119);
				match(COMMA);
				}
			}

			setState(122);
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
		enterRule(_localctx, 22, RULE_variableTerm);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			match(IDENTIFIER);
			setState(127);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				setState(125);
				match(ASSIGN);
				setState(126);
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
		public NotArrayTypeContext notArrayType() {
			return getRuleContext(NotArrayTypeContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode BRACK_L() { return getToken(MxxParser.BRACK_L, 0); }
		public TerminalNode BRACK_R() { return getToken(MxxParser.BRACK_R, 0); }
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
		return type(0);
	}

	private TypeContext type(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		TypeContext _localctx = new TypeContext(_ctx, _parentState);
		TypeContext _prevctx = _localctx;
		int _startState = 24;
		enterRecursionRule(_localctx, 24, RULE_type, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(130);
			notArrayType();
			}
			_ctx.stop = _input.LT(-1);
			setState(137);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new TypeContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_type);
					setState(132);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(133);
					match(BRACK_L);
					setState(134);
					match(BRACK_R);
					}
					} 
				}
				setState(139);
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
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class NotArrayTypeContext extends ParserRuleContext {
		public TerminalNode BOOL() { return getToken(MxxParser.BOOL, 0); }
		public TerminalNode INT() { return getToken(MxxParser.INT, 0); }
		public TerminalNode STRING() { return getToken(MxxParser.STRING, 0); }
		public TerminalNode IDENTIFIER() { return getToken(MxxParser.IDENTIFIER, 0); }
		public NotArrayTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_notArrayType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).enterNotArrayType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).exitNotArrayType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxxParserVisitor ) return ((MxxParserVisitor<? extends T>)visitor).visitNotArrayType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NotArrayTypeContext notArrayType() throws RecognitionException {
		NotArrayTypeContext _localctx = new NotArrayTypeContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_notArrayType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(140);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL) | (1L << INT) | (1L << STRING) | (1L << IDENTIFIER))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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
		enterRule(_localctx, 28, RULE_suite);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(142);
			match(BRACE_L);
			setState(146);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PAREN_L) | (1L << BRACE_L) | (1L << SEMI) | (1L << BANG) | (1L << ADD) | (1L << SUB) | (1L << INC) | (1L << DEC) | (1L << TILDE) | (1L << THIS) | (1L << NEW) | (1L << BOOL) | (1L << INT) | (1L << STRING) | (1L << NULL) | (1L << TRUE) | (1L << FALSE) | (1L << IF) | (1L << FOR) | (1L << WHILE) | (1L << CONTINUE) | (1L << BREAK) | (1L << RETURN) | (1L << IDENTIFIER) | (1L << DECIMAL_INTEGER) | (1L << STRING_CONSTANT))) != 0)) {
				{
				{
				setState(143);
				statement();
				}
				}
				setState(148);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(149);
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
		public ExpressionContext conditionExpr;
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
		public ExpressionContext conditionExpr;
		public TerminalNode WHILE() { return getToken(MxxParser.WHILE, 0); }
		public TerminalNode PAREN_L() { return getToken(MxxParser.PAREN_L, 0); }
		public TerminalNode PAREN_R() { return getToken(MxxParser.PAREN_R, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
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
	public static class ExpressionStmtContext extends StatementContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(MxxParser.SEMI, 0); }
		public ExpressionStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).enterExpressionStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxxParserListener ) ((MxxParserListener)listener).exitExpressionStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxxParserVisitor ) return ((MxxParserVisitor<? extends T>)visitor).visitExpressionStmt(this);
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
		enterRule(_localctx, 30, RULE_statement);
		int _la;
		try {
			setState(197);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				_localctx = new SuiteStmtContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(151);
				suite();
				}
				break;
			case 2:
				_localctx = new IfStmtContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(152);
				match(IF);
				setState(153);
				match(PAREN_L);
				setState(154);
				expression(0);
				setState(155);
				match(PAREN_R);
				setState(156);
				((IfStmtContext)_localctx).trueBranch = statement();
				setState(159);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
				case 1:
					{
					setState(157);
					match(ELSE);
					setState(158);
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
				setState(161);
				match(FOR);
				setState(162);
				match(PAREN_L);
				setState(164);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PAREN_L) | (1L << BANG) | (1L << ADD) | (1L << SUB) | (1L << INC) | (1L << DEC) | (1L << TILDE) | (1L << THIS) | (1L << NEW) | (1L << NULL) | (1L << TRUE) | (1L << FALSE) | (1L << IDENTIFIER) | (1L << DECIMAL_INTEGER) | (1L << STRING_CONSTANT))) != 0)) {
					{
					setState(163);
					((ForStmtContext)_localctx).initialExpr = expression(0);
					}
				}

				setState(166);
				match(SEMI);
				setState(168);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PAREN_L) | (1L << BANG) | (1L << ADD) | (1L << SUB) | (1L << INC) | (1L << DEC) | (1L << TILDE) | (1L << THIS) | (1L << NEW) | (1L << NULL) | (1L << TRUE) | (1L << FALSE) | (1L << IDENTIFIER) | (1L << DECIMAL_INTEGER) | (1L << STRING_CONSTANT))) != 0)) {
					{
					setState(167);
					((ForStmtContext)_localctx).conditionExpr = expression(0);
					}
				}

				setState(170);
				match(SEMI);
				setState(172);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PAREN_L) | (1L << BANG) | (1L << ADD) | (1L << SUB) | (1L << INC) | (1L << DEC) | (1L << TILDE) | (1L << THIS) | (1L << NEW) | (1L << NULL) | (1L << TRUE) | (1L << FALSE) | (1L << IDENTIFIER) | (1L << DECIMAL_INTEGER) | (1L << STRING_CONSTANT))) != 0)) {
					{
					setState(171);
					((ForStmtContext)_localctx).stepExpr = expression(0);
					}
				}

				setState(174);
				match(PAREN_R);
				setState(175);
				statement();
				}
				break;
			case 4:
				_localctx = new WhileStmtContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(176);
				match(WHILE);
				setState(177);
				match(PAREN_L);
				setState(179);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PAREN_L) | (1L << BANG) | (1L << ADD) | (1L << SUB) | (1L << INC) | (1L << DEC) | (1L << TILDE) | (1L << THIS) | (1L << NEW) | (1L << NULL) | (1L << TRUE) | (1L << FALSE) | (1L << IDENTIFIER) | (1L << DECIMAL_INTEGER) | (1L << STRING_CONSTANT))) != 0)) {
					{
					setState(178);
					((WhileStmtContext)_localctx).conditionExpr = expression(0);
					}
				}

				setState(181);
				match(PAREN_R);
				setState(182);
				statement();
				}
				break;
			case 5:
				_localctx = new ContinueStmtContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(183);
				match(CONTINUE);
				setState(184);
				match(SEMI);
				}
				break;
			case 6:
				_localctx = new BreakStmtContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(185);
				match(BREAK);
				setState(186);
				match(SEMI);
				}
				break;
			case 7:
				_localctx = new ReturnStmtContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(187);
				match(RETURN);
				setState(189);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PAREN_L) | (1L << BANG) | (1L << ADD) | (1L << SUB) | (1L << INC) | (1L << DEC) | (1L << TILDE) | (1L << THIS) | (1L << NEW) | (1L << NULL) | (1L << TRUE) | (1L << FALSE) | (1L << IDENTIFIER) | (1L << DECIMAL_INTEGER) | (1L << STRING_CONSTANT))) != 0)) {
					{
					setState(188);
					expression(0);
					}
				}

				setState(191);
				match(SEMI);
				}
				break;
			case 8:
				_localctx = new ExpressionStmtContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				{
				setState(192);
				expression(0);
				setState(193);
				match(SEMI);
				}
				}
				break;
			case 9:
				_localctx = new VariableStmtContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(195);
				variableDefine();
				}
				break;
			case 10:
				_localctx = new EmptyStmtContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(196);
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
		public ExpressionContext arrayName;
		public ExpressionContext index;
		public TerminalNode BRACK_L() { return getToken(MxxParser.BRACK_L, 0); }
		public TerminalNode BRACK_R() { return getToken(MxxParser.BRACK_R, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
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
		public ExpressionContext termL;
		public Token op;
		public ExpressionContext termR;
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
		int _startState = 32;
		enterRecursionRule(_localctx, 32, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(213);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PAREN_L:
				{
				_localctx = new ParenExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(200);
				match(PAREN_L);
				setState(201);
				expression(0);
				setState(202);
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
				setState(204);
				atom();
				}
				break;
			case NEW:
				{
				_localctx = new NewExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(205);
				match(NEW);
				setState(206);
				type(0);
				}
				break;
			case INC:
			case DEC:
				{
				_localctx = new UnaryExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(207);
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
				setState(208);
				expression(12);
				}
				break;
			case BANG:
			case TILDE:
				{
				_localctx = new UnaryExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(209);
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
				setState(210);
				expression(11);
				}
				break;
			case ADD:
			case SUB:
				{
				_localctx = new UnaryExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(211);
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
				setState(212);
				expression(10);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(263);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(261);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
					case 1:
						{
						_localctx = new AssignExprContext(new ExpressionContext(_parentctx, _parentState));
						((AssignExprContext)_localctx).lValue = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(215);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(216);
						match(ASSIGN);
						setState(217);
						((AssignExprContext)_localctx).rValue = expression(15);
						}
						break;
					case 2:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).termL = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(218);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(219);
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
						setState(220);
						((BinaryExprContext)_localctx).termR = expression(10);
						}
						break;
					case 3:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).termL = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(221);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(222);
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
						setState(223);
						((BinaryExprContext)_localctx).termR = expression(9);
						}
						break;
					case 4:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).termL = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(224);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(225);
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
						setState(226);
						((BinaryExprContext)_localctx).termR = expression(8);
						}
						break;
					case 5:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).termL = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(227);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(228);
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
						setState(229);
						((BinaryExprContext)_localctx).termR = expression(7);
						}
						break;
					case 6:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).termL = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(230);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(231);
						((BinaryExprContext)_localctx).op = match(BIT_AND);
						setState(232);
						((BinaryExprContext)_localctx).termR = expression(6);
						}
						break;
					case 7:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).termL = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(233);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(234);
						((BinaryExprContext)_localctx).op = match(BIT_OR);
						setState(235);
						((BinaryExprContext)_localctx).termR = expression(5);
						}
						break;
					case 8:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).termL = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(236);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(237);
						((BinaryExprContext)_localctx).op = match(CARET);
						setState(238);
						((BinaryExprContext)_localctx).termR = expression(4);
						}
						break;
					case 9:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).termL = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(239);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(240);
						((BinaryExprContext)_localctx).op = match(AND);
						setState(241);
						((BinaryExprContext)_localctx).termR = expression(3);
						}
						break;
					case 10:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).termL = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(242);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(243);
						((BinaryExprContext)_localctx).op = match(OR);
						setState(244);
						((BinaryExprContext)_localctx).termR = expression(2);
						}
						break;
					case 11:
						{
						_localctx = new MemberExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(245);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(246);
						match(DOT);
						setState(247);
						match(IDENTIFIER);
						}
						break;
					case 12:
						{
						_localctx = new ArrayExprContext(new ExpressionContext(_parentctx, _parentState));
						((ArrayExprContext)_localctx).arrayName = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(248);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(249);
						match(BRACK_L);
						setState(250);
						((ArrayExprContext)_localctx).index = expression(0);
						setState(251);
						match(BRACK_R);
						}
						break;
					case 13:
						{
						_localctx = new FunctionExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(253);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(254);
						match(PAREN_L);
						setState(256);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PAREN_L) | (1L << BANG) | (1L << ADD) | (1L << SUB) | (1L << INC) | (1L << DEC) | (1L << TILDE) | (1L << THIS) | (1L << NEW) | (1L << NULL) | (1L << TRUE) | (1L << FALSE) | (1L << IDENTIFIER) | (1L << DECIMAL_INTEGER) | (1L << STRING_CONSTANT))) != 0)) {
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
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
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
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
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
		enterRule(_localctx, 34, RULE_atom);
		int _la;
		try {
			setState(272);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case THIS:
				enterOuterAlt(_localctx, 1);
				{
				setState(266);
				match(THIS);
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(267);
				match(IDENTIFIER);
				}
				break;
			case STRING_CONSTANT:
				enterOuterAlt(_localctx, 3);
				{
				setState(268);
				match(STRING_CONSTANT);
				}
				break;
			case DECIMAL_INTEGER:
				enterOuterAlt(_localctx, 4);
				{
				setState(269);
				match(DECIMAL_INTEGER);
				}
				break;
			case TRUE:
			case FALSE:
				enterOuterAlt(_localctx, 5);
				{
				setState(270);
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
				setState(271);
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
		enterRule(_localctx, 36, RULE_expressionList);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(274);
			expression(0);
			setState(279);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(275);
					match(COMMA);
					setState(276);
					expression(0);
					}
					} 
				}
				setState(281);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			}
			setState(283);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(282);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 12:
			return type_sempred((TypeContext)_localctx, predIndex);
		case 16:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean type_sempred(TypeContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 15);
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
			return precpred(_ctx, 1);
		case 11:
			return precpred(_ctx, 18);
		case 12:
			return precpred(_ctx, 17);
		case 13:
			return precpred(_ctx, 16);
		case 14:
			return precpred(_ctx, 13);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3:\u0120\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\3\2\7\2*\n\2\f\2\16\2-\13\2\3\2\3\2\3\3\3\3\3\3\5"+
		"\3\64\n\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\7\5>\n\5\f\5\16\5A\13\5\3\5"+
		"\3\5\3\5\3\6\3\6\3\6\5\6I\n\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\5\7R\n\7\3\7"+
		"\3\7\3\7\3\b\3\b\5\bY\n\b\3\t\3\t\3\t\5\t^\n\t\3\t\3\t\3\t\3\n\3\n\3\n"+
		"\7\nf\n\n\f\n\16\ni\13\n\3\n\5\nl\n\n\3\13\3\13\3\13\3\f\3\f\3\f\3\f\7"+
		"\fu\n\f\f\f\16\fx\13\f\3\f\5\f{\n\f\3\f\3\f\3\r\3\r\3\r\5\r\u0082\n\r"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\7\16\u008a\n\16\f\16\16\16\u008d\13\16"+
		"\3\17\3\17\3\20\3\20\7\20\u0093\n\20\f\20\16\20\u0096\13\20\3\20\3\20"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u00a2\n\21\3\21\3\21\3\21"+
		"\5\21\u00a7\n\21\3\21\3\21\5\21\u00ab\n\21\3\21\3\21\5\21\u00af\n\21\3"+
		"\21\3\21\3\21\3\21\3\21\5\21\u00b6\n\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\5\21\u00c0\n\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u00c8\n"+
		"\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\5\22\u00d8\n\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\5\22\u0103\n\22\3\22\3\22\3\22\7\22\u0108\n\22\f\22\16"+
		"\22\u010b\13\22\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u0113\n\23\3\24\3\24"+
		"\3\24\7\24\u0118\n\24\f\24\16\24\u011b\13\24\3\24\5\24\u011e\n\24\3\24"+
		"\2\4\32\"\25\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&\2\n\4\2)+88\3"+
		"\2\35\36\4\2\16\16\37\37\3\2\30\31\3\2\32\34\3\2#$\3\2\20\25\3\2-.\2\u0147"+
		"\2+\3\2\2\2\4\63\3\2\2\2\6\65\3\2\2\2\b\67\3\2\2\2\nE\3\2\2\2\fM\3\2\2"+
		"\2\16X\3\2\2\2\20Z\3\2\2\2\22b\3\2\2\2\24m\3\2\2\2\26p\3\2\2\2\30~\3\2"+
		"\2\2\32\u0083\3\2\2\2\34\u008e\3\2\2\2\36\u0090\3\2\2\2 \u00c7\3\2\2\2"+
		"\"\u00d7\3\2\2\2$\u0112\3\2\2\2&\u0114\3\2\2\2(*\5\4\3\2)(\3\2\2\2*-\3"+
		"\2\2\2+)\3\2\2\2+,\3\2\2\2,.\3\2\2\2-+\3\2\2\2./\7\2\2\3/\3\3\2\2\2\60"+
		"\64\5\b\5\2\61\64\5\f\7\2\62\64\5\6\4\2\63\60\3\2\2\2\63\61\3\2\2\2\63"+
		"\62\3\2\2\2\64\5\3\2\2\2\65\66\5\26\f\2\66\7\3\2\2\2\678\7%\2\289\78\2"+
		"\29?\7\7\2\2:>\5\f\7\2;>\5\20\t\2<>\5\26\f\2=:\3\2\2\2=;\3\2\2\2=<\3\2"+
		"\2\2>A\3\2\2\2?=\3\2\2\2?@\3\2\2\2@B\3\2\2\2A?\3\2\2\2BC\7\b\2\2CD\7\13"+
		"\2\2D\t\3\2\2\2EF\78\2\2FH\7\5\2\2GI\5\22\n\2HG\3\2\2\2HI\3\2\2\2IJ\3"+
		"\2\2\2JK\7\6\2\2KL\5\36\20\2L\13\3\2\2\2MN\5\16\b\2NO\78\2\2OQ\7\5\2\2"+
		"PR\5\22\n\2QP\3\2\2\2QR\3\2\2\2RS\3\2\2\2ST\7\6\2\2TU\5\36\20\2U\r\3\2"+
		"\2\2VY\7(\2\2WY\5\32\16\2XV\3\2\2\2XW\3\2\2\2Y\17\3\2\2\2Z[\78\2\2[]\7"+
		"\5\2\2\\^\5\22\n\2]\\\3\2\2\2]^\3\2\2\2^_\3\2\2\2_`\7\6\2\2`a\5\36\20"+
		"\2a\21\3\2\2\2bg\5\24\13\2cd\7\f\2\2df\5\24\13\2ec\3\2\2\2fi\3\2\2\2g"+
		"e\3\2\2\2gh\3\2\2\2hk\3\2\2\2ig\3\2\2\2jl\7\f\2\2kj\3\2\2\2kl\3\2\2\2"+
		"l\23\3\2\2\2mn\5\32\16\2no\78\2\2o\25\3\2\2\2pq\5\32\16\2qv\5\30\r\2r"+
		"s\7\f\2\2su\5\30\r\2tr\3\2\2\2ux\3\2\2\2vt\3\2\2\2vw\3\2\2\2wz\3\2\2\2"+
		"xv\3\2\2\2y{\7\f\2\2zy\3\2\2\2z{\3\2\2\2{|\3\2\2\2|}\7\13\2\2}\27\3\2"+
		"\2\2~\u0081\78\2\2\177\u0080\7\17\2\2\u0080\u0082\5\"\22\2\u0081\177\3"+
		"\2\2\2\u0081\u0082\3\2\2\2\u0082\31\3\2\2\2\u0083\u0084\b\16\1\2\u0084"+
		"\u0085\5\34\17\2\u0085\u008b\3\2\2\2\u0086\u0087\f\3\2\2\u0087\u0088\7"+
		"\t\2\2\u0088\u008a\7\n\2\2\u0089\u0086\3\2\2\2\u008a\u008d\3\2\2\2\u008b"+
		"\u0089\3\2\2\2\u008b\u008c\3\2\2\2\u008c\33\3\2\2\2\u008d\u008b\3\2\2"+
		"\2\u008e\u008f\t\2\2\2\u008f\35\3\2\2\2\u0090\u0094\7\7\2\2\u0091\u0093"+
		"\5 \21\2\u0092\u0091\3\2\2\2\u0093\u0096\3\2\2\2\u0094\u0092\3\2\2\2\u0094"+
		"\u0095\3\2\2\2\u0095\u0097\3\2\2\2\u0096\u0094\3\2\2\2\u0097\u0098\7\b"+
		"\2\2\u0098\37\3\2\2\2\u0099\u00c8\5\36\20\2\u009a\u009b\7/\2\2\u009b\u009c"+
		"\7\5\2\2\u009c\u009d\5\"\22\2\u009d\u009e\7\6\2\2\u009e\u00a1\5 \21\2"+
		"\u009f\u00a0\7\60\2\2\u00a0\u00a2\5 \21\2\u00a1\u009f\3\2\2\2\u00a1\u00a2"+
		"\3\2\2\2\u00a2\u00c8\3\2\2\2\u00a3\u00a4\7\61\2\2\u00a4\u00a6\7\5\2\2"+
		"\u00a5\u00a7\5\"\22\2\u00a6\u00a5\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a8"+
		"\3\2\2\2\u00a8\u00aa\7\13\2\2\u00a9\u00ab\5\"\22\2\u00aa\u00a9\3\2\2\2"+
		"\u00aa\u00ab\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\u00ae\7\13\2\2\u00ad\u00af"+
		"\5\"\22\2\u00ae\u00ad\3\2\2\2\u00ae\u00af\3\2\2\2\u00af\u00b0\3\2\2\2"+
		"\u00b0\u00b1\7\6\2\2\u00b1\u00c8\5 \21\2\u00b2\u00b3\7\62\2\2\u00b3\u00b5"+
		"\7\5\2\2\u00b4\u00b6\5\"\22\2\u00b5\u00b4\3\2\2\2\u00b5\u00b6\3\2\2\2"+
		"\u00b6\u00b7\3\2\2\2\u00b7\u00b8\7\6\2\2\u00b8\u00c8\5 \21\2\u00b9\u00ba"+
		"\7\63\2\2\u00ba\u00c8\7\13\2\2\u00bb\u00bc\7\64\2\2\u00bc\u00c8\7\13\2"+
		"\2\u00bd\u00bf\7\65\2\2\u00be\u00c0\5\"\22\2\u00bf\u00be\3\2\2\2\u00bf"+
		"\u00c0\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1\u00c8\7\13\2\2\u00c2\u00c3\5"+
		"\"\22\2\u00c3\u00c4\7\13\2\2\u00c4\u00c8\3\2\2\2\u00c5\u00c8\5\26\f\2"+
		"\u00c6\u00c8\7\13\2\2\u00c7\u0099\3\2\2\2\u00c7\u009a\3\2\2\2\u00c7\u00a3"+
		"\3\2\2\2\u00c7\u00b2\3\2\2\2\u00c7\u00b9\3\2\2\2\u00c7\u00bb\3\2\2\2\u00c7"+
		"\u00bd\3\2\2\2\u00c7\u00c2\3\2\2\2\u00c7\u00c5\3\2\2\2\u00c7\u00c6\3\2"+
		"\2\2\u00c8!\3\2\2\2\u00c9\u00ca\b\22\1\2\u00ca\u00cb\7\5\2\2\u00cb\u00cc"+
		"\5\"\22\2\u00cc\u00cd\7\6\2\2\u00cd\u00d8\3\2\2\2\u00ce\u00d8\5$\23\2"+
		"\u00cf\u00d0\7\'\2\2\u00d0\u00d8\5\32\16\2\u00d1\u00d2\t\3\2\2\u00d2\u00d8"+
		"\5\"\22\16\u00d3\u00d4\t\4\2\2\u00d4\u00d8\5\"\22\r\u00d5\u00d6\t\5\2"+
		"\2\u00d6\u00d8\5\"\22\f\u00d7\u00c9\3\2\2\2\u00d7\u00ce\3\2\2\2\u00d7"+
		"\u00cf\3\2\2\2\u00d7\u00d1\3\2\2\2\u00d7\u00d3\3\2\2\2\u00d7\u00d5\3\2"+
		"\2\2\u00d8\u0109\3\2\2\2\u00d9\u00da\f\21\2\2\u00da\u00db\7\17\2\2\u00db"+
		"\u0108\5\"\22\21\u00dc\u00dd\f\13\2\2\u00dd\u00de\t\6\2\2\u00de\u0108"+
		"\5\"\22\f\u00df\u00e0\f\n\2\2\u00e0\u00e1\t\5\2\2\u00e1\u0108\5\"\22\13"+
		"\u00e2\u00e3\f\t\2\2\u00e3\u00e4\t\7\2\2\u00e4\u0108\5\"\22\n\u00e5\u00e6"+
		"\f\b\2\2\u00e6\u00e7\t\b\2\2\u00e7\u0108\5\"\22\t\u00e8\u00e9\f\7\2\2"+
		"\u00e9\u00ea\7 \2\2\u00ea\u0108\5\"\22\b\u00eb\u00ec\f\6\2\2\u00ec\u00ed"+
		"\7!\2\2\u00ed\u0108\5\"\22\7\u00ee\u00ef\f\5\2\2\u00ef\u00f0\7\"\2\2\u00f0"+
		"\u0108\5\"\22\6\u00f1\u00f2\f\4\2\2\u00f2\u00f3\7\26\2\2\u00f3\u0108\5"+
		"\"\22\5\u00f4\u00f5\f\3\2\2\u00f5\u00f6\7\27\2\2\u00f6\u0108\5\"\22\4"+
		"\u00f7\u00f8\f\24\2\2\u00f8\u00f9\7\r\2\2\u00f9\u0108\78\2\2\u00fa\u00fb"+
		"\f\23\2\2\u00fb\u00fc\7\t\2\2\u00fc\u00fd\5\"\22\2\u00fd\u00fe\7\n\2\2"+
		"\u00fe\u0108\3\2\2\2\u00ff\u0100\f\22\2\2\u0100\u0102\7\5\2\2\u0101\u0103"+
		"\5&\24\2\u0102\u0101\3\2\2\2\u0102\u0103\3\2\2\2\u0103\u0104\3\2\2\2\u0104"+
		"\u0108\7\6\2\2\u0105\u0106\f\17\2\2\u0106\u0108\t\3\2\2\u0107\u00d9\3"+
		"\2\2\2\u0107\u00dc\3\2\2\2\u0107\u00df\3\2\2\2\u0107\u00e2\3\2\2\2\u0107"+
		"\u00e5\3\2\2\2\u0107\u00e8\3\2\2\2\u0107\u00eb\3\2\2\2\u0107\u00ee\3\2"+
		"\2\2\u0107\u00f1\3\2\2\2\u0107\u00f4\3\2\2\2\u0107\u00f7\3\2\2\2\u0107"+
		"\u00fa\3\2\2\2\u0107\u00ff\3\2\2\2\u0107\u0105\3\2\2\2\u0108\u010b\3\2"+
		"\2\2\u0109\u0107\3\2\2\2\u0109\u010a\3\2\2\2\u010a#\3\2\2\2\u010b\u0109"+
		"\3\2\2\2\u010c\u0113\7&\2\2\u010d\u0113\78\2\2\u010e\u0113\7:\2\2\u010f"+
		"\u0113\79\2\2\u0110\u0113\t\t\2\2\u0111\u0113\7,\2\2\u0112\u010c\3\2\2"+
		"\2\u0112\u010d\3\2\2\2\u0112\u010e\3\2\2\2\u0112\u010f\3\2\2\2\u0112\u0110"+
		"\3\2\2\2\u0112\u0111\3\2\2\2\u0113%\3\2\2\2\u0114\u0119\5\"\22\2\u0115"+
		"\u0116\7\f\2\2\u0116\u0118\5\"\22\2\u0117\u0115\3\2\2\2\u0118\u011b\3"+
		"\2\2\2\u0119\u0117\3\2\2\2\u0119\u011a\3\2\2\2\u011a\u011d\3\2\2\2\u011b"+
		"\u0119\3\2\2\2\u011c\u011e\7\f\2\2\u011d\u011c\3\2\2\2\u011d\u011e\3\2"+
		"\2\2\u011e\'\3\2\2\2\37+\63=?HQX]gkvz\u0081\u008b\u0094\u00a1\u00a6\u00aa"+
		"\u00ae\u00b5\u00bf\u00c7\u00d7\u0102\u0107\u0109\u0112\u0119\u011d";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}