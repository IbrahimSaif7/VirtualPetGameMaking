package com.example.virtualpetgame;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;


import java.util.*;
import java.time.*;

public class User {

    private String username;
    protected List<Pet> pets;
    private int money;
    List<Food> foodInventory;
    List<Toy> toyInventory;
    private TaskType currentTask;
    private boolean taskCompleted;
    private LocalDateTime lastTaskTime;
    DailyTask dailyTask;
    private boolean fedPetToday;
    private boolean playedWithPetToday;
    private boolean putPetToSleepToday;
    private boolean evolvedPetToday;
    private Label moneyAmountLabel;
    private HBox moneyContainer;




    public User(String username, int initialMoney) {
        this.username = username;
        this.money = initialMoney;
        this.pets = new ArrayList<>();
        this.foodInventory = new ArrayList<>();
        this.toyInventory = new ArrayList<>();
        this.lastTaskTime = LocalDateTime.now().minusHours(25); // ensure task is available immediately
        this.currentTask = getCurrentTask();
        this.taskCompleted=false;
        this.dailyTask= generateRandomTask();
        this.resetDailyTaskFlags();
    }
    public String getUsername(){
        return username;
    }

    public boolean buyPet(Pet pet) {
        if (pets.size() >= 3) {
            System.out.println("You can only have 3 pets!");
            return false;
        }

        if (money >= pet.price) {
            money -= pet.price;
            pets.add(pet);
            System.out.println(username + " has bought a " + pet.name + "!");
            return true;
        } else {
            System.out.println(username + " doesn't have enough money to buy this pet.");
            return false;
        }
    }

    public boolean buyPet(Pet pet, StackPane root) {
        if (pets.size() >= 3) {
            System.out.println("You can only have 3 pets!");
            Toast.show(root,"You can only have 3 pets!");
            return false;
        }

        if (money >= pet.price) {
            money -= pet.price;
            pets.add(pet);
            System.out.println(username + " has bought " + pet.name + "!");
            Toast.show(root, username + " has bought a " + pet.name + "!");
            return true;
        } else {
            System.out.println(username + " doesn't have enough money to buy this pet.");
            Toast.show(root, username + " doesn't have enough money to buy this pet.");

            return false;
        }
    }

    public boolean buyFood(Food food) {
        if (money >= food.price) {
            money -= food.price;
            foodInventory.add(food);
            System.out.println("Bought " + food.name + " for $" + food.price);
            return true;

        } else {
            System.out.println("Not enough money to buy " + food.name);
            return false;
        }
    }

    public boolean buyFood(Food food, StackPane root){

        if (money >= food.price) {
            money -= food.price;
            foodInventory.add(food);
            System.out.println("Bought " + food.name + " for $" + food.price);
            Toast.show(root,"Bought " + food.name + " for $" + food.price);
            return true;

        } else {
            System.out.println("Not enough money to buy " + food.name);
            Toast.show(root, "Not enough money to buy " + food.name);
            return false;
        }

    }

    public boolean buyToy(Toy toy) {
        if (money >= toy.price) {
            money -= toy.price;
            toyInventory.add(toy);
            System.out.println("Bought " + toy.name + " for $" + toy.price);
            return true;
        } else {
            System.out.println("Not enough money to buy " + toy.name);
            return false;
        }
    }

    public boolean buyToy(Toy toy, StackPane root){

        if (money >= toy.price) {
            money -= toy.price;
            toyInventory.add(toy);
            System.out.println("Bought " + toy.name + " for $" + toy.price);
            Toast.show(root,"Bought " + toy.name + " for $" + toy.price );
            return true;
        } else {
            System.out.println("Not enough money to buy " + toy.name);
            Toast.show(root, ("Not enough money to buy " + toy.name));
            return false;
        }

    }

    public List<Food> getFoodInventory() {
        return foodInventory;
    }

    public List<Toy> getToyInventory() {
        return toyInventory;
    }

    public void removeFood(Food food) {
        foodInventory.remove(food);
    }

    public void earnMoney(int amount) {
        money += amount;
        System.out.println(username + " earned $" + amount + "!");
    }

    public void spendMoney(int amount) {
        money -= amount;
        System.out.println(username + " spent $" + amount + ".");
    }

    public int getMoney() {
        return money;
    }

    public void showStatus() {
        System.out.println("\n== " + username + "'s STATUS ==");
        System.out.println("MONEY: $" + money);
        if (pets.size() > 0) {
            for (Pet pet : pets) {
                if (pet.isAlive()) pet.displayStatus();
            }
        } else {
            System.out.println("No pets yet.");
        }
        System.out.println("FOOD INVENTORY:");
        for (int i = 0; i < foodInventory.size(); i++) {
            Food food = foodInventory.get(i);
            System.out.println((i + 1) + ". " + food.name + " (Hunger -" + food.hungerReduction + ", Cost: $" + food.price + ")");
        }
        System.out.println("TOY INVENTORY:");
        for (int i = 0; i < toyInventory.size(); i++) {
            Toy toy = toyInventory.get(i);
            System.out.println((i + 1) + ". " + toy.name + " (Hunger -" + toy.moodIncrease + ", Cost: $" + toy.price + ")");
        }
    }


    public TaskType getCurrentTask() {
        return currentTask;
    }

    public boolean isTaskCompleted() {
        return taskCompleted;
    }
    public void completeTask(TaskType actionType) {
        if (!taskCompleted && actionType == currentTask) {
            taskCompleted = true;
            earnMoney(30); // fixed reward, or adjust per task
            System.out.println("🎉 Daily task completed! Earned $30.");
        }
    }



    public DailyTask generateRandomTask(){

        TaskType[] values = TaskType.values();
        int index = (int) (Math.random() * values.length);
        TaskType type = values[index];
        int reward = switch (type) {
            case FEED_PET -> 20;
            case PLAY_WITH_PET -> 30;
            case MAKE_PET_SLEEP -> 15;
            case EVOLVE_PET -> 50;
        };
        return new DailyTask(type,reward);

    }

    public DailyTask getDailyTask() {
        return dailyTask;
    }

    public void markFedPet() {
        this.fedPetToday = true;
        checkAndCompleteTask(TaskType.FEED_PET);
    }

    public void markPlayedWithPet() {
        this.playedWithPetToday = true;
        checkAndCompleteTask(TaskType.PLAY_WITH_PET);
    }

    public void markPutPetToSleep() {
        this.putPetToSleepToday = true;
        checkAndCompleteTask(TaskType.MAKE_PET_SLEEP);
    }

    public void markEvolvedPet() {
        this.evolvedPetToday = true;
        checkAndCompleteTask(TaskType.EVOLVE_PET);
    }

    public void checkAndCompleteTask(TaskType type) {
        if (dailyTask.isCompleted()) {
            return;
        }

        if (dailyTask.getTaskType() != type) {
            return;
        }

        boolean conditionsMet = false;
        switch (type) {
            case FEED_PET:
                conditionsMet = fedPetToday;
                break;
            case PLAY_WITH_PET:
                conditionsMet = playedWithPetToday;
                break;
            case MAKE_PET_SLEEP:
                conditionsMet = putPetToSleepToday;
                break;
            case EVOLVE_PET:
                conditionsMet = evolvedPetToday;
                break;
        }

        if (conditionsMet) {
            dailyTask.complete(this);
        }
    }

    public void resetDailyTaskFlags() {
        fedPetToday = false;
        playedWithPetToday = false;
        putPetToSleepToday = false;
        evolvedPetToday = false;
    }

    public HBox getMoneyDisplay() {
        if (moneyAmountLabel == null) {
            Image moneyIcon = new Image(getClass().getResourceAsStream("/images/Money.png"));
            ImageView moneyIconView = new ImageView(moneyIcon);
            moneyIconView.setFitWidth(40);
            moneyIconView.setFitHeight(40);

            moneyAmountLabel = new Label(": " + getMoney());
            moneyAmountLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: gold;");

            moneyContainer = new HBox(5);
            moneyContainer.setAlignment(Pos.CENTER_LEFT);
            moneyContainer.getChildren().addAll(moneyIconView, moneyAmountLabel);
        }

        updateMoneyLabel(); // Always refresh value before returning
        return moneyContainer;
    }

    public void updateMoneyLabel() {
        if (moneyAmountLabel != null) {
            moneyAmountLabel.setText(": " + getMoney());
        }
    }

    public void sellPet(Pet pet) {
        if (pets.isEmpty()) {
            System.out.println("No pets to sell.");
            return;
        }

        if (pets.remove(pet)) {
            earnMoney(pet.sellValue); // assuming Pet has a sellValue field
            System.out.println("You sold " + pet.name + " for $" + pet.sellValue + ".");
        } else {
            System.out.println("Pet not found.");
        }
    }

    public void sellPet(Pet pet, StackPane root) {
        if (pets.isEmpty()) {
            System.out.println("No pets to sell.");

            return;
        }

        if (pets.remove(pet)) {
            earnMoney(pet.sellValue); // assuming Pet has a sellValue field
            System.out.println("You sold " + pet.name + " for $" + pet.sellValue + ".");
            Toast.show(root, "You sold " + pet.name + " for $" + pet.sellValue + ".");
        } else {
            System.out.println("Pet not found.");
        }
    }

    public void checkPetDeath(Pet pet, StackPane root, Runnable onDeathCallback) {
        if (pet.isDead()) {
            pets.remove(pet);
            updateMoneyLabel();

            // Show an alert window 💀
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Oh no!");
            alert.setHeaderText(null);
            alert.setContentText(pet.name + " has died 💀");
            alert.showAndWait();

            // Optional toast for flair
            DialogPane dialog = alert.getDialogPane();
            dialog.setStyle("-fx-background-color: #1e1e2e;");
            dialog.lookup(".content.label").setStyle("-fx-text-fill: white; -fx-font-size: 14px;");

            // Run the callback to refresh UI, etc.
            if (onDeathCallback != null) {
                onDeathCallback.run();
            }
        }
    }




}
