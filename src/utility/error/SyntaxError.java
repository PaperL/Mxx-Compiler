package utility.error;

import utility.Position;

public class SyntaxError extends Error {

    public SyntaxError(String msg, Position pos) {
        super("syntax error", msg, pos);
    }
}