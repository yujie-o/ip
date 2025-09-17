package penguin.app;

import penguin.task.Deadline;
import penguin.task.Event;
import penguin.task.TaskList;
import penguin.task.ToDo;
import penguin.exception.PenguinException;
import penguin.exception.PenguinUnknownCommandException;
import penguin.exception.PenguinEmptyDescriptionException;
import penguin.exception.PenguinParseException;
import penguin.exception.PenguinInvalidIndexException;

import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Penguin {
    private static final String NAME = "Penguin";

    public static void main(String[] args) {
        Path savePath = Paths.get("data", "penguin.txt");   // relative, OS-independent
        Storage storage = new Storage(savePath);
        TaskList taskList = storage.loadOrEmpty();

        Ui.showGreeting(NAME);

        try (Scanner in = new Scanner(System.in)) {
            while (true) {
                System.out.print(Ui.PROMPT);
                String input = in.nextLine().trim();
                Ui.showDivider();

                try {
                    if (input.isEmpty()) {
                        throw new PenguinParseException("You typed nothing. Try one of: todo, deadline, event, list, mark, unmark, bye.");
                    }

                    String[] tokens = input.split("\\s+", 2);
                    String command = tokens[0].toLowerCase();
                    String payload = tokens.length > 1 ? tokens[1] : "";

                    switch (command) {
                    case "bye":
                        Ui.showBye();
                        return;

                    case "list":
                        taskList.listTasks();
                        break;

                    case "mark": {
                        int index = parseIndexOrThrow(payload, taskList.size(), "mark <task-number>");
                        taskList.markTask(index);
                        try {
                            storage.save(taskList);
                        } catch (Exception ignored) { }
                        break;
                    }

                    case "unmark": {
                        int index = parseIndexOrThrow(payload, taskList.size(), "unmark <task-number>");
                        taskList.unmarkTask(index);
                        try {
                            storage.save(taskList);
                        } catch (Exception ignored) { }
                        break;
                    }

                    case "todo": {
                        String description = payload.trim();
                        if (description.isEmpty()) {
                            throw new PenguinEmptyDescriptionException("todo");
                        }
                        ToDo todo = new ToDo(description);
                        taskList.addTask(todo);
                        Ui.showAdded(todo, taskList.size());
                        try {
                            storage.save(taskList);
                        } catch (Exception ignored) { }
                        break;
                    }

                    case "deadline": {
                        String[] byTokens = payload.split("\\s*/by\\s*", 2);
                        if (byTokens.length < 2 || byTokens[0].isBlank() || byTokens[1].isBlank()) {
                            throw new PenguinParseException("Usage: deadline <description> /by <when>");
                        }
                        Deadline deadline = new Deadline(byTokens[0].trim(), byTokens[1].trim());
                        taskList.addTask(deadline);
                        Ui.showAdded(deadline, taskList.size());
                        try {
                            storage.save(taskList);
                        } catch (Exception ignored) { }
                        break;
                    }

                    case "event": {
                        String[] fromTokens = payload.split("\\s*/from\\s*", 2);
                        if (fromTokens.length < 2 || fromTokens[0].isBlank()) {
                            throw new PenguinParseException("Usage: event <description> /from <start> /to <end>");
                        }
                        String description = fromTokens[0].trim();
                        String[] startEndTokens = fromTokens[1].split("\\s*/to\\s*", 2);
                        if (startEndTokens.length < 2 || startEndTokens[0].isBlank() || startEndTokens[1].isBlank()) {
                            throw new PenguinParseException("Usage: event <description> /from <start> /to <end>");
                        }
                        Event eventTask = new Event(description, startEndTokens[0].trim(), startEndTokens[1].trim());
                        taskList.addTask(eventTask);
                        Ui.showAdded(eventTask, taskList.size());
                        try {
                            storage.save(taskList);
                        } catch (Exception ignored) { }
                        break;
                    }

                    case "delete": {
                        if (tokens.length < 2) {
                            throw new PenguinParseException("Usage: delete <task-number>");
                        }
                        int idx = parseIndexOrThrow(tokens[1], taskList.size(), "delete <task-number>");
                        penguin.task.Task removed = taskList.deleteTask(idx);
                        System.out.println(" Noted. I've removed this task:");
                        System.out.println("   " + removed);
                        System.out.println(" Now you have " + taskList.size() + " tasks in the list.");
                        break;
                    }

                    case "help": {
                        Ui.showManual();
                        break;
                    }

                    default:
                        throw new PenguinUnknownCommandException(command);
                    }

                } catch (PenguinException e) {
                    Ui.showError(e.getMessage());
                }

                Ui.showDivider();
            }
        }
    }

    // helper for mark/unmark parsing
    private static int parseIndexOrThrow(String token, int size, String usageIfFail) throws PenguinParseException, PenguinInvalidIndexException {
        if (token == null || token.isBlank()) {
            throw new PenguinParseException("Usage: " + usageIfFail);
        }
        int shownIndex;
        try {
            shownIndex = Integer.parseInt(token);
        } catch (NumberFormatException e) {
            throw new PenguinParseException("Task number must be an integer. Usage: " + usageIfFail);
        }
        int index = shownIndex - 1;
        if (index < 0 || index >= size) {
            throw new PenguinInvalidIndexException(shownIndex);
        }
        return index;
    }
}

