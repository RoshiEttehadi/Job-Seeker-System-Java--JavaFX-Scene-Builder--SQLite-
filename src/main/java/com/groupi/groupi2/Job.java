package com.groupi.groupi2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;

public class Job {
    private int id;
    private int recruiterId;
    private String title;
    private String description;
    private String salaryRange;
    private int locationId;
    private ArrayList<Integer> categoryIds;
    private ArrayList<Integer> skillIds;
    private int isPublished;
    private int isDisabled;

    public Job(int recruiterId, String title, String description, String salaryRange, int locationId, int isPublished, int isDisabled, ArrayList<Integer> categoryIds, ArrayList<Integer> skillIds) {
        this.recruiterId = recruiterId;
        this.title = title;
        this.description = description;
        this.salaryRange = salaryRange;
        this.locationId = locationId;
        this.categoryIds = categoryIds;
        this.skillIds = skillIds;
        this.isPublished = isPublished;
        this.isDisabled = isDisabled;
    }
    public Job(int id, int recruiterId, String title, String description, String salaryRange, int locationId, int isPublished, int isDisabled, ArrayList<Integer> categoryIds, ArrayList<Integer> skillIds) {
        this.id = id;
        this.recruiterId = recruiterId;
        this.title = title;
        this.description = description;
        this.salaryRange = salaryRange;
        this.locationId = locationId;
        this.categoryIds = categoryIds;
        this.skillIds = skillIds;
        this.isPublished = isPublished;
        this.isDisabled = isDisabled;
    }

    public static ObservableList<Job> getAllJobs() {
        ObservableList allJobs = FXCollections.observableArrayList();
        try {
            Connection conn = new DatabaseHandler().getConn();
            Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM jobs");
            while (results.next()) {
                int jobId = results.getInt("id");
                int recruiterId = results.getInt("recruiterId");
                String title = results.getString("title");
                String description = results.getString("description");
                String salaryRange = results.getString("salaryRange");
                int locationId = results.getInt("locationId");
                int isPublished = results.getInt("isPublished");
                int isDisabled = results.getInt("isDisabled");
                String categoryIds = results.getString("categoryIds");
                ArrayList<Integer> categoryIdList = new ArrayList<>();
                for (String id : categoryIds.split(",")) {
                    categoryIdList.add(Integer.parseInt(id));
                }
                String skillIds = results.getString("skillIds");
                ArrayList<Integer> skillIdList = new ArrayList<>();
                for (String id : skillIds.split(",")) {
                    skillIdList.add(Integer.parseInt(id));
                }

                allJobs.add(new Job(
                        jobId,
                        recruiterId,
                        title,
                        description,
                        salaryRange,
                        locationId,
                        isPublished,
                        isDisabled,
                        categoryIdList,
                        skillIdList
                ));
            }
            return allJobs;
        } catch (SQLException e) {
            return allJobs;
        }
    }

    public Job getJobById(int id) throws Exception {
        try {
            String sql = "SELECT * FROM jobs WHERE id = ?";
            Connection conn = new DatabaseHandler().getConn();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet results    = preparedStatement.executeQuery();

            String categoryIds = results.getString("categoryIds");
            ArrayList<Integer> categoryIdList = new ArrayList<>();
            for (String catId : categoryIds.split(",")) {
                categoryIdList.add(Integer.parseInt(catId));
            }
            String skillIds = results.getString("skillIds");
            ArrayList<Integer> skillIdList = new ArrayList<>();
            for (String skId : skillIds.split(",")) {
                skillIdList.add(Integer.parseInt(skId));
            }
            return new Job(
                    results.getInt("recruiterId"),
                    results.getString("title"),
                    results.getString("description"),
                    results.getString("salaryRange"),
                    results.getInt("locationId"),
                    results.getInt("isPublished"),
                    results.getInt("isDisabled"),
                    categoryIdList,
                    skillIdList
            );
        } catch (SQLException e) {
            throw new Exception("Could not find job in database.");
        }
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Integer> getSkillIds() {
        return skillIds;
    }

    public void setSkillIds(ArrayList<Integer> skillIds) {
        this.skillIds = skillIds;
    }

    public ArrayList<Integer> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(ArrayList<Integer> categoryIds) {
        this.categoryIds = categoryIds;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSalaryRange() {
        return salaryRange;
    }

    public void setSalaryRange(String salaryRange) {
        this.salaryRange = salaryRange;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }
    public int getIsDisabled() {
        return isDisabled;
    }

    public void setIsDisabled(int isDisabled) {
        this.isDisabled = isDisabled;
    }

    public int getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(int isPublished) {
        this.isPublished = isPublished;
    }

    public int getRecruiterId() {
        return recruiterId;
    }

    public void setRecruiterId(int recruiterId) {
        this.recruiterId = recruiterId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}


