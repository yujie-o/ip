package penguin.command;

import penguin.app.Storage;
import penguin.app.Ui;
import penguin.task.TaskList;

public class UnknownCommand extends Command {
    private final String name;
    public UnknownCommand(String name) { this.name = name; }

    @Override public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.showError("I'm sorry, but I don't know what \"" + name + "\" means :-(");
        System.out.println("Try 'help' for the list of commands.");
    }
}
