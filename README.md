# Mx* Compiler

[![Latest tag](https://img.shields.io/github/v/tag/PaperL/Mxx-Compiler)](https://github.com/PaperL/Mxx-Compiler/tags)
[![Last commit](https://img.shields.io/github/last-commit/PaperL/Mxx-Compiler)](https://github.com/PaperL/Mxx-Compiler/commits/)
[![Programming language](https://img.shields.io/badge/language-java-B07219.svg)](http://jdk.java.net/17/)
[![License](https://img.shields.io/github/license/PaperL/Mxx-Compiler)](https://www.gnu.org/licenses/gpl-3.0.html)

## 简介

- 一个用 Java 编写的 Mx* 语言编译器
- 本项目为 ACM 班 20 级大二大作业, 题面见: [Compiler-2022](https://github.com/ACMClassCourses/Compiler-Design-Implementation)
- 目前进度: `Parser & Lexer`

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

## 其他说明

- 可能会实现一些题面未要求的特性