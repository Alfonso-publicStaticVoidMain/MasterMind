package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import view.ViewHowToPlay;
import view.ViewIndex;

/**
 *
 * @author Silvia García Bouza
 * @author Nuria Calo Mosquera
 * @author Alfonso Gallego Fernández
 */
public class ControllerHowToPlay implements ActionListener {

    private ViewHowToPlay view;

    public ControllerHowToPlay(ViewHowToPlay view) {
        this.view = view;
        this.view.setActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        System.out.println(this.getClass().getSimpleName()+" action received: "+command);
        
        switch (command) {
            case "back" -> {
                SwingUtilities.invokeLater( () -> {
                    view.dispose();
                    ControllerIndex controllerIndex = new ControllerIndex(new ViewIndex());
                });
            }
        }
    }

}
