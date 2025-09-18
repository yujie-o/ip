package penguin.app;

import penguin.command.Command;
import penguin.exception.PenguinException;
import penguin.task.TaskList;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Penguin {
    private static final String NAME = "Penguin";

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Penguin(String filePath) {
        this.ui = new Ui();
        Path savePath = Paths.get(filePath);
        this.storage = new Storage(savePath);
        this.tasks = storage.loadOrEmpty();
    }

    public void run() {
        Ui.showGreeting(NAME);
        boolean isExit = false;

        try (java.util.Scanner in = new java.util.Scanner(System.in)) {
            while (!isExit) {
                System.out.print(Ui.PROMPT);
                String fullCommand = in.nextLine();

                Ui.showDivider(); // opening divider for this command's output
                try {
                    Command c = Parser.parse(fullCommand);
                    c.execute(tasks, ui, storage);
                    isExit = c.isExit();
                } catch (penguin.exception.PenguinException e) {
                    Ui.showError(e.getMessage());
                }

                if (!isExit) {          // <-- key change: no divider after 'bye'
                    Ui.showDivider();
                }
            }
        }
    }


    public static void main(String[] args) {
        new Penguin("data/penguin.txt").run();
    }
}
