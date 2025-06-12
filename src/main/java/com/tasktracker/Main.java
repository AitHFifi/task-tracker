package com.tasktracker;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            printUsage();
            return;
        }

        TaskManager taskManager = new TaskManager();
        String command = args[0];

        try {
            switch (command) {
                case "add":
                    if (args.length < 2) {
                        System.out.println("Error: Missing task description");
                        printUsage();
                        return;
                    }
                    int id = taskManager.addTask(args[1]);
                    System.out.println("Task added successfully (ID: " + id + ")");
                    break;

                case "update":
                    if (args.length < 3) {
                        System.out.println("Error: Missing task ID or description");
                        printUsage();
                        return;
                    }
                    int updateId = Integer.parseInt(args[1]);
                    boolean updated = taskManager.updateTask(updateId, args[2]);
                    if (updated) {
                        System.out.println("Task updated successfully");
                    } else {
                        System.out.println("Error: Task not found");
                    }
                    break;

                case "delete":
                    if (args.length < 2) {
                        System.out.println("Error: Missing task ID");
                        printUsage();
                        return;
                    }
                    int deleteId = Integer.parseInt(args[1]);
                    boolean deleted = taskManager.deleteTask(deleteId);
                    if (deleted) {
                        System.out.println("Task deleted successfully");
                    } else {
                        System.out.println("Error: Task not found");
                    }
                    break;

                case "mark-in-progress":
                    if (args.length < 2) {
                        System.out.println("Error: Missing task ID");
                        printUsage();
                        return;
                    }
                    int inProgressId = Integer.parseInt(args[1]);
                    boolean markedInProgress = taskManager.markInProgress(inProgressId);
                    if (markedInProgress) {
                        System.out.println("Task marked as in-progress");
                    } else {
                        System.out.println("Error: Task not found");
                    }
                    break;

                case "mark-done":
                    if (args.length < 2) {
                        System.out.println("Error: Missing task ID");
                        printUsage();
                        return;
                    }
                    int doneId = Integer.parseInt(args[1]);
                    boolean markedDone = taskManager.markDone(doneId);
                    if (markedDone) {
                        System.out.println("Task marked as done");
                    } else {
                        System.out.println("Error: Task not found");
                    }
                    break;

                case "list":
                    List<Task> taskList;
                    if (args.length > 1) {
                        String status = args[1];
                        if (status.equals("todo") || status.equals("in-progress") || status.equals("done")) {
                            taskList = taskManager.listTasksByStatus(status);
                            System.out.println("Tasks with status '" + status + "':");
                        } else {
                            System.out.println("Error: Invalid status. Use 'todo', 'in-progress', or 'done'");
                            return;
                        }
                    } else {
                        taskList = taskManager.listAllTasks();
                        System.out.println("All tasks:");
                    }

                    if (taskList.isEmpty()) {
                        System.out.println("No tasks found");
                    } else {
                        for (Task task : taskList) {
                            System.out.println(task);
                        }
                    }
                    break;

                default:
                    System.out.println("Error: Unknown command '" + command + "'");
                    printUsage();
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid task ID format");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void printUsage() {
        System.out.println("Usage:");
        System.out.println("  .\task-cli add \"Task description\"");
        System.out.println("  .\task-cli update <task-id> \"Updated description\"");
        System.out.println("  .\task-cli delete <task-id>");
        System.out.println("  .\task-cli mark-in-progress <task-id>");
        System.out.println("  .\task-cli mark-done <task-id>");
        System.out.println("  .\task-cli list");
        System.out.println("  .\task-cli list <status>");
        System.out.println("    where <status> is one of: todo, in-progress, done");
    }
}