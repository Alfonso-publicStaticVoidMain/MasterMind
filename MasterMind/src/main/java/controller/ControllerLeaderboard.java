package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.SwingUtilities;
import model.ModelGame;
import model.ScoreFileHandler;
import view.ViewIndex;
import view.ViewLeaderboard;

/**
 *
 * @author agall
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
        for (Map.Entry entry : scoreList) {
            this.view.getScoreBoard()[row][0].setText((String) entry.getKey());
            this.view.getScoreBoard()[row][1].setText(Integer.toString((Integer) entry.getValue()));
            row++;
            if (row > 10) {
                break;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        System.out.println(this.getClass().getSimpleName()+" action received: "+command);
        
        switch (command) {
            case "back" -> {
                SwingUtilities.invokeLater( () -> {
                    view.dispose();
                    ControllerIndex controllerIndex = new ControllerIndex(new ViewIndex());
                });
            }
        }
    }

    // Add this method to be called when exiting the application
//    public void onExit() {
//        model.saveScoresOnExit();
//    }
}
