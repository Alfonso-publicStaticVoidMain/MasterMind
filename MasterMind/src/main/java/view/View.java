/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.Controller;
import controller.MasterMindController;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author silvia
 */
public class View extends javax.swing.JFrame {
    //Paneles de la vista
    private final JPanel titlePanel;
    private final JPanel userInputPanel;
    private final JPanel previousTriesPanel;
    private final JPanel bottomPanel;
    //Elementos
     private final JLabel titleLabel;
    /**
     * Array de 4 textfield para que el usuario escriba los 4 digitos.
     */
    private final JTextField[] userInputs;
    /**
     * Array de 4 JTextArea donde se muestran los intentos previos.
     */
    private final JTextArea[][] previousTries;
    /**
     * Botón para que el usuario valide su intento.
     */
    private final JButton submitButton;
    
    private final JTextField triesLeftField;

  
   //variables para los panles
    private int length=4;
    private int maxTries=10;
    private int triesLeft=10;
    private JLabel scoreLabel = new JLabel("Score: 0");

  
    public View() {
        
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        //titulo
        setTitle("Mastermind");
        
         //panel titulo
        titlePanel = new JPanel();
        titlePanel.setBackground(Color.pink);

        titleLabel = new JLabel("Mastermind");
        //titleLabel.setFont(); 
        titleLabel.setForeground(new Color(41, 128, 185));
        titlePanel.add(titleLabel);

        //Panel para el usuario
        userInputs = new JTextField[this.length];
        userInputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        userInputPanel.setBackground(Color.pink);
        userInputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));

        titlePanel.add(scoreLabel); // 🚀 Show score next to the game title
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Bigger, clearer text
        scoreLabel.setForeground(new Color(41, 128, 185)); // Stylish blue color
        scoreLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Space around label

        for (int i = 0; i < userInputs.length; i++) {
            userInputs[i] = new JTextField(3);//ancho para un digito
            userInputs[i].setHorizontalAlignment(JTextField.CENTER);//horiz
            userInputs[i].setFont(new Font("Poppins", Font.PLAIN, 18)); // Fuente Poppins
//           final int width = 30;  // Establecer un ancho fijo
//            final int height = 30; // Establecer un alto fijo
//            numberInputs[i].setBorder(BorderFactory.createCompoundBorder(
//                    BorderFactory.createRoundedBorder(new RoundRectangle2D.Double(0, 0, width, height, 8, 8), new Color(52, 152, 219)), // Borde redondeado azul
//                    BorderFactory.createEmptyBorder(0, 5, 0, 5) // Espaciado interno
//            ));
            userInputPanel.add(userInputs[i]);//añades al panel
        }
        userInputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));

        for (int i = 0; i < userInputs.length; i++) {
            final int currentIndex = i;
            userInputs[i].addKeyListener(new java.awt.event.KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent evt) {
                    if (Character.isDigit(evt.getKeyChar())) {
                        userInputs[currentIndex].setText(String.valueOf(evt.getKeyChar())); // Set input

                        if (currentIndex < userInputs.length - 1) {
                            userInputs[currentIndex + 1].requestFocusInWindow(); // Move to next field
                        }
                        evt.consume(); // Prevent extra characters
                    }
                }
            });
        }

        //Panel para intentos de usuario:
        previousTries = new JTextArea[this.maxTries][this.length];
        //ultimos 10 10para añadir espacio entre celdas
        previousTriesPanel = new JPanel(new GridLayout(this.maxTries, this.length, 10, 10));
        previousTriesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
        previousTriesPanel.setBackground(Color.PINK);
        // A: Ese 10 debería ser o MAX_TRIES do Controller! Pero hai que mirar como facer iso ben
        for (int i = 0; i < maxTries; i++) {
            for (int j = 0; j < length; j++) {
                previousTries[i][j] = new JTextArea(1, 3);
                previousTries[i][j].setEditable(false);
                previousTries[i][j].setBorder(BorderFactory.createLineBorder(new Color(221, 221, 221), 2));
                previousTries[i][j].setBackground(Color.WHITE);
                previousTriesPanel.add(previousTries[i][j]);
            }
        }
        
        //panel boton  inferior
         // Submit button & tries left display
        submitButton = new JButton("Submit");
        submitButton.setActionCommand("submit");
        //previousTriesText = new JTextField(" ");//todo meter dentro de un scroll
        //previousTriesText.setEditable(false);//intentos previos
        triesLeftField = new JTextField("Intentos restantes: " + this.maxTries);
        triesLeftField.setEditable(false);

        bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));

        submitButton.setBorderPainted(false);

        bottomPanel.add(submitButton);
        bottomPanel.add(triesLeftField);
        
        
          //se añaden los paneles al contenedor   
        add(titlePanel, 0); // Añadir  parte superior
        add(userInputPanel);
        add(previousTriesPanel);
        add(bottomPanel);
        
        
          //tamaño adaptado, contenido centrado, visible
        setSize(360, 640);
        setResizable(false);//q no ca,bie el tamaño eluser
        // pack();
        setLocationRelativeTo(null);
        setVisible(true);
        
        
        
        
    }
    
    
    
    
    public void setActionListener(Controller controller){
        submitButton.addActionListener(controller);
         this.length= controller.getLength();
         this.maxTries= controller.getMaxTries();
         this.triesLeft= controller.getTriesLeft();
         
   
       
        }
        
          public void displayFeedback(String guess, String[] feedback) {
        int currentTry = this.length - this.triesLeft - 1;
        if (currentTry < this.triesLeft) {
        //if (currentTry < this.maxTries) {
            for (int i = 0; i < this.length; i++) {
                previousTries[currentTry][i].setText(String.valueOf(guess.charAt(i)));
                switch (feedback[i]) {
                    case "correct" ->
                        previousTries[currentTry][i].setBackground(Color.GREEN);
                    case "partial" ->
                        previousTries[currentTry][i].setBackground(Color.ORANGE);
                    default ->
                        previousTries[currentTry][i].setBackground(Color.RED);
                }
            }
        }
    }  
                 
            public void setTriesLeftText() {
        triesLeftField.setText("Tries left: " + this.triesLeft);
    }
    

    
    // Obtener los digitos introducidos por el usuario
    public String getUserDigits() {
        StringBuilder digits = new StringBuilder();
        for (JTextField textField : userInputs) {
            digits.append(textField.getText());
        }
        return digits.toString();
    }
    //borrar digitos
    public void clearInputFields() {
        for (JTextField textField : userInputs) {
            textField.setText("");
        }
        userInputs[0].requestFocusInWindow();
    }

    //cambiar texto intentos restantes
    public void setTriesLeftText(int triesLeft) {
        triesLeftField.setText("Tries left: " + triesLeft);
    }
    
    
        public void disableInputs() {
        for (JTextField field : userInputs) {
            field.setEnabled(false);
        }
        submitButton.setEnabled(false);
    }
     //Nome xogadores
    public String getPlayerName() {
        String playerName = JOptionPane.showInputDialog(
                this,
                "Enter your name for the leaderboard:",
                "Player Name",
                JOptionPane.QUESTION_MESSAGE
        );

        if (playerName == null || playerName.trim().isEmpty()) {
            playerName = "Player"; // Default name if user cancels
        }

        return playerName; // Return the entered name
    }
 //Puntuaxe Máximo Logrado
    public void showLeaderboard(ArrayList<String> names, ArrayList<Integer> scores) {
        StringBuilder leaderboardText = new StringBuilder("🏆 High Scores 🏆\n");
        for (int i = 0; i < names.size(); i++) {
            leaderboardText.append((i + 1)).append(". ").append(names.get(i))
                    .append(" - ").append(scores.get(i)).append(" points\n");
        }

        JOptionPane.showMessageDialog(
                this,
                leaderboardText.toString(),
                "Leaderboard",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
    
     public void setScoreText(int score) {
        scoreLabel.setText("Score: " + score);
    }
  //Borrar intentos anteriores
    public void clearPreviousTries() {
        for (int i = 0; i < previousTries.length; i++) {
            for (int j = 0; j < previousTries[i].length; j++) {
                previousTries[i][j].setText(""); // Clear text
                previousTries[i][j].setBackground(Color.WHITE); // Reset color
            }
        }
    }
 //Volver a xogar
    public void enableInputs() {
        for (JTextField field : userInputs) {
            field.setText("");  // Clear existing input
            field.setEnabled(true);  // 🚀 Make sure users can type again
        }
        submitButton.setEnabled(true);
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pack();
    }// </editor-fold>//GEN-END:initComponents

 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
