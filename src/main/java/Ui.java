public class Ui {
    public static final int WIDTH     = 58;
    public static final String DIV    = "─".repeat(WIDTH);

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

    public static void showDivider() {
        System.out.println(DIV);
    }


}
