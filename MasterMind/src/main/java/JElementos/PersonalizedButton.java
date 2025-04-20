/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JElementos;

/**
 *
 * @author silvia
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class PersonalizedButton extends JButton {

    public PersonalizedButton(String text, String actionCommand) {
        super(text);
        setActionCommand(actionCommand);
        setFont(new Font("Arial", Font.BOLD, 16));
        setForeground(Color.WHITE);
        setBackground(new Color(46, 134, 193));
        setBorderPainted(false);
        setFocusPainted(false);
        setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));//padding
        setUI(new javax.swing.plaf.basic.BasicButtonUI() {
         @Override
        public void paint(Graphics g, JComponent c) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(c.getBackground());
            RoundRectangle2D roundedRect = new RoundRectangle2D.Double(0, 0, c.getWidth(), c.getHeight(), 30, 30);
            g2.fill(roundedRect);
            g2.dispose();
            super.paint(g, c);
        }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                setBackground(new Color(52, 152, 219));
                setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                setBackground(new Color(46, 134, 193));
                setForeground(Color.WHITE);
            }
        });
    }
}
