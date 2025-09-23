package penguin.command;

import penguin.app.Storage;
import penguin.app.Ui;
import penguin.exception.PenguinParseException;
import penguin.task.*;

/**
 * Adds an {@link Event} task to the list.
 */
public class AddEventCommand extends Command {
    private final String desc, from, to;

    /**
     * Constructs a command to add an Event.
     *
     * @param desc event description
     * @param from start datetime string
     * @param to   end datetime string
     */
    public AddEventCommand(String desc, String from, String to) {
        this.desc = desc;
        this.from = from;
        this.to = to;
    }

    /**
     * Creates the Event, appends, prints confirmation, persists.
     *
     * @param tasks   task list model
     * @param ui      UI boundary
     * @param storage storage component
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PenguinParseException {
        Event e = new Event(desc, from, to);
        tasks.addTask(e);
        Ui.showAdded(e, tasks.size());
        trySave(storage, tasks);
    }
}
