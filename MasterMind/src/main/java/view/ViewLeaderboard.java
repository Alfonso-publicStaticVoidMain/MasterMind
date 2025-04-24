package view;

import controller.ControllerLeaderboard;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author silvia
 */
public class ViewLeaderboard extends javax.swing.JFrame {

    //Paneles de la vista
    private JPanel topTitlePanel;
    private JPanel midTitlePanel;
    private JPanel scorePanel;
    private JPanel bottomPanel;
    //Elementos
    private JLabel topTitleLabel;
    private JLabel midTitleLabel;
    private JLabel[][] scoreBoard;
    private JButton backButton;

    private ControllerLeaderboard controller;

    public ViewLeaderboard() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (controller != null) {
                    controller.onExit();
                }
                System.exit(0);
            }
        });
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setTitle("Mastermind");

        // panel do título "Mastermind" e seu label
        topTitlePanel = new JPanel();
        topTitlePanel.setBackground(Color.pink);
        topTitleLabel = new JLabel("Mastermind");
        topTitleLabel.setForeground(new Color(41, 128, 185));
        topTitlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        topTitlePanel.add(topTitleLabel);
        add(topTitlePanel);

        // panel do título "Scoreboard" e seu label
        midTitlePanel = new JPanel();
        midTitlePanel.setBackground(Color.pink);
        midTitleLabel = new JLabel("Scoreboard");
        midTitleLabel.setForeground(new Color(41, 128, 185));
        midTitlePanel.add(midTitleLabel);
        midTitlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        add(midTitlePanel);

        // panel que contén as scores
        scorePanel = new JPanel(new GridLayout(10, 2));
        scoreBoard = new JLabel[10][2];
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 2; col++) {
                scoreBoard[row][col] = new JLabel();
                if (row == 0) {
                    scoreBoard[row][col].setText(col == 0 ? "Player" : "Score");
                }
                scoreBoard[row][col].setHorizontalAlignment(col == 0 ? SwingConstants.LEFT : SwingConstants.RIGHT);
                scorePanel.add(scoreBoard[row][col]);
            }
        }
        scorePanel.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 100));
        add(scorePanel);

        // panel que contén o botón de volta atrás
        bottomPanel = new JPanel();
        backButton = new JButton("Back");
        backButton.setBorderPainted(false);
        bottomPanel.add(backButton);
        add(bottomPanel);

        //tamaño adaptado, contenido centrado, visible
        setSize(360, 640);
        setResizable(false);//q no ca,bie el tamaño eluser
        // pack();
        setLocationRelativeTo(null);

    }

    //Añadir botón para ir para atrás
    public JButton getBackButton() {
        return backButton;
    }

    public void setActionListener(ControllerLeaderboard controller) {
        backButton.addActionListener(controller);
    }

    public JLabel[][] getScoreBoard() {
        return scoreBoard;
    }

    public void setController(ControllerLeaderboard controller) {
        this.controller = controller;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
