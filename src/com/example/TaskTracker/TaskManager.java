package com.example.TaskTracker;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.*;

public class TaskManager {
    private static final String FILE_NAME = "tasks.json";
    private List<Task> tasks;

    public TaskManager() {
        tasks = loadTasksFromFile();
        if (tasks == null) {
            tasks = new ArrayList<>();
        }
    }

    public void addTask(String description) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Priority (LOW / MEDIUM / HIGH):");
        String input = sc.nextLine().toUpperCase();

        Priority priority;
        try {
            priority = Priority.valueOf(input);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid priority. Defaulting to MEDIUM.");
            priority = Priority.MEDIUM; // Corrected enum usage
        }

        int id = tasks.size() + 1;
        Task newTask = new Task(id, description, priority);
        tasks.add(newTask);
        saveTasksToFile();
        System.out.println("Task added successfully: " + newTask);
    }

    public void updateTask(int id, String newDescription) {
        Task taskToUpdate = findTaskById(id);

        if (taskToUpdate != null) {
            taskToUpdate.setDescription(newDescription);
            taskToUpdate.setUpdatedAt(LocalDateTime.now());
            saveTasksToFile();
            System.out.println("Task updated successfully: " + taskToUpdate);
        } else {
            System.out.println("Task with ID " + id + " not found.");
        }
    }

    public void deleteTask(int id) {
        Task taskToDelete = findTaskById(id);

        if (taskToDelete != null) {
            tasks.remove(taskToDelete);
            saveTasksToFile();
            System.out.println("Task deleted successfully: " + taskToDelete);
        } else {
            System.out.println("Task with ID " + id + " not found.");
        }
    }

    public void updateTaskStatus(int id, Status status) {
        Task taskToUpdate = findTaskById(id);

        if (taskToUpdate != null) {
            taskToUpdate.setStatus(status);
            taskToUpdate.setUpdatedAt(LocalDateTime.now());
            saveTasksToFile();
            System.out.println("Task status updated successfully: " + taskToUpdate);
        } else {
            System.out.println("Task with ID " + id + " not found.");
        }
    }

    public void listTasksByStatus(String statusInput) {
        Status status;
        try {
            status = Status.valueOf(statusInput.toUpperCase().replace("-", "_"));
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid status! Use TODO, COMPLETED.");
            return;
        }

        System.out.println("Tasks with status '" + status + "':");
        boolean found = false;

        for (Task task : tasks) {
            if (task.getStatus() == status) {
                System.out.println(task);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No tasks found with status '" + status + "'.");
        }
    }

    public void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }

        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    private Task findTaskById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    private void saveTasksToFile() {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                    .create();
            gson.toJson(tasks, writer);
        } catch (IOException e) {
            System.err.println("Error saving tasks: " + e.getMessage());
        }
    }

    private List<Task> loadTasksFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (FileReader reader = new FileReader(file)) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                    .create();
            Type taskListType = new TypeToken<ArrayList<Task>>() {}.getType();
            return gson.fromJson(reader, taskListType);
        } catch (IOException | JsonSyntaxException e) {
            System.err.println("Error loading tasks: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
