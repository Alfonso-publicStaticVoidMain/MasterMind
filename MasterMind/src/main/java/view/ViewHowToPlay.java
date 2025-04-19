package view;

import controller.ControllerHowToPlay;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author nuriacalo
 */
public class ViewHowToPlay extends javax.swing.JFrame {

    /**
     * Paneles
     */
    private JPanel titlePanel;
    private JPanel buttonPanel;

    /**
     * Elementos
     */
    private JLabel titleLabel;
    private JButton backButtom;

    public ViewHowToPlay() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setTitle("Mastermind");

        //Panel titulo
        titlePanel = new JPanel();
        titlePanel.setBackground(Color.pink);
        titleLabel = new JLabel("MasterMind");
        titlePanel.add(titleLabel);
        add(titlePanel);
        //Panel elementos
        backButtom = new JButton("Back");
        backButtom.setActionCommand("back");
        buttonPanel = new JPanel();
        add(buttonPanel);
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(backButtom);

        //tamaño adaptado, contenido centrado, visible
        setSize(360, 640);
        setResizable(false);//q no ca,bie el tamaño eluser
        // pack();
        setLocationRelativeTo(null);
        setVisible(true);
        

        // Panel de intruccións
        JPanel descriptionPanel = new JPanel();
        descriptionPanel.setBackground(Color.PINK);

        // Reglas do xogo
        JLabel instructions = new JLabel("<html>"
                + "<b>How to Play:</b><br>"
                + "- Guess the secret number.<br>"
                + "- Correct digits in the right place turn green.<br>"
                + "- Correct digits in the wrong place turn orange.<br>"
                + "- You have limited tries to solve it!"
                + "</html>");
        instructions.setForeground(Color.BLACK); 
        descriptionPanel.add(instructions);

        add(descriptionPanel); 

    }

    public void setActionListener(ControllerHowToPlay controller) {
        backButtom.addActionListener(controller);
    }

    public void setActionListener(ActionListener listener) {
    backButtom.addActionListener(listener); 
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
