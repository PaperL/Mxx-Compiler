package utility.error;

import utility.AstPosition;

public class SemanticError extends Error {

    public SemanticError(String msg, AstPosition pos) {
        super("semantic error", msg, pos);
    }
}
