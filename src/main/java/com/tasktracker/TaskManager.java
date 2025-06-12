package com.tasktracker;

import java.util.List;
import java.util.stream.Collectors;

public class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        this.tasks = FileHandler.readTasks();
    }

    // Add a new task
    public int addTask(String description) {
        int id = getNextId();
        Task task = new Task(id, description);
        tasks.add(task);
        FileHandler.writeTasks(tasks);
        return id;
    }

    // Update a task
    public boolean updateTask(int id, String description) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setDescription(description);
                FileHandler.writeTasks(tasks);
                return true;
            }
        }
        return false;
    }

    // Delete a task
    public boolean deleteTask(int id) {
        boolean removed = tasks.removeIf(task -> task.getId() == id);
        if (removed) {
            FileHandler.writeTasks(tasks);
        }
        return removed;
    }

    // Mark a task as in-progress
    public boolean markInProgress(int id) {
        return updateTaskStatus(id, "in-progress");
    }

    // Mark a task as done
    public boolean markDone(int id) {
        return updateTaskStatus(id, "done");
    }

    // Update task status
    private boolean updateTaskStatus(int id, String status) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setStatus(status);
                FileHandler.writeTasks(tasks);
                return true;
            }
        }
        return false;
    }

    // List all tasks
    public List<Task> listAllTasks() {
        return tasks;
    }

    // List tasks by status
    public List<Task> listTasksByStatus(String status) {
        return tasks.stream()
                .filter(task -> task.getStatus().equals(status))
                .collect(Collectors.toList());
    }

    // Get the next available ID
    private int getNextId() {
        return tasks.stream()
                .mapToInt(Task::getId)
                .max()
                .orElse(0) + 1;
    }
}