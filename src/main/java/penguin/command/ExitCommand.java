package penguin.command;

import penguin.app.Storage;
import penguin.app.Ui;
import penguin.task.TaskList;

public class ExitCommand extends Command {
    @Override public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
    }
    @Override public boolean isExit() { return true; }
}
