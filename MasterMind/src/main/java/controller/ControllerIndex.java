/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.ModelGame;
import view.ViewGame;
import view.ViewIndex;
import view.ViewLeaderboard;

/**
 *
 * @author silvia
 */
public class ControllerIndex implements ActionListener {

    private ViewIndex view;
    private ModelGame model;
    private ViewGame viewGame;
    private ViewLeaderboard viewLeaderboard;

    public ControllerIndex(ViewIndex view, ModelGame model) {
        this.view = view;
        this.model = model;
        this.view.setActionListener(this);
        this.viewGame = new ViewGame();
        this.viewLeaderboard = new ViewLeaderboard();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "play" -> {
                view.setVisible(false);
                viewGame = new ViewGame();
                viewGame.setVisible(true);
                ControllerGame cg = new ControllerGame(viewGame, model, view);
                
            }
            case "difficulty" -> {
            }

            case "howToPlay" -> {
            }

            case "leaderBoard" -> {
                view.setVisible(false);
                viewLeaderboard.setVisible(true);
                ControllerLeaderboard cl = new ControllerLeaderboard(viewLeaderboard, model, view);
                cl.updateScores();
            }
            // TO DO... or not?
            case "exit" -> {
                view.dispose();
            }
        }
    }

}
