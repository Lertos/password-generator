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

    private Generator generator = new Generator();

    private int currentSliderValue = 0;

    @FXML
    public void initialize() {
        //Set the initial values of the desired checkboxes
        cbLowercase.setSelected(true);
        cbUppercase.setSelected(true);
        cbNumbers.setSelected(true);
        cbSymbols.setSelected(true);

        //Set the initial slider value
        sliderPasswordLength.valueProperty().addListener(
                new ChangeListener<Number>() {
                    public void changed(ObservableValue<? extends Number > observable, Number oldValue, Number newValue) {
                        if (currentSliderValue != newValue.intValue()) {
                            currentSliderValue = newValue.intValue();
                            regeneratePassword();
                        }
                    }
                });

        //Generate the initial password
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
            if (cbLowercase.isSelected())
                generator.updateCharTypeList(CharType.LOWERCASE, true);
            else
                generator.updateCharTypeList(CharType.LOWERCASE, false);
        }
        //Uppercase
        else if (e.getSource().equals(cbUppercase)) {
            if (cbUppercase.isSelected())
                generator.updateCharTypeList(CharType.UPPERCASE, true);
            else
                generator.updateCharTypeList(CharType.UPPERCASE, false);
        }
        //Numbers
        else if (e.getSource().equals(cbNumbers)) {
            if (cbNumbers.isSelected())
                generator.updateCharTypeList(CharType.NUMBER, true);
            else
                generator.updateCharTypeList(CharType.NUMBER, false);
        }
        //Symbols
        else if (e.getSource().equals(cbSymbols)) {
            if (cbSymbols.isSelected())
                generator.updateCharTypeList(CharType.SYMBOL, true);
            else
                generator.updateCharTypeList(CharType.SYMBOL, false);
        }
    }

    private void regeneratePassword() {
        int passwordLength = (int) sliderPasswordLength.getValue();
        String newPassword = generator.generatePassword(passwordLength);

        labelPasswordLength.setText(String.valueOf(passwordLength));
        labelPassword.setText(newPassword);
    }
}