package com.example.virtualpetgame;



public class Food {
    String name;
    int hungerReduction;
    int price;
    int energyIncrease;

    public Food(String name, int hungerReduction,int energyIncrease, int price) {
        this.name = name;
        this.hungerReduction = hungerReduction;
        this.price = price;
        this.energyIncrease=energyIncrease;
    }
}
