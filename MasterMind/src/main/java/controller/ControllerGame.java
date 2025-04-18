package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.ModelGame;
import view.ViewGame;
import view.ViewIndex;


/**
 *
 * @author silvia
 */
public class ControllerGame implements ActionListener {
    //view y model
    ViewGame view;
    ModelGame model;
    ViewIndex viewIndex;

    public ControllerGame(ViewGame view, ModelGame model, ViewIndex viewIndex) {
        this.view = view;
        this.model = model;
        this.viewIndex = viewIndex;
        this.view.createView(this.model.getLength(), this.model.getMaxTries());
        this.view.setActionListener(this);
        //A: O método de abaixo está comentado porque agora faise tamén dentro de createView()
        //this.view.setTriesLeftText(this.model.getMaxTries()); // Inicializar intentos en la vista
        this.view.setScoreText(this.model.getScore()); // Inicializar el score en la vista
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (!model.isGameFinished() && command.equals("submit")) {
            String guess = view.getUserDigits();

            if (guess.length() == model.getLength() && guess.matches("[0-9]+")) {
                model.consumeTry();
                view.setTriesLeftText(model.getTriesLeft()); // Actualizar intentos restantes en la vista
                view.clearInputFields();

                String[] feedbackInfo = model.feedbackInfo(guess);
                view.displayFeedback(this.getMaxTries() - this.getTriesLeft() - 1, guess, feedbackInfo); // Pasar número de intento

                if (model.hitsSamePlace(guess) == model.getLength()) {
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
        this.model.finishGame(); // También setea la score a 0.
        String message = won ? "You guessed correctly! Play again?" : "You ran out of tries! The number was " + model.getNumberToGuess() + ". Play again?";
        String title = won ? "Congratulations!" : "Game Over";
        int choice = JOptionPane.showConfirmDialog(view, message, title, JOptionPane.YES_NO_OPTION);
        recordScore();
        resetGame();
        if (choice == JOptionPane.NO_OPTION) {
            view.setVisible(false);
            viewIndex.setVisible(true);
        }
        
//        if (choice == JOptionPane.YES_OPTION) {
//            resetGame();
//        } else {
//            view.disableInputs();
//            showGoodbyeMessage();
//        }
    }
    
    
    private void showGoodbyeMessage() {
        JOptionPane.showMessageDialog(view, "Thank you for playing Mastermind! See you soon. 👋", "Goodbye!", JOptionPane.INFORMATION_MESSAGE);
       // javax.swing.SwingUtilities.invokeLater(System::exit);
    }

    public void recordScore() {
        boolean won = model.isGameFinished(); // Guardar el estado del juego anterior
        String playerName = view.getPlayerName();
        model.updateScore(won);
        model.updateHighScores(playerName);
        view.setScoreText(model.getScore());
    }
    
    public void resetGame() {
        model.resetGame(); // Usar el método resetGame del ModelGame -> genera un nuevo número, triesLeft = maxTries, gameFinished = false, attemptHistoy.clear()
        view.clearPreviousTries();
        view.setTriesLeftText(model.getMaxTries());
        view.enableInputs();
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
