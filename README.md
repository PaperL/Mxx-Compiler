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
- 目前进度: `Semantic: SemanticChecker`
- 代码量: `1257 lines`

## 说明

- 本项目包含四部分：
  - Semantic, Codegen, Optimization, Bonus
  - 其中除 Semantic 部分使用 Antlr4 库以外, 不使用任何第三方库
- AST 及文件结构详见 [DETAIL.md](https://github.com/PaperL/Mxx-Compiler/blob/main/DETAIL.md)
- 代码中部分注释使用 IDEA 插件 [Comments Highlighter](https://plugins.jetbrains.com/plugin/12895-comments-highlighter) 提供高亮
- 代码量统计使用 IDEA 插件 [Statistic](https://plugins.jetbrains.com/plugin/4509-statistic) 完成, 统计方式为计算由本人创建 / 修改的文件中的代码总行数 (纯空行与注释行不计) 
- 支持 ` \* *\ ` 注释
- 支持逗号分割的列表末可有逗号 (例如: `fun(1, 2, 3,);`)