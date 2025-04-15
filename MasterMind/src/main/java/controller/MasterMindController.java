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
        
        this.model.setLength(this.view.getLength());
        this.model.setMaxTries(this.view.getMaxTries());

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

                if (this.model.hitsSamePlace(guess) == this.length()) {
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
