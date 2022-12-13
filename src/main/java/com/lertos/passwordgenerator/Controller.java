package com.lertos.passwordgenerator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Controller {
    @FXML
    private Button randomizeButton;
    private Generator generator = new Generator(true, false, false, false);

    @FXML
    protected void onButtonClick(ActionEvent e) {
        if (e.getSource().equals(randomizeButton)) {
            generator.generatePassword();
        }
    }
}