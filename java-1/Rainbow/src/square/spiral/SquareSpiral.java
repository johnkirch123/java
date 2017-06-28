/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package square.spiral;

/**
 *
 * @author John
 */
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class SquareSpiral extends JPanel{
    
    
    public SquareSpiral () {
        
        setBackground(Color.LIGHT_GRAY);
        
    }
    @ Override
    public void paintComponent (Graphics g) {
        
        super.paintComponent(g);
        
        int radius = 1;
        
        int centerX = getWidth() / 2;
        int centerY = getHeight() - 10;
        
        for (int counter = 1; counter < 20; counter++) {
            
            g.setColor(Color.BLUE);
            
            g.drawArc(centerX - counter * radius, centerY - counter * radius, counter * radius / 2, counter * radius * 2, 0, 180);
            
            g.setColor(Color.YELLOW);
            
            g.drawArc(centerX - counter * radius , centerY - counter * radius , counter * radius * 2, counter * radius * 2, 180, 0);
            radius++;
            
        }
        
    }
    
}
