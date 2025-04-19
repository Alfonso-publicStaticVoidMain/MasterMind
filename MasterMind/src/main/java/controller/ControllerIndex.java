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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        System.out.println("Action received: " + command);

        switch (command) {
            case "play" -> {
                view.dispose();
                ViewGame vg = new ViewGame();
                ControllerGame cg = new ControllerGame(vg, this.model);
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
                view.dispose();
                ViewLeaderboard vl = new ViewLeaderboard();
                ControllerLeaderboard cl = new ControllerLeaderboard(vl, this.model);
            }

            case "exit" -> {
                view.dispose();
            }
        }
    }

}
