package view;

import controller.ControllerHowToPlay;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import JElementos.Colors;
import JElementos.PersonalizedButton;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Silvia García Bouza
 * @author Nuria Calo Mosquera
 * @author Alfonso Gallego Fernández
 */
public class ViewHowToPlay extends javax.swing.JFrame {

    // Panels.
    private JPanel mainContentPanel;
    private JPanel titlePanel;
    private JPanel descriptionPanel;
    private JPanel backPanel;
    //Elements.
    private JLabel titleLabel;
    private JButton backButton;
    private JButton darkModeButton;
    private ImageIcon titleImage;
    private final Color LIGHT_PANEL = Color.WHITE;
    private final Color LIGHT_BG = Colors.BACKGROUND;

    public ViewHowToPlay() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Mastermind");
        Image customIcon = Toolkit.getDefaultToolkit().getImage("src" + File.separator + "main" + File.separator + "resources" + File.separator + "LogoSinTitulo.png");
        setIconImage(customIcon);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(Colors.BACKGROUND);
        
        // Main Panel.
        mainContentPanel = new JPanel();
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS));
        mainContentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainContentPanel.setBackground(Colors.BACKGROUND);
        add(mainContentPanel, BorderLayout.CENTER);
        
        //Tittle panel.
        titlePanel = new JPanel();
        titlePanel.setBackground(Colors.BACKGROUND);
        titlePanel.setBorder(new EmptyBorder(5, 0, 0, 0));
        titlePanel.setPreferredSize(new Dimension(130, 130));
        titleImage = new ImageIcon(getClass().getResource("/TituloImagen.png"));
        if (titleImage != null) {
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
        mainContentPanel.add(titlePanel);
        
        //Description panel.
        backButton = PersonalizedButton.midBackButton;
        descriptionPanel = new JPanel(new BorderLayout());
        descriptionPanel.setBackground(LIGHT_PANEL);
        descriptionPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Colors.GREY, 1),
                BorderFactory.createEmptyBorder(15, 20, 20, 20)
        ));

        // Create JEditorPane and configurre for use HTML
        JEditorPane instructions = new JEditorPane();
        instructions.setContentType("text/html");
        instructions.setEditable(false);
        instructions.setBackground(LIGHT_PANEL);
        instructions.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        // Text html
        String htmlContent = "<html>"
                + "<body style='font-family: Arial; font-size: 14px; color: #333333;'>"
                + "<h1>How to Play:</h1>"
                + "<p>- Correct digits in right position: <span style='background-color: #90EE90; font-weight: bold;'>green</span></p>"
                + "<p>- Correct digits in wrong position: <span style='background-color: #FFCC66; font-weight: bold;'>orange</span></p>"
                + "<p>- Incorrect digits: <span style='background-color: #FF7878; font-weight: bold;'>red</span></p>"
                + "<h2 style='margin-bottom: 0px;'>Example:</h2>"
                + "<p><b>Secret:</b> 4 7 2 8</p>"
                + "<p><b>Guess:</b> 4 2 9 3</p>"
                + "<h2 style='margin-bottom: 0px;'>Feedback:</h2>"
                + "<p><span style='background-color: #90EE90; font-weight: bold;'>✔</span> (green) for 4</p>"
                + "<p><span style='background-color: #FFCC66; font-weight: bold;'>◒</span> (orange) for 2</p>"
                + "<p><span style='background-color: #FF7878; font-weight: bold;'>✖</span> (red) for 9, 3</p>"
                + "<p>You have limited attempts to solve it. Good luck!</p>"
                + "</body></html>";

        instructions.setText(htmlContent);
        instructions.setCaretPosition(0);

        // Create JScrollPane that wrapping JEditorPane
        JScrollPane scrollPane = new JScrollPane(instructions);
        scrollPane.setPreferredSize(new Dimension(220, 350));
        scrollPane.setBorder(null);
        scrollPane.setBackground(LIGHT_PANEL);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        // Add scrollPane to the descriptionPanel 
        descriptionPanel = new JPanel(new BorderLayout());
        descriptionPanel.setBackground(LIGHT_PANEL);
        descriptionPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Colors.GREY, 1),
                BorderFactory.createEmptyBorder(15, 20, 20, 20)
        ));

        descriptionPanel.add(scrollPane, BorderLayout.CENTER);

        //Content wrapper panel.
        JPanel contentWrapper = new JPanel(new BorderLayout());
        contentWrapper.setBackground(LIGHT_BG);
        contentWrapper.add(descriptionPanel, BorderLayout.CENTER);
        mainContentPanel.add(contentWrapper);
        backPanel = new JPanel();
        backPanel.setBackground(Colors.BACKGROUND);
        backPanel.setPreferredSize(new Dimension(150, 90));
        backPanel.add(backButton);
        mainContentPanel.add(backPanel);

        setSize(360, 640);
        setMinimumSize(new Dimension(360, 640));
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void setActionListener(ControllerHowToPlay controller) {
        for (ActionListener al : backButton.getActionListeners()) {
            backButton.removeActionListener(al);
        }
        backButton.addActionListener(controller);
    }

    /**
     * Panel del título (centrado) titlePanel = new JPanel(new BorderLayout());
     * titlePanel.setBackground(LIGHT_BG); titlePanel.setPreferredSize(new
     * Dimension(200,200)); titleLabel = new JLabel("MasterMind",
     * SwingConstants.CENTER); titleLabel.setFont(new Font("Arial", Font.BOLD,
     * 36)); titleLabel.setForeground(TITLE_COLOR); titlePanel.add(titleLabel,
     * BorderLayout.CENTER); mainContentPanel.add(titlePanel); * Panel de
     * botones (now smaller) buttonPanel = new JPanel(new BorderLayout());
     * buttonPanel.setBackground(LIGHT_BG); buttonPanel.setMaximumSize(new
     * Dimension(Integer.MAX_VALUE, 40)); * Configurar botón Dark Mode (smaller
     * size) darkModeButton = new JButton(darkMode ? "☀ Light" : "☾ Dark");
     * darkModeButton.setFont(new Font("Arial", Font.PLAIN, 12));
     * darkModeButton.setBackground(BUTTON_COLOR);
     * darkModeButton.setForeground(Color.WHITE);
     * darkModeButton.setOpaque(true);
     * darkModeButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
     * darkModeButton.setFocusPainted(false); darkModeButton.addActionListener(e
     * -> toggleDarkMode()); * Añadir botones al panel
     * buttonPanel.add(backButton); backButton.setPreferredSize(new
     * Dimension(100, 50)); buttonPanel.add(darkModeButton, BorderLayout.EAST);
     * * MÉTODOS PARA EL MODO OSCURO private void toggleDarkMode() { darkMode =
     * !darkMode; applyColorScheme(); darkModeButton.setText(darkMode ? "☀
     * Light" : "☾ Dark");} * Función para aplicar o Esquema de Colores private
     * void applyColorScheme() { // Seleccionar colores según el modo actual
     * Color bgColor = darkMode ? DARK_BG : LIGHT_BG; Color textColor = darkMode
     * ? DARK_TEXT : LIGHT_TEXT; Color panelColor = darkMode ? DARK_PANEL :
     * LIGHT_PANEL; // Aplicar los cambios
     * getContentPane().setBackground(bgColor);
     * titlePanel.setBackground(bgColor); titleLabel.setForeground(darkMode ?
     * DARK_TEXT : TITLE_COLOR); descriptionPanel.setBackground(panelColor);
     * descriptionPanel.setBorder(BorderFactory.createCompoundBorder(
     * BorderFactory.createLineBorder(darkMode ? new Color(100, 100, 110) :
     * Colors.GREY, 1), BorderFactory.createEmptyBorder(15, 20, 20, 20) ));
     *
     * getContentPane().revalidate(); getContentPane().repaint(); } * Aplicar
     * esquema de colores inicial applyColorScheme(); * Color scheme from Colors
     * class private final Color LIGHT_TEXT = Colors.TEXT; private final Color
     * BUTTON_COLOR = Colors.BUTTON; private final Color TITLE_COLOR =
     * Colors.TITLE; private final Color DARK_BG = new Color(40, 40, 50);
     * private final Color DARK_TEXT = new Color(230, 230, 230); private final
     * Color DARK_PANEL = new Color(60, 60, 70); private boolean darkMode =
     * false;
     */

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
