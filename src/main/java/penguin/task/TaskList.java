package penguin.task;

import penguin.exception.PenguinException;
import penguin.exception.PenguinInvalidIndexException;
import penguin.exception.PenguinStorageFullException;



public class TaskList {
    private static final int CAPACITY = 100;
    private final Task[] tasks = new Task[CAPACITY];
    private int count = 0;

    private boolean outOfRange(int index) {
        return index < 0 || index >= count;
    }

    public void addTask(Task t) throws PenguinException {
        if (count >= CAPACITY) {
            throw new PenguinStorageFullException(CAPACITY);
        }
        tasks[count++] = t;
    }

    public int size() {
        return count;
    }

    // --- Queries ---
    public void listTasks() {
        if (count == 0) {
            System.out.println("Your task list is empty.");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
    }

    // --- Updates ---
    public void markTask(int index) throws PenguinException {
        if (outOfRange(index)) {
            throw new PenguinInvalidIndexException(index + 1);
        }
        Task t = tasks[index];
        if (t.isDone) {
            System.out.println("⚠ Task " + (index + 1) + " is already marked as done.");
        } else {
            t.markDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(" " + t);
        }
    }

    public void unmarkTask(int index) throws PenguinException {
        if (outOfRange(index)) {
            throw new PenguinInvalidIndexException(index + 1);
        }
        Task t = tasks[index];
        if (t.isDone) {
            t.markNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(" " + t);
        } else {
            System.out.println("⚠ Task " + (index + 1) + " is already marked as undone.");
        }
    }
}
