package com.company;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Calculations {

    public static void Initialize(String messageInput, String polynomInput, VBox sender, VBox receiver){
        Label messageLabel = new Label();
        String messageString = messageInput;
        List<String> message = Arrays.stream(messageInput.split("")).collect(Collectors.toList());
        List<String> divisor = Arrays.stream(polynomInput.split("")).collect(Collectors.toList());
        for (int i = 0; i < divisor.size()-1; i++) {
            message.add("0");
            messageString = messageString.concat("0");
        }
        messageLabel.setText(messageString);
        sender.getChildren().add(messageLabel);
        List<String> messageToCalculate = new ArrayList<>();
        int countTimes = 0;
        for (int i = countTimes; i < divisor.size()+countTimes; i++) {
            messageToCalculate.add(message.get(i));
        }
        message = sender(countTimes, message, divisor, messageToCalculate, sender);
        countTimes = 0;
        messageString = "";
        for (int i = 0; i < message.size(); i++) {
            messageString = messageString.concat(message.get(i));
        }
        receiver.getChildren().add(new Label(messageString));
        messageToCalculate = new ArrayList<>();
        for (int i = countTimes; i < divisor.size()+countTimes; i++) {
            messageToCalculate.add(message.get(i));
        }
        message = receiver(countTimes, message, divisor, messageToCalculate, receiver);
        for (int i = 0; i < message.size(); i++) {
            System.out.print(message.get(i));
        }
    }
    public static String xor(String message, String divisor){
        if (message.equals(divisor)){
            return "0";
        }else {
            return "1";
        }
    }

    public static List<String> sender(int countTimes, List<String> message, List<String> divisor, List<String> messageToCalculate, VBox sender){
        int pixels = 0;
        String divisorString = "";
        for (int i = 0; i < divisor.size(); i++) {
            divisorString = divisorString.concat(divisor.get(i));
        }
        while (true){
            Label resultLabel = new Label();
            Label divisorLabel = new Label(divisorString);
            Label messageToCalculateLabel = new Label();
            String messageToCalculateForLabel = "";
            String resultString = "";
            if (!messageToCalculate.get(0).equals("0")){
                for (int i = 0; i < messageToCalculate.size(); i++) {
                    messageToCalculateForLabel = messageToCalculateForLabel.concat(messageToCalculate.get(i));
                }

                for (int i = 0; i < divisor.size(); i++) {
                    messageToCalculate.set(i, xor(messageToCalculate.get(i), divisor.get(i)));
                    resultString = resultString.concat(messageToCalculate.get(i));
                }
                messageToCalculateLabel.setText(messageToCalculateForLabel);
                messageToCalculateLabel.setTranslateX(pixels);
                divisorLabel.setTranslateX(pixels);
                resultLabel.setTranslateX(pixels);
                resultLabel.setText(resultString);
                sender.getChildren().add(messageToCalculateLabel);
                sender.getChildren().add(divisorLabel);
                sender.getChildren().add(resultLabel);
            }else {
                countTimes++;
                messageToCalculate.remove(0);
                messageToCalculate.add(message.get(divisor.size()+countTimes-1));
                pixels = pixels + 6;
            }
            if (countTimes==message.size()-divisor.size()){
                messageToCalculateForLabel = "";
                resultString = "";
                for (int i = 0; i < messageToCalculate.size(); i++) {
                    messageToCalculateForLabel = messageToCalculateForLabel.concat(messageToCalculate.get(i));
                }
                messageToCalculateLabel.setTranslateX(pixels);
                messageToCalculateLabel.setText(messageToCalculateForLabel);
                divisorLabel.setTranslateX(pixels);
                sender.getChildren().add(messageToCalculateLabel);
                sender.getChildren().add(divisorLabel);
                for (int i = 0; i < divisor.size(); i++) {
                    messageToCalculate.set(i, xor(messageToCalculate.get(i), divisor.get(i)));
                    if (i!=0){
                        resultString = resultString.concat(messageToCalculate.get(i));
                    }
                }
                resultLabel.setTranslateX(pixels+6);
                resultLabel.setText(resultString.concat("->CRC"));
                sender.getChildren().add(resultLabel);
                messageToCalculate.remove(0);
                for (int i = messageToCalculate.size()-1; i >= 0 ; i++) {
                    if (messageToCalculate.get(i).equals("0")) {
                        messageToCalculate.remove(i);
                    } else {
                        break;
                    }
                }
                for (int i = 0; i < divisor.size()-1; i++) {
                    message.remove(message.size()-1);
                }
                for (int i = 0; i < messageToCalculate.size(); i++) {
                    message.add(messageToCalculate.get(i));
                }
                break;
            }

        }
        return message;
    }

    public static List<String> receiver (int countTimes, List<String> message, List<String> divisor, List<String> messageToCalculate, VBox receiver){
        int pixels = 0;
        String divisorString = "";
        for (int i = 0; i < divisor.size(); i++) {
            divisorString = divisorString.concat(divisor.get(i));
        }
        while (true){
            Label resultLabel = new Label();
            Label divisorLabel = new Label(divisorString);
            Label messageToCalculateLabel = new Label();
            String messageToCalculateForLabel = "";
            String resultString = "";
            if (!messageToCalculate.get(0).equals("0")){
                for (int i = 0; i < messageToCalculate.size(); i++) {
                    messageToCalculateForLabel = messageToCalculateForLabel.concat(messageToCalculate.get(i));
                }
                for (int i = 0; i < divisor.size(); i++) {
                    messageToCalculate.set(i, xor(messageToCalculate.get(i), divisor.get(i)));
                    resultString = resultString.concat(messageToCalculate.get(i));
                }
                messageToCalculateLabel.setText(messageToCalculateForLabel);
                messageToCalculateLabel.setTranslateX(pixels);
                divisorLabel.setTranslateX(pixels);
                resultLabel.setTranslateX(pixels);
                resultLabel.setText(resultString);
                receiver.getChildren().add(messageToCalculateLabel);
                receiver.getChildren().add(divisorLabel);
                receiver.getChildren().add(resultLabel);
            }else {
                countTimes++;
                messageToCalculate.remove(0);
                messageToCalculate.add(message.get(divisor.size()+countTimes-1));
                pixels = pixels + 6;
            }
            if (countTimes==message.size()-divisor.size()){
                messageToCalculateForLabel = "";
                resultString = "";
                for (int i = 0; i < messageToCalculate.size(); i++) {
                    messageToCalculateForLabel = messageToCalculateForLabel.concat(messageToCalculate.get(i));
                }
                messageToCalculateLabel.setTranslateX(pixels);
                messageToCalculateLabel.setText(messageToCalculateForLabel);
                divisorLabel.setTranslateX(pixels);
                receiver.getChildren().add(messageToCalculateLabel);
                receiver.getChildren().add(divisorLabel);
                for (int i = 0; i < divisor.size(); i++) {
                    messageToCalculate.set(i, xor(messageToCalculate.get(i), divisor.get(i)));
                    if (i!=0){
                        resultString = resultString.concat(messageToCalculate.get(i));
                    }
                }
                resultLabel.setTranslateX(pixels+6);
                resultLabel.setText(resultString);
                receiver.getChildren().add(resultLabel);
                messageToCalculate.remove(0);
                int nullCounter = 0;
                String errors = "";

                for (int i = messageToCalculate.size()-1; i >= 0 ; i--) {
                    if (messageToCalculate.get(i).equals("0")) {
                        nullCounter++;
                    }
                }
                if (nullCounter==divisor.size()-1){
                    errors = "no transmission errors";
                    for (int i = 0; i < divisor.size()-1; i++) {
                        message.remove(message.size()-1);
                    }
                    receiver.getChildren().add(new Label(errors));
                }else{
                    errors = "transmission error";
                    receiver.getChildren().add(new Label(errors));
                }

                //for (int i = 0; i < messageToCalculate.size(); i++) {
                //    message.add(messageToCalculate.get(i));
                //}
                break;
            }
        }
        return message;
    }
}
