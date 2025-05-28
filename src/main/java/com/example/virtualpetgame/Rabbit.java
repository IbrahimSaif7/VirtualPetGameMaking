package com.example.virtualpetgame;

public class Rabbit extends Pet{
    public Rabbit(String name) {
        super(name, 60, 50, 40, new String[]{"Carrot", "Lettuce"}, new String[]{"Ball", "Cardboard Box"} , 60, "eook eook");
    }

    @Override
    public String evolveName(String original) {
        return "Pikachu";
    }

    @Override
    public Pet clonePet() {
        return new Rabbit("");
    }
}




