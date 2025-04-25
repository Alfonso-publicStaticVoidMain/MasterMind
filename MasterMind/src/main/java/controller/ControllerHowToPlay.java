package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.ModelGame;
import view.ViewHowToPlay;
import view.ViewIndex;

/**
 *
 * @author nuriacalo
 */
public class ControllerHowToPlay implements ActionListener {

    private ViewHowToPlay view;
    private ViewIndex viewIndex;
    ModelGame model = new ModelGame();

    public ControllerHowToPlay(ViewHowToPlay view, ViewIndex viewIndex) {
        this.view = view;
        this.viewIndex = viewIndex;
        this.view.setActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "back" -> {
                view.dispose();
                viewIndex.setVisible(true);
            }
        }
    }

}
