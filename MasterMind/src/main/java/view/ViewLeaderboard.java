package view;

import controller.ControllerGame;
import controller.ControllerLeaderboard;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author silvia
 */
public class ViewLeaderboard extends javax.swing.JFrame {

    //Paneles de la vista
    private JPanel topTitlePanel;
    private JPanel midTitlePanel;
    private JPanel bottomScorePanel;
    //Elementos
    private JLabel topTitleLabel;
    private JLabel midTitleLabel;
    private JLabel[][] scoreBoard;
    
    private ControllerLeaderboard controller;

    public ViewLeaderboard() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        bottomScorePanel = new JPanel(new GridLayout(10, 2));
        scoreBoard = new JLabel[10][2];
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 2; col++) {
                scoreBoard[row][col] = new JLabel();
                if (row == 0) scoreBoard[row][col].setText(col == 0 ? "Player" : "Score");
                scoreBoard[row][col].setHorizontalAlignment(col == 0 ? SwingConstants.LEFT : SwingConstants.RIGHT);
                bottomScorePanel.add(scoreBoard[row][col]);
            }
        }
        bottomScorePanel.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 100));
        add(bottomScorePanel);
        //tamaño adaptado, contenido centrado, visible
        setSize(360, 640);
        setResizable(false);//q no ca,bie el tamaño eluser
        // pack();
        setLocationRelativeTo(null);
        setVisible(true);
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
