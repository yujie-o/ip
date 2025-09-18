package penguin.app;

import penguin.command.Command;
import penguin.task.TaskList;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Penguin {
    private static final String NAME = "Penguin";

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    public Penguin(String filePath) {
        this.ui = new Ui();                    // instance Ui (correct now)
        Path savePath = Paths.get(filePath);
        this.storage = new Storage(savePath);
        this.tasks = storage.loadOrEmpty();
    }

    public void run() {
        ui.showGreeting(NAME);
        boolean isExit = false;

        while (!isExit) {
            System.out.print(Ui.PROMPT);       // static prompt is fine
            String fullCommand = ui.readCommand();

            Ui.showDivider();                  // opening divider for this command
            try {
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (penguin.exception.PenguinException e) {
                Ui.showError(e.getMessage());
            }

            if (!isExit) {                     // no trailing divider after 'bye'
                Ui.showDivider();
            }
        }

        ui.close();                            // tidy up input
    }

    public static void main(String[] args) {
        new Penguin("data/penguin.txt").run();
    }
}
