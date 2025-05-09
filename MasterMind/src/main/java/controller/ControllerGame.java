package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;
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
    // To store the user's previous guesses
    private Set<String> previousGuesses;

    public ControllerGame(ViewGame view, ModelGame model) {
        this.view = view;
        this.model = model;
        this.view.createView(model.getLength(), model.getMaxTries());
        this.view.setController(this);
        // Initialize the set of previous guesses
        this.previousGuesses = new HashSet<>();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char enteredChar = e.getKeyChar();
        String currentInput = view.getUserDigits();

        // Check if the entered character is a digit and not already present in the current input
        if (Character.isDigit(enteredChar) && !currentInput.contains(String.valueOf(enteredChar)) && currentInput.length() < model.getLength()) {
            view.updateValue(enteredChar);
        } else if (Character.isDigit(enteredChar) && currentInput.contains(String.valueOf(enteredChar))) {
            // SHOW MESSAGE WHEN TRYING TO ENTER A DUPLICATE DIGIT WHILE TYPING
            JOptionPane.showMessageDialog(view, "You cannot enter the same digit twice.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
        }
        e.consume(); // Consume the event to prevent further processing
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
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        System.out.println("Action received: " + command);

        if (command.equals("back")) {
            if (!model.isGameStarted() || view.playerChoice("Confirmation", "Are you sure you want to go back?")) {
                SwingUtilities.invokeLater(() -> {
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
            // Check for duplicate digits in the current guess
            Set<Character> uniqueDigits = new HashSet<>();
            for (char c : guess.toCharArray()) {
                if (!uniqueDigits.add(c)) {
                    view.clearInputFields();
                    JOptionPane.showMessageDialog(
                            view,
                            "The digits in your guess must be unique.",
                            "Invalid Input",
                            JOptionPane.WARNING_MESSAGE
                    );
                    // Don't proceed if there are duplicate digits
                    return;
                }
            }

            if (previousGuesses.contains(guess)) {
                view.clearInputFields();
                JOptionPane.showMessageDialog(
                        view,
                        "You have already tried this number.",
                        "Invalid Input",
                        JOptionPane.WARNING_MESSAGE
                );
                // Don't proceed if the guess was made before
                return;
            }

            if (!model.isGameStarted()) {
                model.startGame();
            }
            model.consumeTry();
            view.setTriesLeftText(model.getTriesLeft());
            view.clearInputFields();

            String[] feedbackInfo = model.feedbackInfo(guess);
            // DISPLAY FEEDBACK FOR THE CURRENT TRY ONLY (WHICH WILL BE THE FIRST AVAILABLE ROW)
            view.displayFeedback(model.getMaxTries() - model.getTriesLeft() - 1, guess, feedbackInfo);
            // Add the current guess to the set of previous guesses
            previousGuesses.add(guess);

            if (model.hitsSamePlace(guess) == model.getLength()) {
                this.finishGame(true); // the user win
            } else if (model.getTriesLeft() == 0) {
                this.finishGame(false); // the user lost
            }
        } else if (guess.length() != model.getLength()) {
            view.clearInputFields();
            JOptionPane.showMessageDialog(
                    view,
                    "Please enter " + model.getLength() + " unique digits.", // Updated message
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE
            );
        } else if (!guess.matches("[0-9]+")) {
            view.clearInputFields();
            JOptionPane.showMessageDialog(
                    view,
                    "Only digits are allowed.",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE
            );
        }

    }

    private void finishGame(boolean won) {
        model.finishGame();
        // Only update the score if playing difficult mode
        if (model.getLength() == 5) {
            model.updateScore(won);
        }
        String message = won ? "You guessed correctly!👏 \n" + (model.getLength() == 5 ? "You got a score of " + model.getScore() + "\n" : "") + "Play again?" : "You ran out of tries!😢😢 The number was " + model.getNumberToGuess() + ". Play again?";
        String title = won ? "Congratulations!🎉🎉🎉" : "Game Over";
        boolean continuePlaying = view.playerChoice(title, message);
        if (model.getLength() == 5) {
            String playerName = view.getPlayerName();
            model.updateHighScores(playerName);
        }
        resetGame();
        if (!continuePlaying) {
            SwingUtilities.invokeLater(() -> {
                view.dispose();
                ControllerIndex controllerIndex = new ControllerIndex(new ViewIndex());
            });
        }
    }

    private void showGoodbyeMessage() {
        JOptionPane.showMessageDialog(view, "Thank you for playing Mastermind! See you soon. 👋", "Goodbye!", JOptionPane.INFORMATION_MESSAGE);
    }

    public void resetGame() {
        model.resetGame(); // Use resetGame of ModelGame -> generate new number, triesLeft = maxTries, gameFinished = false, attemptHistoy.clear()
        view.clearPreviousTries();
        view.setTriesLeftText(model.getMaxTries());
        view.enableInputs();
        // Clear the set of previous guesses for a new game
        previousGuesses.clear();
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
