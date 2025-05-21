package com.example.my;

public class Task {
    private final int id;
    private final String title;
    private final String description;
    private final String dueDate;
    private final String category;

    public Task(int id, String title, String description, String dueDate, String category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getCategory() {
        return category;
    }
}