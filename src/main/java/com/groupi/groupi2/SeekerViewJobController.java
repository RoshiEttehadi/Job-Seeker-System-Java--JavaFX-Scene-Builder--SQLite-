package com.groupi.groupi2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class SeekerViewJobController {
    private Job job;
    @FXML private Label subheader;
    @FXML private TextArea coverLetter;
    @FXML private TextArea additionalInfo;
    @FXML private Label jobInfo;
    @FXML private Label jobSalary;
    @FXML private TableView tb;

    public void applyForJob(ActionEvent event) {
        CurrentUserSingleton currUser = CurrentUserSingleton.CurrentUserSingleton();
        JobSeeker me = (JobSeeker)currUser.me;
        JobApplication app = new JobApplication(job.getId(), me.getId(), coverLetter.getText(), additionalInfo.getText());
        boolean didSave = JobApplication.saveJobApplication(app);

        if (didSave) {
            tb.getItems().remove(job);
        } else {
            System.out.println("Error saving job application.");
        }

        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
    public void cancel(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    public void setStage(Job job, TableView table) {
        this.job = job;
        this.tb = table;

        subheader.setText(job.getTitle());
        jobInfo.setText(job.getDescription());
        jobSalary.setText("Salary range: " + job.getSalaryRange());
        // TODO: Add skills, location, category info to view job page.
    }
}
