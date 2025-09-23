package penguin.command;

import penguin.app.Storage;
import penguin.app.Ui;
import penguin.task.TaskList;

/**
 * Lists all tasks currently stored.
 */
public class ListCommand extends Command {

    /**
     * Prints the full task list to the console.
     *
     * @param tasks   task list model
     * @param ui      UI boundary
     * @param storage storage component (unused)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.listTasks();
    }
}
