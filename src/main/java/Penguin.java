import java.util.Scanner;

public class Penguin {
    static final String NAME = "Penguin";
    static final String PROMPT = "➤ ";

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        TaskList taskList = new TaskList();
        Ui.showGreeting(NAME);

        while (true) {
            System.out.print(PROMPT);
            String line = in.nextLine().trim();
            Ui.showDivider();
            if (line.equalsIgnoreCase("bye")){
                Ui.showBye();
                break;
            }
            else if (line.equalsIgnoreCase("list")){
                taskList.listTasks();
            }
            else {
                taskList.addTask(line);
            }
            Ui.showDivider();
        }
    }
}
