package penguin.task;

import penguin.util.DateTimeUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with a description and a due date/time.
 * <p>
 * Accepted input formats (handled by {@link DateTimeUtil#parseDateTime(String)}):
 * <ul>
 *   <li>User-friendly: {@code yyyy-MM-dd HHmm} (e.g., 2019-12-02 1800)</li>
 *   <li>ISO-8601: {@code yyyy-MM-dd'T'HH:mm} (e.g., 2019-12-02T18:00) — used in storage</li>
 * </ul>
 */
public class Deadline extends Task {
    private static final DateTimeFormatter PRETTY = DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");

    private final LocalDateTime by;

    /**
     * Creates a Deadline.
     *
     * @param description task description
     * @param byString    date/time string (see accepted formats above)
     * @throws IllegalArgumentException if the date/time cannot be parsed
     */
    public Deadline(String description, String byString) {
        super(description);
        this.by = DateTimeUtil.parseDateTime(byString);
    }

    /**
     * Returns the due date/time.
     */
    public LocalDateTime getByDateTime() {
        return by;
    }

    /**
     * Returns a pretty, user-facing representation, e.g., {@code Dec 2 2019, 6:00PM}.
     */
    public String getByPretty() {
        return by.format(PRETTY);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getByPretty() + ")";
    }
}
