package com.groupi.groupi2;

import com.groupi.groupi2.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.controlsfx.control.action.Action;

import java.io.IOException;

public class LoginController {
    private String userType;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Label error;
    @FXML private VBox loginForm;
    @FXML private HBox userSelect;

    public void handleLogin(ActionEvent event) throws IOException {
        Authentication auth = new Authentication();
        Validation validation = new Validation();
        SceneController sc = new SceneController();
        String errorText = null;

        if (!validation.checkEmailIsValid(emailField.getText()) || validation.stringIsBlank(emailField.getText())) {
            errorText = "Invalid email address.\n";
        }
        if (validation.stringIsBlank(passwordField.getText())) {
            errorText += "Password cannot be blank.";
        }

        if (errorText == null) {
            if (userType.equals("Recruiter")) {
                Recruiter authenticatedUser = null;
                try {
                    authenticatedUser = auth.findRecruiterByEmail(emailField.getText());
                } catch (Exception e) {
                    error.setText("Incorrect email or password.");
                }

                if (authenticatedUser != null && authenticatedUser.getPassword().equals(passwordField.getText())) {
                    CurrentUserSingleton me = CurrentUserSingleton.CurrentUserSingleton();
                    me.setMe(new Recruiter(
                            authenticatedUser.getId(),
                            authenticatedUser.getFirstName(),
                            authenticatedUser.getLastName(),
                            authenticatedUser.getEmail(),
                            authenticatedUser.getPassword(),
                            authenticatedUser.getCompanyName(),
                            authenticatedUser.getAbn(),
                            authenticatedUser.getLocationId()
                    ));
                    sc.switchScene(event, "recruiter-profile.fxml");
                } else {
                    error.setText("Incorrect email or password.");
                }
            } else {
                JobSeeker authenticatedUser = null;
                try {
                    authenticatedUser = auth.findSeekerByEmail(emailField.getText());
                } catch (Exception e) {
                    error.setText("Incorrect email or password.");
                }

                if (authenticatedUser != null && authenticatedUser.getPassword().equals(passwordField.getText())) {
                    CurrentUserSingleton me = CurrentUserSingleton.CurrentUserSingleton();
                    me.setMe(new JobSeeker(
                            authenticatedUser.getId(),
                            authenticatedUser.getFirstName(),
                            authenticatedUser.getLastName(),
                            authenticatedUser.getEmail(),
                            authenticatedUser.getPassword(),
                            authenticatedUser.getEducation(),
                            authenticatedUser.getLocationId(),
                            authenticatedUser.getSkillIds()
                    ));
                    sc.switchScene(event, "seeker-profile.fxml");
                } else {
                    error.setText("Incorrect email or password.");
                }
            }
        } else {
            error.setText(errorText);
        }
    }

    public void loginAsSeeker() {
        this.userType = "Job Seeker";
        userSelect.setVisible(false);
        loginForm.setVisible(true);
    }

    public void loginAsRecruiter() {
        this.userType = "Recruiter";
        userSelect.setVisible(false);
        loginForm.setVisible(true);
    }

    public void navigateToMain(ActionEvent event) throws IOException {
        SceneController sc = new SceneController();
        sc.switchScene(event, "main.fxml");
    }
}
