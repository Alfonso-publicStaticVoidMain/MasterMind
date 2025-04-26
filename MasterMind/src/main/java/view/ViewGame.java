package view;

import JElementos.RoundedTextField;
import JElementos.Colors;
import JElementos.PersonalizedButton;
import controller.ControllerGame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Silvia García Bouza
 * @author Nuria Calo Mosquera
 * @author Alfonso Gallego Fernández
 */
public class ViewGame extends javax.swing.JFrame {

    //Paneles de la vista
    private JPanel titlePanel;
    private JPanel triesLeftPanel;
    private JPanel userInputPanel;
    private JPanel previousTriesPanel;
    private JPanel bottomPanel;
    private JPanel backPanel;
    //Elementos
    private JLabel titleLabel;
    /**
     * Array de 4 textfield para que el usuario escriba los 4 digitos.
     */
    private RoundedTextField[] userInputs;
    /**
     * Array de 4 JTextArea donde se muestran los intentos previos.
     */
    private JTextArea[][] previousTries;
    /**
     * Botón para que el usuario valide su intento.
     */
    private JButton submitButton;
    private JTextField triesLeftField;
    /**
     * Logo
     */
    private ImageIcon titleImage;
    /**
     * Boton hacia atras
     */
    private JButton backButton;

    //variables para los panles
    private int length = 4;
    private int maxTries = 10;

    public ViewGame() {
    }

    public void createView(int length, int maxTries) {
        this.length = length;
        this.maxTries = maxTries;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        //titulo
        setTitle("Mastermind");

        // panel titulo
        titlePanel = new JPanel();
        titlePanel.setBackground(Colors.BACKGROUND);
        titlePanel.setBorder(new EmptyBorder(20, 0, 0, 0)); // Top, Left, Bottom, Right
        titlePanel.setPreferredSize(new Dimension(150, 85));  // Establece un tamaño preferido inicial
        if (maxTries == 10) {
            titlePanel.setPreferredSize(new Dimension(120, 65));
        }
        titleImage = new ImageIcon(getClass().getResource("/widTitle.png"));
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

        //panel score
        triesLeftPanel = new JPanel();
        triesLeftPanel.setBackground(Colors.BACKGROUND);
        if (maxTries == 10) {
            triesLeftPanel.setPreferredSize(new Dimension(360, 17));
        } else {
            triesLeftPanel.setPreferredSize(new Dimension(360, 5));
        }
        triesLeftField = new JTextField("Intentos restantes: " + this.maxTries);
        triesLeftField.setEditable(false);
        triesLeftField.setBackground(Colors.BACKGROUND);
        triesLeftPanel.add(triesLeftField);
        //Panel para el usuario
        userInputs = new RoundedTextField[this.length];
        userInputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        userInputPanel.setBackground(Colors.BACKGROUND);
        if (maxTries > 10) {
            userInputPanel.setPreferredSize(new Dimension(360, 70));
        } else {
            userInputPanel.setPreferredSize(new Dimension(360, 60));
        }
        for (int i = 0; i < userInputs.length; i++) {

            if (maxTries == 10) {
                userInputs[i] = new RoundedTextField(10);
            } else {
                userInputs[i] = new RoundedTextField(2);
            }

            userInputs[i].setHorizontalAlignment(JTextField.CENTER);//horiz
            userInputs[i].setFont(new Font("Poppins", Font.PLAIN, 18)); // Fuente Poppins
            userInputPanel.add(userInputs[i]);//añades al panel
        }
        userInputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

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
        previousTriesPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 20, 40));
        if (maxTries == 10) {
            previousTriesPanel = new JPanel(new GridLayout(this.maxTries, this.length, 10, 5));
            previousTriesPanel.setBorder(BorderFactory.createEmptyBorder(15, 40, 0, 40));
        }
        previousTriesPanel.setBackground(Colors.BACKGROUND);
        previousTriesPanel.setPreferredSize(new Dimension(200, 200));
        if (maxTries == 10) {
            previousTriesPanel.setPreferredSize(new Dimension(200, 360));
        }
        // A: Ese 10 debería ser o MAX_TRIES do ControllerGame! Pero hai que mirar como facer iso ben
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
        submitButton = PersonalizedButton.slimButton("Submit", "submit");
        //previousTriesText = new JTextField(" ");//todo meter dentro de un scroll
        //previousTriesText.setEditable(false);//intentos previos

        bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setBackground(Colors.BACKGROUND);
        bottomPanel.setPreferredSize(new Dimension(360, 40));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        if (maxTries == 10) {
            submitButton.setPreferredSize(new Dimension(120, 20));
            bottomPanel.setPreferredSize(new Dimension(360, 25));
        }
        submitButton.setBorderPainted(false);

        bottomPanel.add(submitButton);

        //panel hacia atras
        // banel boton detras
        backButton = PersonalizedButton.slimBackButton;
        backPanel = new JPanel();
        backPanel.setBackground(Colors.BACKGROUND);
        backPanel.add(backButton);
        if (maxTries == 10) {
            backPanel.setPreferredSize(new Dimension(100, 25));
        } else {
            backPanel.setPreferredSize(new Dimension(360, 40));
        }

        //se añaden los paneles al contenedor   
        add(titlePanel, 0); // Añadir  parte superior
        add(triesLeftPanel);
        add(userInputPanel);
        add(previousTriesPanel);
        add(bottomPanel);
        add(backPanel);

        //tamaño adaptado, contenido centrado, visible
        setSize(360, 640);
        setResizable(false);//q no ca,bie el tamaño eluser
        // pack();
        setLocationRelativeTo(null);
        setVisible(true);
        userInputs[0].requestFocusInWindow();
    }

    public void setActionListener(ControllerGame controller) {
        for (ActionListener al : backButton.getActionListeners()) {
            backButton.removeActionListener(al);
        }
        backButton.addActionListener(controller);
        this.length = controller.getLength();
        this.maxTries = controller.getMaxTries();
    }

    // El ControllerGame le dice a la ViewGame qué mostrar y dónde
    public void displayFeedback(int attemptNumber, String guess, String[] feedback) {
        if (attemptNumber < this.maxTries) {
            for (int i = 0; i < this.length; i++) {
                previousTries[attemptNumber][i].setText(String.valueOf(guess.charAt(i)));
                switch (feedback[i]) {
                    case "correct" ->
                        previousTries[attemptNumber][i].setBackground(Color.GREEN);
                    case "partial" ->
                        previousTries[attemptNumber][i].setBackground(Color.ORANGE);
                    default ->
                        previousTries[attemptNumber][i].setBackground(Color.RED);
                }
            }
        }
    }

    // El ControllerGame actualiza el texto de los intentos restantes
    public void setTriesLeftText(int triesLeft) {
        triesLeftField.setText("Tries left: " + triesLeft);
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

    public boolean playerChoice(String title, String message) {
        return JOptionPane.showConfirmDialog(
                this,
                message,
                title,
                JOptionPane.YES_NO_OPTION
        ) == JOptionPane.YES_OPTION;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    void isVisible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
