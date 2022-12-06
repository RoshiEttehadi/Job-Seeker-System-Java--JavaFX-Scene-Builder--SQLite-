package com.groupi.groupi2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class RegisterController {
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private ToggleGroup userType;
    @FXML
    private Label error;

    public void registerUser(ActionEvent event) throws Exception {
        Authentication auth = new Authentication();
        Validation validation = new Validation();
        String errorText = null;
        RadioButton rb = (RadioButton)userType.getSelectedToggle(); // "Job Seeker" || "Recruiter"

        if (!validation.checkEmailIsValid(email.getText()) || validation.stringIsBlank(email.getText())) {
            errorText = "Invalid email address.\n";
        }
        if (validation.stringIsBlank(firstName.getText())) {
            errorText += "First name cannot be blank.\n";
        }
        if (validation.stringIsBlank(lastName.getText())) {
            errorText += "Last name cannot be blank.\n";
        }
        if (validation.stringIsBlank(password.getText())) {
            errorText += "Password cannot be blank.";
        }
        if (validation.stringIsBlank(rb.getText())) {
            errorText += "You must select a user type.";
        }

        if (errorText == null) {
            boolean success = auth.register(firstName.getText(), lastName.getText(), email.getText(), password.getText(), rb.getText());
            if (success) {
                CurrentUserSingleton me = CurrentUserSingleton.CurrentUserSingleton();
                SceneController sc = new SceneController();
                if (rb.getText().equals("Recruiter")) {
                    Recruiter createdUser = auth.findRecruiterByEmail(email.getText());
                    me.setMe(new Recruiter(createdUser.getId(), createdUser.getFirstName(), createdUser.getLastName(), createdUser.getEmail(), createdUser.getPassword()));
                    sc.switchScene(event, "recruiter-profile.fxml");
                } else {
                    JobSeeker createdUser = auth.findSeekerByEmail(email.getText());
                    me.setMe(new JobSeeker(createdUser.getId(), createdUser.getFirstName(), createdUser.getLastName(), createdUser.getEmail(), createdUser.getPassword()));
                    sc.switchScene(event, "seeker-profile.fxml");
                }
            } else {
                error.setText("Error: Email already registered in system. Please use another email.");
            }
        } else {
            error.setText(errorText);
        }
    }

    public void navigateToMain(ActionEvent event) throws IOException {
        SceneController sc = new SceneController();
        sc.switchScene(event, "main.fxml");
    }
}
