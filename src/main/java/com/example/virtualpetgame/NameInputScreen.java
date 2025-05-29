package com.example.virtualpetgame;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class NameInputScreen {

    private final Stage stage;
    private Image backgroundImage;
    private ImageView backgroundView;
    private String playerName;

    public NameInputScreen(Stage stage){

        this.stage=stage;
    }

    public Scene getScene(){

        StackPane gameRoot = new StackPane();
        backgroundImage = new Image(getClass().getResourceAsStream("/images/startingscreenbg.jpg"));
        backgroundView = new ImageView(backgroundImage);
        backgroundView.setPreserveRatio(false);
        backgroundView.fitWidthProperty().bind(stage.widthProperty());
        backgroundView.fitHeightProperty().bind(stage.heightProperty());;

        Image signImage = new Image(getClass().getResourceAsStream("/images/woodenboard3.png"));
        ImageView signView = new ImageView(signImage);
        signView.setPreserveRatio(true);
        signView.setFitWidth(250);
        StackPane.setAlignment(signView, Pos.TOP_CENTER);
        StackPane.setMargin(signView, new Insets(0, 0, 0, 0));



        Image enterName = new Image(getClass().getResourceAsStream("/images/ENTERNAME.png"));
        ImageView enterNameView = new ImageView(enterName);
        enterNameView.setPreserveRatio(true);
        enterNameView.setFitWidth(200);
        StackPane.setAlignment(enterNameView, Pos.CENTER);
        StackPane.setMargin(enterNameView, new Insets(130, 0, 0, 0));

        TextField nameField = new TextField();
        nameField.setStyle(
                "-fx-background-color: transparent; " +
                        "-fx-border-color: transparent; " +
                        "-fx-text-fill: #FEC837; " +
                        "-fx-font-weight: bold; " +
                        "-fx-font-size: 19px; " +
                        "-fx-padding: 0 10 0 10;" // Adjust padding to center in your image
        );
        nameField.setPromptText("Type name...");
        nameField.setMaxWidth(150); // Match width of your ENTERNAME image area
        nameField.setAlignment(Pos.CENTER);

        StackPane.setMargin(nameField, new Insets(160,0,0,0));

        Image confirmButton = new Image(getClass().getResourceAsStream("/images/confirm button.png"));
        ImageView confirmButtonView = new ImageView(confirmButton);
        confirmButtonView.setPreserveRatio(true);
        confirmButtonView.setFitWidth(100);
        StackPane.setAlignment(confirmButtonView, Pos.BOTTOM_CENTER);
        StackPane.setMargin(confirmButtonView, new Insets(0,0,50,0));

        confirmButtonView.setOnMouseEntered(e -> confirmButtonView.setOpacity(0.8));
        confirmButtonView.setOnMouseExited(e -> confirmButtonView.setOpacity(1.0));
        confirmButtonView.setOnMouseClicked(e -> {
            if (!nameField.getText().trim().isEmpty()) {
                playerName = nameField.getText().trim();

            }
            GameController controller = new GameController(playerName);
            InitialPetSelectionScreen initialPetScreen = new InitialPetSelectionScreen(stage, controller);
            stage.setScene(initialPetScreen.getScene());
        });



        gameRoot.getChildren().addAll(backgroundView,signView, enterNameView,nameField,confirmButtonView);
        return new Scene(gameRoot, 800, 600);

    }

}
