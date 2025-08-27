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
            } else {
                taskList.addTask(line);
            }
            Ui.showDivider();
        }
    }
}
