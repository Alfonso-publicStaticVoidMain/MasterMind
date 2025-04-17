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

    public ControllerIndex(ViewIndex view, ModelGame model) {
        this.view = view;
        this.model = model;
        this.view.setActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "play" -> {
                view.dispose();
                ViewGame vg = new ViewGame();
                ControllerGame cg = new ControllerGame(vg, this.model);
            }
            case "difficulty" -> {
            }

            case "howToPlay" -> {
            }

            case "leaderBoard" -> {
                view.dispose();
                ViewLeaderboard vl = new ViewLeaderboard();
                ControllerLeaderboard cl = new ControllerLeaderboard(vl, this.model);
                cl.updateScores();
            }
            // TO DO... or not?
            case "exit" -> {
                view.dispose();
            }
        }
    }

}
