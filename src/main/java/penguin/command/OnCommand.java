package penguin.command;

import penguin.app.Storage;
import penguin.app.Ui;
import penguin.task.Deadline;
import penguin.task.Event;
import penguin.task.Task;
import penguin.task.TaskList;

import java.time.LocalDate;

public class OnCommand extends Command {
    private final LocalDate date;

    public OnCommand(String dateStr) {
        this.date = LocalDate.parse(dateStr); // yyyy-MM-dd
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("Here are tasks on " + date + ":");
        int i = 1;
        for (Task t : tasks.getAllTasks()) {
            if (t instanceof Deadline d && d.getByDateTime().toLocalDate().equals(date)) {
                System.out.println(i++ + "." + d);
            } else if (t instanceof Event e &&
                    (!date.isBefore(e.getFromDateTime().toLocalDate()) && !date.isAfter(e.getToDateTime().toLocalDate()))) {
                System.out.println(i++ + "." + e);
            }
        }
        if (i == 1) System.out.println("No tasks found.");
    }
}

