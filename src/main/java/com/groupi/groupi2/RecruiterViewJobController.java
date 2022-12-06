package com.groupi.groupi2;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class RecruiterViewJobController implements Initializable {
    @FXML private Label header;
    @FXML private TextField title;
    @FXML private TextArea description;
    @FXML private ComboBox salaryRange;
    @FXML private ComboBox location;
    @FXML private CheckComboBox categories;
    @FXML private CheckComboBox skills;
    @FXML private CheckBox isPublished;
    @FXML private Button editJobButton;
    @FXML private TableView applicationsTable;
    @FXML private TableColumn<JobApplication, String> tcApplicationsMatch;
    @FXML private TableColumn<JobApplication, String> tcApplicationsEmail;
    @FXML private TableView candidatesTable;
    @FXML private TableColumn<JobSeeker, String> tcCandidatesMatch;
    @FXML private TableColumn<JobSeeker, String> tcCandidatesEmail;

    private ObservableList<JobApplication> applicationsTableData = FXCollections.observableArrayList();
    private ObservableList<JobSeeker> candidatesTableData = FXCollections.observableArrayList();

    private Job job;

    public void setStage(Job job) {
        this.job = job;
        boolean jobIsPublished = job.getIsPublished() == 0 ? false : true;
        if (jobIsPublished) {
            title.setDisable(true);
            description.setDisable(true);
            salaryRange.setDisable(true);
            location.setDisable(true);
            categories.setDisable(true);
            skills.setDisable(true);
            isPublished.setDisable(true);
            editJobButton.setDisable(true);
        }

        header.setText(job.getTitle());
        title.setText(job.getTitle());
        description.setText(job.getDescription());
        salaryRange.setPromptText(job.getSalaryRange());
        //location.setPromptText(job.getLocationId());
        //categories.getCheckModel().
        //skills.setPromptText(job.getTitle());
        isPublished.setSelected(jobIsPublished);
    }

    public void editJob(ActionEvent event) throws IOException {}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tcApplicationsMatch.setCellValueFactory(cellData -> new ReadOnlyStringWrapper("2%"));
        tcApplicationsEmail.setCellValueFactory(cellData -> new ReadOnlyStringWrapper("Tom Kenyon"));
        // tcApplicationsEmail.setCellValueFactory(cell -> cell.getValue().getApplicantEmail());
        //tcCandidatesEmail.setCellValueFactory(cell -> cell.getValue().getEmail());

        // Set applications table data
        ObservableList<JobApplication> allApplications = JobApplication.getAllApplicationsForJob();
        System.out.println(allApplications);
        applicationsTableData.add(new JobApplication(
                1, 1, 1, "test", "test"
        ));
        for (JobApplication app : allApplications) {
            System.out.println("App id 81: " + app.getJobSeekerId());
            applicationsTableData.add(app);
        }
        applicationsTable.setItems(applicationsTableData);

        // Set candidates table data
//        ObservableList<Job> allJobs = Job.getAllJobs().filtered(job -> {
//            boolean isPublished = job.getIsPublished() == 1 ? true : false;
//            boolean isDisabled = job.getIsDisabled() == 1 ? true : false;
//            JobApplication existingApp = JobApplication.getJobApplicationForSeeker(job.getId(), me.getId());
//
//            return isPublished && !isDisabled && existingApp == null;
//        });
//        for (Job job : allJobs) {
//            data.add(job);
//        }
//
//        jobsTable.setItems(data);
    }
}
