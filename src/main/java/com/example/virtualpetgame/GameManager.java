package com.example.virtualpetgame;

import javafx.scene.Scene;

public class GameManager {

    private GameController gameController;
    private User user;
    private Scene currentScene;
    private PetSelectionScreen petSelectionScreen;

    public GameManager(String userName) {
        this.gameController = new GameController(userName);
        this.user = gameController.getUser();
    }


    public void showPetSelectionScreen() {
        this.petSelectionScreen = new PetSelectionScreen(this);
        currentScene.setRoot(petSelectionScreen.getView());
    }


    public Scene getCurrentScene(){
        return currentScene;
    }

    public User getUser() {
        return user;
    }
}
