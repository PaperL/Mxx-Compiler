package utility.error;

import utility.AstPosition;

abstract public class Error extends RuntimeException {
    protected final String errorType, message;
    protected final AstPosition position;

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
