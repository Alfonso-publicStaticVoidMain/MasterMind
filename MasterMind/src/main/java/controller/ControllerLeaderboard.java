package controller;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import model.ScoreFileHandler;
import view.ViewIndex;
import view.ViewLeaderboard;

/**
 *
 * @author Silvia García Bouza
 * @author Nuria Calo Mosquera
 * @author Alfonso Gallego Fernández
 */
public class ControllerLeaderboard implements ActionListener {

    private ViewLeaderboard view;

    public ControllerLeaderboard(ViewLeaderboard view) {
        this.view = view;
        this.view.setActionListener(this);
        this.displayScores();
    }

    public Map<String, Integer> getScores() {
        return ScoreFileHandler.loadScores();
    }

    public void displayScores() {
        List<Map.Entry<String, Integer>> scoreList = new ArrayList<>(this.getScores().entrySet());
        int row = 1;
        for (Map.Entry<String, Integer> entry : scoreList) {
            String playerName = entry.getKey();
            int score = entry.getValue();

            // Cargar la imagen correspondiente (medalla o nada)
            ImageIcon icon = switch (row) {
                case 1 ->
                    new ImageIcon(getClass().getResource("/medals/gold.png"));
                case 2 ->
                    new ImageIcon(getClass().getResource("/medals/silver.png"));
                case 3 ->
                    new ImageIcon(getClass().getResource("/medals/bronze.png"));
                default ->
                    null;
            };

            // Asignar imagen y texto al JLabel
            JLabel label = this.view.getScoreBoard()[row][0];
            label.setFont(new Font("Noto Sans", Font.PLAIN, 18));
            label.setIcon(icon);
            // Ajustar el nombre con espacios manualmente para alinear el texto
            String formattedName = (row > 3) ? "     " + playerName : playerName;
            label.setText(formattedName);

            this.view.getScoreBoard()[row][1].setText(Integer.toString(score));
            row++;
            if (row > 10) {
                break;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        System.out.println(this.getClass().getSimpleName() + " action received: " + command);

        switch (command) {
            case "back" -> {
                SwingUtilities.invokeLater(() -> {
                    view.dispose();
                    ControllerIndex controllerIndex = new ControllerIndex(new ViewIndex());
                });
            }
        }
    }

}
