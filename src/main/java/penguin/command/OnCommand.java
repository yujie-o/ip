package penguin.command;

import penguin.app.Storage;
import penguin.app.Ui;
import penguin.exception.PenguinParseException;
import penguin.task.Deadline;
import penguin.task.Event;
import penguin.task.Task;
import penguin.task.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Shows deadlines/events that occur on a specific date (ignoring time).
 */
public class OnCommand extends Command {
    private final String dateStr; // keep raw; parse at execute to surface friendly error

    /**
     * Constructs a date filter command.
     *
     * @param dateStr date string (yyyy-MM-dd)
     */
    public OnCommand(String dateStr) {
        this.dateStr = dateStr == null ? "" : dateStr.trim();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws penguin.exception.PenguinException {
        final LocalDate date;
        try {
            date = LocalDate.parse(dateStr); // expects yyyy-MM-dd
        } catch (DateTimeParseException e) {
            throw new PenguinParseException("Invalid date format. Expected yyyy-MM-dd, e.g., 2019-12-02");
        }

        System.out.println("Here are tasks on " + date + ":");
        int i = 1;
        for (Task t : tasks.getAllTasks()) {
            if (t instanceof Deadline d && d.getByDateTime().toLocalDate().equals(date)) {
                System.out.println(i++ + "." + d);
            } else if (t instanceof Event e &&
                    (!date.isBefore(e.getFromDateTime().toLocalDate())
                            && !date.isAfter(e.getToDateTime().toLocalDate()))) {
                System.out.println(i++ + "." + e);
            }
        }
        if (i == 1) System.out.println("No tasks found.");
    }
}
