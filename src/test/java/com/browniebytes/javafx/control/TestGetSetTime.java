package com.browniebytes.javafx.control;

import javafx.application.Application;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.ResourceBundle;

public class TestGetSetTime extends Application implements Initializable {
    private final String style = "yyyy-MM-dd HH:mm:ss";

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void start(Stage primaryStage) {
        LocalDateTime testTime = LocalDateTime.now().plusYears(1);

        Group root = new Group();

        DateTimePicker dateTimePicker = new DateTimePicker();
        dateTimePicker.setLayoutX(18);
        dateTimePicker.setLayoutY(10);
        dateTimePicker.setPrefWidth(600);
        dateTimePicker.setPrefHeight(10);
        root.getChildren().add(dateTimePicker);

        Button buttonSetDate = new Button();
        buttonSetDate.setLayoutX(18);
        buttonSetDate.setLayoutY(60);
        buttonSetDate.setPrefWidth(600);
        buttonSetDate.setPrefHeight(10);
        buttonSetDate.setText("设置时间为 " + testTime);
        root.getChildren().add(buttonSetDate);

        Button buttonGetDate = new Button();
        buttonGetDate.setLayoutX(18);
        buttonGetDate.setLayoutY(100);
        buttonGetDate.setPrefWidth(600);
        buttonGetDate.setPrefHeight(10);
        buttonGetDate.setText("获取日期时间选择器中的内容");
        root.getChildren().add(buttonGetDate);

        TextField textField = new TextField();
        textField.setLayoutX(18);
        textField.setLayoutY(140);
        textField.setPrefWidth(600);
        textField.setPrefHeight(50);
        textField.setText("");
        textField.setEditable(false);// 设置不可编辑
        root.getChildren().add(textField);

        final Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("日期时间选择器控件使用案例");
        primaryStage.setHeight(700);
        primaryStage.setWidth(1000);
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();

        primaryStage.show();

        buttonSetDate.setOnAction(event -> {
            try {
                // dateTimePicker.setDateString(TEST_TIME, TEST_FONT);//
                // 设置DateTimePicker要显示的时间
                dateTimePicker.setTime(Date.from(testTime.toInstant(ZoneOffset.ofHours(8))));
            } catch (Exception e) {
                textField.setText(e.toString());
            }
        });

        buttonGetDate.setOnAction(event -> {
            try {
                textField.setText(dateTimePicker.getTime(style));// 将获取到的时间显示到文本框
            } catch (Exception e) {
                textField.setText(e.toString());
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
