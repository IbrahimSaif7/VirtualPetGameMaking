package com.example.virtualpetgame;
import javafx.scene.layout.StackPane;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.time.*;

abstract class Pet {
    protected String name;
    protected int hunger;
    protected int mood;
    protected int energy;
    protected String[] favoriteFoods= new String[2];
    protected String[] favouriteToys = new String[2];
    protected boolean isAlive;
    protected int price;
    protected boolean evolved;
    protected int sellValue;

    protected String sound;
    protected LocalDateTime lastUpdated;

    public Pet(String name, int hunger, int mood, int energy, String[] favoriteFoods, String[] favouriteToys, int price, String sound) {
        this.name = name;
        this.hunger = hunger;
        this.mood = mood;
        this.energy = energy;
        this.favoriteFoods = favoriteFoods.clone();
        this.favouriteToys = favouriteToys.clone();// Deep Copy
        this.isAlive = true;
        this.price = price;
        this.sellValue= (int) (price*0.7);
        this.evolved = false;
        this.sound=sound;
        this.lastUpdated = LocalDateTime.now();

    }

    public abstract String evolveName(String original);
    public abstract Pet clonePet();

    public void setName(String name){
        this.name=name;

    }
    public String getName(){
        return name;
    }

    public Pet(Pet other) {
        this(other.name, other.hunger, other.mood, other.energy, other.favoriteFoods, other.favouriteToys ,other.price, other.sound);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pet pet = (Pet) obj;
        return hunger == pet.hunger && mood == pet.mood && energy == pet.energy &&
                Objects.equals(name, pet.name) && Arrays.equals(favoriteFoods, pet.favoriteFoods);
    }

    public int clamp(int value){
        if(value>100){
            return 100;
        }
        else if (value<0) {
            return 0;
        }
        else{
            return value;
        }
    }

    public void feed(Food food, User user) {


        if (Arrays.asList(favoriteFoods).contains(food.name)) {
            hunger -= food.hungerReduction*1.5;
            hunger = clamp(hunger);
            mood += 5;
            mood = clamp(mood);
            energy += food.energyIncrease;
            energy = clamp(energy);
            user.earnMoney(15);
            System.out.println(name + " loves " + food.name + "! Hunger -" + food.hungerReduction*1.5 + ", Mood +5, Energy "+ food.energyIncrease + ", Earned $15");
        } else {
            hunger -= food.hungerReduction;
            hunger = clamp(hunger);
            energy += food.energyIncrease;
            energy = clamp(energy);
            user.earnMoney(5);
            System.out.println(name + " eats " + food.name + ". It's okay. Hunger -" + food.hungerReduction + "Energy "+ food.energyIncrease +", Earned $5");
        }
    }

    public void feed(Food food, User user, StackPane root) {
        if (Arrays.asList(favoriteFoods).contains(food.name)) {
            hunger -= food.hungerReduction*1.5;
            hunger = clamp(hunger);
            mood += 5;
            mood = clamp(mood);
            energy += food.energyIncrease;
            energy = clamp(energy);
            user.earnMoney(7);
            System.out.println(name + " loves " + food.name + "! Hunger -" + food.hungerReduction*1.5 + ", Mood +5, Energy "+ food.energyIncrease + ", Earned $15");
            Toast.show(root, name + " loves " + food.name + "! Hunger -" + food.hungerReduction*1.5 + ", Mood +5, Energy "+ food.energyIncrease + ", Earned $7");
        } else {
            hunger -= food.hungerReduction;
            hunger = clamp(hunger);
            energy += food.energyIncrease;
            energy = clamp(energy);
            user.earnMoney(3);
            System.out.println(name + " eats " + food.name + ". It's okay. Hunger -" + food.hungerReduction + "Energy "+ food.energyIncrease +", Earned $5");
            Toast.show(root, name + " eats " + food.name + ". It's okay. Hunger -" + food.hungerReduction + "Energy "+ food.energyIncrease +", Earned $3");
        }
    }

    public void play(Toy toy, User user) {
        if (toy.uses > 0) {
            if (Arrays.asList(favouriteToys).contains(toy.name)) {
                toy.use();
                mood += toy.moodIncrease * 1.5;
                mood = clamp(mood);
                energy -= toy.energyDecrease;
                energy = clamp(energy);
                user.earnMoney(15);
                System.out.println(name + " loved to play with" + toy.name + "! Mood +" + toy.moodIncrease * 1.5 + ", Energy -" + toy.energyDecrease + " Earned $15");
            } else {
                toy.use();
                mood += toy.moodIncrease;
                mood = clamp(mood);
                energy -= toy.energyDecrease;
                energy = clamp(energy);
                user.earnMoney(5);
                System.out.println(name + " had little fun playing with " + toy.name + " Mood +" + toy.moodIncrease + ", Energy -" + toy.energyDecrease + " Earned $15");
            }
            if (toy.uses == 0) {
                System.out.println("You've word this toy out!");
                user.toyInventory.remove(toy);
            }
        }
    }

    public void play(Toy toy, User user, StackPane root) {
        if (toy.uses > 0) {
            if (Arrays.asList(favouriteToys).contains(toy.name)) {
                toy.use();
                mood += toy.moodIncrease * 1.5;
                mood = clamp(mood);
                energy -= toy.energyDecrease;
                energy = clamp(energy);
                user.earnMoney(8);
                System.out.println(name + " loved to play with" + toy.name + "! Mood +" + toy.moodIncrease * 1.5 + ", Energy -" + toy.energyDecrease + " Earned $15");
                Toast.show(root, name + " loved to play with" + toy.name + "! Mood +" + toy.moodIncrease * 1.5 + ", Energy -" + toy.energyDecrease + " Earned $8");
            } else {
                toy.use();
                mood += toy.moodIncrease;
                mood = clamp(mood);
                energy -= toy.energyDecrease;
                energy = clamp(energy);
                user.earnMoney(4);
                System.out.println(name + " had little fun playing with " + toy.name + " Mood +" + toy.moodIncrease + ", Energy -" + toy.energyDecrease + " Earned $15");
                Toast.show(root,name + " had little fun playing with " + toy.name + " Mood +" + toy.moodIncrease + ", Energy -" + toy.energyDecrease + " Earned $4" );
            }
            if (toy.uses == 0) {
                System.out.println("You've word this toy out!");
                Toast.show(root, "You've word this toy out!");
                user.toyInventory.remove(toy);
            }
        }
    }

    public void sleep(User user) {
        energy += 20;
        hunger += 10;
        energy= clamp(energy);
        hunger= clamp(hunger);
        user.earnMoney(10);
        System.out.println(name + " had a good nap! Energy +20, Hunger +10, Earned $10");
    }

    public void sleep(User user, StackPane root) {
        energy += 20;
        hunger += 10;
        energy= clamp(energy);
        hunger= clamp(hunger);
        user.earnMoney(4);
        System.out.println(name + " had a good nap! Energy +20, Hunger +10, Earned $10");
        Toast.show(root, name + " had a good nap! Energy +20, Hunger +10, Earned $4");
    }

    public void displayStatus() {
        System.out.println("[ " + name + (evolved ? " (Evolved)" : "") + " ] - Hunger: " + hunger + ", Mood: " + mood + ", Energy: " + energy);
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void checkDeath() {
        if (hunger > 100 || mood < 10) {
            isAlive = false;
            System.out.println(name + " has died due to poor care. RIP.");
        }
    }

    public boolean isDead() {
        return hunger >= 95 || energy <= 5 || mood <= 5;
    }


    public void tryEvolve(User user) {
        if (!evolved && mood >= 75 && energy >= 70 && user.getMoney() >= 150) {
            evolved = true;
            user.spendMoney(150);
            name = evolveName(name);
            mood += 10;
            energy += 10;
            mood= clamp(mood);
            energy= clamp(energy);
            System.out.println("Your pet has evolved into " + name + "! Congratulations!");
        } else if (evolved) {
            System.out.println(name + " has already evolved.");
        } else {
            System.out.println(name + " is not ready to evolve yet. Requires mood >= 75, energy >= 70 and $150.");
        }
    }


}


