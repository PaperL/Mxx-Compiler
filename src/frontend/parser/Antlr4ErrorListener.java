package frontend.parser;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import utility.error.SyntaxError;
import frontend.ast.AstPosition;

public class Antlr4ErrorListener extends BaseErrorListener {
    @Override
    public void syntaxError(Recognizer<?, ?> recognizer,
                            Object offendingSymbol,
                            int line, int charPositionInLine,
                            String msg,
                            RecognitionException e) {

        throw new SyntaxError(msg, new AstPosition(line, charPositionInLine));
    }
}
