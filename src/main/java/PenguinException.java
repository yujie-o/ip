public class PenguinException extends Exception{
    public PenguinException(String message) {
        super(message);
    }
}

class PenguinUnknownCommandException extends PenguinException {
    public PenguinUnknownCommandException(String cmd) {
        super("I don't know the command: \"" + cmd + "\"");
    }
}

class PenguinEmptyDescriptionException extends PenguinException {
    public PenguinEmptyDescriptionException(String cmd) {
        super("The description of a " + cmd + " cannot be empty.");
    }
}

class PenguinParseException extends PenguinException {
    public PenguinParseException(String message) {
        super(message);
    }
}

class PenguinInvalidIndexException extends PenguinException {
    public PenguinInvalidIndexException(int shownIndex) {
        super("There is no such task number: " + shownIndex);
    }
}

class PenguinStorageFullException extends PenguinException {
    public PenguinStorageFullException(int capacity) {
        super("⚠ Sorry, task list is full (" + capacity + ").");
    }
}
