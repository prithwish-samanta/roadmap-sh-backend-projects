package dev.prithwish.task_tracker.commands;

import dev.prithwish.task_tracker.demain.Status;
import dev.prithwish.task_tracker.demain.Task;
import dev.prithwish.task_tracker.service.FileDBService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.shell.table.*;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@ShellComponent
public class Command {
    private final FileDBService service;

    public Command(FileDBService service) {
        this.service = service;
    }

    @ShellMethod(key = "add", value = "Add new task")
    public String add(@ShellOption String task) {
        try {
            //Load the task list
            List<Task> tasks = service.readFile();

            //Create new task
            Task newTask = new Task();
            newTask.setId(tasks.isEmpty() ? 1 : tasks.getLast().getId() + 1);
            newTask.setDescription(task);
            newTask.setStatus(Status.NOT_STARTED);
            newTask.setCreatedAt(LocalDateTime.now());
            newTask.setUpdatedAt(LocalDateTime.now());
            tasks.add(newTask);

            //Save the new task
            service.writeFile(tasks);

            return "Task added successfully";
        } catch (Exception e) {
            return "Unexpected error occurred";
        }
    }

    @ShellMethod(key = "update", value = "Update an existing task")
    public String update(@ShellOption int id, @ShellOption String task) {
        try {
            //Load the task list
            List<Task> tasks = service.readFile();

            //Find the task to update
            Task taskToUpdate = tasks.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
            if (taskToUpdate == null) {
                return "Task not found";
            }
            taskToUpdate.setDescription(task);
            taskToUpdate.setUpdatedAt(LocalDateTime.now());

            //Save the updated task
            service.writeFile(tasks);

            return "Task updated successfully";
        } catch (Exception e) {
            return "Unexpected error occurred";
        }
    }

    @ShellMethod(key = "delete", value = "Delete an existing task")
    public String delete(@ShellOption int id) {
        try {
            //Load the task list
            List<Task> tasks = service.readFile();

            //Find the task to delete
            Task taskToDelete = tasks.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
            if (taskToDelete == null) {
                return "Task not found";
            }
            tasks.remove(taskToDelete);

            //Save the updated task
            service.writeFile(tasks);

            return "Task deleted successfully";
        } catch (Exception e) {
            return "Unexpected error occurred";
        }
    }

    @ShellMethod(key = "mark-not-started", value = "Mark task as not started")
    public String markNotStarted(@ShellOption int id) {
        try {
            //Load the task list
            List<Task> tasks = service.readFile();

            //Find task to update the status
            Task task = tasks.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
            if (task == null) {
                return "Task not found";
            }
            task.setStatus(Status.NOT_STARTED);
            task.setUpdatedAt(LocalDateTime.now());

            //Save the updated task
            service.writeFile(tasks);

            return "Task marked as 'not started' successfully";
        } catch (Exception e) {
            return "Unexpected error occurred";
        }
    }

    @ShellMethod(key = "mark-in-progress", value = "Mark task as in progress")
    public String markInProgress(@ShellOption int id) {
        try {
            //Load the task list
            List<Task> tasks = service.readFile();

            //Find task to update the status
            Task task = tasks.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
            if (task == null) {
                return "Task not found";
            }
            task.setStatus(Status.IN_PROGRESS);
            task.setUpdatedAt(LocalDateTime.now());

            //Save the updated task
            service.writeFile(tasks);

            return "Task marked as 'in progress' successfully";
        } catch (Exception e) {
            return "Unexpected error occurred";
        }
    }

    @ShellMethod(key = "mark-done", value = "Mark task as done")
    public String markDone(@ShellOption int id) {
        try {
            //Load the task list
            List<Task> tasks = service.readFile();

            //Find task to update the status
            Task task = tasks.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
            if (task == null) {
                return "Task not found";
            }
            task.setStatus(Status.DONE);
            task.setUpdatedAt(LocalDateTime.now());

            //Save the updated task
            service.writeFile(tasks);

            return "Task marked as 'done' successfully";
        } catch (Exception e) {
            return "Unexpected error occurred";
        }
    }

    @ShellMethod(key = "list", value = "List down tasks")
    public Table list(@ShellOption(help = "Task status (done, in-progress, not-started)", defaultValue = "all") String status) {
        try {
            // Load the task list
            List<Task> tasks = service.readFile();

            List<Task> filteredTasks;
            if (status.equalsIgnoreCase("done")) {
                filteredTasks = tasks.stream().filter(t -> t.getStatus() == Status.DONE).collect(Collectors.toList());
            } else if (status.equalsIgnoreCase("in-progress")) {
                filteredTasks = tasks.stream().filter(t -> t.getStatus() == Status.IN_PROGRESS).collect(Collectors.toList());
            } else if (status.equalsIgnoreCase("not-started")) {
                filteredTasks = tasks.stream().filter(t -> t.getStatus() == Status.NOT_STARTED).collect(Collectors.toList());
            } else {
                filteredTasks = tasks;
            }

            //If there is no tasks
            if (filteredTasks.isEmpty()) {
                TableModel emptyModel = new ArrayTableModel(new String[][]{
                        {"No tasks found."}
                });

                TableBuilder emptyTableBuilder = new TableBuilder(emptyModel);
                emptyTableBuilder.addFullBorder(BorderStyle.oldschool);
                return emptyTableBuilder.build();
            }

            // Create a TableModel from the task list
            TableModel tableModel = new BeanListTableModel<>(
                    filteredTasks,
                    new LinkedHashMap<>() {{
                        put("id", "ID");
                        put("description", "Description");
                        put("status", "Status");
                    }}
            );
            TableBuilder tableBuilder = new TableBuilder(tableModel);
            tableBuilder.addFullBorder(BorderStyle.oldschool);
            tableBuilder.addHeaderBorder(BorderStyle.oldschool);

            return tableBuilder.build();
        } catch (Exception e) {
            return null;
        }
    }
}
