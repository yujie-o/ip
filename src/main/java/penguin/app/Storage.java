package penguin.app;

import penguin.exception.PenguinException;
import penguin.exception.PenguinParseException;
import penguin.task.*;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Handles loading and saving of tasks to a file.
 */
public record Storage(Path path) {

    // Storage format: ISO_LOCAL_DATE_TIME (e.g., 2019-12-02T18:00)
    private static final DateTimeFormatter STORAGE_DT = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    // User input format expected by constructors (e.g., 2019-12-02 1800)
    private static final DateTimeFormatter USER_DT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Constructs a storage handler for the given path.
     *
     * @param path path of the save file
     */
    public Storage {
    }

    /**
     * Loads tasks from file if it exists, otherwise returns an empty list.
     *
     * @return a TaskList containing the saved tasks, or empty list
     */
    public TaskList loadOrEmpty() {
        TaskList list = new TaskList();
        if (Files.notExists(path)) return list;

        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    Task t = parse(line);
                    list.addTask(t);
                } catch (Exception ignored) {
                    // Stretch goal: skip corrupted lines silently
                }
            }
        } catch (IOException ignored) {
        }
        return list;
    }

    public void save(TaskList list) throws IOException {
        ensureFileExists();
        try (BufferedWriter bw = Files.newBufferedWriter(path)) {
            for (int i = 0; i < list.size(); i++) {
                Task t = list.get(i);
                bw.write(toLine(t));
                bw.newLine();
            }
        } catch (PenguinException e) {
            throw new RuntimeException(e);
        }
    }

    private void ensureFileExists() throws IOException {
        Path parent = path.getParent();
        if (parent != null) Files.createDirectories(parent);
        if (Files.notExists(path)) Files.createFile(path);
    }

    // ---------- Text format ----------
    // T | 1 | read book
    // D | 0 | return book | 2019-12-02T18:00
    // E | 0 | project meeting | 2019-12-01T09:00 | 2019-12-01T11:00

    private String toLine(Task t) throws PenguinException {
        String done = t.isDone() ? "1" : "0";
        if (t instanceof ToDo) {
            return String.join(" | ", "T", done, t.getDescription());
        } else if (t instanceof Deadline d) {
            return String.join(" | ", "D", done, d.getDescription(),
                    d.getByDateTime().format(STORAGE_DT));
        } else if (t instanceof Event e) {
            return String.join(" | ", "E", done, e.getDescription(),
                    e.getFromDateTime().format(STORAGE_DT),
                    e.getToDateTime().format(STORAGE_DT));
        } else {
            throw new PenguinException("Unknown task type: " + t.getClass().getSimpleName());
        }
    }

    private static Task parse(String line) throws PenguinParseException {
        String[] p = line.split("\\s*\\|\\s*");
        if (p.length < 3) throw new IllegalArgumentException("bad line: " + line);

        String type = p[0];
        boolean done = "1".equals(p[1]);

        switch (type) {
        case "T": {
            Task t = new ToDo(p[2]);
            if (done) t.markDone();
            return t;
        }
        case "D": {
            if (p.length < 4) throw new IllegalArgumentException("bad deadline: " + line);
            // parse ISO from storage, format to user string for constructor
            LocalDateTime by = LocalDateTime.parse(p[3], STORAGE_DT);
            Deadline d = new Deadline(p[2], by.format(USER_DT));
            if (done) d.markDone();
            return d;
        }
        case "E": {
            if (p.length < 5) throw new IllegalArgumentException("bad event: " + line);
            LocalDateTime from = LocalDateTime.parse(p[3], STORAGE_DT);
            LocalDateTime to   = LocalDateTime.parse(p[4], STORAGE_DT);
            Event e = new Event(
                    p[2],
                    from.format(USER_DT),
                    to.format(USER_DT)
            );
            if (done) e.markDone();
            return e;
        }
        default:
            throw new IllegalArgumentException("unknown type: " + type);
        }
    }
}
