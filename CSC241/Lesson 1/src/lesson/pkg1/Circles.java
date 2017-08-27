package lesson.pkg1;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author John
 */
public class Circles extends JPanel{
    
    Random random;
    
    public Circles() {
        
        random = new Random();
        setBackground(Color.WHITE);
        
    }
    
    private int randomNumber(int number) {
        
        return random.nextInt(number);
        
    }
    
    public void drawCircle(Graphics g, int x, int y, int radius) {
        
        g.drawOval(x, y, radius, radius);
        
    }
    
    @Override
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        
        for (int counter = 0; counter > 20; counter++) {
            
            drawCircle(g, randomNumber(400) + 100, randomNumber(350) + 100, randomNumber(50));
            
        }
        
    } 
    
}
