package com.example.virtualpetgame;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InitialPetSelectionScreen {

    private final Stage stage;
    private final GameController controller;
    StackPane root = new StackPane();
    private Label moneyAmount;

    public InitialPetSelectionScreen(Stage stage, GameController controller) {
        this.stage = stage;
        this.controller = controller;
    }

    public Scene getScene(){


        User user= controller.getUser();

        Image backgroundImg = new Image(getClass().getResourceAsStream("/images/firstpetbackground.jpg")); // your custom image
        ImageView bgView = new ImageView(backgroundImg);
        bgView.setFitWidth(800);
        bgView.setFitHeight(600);
        bgView.setPreserveRatio(false);

//        Label moneyLabel = new Label("Money: $" + user.getMoney());
//        moneyLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
//        StackPane.setAlignment(moneyLabel, Pos.TOP_LEFT);
//        StackPane.setMargin(moneyLabel, new Insets(10));

//        Image moneyIcon = new Image(getClass().getResourceAsStream("/images/Money.png")); // Add your money icon image
//        ImageView moneyIconView = new ImageView(moneyIcon);
//        moneyIconView.setFitWidth(40);  // Adjust size as needed
//        moneyIconView.setFitHeight(40);
//
//        // Create label for the amount (without "Money: $")
//         moneyAmount = new Label(": "+ user.getMoney());
//        moneyAmount.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: gold;");
//
//        // Create a horizontal box to hold both icon and amount
//        HBox moneyContainer = new HBox(5); // 5 is spacing between elements
//        moneyContainer.setAlignment(Pos.CENTER_LEFT);
//        moneyContainer.getChildren().addAll(moneyIconView, moneyAmount);
//
//        StackPane.setAlignment(moneyContainer, Pos.TOP_LEFT);
//        StackPane.setMargin(moneyContainer, new Insets(0,0,580,0));

        HBox moneyDisplay = user.getMoneyDisplay();
        StackPane.setAlignment(moneyDisplay, Pos.TOP_LEFT);
        StackPane.setMargin(moneyDisplay, new Insets(0,0,580,0));




        Pet[] starterPets = new Pet[]{
                controller.getAvailablePets().get(0),
                controller.getAvailablePets().get(1),
                controller.getAvailablePets().get(2)
        };

        Button leftbtn = createPetButton(starterPets[1], user);
        Button centerBtn = createPetButton(starterPets[0], user);
        Button rightBtn = createPetButton(starterPets[2], user);

        StackPane.setAlignment(leftbtn, Pos.CENTER_LEFT);
        StackPane.setMargin(leftbtn, new Insets(0, 0, 0, 60)); // Adjust based on your image

        StackPane.setAlignment(centerBtn, Pos.CENTER);
        StackPane.setMargin(centerBtn, new Insets(0, 0, 0, 0));

        StackPane.setAlignment(rightBtn, Pos.CENTER_RIGHT);
        StackPane.setMargin(rightBtn, new Insets(0, 60, 0, 0));



        root.getChildren().addAll(bgView,leftbtn, centerBtn, rightBtn, moneyDisplay);
        return new Scene(root, 800, 600);

    }

    private Button createPetButton(Pet pet, User user) {

        Button button = new Button();
        button.setStyle("-fx-background-color: transparent;");
        button.setPrefSize(180, 180); // Match your image's circle size
//

        button.setOnAction(e -> {
            showPetNamingOverlay(root, pet, user);
        });

        // Optional: Visual cue on hover
        button.setOnMouseEntered(e -> button.setStyle("-fx-border-color: gold; -fx-border-width: 2px; -fx-background-color: transparent;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: transparent;"));

        return button;

    }

    private void showPetNamingOverlay(StackPane root, Pet pet, User user) {
        VBox overlay = new VBox(15);
        overlay.setStyle("-fx-background-color: rgba(0,0,0,0.75); -fx-padding: 30; -fx-background-radius: 15;");
        overlay.setAlignment(Pos.CENTER);

        Label title = new Label("Name Your "+ pet.getClass().getSimpleName());
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: gold;");

        TextField nameInput = new TextField();
        nameInput.setPromptText("Enter name...");
        nameInput.setMaxWidth(200);
        nameInput.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-size: 14px;");

        Button confirm = new Button("Confirm");
        confirm.setStyle("-fx-background-color: gold; -fx-font-weight: bold;");

        confirm.setOnAction(e -> {
            String name = nameInput.getText().trim();
            if (!name.isEmpty()) {
                Pet newPet = pet.clonePet();
                newPet.setName(name);
                boolean bought = user.buyPet(newPet);
                if (bought) {
                    root.getChildren().remove(overlay); // optional fade out
                    Toast.show(root, "You chose: " + pet.name + "!");
                    MainGameScreen main = new MainGameScreen(stage, controller);
                   stage.setScene(main.getScene());
                }
            } else {
                nameInput.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            }
        });

        overlay.getChildren().addAll(title, nameInput, confirm);
        StackPane.setAlignment(overlay, Pos.CENTER);
        root.getChildren().add(overlay);
    }



}
