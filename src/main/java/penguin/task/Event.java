package penguin.task;

import penguin.exception.PenguinParseException;
import penguin.util.DateTimeUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event with a start and end date/time.
 * <p>
 * Accepted input formats (handled by {@link DateTimeUtil#parseDateTime(String)}):
 * <ul>
 *   <li>User-friendly: {@code yyyy-MM-dd HHmm} (e.g., 2019-12-01 0900)</li>
 *   <li>ISO-8601: {@code yyyy-MM-dd'T'HH:mm} (e.g., 2019-12-01T09:00) — used in storage</li>
 * </ul>
 */
public class Event extends Task {
    private static final DateTimeFormatter PRETTY = DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");

    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Creates an Event.
     *
     * @param description task description
     * @param fromString  start date/time string
     * @param toString    end date/time string
     * @throws IllegalArgumentException if either date/time cannot be parsed
     */
    public Event(String description, String fromString, String toString) throws PenguinParseException {
        super(description);
        this.from = DateTimeUtil.parseDateTime(fromString);
        this.to = DateTimeUtil.parseDateTime(toString);
    }

    /**
     * Returns the start date/time.
     */
    public LocalDateTime getFromDateTime() {
        return from;
    }

    /**
     * Returns the end date/time.
     */
    public LocalDateTime getToDateTime() {
        return to;
    }

    /**
     * Pretty strings for UI.
     */
    public String getFromPretty() {
        return from.format(PRETTY);
    }

    public String getToPretty() {
        return to.format(PRETTY);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + getFromPretty()
                + " to: " + getToPretty() + ")";
    }
}
