package com.example.virtualpetgame;

public class Toy {
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

