package com.example.todo;

import com.example.todo.Tasks.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TreeView;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;

public class HelloController {
    private Stage primaryStage;
    @FXML
    private Label welcomeText;

    @FXML
    private InputEvent input;

    public HelloController() {
        primaryStage = null;
    }

    @FXML
    public void setInput() {
        input.getEventType();
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    @FXML
    private TreeView<Task> treeView;

    public void YourController(Stage primaryStage) {
        // Armazena o primaryStage
        this.primaryStage = primaryStage;
    }
}