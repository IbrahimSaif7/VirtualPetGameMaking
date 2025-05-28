package com.example.virtualpetgame;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class GameShop {

    private final StackPane root;
    private final GameController controller;
    private final PetShop shop;
    private final MainGameScreen mainScreen;

    public GameShop(StackPane root, GameController controller, MainGameScreen mainGameScreen) {
        this.root = root;
        this.controller = controller;
        this.shop = new PetShop(); // or controller.getShop() if you want shared instance
        this.mainScreen=mainGameScreen;
    }

    public void showPetShop(){
        User user= controller.getUser();
        Region dim = new Region();
        dim.setStyle("-fx-background-color: rgba(0,0,0,0.6);");
        dim.setPrefSize(700, 600);

        ImageView petShopBg = new ImageView(new Image(getClass().getResourceAsStream("/images/petcategory.jpg")));
        petShopBg.setFitWidth(650);
        petShopBg.setFitHeight(450);
        petShopBg.setPreserveRatio(false);

        Pet[] availablePets = new Pet[]{
                controller.getAvailablePets().get(0),
                controller.getAvailablePets().get(1),
                controller.getAvailablePets().get(2),
                controller.getAvailablePets().get(3),
                controller.getAvailablePets().get(4)
        };

        Button cat = createPetButton(availablePets[1],user);
        cat.setPrefSize(75,75);

        Button dog = createPetButton(availablePets[0],user);
        dog.setPrefSize(75,75);

        Button rabbit =createPetButton(availablePets[2],user);
        rabbit.setPrefSize(75,75);

        Button hamster = createPetButton(availablePets[3],user);
        hamster.setPrefSize(70,70);
        Button dragon =createPetButton(availablePets[4],user);
        dragon.setPrefSize(80,80);

        StackPane.setAlignment(cat, Pos.CENTER);
        StackPane.setMargin(cat, new Insets(0,165,210,0));

        StackPane.setAlignment(dog, Pos.CENTER);
        StackPane.setMargin(dog, new Insets(0,0,210,165));

        StackPane.setAlignment(rabbit, Pos.CENTER);
        StackPane.setMargin(rabbit,new Insets(0,0,0,0));

        StackPane.setAlignment(hamster, Pos.CENTER);
        StackPane.setMargin(hamster,new Insets(225,0,0,145));

        StackPane.setAlignment(dragon, Pos.CENTER);
        StackPane.setMargin(dragon,new Insets(215,150,0,0));

        for (Button btn : new Button[]{cat, dog, rabbit,hamster,dragon}){
            btn.setStyle("-fx-background-color: transparent;"+ "-fx-cursor: hand;");


        }

        Button close = new Button("X");
        close.setStyle("-fx-font-weight: bold;");
        StackPane.setAlignment(close, Pos.TOP_RIGHT);
        StackPane.setMargin(close, new Insets(60, 60, 0, 0));

        StackPane overlay = new StackPane(dim, petShopBg, cat, dog, rabbit,hamster,dragon, close);
        close.setOnAction(e -> root.getChildren().remove(overlay));



        root.getChildren().addAll(overlay);
    }

    private void showPetNamingOverlay(StackPane root, Pet pet, User user) {
        VBox overlay = new VBox(15);
        overlay.setStyle("-fx-background-color: rgba(0,0,0,0.75); -fx-padding: 30; -fx-background-radius: 15;");
        overlay.setAlignment(Pos.CENTER);

        Label title = new Label("Name Your " + pet.getClass().getSimpleName());
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: gold;");

        TextField nameInput = new TextField();
        nameInput.setPromptText("Enter name...");
        nameInput.setMaxWidth(200);
        nameInput.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-size: 14px;");

        Button confirm = new Button("Confirm");
        confirm.setStyle("-fx-background-color: gold; -fx-font-weight: bold;");

        confirm.setOnAction(e -> {
            String name = nameInput.getText().trim();

            if (name.isEmpty()) {
                nameInput.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-border-color: red; -fx-border-width: 2;");
                Toast.show(root, "Please enter a name!");
                return;
            }

            Pet newPet = pet.clonePet();
            newPet.setName(name);
            boolean bought = user.buyPet(newPet, root);

            if (bought) {
                user.updateMoneyLabel();
                mainScreen.refreshPetDisplay(root);
            }
            root.getChildren().remove(overlay);

        });

        overlay.getChildren().addAll(title, nameInput, confirm);
        root.getChildren().add(overlay);
    }

    private Button createPetButton(Pet pet, User user) {

        Button button = new Button();
        button.setStyle("-fx-background-color: transparent;");

        button.setOnAction(e -> {
            showPetNamingOverlay(root, pet, user);
        });


        return button;

    }

    public void showFoodShop(){

        User user = controller.getUser();
        Region dim = new Region();
        dim.setStyle("-fx-background-color: rgba(0,0,0,0.6);");
        dim.setPrefSize(700, 600);

        ImageView foodShopBg = new ImageView(new Image(getClass().getResourceAsStream("/images/food shop.jpg")));
        foodShopBg.setFitWidth(650);
        foodShopBg.setFitHeight(450);
        foodShopBg.setPreserveRatio(false);

        Food[] food = new Food[]{
                controller.getAvailableFoods().get(0),
                controller.getAvailableFoods().get(1),
                controller.getAvailableFoods().get(2),
                controller.getAvailableFoods().get(3),
                controller.getAvailableFoods().get(4),
                controller.getAvailableFoods().get(5),
                controller.getAvailableFoods().get(6),

        };

        Button chicken = new Button();
        chicken.setPrefSize(62,62);
        StackPane.setAlignment(chicken, Pos.CENTER);
        StackPane.setMargin(chicken, new Insets(0,210,140,0));
        chicken.setOnAction(e -> {
            buyingFood(food[2],root,user);
        });

        Button meat = new Button();
        meat.setPrefSize(68,68);
        StackPane.setAlignment(meat,Pos.CENTER);
        StackPane.setMargin(meat, new Insets(0,0,150,25));
        meat.setOnAction(e -> {
            buyingFood(food[4],root,user);
        });

        Button fish = new Button();
        fish.setPrefSize(65,65);
        StackPane.setAlignment(fish,Pos.CENTER);
        StackPane.setMargin(fish, new Insets(0,0,150,210));
        fish.setOnAction(e -> {
            buyingFood(food[0],root,user);
        });

        Button carrot = new Button();
        carrot.setPrefSize(90,35);
        StackPane.setAlignment(carrot,Pos.CENTER);
        StackPane.setMargin(carrot,new Insets(75,140,0,0));
        carrot.setOnAction(e -> {
            buyingFood(food[1],root,user);
        });

        Button lettuce = new Button();
        lettuce.setPrefSize(50,65);
        StackPane.setAlignment(lettuce,Pos.CENTER);
        StackPane.setMargin(lettuce, new Insets(55,0,0,160));
        lettuce.setOnAction(e -> {
            buyingFood(food[5],root,user);
        });

        Button bone = new Button();
        bone.setPrefSize(100,30);
        StackPane.setAlignment(bone, Pos.CENTER);
        StackPane.setMargin(bone, new Insets(250,110,0,0));
        bone.setOnAction(e -> {
            buyingFood(food[3],root,user);
        });

        Button cookie = new Button();
        cookie.setPrefSize(45,45);
        StackPane.setAlignment(carrot,Pos.CENTER);
        StackPane.setMargin(cookie,new Insets(240,0,0,180));
        cookie.setOnAction(e -> {
            buyingFood(food[6],root,user);
        });

        for (Button btn : new Button[]{chicken,meat,fish,lettuce,carrot,bone,cookie}){
            btn.setStyle("-fx-background-color: transparent;"+ "-fx-cursor: hand;");


        }
        Button close = new Button("X");
        close.setStyle("-fx-font-weight: bold;");
        StackPane.setAlignment(close, Pos.TOP_RIGHT);
        StackPane.setMargin(close, new Insets(60, 60, 0, 0));

        StackPane overlay = new StackPane(dim,foodShopBg,chicken,meat,fish,carrot,lettuce,bone,cookie,close);


        close.setOnAction(e -> root.getChildren().remove(overlay));

        root.getChildren().addAll(overlay);
    }

    public void showToyShop(){

        User user = controller.getUser();
        Region dim = new Region();
        dim.setStyle("-fx-background-color: rgba(0,0,0,0.6);");
        dim.setPrefSize(700, 600);

        ImageView toyShopBg = new ImageView(new Image(getClass().getResourceAsStream("/images/toyshop.jpg")));
        toyShopBg.setFitWidth(650);
        toyShopBg.setFitHeight(450);
        toyShopBg.setPreserveRatio(false);

        Toy[] toy = new Toy[]{

                controller.getAvailableToys().get(0),
                controller.getAvailableToys().get(1),
                controller.getAvailableToys().get(2),
                controller.getAvailableToys().get(3),
                controller.getAvailableToys().get(4),
                controller.getAvailableToys().get(5),
                controller.getAvailableToys().get(6)


        };

        Button ball = new Button();
        ball.setPrefSize(56,56);
        StackPane.setAlignment(ball, Pos.CENTER);
        StackPane.setMargin(ball, new Insets(0,210,140,0));
        ball.setOnAction(e -> {
            buyingToys(toy[0],root,user);
        });

        Button frisbee = new Button();
        frisbee.setPrefSize(80,40);
        StackPane.setAlignment(frisbee,Pos.CENTER);
        StackPane.setMargin(frisbee, new Insets(0,0,120,5));
        frisbee.setOnAction(e -> {
            buyingToys(toy[1],root,user);
        });

        Button boomerang = new Button();
        boomerang.setPrefSize(65,55);
        StackPane.setAlignment(boomerang,Pos.CENTER);
        StackPane.setMargin(boomerang, new Insets(0,0,150,210));
        boomerang.setOnAction(e -> {
            buyingToys(toy[3],root,user);
        });

        Button plushie = new Button();
        plushie.setPrefSize(64,70);
        StackPane.setAlignment(plushie,Pos.CENTER);
        StackPane.setMargin(plushie,new Insets(60,120,0,0));
        plushie.setOnAction(e -> {
            buyingToys(toy[5],root,user);
        });

        Button hamsterWheel = new Button();
        hamsterWheel.setPrefSize(65,65);
        StackPane.setAlignment(hamsterWheel,Pos.CENTER);
        StackPane.setMargin(hamsterWheel, new Insets(55,0,0,142));
        hamsterWheel.setOnAction(e -> {
            buyingToys(toy[6],root,user);
        });

        Button box = new Button();
        box.setPrefSize(57,57);
        StackPane.setAlignment(box, Pos.CENTER);
        StackPane.setMargin(box, new Insets(250,110,0,0));
        box.setOnAction(e -> {
            buyingToys(toy[2],root,user);
        });

        Button bone = new Button();
        bone.setPrefSize(85,30);
        StackPane.setAlignment(bone, Pos.CENTER);
        StackPane.setMargin(bone, new Insets(280,0,0,140));
        bone.setOnAction(e -> {
            buyingToys(toy[4],root,user);
        });

        for (Button btn : new Button[]{ball,frisbee,bone,hamsterWheel,box,bone,plushie,boomerang}){
            btn.setStyle("-fx-background-color: transparent;"+ "-fx-cursor: hand;");


        }
        Button close = new Button("X");
        close.setStyle("-fx-font-weight: bold;");
        StackPane.setAlignment(close, Pos.TOP_RIGHT);
        StackPane.setMargin(close, new Insets(60, 60, 0, 0));

        StackPane overlay= new StackPane(dim,toyShopBg,ball,frisbee,boomerang,plushie,hamsterWheel,box,bone,close);


        close.setOnAction(e -> root.getChildren().remove(overlay));




        root.getChildren().addAll(overlay);
    }

    private void buyingFood(Food food, StackPane root, User user){

       user.buyFood(food,root);
       user.updateMoneyLabel();

    }

    private void buyingToys(Toy toy, StackPane root, User user){

        user.buyToy(toy,root);
        user.updateMoneyLabel();


    }

}
