package com.groupi.groupi2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JobSeeker extends User {
    private String education;
    private String skillIds;
    public JobSeeker(int id, String firstName, String lastName, String email, String password) {
        super(id, firstName, lastName, email, password);
    }

    public JobSeeker(int id, String firstName, String lastName, String email, String password, String education, int locationId, String skillIds) {
        super(id, firstName, lastName, email, password, locationId);
        this.education = education;
        this.skillIds = skillIds;
    }

    public JobSeeker getJobSeekerById(int id) throws Exception {
        try {
            String sql = "SELECT * FROM seekers WHERE id = ?";
            Connection conn = new DatabaseHandler().getConn();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet results    = preparedStatement.executeQuery();
            return new JobSeeker(
                    results.getInt("id"),
                    results.getString("firstName"),
                    results.getString("lastName"),
                    results.getString("email"),
                    "",
                    results.getString("education"),
                    results.getInt("locationId"),
                    results.getString("skillIds")
            );
        } catch (SQLException e) {
            throw new Exception("Could not find seeker in database.");
        }
    }

    public boolean updateProfile(int id, String firstName, String lastName, String education, int locationId, String skillIds) {
        String sql = "UPDATE seekers SET firstName = ?, lastName = ?, education = ?, locationId = ?, skillIds = ? WHERE id = ?";

        try{
            Connection conn = new DatabaseHandler().getConn();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, education);
            pstmt.setInt(4, locationId);
            pstmt.setString(5, skillIds);
            pstmt.setInt(6, id);
            pstmt.executeUpdate();
            System.out.println("User updated successfully.");
        } catch (SQLException e) {
            // Let the caller handle the error to display to the user.
            return false;
        }

        // DB update successful, update user singleton
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEducation(education);
        this.setLocationId(locationId);
        this.setSkillIds(skillIds);
        CurrentUserSingleton me = CurrentUserSingleton.CurrentUserSingleton();
        me.setMe(this);

        return true;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getSkillIds() {
        return skillIds;
    }

    public void setSkillIds(String skillIds) {
        this.skillIds = skillIds;
    }
}
