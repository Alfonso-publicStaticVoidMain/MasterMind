package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.MasterMindModel;
import view.MasterMindView;

/**
 *
 * @author Silvia García Bouza
 * @author Nuria Calo Mosquera
 * @author Alfonso Gallego Fernández
 */
public class MasterMindController implements ActionListener {

    private MasterMindView view;
    private MasterMindModel model;

    public MasterMindController(MasterMindView view, MasterMindModel model) {
        this.view = view;
        this.model = model;

        // Set initial game parameters
        this.model.setLength(this.view.getLength());
        this.model.setMaxTries(this.view.getMaxTries());

        // Connect Controller with View
        this.view.setController(this);
        //inicial el cont de intentos
        this.view.setTriesLeftText(this.triesLeft());
    }
    
    public MasterMindModel model() {
        return this.model;
    }

    public int maxTries() {
        return this.model.getMaxTries();
    }

    public int triesLeft() {
        return this.model.getTriesLeft();
    }

    public int length() {
        return this.model.getLength();
    }

    public boolean isGameFinished() {
        return this.model.isGameFinished();
    }

    public void finishGame() {
        this.model.finishGame();
    }

    //TODO
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (!this.isGameFinished() && command.equals("submit")) {
            String guess = view.getUserDigits();

            if (guess.length() == model.getLength() && guess.matches("[0-9]+")) {
                this.model.consumeTry();
                view.setTriesLeftText(this.triesLeft());
                view.clearInputFields();

                String[] feedbackInfo = this.model.feedbackInfo(guess);
                view.displayFeedback(guess, feedbackInfo); // Pasar feedback a vista

                // **CONDICION DE GANAR**
                if (this.model.hitsSamePlace(guess) == this.length()) {
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
                if (!this.isGameFinished() && this.triesLeft() == 0) {
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

}
