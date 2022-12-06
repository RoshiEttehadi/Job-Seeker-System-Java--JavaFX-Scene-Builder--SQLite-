package com.groupi.groupi2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Skill {
    private int id;
    private String name;

    public Skill(String name) {
        this.name = name;
    }

    public Skill(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ObservableList<String> getAllSkills() {
        ObservableList allSkills = FXCollections.observableArrayList();
        try {
            Connection conn = new DatabaseHandler().getConn();
            Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM skills");
            while (results.next()) {
                allSkills.add(results.getString("name"));
            }
            return allSkills;
        } catch (SQLException e) {
            return allSkills;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
