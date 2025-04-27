package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import model.ModelGame;
import view.ViewDifficulty;
import view.ViewGame;
import view.ViewIndex;

/**
 *
 * @author Silvia García Bouza
 * @author Nuria Calo Mosquera
 * @author Alfonso Gallego Fernández
 */
public class ControllerDifficulty implements ActionListener {

    private ViewDifficulty view;  

    public ControllerDifficulty(ViewDifficulty view) {
        this.view = view;
        this.view.setActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        System.out.println(this.getClass().getSimpleName()+" action received: "+command);
        
        switch (command) {
            case "practice" -> {
                SwingUtilities.invokeLater( () -> {
                    view.dispose();
                    ControllerGame controllerGame = new ControllerGame(new ViewGame(), new ModelGame(4, 10));
                });
            }
            case "play" -> {
                SwingUtilities.invokeLater( () -> {
                    view.dispose();
                    ControllerGame controllerGame = new ControllerGame(new ViewGame(), new ModelGame(5, 5));
                });
            }
            case "back" -> {
                SwingUtilities.invokeLater( () -> {
                    view.dispose();
                    ControllerIndex controllerIndex = new ControllerIndex(new ViewIndex());
                });
            }

        }

    }
}
