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

        Map<String, Integer> scores = new LinkedHashMap<>();
        scores.put("Alfonso", 1000);
        scores.put("Silvia", 1500);
        scores.put("Nuria", 2000);
        ModelGame model = new ModelGame();
        model.setScoreHistory(scores);
//
//        // Crear la vista, pasando la configuración inicial del modelo
//        ViewGame view = new ViewGame();
//
//        //  controlador, pasando la vista y el modelo
//        ControllerGame controller = new ControllerGame(view, model);
//        ViewIndex view = new ViewIndex();
//        ControllerIndex c = new ControllerIndex(view);

        ViewLeaderboard viewLeaderboard = new ViewLeaderboard();
        ControllerLeaderboard controllerLeaderboard = new ControllerLeaderboard(viewLeaderboard, model);
        controllerLeaderboard.updateScores();
    }
}
