package utility.error;

public class InternalError extends Error {
    public InternalError(String type, String message) {
        super(type, message, null);
    }

    @Override
    public String toString() {
        return ("[Internal] " + errorType + ": " + message);
    }
}