package com.groupi.groupi2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Category {
    private int id;
    private String name;

    public Category(String name) {
        this.name = name;
    }

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ObservableList<String> getAllCategories() {
        ObservableList allCategories = FXCollections.observableArrayList();
        try {
            Connection conn = new DatabaseHandler().getConn();
            Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM categories");
            while (results.next()) {
                allCategories.add(results.getString("name"));
            }
            return allCategories;
        } catch (SQLException e) {
            return allCategories;
        }
    }

    public static String getCategoryNameById(int categoryId) {
        try {
            String sql = "SELECT * FROM categories WHERE id = ?";
            Connection conn = new DatabaseHandler().getConn();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, categoryId);
            ResultSet results    = preparedStatement.executeQuery();
            String dbName = results.getString("name");
            return dbName;
        } catch (SQLException e) {
            return "Category";
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
