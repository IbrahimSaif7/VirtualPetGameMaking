package com.example.virtualpetgame;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.time.*;

public class VirtualPetSimulator {
    private static LocalDate lastCheckedDate = LocalDate.now();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String userName = scanner.nextLine();

        GameController game = new GameController(userName);
        game.startGame();

     }
}


