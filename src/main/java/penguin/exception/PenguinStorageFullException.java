package penguin.exception;

public class PenguinStorageFullException extends PenguinException {
    public PenguinStorageFullException(int capacity) {
        super("☹ OOPS!!! The task list is full (capacity: " + capacity + ")");
    }
}
