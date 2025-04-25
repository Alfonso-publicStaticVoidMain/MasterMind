package view;

import controller.ControllerHowToPlay;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import JElementos.Colors;
import JElementos.PersonalizedButton;

public class ViewHowToPlay extends javax.swing.JFrame {

    // Componentes de la interfaz
    private JPanel mainContentPanel;
    private JPanel titlePanel;
   // private JPanel buttonPanel;
    private JPanel descriptionPanel;
    private JPanel backPanel;
    private JLabel titleLabel;
    private JButton backButton;
    private JTextArea instructions;
    private JButton darkModeButton;

    // Color scheme from Colors class
    private final Color LIGHT_BG = Colors.BACKGROUND;
    private final Color LIGHT_TEXT = Colors.TEXT;
    private final Color LIGHT_PANEL = Color.WHITE;
    private final Color BUTTON_COLOR = Colors.BUTTON;
    private final Color TITLE_COLOR = Colors.TITLE;

    private final Color DARK_BG = new Color(40, 40, 50);     // Fondo azul oscuro
    private final Color DARK_TEXT = new Color(230, 230, 230); // Texto blanco
    private final Color DARK_PANEL = new Color(60, 60, 70);  // Panel gris oscuro

    private boolean darkMode = false;

    public ViewHowToPlay() {
        // Configuración básica de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Mastermind");
        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(LIGHT_BG);

        // Panel principal con márgenes
        mainContentPanel = new JPanel();
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS));
        mainContentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainContentPanel.setBackground(LIGHT_BG);
        add(mainContentPanel, BorderLayout.CENTER);

        // Panel del título (centrado)
        titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(LIGHT_BG);
        titlePanel.setPreferredSize(new Dimension(200,200));
        titleLabel = new JLabel("MasterMind", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(TITLE_COLOR);
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        mainContentPanel.add(titlePanel);

        // Small spacing after title
        mainContentPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Panel de botones (now smaller)
//        buttonPanel = new JPanel(new BorderLayout());
//        buttonPanel.setBackground(LIGHT_BG);
//        buttonPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40)); // Limit height

        // Configurar botón Back 
        backButton = new PersonalizedButton("← Back",15);
        backButton.setActionCommand("back");
        backButton.setPreferredSize(new Dimension(100, 50));

        // Configurar botón Dark Mode (smaller size)
//        darkModeButton = new JButton(darkMode ? "☀ Light" : "☾ Dark");
//        darkModeButton.setFont(new Font("Arial", Font.PLAIN, 12));
//        darkModeButton.setBackground(BUTTON_COLOR);
//        darkModeButton.setForeground(Color.WHITE);
//        darkModeButton.setOpaque(true);
//        darkModeButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
//        darkModeButton.setFocusPainted(false);
//        darkModeButton.addActionListener(e -> toggleDarkMode());

        // Añadir botones al panel
        //buttonPanel.add(backButton);
       // backButton.setPreferredSize(new Dimension(100, 50));
       // buttonPanel.add(darkModeButton, BorderLayout.EAST);

        // Small spacing before instructions
        mainContentPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Texto de instrucciones (now with more space)
        descriptionPanel = new JPanel(new BorderLayout());
        descriptionPanel.setBackground(LIGHT_PANEL);
        descriptionPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Colors.GREY, 1),
                BorderFactory.createEmptyBorder(15, 20, 20, 20)
        ));

        instructions = new JTextArea(
                "How to Play:\n\n"
                + "- Correct digits in right position: green\n"
                + "- Correct digits in wrong position: orange\n"
                + "- Incorrect digits: red\n"
                + "- Limited attempts to solve it!");
        instructions.setFont(new Font("Arial", Font.PLAIN, 18));
        instructions.setEditable(false);
        instructions.setLineWrap(true);
        instructions.setWrapStyleWord(true);
        instructions.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        instructions.setBackground(LIGHT_PANEL);
        instructions.setForeground(LIGHT_TEXT);

        descriptionPanel.add(instructions, BorderLayout.CENTER);

        // Make the description panel take most of the space
        JPanel contentWrapper = new JPanel(new BorderLayout());
        contentWrapper.setBackground(LIGHT_BG);
        contentWrapper.add(descriptionPanel, BorderLayout.CENTER);
        mainContentPanel.add(contentWrapper);
        backPanel= new JPanel();
        backPanel.setBackground(Colors.BACKGROUND);
        backPanel.setPreferredSize(new Dimension(160,180));
        backPanel.add(backButton);  
        mainContentPanel.add(backPanel);

        // Aplicar esquema de colores inicial
        applyColorScheme();

        // Configuración final de la ventana
        setSize(360, 640); // Same size as ViewIndex
        setMinimumSize(new Dimension(360, 640)); // Fixed size like ViewIndex
        setResizable(false); // Not resizable like ViewIndex
        setLocationRelativeTo(null);
        setVisible(true);
    }

//    // MÉTODOS PARA EL MODO OSCURO
//    private void toggleDarkMode() {
//        darkMode = !darkMode;
//        applyColorScheme();
//        darkModeButton.setText(darkMode ? "☀ Light" : "☾ Dark");
//    }
    private void applyColorScheme() {
        // Seleccionar colores según el modo actual
        Color bgColor = darkMode ? DARK_BG : LIGHT_BG;
        Color textColor = darkMode ? DARK_TEXT : LIGHT_TEXT;
        Color panelColor = darkMode ? DARK_PANEL : LIGHT_PANEL;
        // Aplicar los cambios
        getContentPane().setBackground(bgColor);
        titlePanel.setBackground(bgColor);
        titleLabel.setForeground(darkMode ? DARK_TEXT : TITLE_COLOR);
        descriptionPanel.setBackground(panelColor);
        descriptionPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(darkMode ? new Color(100, 100, 110) : Colors.GREY, 1),
                BorderFactory.createEmptyBorder(15, 20, 20, 20)
        ));

        instructions.setForeground(textColor);
        instructions.setBackground(panelColor);

        getContentPane().revalidate();
        getContentPane().repaint();
    }

    public void setActionListener(ControllerHowToPlay controller) {
        backButton.addActionListener(controller);
    }

    public void setActionListener(ActionListener listener) {
        backButton.addActionListener(listener);
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
