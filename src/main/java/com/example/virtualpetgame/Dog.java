package com.example.virtualpetgame;

public class Dog extends Pet{


    public Dog(String name) {
        super(name, 60, 50, 80, new String[]{"Bone","Beef"},new String[]{"Bone", "Frisbee"} , 80, "Woof Woof");
    }

    @Override
    public String evolveName(String original) {
        return "Wolf";
    }

    @Override
    public Pet clonePet() {
        return new Dog("");
    }
}
