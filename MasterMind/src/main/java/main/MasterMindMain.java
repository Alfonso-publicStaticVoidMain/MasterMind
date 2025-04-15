package main;

import controller.MasterMindController;
import model.MasterMindModel;
import view.MasterMindView;

/**
 *
 * @author Silvia García Bouza
 * @author Nuria Calo Mosquera
 * @author Alfonso Gallego Fernández
 */
public class MasterMindMain {

    public static void main(String[] args) {
        MasterMindView view = new MasterMindView();
        MasterMindModel model= new MasterMindModel();
        MasterMindController controller = new MasterMindController(view, model);
    }
}
