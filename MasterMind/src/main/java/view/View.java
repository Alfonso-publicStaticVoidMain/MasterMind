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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author silvia
 */
public class View extends javax.swing.JFrame {

    private final JPanel userInputPanel;
    private final JPanel previousTriesPanel;
    private final JPanel bottomPanel;
    private final JPanel titlePanel;
    
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
    /**
     * Campo donde se muestran el número de intentos restantes.
     */
    private final JTextField triesLeftField;

    private Controller controller;
    /**
     * Intento actual.
     */
    private int currentTry = 0;
    
    private int length = 4;
    private int maxTries = 10;

    public View() {
        
        Object[] options = {"Easy", "Normal"};
        int n = JOptionPane.showOptionDialog(
            this,
            "Choose the difficulty.",
            "Difficulty menu",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,     //do not use a custom Icon
            options,  //the titles of buttons
            options[0]); //default button title
        switch (n) {
            case 0 -> {
                this.length = 4;
                this.maxTries = 10;
            }
            case 1 -> {
                this.length = 5;
                this.maxTries = 10;
            }
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        //titulo
        setTitle("Mastermind");
        //fondo
        getContentPane().setBackground(Color.PINK);

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

        //Panel para intentos de usuario:
        previousTries = new JTextArea[this.maxTries][this.length];
        //ultimos 10 10para añadir espacio entre celdas
        previousTriesPanel = new JPanel(new GridLayout(this.maxTries, this.length, 10, 10));
        previousTriesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
        previousTriesPanel.setBackground(Color.PINK);
        previousTriesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
        // A: Ese 10 debería ser o MAX_TRIES do Controller! Pero hai que mirar como facer iso ben
        for (int i = 0; i < maxTries; i++) {
            for (int j = 0; j < length; j++) {
                previousTries[i][j] = new JTextArea(this.maxTries, this.length); // 1 fila, 3 columnas de ancho
                previousTries[i][j].setEditable(false); // No permitir edición directa
                //userTriesTexts[i][j].setHorizontalAlignment(SwingConstants.CENTER); // Centrar texto
                previousTries[i][j].setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
                previousTries[i][j].setAlignmentX(JTextArea.CENTER_ALIGNMENT);
                previousTries[i][j].setAlignmentY(JTextArea.CENTER_ALIGNMENT);
                previousTries[i][j].setBorder(BorderFactory.createLineBorder(new Color(221, 221, 221), 2)); // Borde gris claro
                previousTries[i][j].setBorder(BorderFactory.createLineBorder(new Color(221, 221, 221), 2)); // Borde gris claro
                previousTriesPanel.add(previousTries[i][j]);
                previousTries[i][j].setBackground(Color.WHITE);
            }
        }
        add(previousTriesPanel);

        //panel para el resto de elementos
        submitButton = new JButton("Submit");
        submitButton.setActionCommand("submit");
        //previousTriesText = new JTextField(" ");//todo meter dentro de un scroll
        //previousTriesText.setEditable(false);//intentos previos
        triesLeftField = new JTextField("intentos restantes: 10");
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
    
    
//    public View() {
//        
//        this(4, 10);
//        
//        /*
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
//        //titulo
//        setTitle("Mastermind");
//        //fondo
//        getContentPane().setBackground(Color.PINK);
//
//        //panel titulo
//        titlePanel = new JPanel();
//        titlePanel.setBackground(Color.pink);
//
//        titleLabel = new JLabel("Mastermind");
//        //titleLabel.setFont(); 
//        titleLabel.setForeground(new Color(41, 128, 185));
//        titlePanel.add(titleLabel);
//
//        //Panel para el usuario
//        userInputs = new JTextField[4];
//        userInputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
//        userInputPanel.setBackground(Color.pink);
//        userInputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
//        for (int i = 0; i < userInputs.length; i++) {
//            userInputs[i] = new JTextField(3);//ancho para un digito
//            userInputs[i].setHorizontalAlignment(JTextField.CENTER);//horiz
//            userInputs[i].setFont(new Font("Poppins", Font.PLAIN, 18)); // Fuente Poppins
////           final int width = 30;  // Establecer un ancho fijo
////            final int height = 30; // Establecer un alto fijo
////            numberInputs[i].setBorder(BorderFactory.createCompoundBorder(
////                    BorderFactory.createRoundedBorder(new RoundRectangle2D.Double(0, 0, width, height, 8, 8), new Color(52, 152, 219)), // Borde redondeado azul
////                    BorderFactory.createEmptyBorder(0, 5, 0, 5) // Espaciado interno
////            ));
//            userInputPanel.add(userInputs[i]);//añades al panel
//        }
//        userInputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
//
//        //Panel para intentos de usuario:
//        previousTries = new JTextArea[10][4];
//        //ultimos 10 10para añadir espacio entre celdas
//        previousTriesPanel = new JPanel(new GridLayout(10, 4, 10, 10));
//        previousTriesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
//        previousTriesPanel.setBackground(Color.PINK);
//        previousTriesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
//        // A: Ese 10 debería ser o MAX_TRIES do Controller! Pero hai que mirar como facer iso ben
//        for (int i = 0; i < 10; i++) {
//            for (int j = 0; j < 4; j++) {
//                previousTries[i][j] = new JTextArea(10, 4); // 1 fila, 3 columnas de ancho
//                previousTries[i][j].setEditable(false); // No permitir edición directa
//                //userTriesTexts[i][j].setHorizontalAlignment(SwingConstants.CENTER); // Centrar texto
//                previousTries[i][j].setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
//                previousTries[i][j].setAlignmentX(JTextArea.CENTER_ALIGNMENT);
//                previousTries[i][j].setAlignmentY(JTextArea.CENTER_ALIGNMENT);
//                previousTries[i][j].setBorder(BorderFactory.createLineBorder(new Color(221, 221, 221), 2)); // Borde gris claro
//                previousTries[i][j].setBorder(BorderFactory.createLineBorder(new Color(221, 221, 221), 2)); // Borde gris claro
//                previousTriesPanel.add(previousTries[i][j]);
//                previousTries[i][j].setBackground(Color.WHITE);
//            }
//        }
//        add(previousTriesPanel);
//
//        //panel para el resto de elementos
//        submitButton = new JButton("Submit");
//        submitButton.setActionCommand("submit");
//        //previousTriesText = new JTextField(" ");//todo meter dentro de un scroll
//        //previousTriesText.setEditable(false);//intentos previos
//        triesLeftField = new JTextField("intentos restantes: 10");
//        triesLeftField.setEditable(false);
//
//        bottomPanel = new JPanel(new FlowLayout());
//        bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
//        
//        submitButton.setBorderPainted(false);
//
//        bottomPanel.add(submitButton);
//        bottomPanel.add(triesLeftField);
//
//        //se añaden los paneles al contenedor   
//        add(titlePanel, 0); // Añadir  parte superior
//        add(userInputPanel);
//        add(previousTriesPanel);
//        add(bottomPanel);
//
//        //tamaño adaptado, contenido centrado, visible
//        setSize(360, 640);
//        setResizable(false);//q no ca,bie el tamaño eluser
//        // pack();
//        setLocationRelativeTo(null);
//        setVisible(true);
//        */
//    }

    public int getLength() {
        return this.length;
    }
    
    public int getMaxTries() {
        return this.maxTries;
    }
    
    //añadir los listeners
    public void setController(Controller controller) {
        this.controller = controller;
        submitButton.addActionListener(controller);
        //TODO: Averiguar por que leches funciona isto
        //Actualmente o que fai é que se pulsas enter borra os 4 numeros
        for (JTextField input : userInputs) {
            input.addActionListener(controller);
        }

        // Añadir KeyListeners a todos los JTextFields y limitar a un único dígito
        for (JTextField textField : userInputs) {
            textField.addKeyListener(
                    new java.awt.event.KeyListener() {
                @Override
                public void keyTyped(KeyEvent evt) {
                    char c = evt.getKeyChar();
                    if (!Character.isDigit(c)) { //|| textField.getText().length() >= 1) {
                        evt.consume(); // Ignorar caracteres no numéricos
                    } else if (textField.getText().length() >= 1) {
                        textField.setText(""); // Si ya hay contenido en el textField, resetearlo antes de escribir el nuevo
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
        for (int i = 0; i < userInputs.length - 1; i++) {
            final int currentIndex = i;
            userInputs[i].addKeyListener(new java.awt.event.KeyListener() {
                @Override
                public void keyTyped(KeyEvent evt) {
                }

                @Override
                public void keyPressed(KeyEvent evt) {
                }

                @Override
                public void keyReleased(KeyEvent evt) {
                    if (userInputs[currentIndex].getText().length() == 1) {
                        userInputs[currentIndex + 1].requestFocusInWindow();
                    }
                }
            });
        }

        // AL inicio el cursor en el primer textfield
        userInputs[0].requestFocusInWindow();
    }

    // Obtener los 4 digntos introducidos por l usuario
    public String getUserDigits() {
        StringBuilder digits = new StringBuilder();
        for (JTextField textField : userInputs) {
            digits.append(textField.getText());
        }
        return digits.toString();
    }

    //limpiar los textos del usuario
    public void clearInputFields() {
        for (JTextField textField : userInputs) {
            textField.setText("");
        }
        userInputs[0].requestFocusInWindow();
    }

    public void setTriesLeftNumbersText(int triesLeft) {
        triesLeftField.setText("Intentos restantes: " + triesLeft);
    }

    public void displayFeedback(String guess, String[] feedback) {
        if (currentTry < this.getMaxTries()) {
            for (int i = 0; i < this.length; i++) {
                previousTries[currentTry][i].setText(String.valueOf(guess.charAt(i))); // Mostrar el número
                switch (feedback[i]) {
                    case "correct" -> previousTries[currentTry][i].setBackground(Color.green);
                    case "partial" -> previousTries[currentTry][i].setBackground(Color.ORANGE);
                    default -> previousTries[currentTry][i].setBackground(Color.red);
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
