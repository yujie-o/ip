package penguin.exception;

public class PenguinInvalidIndexException extends PenguinException {
    public PenguinInvalidIndexException(int shownIndex) {
        super("There is no such task number: " + shownIndex);
    }
}
