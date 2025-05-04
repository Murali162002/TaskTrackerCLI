package com.example.TaskTracker;
import java.time.LocalDateTime;
import java.util.List;

public class ReminderService extends Thread {
    private TaskManager taskManager;

    public ReminderService(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // Get all tasks from TaskManager
                List<Task> tasks = taskManager.getTasks();

                // Check each task for its reminder time and if it's time to remind
                for (Task task : tasks) {
                    LocalDateTime reminderTime = task.getReminderTime();
                    if (reminderTime != null && reminderTime.isBefore(LocalDateTime.now())) {
                        // Play sound alarm if the reminder time has passed
                        Soundalarm.playalarm();
                        System.out.println("Reminder: " + task.getDescription());
                    }
                }

                // Sleep for a certain interval before checking again (e.g., every minute)
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
