/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.ControllerIndex;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
    private JButton difficultyButton;
    private JButton howToPlayButton;
    private JButton leaderBoardButton;

    public ViewIndex() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setTitle("Mastermind");

        //panel titulo
        titlePanel = new JPanel();
        titlePanel.setBackground(Color.pink);
        titleLabel=new JLabel("MasterMind");
        titlePanel.add(titleLabel);
        add(titlePanel);
        //panel elementos
        playButton = new JButton("Play");
        playButton.setActionCommand("play");
        difficultyButton = new JButton("Difficulty");
        difficultyButton.setActionCommand("difficulty");
        howToPlayButton=new JButton("Hoy to play");
        howToPlayButton.setActionCommand("howToPlay");
        leaderBoardButton=new JButton("Leader Board");
        leaderBoardButton.setActionCommand("leaderBoard");
        buttonPanel= new JPanel();
        add(buttonPanel);
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(playButton);
        buttonPanel.add(difficultyButton);
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
        difficultyButton.addActionListener(controller);
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
