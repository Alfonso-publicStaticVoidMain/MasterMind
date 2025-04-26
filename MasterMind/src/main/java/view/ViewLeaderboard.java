package view;

import JElementos.Colors;
import JElementos.PersonalizedButton;
import controller.ControllerLeaderboard;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.File;
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
 * @author Silvia García Bouza
 * @author Nuria Calo Mosquera
 * @author Alfonso Gallego Fernández
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image customIcon = Toolkit.getDefaultToolkit().getImage("src" + File.separator + "main" + File.separator + "resources" + File.separator + "LogoSinTitulo.png");
        setIconImage(customIcon);
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
        midTitleLabel = new JLabel("Scoreboard 🏆");
        midTitleLabel.setFont(new Font("Segoe UI Emoji", Font.BOLD, 22));
        midTitleLabel.setForeground(Colors.TEXT);
        midTitleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
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
                if (row == 0) {
                    scoreBoard[row][col].setFont(new Font("Arial", Font.BOLD, 20));
                } else {
                    scoreBoard[row][col].setFont(new Font("Arial", Font.PLAIN, 18));
                }
                scorePanel.add(scoreBoard[row][col]);
            }
        }
        
        scorePanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
        add(scorePanel);

        // panel boton detras
        backButton = PersonalizedButton.midBackButton;
//        backButton.setActionCommand("back");
//        backButton.setPreferredSize(new Dimension(100, 50));
        backPanel = new JPanel();
        backPanel.setBackground(Colors.BACKGROUND);
        backPanel.add(backButton);
        backPanel.setPreferredSize(new Dimension(160, 180));
        add(backPanel);

        //tamaño adaptado, contenido centrado, visible
        setSize(360, 640);
        setResizable(false);//q no ca,bie el tamaño eluser
        // pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void setActionListener(ControllerLeaderboard controller) {
        for (ActionListener al : backButton.getActionListeners()) {
            backButton.removeActionListener(al);
        }
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
