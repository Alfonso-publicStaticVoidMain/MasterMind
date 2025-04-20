package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.ModelGame;
import view.ViewDifficulty;
import view.ViewGame;
import view.ViewIndex;

/**
 *
 * @author silvia
 */
public class ControllerDifficulty implements ActionListener {

    private ViewDifficulty viewDifficulty;
    private ModelGame model;
    private ViewIndex viewIndex;
    private ViewGame viewGame;

    public ControllerDifficulty(ViewDifficulty viewDifficulty, ModelGame model, ViewIndex viewIndex) {
        this.viewDifficulty = viewDifficulty;
        this.model = model;
        this.viewIndex = viewIndex;
        this.viewDifficulty.setActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Easy" -> {
                viewDifficulty.setVisible(false);
                viewGame = new ViewGame();
                viewGame.setVisible(true);
                viewIndex.setVisible(false);
                ControllerGame cg = new ControllerGame(viewGame, model, viewIndex);
            }
            case "Difficult" -> {
                viewDifficulty.setVisible(false);
                viewGame = new ViewGame();
                viewGame.setVisible(true);
                viewIndex.setVisible(false);
                model.setLength(5);
                model.setMaxTries(5);
                ControllerGame cg = new ControllerGame(viewGame, model, viewIndex);
            }

        }

    }
}
