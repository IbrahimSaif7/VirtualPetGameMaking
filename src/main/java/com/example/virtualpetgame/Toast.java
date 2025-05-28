package com.example.virtualpetgame;
import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class Toast {

    public static void show(StackPane root, String message) {
        Label toast = new Label(message);
        toast.setStyle(
                "-fx-background-color: rgba(0, 0, 0, 0.7);" +
                        "-fx-text-fill: white;" +
                        "-fx-padding: 10 20 10 20;" +
                        "-fx-font-size: 14px;" +
                        "-fx-background-radius: 10;"
        );
        toast.setOpacity(0);

        StackPane.setAlignment(toast, javafx.geometry.Pos.BOTTOM_CENTER);
        StackPane.setMargin(toast, new javafx.geometry.Insets(0, 0, 50, 0));

        root.getChildren().add(toast);

        FadeTransition fadeIn = new FadeTransition(Duration.millis(300), toast);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);

        FadeTransition fadeOut = new FadeTransition(Duration.millis(500), toast);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setDelay(Duration.seconds(2.5));

        fadeOut.setOnFinished(e -> root.getChildren().remove(toast));

        fadeIn.play();
        fadeOut.play();
    }
}
