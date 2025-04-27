package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import model.ModelGame;
import view.ViewDifficulty;
import view.ViewGame;
import view.ViewIndex;

/**
 *
 * @author Silvia García Bouza
 * @author Nuria Calo Mosquera
 * @author Alfonso Gallego Fernández
 */
public class ControllerGame implements ActionListener, KeyListener {
    ViewGame view;
    ModelGame model;

    public ControllerGame(ViewGame view, ModelGame model) {
        this.view = view;
        this.model = model;
        this.view.createView(model.getLength(), model.getMaxTries());
        this.view.setController(this);
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        view.updateValue(e.getKeyChar());
        e.consume();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER && !model.isGameFinished()) {
            handleSubmitLogic();
        }
        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            view.deleteOneUserInput();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        System.out.println("Action received: "+command);
        
        if (command.equals("back")) {
            if (!model.isGameStarted() || view.playerChoice("Confirmation", "Are you sure you want to go back?")) {
                SwingUtilities.invokeLater( () -> {
                    view.dispose();
                    ControllerDifficulty controllerDifficulty = new ControllerDifficulty(new ViewDifficulty());
                });
            }
        }

        if (!model.isGameFinished() && command.equals("submit")) {
            handleSubmitLogic();
        }
    }
    
    private void handleSubmitLogic() {
        String guess = view.getUserDigits();

        if (guess.length() == model.getLength() && guess.matches("[0-9]+")) {
            if (!model.isGameStarted()) model.startGame();
            model.consumeTry();
            view.setTriesLeftText(model.getTriesLeft()); 
            view.clearInputFields();

            String[] feedbackInfo = model.feedbackInfo(guess);
            view.displayFeedback(model.getMaxTries() - model.getTriesLeft() - 1, guess, feedbackInfo); 

            if (model.hitsSamePlace(guess) == model.getLength()) {
                this.finishGame(true); // the user win
            } else if (model.getTriesLeft() == 0) {
                this.finishGame(false); // the user lost
            }
        } else {
            view.clearInputFields();
            JOptionPane.showMessageDialog(
                view,
                "Please enter " + model.getLength() + " digits.",
                "Invalid Input",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void finishGame(boolean won) {
        model.finishGame();
        if (model.getLength() == 5) model.updateScore(won);
        String message = won ? "You guessed correctly!👏 \n"+(model.getLength() == 5 ? "You got a score of "+model.getScore()+"\n" : "")+"Play again?" : "You ran out of tries!😢😢 The number was " + model.getNumberToGuess() + ". Play again?";
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
    }
    
    public void resetGame() {
        model.resetGame(); // Use  resetGame of ModelGame -> generate new number, triesLeft = maxTries, gameFinished = false, attemptHistoy.clear()
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
