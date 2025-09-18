package penguin.command;

import penguin.app.Storage;
import penguin.app.Ui;
import penguin.task.TaskList;
import penguin.exception.PenguinException;

/**
 * Abstract base class for all commands.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks   task list to operate on
     * @param ui      user interface for I/O
     * @param storage storage for saving
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws PenguinException;

    /**
     * Returns true if this command exits the program.
     */
    public boolean isExit() {
        return false;
    }

    protected static void trySave(Storage storage, TaskList tasks) {
        try {
            storage.save(tasks);
        } catch (Exception ignored) {
        }
        // Optional: you can surface the error via Ui if you prefer.
    }
}
