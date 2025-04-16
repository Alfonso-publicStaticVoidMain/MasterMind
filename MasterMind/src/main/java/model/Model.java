/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Model {
    //variables

    /**
     * Número aleatorio que el usuario tiene que adivinar. Se almacena en una
     * String para mayor comodidad de uso. Tiene por defecto 4 cifras sin
     * repeticiones.
     */
    private String numberToGuess;
    /**
     * Longitud del número aleatorio a generar.
     */
    private int length;
    /**
     * Número máximo de intentos.
     */
    private int maxTries;
    /**
     * Número de intentos restantes.
     */
    private int triesLeft;
    //guardar el historial de intentos:
    private ArrayList<String> attemptHistory = new ArrayList<>();
    private boolean gameFinished = false;
    private int score = 0;

    boolean[] secretUse;

    //constructor
    public Model() {
        this.length=4;
        this.maxTries=10;
        this.triesLeft=10;
        resetGame();

    }

    //Resetear o xogo
    public void resetGame() {
        this.numberToGuess = generateRandomNumber();
        this.triesLeft = this.maxTries;
        this.gameFinished = false;  // 🚀 Allow a new game to start
    }

    //Novo xogo
    public void startNewGame() {
        this.numberToGuess = generateRandomNumber(); // Generate new number
        this.triesLeft = this.maxTries; // Reset attempts
        this.gameFinished = false; // 🚀 Allow a new game to start
    }

    //recibe numero de columnas
    public void setLength(int length) {
        this.length = length;
        this.numberToGuess = this.generateRandomNumber();
        System.out.println(numberToGuess);
    }

    //recibe numero de filas
    public void setMaxTries(int maxTries) {
        this.maxTries = maxTries;
        this.triesLeft = this.maxTries;
    }

    //recibe numero de intentos restantes y los va modificndpo
    public void setTriesLeft(int triesLeft) {
        this.triesLeft = triesLeft;
    }
    //disminuye los intentos restantes

    public void consumeTry() {
        this.triesLeft -= 1;
    }

    //termina el juego
    public boolean isGameFinished() {
        return gameFinished;
    }

    public void finishGame() {
        this.gameFinished = true;
    }

    public String generateRandomNumber() {
        StringBuilder str = new StringBuilder();
        StringBuilder avaliableDigits = new StringBuilder("123456789");
        Random random = new Random();
        for (int i = 0; i < this.length; i++) {
            int numero = random.nextInt(avaliableDigits.length());
            str.append(avaliableDigits.charAt(numero));
            avaliableDigits.deleteCharAt(numero);
        }
        System.out.println(str.toString());
        return str.toString();
    }
    //
//      public String getNumberToGuess() {
//        return numberToGuess;
//    }

    /**
     * Resume información acerca del intento del usuario en un array de Strings.
     *
     * @param guess String que guarda el intento del usuario.
     * @return Retorna un array de Strings de forma que si en la posición i
     * aparece "correct", es porque el dígito de la posición i del intento
     * coincide con el número del juego en la posición i, si aparece "partial"
     * el dígito del intento aparece en el número del juego pero en otra
     * posición y si aparece "incorrect" no aparece en el número del juego.
     */
    public String[] feedbackInfo(String guess) {
        String[] result = new String[this.length];
        for (int i = 0; i < this.length; i++) {
            char userDigit = guess.charAt(i);
            boolean resultUpdated = false;
            for (int j = 0; j < this.length; j++) {
                if (userDigit == numberToGuess.charAt(j)) {
                    resultUpdated = true;
                    if (i == j) {
                        result[i] = "correct";
                        break;
                    } else {
                        result[i] = "partial";
                    }
                }
            }
            if (!resultUpdated) {
                result[i] = "incorrect";
            }
        }
        return result;
    }

    public int hitsSamePlace(String guess) {
        int counter = 0;
        for (int i = 0; i < guess.length(); i++) {
            if (this.numberToGuess.charAt(i) == guess.charAt(i)) {
                counter++;
            }
        }
        return counter;
    }

    public int hitsAnyWhere(String guess) {
        int cont = 0;
        String secret = this.numberToGuess;
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
//      public boolean[] getSecretUse() {
//        return secretUse;
//    }

    //guardar el historial de intentos
    public void addAttempt(String attempt) {
        this.attemptHistory.add(attempt);
    }
    //obter el historial de intentos

    public ArrayList<String> getAttemptsHistory() {
        return attemptHistory;
    }

    //puntuaje
    public int getScore() {
        return score;
    }

    public void updateScore(boolean won) {
        if (won) {
            score += (triesLeft * 100); // Base points
            if (triesLeft >= maxTries / 2) {
                score += 200; // Bonus for quick win
            }
        } else {
            score = 0; // Reset if player loses
        }
    }

    //Histórico de Puntuaxe
    private ArrayList<Integer> highScores = new ArrayList<>();

    public ArrayList<Integer> getHighScores() {
        return highScores;
    }

    public void updateHighScores() {
        highScores.add(score); // Add current score to the list
        Collections.sort(highScores, Collections.reverseOrder()); // Sort from highest to lowest
        //ranking
        if (highScores.size() > 5) {
            highScores.remove(highScores.size() - 1); // Keep only top 5 scores
        }
    }
    //Nome de xogadores
    private ArrayList<String> playerNames = new ArrayList<>();

    public ArrayList<String> getPlayerNames() {
        return playerNames;
    }

    public void updateHighScores(String playerName) {
        playerNames.add(playerName);
        highScores.add(score); // Add current score to the list

        // Sort scores and names together
        ArrayList<Integer> sortedScores = new ArrayList<>(highScores);
        ArrayList<String> sortedNames = new ArrayList<>(playerNames);
// Sort scores while keeping names aligned
        for (int i = 0; i < sortedScores.size(); i++) {
            for (int j = i + 1; j < sortedScores.size(); j++) {
                if (sortedScores.get(i) < sortedScores.get(j)) {
                    Collections.swap(sortedScores, i, j);
                    Collections.swap(sortedNames, i, j);
                }
            }
        }

// Keep only top 5
        while (sortedScores.size() > 5) {
            sortedScores.remove(sortedScores.size() - 1);
            sortedNames.remove(sortedNames.size() - 1);
        }

// Update sorted names and scores
        playerNames = sortedNames;
        highScores = sortedScores;

        playerNames = new ArrayList<>(sortedNames);
        highScores = new ArrayList<>(sortedScores);

        // Keep only top 5 scores
        while (highScores.size() > 5) {
            highScores.remove(highScores.size() - 1);
            playerNames.remove(playerNames.size() - 1);
        }

    }

    public int getLength() {
        return length;
    }

    public int getMaxTries() {
        return maxTries;
    }

    public int getTriesLeft() {
        return triesLeft;
    }

    
    
    
}
