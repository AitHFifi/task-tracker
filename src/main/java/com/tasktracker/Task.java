package com.tasktracker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.json.JSONObject;

public class Task {
    private int id;
    private String description;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructor for new task
    public Task(int id, String description) {
        this.id = id;
        this.description = description;
        this.status = "todo";
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Constructor from JSON
    public Task(JSONObject json) {
        this.id = json.getInt("id");
        this.description = json.getString("description");
        this.status = json.getString("status");
        
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        this.createdAt = LocalDateTime.parse(json.getString("createdAt"), formatter);
        this.updatedAt = LocalDateTime.parse(json.getString("updatedAt"), formatter);
    }

    // djdjdjd

    // Getters and setters
    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        this.updatedAt = LocalDateTime.now();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        this.updatedAt = LocalDateTime.now();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    // Convert to JSON
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("description", description);
        json.put("status", status);
        
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        json.put("createdAt", createdAt.format(formatter));
        json.put("updatedAt", updatedAt.format(formatter));
        return json;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s (Status: %s)", id, description, status);
    }
}