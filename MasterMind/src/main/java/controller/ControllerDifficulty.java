/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

    public ControllerDifficulty(ViewDifficulty viewDifficulty, ModelGame model) {
        this.viewDifficulty = viewDifficulty;
        this.model = model;
        this.viewDifficulty.setActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Easy" -> {
                viewDifficulty.setVisible(false);
                viewGame = new ViewGame();
                viewIndex = new ViewIndex();
                viewGame.setVisible(true);
                viewGame.createView(4, 10);
                viewIndex.setVisible(false);
                ControllerGame cg = new ControllerGame(viewGame, model, viewIndex);
            }
            case "Difficult" -> {
                  viewDifficulty.setVisible(false);
                viewGame = new ViewGame();
                viewIndex = new ViewIndex();
                viewGame.setVisible(true);
                viewGame.createView(5, 5);
                viewIndex.setVisible(false);
                ControllerGame cg = new ControllerGame(viewGame, model, viewIndex);
            }

        }

    }
}
