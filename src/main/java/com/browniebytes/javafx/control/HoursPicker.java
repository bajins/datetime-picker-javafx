package com.browniebytes.javafx.control;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class HoursPicker extends GridPane implements Initializable {

    private static final int NUM_BUTTONS = 12;

    private final List<ToggleButton> buttonList = new ArrayList<ToggleButton>(NUM_BUTTONS);

    private final DateTimePickerPopup parentContainer;

    @FXML
    private ToggleGroup hoursToggleGroup;

    @FXML
    private ToggleButton zeroButton;

    @FXML
    private ToggleButton oneButton;

    @FXML
    private ToggleButton twoButton;

    @FXML
    private ToggleButton threeButton;

    @FXML
    private ToggleButton fourButton;

    @FXML
    private ToggleButton fiveButton;

    @FXML
    private ToggleButton sixButton;

    @FXML
    private ToggleButton sevenButton;

    @FXML
    private ToggleButton eightButton;

    @FXML
    private ToggleButton nineButton;

    @FXML
    private ToggleButton tenButton;

    @FXML
    private ToggleButton elevenButton;

    @FXML
    private ToggleButton amPmButton;

    HoursPicker(DateTimePickerPopup parentContainer) {

        this.parentContainer = parentContainer;

        // Load FXML
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HoursPicker.fxml"));
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
        buttonList.add(zeroButton);
        buttonList.add(oneButton);
        buttonList.add(twoButton);
        buttonList.add(threeButton);
        buttonList.add(fourButton);
        buttonList.add(fiveButton);
        buttonList.add(sixButton);
        buttonList.add(sevenButton);
        buttonList.add(eightButton);
        buttonList.add(nineButton);
        buttonList.add(tenButton);
        buttonList.add(elevenButton);

        // Size the AM/PM toggle button
        amPmButton.prefHeightProperty().bind(zeroButton.heightProperty().multiply(3).add(getHgap() * 2));
        //amPmButton.prefWidthProperty().set(40);

        Locale locale = Locale.getDefault();
        // Configure AM/PM toggle button change listener
        amPmButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
            // Offset added for times after noon and before midnight
            int offset = 0;

            // Toggle "AM" and "PM" and add 12 to the offset for PM
            if (newValue) {
                if (locale.equals(Locale.CHINA)) {
                    amPmButton.setText("下午");
                } else {
                    amPmButton.setText("PM");
                }
                offset = NUM_BUTTONS;
            } else {
                if (locale.equals(Locale.CHINA)) {
                    amPmButton.setText("上午");
                } else {
                    amPmButton.setText("AM");
                }
            }

            // Update button text
            for (int i = 0; i < NUM_BUTTONS; i++) {
                buttonList.get(i).setText(String.format("%02d", i + offset));
            }

            // TODO: update hour value
        });

        // Set hour to the value stored in the parent
        int hour = parentContainer.getHour();

        // Select button
        selectButton(hour);

        // Change listener ensures that an hour value is always selected
        hoursToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            // If user clicks the same value, set it back to the
            // old value and do nothing
            if (newValue == null) {
                hoursToggleGroup.selectToggle(oldValue);
            } else {
                parentContainer.restoreTimePanel();
            }
        });
    }

    private void selectButton(int hour) {
        int offset = 0;
        if (hour > 11) {
            amPmButton.setSelected(true);
            offset = -12;
        }
        hoursToggleGroup.selectToggle(buttonList.get(hour + offset));
    }

    int getHour() {
        int hour = buttonList.indexOf(hoursToggleGroup.getSelectedToggle());
        if (amPmButton.isSelected()) {
            hour += 12;
        }
        return hour;
    }

    public void setHour(int hour) {
        selectButton(hour);
    }
}
