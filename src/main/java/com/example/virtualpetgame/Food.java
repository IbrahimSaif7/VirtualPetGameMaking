package com.example.virtualpetgame;


import java.io.Serializable;

public class Food implements Serializable {
    private static final long serialVersionUID = 1L;
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
