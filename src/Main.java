// JDK

import backend.asm.AsmBuilder;
import backend.optimization.AsmOptimizer;
import frontend.ast.AstBuilder;
import frontend.ast.ForwardCollector;
import frontend.ast.SemanticChecker;
import frontend.ir.IrBuilder;
import frontend.parser.Antlr4ErrorListener;
import frontend.parser.MxxLexer;
import frontend.parser.MxxParser;
import middle_end.IrOptimizer;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import utility.CmdArgument;
import utility.Constant;
import utility.error.StopException;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        var cmdArgs = new CmdArgument(args);
        Constant.cmdArgs = cmdArgs;
        InputStream inputStream;
        PrintStream outputStream = null;
        if (cmdArgs.contains(CmdArgument.ArgumentType.LOCAL)) {
            System.out.println("Please input testcase name (testspace/*.mx):");
            Constant.SOURCE_FILENAME = "testspace/" + (new Scanner(System.in)).nextLine() + ".mx";
            inputStream = new FileInputStream(Constant.SOURCE_FILENAME);
            outputStream = System.out;
        } else {
            Constant.SOURCE_FILENAME = "stdin";
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

        var targetTask = CmdArgument.ArgumentType.ASM;
        if (cmdArgs.contains(CmdArgument.ArgumentType.SEMANTIC))
            targetTask = CmdArgument.ArgumentType.SEMANTIC;
        else if (cmdArgs.contains(CmdArgument.ArgumentType.IR)) {
            targetTask = CmdArgument.ArgumentType.IR;
            Constant.POINTER_SIZE = 8; // Clang cannot run 32-bit LLVM IR
        }
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


            var astBuilder = new AstBuilder(parseTreeRoot);
            var astRoot = astBuilder.astRoot;
            astBuilder.build();
            astBuilder.generateBuiltIn();
            outputStream.println("\033[36m🔨 Building AST finished.\033[0m");
            // AST 树构建完成后, package frontend.parser 不再被使用

            // * Semantic Check on AST
            var forwardCollector = new ForwardCollector(astRoot);
            forwardCollector.work();
            outputStream.println("\033[36m🔨 Collecting forward reference symbol finished.\033[0m");
            // Class, class method and function name are collected

            var semanticChecker = new SemanticChecker(astRoot, forwardCollector.getScope());
            semanticChecker.work();
            outputStream.println("\033[36m🔨 Semantic check finished.\033[0m");
            if (targetTask == CmdArgument.ArgumentType.SEMANTIC)
                throw new StopException("Semantic Check");

            // * Generate LLVM IR from AST
            var irBuilder = new IrBuilder(astRoot);
            var irRoot = irBuilder.irRoot;
            irBuilder.build();
            outputStream.println("\033[36m🔨 IR generation finished.\033[0m");
            outputStream.println("\033[33m🎗️ Frontend worked successfully.\033[0m");
            if (targetTask == CmdArgument.ArgumentType.IR) {
                rstOut.write(irBuilder.print());
                throw new StopException("Generate LLVM IR");
            }

            // ! MIDDLE END
            // * Optimize IR
            var irOptimizer = new IrOptimizer(irRoot);
            irOptimizer.work();

            // ! BACKEND
            // * Generate Assembly from IR
            var asmBuilder = new AsmBuilder(irRoot);
            var asmRoot = asmBuilder.asmRoot;
            asmBuilder.build();

            // * Optimize Assembly
            var asmOptimizer = new AsmOptimizer(asmRoot);
            asmOptimizer.work();

            // Finish Compiling
            rstOut.write(asmBuilder.print());
            outputStream.println("\033[33m🎗️ Backend worked successfully.\033[0m");
            allStageFinished = true;
        } catch (Throwable exception) {
//            System.err.println(error);
            if (exception instanceof StopException)
                outputStream.println("Compiler stops at stage \"" + exception.getMessage() + "\".");
            else {
                exception.printStackTrace();    // 输出异常位置
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