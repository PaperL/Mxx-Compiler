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
- 目前进度: `Assembly`
- 代码量: `3451 lines`（不计空行与注释）



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
  
- 下表为程序运行附加参数, 运行脚本见 [DETAIL.md](https://github.com/PaperL/Mxx-Compiler/blob/main/DETAIL.md) 说明

  - | 参数名称               | 参数功能                                       |
    | ---------------------- | ---------------------------------------------- |
    | **--debug**            | 运行过程中输出易读的调试信息                   |
    | **--local**            | 使用仓库自带测试数据, 并于终端输出程序运行情况 |
    | **--semantic**         | 仅执行语法及语义检查                           |
    | **--ir**               | 仅生成 LLVM IR 并输出                          |
    | **--ir-source-code**   | 输出 LLVM IR 时添加源代码注释                  |
    | **--assembly-comment** | 输出汇编程序时添加注释                         |
    | **-O**                 | 开启所有优化                                   |



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
        - IrBuilder, IrOptimizer
- *backend*
    - *asm*
        - *node*
            - **AsmNode**, AsmTop, AsmFunction, AsmBlock, AsmInstruction
        - AsmId, AsmStackFrame
        - AsmBuilder, AsmOptimizer
- *utility*
    - *error*
        - **Error**, SyntaxError, SemanticError, InternalError
    - CmdArgument