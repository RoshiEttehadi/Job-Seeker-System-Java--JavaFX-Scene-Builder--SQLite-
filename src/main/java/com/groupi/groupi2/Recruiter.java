package com.groupi.groupi2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Recruiter extends User {
    private String companyName;
    private String abn;
    public Recruiter(int id, String firstName, String lastName, String email, String password) {
        super(id, firstName, lastName, email, password);
    }

    public Recruiter(int id, String firstName, String lastName, String email, String password, String companyName, String abn, int locationId) {
        super(id, firstName, lastName, email, password, locationId);
        this.companyName = companyName;
        this.abn = abn;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAbn() {
        return abn;
    }

    public void setAbn(String abn) {
        this.abn = abn;
    }
    public boolean updateProfile(int id, String firstName, String lastName, String companyName, String abn, int locationId) {
        String sql = "UPDATE recruiters SET firstName = ?, lastName = ?, companyName = ?, abn = ?, locationId = ? WHERE id = ?";

        try{
            Connection conn = new DatabaseHandler().getConn();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, companyName);
            pstmt.setString(4, abn);
            pstmt.setInt(5, locationId);
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
        this.setCompanyName(companyName);
        this.setAbn(abn);
        this.setLocationId(locationId);
        CurrentUserSingleton me = CurrentUserSingleton.CurrentUserSingleton();
        me.setMe(this);

        return true;
    }

}
