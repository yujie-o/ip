package penguin.exception;

public class PenguinUnknownCommandException extends PenguinException {
    public PenguinUnknownCommandException(String cmd) {
        super("I don't know the command: \"" + cmd + "\"");
    }
}