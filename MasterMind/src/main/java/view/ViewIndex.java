package view;

import JElementos.Colors;
import JElementos.PersonalizedButton;
import controller.ControllerIndex;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import javax.swing.Box;
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
public class ViewIndex extends javax.swing.JFrame {
    
    /**
     * Paneles
     */
    private JPanel titlePanel;
    private JPanel buttonPanel;
    /**
     * Elementos
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

          // panel titulo
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
     
       

        //panel elementos
        playButton = new PersonalizedButton("Play",15);
        playButton.setActionCommand("play");
        howToPlayButton=new PersonalizedButton("How to play",15);
        howToPlayButton.setActionCommand("howToPlay");
        leaderBoardButton=new PersonalizedButton("Leader Board",15);
        leaderBoardButton.setActionCommand("leaderBoard");
        buttonPanel = new JPanel();
        buttonPanel.setBackground(Colors.BACKGROUND);
        buttonPanel.setPreferredSize(new Dimension(160,440));
        add(buttonPanel);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.add(playButton);
        buttonPanel.add(howToPlayButton);
        buttonPanel.add(leaderBoardButton);        
        //tamaño adaptado, contenido centrado, visible
        setSize(360, 640);
        setResizable(false);//q no ca,bie el tamaño eluser
        // pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }
    
    public void setActionListener(ControllerIndex controller) {
        playButton.addActionListener(controller);
        howToPlayButton.addActionListener(controller);
        leaderBoardButton.addActionListener(controller);
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
