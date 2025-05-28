package com.example.virtualpetgame;
import java.util.Scanner;
import java.util.Random;

public class MiniGames {
    private static final Random random = new Random();
    private static final Scanner scanner = new Scanner(System.in);

    public static void playTriviaGame(User user) {


        String[][] questions = {
                {"What food do cats love?", "A) Milk", "B) Chocolate", "C) Onions", "A"},
                {"How many hours do rabbits sleep daily?", "A) 2", "B) 8", "C) 12", "B"},
                {"Which is toxic to dogs?", "A) Carrots", "B) Grapes", "C) Rice", "B"},
                {"A cat's purr vibrates at ___ Hz?", "A) 10-20", "B) 25-150", "C) 200-300", "B"},
                {"Dogs smell ____ times better than humans?", "A) 10", "B) 10,000", "C) 1,000,000", "B"}
        };

        final int ENTRY_FEE = 10;
        final int REWARD = 25;

        while (true) {
            System.out.println("\n=== TRIVIA GAME ===");
            System.out.println("Entry fee: $" + ENTRY_FEE + " | Correct answer wins $" + REWARD);


            if (user.getMoney() < ENTRY_FEE) {
                System.out.println("❌ You need at least $" + ENTRY_FEE + " to play!");
                break;
            }

            user.spendMoney(ENTRY_FEE);
            int randomQ = random.nextInt(questions.length);


            System.out.println("\n" + questions[randomQ][0]);
            System.out.println( questions[randomQ][1]);
            System.out.println( questions[randomQ][2]);
            System.out.println( questions[randomQ][3]);


            String answer;
            while (true) {
                System.out.print("Your answer (A/B/C): ");
                answer = scanner.nextLine().toUpperCase();
                if (answer.matches("[A-C]")) break;
                System.out.println("Invalid input! Choose A, B, or C.");
            }


            if (answer.equals(questions[randomQ][4])) {
                user.earnMoney(REWARD);
                System.out.println("✅ Correct! You won $" + REWARD + "!");
            } else {
                System.out.println("❌ Wrong! The answer was " + questions[randomQ][4]);
            }


            System.out.print("\nPlay again? (Y/N): ");
            if (!scanner.nextLine().equalsIgnoreCase("Y")) {
                break;
            }


        }
    }

    public static void playMemoryGame(User user) {
        final int ENTRY_FEE = 10;
        final int REWARD = 30;


        while (true) {
            System.out.println("\n=== MEMORY GAME ===");
            System.out.println("ENTRY FEE: $" + ENTRY_FEE + " | REWARD: $" + REWARD);

            if (user.getMoney() < ENTRY_FEE) {
                System.out.println("❌ You need at least $" + ENTRY_FEE + " to play!");
                break;
            }

            System.out.println("Press S to START GAME or X to EXIT!");
            String choice = scanner.nextLine().trim().toUpperCase();

            if (choice.equals("X")) {
                break;
            } else if (!choice.equals("S")) {
                System.out.println("Invalid Input! Try again :(");
                continue;
            }
            user.spendMoney(ENTRY_FEE);

            StringBuilder sequence = new StringBuilder();
            for (int i = 0; i < 5; i++) {
                sequence.append(random.nextInt(10));
            }

            // Show number
            System.out.println("\nMEMORIZE THIS NUMBER: " + sequence);
            System.out.println("You have 5 seconds...");

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            // Clear screen with blank lines
            for (int i = 0; i < 60; i++) {
                System.out.println();
            }

            // Get guess
            System.out.print("ENTER THE NUMBER: ");
            String guess = scanner.next();

            // Check answer
            if (guess.equals(sequence.toString())) {
                System.out.println("✅ CORRECT! YOU WIN $" + REWARD);
                user.earnMoney(REWARD);
            } else {
                System.out.println("❌ WRONG! The number was: " + sequence);
            }

            // Play again prompt
            System.out.print("\nPlay again? (Y/N): ");
            String play = scanner.next();
            scanner.nextLine();
            if (!play.equalsIgnoreCase("Y")) {
                break;
            }
        }
    }

    public static void playArithmeticGame(User user){

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        final int ENTRY_FEE = 15;
        final int REWARD = 30;

        while (true){
            System.out.println("\n=== ARITHMETIC GAME ===");
            System.out.println("Entry fee: $" + ENTRY_FEE + " | Correct answer: $" +REWARD );

            if (user.getMoney() < ENTRY_FEE) {
                System.out.println("❌ You need at least $" + ENTRY_FEE + " to play!");
                break;
            }
            System.out.println("Press S to START GAME or X to EXIT!");
            String choice = scanner.nextLine().trim().toUpperCase();

            if (choice.equals("X")) {
                break;
            } else if (!choice.equals("S")) {
                System.out.println("Invalid Input! Try again :(");
                continue;
            }
            user.spendMoney(ENTRY_FEE);

            int a = random.nextInt(51);
            int b = random.nextInt(51);
            String[] operators = {"+", "-", "*"};
            String op = operators[random.nextInt(3)];

            int correctAnswer = switch (op) {
                case "+" -> a + b;
                case "-" -> a - b;
                case "*" -> a * b;
                default -> 0;
            };

            System.out.printf("\nWhat is %d %s %d? ", a, op, b);
            int playerAnswer;

            try {
                playerAnswer = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("❌ Invalid input! Numbers only.");
                scanner.nextLine();
                continue;
            }

            if (playerAnswer == correctAnswer) {
                user.earnMoney(REWARD);
                System.out.println("✅ Correct! You earned $" + REWARD + "!");
            } else {
                System.out.printf("❌ Wrong! The answer was %d.\n", correctAnswer);
            }
            System.out.print("\nPlay again? (Y/N): ");
            if (!scanner.nextLine().equalsIgnoreCase("Y")) break;
        }





    }



}

