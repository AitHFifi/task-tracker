package com.tasktracker;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final String FILE_NAME = "tasks.json";

    // Read tasks from JSON file
    public static List<Task> readTasks() {
        List<Task> tasks = new ArrayList<>();
        File file = new File(FILE_NAME);
        
        if (!file.exists()) {
            return tasks;
        }
        
        try {
            String content = new String(Files.readAllBytes(Paths.get(FILE_NAME)));
            if (content.trim().isEmpty()) {
                return tasks;
            }
            
            JSONArray jsonArray = new JSONArray(content);
            
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonTask = jsonArray.getJSONObject(i);
                Task task = new Task(jsonTask);
                tasks.add(task);
            }
        } catch (IOException e) {
            System.err.println("Error reading tasks: " + e.getMessage());
        }
        
        return tasks;
    }

    // Write tasks to JSON file
    public static void writeTasks(List<Task> tasks) {
        JSONArray jsonArray = new JSONArray();
        
        for (Task task : tasks) {
            jsonArray.put(task.toJson());
        }
        
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.write(jsonArray.toString(2));
        } catch (IOException e) {
            System.err.println("Error writing tasks: " + e.getMessage());
        }
    }
}