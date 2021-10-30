import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import parser.MxxLexer;
import parser.MxxParser;
import utility.error.Error;
import utility.ErrorListener;
import utility.Scope;
import ast.NodeRoot;
import frontend.AstBuilder;

public class Main {
    public static void main(String[] args) throws Exception {
        String name = "test/test.yx";
        InputStream input = new FileInputStream(name);

        try {
            MxxLexer lexer = new MxxLexer(CharStreams.fromStream(input));
            lexer.removeErrorListeners();
            lexer.addErrorListener(new ErrorListener());

            MxxParser parser = new MxxParser(new CommonTokenStream(lexer));
            parser.removeErrorListeners();
            parser.addErrorListener(new ErrorListener());

            NodeRoot ASTRoot;
            Scope scope = new Scope(null);

            ParseTree parseTreeRoot = parser.program();
            AstBuilder astBuilder = new AstBuilder();

            ASTRoot = (NodeRoot) astBuilder.visit(parseTreeRoot);
//            new SymbolCollector(scope).visit(ASTRoot);
//            new SemanticChecker(scope).visit(ASTRoot);

        } catch (Error error) {
            System.err.println(error);
            throw new RuntimeException();
        }

        System.out.println("Done.");

    }
}
