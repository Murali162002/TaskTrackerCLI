package com.example.TaskTracker;
import java.time.LocalDateTime;

public class Task {
    private int id;
    private String description;
    private String status; // Field for status
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String priority;

    public Task(int id, String description, String priority) {
        this.id = id;
        this.description = description;
        this.priority = priority;
        this.status = "todo"; // Default status
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public String getpriority()
    {
    	return priority;
    }

    // Setters
    public void setDescription(String description) {
        this.description = description;
        this.updatedAt = LocalDateTime.now();
    }

    public void setStatus(String status) {
        this.status = status;
        this.updatedAt = LocalDateTime.now();
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setpriority(String priority)
    {
    	this.priority = priority;
    }
    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
