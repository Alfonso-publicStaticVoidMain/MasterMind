package JElementos;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JTextField;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;

/**
 *
 * @author Silvia García Bouza
 * @author Nuria Calo Mosquera
 * @author Alfonso Gallego Fernández
 */
public class RoundedTextField extends JTextField {

    private int borderRadius;

    public RoundedTextField(int columns, int radius) {
        super(columns);
        this.borderRadius = radius;
        setBorder((Border) new RoundedBorder(radius));
    }

    public RoundedTextField(int radius) {
        this(3, radius); // Default to 3 columns as in your example
    }

    private static class RoundedBorder extends AbstractBorder {
        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(c.getForeground()); // You can customize the border color here
            g2d.draw(new RoundRectangle2D.Double(x, y, width - 1, height - 1, radius, radius));
            g2d.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.left = insets.top = insets.right = insets.bottom = this.radius / 2; // Adjust insets as needed
            return insets;
        }

        @Override
        public boolean isBorderOpaque() {
            return false;
        }
    }

    

    
}
