package com.example.virtualpetgame;

import java.io.Serializable;

public class Toy implements Serializable {
    private static final long serialVersionUID = 1L;
    String name;
    int moodIncrease;
    int energyDecrease;
    int price;
    int uses;

    public Toy(String name, int moodIncrease,int energyDecrease, int price) {
        this.name = name;
        this.moodIncrease = moodIncrease;
        this.price = price;
        this.energyDecrease=energyDecrease;
        uses=4;
    }

    public void use() {
        if (uses > 0) {
            uses -= 1;
        }
    }
}

