package penguin.exception;

/**
 * Signals a problem parsing user input into a valid command or arguments.
 */
public class PenguinParseException extends PenguinException {

    /**
     * Constructs with a message.
     *
     * @param message details of the parse failure
     */
    public PenguinParseException(String message) {
        super(message);
    }
}