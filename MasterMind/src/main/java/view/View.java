package view;

import controller.Controller;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author silvia
 */
public class View extends javax.swing.JFrame {

    //array de 4 textfield para que el usuario escriba los 4 digitos
    /**
     * A length-4 array of text fields for the user to input their numbers.
     */
    private  JLabel titleLabel;
    private JTextField[] numberInputs;
    // Array para que se muestreslos JTextArea de los intentos del usuario
    private JTextArea[][] userTriesTexts;
    //Resto de elementos
    private JButton submitButton;
    private JTextField previousTriesText;
    private JTextField triesLeftNumbersText;
    //TODO texto final de Acertaste! para colocar nmeros y que se vea en verde por ejemplo

    private Controller controller;
    private int currentTry = 0; // Para llevar el registro del intento actual

    public View() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BoxLayout bx= new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
        setLayout(bx);
        //titulo
        setTitle("Mastermind");
        //fondo
        getContentPane().setBackground(Color.PINK);
        
        
        
        //panel titulo
            
    
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.pink); 

        titleLabel = new JLabel("Mastermind");
        //titleLabel.setFont(); 
        titleLabel.setForeground(new Color(41, 128, 185)); 
        titlePanel.add(titleLabel);
        
    
        
        
        

        //Panel para el usuario
        numberInputs = new JTextField[4];
        JPanel fieldsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
         fieldsPanel.setBackground(Color.pink);
        fieldsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        for (int i = 0; i < numberInputs.length; i++) {
            numberInputs[i] = new JTextField(3);//ancho para un digito
            numberInputs[i].setHorizontalAlignment(JTextField.CENTER);//horiz
            numberInputs[i].setFont(new Font("Poppins", Font.PLAIN, 18)); // Fuente Poppins
//           final int width = 30;  // Establecer un ancho fijo
//            final int height = 30; // Establecer un alto fijo
//            numberInputs[i].setBorder(BorderFactory.createCompoundBorder(
//                    BorderFactory.createRoundedBorder(new RoundRectangle2D.Double(0, 0, width, height, 8, 8), new Color(52, 152, 219)), // Borde redondeado azul
//                    BorderFactory.createEmptyBorder(0, 5, 0, 5) // Espaciado interno
//            ));
            fieldsPanel.add(numberInputs[i]);//añades al panel
        }
        fieldsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        
        //Panel para intentos de usuario:
        userTriesTexts = new JTextArea[10][4];
        //ultimos 10 10para añadir espacio entre celdas
        JPanel triesPanel = new JPanel(new GridLayout(10, 4,10,10));
        triesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
         triesPanel.setBackground(Color.PINK);


        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 4; j++) {
                userTriesTexts[i][j] = new JTextArea(10, 4); // 1 fila, 3 columnas de ancho
                userTriesTexts[i][j].setEditable(false); // No permitir edición directa
                //userTriesTexts[i][j].setHorizontalAlignment(SwingConstants.CENTER); // Centrar texto
               userTriesTexts[i][j].setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
                userTriesTexts[i][j].setAlignmentX(JTextArea.CENTER_ALIGNMENT);
                userTriesTexts[i][j].setAlignmentY(JTextArea.CENTER_ALIGNMENT);
                  userTriesTexts[i][j].setBorder(BorderFactory.createLineBorder(new Color(221, 221, 221), 2)); // Borde gris claro
                userTriesTexts[i][j].setBorder(BorderFactory.createLineBorder(new Color(221, 221, 221), 2)); // Borde gris claro
                  triesPanel.add(userTriesTexts[i][j]);
                userTriesTexts[i][j].setBackground(Color.WHITE);
            }
        }
        add(triesPanel);

        //panel para el resto de elementos
        submitButton = new JButton("Submit");
        submitButton.setActionCommand("submit");
        previousTriesText = new JTextField(" ");//todo meter dentro de un scroll
        previousTriesText.setEditable(false);//intentos previos
        triesLeftNumbersText = new JTextField("intentos restantes: 10");
        triesLeftNumbersText.setEditable(false);
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        triesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
         submitButton.setBorderPainted(false);
        
        buttonPanel.add(submitButton);
        buttonPanel.add(previousTriesText);
        buttonPanel.add(triesLeftNumbersText);

        //se añaden los paneles al contenedor   
        add(titlePanel, 0); // Añadir  parte superior
        add(fieldsPanel);
        add(triesPanel);
        add(buttonPanel);

        //tamaño adaptado, contenido centrado, visible
        setSize(360, 640); 
        setResizable(false);//q no ca,bie el tamaño eluser
       // pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    //añadir los listeners
    public void setController(Controller controller) {
        this.controller = controller;
        submitButton.addActionListener((ActionListener) controller);
        //tb con enter
         for (JTextField input : numberInputs) {
            input.addActionListener(controller); //todo
        }

        // Añadir KeyListeners a todos los JTextFields y limitar a un único dígito
        for (JTextField textField : numberInputs) {
            textField.addKeyListener(
                new java.awt.event.KeyListener() {
                @Override
                public void keyTyped(KeyEvent evt) {
                    char c = evt.getKeyChar();
                    if (!Character.isDigit(c) || textField.getText().length() >= 1) {
                        evt.consume(); // Ignorar caracteres no numéricos o si ya hay un dígito
                    }
                }

                @Override
                public void keyPressed(KeyEvent evt) {
                }

                @Override
                public void keyReleased(KeyEvent evt) {
                }
            }
            );
        }

        // Añadir KeyListeners para pasar al siguiente JTextField al completar un dígito
        for (int i = 0; i < numberInputs.length - 1; i++) {
            final int currentIndex = i;
            numberInputs[i].addKeyListener(new java.awt.event.KeyListener() {
                @Override
                public void keyTyped(KeyEvent evt) {
                }

                @Override
                public void keyPressed(KeyEvent evt) {
                }

                @Override
                public void keyReleased(KeyEvent evt) {
                    if (numberInputs[currentIndex].getText().length() == 1) {
                        numberInputs[currentIndex + 1].requestFocusInWindow();
                    }
                }
            });
        }

        // AL inicio el cursor en el primer textfield
        numberInputs[0].requestFocusInWindow();
    }

    // Obtener los 4 digntos introducidos por l usuario
    public String getUserDigits() {
        StringBuilder digits = new StringBuilder();
        for (JTextField textField : numberInputs) {
            digits.append(textField.getText());
        }
        return digits.toString();
    }

//limpiar los textos del usuario
    public void clearInputFields() {
        for (JTextField textField : numberInputs) {
            textField.setText("");
        }
        numberInputs[0].requestFocusInWindow();
    }

    public void setTriesLeftNumbersText(int triesLeft) {
        triesLeftNumbersText.setText("Intentos restantes: " + triesLeft);
    }


    
    
     public void displayFeedback(String guess, String[] feedback) {
        if (currentTry < 10) {
            for (int i = 0; i < 4; i++) {
                userTriesTexts[currentTry][i].setText(String.valueOf(guess.charAt(i))); // Mostrar el número
                switch (feedback[i]) {
                    case "correct":
                        userTriesTexts[currentTry][i].setBackground(Color.green);
                        break;
                    case "partial":
                        userTriesTexts[currentTry][i].setBackground(Color.ORANGE);
                        break;
                    default:
                        userTriesTexts[currentTry][i].setBackground(Color.red);
                        break;
                }
            }
            currentTry++;
        }
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

//todo al acertar finaliza el juego

}
