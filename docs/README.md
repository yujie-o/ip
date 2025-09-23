# Penguin User Guide

![Penguin startup](https://github.com/user-attachments/assets/38f0c624-4b14-4443-9148-221a8722366c)

![Penguin list command](https://github.com/user-attachments/assets/2ea13ce1-5192-4cac-9b0c-85d842f0275e)

---

## Introduction

Penguin is a friendly **task manager chatbot** that helps you keep track of ToDos, Deadlines, and Events.  
It runs in the terminal and accepts simple text commands.  

---

## Quick Start

1. Ensure you have **Java 17 or later** installed on your computer.  
2. Download the latest release of `ip.jar` from this repository.  
3. Open a terminal in the folder where `ip.jar` is located.  
4. Run the program with:
   ```
   java -jar ip.jar
   ```
5. Type a command and press Enter to interact with Penguin.  
6. Type `help` to see the full list of commands.  

---

## Command Summary

| Command | Format | Example |
|---------|--------|---------|
| **ToDo** | `todo <description>` | `todo borrow book` |
| **Deadline** | `deadline <description> /by <yyyy-MM-dd HHmm>` | `deadline return book /by 2019-12-02 1800` |
| **Event** | `event <description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>` | `event project meeting /from 2019-12-01 0900 /to 2019-12-01 1100` |
| **List** | `list` | `list` |
| **Mark** | `mark <task-number>` | `mark 2` |
| **Unmark** | `unmark <task-number>` | `unmark 2` |
| **Delete** | `delete <task-number>` | `delete 1` |
| **Find** | `find <keyword>` | `find book` |
| **On** | `on <yyyy-MM-dd>` | `on 2019-12-02` |
| **Help** | `help` | `help` |
| **Exit** | `bye` | `bye` |

---

## Features

### Adding ToDo

Adds a simple task without date/time.

Example:
```
todo borrow book
```

Expected outcome:
```
Got it. I've added this task:
  [T][ ] borrow book
Now you have 1 task in the list.
```

---

### Adding Deadlines

Adds a task with a deadline.

Example:
```
deadline return book /by 2019-12-02 1800
```

Expected outcome:
```
Got it. I've added this task:
  [D][ ] return book (by: Dec 2 2019, 6:00PM)
Now you have 2 tasks in the list.
```

---

### Adding Events

Adds a task with a start and end time.

Example:
```
event project meeting /from 2019-12-01 0900 /to 2019-12-01 1100
```

Expected outcome:
```
Got it. I've added this task:
  [E][ ] project meeting (from: Dec 1 2019, 9:00AM to: Dec 1 2019, 11:00AM)
Now you have 3 tasks in the list.
```

---

### Listing All Tasks

Shows all tasks currently stored.

Example:
```
list
```

Expected outcome:
```
Here are the tasks in your list:
1.[T][ ] borrow book
2.[D][ ] return book (by: Dec 2 2019, 6:00PM)
3.[E][ ] project meeting (from: Dec 1 2019, 9:00AM to: Dec 1 2019, 11:00AM)
```

---

### Marking a Task

Marks a task as done.

Example:
```
mark 2
```

Expected outcome:
```
Nice! I've marked this task as done:
  [D][X] return book (by: Dec 2 2019, 6:00PM)
```

---

### Unmarking a Task

Marks a task as not done.

Example:
```
unmark 2
```

Expected outcome:
```
OK, I've marked this task as not done yet:
  [D][ ] return book (by: Dec 2 2019, 6:00PM)
```

---

### Deleting a Task

Deletes a task by its number.

Example:
```
delete 1
```

Expected outcome:
```
Noted. I've removed this task:
  [T][ ] borrow book
Now you have 2 tasks in the list.
```

---

### Finding Tasks

Finds tasks that match a keyword.

Example:
```
find book
```

Expected outcome:
```
Here are the matching tasks in your list:
1.[T][ ] borrow book
2.[D][ ] return book (by: Dec 2 2019, 6:00PM)
```

---

### Viewing Tasks on a Date

Shows tasks scheduled for a specific date.

Example:
```
on 2019-12-02
```

Expected outcome:
```
Here are tasks on 2019-12-02:
1.[D][ ] return book (by: Dec 2 2019, 6:00PM)
```

---

### Help

Displays all available commands.

Example:
```
help
```

Expected outcome:
```
Here are the commands you can use:

  todo <description>
  deadline <description> /by <time>
  event <description> /from <start> /to <end>
  list
  mark <task-number>
  unmark <task-number>
  delete <task-number>
  find <keyword>
  on <yyyy-MM-dd>
  help
  bye
```

---

### Exiting the Program

Exits the Penguin app.

Example:
```
bye
```

Expected outcome:
```
Bye. Hope to see you again soon!
```

---

## Error Handling

Penguin gives clear error messages.

Example (invalid date):
```
deadline pay bills /by 2019/12/02 6pm
```

Expected outcome:
```
☹ OOPS!!! Invalid date/time format. Expected yyyy-MM-dd HHmm, e.g., 2019-12-01 0900
```

Example (invalid task number):
```
delete 999
```

Expected outcome:
```
☹ OOPS!!! There is no such task number: 999
```

---

## Storage

- Tasks are automatically saved to `data/penguin.txt`.  
- When restarted, Penguin loads tasks back into the list.

---

## Acknowledgements

Penguin is based on the [Duke project](https://github.com/nus-cs2113-AY2526S1/ip) for NUS CS2113.  
Markdown syntax follows [GitHub Flavored Markdown](https://docs.github.com/en/get-started/writing-on-github/getting-started-with-writing-and-formatting-on-github/basic-writing-and-formatting-syntax).
