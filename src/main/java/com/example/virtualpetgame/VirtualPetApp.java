package com.example.virtualpetgame;

import javafx.application.Application;

import javafx.scene.Scene;

import javafx.stage.Stage;

public class VirtualPetApp extends Application {


    @Override
    public void start(Stage primaryStage) {

        WelcomeScreen welcome = new WelcomeScreen(primaryStage);
        Scene welcomeScene = welcome.getScene();
        primaryStage.setScene(welcomeScene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Virtual Pet Simulator");
        primaryStage.show();


    }



    public static void main(String[] args) {
        launch(args);
    }
}