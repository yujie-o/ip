package penguin.app;

import penguin.exception.PenguinException;
import penguin.task.*;

import java.io.*;
import java.nio.file.*;

/**
 * Handles loading and saving of tasks to a file.
 */
public class Storage {
    private final Path path;

    /**
     * Constructs a storage handler for the given path.
     *
     * @param path path of the save file
     */
    public Storage(Path path) {
        this.path = path;
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

    // ---------- Simple text format ----------
    // T | 1 | read book
    // D | 0 | return book | June 6th
    // E | 0 | project meeting | Aug 6th 2-4pm

    private String toLine(Task t) throws PenguinException {
        String done = t.isDone() ? "1" : "0";
        if (t instanceof ToDo) {
            return String.join(" | ", "T", done, t.getDescription());
        } else if (t instanceof Deadline d) {
            return String.join(" | ", "D", done, d.getDescription(), d.getByDateTime().toString());
        } else if (t instanceof Event e) {
            return String.join(" | ", "E", done, e.getDescription(),
                    e.getFromDateTime().toString(), e.getToDateTime().toString());
        } else {
            throw new PenguinException("Unknown task type: " + t.getClass().getSimpleName());
        }
    }


    private static Task parse(String line) {
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
            Deadline d = new Deadline(p[2], p[3]);
            if (done) d.markDone();
            return d;
        }
        case "E": {
            Event e = new Event(p[2], p[3], p[4]);
            if (done) e.markDone();
            return e;
        }


        default:
            throw new IllegalArgumentException("unknown type: " + type);
        }
    }

}
