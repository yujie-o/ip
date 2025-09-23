package penguin.command;

import penguin.app.Storage;
import penguin.app.Ui;
import penguin.task.*;

/**
 * Adds a {@link ToDo} task to the list.
 */
public class AddTodoCommand extends Command {
    private final String desc;

    /**
     * Constructs a command to add a ToDo task.
     *
     * @param desc task description
     */
    public AddTodoCommand(String desc) {
        this.desc = desc;
    }

    /**
     * Creates the ToDo, appends it to the list, echoes to UI, then persists.
     *
     * @param tasks   task list model
     * @param ui      UI boundary
     * @param storage storage component
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ToDo t = new ToDo(desc);
        tasks.addTask(t);
        Ui.showAdded(t, tasks.size());
        trySave(storage, tasks);
    }
}
