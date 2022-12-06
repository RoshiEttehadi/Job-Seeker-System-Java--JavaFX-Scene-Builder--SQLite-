package com.groupi.groupi2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Location {
    private int id;
    private String name;

    public Location(String name) {
        this.name = name;
    }

    public Location(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ObservableList<String> getAllLocations() {
        ObservableList allLocations = FXCollections.observableArrayList();
        try {
            Connection conn = new DatabaseHandler().getConn();
            Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM locations");
            while (results.next()) {
                allLocations.add(results.getString("name"));
            }
            return allLocations;
        } catch (SQLException e) {
            return allLocations;
        }
    }

    public static String getLocationNameById(int locationId) {
        try {
            String sql = "SELECT * FROM locations WHERE id = ?";
            Connection conn = new DatabaseHandler().getConn();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, locationId);
            ResultSet results    = preparedStatement.executeQuery();
            String dbName = results.getString("name");
            if (dbName == null) return "Location";
            return dbName;
        } catch (SQLException e) {
            return "Location";
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
