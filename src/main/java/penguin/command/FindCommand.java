package penguin.command;

import penguin.app.Storage;
import penguin.app.Ui;
import penguin.exception.PenguinException;
import penguin.task.Task;
import penguin.task.TaskList;

/**
 * Finds tasks whose description contains a given keyword (case-insensitive).
 * Usage: find <keyword>
 */
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PenguinException {
        System.out.println("Here are the matching tasks in your list:");
        int shown = 0;

        // no need to add new methods to TaskList; just use size()/get(i)
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            if (t.getDescription().toLowerCase().contains(keyword)) {
                System.out.println((++shown) + "." + t);
            }
        }

        if (shown == 0) {
            System.out.println("No matching tasks found!");
        }
    }
}
