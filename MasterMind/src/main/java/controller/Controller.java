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

    private View view;
    private Model model;
    //private int currentTry = 0;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        
        this.model.setLength(this.view.getLength());
        this.model.setMaxTries(this.view.getMaxTries());

        this.view.setController(this);
        //inicial el cont de intentos
        this.view.setTriesLeftNumbersText(this.triesLeft());
        //this.currentTry = 0;
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

            // Validar que el usuario ha introducido 4 dígitos
            if (guess.length() == model.getLength() && guess.matches("[0-9]+")) {
//                int correctPositions = model.hitsSamePlace(guess);
//                int presentDigits = model.hitsAnyWhere(guess) - correctPositions; // Evitar contar los correctos en posición

//                view.displayFeedback(userGuess, correctPositions, presentDigits);
                this.model.consumeTry();
                view.setTriesLeftNumbersText(this.triesLeft());
                view.clearInputFields();

//                if (correctPositions == model.getLength()) {
//
//                } else if (triesLeft == 0) {
//
//                }
//                correctPositions = model.hitsSamePlace(guess);
//                presentDigits = model.hitsAnyWhere(guess);

                /*
                String[] feedback = new String[guess.length()];
                for (int i = 0; i < guess.length(); i++) {
                    if (correctPositions > i) {
                        feedback[i] = "correct";
                    } else if (correctPositions + presentDigits > i) {
                        feedback[i] = "partial";
                    } else {
                        feedback[i] = "incorrect";
                    }
                }
                 */
                String[] feedbackInfo = this.model.feedbackInfo(guess);
                view.displayFeedback(guess, feedbackInfo); // Pasar feedback a vista

                if (this.model.hitsSamePlace(guess) == this.length()
                        /*
                        Stream.of(feedbackInfo)
                        .filter(str -> str.equals("correct"))
                        .count()
                        == 4
                        */) {
                    this.finishGame();
                    JOptionPane.showConfirmDialog(
                        this.view,
                        "You guessed the number correctly. Congrats!",
                        "End of the game",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null
                    );
                }
                
                
                if (!this.isGameFinished() && this.triesLeft() == 0) {
                    this.finishGame();
                    JOptionPane.showConfirmDialog(
                        this.view,
                        "You have no tries left. You lost!",
                        "End of the game",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null
                    );
                }
                
            } 

        } else {
            view.clearInputFields();
        }
    }

}
