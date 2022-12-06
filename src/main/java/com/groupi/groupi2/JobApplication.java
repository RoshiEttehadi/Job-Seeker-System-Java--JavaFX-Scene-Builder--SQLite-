package com.groupi.groupi2;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;

public class JobApplication {
    private int id;
    private int jobId;
    private int jobSeekerId;
    private String coverLetter;
    private String additionalInfo;

    public JobApplication(int jobId, int jobSeekerId, String coverLetter, String additionalInfo) {
        this.id = id;
        this.jobId = jobId;
        this.jobSeekerId = jobSeekerId;
        this.coverLetter = coverLetter;
        this.additionalInfo = additionalInfo;
    }

    public JobApplication(int id, int jobId, int jobSeekerId, String coverLetter, String additionalInfo) {
        this.id = id;
        this.jobId = jobId;
        this.jobSeekerId = jobSeekerId;
        this.coverLetter = coverLetter;
        this.additionalInfo = additionalInfo;
    }

    public static ObservableList<JobApplication> getAllApplicationsForJob() {
        ObservableList allApplications = FXCollections.observableArrayList();

        try {
            Connection conn = new DatabaseHandler().getConn();
            Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM job_applications");
            System.out.println(results.getString("coverLetter"));
            do {
                int appId = results.getInt("id");
                int jobId = results.getInt("jobId");
                int jobSeekerId = results.getInt("recruiterId");
                String coverLetter = results.getString("coverLetter");
                String additionalInfo = results.getString("additionalInfo");
                System.out.println("cover 50" + coverLetter);

                allApplications.add(new JobApplication(
                        appId,
                        jobId,
                        jobSeekerId,
                        coverLetter,
                        additionalInfo
                ));
            } while (results.next());

            return allApplications;
        } catch (SQLException e) {
            return allApplications;
        }
    }

    public static JobApplication getJobApplicationForSeeker(int jobId, int jobSeekerId) {
        try {
            String sql = "SELECT * FROM job_applications WHERE jobId = ? AND jobSeekerId = ?;";
            Connection conn = new DatabaseHandler().getConn();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, jobId);
            preparedStatement.setInt(2, jobSeekerId);
            ResultSet results    = preparedStatement.executeQuery();
            if (results.next() == false) return null;
            return new JobApplication(
                    results.getInt("id"),
                    results.getInt("jobId"),
                    results.getInt("jobSeekerId"),
                    results.getString("coverLetter"),
                    results.getString("additionalInfo")
            );
        } catch (Exception error) {
            System.out.println("Error getting job application for seeker.");
            return null;
        }
    }

    public static boolean saveJobApplication(JobApplication application) {
        try{
            String sql = "INSERT INTO job_applications(jobId, jobSeekerId, coverLetter, additionalInfo) VALUES(?,?,?,?);";
            Connection conn = new DatabaseHandler().getConn();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, application.getJobId());
            pstmt.setInt(2, application.getJobSeekerId());
            pstmt.setString(3, application.getCoverLetter());
            pstmt.setString(4, application.getAdditionalInfo());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getJobSeekerId() {
        return jobSeekerId;
    }

    public void setJobSeekerId(int jobSeekerId) {
        this.jobSeekerId = jobSeekerId;
    }

    public String getCoverLetter() {
        return coverLetter;
    }

    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public ObservableValue<String> getApplicantEmail() {
        String sql = "SELECT * FROM seekers WHERE id = ?";
        try {
            Connection conn = new DatabaseHandler().getConn();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, this.jobSeekerId);
            ResultSet results = preparedStatement.executeQuery();
            String email = results.getString("email");
            System.out.println("App email: " + email);
            return new SimpleStringProperty(email);
        } catch (Exception e) {
            return null;
        }
    }
}
