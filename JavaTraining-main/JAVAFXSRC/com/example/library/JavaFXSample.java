package com.example.library;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class JavaFXSample extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a label
        Label label = new Label("Hello, JavaFX!");

        // Create a button
        Button button = new Button("Click Me!");
        button.setOnAction(event -> label.setText("Button Clicked!"));

        // Create a layout pane and add the label and button
        StackPane root = new StackPane();
        root.getChildren().addAll(label, button);

        // Create a scene with the layout pane
        Scene scene = new Scene(root, 300, 200);

        // Set the scene and show the stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX Sample");
        primaryStage.show();
    }

    public static void main(String[] args) {
//        launch(args);
    	System.out.println("heii");
    }
}
