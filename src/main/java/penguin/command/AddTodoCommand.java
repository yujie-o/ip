package penguin.command;

import penguin.app.Storage;
import penguin.app.Ui;
import penguin.task.*;

public class AddTodoCommand extends Command {
    private final String desc;
    public AddTodoCommand(String desc) { this.desc = desc; }
    @Override public void execute(TaskList tasks, Ui ui, Storage storage) {
        ToDo t = new ToDo(desc);
        tasks.addTask(t);
        Ui.showAdded(t, tasks.size());
        trySave(storage, tasks);
    }
}
