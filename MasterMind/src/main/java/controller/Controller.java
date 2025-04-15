package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.stream.Stream;
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
    private int triesLeft;
    private final int MAX_TRIES = 10;
    private int currentTry = 0;
    private boolean gameFinished = false;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        this.triesLeft = MAX_TRIES;

        this.view.setController(this);
        //inicial el cont de intentos
        this.view.setTriesLeftNumbersText(triesLeft);
        this.currentTry = 0;
    }

    public int getMAX_TRIES() {
        return MAX_TRIES;
    }

    //TODO
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (!gameFinished && command.equals("submit")) {
            String guess = view.getUserDigits();

            // Validar que el usuario ha introducido 4 dígitos
            if (guess.length() == model.getLength() && guess.matches("[0-9]+")) {
//                int correctPositions = model.hitsSamePlace(guess);
//                int presentDigits = model.hitsAnyWhere(guess) - correctPositions; // Evitar contar los correctos en posición

//                view.displayFeedback(userGuess, correctPositions, presentDigits);
                triesLeft--;
                view.setTriesLeftNumbersText(triesLeft);
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

                if (this.model.hitsSamePlace(guess) == 4 
                        /*
                        Stream.of(feedbackInfo)
                        .filter(str -> str.equals("correct"))
                        .count()
                        == 4
                        */) {
                    gameFinished = true;
                    JOptionPane.showConfirmDialog(
                        this.view,
                        "You guessed the number correctly. Congrats!",
                        "End of the game",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null
                    );
                }
                /*
                if (correctPositions == model.getLength()) {
                    gameWon = true;
//                view.displayWinMessage("¡Felicidades! ¡Has ganado!");
//                view.disableInputs();
                } else {
                    currentTry++;
                    //view.setTriesLeft(MAX_TRIES - currentTry);
                    if (currentTry >= MAX_TRIES) {
//                    view.displayWinMessage("¡Se acabaron los intentos! El número era " + model.getRandomNumber());
//                    view.disableInputs();
                    }
                    view.clearInputFields();
                }
                 */
                view.clearInputFields();
                
                if (!gameFinished && triesLeft == 0) {
                    this.gameFinished = true;
                    JOptionPane.showConfirmDialog(
                        this.view,
                        "You have no tries left. You lost!",
                        "End of the game",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null
                    );
                }
                
            } else {
                // Informar al usuario sobre una entrada inválida (podrías añadir un mensaje en la View)
                System.out.println("Entrada inválida. Debes ingresar " + model.getLength() + " dígitos.");
            }

        }
    }

//           private void disableGame() {
//        view.disableInput();
//    }
//
//    // Método para iniciar el juego (si fuera necesario alguna inicialización adicional)
//    public void startGame() {
//        model.generateSecretNumber(); // Asegurarse de que se genere un nuevo número al iniciar
//        triesLeft = MAX_TRIES;
//        view.setTriesLeftNumbersText(triesLeft);
//        view.clearInputFields();
//        view.enableInput(); // Si tuvieras un método para habilitar la entrada
//        view.resetFeedback(); // Si tuvieras un método para limpiar la cuadrícula de intentos
//    }
//    }
}
