package main;

import controller.ControllerIndex;

import model.ModelGame;

import view.ViewIndex;

/**
 *
 * @author Silvia García Bouza
 * @author Nuria Calo Mosquera
 * @author Alfonso Gallego Fernández
 */
public class MasterMindMain {

    public static void main(String[] args) {

        ModelGame model = new ModelGame();
        ViewIndex view = new ViewIndex();
        ControllerIndex c = new ControllerIndex(view, model);
    }
}
