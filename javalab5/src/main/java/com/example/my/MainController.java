package com.example.my;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.*;
import java.io.IOException;

public class MainController {

    @FXML
    private Button deleteTaskButton; // Кнопка удаления задачи


    private void deleteSelectedTask() {
        Task selectedTask = taskTable.getSelectionModel().getSelectedItem();
        if (selectedTask == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Выберите задачу для удаления.");
            alert.show();
            return;
        }

        // Удаляем задачу из базы данных
        boolean isDeleted = databaseHandler.deleteTask(selectedTask.getId());
        if (isDeleted) {
            tasks.remove(selectedTask); // Удаляем задачу из таблицы
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Задача успешно удалена.");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Не удалось удалить задачу.");
            alert.show();
        }
    }


    @FXML
    private TableView<Task> taskTable;

    @FXML
    private TableColumn<Task, String> titleColumn;

    @FXML
    private TableColumn<Task, String> descriptionColumn;

    @FXML
    private TableColumn<Task, String> dueDateColumn;

    @FXML
    private Button addTaskButton;

    private final DatabaseHandler databaseHandler = new DatabaseHandler();

    private final ObservableList<Task> tasks = FXCollections.observableArrayList(); // Данные для таблицы

    @FXML
    public void initialize() {
        // Настройка колонок таблицы
        titleColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTitle()));
        descriptionColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDescription()));
        dueDateColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDueDate()));

        // Установка данных в таблицу
        taskTable.setItems(tasks);

        // Загрузка данных из базы данных
        loadTasks();

        // Обработка кнопки добавления задачи
        addTaskButton.setOnAction(event -> openAddTaskWindow());

        // Обработка кнопки удаления задачи
        deleteTaskButton.setOnAction(event -> deleteSelectedTask());
    }

    private void loadTasks() {
        tasks.clear(); // Сначала очищаем текущие данные
        tasks.addAll(databaseHandler.getAllTasks()); // Загружаем данные из базы
        System.out.println("Список задач обновлён.");
    }

    private void openAddTaskWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("add-task-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 400, 300);
            Stage addTaskStage = new Stage();
            addTaskStage.setTitle("Добавить новую задачу");
            addTaskStage.setScene(scene);
            addTaskStage.initModality(Modality.APPLICATION_MODAL);
            addTaskStage.showAndWait();

            // После закрытия окна добавления задачи обновляем таблицу
            loadTasks();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}