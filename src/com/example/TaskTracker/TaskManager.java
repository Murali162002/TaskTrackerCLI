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
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private static final String FILE_NAME = "tasks.json";
    private List<Task> tasks;

    public TaskManager() {
        tasks = loadTasksFromFile();
        if (tasks == null) {  // Ensure tasks is never null
            tasks = new ArrayList<>();
        }
    }

    public void addTask(String description) {
        int id = tasks.size() + 1; // Auto-increment ID based on task list size
        Task newTask = new Task(id, description);
        tasks.add(newTask);
        saveTasksToFile();
        System.out.println("Task added successfully: " + newTask);
    }

    public void updateTask(int id, String newDescription) {
        // Find the task by ID
        Task taskToUpdate = null;
        for (Task task : tasks) {
            if (task.getId() == id) {
                taskToUpdate = task;
                break;
            }
        }

        if (taskToUpdate != null) {
            // Update the task's description and timestamp
            taskToUpdate.setDescription(newDescription);
            taskToUpdate.setUpdatedAt(LocalDateTime.now());
            saveTasksToFile(); // Save the updated task to the JSON file
            System.out.println("Task updated successfully: " + taskToUpdate);
        } else {
            System.out.println("Task with ID " + id + " not found.");
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

        try (FileReader reader = new FileReader(FILE_NAME)) {
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
    public void deleteTask(int id) {
        // Find the task with the specified ID
        Task taskToDelete = null;
        for (Task task : tasks) {
            if (task.getId() == id) {
                taskToDelete = task;
                break;
            }
        }

        if (taskToDelete != null) {
            // Remove the task from the list
            tasks.remove(taskToDelete);
            saveTasksToFile(); // Save changes to the JSON file
            System.out.println("Task deleted successfully: " + taskToDelete);
        } else {
            System.out.println("Task with ID " + id + " not found.");
        }
    }
    public void updateTaskStatus(int id, String status) {
        // Validate the status
        if (!status.equals("todo") && !status.equals("in-progress") && !status.equals("done")) {
            System.out.println("Invalid status. Please use 'todo', 'in-progress', or 'done'.");
            return;
        }

        // Find the task with the specified ID
        Task taskToUpdate = null;
        for (Task task : tasks) {
            if (task.getId() == id) {
                taskToUpdate = task;
                break;
            }
        }

        if (taskToUpdate != null) {
            // Update the status and timestamp
            taskToUpdate.setStatus(status);
            taskToUpdate.setUpdatedAt(LocalDateTime.now());
            saveTasksToFile(); // Save changes to the JSON file
            System.out.println("Task updated successfully: " + taskToUpdate);
        } else {
            System.out.println("Task with ID " + id + " not found.");
        }
    }
    public void listTasksByStatus(String status) {
        boolean validStatus = status.equalsIgnoreCase("todo") ||
                              status.equalsIgnoreCase("in-progress") ||
                              status.equalsIgnoreCase("done");

        if (!validStatus) {
            System.out.println("Invalid status! Please use 'todo', 'in-progress', or 'done'.");
            return;
        }

        System.out.println("Tasks with status '" + status + "':");
        boolean found = false;

        for (Task task : tasks) {
            if (task.getStatus().equalsIgnoreCase(status)) {
                System.out.println(task);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No tasks found with status '" + status + "'.");
        }
    }


}
