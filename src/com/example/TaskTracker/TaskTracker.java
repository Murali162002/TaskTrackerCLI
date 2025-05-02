package com.example.TaskTracker;

import java.util.Scanner;

public class TaskTracker {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner sc = new Scanner(System.in);

        System.out.println("Available commands: add, update, delete, updatetaskstatus, display, listbystatus, exit");

        while (true) {
            System.out.print("Enter command: ");
            String command = sc.nextLine().trim().toLowerCase();

            switch (command) {
                case "add":
                    System.out.print("Enter task description: ");
                    String description = sc.nextLine();
                    taskManager.addTask(description);
                    break;

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

                case "delete":
                    System.out.print("Enter task ID to delete: ");
                    if (sc.hasNextInt()) {
                        int deleteId = sc.nextInt();
                        sc.nextLine(); // clear buffer
                        taskManager.deleteTask(deleteId);
                    } else {
                        System.out.println("Invalid input. Please enter a numeric ID.");
                        sc.nextLine(); // clear invalid input
                    }
                    break;

                case "updatetaskstatus":
                    System.out.print("Enter task ID to update status: ");
                    if (sc.hasNextInt()) {
                        int statusId = sc.nextInt();
                        sc.nextLine(); // clear buffer
                        System.out.print("Enter new status (TODO, COMPLETED): ");
                        String newStatus = sc.nextLine();
                        taskManager.updateTaskStatus(statusId, Status.valueOf(newStatus.toUpperCase()));
                    } else {
                        System.out.println("Invalid input. Please enter a numeric ID.");
                        sc.nextLine();
                    }
                    break;

                case "listbystatus":
                    System.out.print("Enter status to filter tasks (TODO, COMPLETED): ");
                    String status = sc.nextLine();
                    taskManager.listTasksByStatus(status);
                    break;

                case "display":
                    taskManager.displayTasks();
                    break;

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
