module com.lertos.passwordgenerator {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.lertos.passwordgenerator to javafx.fxml;
    exports com.lertos.passwordgenerator;
}