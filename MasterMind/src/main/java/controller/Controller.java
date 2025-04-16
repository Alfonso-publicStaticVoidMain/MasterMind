/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Model;
import view.View;


/**
 *
 * @author silvia
 */
public class Controller implements ActionListener {
    //view y model
    View view;
    Model model;
    //otras variables
    private int length;
    private int maxTries;
    private int triesLeft;
    private boolean isGameFinished;
    

    public Controller(View view, Model model) {
        //valor a view y a model
        
        this.model = model;
       
        //valor resto de variables
        this.length=model.getLength();
        this.maxTries=model.getMaxTries();
        this.triesLeft=model.getTriesLeft();
        this.isGameFinished=model.isGameFinished();
        
         this.view = view;
        //conectamos la view cn el controler
        this.view.setActionListener(this);
        //inicial el cont de intentos
       
        
    
    }

     @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (!this.isGameFinished && command.equals("submit")) {
            String guess = view.getUserDigits();

            if (guess.length() == model.getLength() && guess.matches("[0-9]+")) {
                this.model.consumeTry();
                view.setTriesLeftText();
                view.clearInputFields();

                String[] feedbackInfo = this.model.feedbackInfo(guess);
                view.displayFeedback(guess, feedbackInfo); // Pasar feedback a vista

                // **CONDICION DE GANAR**
                if (this.model.hitsSamePlace(guess) == this.length) {
                    this.finishGame();
                    int choice = JOptionPane.showConfirmDialog(
                            this.view,
                            "You guessed the number correctly. Congrats! Do you want to play again?",
                            "End of the game",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    if (choice == JOptionPane.YES_OPTION) {
                        resetGame(true); // ✅ Pass 'true' because the user won
                    } else {
                        view.disableInputs();
                        showGoodbyeMessage(); //Show the goodbyw message
                    }

                }

                // **CONDICION DE PERDER **
                if (!this.isGameFinished && this.triesLeft == 0) {
                    this.finishGame();
                    int choice = JOptionPane.showConfirmDialog(
                            this.view,
                            "You have no tries left. You lost! Do you want to play again?",
                            "Game Over",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    if (choice == JOptionPane.YES_OPTION) {
                        resetGame(false); // ✅ Pass 'false' because the user lost
                    } else {
                        view.disableInputs();
                        showGoodbyeMessage(); // 🚀 NEW: Show the goodbye message
                    }
                }
            }
        } else {
            view.clearInputFields();
        }
    }

    
     // **🚀 Added Method to Show Goodbye Message and Exit**

    private void showGoodbyeMessage() {
        JOptionPane.showMessageDialog(
                this.view,
                "Thank you for playing Mastermind! See you soon. 👋",
                "Goodbye!",
                JOptionPane.INFORMATION_MESSAGE
        );

        // 🚀 Ensure the message is displayed before closing
        javax.swing.SwingUtilities.invokeLater(() -> System.exit(0));
    }

    //Resetear xogo
    public void resetGame(boolean won) {
        String playerName = view.getPlayerName(); // Get player's name
        model.updateScore(won); // Update score before reset
        model.updateHighScores(playerName); // 🚀 Add name + score to leaderboard
        view.showLeaderboard(model.getPlayerNames(), model.getHighScores()); // 🚀 Display leaderboard
        model.startNewGame();
        view.setScoreText(model.getScore());
        view.clearPreviousTries();
        view.setTriesLeftText(model.getMaxTries());
        view.enableInputs();
    }
    
     public void finishGame() {
        this.model.finishGame();
    }
    
    public int getLength() {
        return length;
    }

    public int getMaxTries() {
        return maxTries;
    }

    public int getTriesLeft() {
        return triesLeft;
    }
    
    
    
    

}
