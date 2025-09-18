package penguin.command;

import penguin.app.Storage;
import penguin.app.Ui;
import penguin.exception.PenguinException;
import penguin.exception.PenguinInvalidIndexException;
import penguin.exception.PenguinParseException;
import penguin.task.Task;
import penguin.task.TaskList;

public class IndexCommand extends Command {
    public enum Kind {MARK, UNMARK, DELETE}

    private final Kind kind;
    private final int index; // zero-based

    private IndexCommand(Kind kind, int index) {
        this.kind = kind;
        this.index = index;
    }

    public static IndexCommand ofMark(String token) throws PenguinException {
        return new IndexCommand(Kind.MARK, parseShownIndex(token));
    }

    public static IndexCommand ofUnmark(String token) throws PenguinException {
        return new IndexCommand(Kind.UNMARK, parseShownIndex(token));
    }

    public static IndexCommand ofDelete(String token) throws PenguinException {
        return new IndexCommand(Kind.DELETE, parseShownIndex(token));
    }

    private static int parseShownIndex(String token) throws PenguinException {
        if (token == null || token.isBlank()) throw new PenguinParseException("Usage: <command> <task-number>");
        final int shown;
        try {
            shown = Integer.parseInt(token.trim());
        } catch (NumberFormatException e) {
            throw new PenguinParseException("Task number must be an integer.");
        }
        final int idx = shown - 1;
        if (idx < 0) throw new PenguinInvalidIndexException(shown);
        return idx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PenguinException {
        switch (kind) {
        case MARK:
            tasks.markTask(index);
            trySave(storage, tasks);
            return;
        case UNMARK:
            tasks.unmarkTask(index);
            trySave(storage, tasks);
            return;
        case DELETE: {
            Task removed = tasks.deleteTask(index);
            System.out.println("Noted. I've removed this task:");
            System.out.println(" " + removed);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            trySave(storage, tasks);
            return;
        }
        default:
            throw new IllegalStateException("Unknown kind " + kind);
        }
    }
}
