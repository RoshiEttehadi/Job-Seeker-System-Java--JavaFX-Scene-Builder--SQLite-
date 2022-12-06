package com.groupi.groupi2;

import javafx.event.ActionEvent;

import java.io.IOException;

public class MainController {
    public void navigateToLogin(ActionEvent event) throws IOException {
        SceneController sc = new SceneController();
        sc.switchScene(event, "login.fxml");
    }

    public void navigateToRegister(ActionEvent event) throws IOException {
        SceneController sc = new SceneController();
        sc.switchScene(event, "register.fxml");
    }
}