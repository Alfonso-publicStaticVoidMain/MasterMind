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
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

public class PersonalizedButton extends JButton {

    private int borderRadius;

    public PersonalizedButton(String text, int radius) {
        super(text);
        this.borderRadius = radius;
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        setPreferredSize(new Dimension(200, 50));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Colors.BUTTON); 
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, borderRadius, borderRadius));

        g2.setColor(Color.WHITE); 
        FontMetrics fm = g2.getFontMetrics();
        Rectangle2D r = fm.getStringBounds(getText(), g2);
        int x = (getWidth() - (int) r.getWidth()) / 2;
        int y = (getHeight() - (int) r.getHeight()) / 2 + fm.getAscent();
        g2.drawString(getText(), x, y);

        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        // No pintar el borde estándar
    }

}
