import java.io.InputStream;

import frontend.ForwardCollector;
import frontend.IrBuilder;
import frontend.SemanticChecker;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import parser.MxxLexer;
import parser.MxxParser;
import utility.error.Error;
import utility.ErrorListener;
import ast.NodeRoot;
import frontend.AstBuilder;

public class Main {
    public static void main(String[] args) throws Exception {
//        var keyBoardInput = new Scanner(System.in);
//        String inputFileName = "test/all/" + keyBoardInput.nextLine() + ".mx";
//        InputStream input = new FileInputStream(inputFileName);
        InputStream input = System.in;

        boolean exceptionExist = false;
        try {
            // MxxLexer, MxxParser, ParseTree 等均来自 Antlr4 生成
            var lexer = new MxxLexer(CharStreams.fromStream(input));
            lexer.removeErrorListeners();
            lexer.addErrorListener(new ErrorListener());

            var parser = new MxxParser(new CommonTokenStream(lexer));
            parser.removeErrorListeners();
            parser.addErrorListener(new ErrorListener());

            var parseTreeRoot = parser.program();
            System.out.println("\033[36m🔨 Lexer and parser finished.\033[0m");

            NodeRoot astRoot;
            var astBuilder = new AstBuilder();
            astRoot = (NodeRoot) astBuilder.visit(parseTreeRoot);
            System.out.println("\033[36m🔨 Building AST finished.\033[0m");
            // AST 树构建完成后, package parser 不再被使用

            var forwardCollector = new ForwardCollector();
            forwardCollector.collectRoot(astRoot);
            System.out.println("\033[36m🔨 Collecting forward reference symbol finished.\033[0m");
            // Class, class method and function name are collected
            var semanticChecker = new SemanticChecker(forwardCollector.globalScope);
            semanticChecker.checkRoot(astRoot);
            System.out.println("\033[36m🔨 Semantic check finished.\033[0m");

            var IrBuilder = new IrBuilder();
            IrBuilder.build(astRoot);
            System.out.println("\033[36m🔨 IR generation finished.\033[0m");

            System.out.println("\033[33m🎗️ Frontend worked successfully.\033[0m");
        } catch (Error error) {
            System.err.println(error);
            exceptionExist = true;
//            throw new RuntimeException();
        }

        if (!exceptionExist) System.out.println("\033[32m🎉 All work successfully finished.\033[0m");
        else {
            System.out.println("\033[31m⚠️ Process terminated with error.\033[0m");
            throw new RuntimeException("Semantic check failed.");
        }
    }
}