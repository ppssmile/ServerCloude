package org.example;

import javafx.fxml.FXML;

import java.io.IOException;

public class GreetingsController {

    @FXML
    private void switchToSecondary() throws IOException {

        App.setRoot("secondary");

    }
}
