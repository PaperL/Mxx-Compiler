package utility.error;

import frontend.ast.AstPosition;

public class SemanticError extends Error {

    public SemanticError(String msg, AstPosition pos) {
        super("semantic error", msg, pos);
    }
}
