package com.example.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Diem danh");
        stage.setScene(scene);
        stage.setMaxHeight(900);
        stage.setMaxWidth(1200);
        stage.show();
    }

    public static void main(String[] args) {
        launch();

    }
}