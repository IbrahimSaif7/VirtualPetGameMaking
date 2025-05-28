package com.example.virtualpetgame;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Random;


public class MiniGameOverlay {

    private final StackPane root;
    private final GameController controller;

    private final MainGameScreen mainScreen;

    public MiniGameOverlay(StackPane root, GameController controller, MainGameScreen mainScreen) {
        this.root = root;
        this.controller = controller;
        this.mainScreen = mainScreen;
    }





    private void styleSoftButton(Button btn, String color) {
        btn.setStyle(
                "-fx-background-color: " + color + ";" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 15px;" +
                        "-fx-background-radius: 8px;" +
                        "-fx-padding: 10 20;" +
                        "-fx-cursor: hand;"
        );

        btn.setOnMouseEntered(e -> btn.setStyle(
                "-fx-background-color: derive(" + color + ", 20%);" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 15px;" +
                        "-fx-background-radius: 8px;" +
                        "-fx-padding: 10 20;" +
                        "-fx-cursor: hand;"
        ));

        btn.setOnMouseExited(e -> btn.setStyle(
                "-fx-background-color: " + color + ";" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 15px;" +
                        "-fx-background-radius: 8px;" +
                        "-fx-padding: 10 20;" +
                        "-fx-cursor: hand;"
        ));
    }

    private void styleGameButton(Button btn, String bgColor, String hoverColor) {
        btn.setStyle("-fx-background-color: " + bgColor + ";" +
                "-fx-text-fill: black;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 16px;" +
                "-fx-background-radius: 10px;" +
                "-fx-padding: 10 20;" +
                "-fx-cursor: hand;");

        btn.setOnMouseEntered(e -> btn.setStyle("-fx-background-color: " + hoverColor + ";" +
                "-fx-text-fill: gold;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 16px;" +
                "-fx-background-radius: 10px;" +
                "-fx-padding: 10 20;" +
                "-fx-cursor: hand;"));

        btn.setOnMouseExited(e -> btn.setStyle("-fx-background-color: " + bgColor + ";" +
                "-fx-text-fill: black;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 16px;" +
                "-fx-background-radius: 10px;" +
                "-fx-padding: 10 20;" +
                "-fx-cursor: hand;"));
    }



    public void showArithmeticGame() {
        final int ENTRY_FEE = 15;
        final int REWARD = 30;
        User user = controller.getUser();

        // Container
        VBox gameContainer = new VBox(25);
        gameContainer.setAlignment(Pos.CENTER);
        gameContainer.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #141420, #0b0b14); " +
                        "-fx-padding: 35; " +
                        "-fx-border-color: #444444; " +
                        "-fx-border-width: 2; " +
                        "-fx-background-radius: 18; " +
                        "-fx-border-radius: 18; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 15, 0.4, 0, 4);"
        );

        Label title = new Label("✨ Arithmetic Game");
        title.setStyle("-fx-font-size: 26px; -fx-text-fill: #333333; -fx-font-weight: bold;");

        Label info = new Label(String.format("Entry Fee: $%d   |   Reward: $%d", ENTRY_FEE, REWARD));
        info.setStyle("-fx-font-size: 16px; -fx-text-fill: #666666;");

        Button startBtn = new Button("Start Game");
        Button exitBtn = new Button("Exit");

        styleSoftButton(startBtn, "#38b6ff");
        styleSoftButton(exitBtn, "#ff5e5e");

        VBox menuBox = new VBox(15, title, info, startBtn, exitBtn);
        menuBox.setAlignment(Pos.CENTER);

        gameContainer.getChildren().add(menuBox);

        StackPane root = new StackPane(gameContainer);
        root.setStyle("-fx-background-color: rgba(240, 240, 240, 0.9);");
        Scene scene = new Scene(root, 450, 420);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Arithmetic Game");
        stage.initModality(Modality.APPLICATION_MODAL);

        startBtn.setOnAction(e -> {
            if (user.getMoney() < ENTRY_FEE) {
                showAlert("Oops!", "You need at least $" + ENTRY_FEE + " to play!", Alert.AlertType.WARNING);
                return;
            }

            user.spendMoney(ENTRY_FEE);
            user.updateMoneyLabel();

            Random random = new Random();
            int a = random.nextInt(51);
            int b = random.nextInt(51);
            String[] ops = {"+", "-", "×"};
            String op = ops[random.nextInt(ops.length)];

            int correct = switch (op) {
                case "+" -> a + b;
                case "-" -> a - b;
                case "×" -> a * b;
                default -> 0;
            };

            Label qLabel = new Label(String.format("%d %s %d = ?", a, op, b));
            qLabel.setStyle("-fx-font-size: 30px; -fx-text-fill: #444; -fx-font-weight: bold;");

            TextField input = new TextField();
            input.setPromptText("Enter your answer");
            input.setMaxWidth(180);
            input.setStyle("-fx-font-size: 16px; -fx-border-radius: 6px; -fx-background-radius: 6px;");

            Button submit = new Button("Submit");
            Button back = new Button("Back");

            styleSoftButton(submit, "#4CAF50");
            styleSoftButton(back, "#b0bec5");

            HBox actionButtons = new HBox(10, submit, back);
            actionButtons.setAlignment(Pos.CENTER);


            VBox questionBox = new VBox(15, qLabel, input, actionButtons);
            questionBox.setAlignment(Pos.CENTER);

            gameContainer.getChildren().setAll(title, questionBox);

            submit.setOnAction(ev -> {
                try {
                    int answer = Integer.parseInt(input.getText());
                    if (answer == correct) {
                        user.earnMoney(REWARD);
                        user.updateMoneyLabel();
                        showAlert("Nice job! 🎉", "You earned $" + REWARD + "!", Alert.AlertType.INFORMATION);
                    } else {
                        showAlert("Oops!", "Correct answer was: " + correct, Alert.AlertType.ERROR);
                    }
                    gameContainer.getChildren().setAll(menuBox);
                } catch (NumberFormatException ex) {
                    showAlert("Invalid", "Please enter a valid number.", Alert.AlertType.ERROR);
                }
            });

            back.setOnAction(ev -> gameContainer.getChildren().setAll(menuBox));
        });

        exitBtn.setOnAction(e -> stage.close());
        stage.show();
    }

    public void showTriviaGame() {
        final int ENTRY_FEE = 10;
        final int REWARD = 25;
        User user = controller.getUser();

        VBox gameContainer = new VBox(25);
        gameContainer.setAlignment(Pos.CENTER);
        gameContainer.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #141420, #0b0b14); " +
                        "-fx-padding: 35; " +
                        "-fx-border-color: #444444; " +
                        "-fx-border-width: 2; " +
                        "-fx-background-radius: 18; " +
                        "-fx-border-radius: 18; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 15, 0.4, 0, 4);"
        );

        Label title = new Label("🧠 Trivia Game");
        title.setStyle("-fx-font-size: 26px; -fx-text-fill: #f9d923; -fx-font-weight: bold;");

        Label info = new Label(String.format("Entry Fee: $%d   |   Reward: $%d", ENTRY_FEE, REWARD));
        info.setStyle("-fx-font-size: 16px; -fx-text-fill: #cccccc;");

        Button startBtn = new Button("Start Game");
        Button exitBtn = new Button("Exit");
        styleSoftButton(startBtn, "#38b6ff");
        styleSoftButton(exitBtn, "#ff5e5e");

        VBox menuBox = new VBox(15, title, info, startBtn, exitBtn);
        menuBox.setAlignment(Pos.CENTER);
        gameContainer.getChildren().add(menuBox);

        StackPane root = new StackPane(gameContainer);
        root.setStyle("-fx-background-color: rgba(0, 0, 0, 0.75);");
        Scene scene = new Scene(root, 480, 450);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Trivia Game");
        stage.initModality(Modality.APPLICATION_MODAL);

        startBtn.setOnAction(e -> {
            if (user.getMoney() < ENTRY_FEE) {
                showAlert("Oops!", "You need at least $" + ENTRY_FEE + " to play!", Alert.AlertType.WARNING);
                return;
            }

            user.spendMoney(ENTRY_FEE);
            user.updateMoneyLabel();

            // Random question
            String[][] questions = {
                    {"What food do cats love?", "A) Milk", "B) Chocolate", "C) Onions", "A"},
                    {"How many hours do rabbits sleep daily?", "A) 2", "B) 8", "C) 12", "B"},
                    {"Which is toxic to dogs?", "A) Carrots", "B) Grapes", "C) Rice", "B"},
                    {"A cat's purr vibrates at ___ Hz?", "A) 10-20", "B) 25-150", "C) 200-300", "B"},
                    {"Dogs smell ____ times better than humans?", "A) 10", "B) 10,000", "C) 1,000,000", "B"}
            };
            int randomQ = new Random().nextInt(questions.length);
            String[] q = questions[randomQ];

            Label question = new Label(q[0]);
            question.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 20px; -fx-font-weight: bold;");

            ToggleGroup answersGroup = new ToggleGroup();

            RadioButton optionA = new RadioButton(q[1]);
            RadioButton optionB = new RadioButton(q[2]);
            RadioButton optionC = new RadioButton(q[3]);

            optionA.setToggleGroup(answersGroup);
            optionB.setToggleGroup(answersGroup);
            optionC.setToggleGroup(answersGroup);

            for (RadioButton rb : new RadioButton[]{optionA, optionB, optionC}) {
                rb.setStyle("-fx-text-fill: #dddddd; -fx-font-size: 16px;");
            }

            Button submit = new Button("Submit");
            Button back = new Button("Back");
            styleSoftButton(submit, "#4CAF50");
            styleSoftButton(back, "#b0bec5");

            HBox enter = new HBox(10, submit,back);
            enter.setAlignment(Pos.CENTER);


            VBox questionBox = new VBox(15, question, optionA, optionB, optionC, enter);
            questionBox.setAlignment(Pos.CENTER);
            gameContainer.getChildren().setAll(title, questionBox);

            submit.setOnAction(ev2 -> {
                RadioButton selectedRadio = (RadioButton) answersGroup.getSelectedToggle();
                if (selectedRadio == null) {
                    showAlert("Choose!", "Please select an answer!", Alert.AlertType.WARNING);
                    return;
                }

                String selectedAnswer = "";
                if (selectedRadio == optionA) selectedAnswer = "A";
                else if (selectedRadio == optionB) selectedAnswer = "B";
                else if (selectedRadio == optionC) selectedAnswer = "C";

                if (selectedAnswer.equalsIgnoreCase(q[4])) {
                    user.earnMoney(REWARD);
                    user.updateMoneyLabel();
                    showAlert("Correct!", "🎉 You won $" + REWARD + "!", Alert.AlertType.INFORMATION);
                } else {
                    showAlert("Wrong!", "Correct answer: " + q[4], Alert.AlertType.ERROR);
                }
                gameContainer.getChildren().setAll(menuBox);
            });

            back.setOnAction(ev2 -> gameContainer.getChildren().setAll(menuBox));
        });

        exitBtn.setOnAction(e -> stage.close());
        stage.show();
    }

    public void MemoryGame() {
        final int ENTRY_FEE = 10;
        final int REWARD = 30;
        User user = controller.getUser();

        VBox gameContainer = new VBox(25);
        gameContainer.setAlignment(Pos.CENTER);
        gameContainer.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #1f1f2e, #12121c);" +
                        "-fx-padding: 40;" +
                        "-fx-border-color: gold;" +
                        "-fx-border-width: 3;" +
                        "-fx-border-radius: 18;" +
                        "-fx-background-radius: 18;" +
                        "-fx-effect: dropshadow(gaussian, rgba(255, 215, 0, 0.3), 15, 0.4, 0, 2);"
        );

        Label title = new Label("🧠 Memory Challenge 🧠");
        title.setStyle("-fx-font-size: 28px; -fx-text-fill: gold; -fx-font-weight: bold; -fx-effect: dropshadow(gaussian, gold, 5, 0.3, 0, 1);");

        Label info = new Label(String.format("🎮 Entry: $%d   🏆 Reward: $%d", ENTRY_FEE, REWARD));
        info.setStyle("-fx-text-fill: #cccccc; -fx-font-size: 16px; -fx-font-style: italic;");

        Button startBtn = new Button("Start");
        Button exitBtn = new Button("Exit");

        styleGameButton(startBtn, "#ffcc00", "#2a2a3a");
        styleGameButton(exitBtn, "#dd4444", "#2a2a3a");

        VBox menu = new VBox(15, title, info, startBtn, exitBtn);
        menu.setAlignment(Pos.CENTER);
        gameContainer.getChildren().add(menu);

        StackPane rootPane = new StackPane(gameContainer);
        rootPane.setStyle("-fx-background-color: rgba(0, 0, 0, 0.85);");
        Scene scene = new Scene(rootPane, 460, 420);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Memory Game");
        stage.initModality(Modality.APPLICATION_MODAL);

        startBtn.setOnAction(e -> {
            if (user.getMoney() < ENTRY_FEE) {
                showAlert("Not Enough Money", "You need at least $" + ENTRY_FEE + " to play!", Alert.AlertType.WARNING);
                return;
            }

            user.spendMoney(ENTRY_FEE);
            user.updateMoneyLabel();

            Random rand = new Random();
            StringBuilder sequence = new StringBuilder();
            for (int i = 0; i < 5; i++) {
                sequence.append(rand.nextInt(10));
            }

            Label showSeq = new Label(sequence.toString());
            showSeq.setStyle("-fx-font-size: 40px; -fx-text-fill: white; -fx-font-weight: bold;");

            gameContainer.getChildren().setAll(title, showSeq);

            // Delay before hiding and prompting for input
            new Thread(() -> {
                try {
                    Thread.sleep(3000); // Show for 3 seconds
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }

                javafx.application.Platform.runLater(() -> {
                    TextField input = new TextField();
                    input.setPromptText("Enter the number...");
                    input.setMaxWidth(180);
                    input.setStyle("-fx-font-size: 18px; -fx-background-color: #222; -fx-text-fill: gold; -fx-alignment: center; -fx-border-radius: 6; -fx-background-radius: 6;");

                    Button submit = new Button("Submit");
                    Button back = new Button("Back");
                    HBox enter = new HBox(10, submit, back);
                    enter.setAlignment(Pos.CENTER);


                    styleGameButton(submit, "#4CAF50", "#1e1e2e");
                    styleGameButton(back, "#666666", "#1e1e2e");

                    VBox inputBox = new VBox(15, input, enter);
                    inputBox.setAlignment(Pos.CENTER);


                    gameContainer.getChildren().setAll(title, inputBox);

                    submit.setOnAction(ev -> {
                        if (input.getText().equals(sequence.toString())) {
                            user.earnMoney(REWARD);
                            user.updateMoneyLabel();
                            showAlert("Correct!", "🎉 You earned $" + REWARD + "!", Alert.AlertType.INFORMATION);
                        } else {
                            showAlert("Wrong!", "💔 The correct sequence was: " + sequence, Alert.AlertType.ERROR);
                        }
                        gameContainer.getChildren().setAll(menu);
                    });

                    back.setOnAction(ev -> gameContainer.getChildren().setAll(menu));
                });
            }).start();
        });

        exitBtn.setOnAction(e -> stage.close());
        stage.show();
}



    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        // Style the alert dialog
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle("-fx-background-color: #2a3a4a;");
        dialogPane.lookup(".content.label").setStyle("-fx-text-fill: white;");

        alert.showAndWait();
    }

    }

