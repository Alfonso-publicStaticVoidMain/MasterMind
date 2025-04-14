package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Model;
import view.View;

/**
 *
 * @author silvia
 */
public class Controller implements ActionListener{
    private View view;
    private Model model;
     private int triesLeft;
    private final int MAX_TRIES = 10;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        this.triesLeft= MAX_TRIES;
        
        this.view.setController(this);
        //inicial el cont de intentos
         this.view.setTriesLeftNumbersText(triesLeft); 
        updateView();
    }

    
     private void updateView() {

     //todo
     //- view.triesLeftNumbersText -1 
     //- previousTriesText añadir el intento del textArea
     //userTriesText resetearlo
     }
    
    //TODO
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ("submit".equals(command)) {
            String userGuess = view.getUserDigits();

            // Validar que el usuario ha introducido 4 dígitos
            if (userGuess.length() == model.getLength() && userGuess.matches("[0-9]+")) {
                int correctPositions = model.hitsSamePlace(userGuess);
                int presentDigits = model.hitsAnyWhere(userGuess) - correctPositions; // Evitar contar los correctos en posición

                view.displayFeedback(userGuess, correctPositions, presentDigits);
                triesLeft--;
                view.setTriesLeftNumbersText(triesLeft);
                view.clearInputFields();

                if (correctPositions == model.getLength()) {
                    
                } else if (triesLeft == 0) {
                    
                }
            } else {
                // Informar al usuario sobre una entrada inválida (podrías añadir un mensaje en la View)
                System.out.println("Entrada inválida. Debes ingresar " + model.getLength() + " dígitos.");
                view.clearInputFields();
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

   
    
}}
