package penguin.command;

import penguin.app.Storage;
import penguin.app.Ui;
import penguin.task.TaskList;
import penguin.exception.PenguinException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws PenguinException;
    public boolean isExit() { return false; }
    protected static void trySave(Storage storage, TaskList tasks) {
        try { storage.save(tasks); } catch (Exception ignored) { }
        // Optional: you can surface the error via Ui if you prefer.
    }
}
