package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import model.ModelGame;
import view.ViewDifficulty;
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

    public ControllerGame(ViewGame view, ModelGame model) {
        this.view = view;
        this.model = model;
        this.view.createView(model.getLength(), model.getMaxTries());
        this.view.setActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        System.out.println("Action received: "+command);
        
        if (command.equals("back")) {
            // TO DO confirmación
            SwingUtilities.invokeLater( () -> {
                view.dispose();
                ControllerDifficulty controllerDifficulty = new ControllerDifficulty(new ViewDifficulty());
            });
        }

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
        model.finishGame();
        model.updateScore(won);
        String message = won ? "You guessed correctly!👏 \nYou got a score of "+model.getScore()+"\nPlay again?" : "You ran out of tries!😢😢 The number was " + model.getNumberToGuess() + ". Play again?";
        String title = won ? "Congratulations!🎉🎉🎉" : "Game Over";
        boolean continuePlaying = view.playerChoice(title, message);
        if (model.getLength() == 5) {
            String playerName = view.getPlayerName();       
            model.updateHighScores(playerName);
        }
        resetGame();
        if (!continuePlaying) {
            SwingUtilities.invokeLater( () -> {
                view.dispose();
            ControllerIndex controllerIndex = new ControllerIndex(new ViewIndex());
            });
        }
    }
    
    
    private void showGoodbyeMessage() {
        JOptionPane.showMessageDialog(view, "Thank you for playing Mastermind! See you soon. 👋", "Goodbye!", JOptionPane.INFORMATION_MESSAGE);
       // javax.swing.SwingUtilities.invokeLater(System::exit);
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
