package com.groupi.groupi2;

import com.groupi.groupi2.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {
    private Stage stage;
    private Scene scene;
    private FXMLLoader root;

    public void switchScene(ActionEvent event, String name) throws IOException {
        root = new FXMLLoader(Main.class.getResource(name));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root.load(), 1280, 960);
        stage.setScene(scene);
        stage.show();
    }

    public void showDialog(String fxml) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = new Stage();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setAlwaysOnTop(true);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showRecruiterViewJobDialog(Job job) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("recruiter-view-job.fxml"));

            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));

            RecruiterViewJobController controller = loader.getController();
            controller.setStage(job);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showRecruiterCreateJobDialog(TableView jobsTable) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("recruiter-create-job.fxml"));

            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));

            RecruiterCreateJobController controller = loader.getController();
            controller.setStage(jobsTable);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showSeekerViewJobDialog(Job job, TableView table) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("seeker-view-job.fxml"));

            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));

            SeekerViewJobController controller = loader.getController();
            controller.setStage(job, table);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
