
import model.ModelGame;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Map;

/**
 * @author Silvia García Bouza
 * @author Nuria Calo Mosquera
 * @author Alfonso Gallego Fernández
 */
public class ModelGameTest {

    @Test
    void testResetGame() {
        ModelGame game = new ModelGame(4, 10);
        String initialNumber = game.getNumberToGuess();
        int initialTries = game.getTriesLeft();
        boolean initialFinished = game.isGameFinished();

        game.resetGame();

        assertNotEquals(initialNumber, game.getNumberToGuess());
        assertEquals(10, game.getTriesLeft());
        assertFalse(game.isGameFinished());
        assertEquals(0, game.getScore());
    }

    @Test
    void testStartNewGame() {
        ModelGame game = new ModelGame(5, 8);
        game.finishGame();
        game.setTriesLeft(2);
        game.startNewGame();

        assertNotEquals(null, game.getNumberToGuess());
        assertEquals(8, game.getTriesLeft());
        assertFalse(game.isGameFinished());
        assertEquals(0, game.getScore());
    }

    @Test
    void testSetLength() {
        ModelGame game = new ModelGame();
        game.setLength(6);
        assertEquals(6, game.getLength());
        assertEquals(6, game.getNumberToGuess().length());
    }

    @Test
    void testSetMaxTries() {
        ModelGame game = new ModelGame();
        game.setMaxTries(15);
        assertEquals(15, game.getMaxTries());
        assertEquals(15, game.getTriesLeft());
    }

    @Test
    void testSetTriesLeft() {
        ModelGame game = new ModelGame();
        game.setTriesLeft(5);
        assertEquals(5, game.getTriesLeft());
    }

    @Test
    void testConsumeTry() {
        ModelGame game = new ModelGame(4, 5);
        assertEquals(5, game.getTriesLeft());
        game.consumeTry();
        assertEquals(4, game.getTriesLeft());
    }

    @Test
    void testStartGame() {
        ModelGame game = new ModelGame();
        assertFalse(game.isGameStarted());
        game.startGame();
        assertTrue(game.isGameStarted());
    }

    @Test
    void testFinishGame() {
        ModelGame game = new ModelGame();
        assertFalse(game.isGameFinished());
        game.finishGame();
        assertTrue(game.isGameFinished());
    }

    @Test
    void testGenerateRandomNumberWithDefaultLength() {
        ModelGame game = new ModelGame();
        String number = game.generateRandomNumber();
        assertEquals(4, number.length());
        assertTrue(number.matches("[1-9]+"));
        assertEquals(number.chars().distinct().count(), number.length()); // Check for unique digits
    }

    @Test
    void testGenerateRandomNumberWithCustomLength() {
        ModelGame game = new ModelGame(6, 10);
        String number = game.generateRandomNumber();
        assertEquals(6, number.length());
        assertTrue(number.matches("[1-9]+"));
        assertEquals(number.chars().distinct().count(), number.length()); // Check for unique digits
    }

    @Test
    void testFeedbackInfoCorrect() {
        ModelGame game = new ModelGame(4, 10);
        // Mock the numberToGuess for testing
        java.lang.reflect.Field field = null;
        try {
            field = ModelGame.class.getDeclaredField("numberToGuess");
            field.setAccessible(true);
            field.set(game, "1234");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        String[] feedback = game.feedbackInfo("1234");
        assertArrayEquals(new String[]{"correct", "correct", "correct", "correct"}, feedback);
    }

    @Test
    void testFeedbackInfoPartialAndIncorrect() {
        ModelGame game = new ModelGame(4, 10);
        // Mock the numberToGuess for testing
        java.lang.reflect.Field field = null;
        try {
            field = ModelGame.class.getDeclaredField("numberToGuess");
            field.setAccessible(true);
            field.set(game, "1234");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        String[] feedback = game.feedbackInfo("4315");
        assertArrayEquals(new String[]{"partial", "partial", "partial", "incorrect"}, feedback);
    }

    @Test
    void testHitsSamePlace() {
        ModelGame game = new ModelGame(4, 10);
        // Mock the numberToGuess for testing
        java.lang.reflect.Field field = null;
        try {
            field = ModelGame.class.getDeclaredField("numberToGuess");
            field.setAccessible(true);
            field.set(game, "1234");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        assertEquals(3, game.hitsSamePlace("1235"));
        assertEquals(0, game.hitsSamePlace("5678"));
        assertEquals(4, game.hitsSamePlace("1234"));
    }

    @Test
    void testHitsAnyWhere() {
        ModelGame game = new ModelGame(4, 10);
        java.lang.reflect.Field field = null;
        try {
            field = ModelGame.class.getDeclaredField("numberToGuess");
            field.setAccessible(true);
            field.set(game, "1234");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        assertEquals(3, game.hitsAnyWhere("4315"));
        assertEquals(0, game.hitsAnyWhere("5678"));
        assertEquals(0, game.hitsAnyWhere("1234"));
        assertEquals(2, game.hitsAnyWhere("3456"));
    }

    @Test
    void testUpdateScoreWonQuickWin() {
        ModelGame game = new ModelGame(4, 10);
        game.setTriesLeft(7);
        game.updateScore(true);
        assertEquals(700 + 200, game.getScore());
    }

    @Test
    void testUpdateScoreWonSlowWin() {
        ModelGame game = new ModelGame(4, 10);
        game.setTriesLeft(3);
        game.updateScore(true);
        assertEquals(300, game.getScore());
    }

    @Test
    void testUpdateScoreLost() {
        ModelGame game = new ModelGame(4, 10);
        game.setTriesLeft(0);
        game.updateScore(false);
        assertEquals(0, game.getScore());
    }

    @Test
    void testGetLength() {
        ModelGame game = new ModelGame(7, 12);
        assertEquals(7, game.getLength());
    }

    @Test
    void testGetMaxTries() {
        ModelGame game = new ModelGame(7, 12);
        assertEquals(12, game.getMaxTries());
    }

    @Test
    void testGetTriesLeft() {
        ModelGame game = new ModelGame(4, 6);
        game.consumeTry();
        assertEquals(5, game.getTriesLeft());
    }

    @Test
    void testGetNumberToGuess() {
        ModelGame game = new ModelGame();
        assertNotNull(game.getNumberToGuess());
        assertEquals(game.getLength(), game.getNumberToGuess().length());
    }
}
