package penguin.exception;

public class PenguinEmptyDescriptionException extends PenguinException {
    public PenguinEmptyDescriptionException(String cmd) {
        super("The description of a " + cmd + " cannot be empty.");
    }
}