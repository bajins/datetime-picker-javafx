package com.browniebytes.javafx.control;

import javafx.application.Application;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DateTimePickerTest extends Application implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        final VBox vBox = new VBox();
        vBox.getChildren().add(new Label("Date/Time"));
        vBox.getChildren().add(new DateTimePicker());

        final Scene scene = new Scene(vBox);

        primaryStage.setScene(scene);
        primaryStage.sizeToScene();

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
