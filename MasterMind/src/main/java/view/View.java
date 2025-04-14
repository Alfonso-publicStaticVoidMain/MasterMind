/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.Controller;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author silvia
 */
public class View extends javax.swing.JFrame {
    
    
    private JTextArea userTriesText;
    private JButton submitButton;
    private JTextField previousTriesText;
    private JTextField triesLeftNumbersText;
    //TODO texto final de Acertaste! para colocar nmeros y que se vea en verde por ejemplo
    
    private Controller controller;
    
    
    public View() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        
        userTriesText= new JTextArea( );
        submitButton= new JButton("Submit");
        submitButton.setActionCommand("submit");
        previousTriesText= new JTextField(" ");//todo meter dentro de un scroll
        previousTriesText.setEditable(false);//intentos previos
        triesLeftNumbersText= new JTextField("intentos restantes: 10");
        triesLeftNumbersText.setEditable(false);
        
        add(userTriesText);
        add(submitButton);
        add(previousTriesText);
        add(triesLeftNumbersText);
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
   
    }
    
    public void setController(Controller controller){
        this.controller=controller;
        submitButton.addActionListener((ActionListener) controller);
    }

    public String getUserTry(){
        return userTriesText.getText();
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
