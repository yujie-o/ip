package penguin.command;

import penguin.app.Storage;
import penguin.app.Ui;
import penguin.task.TaskList;

/**
 * Terminates the application.
 */
public class ExitCommand extends Command {

    /**
     * Prints a goodbye message.
     *
     * @param tasks   task list model (unused)
     * @param ui      UI boundary
     * @param storage storage component (unused)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.showBye();
    }

    /**
     * Signals the main loop to exit after execution.
     *
     * @return always {@code true}
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
