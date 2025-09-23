package penguin.command;

import penguin.app.Storage;
import penguin.app.Ui;
import penguin.task.TaskList;

/**
 * Shows the help manual.
 */
public class HelpCommand extends Command {

    /**
     * Prints the help manual.
     *
     * @param tasks   task list model (unused)
     * @param ui      UI boundary
     * @param storage storage component (unused)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.showManual();
    }
}
