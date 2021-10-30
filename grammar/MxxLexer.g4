lexer grammar MxxLexer;

// Comment

BLOCK_COMMENT: '/*' .*? '*/' -> skip;

LINE_COMMENT: '//' ~[\r\n]* -> skip;

// Separator

BRACE_L: '{';
BRACE_R: '}';
SEMI: ';';
PAREN_L: '(';   // PARENTHESIS
PAREN_R: ')';
COMMA: ',';
DOT: '.';
BRACK_L: '[';   // BRACKET
BRACK_R: ']';

// Operator

BANG: '!';
ASSIGN: '=';
EQ: '==';
NOT_EQ: '!=';
LT: '<';
LE: '<=';
GT: '>';
GE: '>=';
AND: '&&';
OR: '||';

ADD: '+';
SUB: '-';
MUL: '*';
DIV: '/';
MOD: '%';
INC: '++';
DEC: '--';

TILDE: '~';
BIT_AND: '&';
BIT_OR: '|';
CARET: '^';
SHIFT_L: '<<';
SHIFT_R: '>>';

// Keyword

CLASS: 'class';
THIS: 'this';

NEW: 'new';

VOID: 'void';
BOOL: 'bool';
INT: 'int';
STRING: 'string';

NULL: 'null';
TRUE: 'true';
FALSE: 'false';

IF: 'if';
ELSE: 'else';
FOR: 'for';
WHILE: 'while';
CONTINUE: 'continue';
BREAK: 'break';
RETURN: 'return';

// Basic

WHITE_SPACE: [ \t]+ -> skip;

NEW_LINE: ('\r' '\n'? | '\n') -> skip;

IDENTIFIER: [a-zA-Z_] [a-zA-Z_0-9]*;
DECIMAL_INTEGER: [1-9][0-9]* | '0';
// 此处可扩展支持更多进制
STRING_CONSTANT: '"' ('\\' . | .)*? '"';