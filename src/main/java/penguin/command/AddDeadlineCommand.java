package penguin.command;

import penguin.app.Storage;
import penguin.app.Ui;
import penguin.task.*;

public class AddDeadlineCommand extends Command {
    private final String desc, by;

    public AddDeadlineCommand(String desc, String by) {
        this.desc = desc;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Deadline d = new Deadline(desc, by);
        tasks.addTask(d);
        Ui.showAdded(d, tasks.size());
        trySave(storage, tasks);
    }
}
