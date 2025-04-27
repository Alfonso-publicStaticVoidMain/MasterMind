package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import view.ViewDifficulty;
import view.ViewHowToPlay;
import view.ViewIndex;
import view.ViewLeaderboard;

/**
 *
 * @author Silvia García Bouza
 * @author Nuria Calo Mosquera
 * @author Alfonso Gallego Fernández
 */
public class ControllerIndex implements ActionListener {

    private ViewIndex view;

    public ControllerIndex(ViewIndex view) {
        this.view = view;
        this.view.setActionListener(this);
        this.view.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        System.out.println(this.getClass().getSimpleName()+" action received: "+command);

        switch (command) {
            case "game" -> {
                SwingUtilities.invokeLater( () -> {
                    view.dispose();
                    ControllerDifficulty controllerDifficulty = new ControllerDifficulty(new ViewDifficulty());
                });
            }

            case "howToPlay" -> {
                SwingUtilities.invokeLater( () -> {
                    view.dispose();
                    ControllerHowToPlay controllerHowToPlay = new ControllerHowToPlay(new ViewHowToPlay());
                });
            }

            case "leaderBoard" -> {
                SwingUtilities.invokeLater( () -> {
                    view.dispose();
                    ControllerLeaderboard controllerLeaderboard = new ControllerLeaderboard(new ViewLeaderboard());
                });
            }

            case "exit" -> {
                view.dispose();
            }
        }
    }

}
