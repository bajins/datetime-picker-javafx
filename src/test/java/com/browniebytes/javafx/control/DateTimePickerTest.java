package com.browniebytes.javafx.control;

import javafx.application.Application;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.ResourceBundle;

public class DateTimePickerTest extends Application implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void start(Stage primaryStage) {
        final VBox vBox = new VBox();

        String text;
        Locale locale = Locale.getDefault();
        if (locale.equals(Locale.CHINA)) {
            text = "日期/时间";
        } else {
            text = "Date/Time";
        }
        vBox.getChildren().add(new Label(text));
        //vBox.getChildren().add(new DateTimePicker());
        vBox.getChildren().add(new DateTimePicker(LocalDateTime.now()));

        final Scene scene = new Scene(vBox);

        primaryStage.setScene(scene);
        primaryStage.sizeToScene();

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
