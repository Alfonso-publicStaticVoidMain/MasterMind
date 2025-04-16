package main;

import controller.Controller;


import model.Model;

import view.View;

/**
 *
 * @author Silvia García Bouza
 * @author Nuria Calo Mosquera
 * @author Alfonso Gallego Fernández
 */
public class MasterMindMain {


    public static void main(String[] args) {
       
        Model model = new Model();

        // Crear la vista, pasando la configuración inicial del modelo
        View view = new View(model.getLength(), model.getMaxTries());

        //  controlador, pasando la vista y el modelo
        Controller controller = new Controller(view, model);

    }
}
