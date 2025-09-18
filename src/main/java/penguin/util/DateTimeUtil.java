package penguin.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for parsing date/time strings into {@link LocalDateTime}.
 * Supports both user-friendly and ISO-8601 formats.
 */
public final class DateTimeUtil {
    private DateTimeUtil() {
    }

    /**
     * Parse a date/time string into a LocalDateTime.
     * Accepted formats:
     * - yyyy-MM-dd HHmm (e.g., 2019-12-01 0900)
     * - yyyy-MM-dd'T'HH:mm (e.g., 2019-12-01T09:00) — used in storage
     */
    public static LocalDateTime parseDateTime(String input) {
        // try ISO first (used in storage)
        try {
            return LocalDateTime.parse(input);
        } catch (Exception ignored) {
        }

        // then try user-friendly yyyy-MM-dd HHmm
        try {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            return LocalDateTime.parse(input, fmt);
        } catch (Exception ignored) {
        }

        throw new IllegalArgumentException("Invalid date/time format. " + "Expected yyyy-MM-dd HHmm, e.g., 2019-12-01 0900");
    }
}
