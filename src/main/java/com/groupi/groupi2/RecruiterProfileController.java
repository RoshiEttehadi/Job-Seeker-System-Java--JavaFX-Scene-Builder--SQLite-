package com.groupi.groupi2;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import org.controlsfx.control.CheckComboBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RecruiterProfileController implements Initializable {
    @FXML
    TextField email;
    @FXML
    TextField firstName;
    @FXML
    TextField lastName;
    @FXML
    TextField companyName;
    @FXML
    TextField ABN;
    @FXML
    ComboBox location;
    @FXML
    Button saveButton;
    @FXML
    Label userMessage;

    public void jobsButton(ActionEvent event) throws IOException {
        SceneController sc = new SceneController();
        sc.switchScene(event, "recruiter-jobs.fxml");
    }

    public void notificationsButton() {
        // TODO: Implement notifications page
    }

    public void logoutButton(ActionEvent event) throws IOException {
        CurrentUserSingleton currUser = CurrentUserSingleton.CurrentUserSingleton();
        currUser.setMe(null);
        SceneController sc = new SceneController();
        sc.switchScene(event, "main.fxml");
    }
    public void saveProfile() {
        Validation validation = new Validation();
        String errorText = null;

        if (validation.stringIsBlank(firstName.getText())) {
            errorText = "First name cannot be blank.";
        }
        if (validation.stringIsBlank(lastName.getText())) {
            errorText += "\nLast name cannot be blank.";
        }
        if (errorText == null) {
            CurrentUserSingleton currUser = CurrentUserSingleton.CurrentUserSingleton();
            Recruiter me = (Recruiter) currUser.me;
            boolean isUpdated = me.updateProfile(
                    me.getId(),
                    firstName.getText(),
                    lastName.getText(),
                    companyName.getText(),
                    ABN.getText(),
                    location.getSelectionModel().getSelectedIndex() + 1
            );
            if (isUpdated) {
                userMessage.setTextFill(Color.LIGHTSEAGREEN);
                userMessage.setText("Profile updated!");
            } else {
                // Give user an error code to contact support with.
                userMessage.setText("Failed to update profile. Error code: S0001.2");
            }
        } else {
            userMessage.setText(errorText);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Recruiter me = (Recruiter) CurrentUserSingleton.CurrentUserSingleton().me;
        email.setText(me.getEmail());
        firstName.setText(me.getFirstName());
        lastName.setText(me.getLastName());
        companyName.setText(me.getCompanyName());
        ABN.setText(me.getAbn());
        location.setValue(Location.getLocationNameById(me.getLocationId()));

        ObservableList<String> locationsList = Location.getAllLocations();
        location.getItems().addAll(locationsList);
    }
}
