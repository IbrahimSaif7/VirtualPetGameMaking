package com.example.virtualpetgame;

public class PetShop {
    Pet[] availablePets;
    Food[] availableFoods;
    Toy[] availableToys;

    public PetShop() {

        availablePets = new Pet[]{
                new Dog("Puppy"),
                new Cat("Kitten"),
                new Rabbit("Bunny"),
                new Hamster("Hamster"),
                new Dragon("Dragon")
        };
        availableFoods = new Food[]{
                new Food("Fish", 15,5, 10),
                new Food("Carrot", 10, 8, 8),
                new Food("Chicken",15,7,10),
                new Food("Bone",10,3,5),
                new Food("Beef",20,5,15),
                new Food("Lettuce",15,8,8),
                new Food("Cookie",15,3,5)
        };
        availableToys = new Toy[]{
                new Toy("Ball",20,10,15),
                new Toy("Frisbee",10,8,15),
                new Toy("Cardboard Box",15,8,8),
                new Toy("Boomerang",15,8,10),
                new Toy("Bone",20,10,15),
                new Toy("Plushie",10,9,10),
                new Toy("Wheel",15,8,15)

        };

    }

    public void displayPets(User user) {
        System.out.println("\n=== PET SHOP ===");


        System.out.println("\n-- Pets for Sale --");
        for (int i = 0; i < availablePets.length; i++) {

            System.out.println((i + 1) + ". " + availablePets[i].getClass().getSimpleName() + " - $" + availablePets[i].price);
        }
    }
    public void displayFood(User user){
        System.out.println("\n-- Food for Sale --");
        for (int i = 0; i < availableFoods.length; i++) {

            System.out.println((i+1) + ". " + availableFoods[i].name + " (Hunger -" + availableFoods[i].hungerReduction + ") - $" + availableFoods[i].price);
        }
    }
    public void displayToys(User user){
        System.out.println("\n-- Toys for Sale --");
        for (int i = 0; i < availableToys.length; i++) {

            System.out.println((i+1) + ". " + availableToys[i].name + " (Mood -" + availableToys[i].moodIncrease + ") - $" + availableToys[i].price);
        }
    }
}

