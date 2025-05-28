package com.example.virtualpetgame;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class VirtualPetApp extends Application {

    private Stage primaryStage;
    private Image backgroundImage;
    private ImageView backgroundView;
    private String playerName;


    @Override
    public void start(Stage primaryStage) {

        WelcomeScreen welcome = new WelcomeScreen(primaryStage);
        Scene welcomeScene = welcome.getScene();
        primaryStage.setScene(welcomeScene);
        primaryStage.setTitle("Virtual Pet Simulator");
        primaryStage.show();


    }



    public static void main(String[] args) {
        launch(args);
    }
}