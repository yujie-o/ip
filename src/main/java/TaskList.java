import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();

    private boolean outOfRange(int index) {
        return index < 0 || index >= tasks.size();
    }

    public void addTask(String line) {
        Task t = new Task(line);
        tasks.add(t);
        System.out.println("added: " + line);
    }

    public void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public void markTask(int index) {
        if (outOfRange(index)) {
            System.out.println("⚠️ There is no such task number.");
            return;
        }
        Task t = tasks.get(index);
        if (t.isDone) {
            System.out.println("⚠️ Task " + (index + 1) + " is already marked as done.");
        } else {
            t.markDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(" " + t);
        }
    }

    public void unmarkTask(int index) {
        if (outOfRange(index)) {
            System.out.println("⚠️ There is no such task number.");
            return;
        }
        Task t = tasks.get(index);
        if (t.isDone) {
            t.markNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(" " + t);
        } else {
            System.out.println("⚠️ Task " + (index + 1) + " is already marked as undone.");
        }
    }
}
