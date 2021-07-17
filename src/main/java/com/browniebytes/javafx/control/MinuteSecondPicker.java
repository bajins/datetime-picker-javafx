package com.browniebytes.javafx.control;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MinuteSecondPicker extends VBox implements Initializable {

    private final DateTimePickerPopup parentContainer;

    @FXML
    private Slider slider;

    @FXML
    private Label label;

    MinuteSecondPicker(DateTimePickerPopup parentContainer) {
        this.parentContainer = parentContainer;

        // Load FXML
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MinuteSecondPicker.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException ex) {
            // Should never happen.  If it does however, we cannot recover
            // from this
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        slider.setMin(0);
        slider.setMax(59);
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            final int newValueInt = newValue.intValue();
            label.setText(String.format("%02d", newValueInt));
        });
        slider.onMouseReleasedProperty().set(event -> parentContainer.restoreTimePanel());
    }

    public int getValue() {
        return Integer.parseInt(label.getText());
    }
}
