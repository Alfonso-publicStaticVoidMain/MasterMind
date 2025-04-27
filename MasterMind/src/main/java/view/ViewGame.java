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
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Silvia García Bouza
 * @author Nuria Calo Mosquera
 * @author Alfonso Gallego Fernández
 */
public class ViewGame extends javax.swing.JFrame {

    //View Panels.
    private JPanel titlePanel;
    private JPanel triesLeftPanel;
    private JPanel userInputPanel;
    private JPanel previousTriesPanel;
    private JPanel bottomPanel;
    private JPanel backPanel;
    //Elements.
    private JLabel titleLabel;
    /**
     * Array of 4 textfields for the user to type the 4 digits.
     */
    private JTextField[] userInputs;

    /**
     * Array of 4 JTextArea where the previous attempts are displayed
     */
    private JTextArea[][] previousTries;
    /**
     * Button for the user to validate his attempt.
     */
    private JButton submitButton;
    private JTextField triesLeftField;
    /**
     * MasterMind logo.
     */
    private ImageIcon titleImage;
    /**
     * Back Button.
     */
    private JButton backButton;

    //Variables for panles.
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

        titlePanel = new JPanel();
        titlePanel.setBackground(Colors.BACKGROUND);
        titlePanel.setBorder(new EmptyBorder(20, 0, 0, 0)); // Top, Left, Bottom, Right
        titlePanel.setPreferredSize(new Dimension(150, 110)); 

        if (maxTries == 10) {
            titlePanel.setPreferredSize(new Dimension(120, 65));
        }

        titleImage = new ImageIcon(getClass().getResource("/widTitle.png"));
        if (titleImage != null) {
            // Obtains the original dimensions of the image
            int originalWidth = titleImage.getIconWidth();
            int originalHeight = titleImage.getIconHeight();

            // Gets the size of the panel
            int panelWidth = titlePanel.getPreferredSize().width;
            int panelHeight = titlePanel.getPreferredSize().height;

            // Calculates the aspect ratio of the image
            float aspectRatio = (float) originalWidth / originalHeight;

            // Adjusts the image to fill the entire width of the panel
            int newWidth = panelWidth;
            int newHeight = (int) (panelWidth / aspectRatio);

            if (newHeight < panelHeight) {
                newHeight = panelHeight;
                newWidth = (int) (panelHeight * aspectRatio);
            }

            // Scales the image to the new size.
            Image imagenEscalada = titleImage.getImage().getScaledInstance(
                    newWidth,
                    newHeight,
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

        //Remaining attempts Panel
        triesLeftPanel = new JPanel();
        triesLeftPanel.setBackground(Colors.BACKGROUND);
        if (maxTries == 10) {
            triesLeftPanel.setPreferredSize(new Dimension(360, 17));
        } else {
            triesLeftPanel.setPreferredSize(new Dimension(360, 17));
        }
        triesLeftField = new JTextField("Remaining attempts: " + this.maxTries);
        triesLeftField.setForeground(Colors.TITLE);
        triesLeftField.setFont(new Font("Poppins", Font.BOLD, 16));
        triesLeftField.setBorder(null);
        triesLeftField.setEditable(false);
        triesLeftField.setBackground(Colors.BACKGROUND);
        triesLeftPanel.add(triesLeftField);

        //User panel. 
        userInputs = new RoundedTextField[this.length];
        userInputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        userInputPanel.setBackground(Colors.BACKGROUND);
        Border grayBorder = BorderFactory.createLineBorder(Colors.TITLE);
        if (maxTries < 10) {
            userInputPanel.setPreferredSize(new Dimension(360, 70));
        } else {
            userInputPanel.setPreferredSize(new Dimension(360, 50));
        }
        for (int i = 0; i < userInputs.length; i++) {
            if (maxTries == 10) {
                userInputs[i] = new RoundedTextField(10);
            } else {
                userInputs[i] = new RoundedTextField(1);
            }
            userInputs[i].setHorizontalAlignment(JTextField.CENTER);
            userInputs[i].setFont(new Font("Poppins", Font.PLAIN, 16));
            if (maxTries == 10) {
                userInputs[i].setFont(new Font("Poppins", Font.PLAIN, 18));
            }
            userInputs[i].setBorder(grayBorder);
            userInputPanel.add(userInputs[i]);//añades al panel
        }
        userInputPanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 20, 20));
        if (maxTries == 10) {
            userInputPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        }

        //Previous tries Panel.
        previousTries = new JTextArea[this.maxTries][this.length];
        previousTriesPanel = new JPanel(new GridLayout(this.maxTries, this.length, 10, 10));//10,10 add spaces between cells
        previousTriesPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 20, 40));
        if (maxTries == 10) {
            previousTriesPanel = new JPanel(new GridLayout(this.maxTries, this.length, 10, 5));
            previousTriesPanel.setBorder(BorderFactory.createEmptyBorder(15, 40, 0, 40));
        }
        previousTriesPanel.setBackground(Colors.BACKGROUND);
        previousTriesPanel.setPreferredSize(new Dimension(200, 250));
        if (maxTries == 10) {
            previousTriesPanel.setPreferredSize(new Dimension(200, 360));
        }
        for (int i = 0; i < maxTries; i++) {
            for (int j = 0; j < length; j++) {
                previousTries[i][j] = new JTextArea(1, 3);
                previousTries[i][j].setEditable(false);
                previousTries[i][j].setBorder(BorderFactory.createLineBorder(new Color(221, 221, 221), 2));
                previousTries[i][j].setBackground(Color.WHITE);
                previousTriesPanel.add(previousTries[i][j]);
            }
        }

        // Submit button & tries left display
        submitButton = PersonalizedButton.slimButton("Submit", "submit");
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


        // Back button panel.
        backButton = PersonalizedButton.slimBackButton;
        backPanel = new JPanel();
        backPanel.setBackground(Colors.BACKGROUND);
        backPanel.add(backButton);
        if (maxTries == 10) {
            backPanel.setPreferredSize(new Dimension(100, 25));
        } else {
            backPanel.setPreferredSize(new Dimension(360, 40));
        }

        //Add panels.   
        add(titlePanel, 0); 
        add(triesLeftPanel);
        add(userInputPanel);
        add(previousTriesPanel);
        add(bottomPanel);
        add(backPanel);

         //sice, central panel, not change sice, visible
        setSize(360, 640);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        userInputs[0].requestFocusInWindow();
    }

    public void setController(ControllerGame controller) {
        this.length = controller.getLength();
        this.maxTries = controller.getMaxTries();
        
        for (ActionListener al : submitButton.getActionListeners()) {
            submitButton.removeActionListener(al);
        }
        submitButton.addActionListener(controller);
        
        for (ActionListener al : backButton.getActionListeners()) {
            backButton.removeActionListener(al);
        }
        backButton.addActionListener(controller);
        
        // Add the key listener the controller implements to each JTextField
        for (JTextField userInput : userInputs) {
            userInput.addKeyListener(controller);
        }
    }
    
    public void deleteOneUserInput() {
        for (int i = 0; i < length; i++) {
            if (userInputs[i].isFocusOwner()) {
                if (!userInputs[i].getText().isEmpty()) userInputs[i].setText("");
                else if (i != 0) {
                    userInputs[i-1].setText("");
                    userInputs[i-1].requestFocusInWindow();
                }
            }
        }
    }
    
    public void updateValue(char value) {
        for (int i = 0; i < length; i++) {
            if (userInputs[i].isFocusOwner() && Character.isDigit(value)) {
                userInputs[i].setText(String.valueOf(value)); // Set input
                if (i < length - 1) userInputs[i + 1].requestFocusInWindow(); // Move to next field
            }
        }
    }

    // The ControllerGame tells the ViewGame what to display and where to display it.
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

    // The ControllerGame updates the text of the remaining attempts.
    public void setTriesLeftText(int triesLeft) {
        triesLeftField.setText("Tries left: " + triesLeft);
    }

    // Obtain the digits entered by the user.
    public String getUserDigits() {
        StringBuilder digits = new StringBuilder();
        for (JTextField textField : userInputs) {
            digits.append(textField.getText());
        }
        return digits.toString();
    }

    //Delete digits
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

    //Name users.
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

    public void clearPreviousTries() {
        for (int i = 0; i < previousTries.length; i++) {
            for (int j = 0; j < previousTries[i].length; j++) {
                previousTries[i][j].setText(""); // Clear text
                previousTries[i][j].setBackground(Color.WHITE); // Reset color
            }
        }
    }

    //New Game again.
    public void enableInputs() {
        for (JTextField field : userInputs) {
            field.setText("");
            field.setEnabled(true); 
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
