package main;

import controller.Controller;
import controller.MasterMindController;
import model.MasterMindModel;
import model.Model;
import view.MasterMindView;
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
