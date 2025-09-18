package penguin.app;

import penguin.command.Command;
import penguin.task.TaskList;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Main entry point of the Penguin task manager.
 * <p>
 * Coordinates storage, task list, and user interface.
 * Responsible for the application lifecycle (run loop).
 */
public class Penguin {
    private static final String NAME = "Penguin";

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a new Penguin app.
     *
     * @param filePath path to the storage file (relative or absolute)
     */
    public Penguin(String filePath) {
        this.ui = new Ui();
        Path savePath = Paths.get(filePath);
        this.storage = new Storage(savePath);
        this.tasks = storage.loadOrEmpty();
    }

    /**
     * Runs the main program loop until user exits.
     */
    public void run() {
        ui.showGreeting(NAME);
        boolean isExit = false;

        while (!isExit) {
            System.out.print(Ui.PROMPT);
            String fullCommand = ui.readCommand();

            Ui.showDivider();
            try {
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (penguin.exception.PenguinException e) {
                Ui.showError(e.getMessage());
            }

            if (!isExit) {
                Ui.showDivider();
            }
        }

        ui.close();
    }

    /**
     * Program entry point.
     *
     * @param args command line arguments (unused)
     */
    public static void main(String[] args) {
        new Penguin("data/penguin.txt").run();
    }
}
