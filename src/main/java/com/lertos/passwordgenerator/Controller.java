package com.lertos.passwordgenerator;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class Controller {
    @FXML
    private Button randomizeButton;
    @FXML
    private Button copyButton;
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
        //Fire the events manually so the initial values will be set in the generator
        cbLowercase.fireEvent(new ActionEvent());
        cbUppercase.fireEvent(new ActionEvent());
        cbNumbers.fireEvent(new ActionEvent());
        cbSymbols.fireEvent(new ActionEvent());

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
        else if (e.getSource().equals(copyButton)) {
            ClipboardContent content = new ClipboardContent();
            content.putString(labelPassword.getText());
            Clipboard.getSystemClipboard().setContent(content);
        }
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

        //Generate a new password with the new settings
        regeneratePassword();
    }

    private void regeneratePassword() {
        int passwordLength = (int) sliderPasswordLength.getValue();
        String newPassword = generator.generatePassword(passwordLength);

        labelPasswordLength.setText(String.valueOf(passwordLength));
        labelPassword.setText(newPassword);
    }
}