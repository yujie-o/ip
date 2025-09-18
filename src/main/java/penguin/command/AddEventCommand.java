package penguin.command;

import penguin.app.Storage;
import penguin.app.Ui;
import penguin.task.*;

public class AddEventCommand extends Command {
    private final String desc, from, to;
    public AddEventCommand(String desc, String from, String to) { this.desc = desc; this.from = from; this.to = to; }
    @Override public void execute(TaskList tasks, Ui ui, Storage storage) {
        Event e = new Event(desc, from, to);
        tasks.addTask(e);
        Ui.showAdded(e, tasks.size());
        trySave(storage, tasks);
    }
}
