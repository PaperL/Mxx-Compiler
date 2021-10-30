import java.io.InputStream;
import java.io.FileInputStream;

import frontend.SymbolCollector;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import parser.MxxLexer;
import parser.MxxParser;
import utility.error.Error;
import utility.ErrorListener;
import utility.scope.BroadScope;
import utility.scope.VariableScope;
import ast.NodeRoot;
import frontend.AstBuilder;

public class Main {
    public static void main(String[] args) throws Exception {
        String name = "test/test.yx";
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
            NodeRoot AstRoot;
            var astBuilder = new AstBuilder();
            AstRoot = (NodeRoot) astBuilder.visit(parseTreeRoot);
            // AST 树构建完成后, package parser 不再被使用

//            var scopeRoot = new BroadScope();
//            var symbolCollector = new SymbolCollector(scopeRoot);
//            symbolCollector.visit(AstRoot);
//            new SemanticChecker(scope).visit(AstRoot);

        } catch (Error error) {
            System.err.println(error);
            exceptionExist = true;
//            throw new RuntimeException();
        }

        if (!exceptionExist) System.out.println("\033[32m🎉 All work successfully finished.\033[0m");
        else System.out.println("\033[31m😭 Process terminated with error.\033[0m");

    }
}
