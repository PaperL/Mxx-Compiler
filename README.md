# Mxx Compiler

[![GitHub top language](https://img.shields.io/github/languages/top/PaperL/Mxx-Compiler)](http://jdk.java.net/17/)
[![License](https://img.shields.io/github/license/PaperL/Mxx-Compiler)](https://www.gnu.org/licenses/gpl-3.0.html)

[![Latest tag](https://img.shields.io/github/v/tag/PaperL/Mxx-Compiler)](https://github.com/PaperL/Mxx-Compiler/tags)
[![Last commit](https://img.shields.io/github/last-commit/PaperL/Mxx-Compiler)](https://github.com/PaperL/Mxx-Compiler/commits/)
![GitHub repo size](https://img.shields.io/github/repo-size/PaperL/Mxx-Compiler)
![GitHub code size in bytes](https://img.shields.io/github/languages/code-size/PaperL/Mxx-Compiler)

## 简介

- 一个用 Java 编写的 Mx* 语言编译器, 功能为将 Mx* 语言源代码编译为汇编代码
- 本项目为 ACM 班 20 级大二学年大作业, 题面见: [Compiler-2022](https://github.com/ACMClassCourses/Compiler-Design-Implementation)
- 目前进度: `Assembly`
- 代码量: `3525 lines`（不计空行与注释）



## 项目说明

- 本项目包含四部分：
  - Semantic, Codegen, Optimization, Bonus
  - 其中除 Semantic 部分使用 Antlr4 库以外, 不使用任何第三方库

- 各部分具体实现说明详见 [DETAIL.md](https://github.com/PaperL/Mxx-Compiler/blob/main/DETAIL.md)

- 实现题面未定义功能 (标记 🍸 表示该功能不支持 Semantic Check)
  - 支持 ` \* *\ ` 注释
  - 支持逗号分割的列表末可有逗号 (例如: `fun(1, 2, 3,);`)
  - 支持含有前缀 `++` / `--` 的表达式作为左值
  - 🍸 支持含参构造函数
  - 除 `\n`, `\t` 外支持 `\"`
- 运行方式
  - 在项目根目录下使用 `make <cmd>` 指令执行对应操作
    - `build` / `clean`: 构建项目 / 清理运行内容
    - `semantic`: 进行语法语义分析
    - `ir`: 生成 LLVM IR
    - `run`: 生成 RISC-V Assembly
    - `test_ir`: 自动评测 LLVM IR 正确性
      - 使用 `make test_ir TESTCASE=<name>` 指令评测单个测试点，具体评测方式详见 [ir_local_judge.py](https://github.com/PaperL/Mxx-Compiler/blob/main/run/ir_local_judge.py)




## 源代码结构

> 斜体为包名，加粗类名表示后续类与该类有实现或继承关系

- Main

- *frontend*
    - *parser (由第三方库 Antlr4 生成)*
        - MxxLexer
        - MxxParser
        - **MxxParserListener**, MxxParserBaseListener
        - **MxxParserVisitor**, MxxParserBaseVisitor
    - *ast*
        - *node*
                - **AstNode**, NodeRoot, NodeProgramSection, NodeClassDefine, NodeFunctionDefine, NodeVariableDefine, NodeType, NodeArgumentList, NodeSuite, NodeVariableTerm, NodeExpression, NodeBracket, NodeStatement, NodeExpressionList, NodeAtom
        - *scope*
            - **VariableScope**, BroadScope, ClassScope, FunctionScope
        - AstPosition, AstType
        - AstBuilder, ForwardCollector, SemanticChecker
    
    - *ir*
        - *node*
            - **IrNode**, IrTop, IrClass, IrFunction, IrBlock, IrInsturction
        - IrType, IrId
        - IrBuilder

- *backend*
    - *asm*
        - *node*
            - **AsmNode**, AsmTop, AsmFunction, AsmBlock, AsmInstruction
        - AsmId, AsmStackFrame
        - AsmBuilder
    - *optimization*
        - IrOptimizer, AsmOptimiz
    
- *utility*
    - *error*
        - **Error**, SyntaxError, SemanticError, InternalError
    - Constant
    - CmdArgument