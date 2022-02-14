import java.io.*;
import java.util.Scanner;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import utility.CmdArgument;
import utility.error.Error;

import frontend.parser.MxxLexer;
import frontend.parser.MxxParser;
import frontend.parser.Antlr4ErrorListener;
import frontend.ast.node.NodeRoot;
import frontend.ast.AstBuilder;
import frontend.ast.ForwardCollector;
import frontend.ast.SemanticChecker;
import frontend.ir.IrBuilder;

public class Main {
    public static void main(String[] args) throws Exception {
        var cmdArgs = new CmdArgument(args);
        InputStream inputStream = null;
        if (cmdArgs.contains(CmdArgument.ArgumentType.LOCAL)) {
            System.out.println("Please input testcase name:");
            inputStream = new FileInputStream(
                    "test/all/" + (new Scanner(System.in)).nextLine() + ".mx");
        } else inputStream = System.in;
//        var stdIn = System.in;
        var stdOut = System.out;
        var rstFile = new File("output.ll");
//        if (!rstFile.exists()) rstFile.createNewFile();
        var rstOut = new FileWriter(rstFile);   // FileWriter 会自动创建文件

        try {
            // * Frontend
            // Semantic check
            // MxxLexer, MxxParser, ParseTree 等均来自 Antlr4 生成
            var lexer = new MxxLexer(CharStreams.fromStream(inputStream));
            lexer.removeErrorListeners();
            lexer.addErrorListener(new Antlr4ErrorListener());

            var parser = new MxxParser(new CommonTokenStream(lexer));
            parser.removeErrorListeners();
            parser.addErrorListener(new Antlr4ErrorListener());

            var parseTreeRoot = parser.program();
            stdOut.println("\033[36m🔨 Lexer and frontend.parser finished.\033[0m");

            NodeRoot astRoot;
            var astBuilder = new AstBuilder();
            astRoot = (NodeRoot) astBuilder.visit(parseTreeRoot);
            stdOut.println("\033[36m🔨 Building AST finished.\033[0m");
            // AST 树构建完成后, package frontend.parser 不再被使用

            var forwardCollector = new ForwardCollector();
            forwardCollector.collectRoot(astRoot);
            stdOut.println("\033[36m🔨 Collecting forward reference symbol finished.\033[0m");
            // Class, class method and function name are collected
            var semanticChecker = new SemanticChecker(forwardCollector.globalScope);
            semanticChecker.checkRoot(astRoot);
            stdOut.println("\033[36m🔨 Semantic check finished.\033[0m");

            stdOut.println("\033[33m🎗️ Frontend worked successfully.\033[0m");

            // * Backend
            if (!cmdArgs.contains(CmdArgument.ArgumentType.SEMANTIC)) {
                // Generate LLVM IR
                var IrBuilder = new IrBuilder();
                IrBuilder.buildRoot(astRoot);
                rstOut.write(IrBuilder.print());
                stdOut.println("\033[36m🔨 IR generation finished.\033[0m");

                // Generate assembly
                // todo

                stdOut.println("\033[33m🎗️ Backend worked successfully.\033[0m");
            }
        } catch (Error error) {
//            System.err.println(error);
            error.printStackTrace();    // 输出异常位置
            stdOut.println("\033[31m⚠️ Process terminated with error.\033[0m");
            throw new RuntimeException("Compiling failed.");
        }
        stdOut.println("\033[32m🎉 All work successfully finished.\033[0m");
        if (!cmdArgs.contains(CmdArgument.ArgumentType.SEMANTIC))
            stdOut.println("See IR result in \"output.ll\"");

        rstOut.close();
    }
}