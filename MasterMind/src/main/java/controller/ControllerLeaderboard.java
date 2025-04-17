package controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import model.ModelGame;
import view.ViewLeaderboard;

/**
 *
 * @author agall
 */
public class ControllerLeaderboard {
    private ViewLeaderboard view;
    private ModelGame modelGame;
    
    public ControllerLeaderboard(ViewLeaderboard view, ModelGame modelGame) {
        this.view = view;
        this.modelGame = modelGame;
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
            if (row > 10) break;
        }
    }
}
