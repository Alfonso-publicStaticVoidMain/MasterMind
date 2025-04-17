/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.ViewIndex;

/**
 *
 * @author silvia
 */
public class ControllerIndex implements ActionListener {

    private ViewIndex view;

    public ControllerIndex(ViewIndex view) {
        this.view = view;
        this.view.setActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "play" -> {
            }
            case "difficulty" -> {
            }
            case "howToPlay" -> {
            }
            case "leaderBoard" -> {
            }
        }

    }

}
