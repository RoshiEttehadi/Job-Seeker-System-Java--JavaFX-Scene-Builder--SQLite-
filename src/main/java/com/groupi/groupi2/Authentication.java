package com.groupi.groupi2;

import java.sql.*;

public class Authentication {
    /**
     * Search database for a user with a matching email
     * @return Database.User
     * @throws Exception if no user is found or database returns an error.
     *
     */
    public Recruiter findRecruiterByEmail(String email) throws Exception {
        String sql = "SELECT * FROM recruiters WHERE email = ?";

        try {
            Connection conn = new DatabaseHandler().getConn();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, email);
            ResultSet results    = preparedStatement.executeQuery();

            int dbId = results.getInt("id");
            if (dbId == 0) {
                throw new Exception("Could not find user.");
            }

            String dbEmail = results.getString("email");
            String dbFirstName = results.getString("firstName");
            String dbLastName = results.getString("lastName");
            String dbPassword = results.getString("password");
            String dbCompanyName = results.getString("companyName");
            String dbABN = results.getString("abn");
            int dbLocationId = results.getInt("locationId");

            Recruiter recruiter = new Recruiter(dbId, dbFirstName, dbLastName, dbEmail, dbPassword, dbCompanyName, dbABN, dbLocationId);
            return recruiter;
        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        }
    }

    public JobSeeker findSeekerByEmail(String email) throws Exception {
        String sql = "SELECT * FROM seekers WHERE email = ?";

        try {
            Connection conn = new DatabaseHandler().getConn();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, email);
            ResultSet results    = preparedStatement.executeQuery();

            int dbId = results.getInt("id");
            if (dbId == 0) {
                throw new Exception("Could not find user.");
            }

            String dbEmail = results.getString("email");
            String dbFirstName = results.getString("firstName");
            String dbLastName = results.getString("lastName");
            String dbPassword = results.getString("password");
            String dbEducation = results.getString("education");
            int dbLocationId = results.getInt("locationId");
            String dbSkillIds = results.getString("skillIds");

            JobSeeker seeker = new JobSeeker(dbId, dbFirstName, dbLastName, dbEmail, dbPassword, dbEducation, dbLocationId, dbSkillIds);
            return seeker;
        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Register a new user in the system
     * @return boolean true if registration is successful, false if not.
     *
     */
    public boolean register(String firstName, String lastName, String email, String password, String userType) {
        JobSeeker dbSeeker = null;
        Recruiter dbRecruiter = null;
        String sql = "";
        try {
            dbSeeker = findSeekerByEmail(email);
            dbRecruiter = findRecruiterByEmail(email);
        } catch (Exception e) {}

        if (userType.equals("Recruiter")) {
            sql = "INSERT INTO recruiters(firstName, lastName, email, password) VALUES(?,?,?,?)";
        } else {
            sql = "INSERT INTO seekers(firstName, lastName, email, password) VALUES(?,?,?,?)";
        }

        if ((userType.equals("Job Seeker") && (dbSeeker != null && dbSeeker.getEmail().equals(email))) || (userType.equals("Job Seeker") && (dbRecruiter != null && dbRecruiter.getEmail().equals(email)))) {
            System.out.println("Email already registered on an account.");
            return false;
        } else {


            try{
                Connection conn = new DatabaseHandler().getConn();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, firstName);
                pstmt.setString(2, lastName);
                pstmt.setString(3, email);
                pstmt.setString(4, password);
                pstmt.executeUpdate();
                System.out.println("User registered successfully.");
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
    }
}
