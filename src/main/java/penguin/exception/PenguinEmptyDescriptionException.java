package penguin.exception;

/**
 * Thrown when an add-type command is missing a mandatory description.
 */
public class PenguinEmptyDescriptionException extends PenguinException {

    /**
     * Constructs with the missing command name for better error text.
     *
     * @param cmd command token (e.g., "todo")
     */
    public PenguinEmptyDescriptionException(String cmd) {
        super("The description of a " + cmd + " cannot be empty.");
    }
}