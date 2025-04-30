package JElementos;

/**
 *
 * @author Silvia García Bouza
 * @author Nuria Calo Mosquera
 * @author Alfonso Gallego Fernández
 */
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

public class PersonalizedButton extends JButton {

    private int borderRadius;
    
    public static final JButton slimBackButton = slimButton("← Back", "back");
    public static final JButton midBackButton = midButton("← Back", "back");
    public static final JButton bigBackButton = bigButton("← Back", "back");

    public PersonalizedButton(String text, int radius) {
        super(text);
        this.borderRadius = radius;
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        setBackground(Colors.BUTTON);
        setForeground(Colors.BACKGROUND);
    }
    
    public static JButton bigButton(String label, String actionCommand) {
        JButton result = new PersonalizedButton(label, 15);
        result.setActionCommand(actionCommand);
        result.setPreferredSize(new Dimension(200, 50));
        return result;
    }
    
    public static JButton bigButton(String label) {
        return bigButton(label, label.toLowerCase());
    }
    
    public static JButton midButton(String label, String actionCommand) {
        JButton result = new PersonalizedButton(label, 15);
        result.setActionCommand(actionCommand);
        result.setPreferredSize(new Dimension(100, 50));
        return result;
    }
    
    public static JButton midButton(String label) {
        return midButton(label, label.toLowerCase());
    }
    
    public static JButton slimButton(String label, String actionCommand) {
        JButton result = new PersonalizedButton(label, 15);
        result.setActionCommand(actionCommand);
        result.setPreferredSize(new Dimension(100, 20));
        return result;
    }
    
    public static JButton slimButton(String label) {
        return slimButton(label, label.toLowerCase());
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
        // Do not paint the standard border.
    }

}
