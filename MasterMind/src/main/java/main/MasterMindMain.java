package main;

import controller.ControllerIndex;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
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
        // 1. Configuración inicial de la interfaz gráfica
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> {
            ControllerIndex controller = new ControllerIndex(new ViewIndex());
        });

    }
}
