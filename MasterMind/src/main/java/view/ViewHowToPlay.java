package view;

import controller.ControllerHowToPlay;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

public class ViewHowToPlay extends javax.swing.JFrame {

    // Componentes de la interfaz
    private JPanel mainContentPanel;
    private JPanel titlePanel;
    private JPanel buttonPanel;
    private JPanel descriptionPanel;
    private JLabel titleLabel;
    private JButton backButton;
    private JTextArea instructions;
    private JButton darkModeButton;

    // Esquemas de color
    private final Color LIGHT_BG = new Color(255, 230, 230); // Fondo rosa claro
    private final Color LIGHT_TEXT = new Color(60, 60, 60);  // Texto gris oscuro
    private final Color LIGHT_PANEL = new Color(245, 245, 245); // Panel blanco

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
        titleLabel = new JLabel("MasterMind", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        mainContentPanel.add(titlePanel);

        // Espaciado entre componentes
        mainContentPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Panel de botones (FlowLayout para alinear a la izquierda y derecha)
        buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBackground(LIGHT_BG);

        // Configurar botón Back 
        backButton = new JButton("← Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setPreferredSize(new Dimension(100, 20));
        backButton.setContentAreaFilled(false);
        backButton.setOpaque(true);
        backButton.setFocusPainted(false);

        // Configurar botón Dark Mode (a la derecha)
        darkModeButton = new JButton(darkMode ? "☀ Light" : "☾ Dark");
        darkModeButton.setFont(new Font("Arial", Font.PLAIN, 12));
        darkModeButton.setContentAreaFilled(false);
        darkModeButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        darkModeButton.setFocusPainted(false);
        darkModeButton.addActionListener(e -> toggleDarkMode());

        // Añadir botones al panel
        buttonPanel.add(backButton, BorderLayout.WEST);
        buttonPanel.add(darkModeButton, BorderLayout.EAST);

        mainContentPanel.add(buttonPanel);

        // Más espaciado
        mainContentPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Texto de instrucciones
        descriptionPanel = new JPanel(new BorderLayout());
        descriptionPanel.setBackground(LIGHT_PANEL);
        descriptionPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        instructions = new JTextArea(
                "How to Play:\n\n"
                + "- Correct digits in right position: green\n"
                + "- Correct digits in wrong position: orange\n"
                + "- Incorrect digits: red\n"
                + "- Limited attempts to solve it!");
        instructions.setFont(new Font("Arial", Font.ITALIC, 18));
        instructions.setEditable(false);
        instructions.setLineWrap(true);
        instructions.setWrapStyleWord(true);
        instructions.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        instructions.setBackground(LIGHT_BG);

        descriptionPanel.add(instructions, BorderLayout.CENTER);
        mainContentPanel.add(descriptionPanel);

        // Aplicar esquema de colores inicial
        applyColorScheme();

        // Configuración final de la ventana
        setSize(450, 650);
        setMinimumSize(new Dimension(400, 500));
        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // MÉTODOS PARA EL MODO OSCURO
    private void toggleDarkMode() {
        darkMode = !darkMode;
        applyColorScheme();
        darkModeButton.setText(darkMode ? "☀ Light" : "☾ Dark");
    }

    private void applyColorScheme() {
        // Seleccionar colores según el modo actual
        Color bgColor = darkMode ? DARK_BG : LIGHT_BG;
        Color textColor = darkMode ? DARK_TEXT : LIGHT_TEXT;
        Color panelColor = darkMode ? DARK_PANEL : LIGHT_PANEL;

        backButton.setBackground(darkMode ? new Color(60, 60, 70) : new Color(220, 230, 250));
        backButton.setForeground(darkMode ? Color.WHITE : new Color(40, 40, 40));
        backButton.setBorder(BorderFactory.createLineBorder(darkMode ? new Color(120, 120, 130) : new Color(180, 200, 230), 2));
        backButton.repaint();

        // Aplicar los cambios
        getContentPane().setBackground(bgColor);
        titlePanel.setBackground(bgColor);
        buttonPanel.setBackground(bgColor);
        titleLabel.setForeground(textColor);

        backButton.setForeground(textColor);
        darkModeButton.setForeground(textColor);

        descriptionPanel.setBackground(panelColor);
        instructions.setForeground(textColor);
        instructions.setBackground(panelColor);

        getContentPane().setBackground(bgColor);
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
