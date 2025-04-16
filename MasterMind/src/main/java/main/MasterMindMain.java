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
        View view = new View();
        Model model= new Model();
        Controller controller = new Controller(view, model);
    }
}
