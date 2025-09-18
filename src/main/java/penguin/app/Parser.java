package penguin.app;

import penguin.command.*;
import penguin.exception.PenguinEmptyDescriptionException;
import penguin.exception.PenguinParseException;

/**
 * Parses user input strings into {@link Command} objects.
 */
public final class Parser {
    /**
     * Parses a raw user command string into a specific {@link Command}.
     *
     * @param fullCommand raw user input
     * @return a Command ready to execute
     * @throws PenguinParseException if input is invalid
     */
    public static Command parse(String fullCommand) throws penguin.exception.PenguinException {
        if (fullCommand == null || fullCommand.isBlank()) {
            throw new PenguinParseException("You typed nothing. Try one of: todo, deadline, event, list, mark, unmark, delete, help, bye.");
        }
        String[] tokens = fullCommand.trim().split("\\s+", 2);
        String cmd = tokens[0].toLowerCase();
        String payload = tokens.length > 1 ? tokens[1] : "";

        switch (cmd) {
        case "bye":
            return new ExitCommand();

        case "help":
            return new HelpCommand();

        case "list":
            return new ListCommand();

        case "mark":
            return IndexCommand.ofMark(payload);

        case "unmark":
            return IndexCommand.ofUnmark(payload);

        case "delete":
            return IndexCommand.ofDelete(payload);

        case "todo": {
            String desc = payload.trim();
            if (desc.isEmpty()) throw new PenguinEmptyDescriptionException("todo");
            return new AddTodoCommand(desc);
        }

        case "deadline": {
            String[] p = payload.split("\\s*/by\\s*", 2);
            if (p.length < 2 || p[0].isBlank() || p[1].isBlank()) {
                throw new PenguinParseException("Usage: deadline <description> /by <when>");
            }
            return new AddDeadlineCommand(p[0].trim(), p[1].trim());
        }

        case "event": {
            String[] p1 = payload.split("\\s*/from\\s*", 2);
            if (p1.length < 2 || p1[0].isBlank()) {
                throw new PenguinParseException("Usage: event <description> /from <start> /to <end>");
            }
            String[] p2 = p1[1].split("\\s*/to\\s*", 2);
            if (p2.length < 2 || p2[0].isBlank() || p2[1].isBlank()) {
                throw new PenguinParseException("Usage: event <description> /from <start> /to <end>");
            }
            return new AddEventCommand(p1[0].trim(), p2[0].trim(), p2[1].trim());
        }

        case "on": {
            return new OnCommand(payload);
        }

        case "find": {
            if (payload.isBlank()) {
                throw new penguin.exception.PenguinParseException("Usage: find <keyword>");
            }
            return new penguin.command.FindCommand(payload.trim());
        }

        default:
            return new UnknownCommand(cmd);
        }
    }
}
