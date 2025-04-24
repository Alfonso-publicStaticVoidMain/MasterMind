package model;

import java.io.*;
import java.util.*;

/**
 *
 * @author nuria.calomosquera
 */

public class ScoreFileHandler {

    private static final String SCORES_FILE = "scores.dat";

    // Save scores to file
    public static void saveScores(Map<String, Integer> scores) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SCORES_FILE))) {
            oos.writeObject(scores);
        } catch (IOException e) {
            System.err.println("Error saving scores: " + e.getMessage());
        }
    }

    // Load scores from file
    @SuppressWarnings("unchecked")
    public static Map<String, Integer> loadScores() {
        File file = new File(SCORES_FILE);
        if (!file.exists()) {
            return new LinkedHashMap<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SCORES_FILE))) {
            return (Map<String, Integer>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading scores: " + e.getMessage());
            return new LinkedHashMap<>();
        }
    }
}
