package view;

import JElements.Colors;
import JElements.PersonalizedButton;
import controller.ControllerDifficulty;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.File;
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
 * @author Silvia García Bouza
 * @author Nuria Calo Mosquera
 * @author Alfonso Gallego Fernández
 */
public class ViewDifficulty extends javax.swing.JFrame {

    /**
     * Panels
     */
    private JPanel titlePanel;
    private JPanel buttonPanel;
    private JPanel backPanel;

    /**
     * Elements
     */
    private JLabel titleLabel;
    private JButton easyButton;
    private JButton difficultButton;
    private ImageIcon titleImage;
    private JButton backButton;

    public ViewDifficulty() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setTitle("Mastermind");
        getContentPane().setBackground(Colors.BACKGROUND);
        Image customIcon = Toolkit.getDefaultToolkit().getImage("src"+File.separator+"main"+File.separator+"resources"+File.separator+"LogoSinTitulo.png");
        setIconImage(customIcon);
        
        titlePanel = new JPanel();
        titlePanel.setBackground(Colors.BACKGROUND);
        titlePanel.setBorder(new EmptyBorder(20, 0, 0, 0)); // Top, Left, Bottom, Right
        titlePanel.setPreferredSize(new Dimension(200, 230)); 
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
        easyButton = PersonalizedButton.bigButton("Practice", "practice");
        difficultButton = PersonalizedButton.bigButton("Play", "play");
        buttonPanel = new JPanel();
        buttonPanel.setBackground(Colors.BACKGROUND);
        add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Center horizontal. Apace Vertica.
        buttonPanel.add(Box.createHorizontalStrut(20)); // Spaces between buttons
        buttonPanel.add(easyButton);
        buttonPanel.add(Box.createHorizontalStrut(20)); // Spaces between buttons
        buttonPanel.add(difficultButton);
        buttonPanel.setBackground(Colors.BACKGROUND);
        buttonPanel.setPreferredSize(new Dimension(160, 100));

        // Back button panel.
        backButton = PersonalizedButton.midBackButton;
        backPanel = new JPanel();
        backPanel.setBackground(Colors.BACKGROUND);
        backPanel.add(backButton);
        backPanel.setPreferredSize(new Dimension(160, 100));
        add(backPanel);

        setSize(360, 640);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void setActionListener(ControllerDifficulty controller) {
        for (ActionListener al : easyButton.getActionListeners()) {
            easyButton.removeActionListener(al);
        }
        easyButton.addActionListener(controller);
        for (ActionListener al : difficultButton.getActionListeners()) {
            difficultButton.removeActionListener(al);
        }
        difficultButton.addActionListener(controller);
        for (ActionListener al : backButton.getActionListeners()) {
            backButton.removeActionListener(al);
        }
        backButton.addActionListener(controller);
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
