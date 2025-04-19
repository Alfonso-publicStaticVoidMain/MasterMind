/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.ModelDifficulty;
import model.ModelGame;
import model.ModelGameDifficult;
import view.ViewDifficulty;
import view.ViewGame;
import view.ViewGameDifficult;

/**
 *
 * @author silvia
 */
public class ControllerDifficulty implements ActionListener {

    private ViewDifficulty view;
    private ModelGame model;
    private ModelGameDifficult modeld;

    public ControllerDifficulty(ViewDifficulty view, ModelGame model, ModelGameDifficult modeld) {
        this.view = view;
        this.model = model;
        this.modeld= modeld;
        this.view.setActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Easy" -> {
                view.dispose();
                ViewGame vg= new ViewGame();
                ControllerGame cg = new ControllerGame(vg, this.model);
            }
            case "Difficul" -> {
                view.dispose();
                ViewGameDifficult vgd = new ViewGameDifficult();
                ControllerGameDifficult cgd = new ControllerGameDifficult(vgd, this.modeld);
            }

        }

    }
}
