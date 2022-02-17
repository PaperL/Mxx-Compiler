# Mxx Compiler

[![GitHub top language](https://img.shields.io/github/languages/top/PaperL/Mxx-Compiler)](http://jdk.java.net/17/)
[![License](https://img.shields.io/github/license/PaperL/Mxx-Compiler)](https://www.gnu.org/licenses/gpl-3.0.html)

[![Latest tag](https://img.shields.io/github/v/tag/PaperL/Mxx-Compiler)](https://github.com/PaperL/Mxx-Compiler/tags)
[![Last commit](https://img.shields.io/github/last-commit/PaperL/Mxx-Compiler)](https://github.com/PaperL/Mxx-Compiler/commits/)
![GitHub repo size](https://img.shields.io/github/repo-size/PaperL/Mxx-Compiler)
![GitHub code size in bytes](https://img.shields.io/github/languages/code-size/PaperL/Mxx-Compiler)

## 简介

- 一个用 Java 编写的 Mx* 语言编译器, 功能为将字符串格式的源代码文件编译为汇编代码
- 本项目为 ACM 班 20 级大二大作业, 题面见: [Compiler-2022](https://github.com/ACMClassCourses/Compiler-Design-Implementation)
- 目前进度: `IR & Assembly`
- 代码量: `2869 lines`（除空行与注释）



## 项目说明

- 本项目包含四部分：
  - Semantic, Codegen, Optimization, Bonus
  - 其中除 Semantic 部分使用 Antlr4 库以外, 不使用任何第三方库
  
- 各部分具体实现说明详见 [DETAIL.md](https://github.com/PaperL/Mxx-Compiler/blob/main/DETAIL.md)

- 题面基础要求以外实现的功能
  - 支持 ` \* *\ ` 注释
  - 支持逗号分割的列表末可有逗号 (例如: `fun(1, 2, 3,);`)
  - 支持含有前缀 `++` / `--` 的表达式作为左值
  
- 具体使用方式可参考 [localTest.bash](https://github.com/PaperL/Mxx-Compiler/blob/main/localTest.bash), 下表为程序运行附加参数

  - | 参数名称             | 参数功能                                            |
    | -------------------- | --------------------------------------------------- |
    | **--debug**          | 运行过程中输出易读的调试信息                        |
    | **--local**          | 使用仓库自带测试数据, 并于终端输出程序运行情况      |
    | **--semantic**       | 仅执行语法及语义检查                                |
    | **--ir-source-code** | 输出 LLVM IR 时额外以注释的形式在对应位置输出源代码 |




## 源代码文件结构

- Main
- frontend
    - parser *(Antlr4 生成)*
        - MxxLexer
        - MxxParser
        - MxxParserBaseListener
        - MxxParserListener
        - MxxParserBaseVisitor
        - MxxParserVisitor

    - ast
        - node
            - **AstNode**, NodeRoot, NodeProgramSection, NodeClassDefine, NodeFunctionDefine, NodeVariableDefine, NodeType, NodeArgumentList, NodeSuite, NodeVariableTerm, NodeExpression, NodeBracket, NodeStatement, NodeExpressionList, NodeAtom

        - scope
            - **VariableScope**, BroadScope, ClassScope, FunctionScope

        - AstPosition, AstType
        - AstBuilder, ForwardCollector, SemanticChecker

    - ir
        - node
            - **IrNode**, IrTop, IrFunction, IrBlock, IrInsturction

        - IrType, IrId
        - IrBuilder

- utility
    - error
        - **Error**, SyntaxError, SemanticError, InternalError
    - CmdArgument