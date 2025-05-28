package com.example.virtualpetgame;

public class Dragon extends Pet{
    public Dragon(String name){
        super(name,50, 30, 70, new String[]{"Cookie", "Beef"},new String[]{"Plushie","Boomerang"},80,"Rawr");
    }

    @Override
    public String evolveName(String original) {
        return "Fire Dragon";
    }

    @Override
    public Pet clonePet() {
        return new Dragon("");
    }
}