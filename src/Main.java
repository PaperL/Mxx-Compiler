import java.io.InputStream;
import java.io.FileInputStream;

import frontend.ForwardCollector;
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
        String name = "test.yx";
        InputStream input = new FileInputStream(name);

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
            NodeRoot astRoot;
            var astBuilder = new AstBuilder();
            astRoot = (NodeRoot) astBuilder.visit(parseTreeRoot);
            // AST 树构建完成后, package parser 不再被使用

            var forwardCollector = new ForwardCollector();
            forwardCollector.visitRoot(astRoot);
            // Class, class method and function name are collected
            var semanticChecker = new SemanticChecker(forwardCollector.globalScope);
            semanticChecker.visit(astRoot);

        } catch (Error error) {
            System.err.println(error);
            exceptionExist = true;
//            throw new RuntimeException();
        }

        if (!exceptionExist) System.out.println("\033[32m🎉 All work successfully finished.\033[0m");
        else System.out.println("\033[31m😭 Process terminated with error.\033[0m");

    }
}
