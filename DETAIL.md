# Mxx Compiler 实现说明

> 关于 g4 文件中的 program & programSection 节点\
> 题面 13.4 要求: 全局变量和局部变量不支持前向引用\
> 故不能将 programSection 规则直接在 program 中展开\
> 13.5: 函数和类的声明都应该在顶层，作用域为全局，支持前向引用（forward reference）



## 文件结构

- Main
- **parser** (Antlr4 生成)
    - MxxLexer
    - MxxParser
    - MxxParserBaseListener
    - MxxParserListener
    - MxxParserBaseVisitor
    - MxxParserVisitor
- **ast**
    - AstNode
        - NodeRoot
        - NodeProgramSection
        - NodeClassDefine
        - NodeFunctionDefine
        - NodeVariableDefine
        - NodeType
        - NodeArgumentList
        - NodeSuite
        - NodeVariableTerm
        - NodeExpression
        - NodeBracket
        - NodeStatement
        - NodeExpressionList
        - NodeAtom
- **frontend**
    - AstBuilder
    - ForwardCollector
    - SemanticChecker
- **utility**
    - **error**
        - Error
            - SyntaxError
            - SemanticError
    - **scope**
        - VariableScope
            - FunctionScope
            - ClassScope
            - BroadScope
    - ErrorListener
    - Position
    - Type

## AST 结构

> 在设计 AST 的过程中，尽量将简单的节点规则直接在父节点规则中展开，避免整体结构过于冗长，同时减少工作量。\
> 在设计 g4 语法的过程中，尽量避免 Parser 部分过多实现 Semantic 功能。

- Root
  - ProgramSection[]
    - `Class Define`
      - Name (String), Method Define[], Variable Define[]
    - `Function Define`
    - `Variable Define`
- Variable Define
  - Type, Variable Term[]
    - Variable Term: Name, Initial Expression
- Function Define
  - Type, Name, Argument List, Suite
    - Argument List: Type[], Identifier[]
- Type
  - Type, Bracket[]
    - Bracket: Expression
- Suite
  - Statement[]
    - `Suite`: Suite
    - `If`: CondExpr, TrueBranchStmt, FalseBranchStmt
    - `For`: InitExpr/InitVarDef, CondExpr, StepExpr, BodyStmt
    - `While`: CondExpr, BodyStmt
    - `Continue / Break / Return`
    - `Variable Define`
    - `Expression`
      - Paren `(expression)`
      - Member `expression.IDENTIFIER`
      - Array `expression[expression]`
      - Function `expression(expressionList)`
      - Assign `expression = expression`
      - New `NEW type`
      - Arith
        - Self `expression op`
          - op: `++` | `--`
        - Unary `op expression`
          - op: `!` | `~`
        - Binary `expression op expression`
          - op: `+` | `-` | `*` | `/` | `%` | `<<` | `>>`
          - op: `>` | `<` | `>=` | `<=` | `==` | `!=`
          - op: `&` | `|` | `~` | `&&` | `||`
      - Atom
        - This
        - Identifier
        - String constant
        - Decimal integer
        - `true` | `false`
        - NULL
    - `Empty`: (only `;`)

### 其他说明

- `AstNode`
  - 规避了 `Accept()` 函数的使用必要
- `AstVisitor`
  - 这是个很愚蠢的东西, 见上 `Accept()`
- 暂时参见 `SemanticChecker.java`
- 由于题面理解有误，一开始的设计中并没有左值相关的信息。导致最后对 Semantic Check 部分进行了一波**很丑很丑**的修改，且难以保证正确性



## IR

- module
  - > 全局变量、全局常量、Class 声明、函数声明
  - Function
    - Instruction Block
      - Instruction
        - Type
  - Global Variable Define
  - Type Define

### 其他说明

- 期望实现 llvm
- 使用 clang
  - `clang -S -emit-llvm main.c` 生成 `.ll`
  - `clang -c -emit-llvm main.c` 生成 `.bc`
- string 相关操作应在 codegen 阶段直接将 cstring 函数 link 入汇编程序
