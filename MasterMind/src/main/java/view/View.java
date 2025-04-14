package view;

import controller.Controller;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author silvia
 */
public class View extends javax.swing.JFrame {

    //array de 4 textfield para que el usuario escriba los 4 digitos
    /**
     * A length-4 array of text fields for the user to input their numbers.
     */
    private JTextField[] textFields;
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
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        //Panel para el usuario
        textFields = new JTextField[4];
        JPanel fieldsPanel = new JPanel(new FlowLayout());
        for (int i = 0; i < textFields.length; i++) {
            textFields[i] = new JTextField(3);//ancho para un digito
            textFields[i].setHorizontalAlignment(JTextField.CENTER);//horiz
            fieldsPanel.add(textFields[i]);//añades al panel
        }

        //Panel para intentos de usuario:
        userTriesTexts = new JTextArea[10][4];
        JPanel triesPanel = new JPanel(new GridLayout(10, 4));

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 4; j++) {
                userTriesTexts[i][j] = new JTextArea(10, 4); // 1 fila, 3 columnas de ancho
                userTriesTexts[i][j].setEditable(false); // No permitir edición directa
                //userTriesTexts[i][j].setHorizontalAlignment(SwingConstants.CENTER); // Centrar texto
                triesPanel.add(userTriesTexts[i][j]);
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
        buttonPanel.add(submitButton);
        buttonPanel.add(previousTriesText);
        buttonPanel.add(triesLeftNumbersText);

        //se añaden los paneles al contenedor       
        add(fieldsPanel);
        add(triesPanel);
        add(buttonPanel);

        //tamaño adaptado, contenido centrado, visible
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    //añadir los listeners
    public void setController(Controller controller) {
        this.controller = controller;
        submitButton.addActionListener((ActionListener) controller);

        // Añadir KeyListeners a todos los JTextFields y limitar a un único dígito
        for (JTextField textField : textFields) {
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
        for (int i = 0; i < textFields.length - 1; i++) {
            final int currentIndex = i;
            textFields[i].addKeyListener(new java.awt.event.KeyListener() {
                @Override
                public void keyTyped(KeyEvent evt) {
                }

                @Override
                public void keyPressed(KeyEvent evt) {
                }

                @Override
                public void keyReleased(KeyEvent evt) {
                    if (textFields[currentIndex].getText().length() == 1) {
                        textFields[currentIndex + 1].requestFocusInWindow();
                    }
                }
            });
        }

        // AL inicio el cursor en el primer textfield
        textFields[0].requestFocusInWindow();
    }

    // Obtener los 4 digntos introducidos por l usuario
    public String getUserDigits() {
        StringBuilder digits = new StringBuilder();
        for (JTextField textField : textFields) {
            digits.append(textField.getText());
        }
        return digits.toString();
    }

//limpiar los textos del usuario
    public void clearInputFields() {
        for (JTextField textField : textFields) {
            textField.setText("");
        }
        textFields[0].requestFocusInWindow();
    }

    public void setTriesLeftNumbersText(int triesLeft) {
        triesLeftNumbersText.setText("Intentos restantes: " + triesLeft);
    }

    public void displayFeedback(String guess, int correctPositions, int presentDigits) {

        // Verifica si todavía quedan intentos disponibles.
        if (currentTry < 10) {
            // Itera a través de cada dígito del intento (hasta ñ longitud del número secreto).
            for (int i = 0; i < 4; i++) {
                // Asegura que ñestamos dentro de la longitud del intento proporcionado.
                if (i < guess.length()) {
                    // Establece el texto del JTextArea correspondiente en la cuadrícula de intentos
                    // con el dígito del intento actual.
                    userTriesTexts[currentTry][i].setText(String.valueOf(guess.charAt(i)));

                    // Verde: el dígito es correcto y está en la posición correcta.
                    if (correctPositions > i) {
                        userTriesTexts[currentTry][i].setBackground(java.awt.Color.GREEN);
                    } // Amarillo: el dígito es correcto pero está en una posición incorrecta.
                    else if (correctPositions + presentDigits > i) {
                        userTriesTexts[currentTry][i].setBackground(java.awt.Color.YELLOW);
                    } // Color por defecto (null): el dígito no es correcto.
                    else {
                        userTriesTexts[currentTry][i].setBackground(null);
                    }
                } // Si el intento es más corto que el número de dígitos esperado,celda vacia color pered
                else {
                    userTriesTexts[currentTry][i].setText("");
                    userTriesTexts[currentTry][i].setBackground(null);
                }
            }

            // Incrementa el contador de intentos para el siguiente intento.
            currentTry++;
        }
        // Si no quedan intentos (se ha alcanzado MAX_TRIES), este método no hará nada.
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
