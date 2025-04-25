package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.ModelGame;
import view.ViewIndex;
import view.ViewLeaderboard;

/**
 *
 * @author agall
 */
public class ControllerLeaderboard implements ActionListener {

    private ViewLeaderboard view;
    private ModelGame modelGame;
    private ViewIndex viewIndex;

    public ControllerLeaderboard(ViewLeaderboard view, ModelGame modelGame, ViewIndex viewIndex) {
        this.view = view;
        this.modelGame = modelGame;
        this.viewIndex = viewIndex;
        this.view.setActionListener(this);
    }

    public Map<String, Integer> getScores() {
        return this.modelGame.getScoreHistory();
    }

    public void updateScores() {
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
        if (e.getSource() == view.getBackButton()) {
            view.dispose();
            viewIndex.setVisible(true);
        }
    }

    // Add this method to be called when exiting the application
    public void onExit() {
        modelGame.saveScoresOnExit();
    }
}
