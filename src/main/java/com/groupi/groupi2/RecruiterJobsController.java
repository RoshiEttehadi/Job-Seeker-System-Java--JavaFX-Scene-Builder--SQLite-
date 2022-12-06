package com.groupi.groupi2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class RecruiterJobsController implements Initializable {

    @FXML
    private TableView jobsTable;
    @FXML
    private TableColumn<Job, String> tcJobTitle;
    private ObservableList<Job> data = FXCollections.observableArrayList();
    public void profileButton(ActionEvent event) throws IOException {
        SceneController sc = new SceneController();
        sc.switchScene(event, "recruiter-profile.fxml");
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
                    sc.showRecruiterViewJobDialog(clickedRow);
                }
            });
            return jobRow;
        });

        tcJobTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        Recruiter me = (Recruiter) CurrentUserSingleton.CurrentUserSingleton().me;
        ObservableList<Job> allJobs = Job.getAllJobs().filtered(job -> job.getRecruiterId() == me.getId());
        for (Job job : allJobs) {
            data.add(job);
        }

        jobsTable.setItems(data);
    }

    @FXML
    void handleCreateJobButtonClick(ActionEvent event) {
        SceneController sc = new SceneController();
        sc.showRecruiterCreateJobDialog(jobsTable);
    }
}
