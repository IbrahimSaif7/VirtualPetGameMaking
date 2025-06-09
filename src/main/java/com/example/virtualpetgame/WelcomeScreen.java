package com.example.virtualpetgame;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class WelcomeScreen {

    private final Stage primaryStage;
    private Image backgroundImage;
    private ImageView backgroundView;

    public WelcomeScreen(Stage stage) {
        this.primaryStage = stage;
    }

    public Scene getScene() {

        StackPane root = new StackPane();
        backgroundImage = new Image(getClass().getResourceAsStream("/images/startingscreenbg.jpg"));
        backgroundView = new ImageView(backgroundImage);
        backgroundView.setPreserveRatio(false);
        backgroundView.fitWidthProperty().bind(primaryStage.widthProperty());
        backgroundView.fitHeightProperty().bind(primaryStage.heightProperty());


        // Wooden Sign
        Image signImage = new Image(getClass().getResourceAsStream("/images/woodenboard3.png"));
        ImageView signView = new ImageView(signImage);
        signView.setPreserveRatio(true);
        signView.setFitWidth(250);
        StackPane.setAlignment(signView, Pos.TOP_CENTER);
        StackPane.setMargin(signView, new Insets(0, 0, 0, 0)); // Better positioning

        // Play Button
        Image playButtonImage = new Image(getClass().getResourceAsStream("/images/Play.png"));
        ImageView playButton = new ImageView(playButtonImage);
        playButton.setPreserveRatio(true);
        playButton.setFitWidth(180);
        StackPane.setAlignment(playButton, Pos.CENTER);
        StackPane.setMargin(playButton, new Insets(40,0,0,0));

        // Set button action to switch screens
        playButton.setOnMouseClicked(e ->{
            NameInputScreen nameInput = new NameInputScreen(primaryStage);
            primaryStage.setScene(nameInput.getScene());
        });

        playButton.setOnMouseEntered(e -> playButton.setOpacity(0.8));
        playButton.setOnMouseExited(e -> playButton.setOpacity(1.0));

        Image loadButtonImage = new Image(getClass().getResourceAsStream("/images/LoadGame.png"));
        ImageView loadButton = new ImageView(loadButtonImage);
        loadButton.setPreserveRatio(true);
        loadButton.setFitWidth(100);
        loadButton.setFitHeight(80);
        StackPane.setAlignment(loadButton, Pos.TOP_RIGHT);
        StackPane.setMargin(loadButton, new Insets(0, 0, 0, 0)); // Position below play button

        loadButton.setOnMouseEntered(e -> loadButton.setOpacity(0.8));
        loadButton.setOnMouseExited(e -> loadButton.setOpacity(1.0));
        loadButton.setOnMouseClicked(e -> {
            User loadedUser = GameSave.loadGame();
            if (loadedUser != null) {
                GameController controller = new GameController(loadedUser.getUsername());
                controller.getUser().pets = loadedUser.pets;
                controller.getUser().money= loadedUser.getMoney();
                controller.getUser().foodInventory = loadedUser.foodInventory;
                controller.getUser().toyInventory = loadedUser.toyInventory;

                MainGameScreen main = new MainGameScreen(primaryStage, controller);
                primaryStage.setScene(main.getScene());
            } else {
                Toast.show(root, "No saved game found!");
            }
        });

        root.getChildren().addAll(backgroundView, signView,playButton, loadButton);

        return new Scene(root, 800, 600);

    }

}
