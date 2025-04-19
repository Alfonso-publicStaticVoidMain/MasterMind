package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.ViewHowToPlay;
import view.ViewIndex;

/**
 *
 * @author nuriacalo
 */
public class ControllerHowToPlay implements ActionListener {

    private ViewHowToPlay view;
    private ViewIndex viewIndex;

    public ControllerHowToPlay(ViewHowToPlay view, ViewIndex viewIndex) {
        this.view = view;
        this.viewIndex = viewIndex;
        this.view.setActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        if ("← Back".equals(e.getActionCommand())) {
            view.setVisible(false);
            viewIndex.setVisible(true); // Volver ao Index
        }
    }
}
