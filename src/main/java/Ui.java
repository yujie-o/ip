public class Ui {
    public static final int WIDTH = 58;
    public static final String DIV = "─".repeat(WIDTH);
    public static final String PROMPT = "➤ ";

    public static void showGreeting(String name) {
        showDivider();
        System.out.println("Hello! I'm " + name + ".");
        System.out.println("What can I do for you?");
        showDivider();
    }

    public static void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
        showDivider();
    }

    public static void showAdded(Task t, int count) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + t);
        System.out.println("Now you have " + count + " tasks in the list.");
    }

    public static void showManual() {
        System.out.println("Here are the commands you can use:\n");

        System.out.println("  todo <description>");
        System.out.println("      Adds a ToDo task.");
        System.out.println("      Example: todo borrow book\n");

        System.out.println("  deadline <description> /by <time>");
        System.out.println("      Adds a Deadline task.");
        System.out.println("      Example: deadline return book /by Sunday\n");

        System.out.println("  event <description> /from <start> /to <end>");
        System.out.println("      Adds an Event task.");
        System.out.println("      Example: event project meeting /from Mon 2pm /to 4pm\n");

        System.out.println("  list");
        System.out.println("      Show all tasks in the list.\n");

        System.out.println("  mark <task-number>");
        System.out.println("      Mark a task as done.\n");

        System.out.println("  unmark <task-number>");
        System.out.println("      Mark a task as not done.\n");

        System.out.println("  bye");
        System.out.println("      Exit the program.\n");
    }

    public static void showUsage(String msg) {
        System.out.println("Usage: " + msg);
    }

    public static void showError(String msg) {
        System.out.println("☹ OOPS!!! " + msg);
    }

    public static void showDivider() {
        System.out.println(DIV);
    }
}
