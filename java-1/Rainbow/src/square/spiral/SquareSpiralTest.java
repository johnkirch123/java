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
import javax.swing.JFrame;

public class SquareSpiralTest {
    
    public static void main (String[] args) {
        
        SquareSpiral panel = new SquareSpiral();
        JFrame application = new JFrame();
        
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.add(panel);
        application.setSize(600, 400);
        application.setVisible(true);
        
    }
    
}
