package penguin.util;

import penguin.exception.PenguinParseException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for parsing date/time strings.
 */
public final class DateTimeUtil {
    private DateTimeUtil() {
    }

    /**
     * Parses a user-entered date/time string.
     * Accepts only {@code yyyy-MM-dd HHmm}, e.g., {@code 2019-12-01 0900}.
     *
     * @param input raw date-time string
     * @return parsed LocalDateTime
     * @throws PenguinParseException if input is not in the expected format
     */
    public static LocalDateTime parseDateTime(String input) throws PenguinParseException {
        try {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            return LocalDateTime.parse(input, fmt);
        } catch (Exception e) {
            throw new PenguinParseException(
                    "Invalid date/time format. Expected yyyy-MM-dd HHmm, e.g., 2019-12-01 0900");
        }
    }
}
