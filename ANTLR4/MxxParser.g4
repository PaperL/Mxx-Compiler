parser grammar MxxParser;
options {
	language = Java;
	tokenVocab = MxxLexer;
}

program: (programSection)* EOF;

programSection:
	classDefine
	| functionDefine
	| globalVariableDefine;

globalVariableDefine: variableDefine;

classDefine:
	CLASS IDENTIFIER BRACE_L (
		functionDefine
		| constructorDefine
		| variableDefine
	)* BRACE_R SEMI;

constructorDef: IDENTIFIER PAREN_L argumentList? PAREN_R suite;

functionDefine:
	functionType IDENTIFIER PAREN_L argumentList? PAREN_R suite;

functionType: VOID | type;

constructorDefine:
	IDENTIFIER PAREN_L argumentList? PAREN_R suite;

argumentList: argument (COMMA argument)* COMMA?;

argument: type IDENTIFIER;

variableDefine:
	type variableTerm (COMMA variableTerm)* COMMA? SEMI;
variableTerm: IDENTIFIER (ASSIGN expression)?;

type: notArrayType | (type BRACK_L BRACK_R);

notArrayType: BOOL | INT | STRING | IDENTIFIER;

suite: BRACE_L statement* BRACE_R;

statement: suite | complexStatement | simpleStatement;

complexStatement: ifStatement | forStatement | whileStatement;

ifStatement:
	IF '(' expression ')' trueBranch = statement (
		ELSE falseBranch = statement
	)?;

forStatement:
	FOR PAREN_L (initialExpr = expression)? SEMI (
		conditionExpr = expression
	)? SEMI (stepExpr = expression)? PAREN_R statement;

whileStatement:
	WHILE PAREN_L (conditionExpr = expression)? PAREN_R statement;

simpleStatement:
	(variableDefine SEMI)	# variableStmt
	| (flowStatement SEMI)	# flowStmt
	| (expression SEMI)		# expressionStmt
	| SEMI					# emptyStmt;

flowStatement:
	CONTINUE SEMI				# continueStmt
	| BREAK SEMI				# breakStmt
	| RETURN expression? SEMI	# returnStmt;

expression:
	assignExpression				# assignExpr
	| arithExpression				# arithExpr
	| <assoc = right> NEW type		# newExpr
	| simpleExpression				# simpleExpr
	| PAREN_L expression PAREN_R	# parenExpr;

simpleExpression: molecule | atom;

atom:
	THIS
	| IDENTIFIER
	| STRING_CONSTANT
	| DECIMAL_INTEGER
	| (TRUE | FALSE)
	| NULL;

molecule:
	expression PAREN_L expressionList PAREN_R
	| expression DOT IDENTIFIER
	| (arrayName = expression) BRACK_L (index = expression) BRACK_R;

expressionList: expression (COMMA expression)* COMMA?;

arithExpression:
	expression op = (INC | DEC)														# selfExpr
	| <assoc = right> op = (INC | DEC) expression									# unaryExpr
	| <assoc = right> op = (BANG | TILDE) expression								# unaryExpr
	| <assoc = right> op = (ADD | SUB) expression									# unaryExpr
	| termL = expression op = (MUL | DIV | MOD) termR = expression					# binaryExpr
	| termL = expression op = (ADD | SUB) termR = expression						# binaryExpr
	| termL = expression op = (SHIFT_L | SHIFT_R) termR = expression				# binaryExpr
	| termL = expression op = (GT | LT | GE | LE | EQ | NOT_EQ) termR = expression	# binaryExpr
	| termL = expression op = (BIT_AND) termR = expression							# binaryExpr
	| termL = expression op = (BIT_OR) termR = expression							# binaryExpr
	| termL = expression op = (CARET) termR = expression							# binaryExpr
	| termL = expression op = (AND) termR = expression								# binaryExpr
	| termL = expression op = (OR) termR = expression								# binaryExpr;

assignExpression:
	<assoc = right>lValue = expression ASSIGN rValue = expression;

