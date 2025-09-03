import java.util.Scanner;

public class Penguin {
    private static final String NAME = "Penguin";

    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        Ui.showGreeting(NAME);

        try (Scanner in = new Scanner(System.in)) {
            while (true) {
                System.out.print(Ui.PROMPT);
                String input = in.nextLine().trim();
                Ui.showDivider();

                if (input.isEmpty()) {
                    Ui.showManual();
                    Ui.showDivider();
                    continue;
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
                    if (payload.isBlank()) {
                        Ui.showUsage("mark <task-number>");
                        break;
                    }
                    int index = parseIndexOrNegOne(payload);
                    if (index < 0) {
                        Ui.showUsage("mark <task-number>");
                        break;
                    }
                    taskList.markTask(index);
                    break;
                }

                case "unmark": {
                    if (payload.isBlank()) {
                        Ui.showUsage("unmark <task-number>");
                        break;
                    }
                    int index = parseIndexOrNegOne(payload);
                    if (index < 0) {
                        Ui.showUsage("unmark <task-number>");
                        break;
                    }
                    taskList.unmarkTask(index);
                    break;
                }

                case "todo": {
                    String description = payload.trim();
                    if (description.isEmpty()) {
                        Ui.showTodoEmpty();
                        break;
                    }
                    ToDo todo = new ToDo(description);
                    taskList.addTask(todo);
                    Ui.showAdded(todo, taskList.size());
                    break;
                }

                case "deadline": {
                    String[] byTokens = payload.split("\\s*/by\\s*", 2);
                    if (byTokens.length < 2 || byTokens[0].isBlank() || byTokens[1].isBlank()) {
                        Ui.showUsage("deadline <description> /by <when>");
                        break;
                    }
                    Deadline deadline = new Deadline(byTokens[0].trim(), byTokens[1].trim());
                    taskList.addTask(deadline);
                    Ui.showAdded(deadline, taskList.size());
                    break;
                }

                case "event": {
                    String[] fromTokens = payload.split("\\s*/from\\s*", 2);
                    if (fromTokens.length < 2 || fromTokens[0].isBlank()) {
                        Ui.showUsage("event <description> /from <start> /to <end>");
                        break;
                    }
                    String description = fromTokens[0].trim();
                    String[] startEndTokens = fromTokens[1].split("\\s*/to\\s*", 2);
                    if (startEndTokens.length < 2 || startEndTokens[0].isBlank() || startEndTokens[1].isBlank()) {
                        Ui.showUsage("event <description> /from <start> /to <end>");
                        break;
                    }
                    Event eventTask = new Event(description, startEndTokens[0].trim(), startEndTokens[1].trim());
                    taskList.addTask(eventTask);
                    Ui.showAdded(eventTask, taskList.size());
                    break;
                }

                default:
                    Ui.showManual();
                }

                Ui.showDivider();
            }
        }
    }

    // helper method used by mark/unmark
    private static int parseIndexOrNegOne(String token) {
        try {
            return Integer.parseInt(token) - 1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}

