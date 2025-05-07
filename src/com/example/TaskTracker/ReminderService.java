package com.example.TaskTracker;

import java.time.LocalDateTime;
import java.util.List;

public class ReminderService extends Thread {
    private final TaskManager taskManager;

    public ReminderService(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public void run() {
        while (true) {
            List<Task> tasks = taskManager.getTasks();  // <-- This method must exist in TaskManager

            for (Task task : tasks) {
                LocalDateTime reminderTime = task.getReminderTime();
                if (reminderTime != null && task.getStatus() != Status.DONE) {
                    LocalDateTime now = LocalDateTime.now().withSecond(0).withNano(0);
                    if (reminderTime.withSecond(0).withNano(0).equals(now)) {
                        System.out.println("\n⏰ Reminder for Task ID " + task.getId() + ": " + task.getDescription());
                        Soundalarm.playalarm();
                        taskManager.updateTaskStatus(task.getId(), Status.DONE);  // Mark as done after reminder
                    }
                }
            }

            try {
                Thread.sleep(60000); // Check every 60 seconds
            } catch (InterruptedException e) {
                System.err.println("ReminderService interrupted.");
            }
        }
    }
}
