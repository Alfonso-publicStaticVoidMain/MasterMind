package main;

import controller.ControllerIndex;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;
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

        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater( () -> {
            ModelGame model = new ModelGame();
            ViewIndex view = new ViewIndex();
            ControllerIndex c = new ControllerIndex(view, model);
        });
    }

}
