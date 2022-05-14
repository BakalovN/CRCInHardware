package com.company;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SceneBuilder extends Application {
    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        String[] message = new String[1];
        String[] polynom = new String[1];
        TextField messageTextField = new TextField();
        messageTextField.setPromptText("Insert message");
        messageTextField.setTranslateX(0);
        messageTextField.setTranslateY(0);
        messageTextField.setPrefWidth(1500);
        messageTextField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                message[0] = messageTextField.getText();
            }
        });
        root.getChildren().add(messageTextField);
        TextField polynomTextField = new TextField();
        polynomTextField.setPromptText("Insert polynom");
        polynomTextField.setTranslateX(0);
        polynomTextField.setTranslateY(30);
        polynomTextField.setPrefWidth(1500);
        polynomTextField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                polynom[0] = polynomTextField.getText();
            }
        });
        root.getChildren().add(polynomTextField);
        ScrollPane messageScrollPane = new ScrollPane();
        VBox messageVBox = new VBox();
        messageScrollPane.setContent(messageVBox);
        messageScrollPane.setTranslateX(0);
        messageScrollPane.setTranslateY(90);
        messageScrollPane.setPrefWidth(750);
        messageScrollPane.setPrefHeight(710);
        messageVBox.getChildren().add(new Label("Sender"));
        root.getChildren().add(messageScrollPane);
        ScrollPane receiverScrollPane = new ScrollPane();
        VBox receiverVBox = new VBox();
        receiverScrollPane.setContent(receiverVBox);
        receiverScrollPane.setTranslateX(750);
        receiverScrollPane.setTranslateY(90);
        receiverScrollPane.setPrefWidth(750);
        receiverScrollPane.setPrefHeight(710);
        receiverVBox.getChildren().add(new Label("Receiver"));
        root.getChildren().add(receiverScrollPane);
        Button calculateButton = new Button("Calculate");
        calculateButton.setTranslateY(60);
        calculateButton.setTranslateX(0);
        calculateButton.setPrefWidth(1500);
        calculateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Calculations.Initialize(message[0], polynom[0], messageVBox, receiverVBox);
            }
        });
        root.getChildren().add(calculateButton);
        Scene scene = new Scene(root, 1500, 800);
        stage.setTitle("CRC in Hardware");
        stage.setScene(scene);
        stage.show();
    }
}
