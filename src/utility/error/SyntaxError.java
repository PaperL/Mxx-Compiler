package utility.error;

import utility.AstPosition;

public class SyntaxError extends Error {

    public SyntaxError(String msg, AstPosition pos) {
        super("syntax error", msg, pos);
    }
}