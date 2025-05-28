package com.example.virtualpetgame;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class PetSelectionScreen {

    private VBox root;
    private Pet[] starterPets;
    private GameManager gameManager;

    public PetSelectionScreen(GameManager gameManager) {
        this.gameManager = gameManager;
        this.starterPets = new Pet[]{
                new Dog("Puppy"),
                new Cat("Kitten"),
                new Rabbit("Bunny")
        };

        createUI();
    }

    private void createUI(){

        root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #f5f5dc;");

        Label title = new Label("Choose Your First Pet");
        title.setFont(Font.font("Arial", 24));

        HBox petsContainer = new HBox(30);
        petsContainer.setAlignment(Pos.CENTER);

        for (int i = 0; i < starterPets.length; i++) {
            Pet pet = starterPets[i];
            VBox petCard = createPetCard(pet, i);
            petsContainer.getChildren().add(petCard);
        }

        root.getChildren().addAll(title, petsContainer);
    }

    private VBox createPetCard(Pet pet, int index) {
        VBox card = new VBox(10);
        card.setAlignment(Pos.CENTER);
        card.setStyle("-fx-background-color: white; -fx-padding: 15; -fx-border-radius: 10;");

        /* ImageView petImage = new ImageView();
        try {
            Image image = new Image(getClass().getResourceAsStream(
                    "/images/" + pet.getClass().getSimpleName().toLowerCase() + ".png"));
            petImage.setImage(image);
            petImage.setFitWidth(150);
            petImage.setFitHeight(150);
            petImage.setPreserveRatio(true);
        } catch (Exception e) {
            // Fallback if image not found
            petImage = new ImageView();
            petImage.setFitWidth(150);
            petImage.setFitHeight(150);
            petImage.setStyle("-fx-background-color: lightgray;");
        }
*/
        Label nameLabel = new Label(pet.name);
        nameLabel.setFont(Font.font("Arial", 16));

        // Pet price
        Label priceLabel = new Label("$" + pet.price);
        priceLabel.setFont(Font.font("Arial", 14));

        // Select button
        Button selectButton = new Button("Choose");
        selectButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");

        selectButton.setOnAction(e -> {
            if (gameManager.getUser().getMoney() >= pet.price) {
                gameManager.getUser().buyPet(pet);
             //   gameManager.startMainGame();
            } else {
                // Show not enough money message
                System.out.println("Not enough money!");
            }
        });

        card.getChildren().addAll( nameLabel, priceLabel, selectButton);
        return card;
    }

    public VBox getView() {
        return root;
    }


    }



