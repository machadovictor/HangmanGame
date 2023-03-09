/**
 * Final Project - Hangman Game
 * Object-Oriented Programming 2 (420-PO2-ID)
 * Student: Victor Hugo Motta Machado (ID: 653227967)
 * Instructor: Yves Desharnais
 * Date: 07 March 2023
 */
package com.example.hangmangame;

import java.net.URL;
import java.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Alert;


public class HangmanGameController implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Circle dollHead;
    @FXML
    private Rectangle dollBody;
    @FXML
    private Rectangle dollLeftArm;
    @FXML
    private Rectangle dollRightArm;
    @FXML
    private Rectangle dollLeftLeg;
    @FXML
    private Rectangle dollRightLeg;
    @FXML
    private Label playerNameLabel;
    @FXML
    private Label scoreLabel;
    @FXML
    private Label findWordLabel;
    @FXML
    private Label guessLetterLabel;
    @FXML
    private Button aButton;
    @FXML
    private Button bButton;
    @FXML
    private Button cButton;
    @FXML
    private Button dButton;
    @FXML
    private Button eButton;
    @FXML
    private Button fButton;
    @FXML
    private Button gButton;
    @FXML
    private Button hButton;
    @FXML
    private Button iButton;
    @FXML
    private Button jButton;
    @FXML
    private Button kButton;
    @FXML
    private Button lButton;
    @FXML
    private Button mButton;
    @FXML
    private Button nButton;
    @FXML
    private Button oButton;
    @FXML
    private Button pButton;
    @FXML
    private Button qButton;
    @FXML
    private Button rButton;
    @FXML
    private Button sButton;
    @FXML
    private Button tButton;
    @FXML
    private Button uButton;
    @FXML
    private Button vButton;
    @FXML
    private Button wButton;
    @FXML
    private Button xButton;
    @FXML
    private Button yButton;
    @FXML
    private Button zButton;

    /**
     * Private field that represents a list of strings representing the letters guessed in a game.
     */
    @FXML
    private List<String> lettersGuessed = new ArrayList<>();

    /**
     * Array of char containing the secret word of the game.
     */
    private char[] secretWord;
    private int score = 0;
    char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    String[] possibleWords = new String[]{"Brazil", "Canada", "France", "Germany", "Italy", "Japan", "China", "India", "Russia", "Australia", "Mexico", "Argentina", "Spain", "Sweden", "Norway", "Netherlands", "Switzerland", "Indonesia", "Thailand", "Egypt", "Turkey", "Iran", "Iraq", "Israel", "Palestine", "Greece", "Belgium", "Austria", "Poland", "Ukraine"};

    public HangmanGameController() {
    }

    /**
     * Method that initializes the Hangman game by hiding the body parts, hiding and showing the guessed letters,
     * and disabling all the letters on the keyboard.
     */
    public void initialize(URL url, ResourceBundle rb) {
        this.hideBodyParts();
        this.hideAndShowGuessLetter();
        this.isDisableAllLetters(true);

        /**
         * Set up a map of characters to buttons for easy access to the keyboard buttons.
         */
        Map<Character, Button> buttons = new HashMap<>();

        buttons.put('a', aButton);
        buttons.put('b', bButton);
        buttons.put('c', cButton);
        buttons.put('d', dButton);
        buttons.put('e', eButton);
        buttons.put('f', fButton);
        buttons.put('g', gButton);
        buttons.put('h', hButton);
        buttons.put('i', iButton);
        buttons.put('j', jButton);
        buttons.put('k', kButton);
        buttons.put('l', lButton);
        buttons.put('m', mButton);
        buttons.put('n', nButton);
        buttons.put('o', oButton);
        buttons.put('p', pButton);
        buttons.put('q', qButton);
        buttons.put('r', rButton);
        buttons.put('s', sButton);
        buttons.put('t', tButton);
        buttons.put('u', uButton);
        buttons.put('v', vButton);
        buttons.put('w', wButton);
        buttons.put('x', xButton);
        buttons.put('y', yButton);
        buttons.put('z', zButton);

        /**
         * Add a key press listener to the anchorPane that responds to key presses by finding the corresponding keyboard
         * button and triggering its fire method, or showing an error alert if the key pressed is not a valid letter.
         */
        anchorPane.setOnKeyPressed(e -> {
           Button button = buttons.get(e.getText().charAt(0));

           if(button != null) {
               button.arm();
               button.fire();
           } else {
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("Invalid Input");
               alert.setHeaderText(null);
               alert.setContentText("Chosen character is invalid! (" + e.getText() + ")");
               alert.showAndWait();
           }
        });
    }

    /**
     * Method to hide or show body parts
     */
    public void hideBodyParts() {
        this.dollHead.setVisible(false);
        this.dollBody.setVisible(false);
        this.dollLeftArm.setVisible(false);
        this.dollRightArm.setVisible(false);
        this.dollLeftLeg.setVisible(false);
        this.dollRightLeg.setVisible(false);
    }

    /**
     * Method to hide or show the secret word
     */
    public void hideAndShowGuessLetter() {
        this.guessLetterLabel.setVisible(false);
    }


    /**
     * Method that is called when the user clicks the "Start Game" button.
     */
    @FXML
    public void startGame() {
        // Prompt the user to enter their name using a TextInputDialog, and set the player's name
        TextInputDialog name = new TextInputDialog("");
        name.setTitle("Player Name");
        name.setHeaderText("Player Name");
        name.setContentText("Please enter your name:");
        name.showAndWait();
        this.playerNameLabel.setText(name.getEditor().getText());
        // Reset the player's score to 0 and set the score label to 0.
        this.score = 0;
        this.scoreLabel.setText(String.valueOf(score));
        // Select a random word for the player to guess, and set the visibility of the guessLetterLabel
        // to true to show the player where to enter their guesses.
        this.getRandom();
        this.guessLetterLabel.setVisible(true);
        // Hide the hangman body parts and enable all the letter buttons on the keyboard for the player to use.
        hideBodyParts();
        isDisableAllLetters(false);
    }

    /**
     * Meethod used to randomly select a word for the player to guess from the possibleWords array.
     * It first creates a new instance of the Random class to generate a random index between 0 and the length of
     * the possibleWords array.
     */
    @FXML
    public void getRandom() {
        // Generate a random index between 0 and the length of the possibleWords array, and set the secretWord field
        // to the randomly selected word in uppercase format as a character array.
        Random random = new Random();
        int size = this.possibleWords.length;
        int wordIndex = random.nextInt(size);
        String wordToBeDiscover = this.possibleWords[wordIndex];
        this.secretWord = wordToBeDiscover.toUpperCase().toCharArray();

        // Create a new StringBuilder instance to create a new string of asterisks representing the letters in the secretWord.
        StringBuilder asteriskWord = new StringBuilder();
        for (int i = 0; i < this.secretWord.length; i++) {
            // Append an asterisk to the asteriskWord StringBuilder for each character in the word.
            asteriskWord.append("*");
        }
        // Set the text of the findWordLabel to the asteriskWord string, which displays the word as a series of asterisks
        // to be guessed by the player.
        this.findWordLabel.setText(asteriskWord.toString());
    }

    /**
     This method is called when a letter button is clicked in the Hangman game.
     It retrieves the clicked button, disables it, and determines the letter clicked.
     It then checks if the letter is present in the secret word, updates the displayed word and the score accordingly.
     If the letter is not present in the secret word, it adds it to the list of guessed letters and displays the next
     part of the hangman. If the displayed word is completely revealed, it updates the score and displays a "You won" message.
     If the list of guessed letters reaches seven, it displays a "Game over" message with the secret word and starts a new game.
     @param event the ActionEvent representing the button click
     */

    public void letterButtonClicked(ActionEvent event) {
        Button button = (Button) event.getSource();
        button.setDisable(true);
        char letter = button.getText().charAt(0);
        StringBuilder wordDisplayed = new StringBuilder(this.findWordLabel.getText());
        boolean letterFound = false;

        for (int i = 0; i < this.secretWord.length; i++) {
            if(this.secretWord[i] == letter) {
                wordDisplayed.setCharAt(i, letter);
                letterFound = true;
            }
        }

        if (letterFound) {
            this.score++;
            this.scoreLabel.setText(String.valueOf(score));
            this.findWordLabel.setText(wordDisplayed.toString());
        } else {
            this.lettersGuessed.add(String.valueOf(letter));
            switch (this.lettersGuessed.size()) {
                case 1:
                    this.dollHead.setVisible(true);
                    break;
                case 2:
                    this.dollBody.setVisible(true);
                    break;
                case 3:
                    this.dollLeftArm.setVisible(true);
                    break;
                case 4:
                    this.dollRightArm.setVisible(true);
                    break;
                case 5:
                    this.dollLeftLeg.setVisible(true);
                    break;
                case 6:
                    this.dollRightLeg.setVisible(true);
                    break;
                case 7:
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Game Over");
                    alert.setHeaderText("Game Over!");
                    alert.setContentText("The Secret Word was " + String.valueOf(secretWord) + "!");
                    alert.showAndWait();
                    startGame();
                    return;
            }
        }

        if (!wordDisplayed.toString().contains("*")) {
            this.score += 5;
            this.scoreLabel.setText(String.valueOf(score));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Game Over");
            alert.setHeaderText("Game Over");
            alert.setContentText("You won the game!");
            alert.showAndWait();
            resetGame();
        }
    }

    private void resetGame() {
        this.getRandom();
        hideBodyParts();
        this.lettersGuessed.clear();
        isDisableAllLetters(false);
    }

    /**
     * Method to disable the buttons
     * @param isDisable
     */
    private void isDisableAllLetters(Boolean isDisable) {
        aButton.setDisable(isDisable);
        bButton.setDisable(isDisable);
        cButton.setDisable(isDisable);
        dButton.setDisable(isDisable);
        eButton.setDisable(isDisable);
        fButton.setDisable(isDisable);
        gButton.setDisable(isDisable);
        hButton.setDisable(isDisable);
        iButton.setDisable(isDisable);
        jButton.setDisable(isDisable);
        kButton.setDisable(isDisable);
        lButton.setDisable(isDisable);
        mButton.setDisable(isDisable);
        nButton.setDisable(isDisable);
        oButton.setDisable(isDisable);
        pButton.setDisable(isDisable);
        qButton.setDisable(isDisable);
        rButton.setDisable(isDisable);
        sButton.setDisable(isDisable);
        tButton.setDisable(isDisable);
        uButton.setDisable(isDisable);
        vButton.setDisable(isDisable);
        xButton.setDisable(isDisable);
        zButton.setDisable(isDisable);
        wButton.setDisable(isDisable);
        yButton.setDisable(isDisable);
    }
}



