package penguin.exception;

/**
 * Base type for all Penguin-specific checked exceptions.
 */
public class PenguinException extends Exception {

    /**
     * Constructs with a message.
     *
     * @param message error message
     */
    public PenguinException(String message) {
        super(message);
    }
}