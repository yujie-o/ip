package penguin.command;

import penguin.app.Storage;
import penguin.app.Ui;
import penguin.exception.PenguinParseException;
import penguin.task.*;

/**
 * Adds a {@link Deadline} task to the list.
 */
public class AddDeadlineCommand extends Command {
    private final String desc, by;

    /**
     * Constructs a command to add a Deadline.
     *
     * @param desc description of the task
     * @param by   due datetime string (as parsed later by Deadline)
     */
    public AddDeadlineCommand(String desc, String by) {
        this.desc = desc;
        this.by = by;
    }

    /**
     * Creates the Deadline, appends, prints confirmation, persists.
     *
     * @param tasks   task list model
     * @param ui      UI boundary
     * @param storage storage component
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PenguinParseException {
        Deadline d = new Deadline(desc, by);
        tasks.addTask(d);
        Ui.showAdded(d, tasks.size());
        trySave(storage, tasks);
    }
}
