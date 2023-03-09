/**
 * Final Project - Hangman Game
 * Object-Oriented Programming 2 (420-PO2-ID)
 * Student: Victor Hugo Motta Machado (ID: 653227967)
 * Instructor: Yves Desharnais
 * Date: 07 March 2023
 */
package com.example.hangmangame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HangmanGame extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("HangmanGame.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

