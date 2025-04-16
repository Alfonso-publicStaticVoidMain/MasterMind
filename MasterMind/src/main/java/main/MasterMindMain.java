package main;

import controller.ControllerGame;
import controller.ControllerIndex;

import model.ModelGame;

import view.ViewGame;
import view.ViewIndex;

/**
 *
 * @author Silvia García Bouza
 * @author Nuria Calo Mosquera
 * @author Alfonso Gallego Fernández
 */
public class MasterMindMain {

    public static void main(String[] args) {

//        ModelGame model = new ModelGame();
//
//        // Crear la vista, pasando la configuración inicial del modelo
//        ViewGame view = new ViewGame();
//
//        //  controlador, pasando la vista y el modelo
//        ControllerGame controller = new ControllerGame(view, model);
        ViewIndex view = new ViewIndex();
        ControllerIndex c = new ControllerIndex(view);

    }
}
