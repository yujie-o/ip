package penguin.task;

import penguin.exception.PenguinException;
import penguin.exception.PenguinInvalidIndexException;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks = new ArrayList<>();

    public void addTask(Task t) {
        tasks.add(t);
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int index) throws PenguinException {
        if (index < 0 || index >= tasks.size()) {
            throw new PenguinInvalidIndexException(index + 1);
        }
        return tasks.get(index);
    }

    // --- Queries ---
    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Your task list is empty.");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    // --- Updates ---
    public void markTask(int index) throws PenguinException {
        if (index < 0 || index >= tasks.size()) {
            throw new PenguinInvalidIndexException(index + 1);
        }
        Task t = tasks.get(index);
        if (t.isDone()) {
            System.out.println("⚠ Task " + (index + 1) + " is already marked as done.");
        } else {
            t.markDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(" " + t);
        }
    }

    public void unmarkTask(int index) throws PenguinException {
        if (index < 0 || index >= tasks.size()) {
            throw new PenguinInvalidIndexException(index + 1);
        }
        Task t = tasks.get(index);
        if (t.isDone()) {
            t.markNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(" " + t);
        } else {
            System.out.println("⚠ Task " + (index + 1) + " is already marked as undone.");
        }
    }

    public Task deleteTask(int index) throws PenguinException {
        if (index < 0 || index >= tasks.size()) {
            throw new PenguinInvalidIndexException(index + 1);
        }
        return tasks.remove(index);
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks); // return a copy so caller can't modify internals
    }

}
