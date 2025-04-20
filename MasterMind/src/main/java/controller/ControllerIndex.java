package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.ModelGame;
import view.ViewGame;
import view.ViewHowToPlay;
import view.ViewIndex;
import view.ViewLeaderboard;

/**
 *
 * @author Silvia
 * @author Alfonso
 * @author Nuria
 */
public class ControllerIndex implements ActionListener {

    private ViewIndex view;
    private ModelGame model;
    private ViewGame viewGame;
    private ViewLeaderboard viewLeaderboard;
    private ViewHowToPlay viewHowToPlay;

    private ControllerHowToPlay controllerHowToPlay;

    public ControllerIndex(ViewIndex view, ModelGame model) {
        this.view = view;
        this.model = model;
        this.view.setActionListener(this);

        //Game
        this.viewGame = new ViewGame();

        //How to Play
        this.viewHowToPlay = null;
        this.controllerHowToPlay = null;

        // O xogo comeza ca vista
        this.view.setVisible(true);
        
        // Leaderboard
        this.viewLeaderboard = new ViewLeaderboard();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        System.out.println("Action received: " + command);

        switch (command) {
            case "play" -> {
                view.setVisible(false);
                viewGame = new ViewGame();
                viewGame.setVisible(true);
                ControllerGame cg = new ControllerGame(viewGame, model, view);
                
            }

            case "howToPlay" -> {
                view.setVisible(false);

                if (viewHowToPlay == null) {
                    viewHowToPlay = new ViewHowToPlay();
                    controllerHowToPlay = new ControllerHowToPlay(viewHowToPlay, view);
                }

                viewHowToPlay.setVisible(true);
            }

            case "leaderBoard" -> {
                view.setVisible(false);
                viewLeaderboard.setVisible(true);
                ControllerLeaderboard cl = new ControllerLeaderboard(viewLeaderboard, model, view);
                cl.updateScores();
            }

            case "exit" -> {
                view.dispose();
            }
        }
    }

}
