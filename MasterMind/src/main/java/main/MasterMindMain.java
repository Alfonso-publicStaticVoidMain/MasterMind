package main;

import controller.ControllerIndex;
import controller.ControllerLeaderboard;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
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

        // 2. Creación del modelo y las vistas
        ModelGame model = new ModelGame();
        ViewIndex viewIndex = new ViewIndex();
        ViewLeaderboard viewLeaderboard = new ViewLeaderboard();

        // 3. Creación de los controladores
        ControllerIndex controllerIndex = new ControllerIndex(viewIndex, model);
        ControllerLeaderboard controllerLeaderboard = new ControllerLeaderboard(viewLeaderboard, model, viewIndex);
        // 4. Asignar el controlador al leaderboard
        viewLeaderboard.setController(controllerLeaderboard);

        // 5. Configurar qué pasa al cerrar la ventana
        viewIndex.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        viewIndex.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controllerLeaderboard.onExit(); // Guarda puntuaciones
                System.exit(0); // Cierra totalmente la app
            }
        });
        // 6. Mostrar la ventana principal
        viewIndex.setVisible(true);
    }
}
