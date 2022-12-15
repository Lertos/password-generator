package com.lertos.passwordgenerator;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class Controller {
    @FXML
    private Button randomizeButton;
    @FXML
    private CheckBox cbLowercase;
    @FXML
    private CheckBox cbUppercase;
    @FXML
    private CheckBox cbNumbers;
    @FXML
    private CheckBox cbSymbols;
    @FXML
    private Slider sliderPasswordLength;
    @FXML
    private Label labelPasswordLength;
    @FXML
    private Label labelPassword;

    private Generator generator = new Generator(true, false, false, false);

    private int currentSliderValue = 0;

    @FXML
    public void initialize() {
        sliderPasswordLength.valueProperty().addListener(
                new ChangeListener<Number>() {
                    public void changed(ObservableValue<? extends Number > observable, Number oldValue, Number newValue) {
                        if (currentSliderValue != newValue.intValue()) {
                            currentSliderValue = newValue.intValue();
                            regeneratePassword();
                        }
                    }
                });

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
    protected void onCheckboxClick(ActionEvent e) {
        //Lowercase
        if (e.getSource().equals(cbLowercase)) {

        }
        //Uppercase
        else if (e.getSource().equals(cbUppercase)) {

        }
        //Numbers
        else if (e.getSource().equals(cbNumbers)) {

        }
        //Symbols
        else if (e.getSource().equals(cbSymbols)) {

        }
    }

    private void regeneratePassword() {
        int passwordLength = (int) sliderPasswordLength.getValue();
        String newPassword = generator.generatePassword(passwordLength);

        labelPasswordLength.setText(String.valueOf(passwordLength));
        labelPassword.setText(newPassword);
    }
}