import java.util.Scanner;

public class Penguin {
    static final String NAME = "Penguin";
    static final String PROMPT = "➤ ";

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        Ui.showGreeting(NAME);

        while (true) {
            System.out.print(PROMPT);
            String line = in.nextLine().trim();
            Ui.showDivider();
            if (line.equalsIgnoreCase("bye")){
                Ui.showBye();
                break;
            }
            else {
                System.out.println(line);
            }
            Ui.showDivider();
        }
    }
}
