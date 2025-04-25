/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import JElementos.Colors;
import JElementos.PersonalizedButton;
import controller.ControllerDifficulty;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import javax.swing.BorderFactory;
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
        easyButton = new PersonalizedButton("Practice", 15);
        easyButton.setActionCommand("practice");
        difficultButton = new PersonalizedButton("Play", 15);
        difficultButton.setActionCommand("play");
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
        buttonPanel.setPreferredSize(new Dimension(160,230));

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
        setVisible(true);
    }

    public void setActionListener(ControllerDifficulty controller) {
        easyButton.addActionListener(controller);
        difficultButton.addActionListener(controller);
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
