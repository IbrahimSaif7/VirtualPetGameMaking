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


       /* this.primaryStage = primaryStage;


        backgroundImage = new Image(getClass().getResourceAsStream("/images/background3.png"));
        backgroundView = new ImageView(backgroundImage);
        backgroundView.setPreserveRatio(false);
        backgroundView.fitWidthProperty().bind(primaryStage.widthProperty());
        backgroundView.fitHeightProperty().bind(primaryStage.heightProperty());

        showWelcomeScreen();

        */
    }

//    private void showWelcomeScreen() {
//        StackPane root = new StackPane();
//        root.getChildren().add(backgroundView);
//
//        // Wooden Sign
//        Image signImage = new Image(getClass().getResourceAsStream("/images/woodenboard3.png"));
//        ImageView signView = new ImageView(signImage);
//        signView.setPreserveRatio(true);
//        signView.setFitWidth(250);
//        StackPane.setAlignment(signView, Pos.TOP_CENTER);
//        StackPane.setMargin(signView, new Insets(0, 0, 0, 0)); // Better positioning
//
//        // Play Button
//        Image playButtonImage = new Image(getClass().getResourceAsStream("/images/Play.png"));
//        ImageView playButton = new ImageView(playButtonImage);
//        playButton.setPreserveRatio(true);
//        playButton.setFitWidth(180);
//        StackPane.setAlignment(playButton, Pos.CENTER);
//        StackPane.setMargin(playButton, new Insets(150,0,0,0));
//
//        // Set button action to switch screens
//        playButton.setOnMouseClicked(e -> showNameInputScreen());
//        playButton.setOnMouseEntered(e -> playButton.setOpacity(0.8));
//        playButton.setOnMouseExited(e -> playButton.setOpacity(1.0));
//
//        root.getChildren().addAll(signView, playButton);
//
//        Scene scene = new Scene(root, 800, 600);
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("Virtual Pet Simulator");
//        primaryStage.show();
//    }
//
//    private void showNameInputScreen() {
//        StackPane gameRoot = new StackPane();
//        gameRoot.getChildren().add(backgroundView);
//
//        Image signImage = new Image(getClass().getResourceAsStream("/images/woodenboard3.png"));
//        ImageView signView = new ImageView(signImage);
//        signView.setPreserveRatio(true);
//        signView.setFitWidth(250);
//        StackPane.setAlignment(signView, Pos.TOP_CENTER);
//        StackPane.setMargin(signView, new Insets(0, 0, 0, 0));
//
//
//
//        Image enterName = new Image(getClass().getResourceAsStream("/images/ENTERNAME.png"));
//        ImageView enterNameView = new ImageView(enterName);
//        enterNameView.setPreserveRatio(true);
//        enterNameView.setFitWidth(200);
//        StackPane.setAlignment(enterNameView, Pos.CENTER);
//        StackPane.setMargin(enterNameView, new Insets(130, 0, 0, 0));
//
//        TextField nameField = new TextField();
//        nameField.setStyle(
//                "-fx-background-color: transparent; " +
//                        "-fx-border-color: transparent; " +
//                        "-fx-text-fill: #FEC837; " +
//                        "-fx-font-weight: bold; " +
//                        "-fx-font-size: 19px; " +
//                        "-fx-padding: 0 10 0 10;" // Adjust padding to center in your image
//        );
//        nameField.setPromptText("Type name...");
//        nameField.setMaxWidth(150); // Match width of your ENTERNAME image area
//        nameField.setAlignment(Pos.CENTER);
//
//        StackPane.setMargin(nameField, new Insets(160,0,0,0));
//
//        Image confirmButton = new Image(getClass().getResourceAsStream("/images/confirm button.png"));
//        ImageView confirmButtonView = new ImageView(confirmButton);
//        confirmButtonView.setPreserveRatio(true);
//        confirmButtonView.setFitWidth(100);
//        StackPane.setAlignment(confirmButtonView, Pos.BOTTOM_CENTER);
//        StackPane.setMargin(confirmButtonView, new Insets(0,0,50,0));
//
//        // Set button action to switch screens
//      //  confirmButtonView.setOnMouseClicked(e -> showMainGameScreen());
//        confirmButtonView.setOnMouseEntered(e -> confirmButtonView.setOpacity(0.8));
//        confirmButtonView.setOnMouseExited(e -> confirmButtonView.setOpacity(1.0));
//        confirmButtonView.setOnMouseClicked(e -> {
//                    if (!nameField.getText().trim().isEmpty()) {
//                        playerName = nameField.getText().trim();
//
//                    }
//                    initialPetScreen();
//                });
//
//
//
//        gameRoot.getChildren().addAll(signView, enterNameView,nameField,confirmButtonView);
//        Scene gameScene = new Scene(gameRoot, 800, 600);
//        primaryStage.setScene(gameScene);
//    }
//    private void initialPetScreen(){
//
//        StackPane root = new StackPane();
//
//        Image backgroundImage = new Image(getClass().getResourceAsStream("/images/background3.png"));
//        backgroundView = new ImageView(backgroundImage);
//        backgroundView.setPreserveRatio(false);
//        backgroundView.fitWidthProperty().bind(primaryStage.widthProperty());
//        backgroundView.fitHeightProperty().bind(primaryStage.heightProperty());
//
//
//        root.getChildren().add(backgroundView);
//        Scene scene= new Scene(root, 800, 600);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//
//    }

    public static void main(String[] args) {
        launch(args);
    }
}