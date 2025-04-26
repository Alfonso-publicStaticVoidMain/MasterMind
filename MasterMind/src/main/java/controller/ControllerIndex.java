/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import model.ModelGame;
import model.ModelGameDifficult;
import view.ViewDifficulty;
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

    public ControllerIndex(ViewIndex view) {
        this.view = view;
        this.view.setActionListener(this);

        // O xogo comeza ca vista
        this.view.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        System.out.println("Action received: "+command);

        switch (command) {
            case "game" -> {
                SwingUtilities.invokeLater( () -> {
                    view.dispose();
                    ControllerDifficulty controllerDifficulty = new ControllerDifficulty(new ViewDifficulty()); 
                });
            }

            case "howToPlay" -> {
                SwingUtilities.invokeLater( () -> {
                    view.dispose();
                    ControllerHowToPlay controllerHowToPlay = new ControllerHowToPlay(new ViewHowToPlay());
                });
            }

            case "leaderBoard" -> {
                SwingUtilities.invokeLater( () -> {
                    view.dispose();
                    ControllerLeaderboard controllerLeaderboard = new ControllerLeaderboard(new ViewLeaderboard());
                });
            }

            case "exit" -> {
                view.dispose();
            }
        }
    }

}
