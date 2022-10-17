package utility.error;

public class StopException extends RuntimeException {
    public StopException(String message) {
        super(message);
    }
}
