package main;

import controller.ControllerGame;
import controller.ControllerIndex;
import controller.ControllerLeaderboard;
import java.util.LinkedHashMap;
import java.util.Map;

import model.ModelGame;

import view.ViewGame;
import view.ViewIndex;
import view.ViewLeaderboard;

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
