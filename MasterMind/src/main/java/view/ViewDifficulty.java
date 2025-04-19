/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.ControllerDifficulty;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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

    /**
     * Elementos
     */
    private JLabel titleLabel;
    private JButton easyButton;
    private JButton difficultButton;

    public ViewDifficulty() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setTitle("Mastermind");
        getContentPane().setBackground(Color.pink);

        //panel titulo
        titlePanel = new JPanel();
        titlePanel.setBackground(Color.pink);
        titleLabel = new JLabel("Choose the difficulty");
        titlePanel.add(titleLabel);
        add(titlePanel);
        titlePanel.setPreferredSize(new Dimension(titlePanel.getWidth(), 50));
        //panel elementos
        easyButton = new JButton("Easy");
        easyButton.setActionCommand("Easy");
        difficultButton = new JButton("Difficult");
        difficultButton.setActionCommand("Difficult");
        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.pink);
        add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(Box.createHorizontalStrut(20)); // Espacio entre botones
        buttonPanel.add(easyButton);
        buttonPanel.add(Box.createHorizontalStrut(20)); // Espacio entre botones
        buttonPanel.add(difficultButton);
        buttonPanel.setBackground(Color.pink);

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
