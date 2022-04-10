package utility.error;

import frontend.ast.AstPosition;

abstract public class Error extends RuntimeException {
    public final String errorType, message;
    public final AstPosition position;

    public Error(String errType, String msg, AstPosition pos) {
        this.errorType = errType;
        this.message = msg;
        this.position = pos;
    }

    @Override
    public String toString() {
        return position.toString() + ": " + errorType + ": " + message;
    }
}
