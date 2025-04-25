package view;

import JElementos.Colors;
import JElementos.PersonalizedButton;
import controller.ControllerLeaderboard;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author silvia
 */
public class ViewLeaderboard extends javax.swing.JFrame {

    //Paneles de la vista
    private JPanel titlePanel;
    private JPanel midTitlePanel;
    private JPanel scorePanel;
    private JPanel backPanel;
    //Elementos
    private JLabel titleLabel;
    private JLabel midTitleLabel;
    private JLabel[][] scoreBoard;
    private JButton backButton;
    private ImageIcon titleImage;

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
        titlePanel = new JPanel();
        titlePanel.setBackground(Colors.BACKGROUND);
        titlePanel.setBorder(new EmptyBorder(20, 0, 0, 0)); // Top, Left, Bottom, Right
        titlePanel.setPreferredSize(new Dimension(200, 200)); // Establece un tamaño preferido inicial

        titleImage = new ImageIcon(getClass().getResource("/TituloImagen.png"));

        if (titleImage != null) {
            // Escala la imagen al tamaño PREFERIDO del panel
            Image imagenEscalada = titleImage.getImage().getScaledInstance(
                    titlePanel.getPreferredSize().width,
                    titlePanel.getPreferredSize().height - titlePanel.getInsets().top - titlePanel.getInsets().bottom,
                    Image.SCALE_SMOOTH
            );
            ImageIcon tituloImagenEscalada = new ImageIcon(imagenEscalada);
            titleLabel = new JLabel(tituloImagenEscalada);
            titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
            titlePanel.add(titleLabel);
        } else {
            titleLabel = new JLabel("¡Imagen no encontrada!");
            titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
            titlePanel.add(titleLabel);
        }

        add(titlePanel);

        // panel do título "Scoreboard" e seu label
        midTitlePanel = new JPanel();
        midTitleLabel = new JLabel("Scoreboard");
        midTitleLabel.setForeground(new Color(41, 128, 185));
        midTitlePanel.add(midTitleLabel);
        midTitlePanel.setBackground(Colors.BACKGROUND);
        midTitlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        add(midTitlePanel);

        // panel que contén as scores
        scorePanel = new JPanel(new GridLayout(10, 2));
        scorePanel.setBackground(Colors.BACKGROUND);
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

         // banel boton detras
        backButton = new PersonalizedButton("← Back",15);
        backButton.setActionCommand("back");
        backButton.setPreferredSize(new Dimension(100, 50));
        backPanel= new JPanel();
        backPanel.setBackground(Colors.BACKGROUND);
        backPanel.add(backButton);
        backPanel.setPreferredSize(new Dimension(160,180));
        add(backPanel);

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
