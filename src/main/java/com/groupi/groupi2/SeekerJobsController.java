package com.groupi.groupi2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import org.controlsfx.control.CheckComboBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SeekerJobsController implements Initializable {
    @FXML
    private TextField searchField;
    @FXML
    private CheckComboBox categoriesField;
    @FXML
    private CheckComboBox skillsField;
    @FXML
    private TableView jobsTable;
    @FXML
    private TableColumn<Job, String> tcJobTitle;
    private ObservableList<Job> data = FXCollections.observableArrayList();

    public void profileButton(ActionEvent event) throws IOException {
        SceneController sc = new SceneController();
        sc.switchScene(event, "seeker-profile.fxml");
    }

    public void notificationsButton(ActionEvent event) throws IOException {
        // TODO: Implement notifications page
    }

    public void logoutButton(ActionEvent event) throws IOException {
        CurrentUserSingleton currUser = CurrentUserSingleton.CurrentUserSingleton();
        currUser.setMe(null);
        SceneController sc = new SceneController();
        sc.switchScene(event, "main.fxml");
    }

    public void searchJobs() {
        ObservableList<Job> allJobs = data.filtered(job -> {
            // get search term, search in job titles
            String searchTerm = searchField.getText();
            String jobTitle = job.getTitle();
            Double titleSimilarity = JobSearch.getSimilarity(searchTerm, jobTitle);
            // Using a low weight of 0.200 to match strings to ensure no jobs are missed by the algorithm.
            // This can easily be calibrated via testing, data analysis, and user feedback.
            return titleSimilarity > 0.200;
        });

        jobsTable.setItems(allJobs);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        jobsTable.setRowFactory(tv -> {
            TableRow<Job> jobRow = new TableRow<>();
            jobRow.setOnMouseClicked(event -> {
                if (! jobRow.isEmpty() && event.getButton()== MouseButton.PRIMARY
                        && event.getClickCount() == 2) {

                    Job clickedRow = jobRow.getItem();
                    System.out.println(clickedRow.getTitle());
                    SceneController sc = new SceneController();
                    sc.showSeekerViewJobDialog(clickedRow, jobsTable);
                }
            });
            return jobRow;
        });
        tcJobTitle.setCellValueFactory(new PropertyValueFactory<>("title"));

        CurrentUserSingleton currUser = CurrentUserSingleton.CurrentUserSingleton();
        JobSeeker me = (JobSeeker)currUser.me;
        ObservableList<Job> allJobs = Job.getAllJobs().filtered(job -> {
            boolean isPublished = job.getIsPublished() == 1 ? true : false;
            boolean isDisabled = job.getIsDisabled() == 1 ? true : false;
            JobApplication existingApp = JobApplication.getJobApplicationForSeeker(job.getId(), me.getId());

            return isPublished && !isDisabled && existingApp == null;
        });
        for (Job job : allJobs) {
            data.add(job);
        }

        jobsTable.setItems(data);

        // set categories
        ObservableList<String> categoriesList = Category.getAllCategories();
        categoriesField.getItems().addAll(categoriesList);
        // set skills
        ObservableList<String> skillsList = Skill.getAllSkills();
        skillsField.getItems().addAll(skillsList);
    }
}
