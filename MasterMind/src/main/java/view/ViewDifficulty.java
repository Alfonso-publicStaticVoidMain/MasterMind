package view;

import JElementos.Colors;
import JElementos.PersonalizedButton;
import controller.ControllerDifficulty;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
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
     * Paneles
     */
    private JPanel titlePanel;
    private JPanel buttonPanel;
    private JPanel backPanel;

    /**
     * Elementos
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
        titlePanel = new JPanel();
        titlePanel.setBackground(Colors.BACKGROUND);
        titlePanel.setBorder(new EmptyBorder(20, 0, 0, 0)); // Top, Left, Bottom, Right
        titlePanel.setPreferredSize(new Dimension(200, 230)); // Establece un tamaño preferido inicial
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
        easyButton = PersonalizedButton.bigButton("Practice", "practice");
//        easyButton.setActionCommand("practice");
        difficultButton = PersonalizedButton.bigButton("Play", "play");
//        difficultButton.setActionCommand("play");
        buttonPanel = new JPanel();
        buttonPanel.setBackground(Colors.BACKGROUND);
        add(buttonPanel, BorderLayout.CENTER);
        // buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Centrar horizontalmente, espacio vertical
        buttonPanel.add(Box.createHorizontalStrut(20)); // Espacio entre botones
        buttonPanel.add(easyButton);
        buttonPanel.add(Box.createHorizontalStrut(20)); // Espacio entre botones
        buttonPanel.add(difficultButton);
        buttonPanel.setBackground(Colors.BACKGROUND);
        buttonPanel.setPreferredSize(new Dimension(160, 100));

        // banel boton detras
        backButton = PersonalizedButton.bigBackButton;
//        backButton.setActionCommand("back");
//        backButton.setPreferredSize(new Dimension(100, 50));
        backPanel = new JPanel();
        backPanel.setBackground(Colors.BACKGROUND);
        backPanel.add(backButton);
        backPanel.setPreferredSize(new Dimension(160, 100));
        add(backPanel);

        //tamaño adaptado, contenido centrado, visible
        setSize(360, 640);
        setResizable(false);//q no ca,bie el tamaño eluser
        // pack();
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
