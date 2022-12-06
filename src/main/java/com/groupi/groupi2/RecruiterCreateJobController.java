package com.groupi.groupi2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class RecruiterCreateJobController implements Initializable {
    @FXML private TextField title;
    @FXML private TextArea description;
    @FXML private ComboBox salaryRange;
    @FXML private ComboBox location;
    @FXML private CheckComboBox categories;
    @FXML private CheckComboBox skills;
    @FXML private CheckBox isPublished;
    @FXML private Label error;

    @FXML private TableView tb;
    public void createJob(ActionEvent event) throws IOException {

        Validation validation = new Validation();
        String errorText = null;

        if (validation.stringIsBlank(title.getText())) {
            errorText = "Error: Missing required fields.";
        }
        if (validation.stringIsBlank(description.getText())) {
            errorText = "Error: Missing required fields.";
        }
        if (salaryRange.getValue() == null) {
            errorText = "Error: Missing required fields.";
        }
        if (salaryRange.getValue() == null) {
            errorText = "Error: Missing required fields.";
        }
        if (location.getSelectionModel().getSelectedIndex() < 0) {
            errorText = "Error: Missing required fields.";
        }
        if (categories.getCheckModel().getCheckedIndices().size() == 0) {
            errorText = "Error: Missing required fields.";
        }
        if (skills.getCheckModel().getCheckedIndices().size() == 0) {
            errorText = "Error: Missing required fields.";
        }

        if (errorText == null) {
            try{
                Recruiter me = (Recruiter) CurrentUserSingleton.CurrentUserSingleton().me;
                String sql = "INSERT INTO jobs(recruiterId, title, description, salaryRange," +
                        "locationId, categoryIds, skillIds, isPublished) VALUES(?,?,?,?,?,?,?,?);";
                Connection conn = new DatabaseHandler().getConn();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, me.getId());
                pstmt.setString(2, title.getText());
                pstmt.setString(3, description.getText());
                pstmt.setString(4, salaryRange.getValue().toString());
                pstmt.setInt(5, location.getSelectionModel().getSelectedIndex() + 1);
                ObservableList selectedCategories = categories.getCheckModel().getCheckedIndices();
                ArrayList<String> categoryIds = new ArrayList<>();
                ArrayList<Integer> intCategoryIds = new ArrayList<>();
                for (int i = 0; i <= selectedCategories.size() - 1; i++) {
                    int id = i + 1;
                    intCategoryIds.add(id);
                    categoryIds.add(Integer.toString(id));
                }
                ObservableList selectedSkills = skills.getCheckModel().getCheckedIndices();
                ArrayList<String> skillIds = new ArrayList<>();
                ArrayList<Integer> intSkillIds = new ArrayList<>();
                for (int i = 0; i <= selectedSkills.size() - 1; i++) {
                    int id = i + 1;
                    intSkillIds.add(id);
                    skillIds.add(Integer.toString(id));
                }
                pstmt.setString(6, String.join(",", String.join(",", categoryIds)));
                pstmt.setString(7,  String.join(",", String.join(",", skillIds)));
                pstmt.setInt(8,  isPublished.isSelected() ? 1 : 0);
                pstmt.executeUpdate();

                tb.getItems().add(new Job(
                        me.getId(),
                        title.getText(),
                        description.getText(),
                        salaryRange.getValue().toString(),
                        location.getSelectionModel().getSelectedIndex() + 1,
                        isPublished.isSelected() ? 1 : 0,
                        0,
                        intCategoryIds,
                        intSkillIds
                        ));

                ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                error.setText(e.getMessage());
            }
        } else {
            error.setText(errorText);
        }
    }

    public void cancel(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set salary options
        ObservableList salaryRangeList = FXCollections.observableArrayList();
        salaryRangeList.add("0-30k");
        salaryRangeList.add("30k-60k");
        salaryRangeList.add("60k-80k");
        salaryRangeList.add("80k-100k");
        salaryRangeList.add("100k+");
        salaryRange.getItems().addAll(salaryRangeList);
        // Set location options
        ObservableList<String> locationsList = Location.getAllLocations();
        location.getItems().addAll(locationsList);
        // set categories
        ObservableList<String> categoriesList = Category.getAllCategories();
        categories.getItems().addAll(categoriesList);
        // set skills
        ObservableList<String> skillsList = Skill.getAllSkills();
        skills.getItems().addAll(skillsList);
    }

    public void setStage(TableView jobsTable) {
        tb = jobsTable;
    }
}
