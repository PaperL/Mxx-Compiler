parser grammar MxxParser;
options {
	language = Java;
	tokenVocab = MxxLexer;
}

program: (programSection)* EOF;
programSection:	classDefine	| functionDefine | variableDefine;

classDefine: CLASS IDENTIFIER BRACE_L (functionDefine | variableDefine)* BRACE_R SEMI;

functionDefine:	type? IDENTIFIER PAREN_L argumentList? PAREN_R suite;

argumentList: type IDENTIFIER (COMMA type IDENTIFIER)* COMMA?;

variableDefine:	type variableTerm (COMMA variableTerm)* COMMA? SEMI;
variableTerm: IDENTIFIER (ASSIGN expression)?;

type: (VOID | BOOL | INT | STRING | IDENTIFIER (PAREN_L PAREN_R)?) bracket*;
bracket: BRACK_L expression? BRACK_R;

suite: BRACE_L statement* BRACE_R;

statement
    : suite                                                             # suiteStmt
	| IF PAREN_L expression PAREN_R
	  trueBranch = statement
	  (ELSE falseBranch = statement)?                                   # ifStmt
	| FOR PAREN_L (initialExpr = expression)? SEMI
	              (forCondExpr = expression)? SEMI
	              (stepExpr = expression)?
	  PAREN_R statement                                                 # forStmt
	| WHILE PAREN_L expression?
	  PAREN_R statement                                                 # whileStmt
	| CONTINUE SEMI                                                     # continueStmt
	| BREAK SEMI                                                        # breakStmt
	| RETURN expression? SEMI                                           # returnStmt
	| expression SEMI                                                   # singleExprStmt
	| variableDefine                                                    # variableStmt
	| SEMI                                                              # emptyStmt;

expression
	// term
	: PAREN_L expression PAREN_R                                        # parenExpr
	| atom                                                              # atomExpr
	| expression DOT IDENTIFIER                                         # memberExpr
	| expression bracket+                                               # arrayExpr
	| expression PAREN_L expressionList? PAREN_R                        # functionExpr
	// command
	| <assoc = right> lValue = expression ASSIGN rValue = expression    # assignExpr
	| <assoc = right> NEW type                                          # newExpr
	// arithmetic
	| expression op = (INC | DEC)                                       # selfExpr
	| <assoc = right> op = (INC | DEC) expression					    # unaryExpr
	| <assoc = right> op = (BANG | TILDE) expression                    # unaryExpr
	| <assoc = right> op = (ADD | SUB) expression                       # unaryExpr

	| lTerm = expression op = (MUL | DIV | MOD)     rTerm = expression  # binaryExpr
	| lTerm = expression op = (ADD | SUB)           rTerm = expression  # binaryExpr
	| lTerm = expression op = (SHIFT_L | SHIFT_R)   rTerm = expression	# binaryExpr
	| lTerm = expression op = (GT | LT | GE | LE | EQ | NOT_EQ) rTerm = expression
	                                                                    # binaryExpr
	| lTerm = expression op = BIT_AND               rTerm = expression  # binaryExpr
	| lTerm = expression op = BIT_OR                rTerm = expression  # binaryExpr
	| lTerm = expression op = CARET                 rTerm = expression  # binaryExpr
	| lTerm = expression op = AND                   rTerm = expression  # binaryExpr
	| lTerm = expression op = OR                    rTerm = expression  # binaryExpr;
expressionList: expression (COMMA expression)* COMMA?;

atom
	: THIS
	| IDENTIFIER
	| STRING_CONSTANT
	| DECIMAL_INTEGER
	| (TRUE | FALSE)
	| NULL;
