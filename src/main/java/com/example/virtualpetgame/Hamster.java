package com.example.virtualpetgame;

public class Hamster extends Pet {
    public Hamster(String name) {
        super(name, 50, 40, 60, new String[]{"Carrot", "Lettuce"}, new String[]{"Wheel"}, 100, "Squeak");
    }

    @Override
    public String evolveName(String original) {
        return "Capybara";
    }

    @Override
    public Pet clonePet() {
        return new Hamster("");
    }
}