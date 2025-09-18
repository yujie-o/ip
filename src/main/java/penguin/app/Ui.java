package penguin.app;

import penguin.task.Task;

import java.util.Scanner;

/**
 * Handles all user input and output.
 * Acts as the boundary between user commands and program logic.
 */
public class Ui {
    public static final int WIDTH = 58;
    public static final String DIV = "─".repeat(WIDTH);
    public static final String PROMPT = "➤ ";

    private final Scanner in = new Scanner(System.in);

    /**
     * Reads a line of input from the user.
     */
    public String readCommand() {
        return in.nextLine();
    }

    /**
     * Closes the scanner safely.
     */
    public void close() {
        try {
            in.close();
        } catch (Exception ignored) {
        }
    }

    /**
     * Prints greeting message.
     */
    public void showGreeting(String name) {
        showDivider();
        System.out.println("Hello! I'm " + name + ".");
        System.out.println("What can I do for you?");
        showDivider();
    }

    /**
     * Prints exit message.
     */
    public static void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
        showDivider();
    }

    /**
     * Prints confirmation after a task has been added.
     *
     * @param t     the task added
     * @param count number of tasks now in the list
     */
    public static void showAdded(Task t, int count) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + t);
        System.out.println("Now you have " + count + " tasks in the list.");
    }

    /**
     * Prints the help manual with all available commands.
     */
    public static void showManual() {
        System.out.println("Here are the commands you can use:");
        System.out.println("  (Note: Dates must be entered in yyyy-MM-dd format.");
        System.out.println("         Date/time must be entered in yyyy-MM-dd HHmm format, 24-hour clock.)\n");

        System.out.println("  todo <description>");
        System.out.println("      Adds a ToDo task.");
        System.out.println("      Example: todo borrow book\n");

        System.out.println("  deadline <description> /by <yyyy-MM-dd HHmm>");
        System.out.println("      Adds a Deadline task with a due date and time.");
        System.out.println("      Example: deadline return book /by 2019-12-02 1800\n");

        System.out.println("  event <description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>");
        System.out.println("      Adds an Event task with a start and end date/time.");
        System.out.println("      Example: event hackathon /from 2019-12-01 0900 /to 2019-12-03 2100\n");

        System.out.println("  list");
        System.out.println("      Show all tasks in the list.\n");

        System.out.println("  mark <task-number>");
        System.out.println("      Mark a task as done.");
        System.out.println("      Example: mark 2\n");

        System.out.println("  unmark <task-number>");
        System.out.println("      Mark a task as not done.");
        System.out.println("      Example: unmark 2\n");

        System.out.println("  delete <task-number>");
        System.out.println("      Delete a task.");
        System.out.println("      Example: delete 3\n");

        System.out.println("  find <keyword>");
        System.out.println("      Find tasks containing the given keyword.");
        System.out.println("      Example: find book\n");

        System.out.println("  on <yyyy-MM-dd>");
        System.out.println("      Show deadlines/events occurring on a specific date (ignores time).");
        System.out.println("      Example: on 2019-12-02\n");

        System.out.println("  help");
        System.out.println("      Show this help manual.\n");

        System.out.println("  bye");
        System.out.println("      Exit the program.\n");
    }

    /**
     * Prints an error message.
     *
     * @param msg explanation of the error
     */
    public static void showError(String msg) {
        System.out.println("☹ OOPS!!! " + msg);
    }

    /**
     * Prints a divider line.
     */
    public static void showDivider() {
        System.out.println(DIV);
    }
}
