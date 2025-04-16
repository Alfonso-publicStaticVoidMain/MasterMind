/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.ModelGame;
import view.ViewGame;


/**
 *
 * @author silvia
 */
public class ControllerGame implements ActionListener {
    //view y model
    ViewGame view;
    ModelGame model;
    private int attemptsMade = 0; // Para controlar la fila en displayFeedback
    //otras variables
//    private int length;
//    private int maxTries;
//    private int triesLeft;
//    private boolean isGameFinished;
    

    public ControllerGame(ViewGame view, ModelGame model) {
        this.view = view;
        this.model = model;
        model.getLength();
        this.view.createView(this.model.getLength(), this.model.getMaxTries());
        this.view.setActionListener(this);
        this.view.setTriesLeftText(this.model.getMaxTries()); // Inicializar intentos en la vista
        this.view.setScoreText(this.model.getScore()); // Inicializar el score en la vista
    }

      @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (!this.model.isGameFinished() && command.equals("submit")) {
            String guess = view.getUserDigits();

            if (guess.length() == model.getLength() && guess.matches("[0-9]+")) {
                this.model.consumeTry();
                view.setTriesLeftText(this.model.getTriesLeft()); // Actualizar intentos restantes en la vista
                view.clearInputFields();

                String[] feedbackInfo = this.model.feedbackInfo(guess);
                view.displayFeedback(this.attemptsMade, guess, feedbackInfo); // Pasar número de intento

                this.attemptsMade++; // Incrementar el contador de intentos

                if (this.model.hitsSamePlace(guess) == this.model.getLength()) {
                    this.finishGame(true); // El jugador ganó
                } else if (this.model.getTriesLeft() == 0) {
                    this.finishGame(false); // El jugador perdió
                }
            } else {
                view.clearInputFields();
                JOptionPane.showMessageDialog(view, "Please enter " + model.getLength() + " digits.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void finishGame(boolean won) {
        this.model.finishGame();
        String message = won ? "You guessed correctly! Play again?" : "You ran out of tries! The number was " + model.getNumberToGuess() + ". Play again?";
        String title = won ? "Congratulations!" : "Game Over";
        int choice = JOptionPane.showConfirmDialog(view, message, title, JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            resetGame();
        } else {
            view.disableInputs();
            showGoodbyeMessage();
        }
    }
    
    
    private void showGoodbyeMessage() {
        JOptionPane.showMessageDialog(view, "Thank you for playing Mastermind! See you soon. 👋", "Goodbye!", JOptionPane.INFORMATION_MESSAGE);
       // javax.swing.SwingUtilities.invokeLater(System::exit);
    }

    public void resetGame() {
        boolean won = model.isGameFinished(); // Guardar el estado del juego anterior
        String playerName = view.getPlayerName();
        model.updateScore(won);
        model.updateHighScores(playerName);
        view.showLeaderboard(model.getPlayerNames(), model.getHighScores());
        model.resetGame(); // Usar el método resetGame del ModelGame
        view.setScoreText(model.getScore());
        view.clearPreviousTries();
        view.setTriesLeftText(model.getMaxTries());
        view.enableInputs();
        this.attemptsMade = 0; // Resetear el contador de intentos
    }

    public int getLength() {
        return model.getLength();
    }

    public int getMaxTries() {
        return model.getMaxTries();
    }

    public int getTriesLeft() {
        return model.getTriesLeft();
    }

    
    
    

}
