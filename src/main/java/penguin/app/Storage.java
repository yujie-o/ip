package penguin.app;

import penguin.exception.PenguinException;
import penguin.task.*;
import java.io.*;
import java.nio.file.*;

public class Storage {
    private final Path path;

    public Storage(Path path) { this.path = path; }

    // Load; if file doesn't exist yet, return an empty list
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
        } catch (IOException ignored) { }
        return list;
    }

    // Save current list to disk (overwrite)
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

    private static String toLine(Task t) {
        String done = t.isDone() ? "1" : "0";
        if (t instanceof ToDo) {
            return "T | " + done + " | " + t.getDescription();
        } else if (t instanceof Deadline) {
            Deadline d = (Deadline) t;
            return "D | " + done + " | " + d.getDescription() + " | " + d.getBy();
        } else if (t instanceof Event) {
            Event e = (Event) t;
            return "E | " + done + " | " + e.getDescription() + " | " + e.getFrom() + " | " + e.getTo();
        }
        throw new IllegalStateException("Unknown task type: " + t.getClass());
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
            if (p.length < 4) throw new IllegalArgumentException("bad D line: " + line);
            Deadline d = new Deadline(p[2], p[3]);
            if (done) d.markDone();
            return d;
        }
        case "E": {
            if (p.length < 5) throw new IllegalArgumentException("bad E line: " + line);
            Event e = new Event(p[2], p[3], p[4]);
            if (done) e.markDone();
            return e;
        }
        default:
            throw new IllegalArgumentException("unknown type: " + type);
        }
    }
}
