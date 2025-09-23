package penguin.command;

import penguin.app.Storage;
import penguin.app.Ui;
import penguin.task.TaskList;

/**
 * Fallback for unrecognized commands.
 */
public class UnknownCommand extends Command {
    private final String name;

    /**
     * Constructs an unknown-command wrapper.
     *
     * @param name raw command token provided by the user
     */
    public UnknownCommand(String name) {
        this.name = name;
    }

    /**
     * Prints a helpful error and a hint to use help.
     *
     * @param tasks   task list model (unused)
     * @param ui      UI boundary
     * @param storage storage component (unused)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.showError("I'm sorry, but I don't know what \"" + name + "\" means :-(");
        System.out.println("Try 'help' for the list of commands.");
    }
}
