package com.example.my;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AddTaskController {

    @FXML
    private TextField titleField;

    @FXML
    private TextArea descriptionField;

    @FXML
    private DatePicker dueDatePicker;

    @FXML
    private ComboBox<String> categoryComboBox;

    @FXML
    private Button saveButton;

    private final DatabaseHandler databaseHandler = new DatabaseHandler(); // Создаем объект класса DatabaseHandler

    @FXML
    public void initialize() {
        // Инициализация категорий
        categoryComboBox.getItems().addAll("Работа", "Учеба", "Личное");

        saveButton.setOnAction(event -> saveTask());
    }

    private void saveTask() {
        String title = titleField.getText();
        String description = descriptionField.getText();
        String dueDate = (dueDatePicker.getValue() != null) ? dueDatePicker.getValue().toString() : null;
        String category = categoryComboBox.getValue();

        if (title.isEmpty() || dueDate == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Заполните все обязательные поля!");
            alert.show();
            return;
        }

        // Используем DatabaseHandler для сохранения задачи
        boolean isTaskAdded = databaseHandler.addTask(title, description, dueDate, category);

        if (isTaskAdded) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Задача успешно сохранена!");
            alert.show();
            saveButton.getScene().getWindow().hide();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Ошибка при сохранении задачи.");
            alert.show();
        }
    }
}