package main;

import controller.ControllerIndex;
import controller.ControllerLeaderboard;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import model.ModelGame;
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
        // 1. Configuración inicial de la interfaz gráfica
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> {
            ViewIndex view = new ViewIndex();
            ModelGame model = new ModelGame();
            ControllerIndex controller = new ControllerIndex(view, model);
        });

    }
}
