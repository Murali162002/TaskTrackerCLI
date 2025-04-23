package com.example.TaskTracker;

import java.util.Scanner;

public class TaskTracker {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner sc = new Scanner(System.in);

        System.out.println("Available commands: add, update, deletetask, updatetaskstatus, display, listbystatus, mark-in progress, mark-done, exit");
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
                    int id = sc.nextInt();
                    sc.nextLine(); // Clear the buffer
                    System.out.print("Enter new task description: ");
                    String newDescription = sc.nextLine();
                    taskManager.updateTask(id, newDescription);
                    break;

                case "deletetask":
                    System.out.print("Enter task ID to delete: ");
                    int deleteId = sc.nextInt();
                    sc.nextLine(); // Clear the buffer
                    taskManager.deleteTask(deleteId);
                    break;

                case "updatetaskstatus":
                    System.out.print("Enter task ID to update status: ");
                    int statusId = sc.nextInt();
                    sc.nextLine(); // Clear the buffer
                    System.out.print("Enter new status (todo, in-progress, done): ");
                    String newStatus = sc.nextLine();
                    taskManager.updateTaskStatus(statusId, newStatus);
                    break;

                case "listbystatus":
                    System.out.print("Enter status to filter tasks (todo, in-progress, done): ");
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
                case "mark-in-progress":
                    System.out.print("Enter task ID to mark as in-progress: ");
                    int inProgressId = sc.nextInt();
                    sc.nextLine(); // Clear the buffer
                    taskManager.updateTaskStatus(inProgressId, "in-progress");
                    break;

                case "mark-done":
                    System.out.print("Enter task ID to mark as done: ");
                    int doneId = sc.nextInt();
                    sc.nextLine(); // Clear the buffer
                    taskManager.updateTaskStatus(doneId, "done");
                    break;
                case "list":
                    taskManager.displayTasks(); // List all tasks (no filter)
                    break;

                case "list todo":
                    taskManager.listTasksByStatus("todo");
                    break;

                case "list in-progress":
                    taskManager.listTasksByStatus("in-progress");
                    break;

                case "list done":
                    taskManager.listTasksByStatus("done");
                    break;
                case "help":
                    System.out.println("Available commands:");
                    System.out.println("add - Add a new task");
                    System.out.println("update - Update a task's description");
                    System.out.println("delete - Delete a task");
                    System.out.println("mark-in-progress - Mark a task as in-progress");
                    System.out.println("mark-done - Mark a task as done");
                    System.out.println("list - List all tasks");
                    System.out.println("list todo - List tasks that are 'todo'");
                    System.out.println("list in-progress - List tasks that are 'in-progress'");
                    System.out.println("list done - List tasks that are 'done'");
                    System.out.println("exit - Exit the program");
                    break;

                default:
                    System.out.println("Unknown command: " + command);
                    break;
            }
        }
    }
}
