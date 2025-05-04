package com.example.TaskTracker;

import java.time.LocalDateTime;

public class Task {
    private int id;
    private String description;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Priority priority;
    private LocalDateTime remindTime;

    public Task(int id, String description, Priority priority) {
        this.id = id;
        this.description = description;
        this.priority = priority;
        this.status = Status.TODO;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.remindTime = null;
        
    }
    
    public Task(int id, String description, Priority priority, LocalDateTime remindTime) {
        this.id = id;
        this.description = description;
        this.priority = priority;
        this.status = Status.TODO;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.remindTime = remindTime;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Priority getPriority() {
        return priority;
    }
    
    public LocalDateTime getReminderTime()
    {
    	return remindTime;
    }

    public void setDescription(String description) {
        this.description = description;
        this.updatedAt = LocalDateTime.now();
    }

    public void setStatus(Status status) {
        this.status = status;
        this.updatedAt = LocalDateTime.now();
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setReminderTime(LocalDateTime reminderTime)
    {
    	this.remindTime = reminderTime;
    }
    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", priority=" + priority +
                '}';
    }

	public void setUpdatedAt(LocalDateTime now) {
		// TODO Auto-generated method stub
		
	}
}
