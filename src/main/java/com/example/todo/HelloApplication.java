package com.example.todo;

import com.example.todo.Tasks.Task;
import com.example.todo.Tasks.TaskTree;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.TreeView;

import java.io.IOException;

public class HelloApplication extends Application {
    private TaskTree tree;

    @Override
    public void start(Stage stage) throws IOException {

    }

    public static void main(String[] args) {
        launch();
    }
}