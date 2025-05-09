package view;

import JElements.Colors;
import JElements.PersonalizedButton;
import controller.ControllerIndex;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
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
public class ViewIndex extends javax.swing.JFrame {
    
    /**
     * Panels
     */
    private JPanel titlePanel;
    private JPanel buttonPanel;
    /**
     * Elements
     */
    private JLabel titleLabel;
    private JButton playButton;
    private JButton howToPlayButton;
    private JButton leaderBoardButton;
    private ImageIcon titleImage;

    public ViewIndex() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setTitle("Mastermind");
        Image customIcon = Toolkit.getDefaultToolkit().getImage("src"+File.separator+"main"+File.separator+"resources"+File.separator+"LogoSinTitulo.png");
        setIconImage(customIcon);

        // Title panel.
        titlePanel = new JPanel();
        titlePanel.setBackground(Colors.BACKGROUND);
        titlePanel.setBorder(new EmptyBorder(20, 0, 0, 0)); // Top, Left, Bottom, Right
        titlePanel.setPreferredSize(new Dimension(200, 200));
        titleImage = new ImageIcon(getClass().getResource("/TituloImagen.png"));
        if (titleImage != null) {
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

        //Button panel.
        playButton = PersonalizedButton.bigButton("Game", "game");
        howToPlayButton = PersonalizedButton.bigButton("How to play", "howToPlay");
        leaderBoardButton = PersonalizedButton.bigButton("Leader Board", "leaderBoard");
        buttonPanel = new JPanel();
        buttonPanel.setBackground(Colors.BACKGROUND);
        buttonPanel.setPreferredSize(new Dimension(160,440));
        add(buttonPanel);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.add(playButton);
        buttonPanel.add(howToPlayButton);
        buttonPanel.add(leaderBoardButton);        

        setSize(360, 640);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

    }
    
    public void setActionListener(ControllerIndex controller) {
        if (playButton.getActionListeners().length == 0) playButton.addActionListener(controller);
        if (howToPlayButton.getActionListeners().length == 0) howToPlayButton.addActionListener(controller);
        if (leaderBoardButton.getActionListeners().length == 0) leaderBoardButton.addActionListener(controller);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
