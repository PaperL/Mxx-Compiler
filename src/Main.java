// JDK

import backend.asm.AsmBuilder;
import backend.optimization.AsmOptimizer;
import backend.optimization.IrOptimizer;
import frontend.ast.AstBuilder;
import frontend.ast.ForwardCollector;
import frontend.ast.SemanticChecker;
import frontend.ast.node.NodeRoot;
import frontend.ir.IrBuilder;
import frontend.parser.Antlr4ErrorListener;
import frontend.parser.MxxLexer;
import frontend.parser.MxxParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import utility.CmdArgument;
import utility.error.Error;
import utility.error.InternalError;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        var cmdArgs = new CmdArgument(args);
        InputStream inputStream;
        PrintStream outputStream = null;
        if (cmdArgs.contains(CmdArgument.ArgumentType.LOCAL)) {
            System.out.println("Please input testcase name:");
            inputStream = new FileInputStream(
                    "test/" + (new Scanner(System.in)).nextLine() + ".mx");
            outputStream = System.out;
        } else {
            inputStream = System.in;
            outputStream = new PrintStream(new OutputStream() {
                @Override
                public void write(int b) {
                }   // Just do nothing
            });
        }
//        var stdIn = System.in;
        var rstFile = new File("output.ll");
//        if (!rstFile.exists()) rstFile.createNewFile();
        var rstOut = new FileWriter(rstFile);   // FileWriter 会自动创建文件

        var allStageFinished = false;
        try {
            // ! FRONTEND
            // * Lexically analyze and parse the source code, get AST
            // MxxLexer, MxxParser, ParseTree 等均来自 Antlr4 生成
            var lexer = new MxxLexer(CharStreams.fromStream(inputStream));
            lexer.removeErrorListeners();
            lexer.addErrorListener(new Antlr4ErrorListener());

            var parser = new MxxParser(new CommonTokenStream(lexer));
            parser.removeErrorListeners();
            parser.addErrorListener(new Antlr4ErrorListener());

            var parseTreeRoot = parser.program();
            outputStream.println("\033[36m🔨 Lexer and frontend.parser finished.\033[0m");

            NodeRoot astRoot;
            var astBuilder = new AstBuilder();
            astRoot = (NodeRoot) astBuilder.visit(parseTreeRoot);
            astBuilder.generateBuiltIn(astRoot);
            outputStream.println("\033[36m🔨 Building AST finished.\033[0m");
            // AST 树构建完成后, package frontend.parser 不再被使用

            // * Semantic Check on AST
            var forwardCollector = new ForwardCollector();
            forwardCollector.collectRoot(astRoot);
            outputStream.println("\033[36m🔨 Collecting forward reference symbol finished.\033[0m");
            // Class, class method and function name are collected

            var semanticChecker = new SemanticChecker(forwardCollector.globalScope);
            semanticChecker.checkRoot(astRoot);
            outputStream.println("\033[36m🔨 Semantic check finished.\033[0m");
            if (cmdArgs.contains(CmdArgument.ArgumentType.SEMANTIC))
                throw new InternalError("Finished", "Semantic Check");

            // * Generate LLVM IR from AST
            var irBuilder = new IrBuilder(cmdArgs);
            irBuilder.buildRoot(astRoot);
            outputStream.println("\033[36m🔨 IR generation finished.\033[0m");
            outputStream.println("\033[33m🎗️ Frontend worked successfully.\033[0m");
            if (cmdArgs.contains(CmdArgument.ArgumentType.IR)) {
                rstOut.write(irBuilder.print());
                throw new InternalError("Finished", "Generate LLVM IR");
            }
            var irResult = IrBuilder.irRoot;

            // ! BACKEND
            // * Optimize IR
            var irOptimizer = new IrOptimizer(cmdArgs);
            irOptimizer.work(irResult);

            // * Generate Assembly from IR
            var asmBuilder = new AsmBuilder(cmdArgs);
            asmBuilder.buildRoot(irResult);
            var asmResult = AsmBuilder.asmRoot;

            // * Optimize Assembly
            var asmOptimizer = new AsmOptimizer(cmdArgs);
            asmOptimizer.work(asmResult);

            // Finish Compiling
            rstOut.write(asmBuilder.print());
            outputStream.println("\033[33m🎗️ Backend worked successfully.\033[0m");
            allStageFinished = true;
        } catch (Error err) {
//            System.err.println(error);
            if (Objects.equals(err.errorType, "Finished"))
                outputStream.println("Compiler stops at stage \"" + err.message + "\".");
            else {
                err.printStackTrace();    // 输出异常位置
                outputStream.println("\033[31m⚠️ Process terminated with error.\033[0m");
                throw new RuntimeException("Compiling failed.");
            }
        }
        if (allStageFinished) outputStream.println("\033[32m🎉 All work successfully finished.\033[0m");
        if (cmdArgs.contains(CmdArgument.ArgumentType.SEMANTIC))
            outputStream.println("No compiling result.");
        else if (cmdArgs.contains(CmdArgument.ArgumentType.IR))
            outputStream.println("See IR result in \"output.ll\"");
        else outputStream.println("See Assembly result in \"output.s\"");
        rstOut.close();
    }
}