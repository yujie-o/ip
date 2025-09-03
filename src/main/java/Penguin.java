import java.util.Scanner;

public class Penguin {
    static final String NAME = "Penguin";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        TaskList taskList = new TaskList();
        Ui.showGreeting(NAME);
        while (true) {
            System.out.print(Ui.PROMPT);
            String line = in.nextLine().trim();
            Ui.showDivider();
            if (line.equalsIgnoreCase("bye")) {
                Ui.showBye();
                break;
            } else if (line.equalsIgnoreCase("list")) {
                taskList.listTasks();
            } else if (line.equalsIgnoreCase("mark")) {
                System.out.println("Usage: mark <task-number>");
            } else if (line.toLowerCase().startsWith("mark")) {
                String[] parts = line.split("\\s+");
                try {
                    taskList.markTask(Integer.parseInt(parts[1]) - 1);
                } catch (NumberFormatException e) {
                    System.out.println("Task number must be an integer.");
                }
            } else if (line.equalsIgnoreCase("unmark")) {
                System.out.println("Usage: unmark <task-number>");
            } else if (line.toLowerCase().startsWith("unmark")) {
                String[] parts = line.split("\\s+");
                try {
                    taskList.unmarkTask(Integer.parseInt(parts[1]) - 1);
                } catch (NumberFormatException e) {
                    System.out.println("Task number must be an integer.");
                }
            } else if (line.toLowerCase().startsWith("todo")) {
                String desc = line.substring(4).trim();
                if (desc.isEmpty()) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                } else {
                    ToDo todo = new ToDo(desc);
                    taskList.addTask(todo);
                    Ui.showAdded(todo, taskList.size());
                }
            } else if (line.toLowerCase().startsWith("deadline")) {
                String[] parts = line.substring(8).trim().split("/by", 2);
                if (parts.length < 2) {
                    System.out.println("Usage: deadline <description> /by <when>");
                } else {
                    Deadline dl = new Deadline(parts[0].trim(), parts[1].trim());
                    taskList.addTask(dl);
                    Ui.showAdded(dl, taskList.size());
                }
            } else if (line.toLowerCase().startsWith("event")) {
                String[] parts = line.substring(5).trim().split("/from", 2);
                if (parts.length < 2) {
                    System.out.println("Usage: event <description> /from <start> /to <end>");
                } else {
                    String desc = parts[0].trim();
                    String[] times = parts[1].split("/to", 2);
                    if (times.length < 2) {
                        System.out.println("Usage: event <description> /from <start> /to <end>");
                    } else {
                        Event ev = new Event(desc, times[0].trim(), times[1].trim());
                        taskList.addTask(ev);
                        Ui.showAdded(ev, taskList.size());
                    }
                }
            } else {
                Ui.showManual();
            }
            Ui.showDivider();
        }
    }
}

