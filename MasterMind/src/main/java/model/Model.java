package model;

import java.awt.Color;
import java.awt.List;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author silvia
 */
public class Model {

    /**
     * Número aleatorio que el usuario tiene que adivinar. Se almacena en una
     * String para mayor comodidad de uso. Tiene por defecto 4 cifras sin
     * repeticiones.
     */
    private String randomNumber;
    private final int length = 4;
    private final int tries = 10;
    //guardar el historial de intentos:
    private ArrayList<String> attemptHistory;

    boolean[] secretUse;

    public Model() {
        this.randomNumber = createRandomNumber(length);
        System.out.println(randomNumber);
        this.attemptHistory = new ArrayList<>();
    }

    public String getRandomNumber() {
        return randomNumber;
    }

    public static String createRandomNumber(int longitud) {
        StringBuilder str = new StringBuilder();
        StringBuilder avaliableDigits = new StringBuilder("123456789");
        int numero;
        Random random = new Random();
        for (int i = 0; i < longitud; i++) {
            numero = random.nextInt(0, avaliableDigits.length());
            str.append(avaliableDigits.toString().charAt(numero));
            avaliableDigits.deleteCharAt(numero);
        }
        return str.toString();
    }
    
    /**
     * Resume información acerca del intento del usuario en un array de
     * Strings.
     * @param guess String que guarda el intento del usuario.
     * @return Retorna un array de Strings de forma que si en la posición i
     * aparece "correct", es porque el dígito de la posición i del intento
     * coincide con el número del juego en la posición i, si aparece "partial"
     * el dígito del intento aparece en el número del juego pero en otra posición
     * y si aparece "incorrect" no aparece en el número del juego.
     */
    public String[] feedbackInfo(String guess) {
        String[] result = new String[4];
        String numberToGuess = this.getRandomNumber();
        for (int i = 0; i < guess.length(); i++) {
            char userDigit = guess.charAt(i);
            boolean resultUpdated = false;
            for (int j = 0; j < guess.length(); j++) {
                if (userDigit == numberToGuess.charAt(j)) {
                    if (i == j) result[i] = "correct";
                    else result[i] = "partial";
                    resultUpdated = true;
                }
            }
            if (!resultUpdated) result[i] = "incorrect";
        }
        return result;
    }
    
    
    public int hitsSamePlace(String guess) {
        int counter = 0;
        for (int i = 0; i < guess.length(); i++) {
            if (this.randomNumber.charAt(i) == guess.charAt(i)) {
                counter++;
            }
        }
        return counter;
    }

//    public int hitsAnyWhere(String introducedString) {
//        int contador = 0;
//        for (int i = 0; i < introducedString.length(); i++) {
//
//            for (int j = 0; j < this.randomNumber.length(); j++) {
//                if (introducedString.charAt(i) == this.randomNumber.charAt(j)) {
//                    contador++;
//                    break;
//                }
//            }
//
//        }
//        return contador;
//    }
    
    
    public int hitsAnyWhere(String guess) {
        int cont = 0;
        String secret = this.randomNumber;
        secretUse = new boolean[secret.length()]; // dig igual o no

        for (int i = 0; i < guess.length(); i++) {
            char digitIntroduced = guess.charAt(i);

            // si hay un acierto nomisma posición 
            if (secret.charAt(i) == digitIntroduced) {
                continue; // Si es acierto namisma posición, lo ignoramos 
            }

            // Buscamos en una posición diferente
            for (int j = 0; j < secret.length(); j++) {
                if (!secretUse[j] && secret.charAt(j) == digitIntroduced && i != j) {
                    cont++;
                    secretUse[j] = true;
                    break; // Pasamos al siguiente dgito 
                }
            }
        }
        return cont;
    }

    public int getLength() {
        return length;
    }

    //  reiniciar el juego (generarnd nuevo num secreto)
    public void resetGame() {
        this.randomNumber = createRandomNumber(length);
        // this.attemptHistory.clear(); // Si implementamos el historial
    }

    //guardar el historial de intentos
    public void addAttempt(String attempt) {
        this.attemptHistory.add(attempt);
    }
    //obter el historial de intentos

    public ArrayList<String> getAttemptsHistory() {
        return attemptHistory;
    }
    //todo q termine al acertar
    //todo q los intentos terminen en 0

    public boolean[] getSecretUse() {
        return secretUse;
    }

}
