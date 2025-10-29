package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    Stage window;
    Scene scene1, scene2;
    TextField nameInput;
    Label messageLabel;

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;

        // ---------- Scene 1 ----------
        Label label1 = new Label("Enter your name:");
        nameInput = new TextField();
        nameInput.setPromptText("Type your name");
        Button button1 = new Button("Go to Next Scene");

        messageLabel = new Label();

        button1.setOnAction(e -> {
            String name = nameInput.getText().trim();
            if (name.isEmpty()) {
                messageLabel.setText("Please enter your name!");
            } else {
                // Pass data to scene 2
                showSecondScene(name);
            }
        });

        VBox layout1 = new VBox(15);
        layout1.getChildren().addAll(label1, nameInput, messageLabel, button1);
        layout1.setStyle("-fx-padding: 20; -fx-alignment: center;");
        scene1 = new Scene(layout1, 400, 250);

        window.setScene(scene1);
        window.setTitle("JavaFX Multi Scene Example - DIVYA PRIYA E (2117240070077)");
        window.show();
    }

    // ---------- Scene 2 ----------
    private void showSecondScene(String name) {
        Label label2 = new Label("Welcome, " + name + "!");
        Button backButton = new Button("Go Back");

        backButton.setOnAction(e -> window.setScene(scene1));

        VBox layout2 = new VBox(15);
        layout2.getChildren().addAll(label2, backButton);
        layout2.setStyle("-fx-padding: 20; -fx-alignment: center;");
        scene2 = new Scene(layout2, 400, 250);

        window.setScene(scene2);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

