package com.example.TaskTracker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class TaskTracker {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner sc = new Scanner(System.in);

        // Start the reminder service in a separate thread
        ReminderService reminderService = new ReminderService(taskManager);
        reminderService.start();

        System.out.println("Available commands: add, update, delete, updatetaskstatus, display, listbystatus, exit");

        while (true) {
            System.out.print("Enter command: ");
            String command = sc.nextLine().trim().toLowerCase();

            switch (command) {
                case "add":
                    System.out.print("Enter task description: ");
                    String description = sc.nextLine();

                    // Parse reminder time
                    System.out.print("Enter reminder time (yyyy-MM-dd HH:mm): ");
                    String reminderTimeInput = sc.nextLine().trim();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm");
                    LocalDateTime reminderTime;
                    try {
                        reminderTime = LocalDateTime.parse(reminderTimeInput, formatter);
                    } catch (Exception e) {
                        System.out.println("Invalid reminder time format. Please use the correct format (yyyy-MM-dd HH:mm).");
                        continue; // Go back to the beginning of the loop to prompt for input again
                    }

                    taskManager.addTask(description, reminderTime);
                    break;

                // Handle other cases (update, delete, etc.)
                case "update":
                    taskManager.displayTasks();
                    System.out.print("Enter task ID to update: ");
                    if (sc.hasNextInt()) {
                        int id = sc.nextInt();
                        sc.nextLine(); // clear buffer
                        System.out.print("Enter new task description: ");
                        String newDescription = sc.nextLine();
                        taskManager.updateTask(id, newDescription);
                    } else {
                        System.out.println("Invalid input. Please enter a numeric ID.");
                        sc.nextLine(); // clear invalid input
                    }
                    break;

                // Other cases remain unchanged...

                case "exit":
                    System.out.println("Exiting Task Tracker.");
                    sc.close();
                    return;

                default:
                    System.out.println("Unknown command: " + command + ". Type 'help' to see available commands.");
                    break;
            }
        }
    }
}
