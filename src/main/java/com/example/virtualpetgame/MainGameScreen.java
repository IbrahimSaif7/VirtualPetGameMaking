package com.example.virtualpetgame;

import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;

public class MainGameScreen {

     final Stage stage;
    StackPane root = new StackPane();
    private final GameController controller;
    private VBox leftSlot = new VBox(10);
    private VBox centerSlot = new VBox(10);
    private VBox rightSlot = new VBox(10);

    private StackPane leftCarpet = new StackPane();
    private StackPane centerCarpet = new StackPane();
    private StackPane rightCarpet = new StackPane();





    public MainGameScreen(Stage stage, GameController controller) {
        this.stage = stage;
        this.controller = controller;
    }


    public Scene getScene(){
        User user = controller.getUser();


        ImageView bg = new ImageView(new Image(getClass().getResourceAsStream("/images/mainScreenBG.jpg")));
        bg.setFitWidth(800);
        bg.setFitHeight(600);


        //PAISA
        HBox moneyDisplay = user.getMoneyDisplay();
        StackPane.setAlignment(moneyDisplay, Pos.TOP_LEFT);
        StackPane.setMargin(moneyDisplay, new Insets(0,0,580,0));

        // store
        ImageView storeIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/store.png")));
        storeIcon.setFitWidth(40);
        storeIcon.setFitHeight(40);

        storeIcon.setOnMouseEntered(e -> {

            storeIcon.setFitHeight(50);
            storeIcon.setFitWidth(50);
            Toast.show(root, "SHOP");
        });
        storeIcon.setOnMouseExited(e -> {

            storeIcon.setFitWidth(40);
            storeIcon.setFitHeight(40);

        });

        storeIcon.setOnMouseClicked(e -> {
            System.out.println("Store clicked!");
            showShopOverlay(root);
            // TODO: Open store
        });
        storeIcon.setStyle("-fx-cursor: hand;");
        StackPane.setAlignment(storeIcon, Pos.TOP_RIGHT);
        StackPane.setMargin(storeIcon, new Insets(10));

        ImageView miniGameIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/MinigameIcon.png")));
        miniGameIcon.setFitWidth(50);
        miniGameIcon.setFitHeight(50);
        miniGameIcon.setOnMouseEntered(e -> {

            miniGameIcon.setFitHeight(60);
            miniGameIcon.setFitWidth(60);
        });
        miniGameIcon.setOnMouseExited(e -> {

            miniGameIcon.setFitWidth(50);
            miniGameIcon.setFitHeight(50);

        });
        miniGameIcon.setOnMouseClicked(e -> {
            System.out.println("Mini game clicked!");
           showMiniGameOverlay(root);

        });
        miniGameIcon.setStyle("-fx-cursor: hand;");
        StackPane.setAlignment(miniGameIcon, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(miniGameIcon, new Insets(10));




        leftCarpet.setLayoutX(165);   // X position of left carpet
        leftCarpet.setLayoutY(295);   // Y position of left carpet

        centerCarpet.setLayoutX(350); // X position of center carpet
        centerCarpet.setLayoutY(330); // Y position of center carpet

        rightCarpet.setLayoutX(540);  // X position of right carpet
        rightCarpet.setLayoutY(295);

        List<Pet> pets = controller.getUser().pets;
        for (int i = 0; i < Math.min(pets.size(), 3); i++) {
            Pet pet = pets.get(i);
            ImageView petImg = new ImageView(new Image(
                    getClass().getResourceAsStream("/images/" + pet.getClass().getSimpleName().toLowerCase() + ".png")));
            petImg.setFitWidth(180);
            petImg.setPreserveRatio(true);
            petImg.setStyle("-fx-cursor: hand;");
            petImg.setOnMouseClicked(e -> showPetPopup(root, pet, petImg));

            switch (i) {
                case 0 -> centerCarpet.getChildren().add(petImg);
                case 1 -> rightCarpet.getChildren().add(petImg);
                case 2 -> leftCarpet.getChildren().add(petImg);
            }
        }

        Pane carpetContainer = new Pane();
        carpetContainer.getChildren().addAll(leftCarpet, centerCarpet, rightCarpet);


        root.getChildren().addAll(bg,  carpetContainer, moneyDisplay, storeIcon, miniGameIcon);
        return new Scene(root, 800, 600);
    }



    private void showPetPopup(StackPane root, Pet pet, ImageView petView) {
        VBox panel = new VBox(10);
        panel.setAlignment(Pos.CENTER);
        panel.setStyle("-fx-background-color: linear-gradient(to bottom, #3a3a3a, #1a1a1a); " +
                "-fx-background-radius: 15; " +
                "-fx-padding: 20; " +
                "-fx-border-color: orange; " +
                "-fx-border-width: 2; " +
                "-fx-border-radius: 13; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.7), 10, 0.5, 0, 2);");
        panel.setMaxWidth(230);
        panel.setMaxHeight(300);

        Label name = new Label(pet.getName());
        name.setStyle("-fx-font-size: 18px; " +
                "-fx-font-weight: bold; " +
                "-fx-text-fill: orange; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 2, 0.5, 0, 1);");



        ProgressBar hunger = new ProgressBar( pet.hunger / 100.0);
        ProgressBar mood = new ProgressBar(pet.mood / 100.0);
        ProgressBar energy = new ProgressBar(pet.energy / 100.0);

        hunger.setPrefWidth(200);
        mood.setPrefWidth(200);
        energy.setPrefWidth(200);

        String progressBarStyle = "-fx-accent: orange; " + // Gold color
                "-fx-control-inner-background: #333333; " +
                "-fx-border-color: #555555; " +
                "-fx-border-radius: 5; " +
                "-fx-padding: 2;";

        hunger.setStyle(progressBarStyle);
        mood.setStyle(progressBarStyle);
        energy.setStyle(progressBarStyle);

        Label hLabel = new Label("Hunger");
        Label mLabel = new Label("Mood");
        Label eLabel = new Label("Energy");


        String statLabelStyle = "-fx-text-fill: #FFD700; " + // Gold text
                "-fx-font-size: 14px; " +
                "-fx-font-weight: bold;";

        hLabel.setStyle(statLabelStyle);
        mLabel.setStyle(statLabelStyle);
        eLabel.setStyle(statLabelStyle);

        Button feed = new Button("Feed");
        Button play = new Button("Play");
        Button nap = new Button("Nap");
        Button sell = new Button("Sell");

        String buttonStyle = "-fx-background-color: linear-gradient(to bottom, #4a4a4a, #2a2a2a); " +
                "-fx-text-fill: white; " +
                "-fx-font-weight: bold; " +
                "-fx-border-color: orange; " +
                "-fx-border-width: 1; " +
                "-fx-border-radius: 5; " +
                "-fx-background-radius: 5; " +
                "-fx-padding: 5 10;";

        String hoverStyle = "-fx-background-color: linear-gradient(to bottom, #5a5a5a, #3a3a3a); " +
                "-fx-effect: dropshadow(gaussian, gold, 5, 0.5, 0, 0);";

        for (Button btn : new Button[]{feed, play,sell,nap}) {
            btn.setStyle(buttonStyle);
            btn.setOnMouseEntered(e -> btn.setStyle(buttonStyle + hoverStyle));
            btn.setOnMouseExited(e -> btn.setStyle(buttonStyle));
        }

        HBox actions = new HBox(10, feed, play,nap,sell);
        actions.setAlignment(Pos.CENTER);

        Button close = new Button("X");
        VBox.setMargin(close, new Insets(0,0,0,210));
        close.setStyle("-fx-background-color: transparent; " +
                "-fx-text-fill: orange; " +
                "-fx-font-size: 16px; " +
                "-fx-font-weight: bold;");
        close.setOnMouseEntered(e -> close.setStyle("-fx-background-color: rgba(255,215,0,0.2); " +
                "-fx-text-fill: orange; " +
                "-fx-font-size: 16px; " +
                "-fx-font-weight: bold;"));
        close.setOnMouseExited(e -> close.setStyle("-fx-background-color: transparent; " +
                "-fx-text-fill: orange; " +
                "-fx-font-size: 16px; " +
                "-fx-font-weight: bold;"));

        close.setOnAction(e -> root.getChildren().remove(panel));


        VBox stats = new VBox(5, hLabel, hunger, mLabel, mood, eLabel, energy);
        stats.setAlignment(Pos.CENTER);

        panel.getChildren().addAll(close,name, stats, actions);

        Bounds petBounds = petView.localToScene(petView.getBoundsInLocal());
        double popupX = petBounds.getMinX() + petBounds.getWidth() / 2;
        double popupY = petBounds.getMinY() - 20;

        panel.setTranslateX(popupX - 400);
        panel.setTranslateY(popupY - 400);

        root.getChildren().add(panel);

        feed.setOnAction(e -> {
            showFoodInventory(pet, panel);

        });
        play.setOnAction(e -> {
            showToyInventory(pet, panel);
        });

        nap.setOnAction(e -> {
            if(pet.energy<92) {
                pet.sleep(controller.getUser(), root);
                root.getChildren().remove(panel);

                if (pet.isDead()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Oh no!");
                    alert.setHeaderText(null);
                    alert.setContentText(pet.getName() + " has died 💀");

                    DialogPane dialog = alert.getDialogPane();
                    dialog.setStyle("-fx-background-color: #2a2a2a;");
                    dialog.lookup(".content.label").setStyle("-fx-text-fill: white;");

                    alert.showAndWait();

                    controller.getUser().pets.remove(pet);               // Remove pet
                    controller.getUser().updateMoneyLabel();             // Refresh money
                }
                refreshPetDisplay(root); // Re-align pets



            }else{
                pet.mood-=11;
                Toast.show(root,pet.name+ " is not tired");
                refreshPetDisplay(root);
            }
        });


        sell.setOnAction(e -> {

            controller.getUser().sellPet(pet, root); // 💥 call sell logic

            root.getChildren().remove(panel);// 🧼 remove popup
            controller.getUser().updateMoneyLabel();
            refreshPetDisplay(root);

        });
    }




    private void showShopOverlay(StackPane root){

        GameShop gameShop= new GameShop(root,controller,this);
        Region dim = new Region();
        dim.setStyle("-fx-background-color: rgba(0, 0, 0, 0.6);");
        dim.setPrefSize(800, 600);

        ImageView shopMenu = new ImageView(new Image(getClass().getResourceAsStream("/images/shop bg.jpg")));
        shopMenu.setFitWidth(700);
        shopMenu.setFitHeight(450);
        shopMenu.setPreserveRatio(false);

        Button petBtn = new Button();
        Button foodBtn = new Button();
        Button toyBtn = new Button();

        for (Button btn : new Button[]{petBtn, foodBtn, toyBtn}) {
            btn.setStyle("-fx-background-color: transparent;"+ "-fx-cursor: hand;");
            btn.setPrefSize(135, 135);
        }

        StackPane.setAlignment(foodBtn, Pos.CENTER_LEFT);
        StackPane.setMargin(foodBtn, new Insets(30, 0, 0, 115));

        StackPane.setAlignment(petBtn, Pos.CENTER);
        StackPane.setMargin(petBtn, new Insets(30, 0, 0, 0));

        StackPane.setAlignment(toyBtn, Pos.CENTER_RIGHT);
        StackPane.setMargin(toyBtn, new Insets(40, 115, 0, 0));

        Button close = new Button("X");
        close.setStyle("-fx-font-weight: bold;");
        StackPane.setAlignment(close, Pos.TOP_RIGHT);
        StackPane.setMargin(close, new Insets(70,60,0,0));


        StackPane overlay = new StackPane(dim, shopMenu, petBtn, foodBtn, toyBtn, close);

        close.setOnAction(e -> root.getChildren().remove(overlay));

        petBtn.setOnAction(e -> {
            root.getChildren().remove(overlay);
            gameShop.showPetShop();


        });

        foodBtn.setOnAction(e -> {
            root.getChildren().remove(overlay);
            gameShop.showFoodShop();
        });

        toyBtn.setOnAction(e -> {
            root.getChildren().remove(overlay);
            gameShop.showToyShop();
        });






        root.getChildren().addAll(overlay);

    }

    private void showMiniGameOverlay(StackPane root){

        MiniGameOverlay miniGameOverlay=new MiniGameOverlay(root,controller,this);
        Region dim = new Region();
        dim.setStyle("-fx-background-color: rgba(0, 0, 0, 0.6);");
        dim.setPrefSize(800, 600);

        ImageView minigames = new ImageView(new Image(getClass().getResourceAsStream("/images/MiniGameScreen.jpg")));
        minigames.setFitWidth(700);
        minigames.setFitHeight(450);
        minigames.setPreserveRatio(false);

        Button memory = new Button();
        Button trivia = new Button();
        Button math = new Button();

        for (Button btn : new Button[]{memory, trivia, math}) {
            btn.setStyle("-fx-background-color: transparent;"+ "-fx-cursor: hand;");
            btn.setPrefSize(135, 135);
        }

        StackPane.setAlignment(memory, Pos.CENTER_LEFT);
        StackPane.setMargin(memory, new Insets(30, 0, 0, 115));

        StackPane.setAlignment(trivia, Pos.CENTER);
        StackPane.setMargin(trivia, new Insets(30, 0, 0, 0));

        StackPane.setAlignment(math, Pos.CENTER_RIGHT);
        StackPane.setMargin(math, new Insets(40, 115, 0, 0));

        Button close = new Button("X");
        close.setStyle("-fx-font-weight: bold;");
        StackPane.setAlignment(close, Pos.TOP_RIGHT);
        StackPane.setMargin(close, new Insets(70,60,0,0));


        StackPane overlay = new StackPane(dim, minigames, memory, trivia, math, close);

        close.setOnAction(e -> root.getChildren().remove(overlay));

        memory.setOnAction(e -> {
            root.getChildren().remove(overlay);
            miniGameOverlay.MemoryGame();

        });

        trivia.setOnAction(e -> {
            root.getChildren().remove(overlay);
            miniGameOverlay.showTriviaGame();
        });

        math.setOnAction(e -> {
            root.getChildren().remove(overlay);
            miniGameOverlay.showArithmeticGame();
        });






        root.getChildren().addAll(overlay);



    }



    public void refreshPetDisplay(StackPane root) {
        leftCarpet.getChildren().clear();
        centerCarpet.getChildren().clear();
        rightCarpet.getChildren().clear();

        List<Pet> pets = controller.getUser().pets;

        for (int i = 0; i < Math.min(pets.size(), 3); i++) {
            Pet pet = pets.get(i);
            ImageView petImg = new ImageView(new Image(
                    getClass().getResourceAsStream("/images/" + pet.getClass().getSimpleName().toLowerCase() + ".png")));
            petImg.setFitWidth(180);
            petImg.setPreserveRatio(true);
            petImg.setStyle("-fx-cursor: hand;");

            petImg.setOnMouseClicked(e -> showPetPopup(root, pet, petImg));
            switch (i) {
                case 0 -> centerCarpet.getChildren().add(petImg);
                case 1 -> rightCarpet.getChildren().add(petImg);
                case 2 -> leftCarpet.getChildren().add(petImg);
            }
        }
    }

    private void showFoodInventory(Pet pet, VBox petPopupPanel)
    {
        List<Food> foodList = controller.getUser().getFoodInventory();
        if (foodList.isEmpty()) {
            Toast.show(root, "🍽️ No food in inventory!");
            return;
        }

        Region blurLayer = new Region();
        blurLayer.setPrefSize(800, 600); // match scene size
        blurLayer.setStyle("-fx-background-color: rgba(0,0,0,0.4);");
        blurLayer.setEffect(new GaussianBlur(15));

        VBox panel = new VBox(15);
        panel.setAlignment(Pos.CENTER);
        panel.setStyle("-fx-background-color: linear-gradient(to bottom, #222, #111); " +
                "-fx-border-color: gold; -fx-border-width: 3px; " +
                "-fx-background-radius: 15; -fx-border-radius: 15; -fx-padding: 20;");
        panel.setMaxWidth(420);

        Label heading = new Label("🍗 Choose Food for " + pet.getName());
        heading.setStyle("-fx-text-fill: gold; -fx-font-size: 22px; -fx-font-weight: bold;");
        panel.getChildren().add(heading);

        for (Food food : foodList) {
            HBox row = new HBox(10);
            row.setAlignment(Pos.CENTER);
            Label name = new Label(food.name);
            Label hunger = new Label("Hunger ↓ " + food.hungerReduction);
            name.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");
            hunger.setStyle("-fx-text-fill: #FFD700;");

            Button select = new Button("Feed");
            select.setStyle("-fx-background-color: gold; -fx-text-fill: black; -fx-font-weight: bold;");
            select.setOnAction(e -> {
                if(pet.hunger>=10) {
                    pet.feed(food, controller.getUser(), root);
                    controller.getUser().removeFood(food);
                    controller.getUser().updateMoneyLabel();
                    refreshPetDisplay(root);

                    root.getChildren().removeAll(blurLayer, panel);       // Close food list
                    root.getChildren().remove(petPopupPanel);

                    if (pet.isDead()) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Oh no!");
                        alert.setHeaderText(null);
                        alert.setContentText(pet.getName() + " has died 💀");

                        DialogPane dialog = alert.getDialogPane();
                        dialog.setStyle("-fx-background-color: #2a2a2a;");
                        dialog.lookup(".content.label").setStyle("-fx-text-fill: white;");

                        alert.showAndWait();

                        controller.getUser().pets.remove(pet);               // Remove pet
                        controller.getUser().updateMoneyLabel();             // Refresh money
                    }
                    refreshPetDisplay(root);
                }else{
                    Toast.show(root,"Not hungry");
                    pet.mood-=11;
                }

            });

            row.getChildren().addAll(name, hunger, select);
            panel.getChildren().add(row);
        }

        Button close = new Button("❌ Cancel");
        close.setStyle("-fx-background-color: darkred; -fx-text-fill: white;");
        close.setOnAction(e -> root.getChildren().removeAll(blurLayer, panel));


        panel.getChildren().add(close);
        StackPane.setAlignment(panel, Pos.CENTER);
        root.getChildren().addAll(blurLayer,panel);
    }

    private void showToyInventory(Pet pet, VBox petPopupPanel) {
        List<Toy> toyList = controller.getUser().getToyInventory();
        if (toyList.isEmpty()) {
            Toast.show(root, "🍽️ No Toy in inventory!");
            return;
        }


        StackPane blurLayer = new StackPane();
        blurLayer.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");
        blurLayer.setPrefSize(800, 600);

        VBox panel = new VBox(10);
        panel.setAlignment(Pos.CENTER);
        panel.setStyle("-fx-background-color: #222; -fx-background-radius: 15; -fx-padding: 20; -fx-border-color: orange; -fx-border-width: 2;");
        panel.setMaxWidth(250);
        panel.setMaxHeight(350);

        Label title = new Label("🎲 Choose a Toy");
        title.setStyle("-fx-text-fill: orange; -fx-font-size: 16px; -fx-font-weight: bold;");

        VBox list = new VBox(8);
        list.setAlignment(Pos.CENTER);

        for (Toy toy : controller.getUser().getToyInventory()) {
            Button select = new Button(toy.name + " (Mood +" + toy.moodIncrease + ")");
            select.setStyle("-fx-background-color: #444; -fx-text-fill: white; -fx-background-radius: 8;");

            select.setOnAction(e -> {

                    pet.play(toy, controller.getUser(), root);
                    controller.getUser().updateMoneyLabel();
                    refreshPetDisplay(root);

                    root.getChildren().removeAll(blurLayer, panel);   // Close toy list
                    root.getChildren().remove(petPopupPanel);

                    if (pet.isDead()) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Oh no!");
                        alert.setHeaderText(null);
                        alert.setContentText(pet.getName() + " has died 💀");

                        DialogPane dialog = alert.getDialogPane();
                        dialog.setStyle("-fx-background-color: #2a2a2a;");
                        dialog.lookup(".content.label").setStyle("-fx-text-fill: white;");

                        alert.showAndWait();

                        controller.getUser().pets.remove(pet);               // Remove pet
                        controller.getUser().updateMoneyLabel();             // Refresh money
                    }
                    refreshPetDisplay(root); // Re-align pets

                    // Close pet popup

            });

            list.getChildren().add(select);
        }

        Button close = new Button("X");
        close.setStyle("-fx-background-color: transparent; -fx-text-fill: orange; -fx-font-weight: bold;");
        close.setOnAction(e -> root.getChildren().removeAll(blurLayer, panel));

        VBox.setMargin(close, new Insets(0, 0, 0, 200));

        panel.getChildren().addAll(close, title, list);
        root.getChildren().addAll(blurLayer, panel);
    }





}







