package lesson.pkg1;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;
/**
 *
 * @author John
 */
public class CirclesPanel extends JPanel{
    
    private float r;
    private float g;
    private float b;
    private Random random;
    private Color color;
    
    public CirclesPanel() {
        
        random = new Random();
        setBackground(Color.WHITE);
        
        
    }
    
    private Color randomColor(boolean intersect) {
        
        r = random.nextFloat();
        g = random.nextFloat();
        b = random.nextFloat();
        
        color = new Color(r, g, b);
        
        if (intersect) {
            return color;
        } else {
            return new Color(1, 0, 0);
        }
    }
    
    private int randomNumber(int number) {
        
        return random.nextInt(number);
        
    }
    
    public void drawCircle(Graphics g, int x, int y, int radius) {
        g.setColor(randomColor(true));
        g.drawOval(x, y, radius, radius);
        
    }
    
    @Override
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        
        for (int counter = 0; counter < 20; counter++) {
            
            drawCircle(g, randomNumber(400) + 50, randomNumber(350) + 50, randomNumber(150));
            
        }
    }
}
