package com.example.virtualpetgame;

public class Cat extends Pet{

    public Cat(String name){

        super(name,60, 30, 50, new String[]{"Milk", "Chicken"}, new String[]{"Ball", "Plushie"} ,80, "Meow Meow");


    }

    @Override
    public String evolveName(String original) {
        return "Tiger";
    }

    @Override
    public Pet clonePet() {
        return new Cat("");
    }

}
