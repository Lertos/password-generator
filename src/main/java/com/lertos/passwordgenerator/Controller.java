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

    private final Generator generator = new Generator();

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
                new ChangeListener<>() {
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
        if (e.getSource().equals(cbLowercase))
            generator.updateCharTypeList(CharType.LOWERCASE, cbLowercase.isSelected());
        //Uppercase
        else if (e.getSource().equals(cbUppercase))
            generator.updateCharTypeList(CharType.UPPERCASE, cbUppercase.isSelected());
        //Numbers
        else if (e.getSource().equals(cbNumbers))
            generator.updateCharTypeList(CharType.NUMBER, cbNumbers.isSelected());
        //Symbols
        else if (e.getSource().equals(cbSymbols))
            generator.updateCharTypeList(CharType.SYMBOL, cbSymbols.isSelected());

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