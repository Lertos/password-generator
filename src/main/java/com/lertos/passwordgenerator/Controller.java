package com.lertos.passwordgenerator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class Controller {
    @FXML
    private Button randomizeButton;
    @FXML
    private Slider sliderPasswordLength;
    @FXML
    private Label labelPasswordLength;
    private Generator generator = new Generator(true, false, false, false);

    @FXML
    public void initialize() {
        regeneratePassword();
    }

    @FXML
    protected void onButtonClick(ActionEvent e) {
        //Randomize button
        if (e.getSource().equals(randomizeButton)) {
            regeneratePassword();
        }
        //Copy button
    }

    @FXML
    protected void onSliderChange() {
        regeneratePassword();
    }

    private void regeneratePassword() {
        int initialSliderValue = (int) sliderPasswordLength.getValue();

        labelPasswordLength.setText(String.valueOf(initialSliderValue));
    }
}