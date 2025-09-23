package penguin.exception;

/**
 * Thrown when a user-supplied index is missing or invalid.
 */
public class PenguinInvalidIndexException extends PenguinException {

    /**
     * Constructs with the invalid index supplied as text.
     *
     * @param shownIndex raw index string or number
     */
    public PenguinInvalidIndexException(int shownIndex) {
        super("There is no such task number: " + shownIndex);
    }
}
