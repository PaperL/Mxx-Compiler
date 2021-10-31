# Mxx Compiler 实现说明

> **注意: AST 结构与文件结构需要更新**

> 关于 g4 文件中的 program & programSection 节点\
> 题面 13.4 要求: 全局变量和局部变量不支持前向引用\
> 故不能将 programSection 规则直接在 program 中展开\
> 13.5: 函数和类的声明都应该在顶层，作用域为全局，支持前向引用（forward reference）

## AST 结构

> 在设计 AST 的过程中，尽量将简单的节点规则直接在父节点规则中展开，避免整体结构过于冗长，同时减少工作量。\
> 在设计 g4 语法的过程中，尽量避免 Parser 部分过多实现 Semantic 功能。

- Program
    - Class Define
        - `class IDENTIFIER { functionDefine / variableDefine };`
    - Function Define
        - `functionType IDENTIFIER (argumentList) suite`
    - Variable Define

- Suite
    - Statement
        - Suite
        - If Statement
        - For Statement
        - While Statement
        - Continue / Break / Return
        - Variable Define
        - Expression
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
            - Empty (only `;`)

## 文件结构

- Main
- **parser**
    - MxxLexer
    - MxxParser
    - MxxParserBaseListener
    - MxxParserListener
    - MxxParserBaseVisitor
    - MxxParserVisitor
- **semantic**
    - BasicVisitor
- **ast**
    - AstNode
        - NodeRoot
    - AstVisitor
- **semantic**
    - BasicVisitor
- **frontend**
    - AstBuilder
    - SymbolCollector
    - SemanticChecker

## 其他说明

- 题面 7.2 数组类型中允许定义数组时省略类型，但实际未测试该 feature