/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class ModelGame {
    // Variables

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
    // Guardar el historial de intentos:
    private List<String> attemptHistory = new ArrayList<>();
    private boolean gameFinished = false;
    private int score = 0;

    boolean[] secretUse;

    private Map<String, Integer> scoreHistory = new LinkedHashMap<>();

    // Constructor
    public ModelGame() {
        this.length = 4;
        this.maxTries = 10;
        resetGame(); // Inicializar el juego al crear el ModelGame
    }

    // Resetear o xogo
    public void resetGame() {
        this.numberToGuess = generateRandomNumber();
        this.triesLeft = this.maxTries;
        this.gameFinished = false;
        this.attemptHistory.clear(); // Limpiar el historial al resetear
    }

    // Novo xogo
    public void startNewGame() {
        resetGame();
    }

    // Recibe numero de columnas
    public void setLength(int length) {
        this.length = length;
        this.numberToGuess = this.generateRandomNumber();
        System.out.println(numberToGuess);
    }

    // Recibe numero de filas
    public void setMaxTries(int maxTries) {
        this.maxTries = maxTries;
        this.triesLeft = this.maxTries;
    }

    // Recibe numero de intentos restantes y los va modificando
    public void setTriesLeft(int triesLeft) {
        this.triesLeft = triesLeft;
    }

    // Disminuye los intentos restantes
    public void consumeTry() {
        this.triesLeft -= 1;
    }

    // Termina el juego
    public boolean isGameFinished() {
        return gameFinished;
    }

    public void finishGame() {
        this.gameFinished = true;
    }

    public Map<String, Integer> getScoreHistory() {
        return scoreHistory;
    }

    public void setScoreHistory(Map<String, Integer> scoreHistory) {
        this.scoreHistory = scoreHistory;
    }

    public String generateRandomNumber() {
        StringBuilder str = new StringBuilder();
        StringBuilder availableDigits = new StringBuilder("123456789");
        Random random = new Random();
        for (int i = 0; i < this.length; i++) {
            if (availableDigits.length() == 0) {
                
                break;
            }
            int numero = random.nextInt(availableDigits.length());
            str.append(availableDigits.charAt(numero));
            availableDigits.deleteCharAt(numero);
        }
        System.out.println(str.toString());
        return str.toString();
    }

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

    // Guardar el historial de intentos
    public void addAttempt(String attempt) {
        this.attemptHistory.add(attempt);
    }

    // Obtener el historial de intentos
    public List<String> getAttemptsHistory() {
        return attemptHistory;
    }

    // Puntuaje
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

    public void updateHighScores(String playerName) {
        scoreHistory.put(playerName, score);
        List<Map.Entry<String, Integer>> scoreList = new ArrayList<>(scoreHistory.entrySet());
        scoreList.sort(Collections.reverseOrder(Map.Entry.comparingByValue()));
        scoreHistory.clear();
        for (Map.Entry<String, Integer> entry : scoreList) scoreHistory.put(entry.getKey(), entry.getValue());
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

    public String getNumberToGuess() {
        return numberToGuess;
    }
}