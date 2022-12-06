package com.groupi.groupi2;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import org.controlsfx.control.CheckComboBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SeekerProfileController implements Initializable {
    @FXML
    TextField email;
    @FXML
    TextField firstName;
    @FXML
    TextField lastName;
    @FXML
    ComboBox education;
    @FXML
    CheckComboBox skills;
    @FXML
    ComboBox location;
    @FXML
    Button saveButton;
    @FXML
    Label userMessage;

    public void jobsButton(ActionEvent event) throws IOException {
        SceneController sc = new SceneController();
        sc.switchScene(event, "seeker-jobs.fxml");
    }

    // TODO: Implement notifications page
    public void notificationsButton(ActionEvent event) throws IOException {}

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
            JobSeeker me = (JobSeeker)currUser.me;

            // skills
            ObservableList selectedSkills = skills.getCheckModel().getCheckedIndices();
            ArrayList<String> skillIds = new ArrayList<>();
            ArrayList<Integer> intSkillIds = new ArrayList<>();
            for (int i = 0; i <= selectedSkills.size() - 1; i++) {
                int id = i + 1;
                intSkillIds.add(id);
                skillIds.add(Integer.toString(id));
            }

            boolean isUpdated = me.updateProfile(
                    me.getId(),
                    firstName.getText(),
                    lastName.getText(),
                    education.getValue() == null ? me.getEducation() : education.getValue().toString(),
                    location.getSelectionModel().getSelectedIndex() + 1,
                    String.join(",", String.join(",", skillIds))
            );
            if (isUpdated) {
                userMessage.setTextFill(Color.LIGHTSEAGREEN);
                userMessage.setText("Profile updated!");
            } else {
                // Give user an error code to contact support with.
                userMessage.setText("Failed to update profile. Error code: S0001.1");
            }
        } else {
            userMessage.setText(errorText);
        }
    }

    public void saveLocation(ActionEvent event) {
        // TODO: Save location to db
        System.out.println(location.getValue().toString());
    }

    public void saveEducation(ActionEvent event) {
        // TODO: Save location to db
        System.out.println(education.getValue().toString());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        JobSeeker me = (JobSeeker) CurrentUserSingleton.CurrentUserSingleton().me;

        ObservableList<String> skillsList = Skill.getAllSkills();
        skills.getItems().addAll(skillsList);
        skills.getCheckModel().getCheckedItems().addListener(new ListChangeListener() {
            @Override
            public void onChanged(Change change) {
                ObservableList selectedSkills = skills.getCheckModel().getCheckedItems();
                for (int i = 0; i <= selectedSkills.size() - 1; i++) {
                    System.out.println(selectedSkills.get(i));
                    // TODO: save skill to user
                }
            }
        });

        ObservableList allEducationLevels = FXCollections.observableArrayList();
        allEducationLevels.add("Diploma");
        allEducationLevels.add("Bachelors Degree");
        allEducationLevels.add("Graduate Degree");
        allEducationLevels.add("Masters Degree");
        allEducationLevels.add("Doctoral Degree");
        education.getItems().addAll(allEducationLevels);

        email.setText(me.getEmail());
        firstName.setText(me.getFirstName());
        lastName.setText(me.getLastName());
        location.setValue(Location.getLocationNameById(me.getLocationId()));
        education.setValue(me.getEducation());

        ObservableList<String> locationsList = Location.getAllLocations();
        location.getItems().addAll(locationsList);

        // TODO: set skills
    }
}
