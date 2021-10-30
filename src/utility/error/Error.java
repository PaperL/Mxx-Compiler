package utility.error;

import utility.Position;

abstract public class Error extends RuntimeException {
    private final String errorType, message;
    private final Position position;

    public Error(String errType, String msg, Position pos) {
        this.errorType = errType;
        this.message = msg;
        this.position = pos;
    }

    @Override
    public String toString() {
        return position.toString() + ": " + errorType + ": " + message;
    }
}
