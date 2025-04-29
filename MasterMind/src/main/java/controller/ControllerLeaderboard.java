package controller;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.SwingUtilities;
import model.ScoreFileHandler;
import view.ViewIndex;
import view.ViewLeaderboard;

/**
 *
 * @author Silvia García Bouza
 * @author Nuria Calo Mosquera
 * @author Alfonso Gallego Fernández
 */
public class ControllerLeaderboard implements ActionListener {

    private ViewLeaderboard view;

    public ControllerLeaderboard(ViewLeaderboard view) {
        this.view = view;
        this.view.setActionListener(this);
        this.displayScores();
    }

    public Map<String, Integer> getScores() {
        return ScoreFileHandler.loadScores();
    }

    public void displayScores() {
        List<Map.Entry<String, Integer>> scoreList = new ArrayList<>(this.getScores().entrySet());
        int row = 1;
        for (Map.Entry<String, Integer> entry : scoreList) {
            String playerName = entry.getKey();
            int score = entry.getValue();

            playerName = switch (row) {
                case 1 -> "🥇 " + playerName;
                case 2 -> "🥈 " + playerName;
                case 3 -> "🥉 " + playerName;
                default -> "    " + playerName;
            };
            
            // Cambiar la fuente del JLabel que contiene los símbolos
            this.view.getScoreBoard()[row][0].setFont(new Font("Noto Sans Symbols", Font.PLAIN, 18));
            this.view.getScoreBoard()[row][0].setText(playerName);
            this.view.getScoreBoard()[row][1].setText(Integer.toString(score));
            row++;
            if (row > 10) {
                break;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        System.out.println(this.getClass().getSimpleName() + " action received: " + command);

        switch (command) {
            case "back" -> {
                SwingUtilities.invokeLater(() -> {
                    view.dispose();
                    ControllerIndex controllerIndex = new ControllerIndex(new ViewIndex());
                });
            }
        }
    }
    
}
