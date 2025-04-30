package model;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Silvia García Bouza
 * @author Nuria Calo Mosquera
 * @author Alfonso Gallego Fernández
 */
public class ModelGame {
    // Variables

    /**
     * Random number that the user has to guess. It is stored in a
    * String for convenience of use. It defaults to 4 digits without
    * repetitions..
     */
    private String numberToGuess;
    /**
     * Length of the random number to be generated.
     */
    private int length;
    /**
     * Maximum number of attempts.
     */
    private int maxTries;
    /**
     * Number of remaining attempts.
     */
    private int triesLeft;
    private boolean gameFinished = false;
    private boolean gameStarted = false;
    private int score = 0;

    private Map<String, Integer> scoreHistory;

    public ModelGame() {
        this.length = 4;
        this.maxTries = 10;
        this.scoreHistory = ScoreFileHandler.loadScores(); //Save scores
        resetGame(); // Initializing the game when creating the ModelGame
    }
    
    public ModelGame(int length, int maxTries) {
        this.length = length;
        this.maxTries = maxTries;
        this.scoreHistory = ScoreFileHandler.loadScores();
        resetGame(); 
    }

    // Reset.
    public void resetGame() {
        this.numberToGuess = generateRandomNumber();
        this.triesLeft = this.maxTries;
        this.gameFinished = false;
        this.score = 0;
    }

    // New game.
    public void startNewGame() {
        resetGame();
    }

    // Change number of row.
    public void setLength(int length) {
        this.length = length;
        this.numberToGuess = this.generateRandomNumber();
    }

    // Receives number of rows.
    public void setMaxTries(int maxTries) {
        this.maxTries = maxTries;
        this.triesLeft = this.maxTries;
    }

    // Receives the number of remaining attempts and modifies them.
    public void setTriesLeft(int triesLeft) {this.triesLeft = triesLeft;}

    // Decreases remaining attempts.
    public void consumeTry() {triesLeft--;}

    public boolean isGameStarted() {return gameStarted;}
    
    public void startGame() {gameStarted = true;}

    public boolean isGameFinished() {return gameFinished;}

    public void finishGame() {gameFinished = true;}

    public Map<String, Integer> getScoreHistory() {
        return scoreHistory;
    }

    public void setScoreHistory(Map<String, Integer> scoreHistory) {
        this.scoreHistory = scoreHistory;
    }

    public String generateRandomNumber() {
        StringBuilder result = new StringBuilder();
        StringBuilder availableDigits = new StringBuilder("123456789");
        Random random = new Random();
        for (int i = 0; i < this.length; i++) {
            if (availableDigits.length() == 0) {
                break;
            }
            int numero = random.nextInt(availableDigits.length());
            result.append(availableDigits.charAt(numero));
            availableDigits.deleteCharAt(numero);
        }
        System.out.println("[DEBUG] generateRandomNumber: " + result.toString());
        return result.toString();
    }

    /**
     * Resume información acerca del intento del usuario en un array de Strings.
     *
     * @param guess String que guarda el intento del usuario.
     * @return Returns an array of Strings so that if in the position i
     * appears “correct”, it is because the digit in position i of the
     * coincides with the set number in position i, if “partial” appears
     * the digit of the attempt appears in the number of the set but in another
     * position and if “incorrect” appears, it does not appear in the set number.
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
        boolean[] secretUse = new boolean[secret.length()]; // dig igual o no

        for (int i = 0; i < guess.length(); i++) {
            char digitIntroduced = guess.charAt(i);

            // Looking for a same position
            if (secret.charAt(i) == digitIntroduced) {
                continue; // Si es acierto namisma posición, lo ignoramos
            }

            //Looking for a different position
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

    // Score.
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
        if ((scoreHistory.containsKey(playerName) && scoreHistory.get(playerName) < score) || !scoreHistory.containsKey(playerName)) {
            scoreHistory.put(playerName, score);
            List<Map.Entry<String, Integer>> scoreList = new ArrayList<>(scoreHistory.entrySet());
            scoreList.sort(Collections.reverseOrder(Map.Entry.comparingByValue()));
            scoreHistory.clear();
            for (Map.Entry<String, Integer> entry : scoreList) {
                scoreHistory.put(entry.getKey(), entry.getValue());
            }

            // Save scores.
            ScoreFileHandler.saveScores(scoreHistory);
        }
    }

    public void saveScoresOnExit() {
        ScoreFileHandler.saveScores(scoreHistory);
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
