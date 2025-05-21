package com.example.my;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DatabaseHandler {
    private static final String DB_URL = "jdbc:sqlite:tasks.db";
    private Connection connection;

    // Метод для подключения к базе данных
    public Connection connect() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(DB_URL);
                System.out.println("Соединение с SQLite успешно установлено.");
                createTasksTable(); // Создание таблицы tasks
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Не удалось подключиться к SQLite.");
            }
        }
        return connection;
    }

    public boolean deleteTask(int id) {
        String query = "DELETE FROM tasks WHERE id = ?";
        try (PreparedStatement statement = connect().prepareStatement(query)) {
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0; // Если удалена хотя бы одна строка, возвращаем true
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        String query = "SELECT * FROM tasks";
        try (Statement statement = connect().createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Task task = new Task(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getString("due_date"),
                        resultSet.getString("category")
                );
                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Ошибка при загрузке задач из базы данных.");
        }
        return tasks;
    }

    // Метод для создания таблицы tasks
    public void createTasksTable() {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS tasks (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT NOT NULL, " +
                "description TEXT, " +
                "due_date TEXT, " +
                "category TEXT)";
        try (Statement statement = connect().createStatement()) {
            statement.execute(createTableQuery);
            System.out.println("Таблица tasks создана или уже существует.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Ошибка при создании таблицы tasks.");
        }
    }

    // Метод для добавления задачи
    public boolean addTask(String title, String description, String dueDate, String category) {
        String query = "INSERT INTO tasks (title, description, due_date, category) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connect().prepareStatement(query)) {
            statement.setString(1, title);
            statement.setString(2, description);
            statement.setString(3, dueDate);
            statement.setString(4, category);

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}