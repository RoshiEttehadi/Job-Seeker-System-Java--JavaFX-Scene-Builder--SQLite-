package com.groupi.groupi2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;

class CurrentUserSingleton {
    private static CurrentUserSingleton instance = null;

    public User me;

    private CurrentUserSingleton() {
        me = null;
    }

    public static CurrentUserSingleton CurrentUserSingleton() {
        if (instance == null) {
            instance = new CurrentUserSingleton();
        }
        return instance;
    }

    public void setMe(User user) {
        this.me = user;
    }
}

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader root = new FXMLLoader(Main.class.getResource("main.fxml"));
        Scene scene = new Scene(root.load(), 1280, 960);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        CurrentUserSingleton.CurrentUserSingleton();
        DatabaseHandler db = new DatabaseHandler();
        db.initialiseDatabase();
        launch();
    }
}