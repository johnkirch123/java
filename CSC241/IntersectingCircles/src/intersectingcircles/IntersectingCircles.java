package intersectingcircles;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author John Kirch S01581562
 * Lesson 1 Intersecting Circles v1.0
 */
public class IntersectingCircles {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                
                JFrame frame = new MainFrame("Intersecting Circles");
                
                frame.setSize(600, 600);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(false);
                frame.setVisible(true);
            }
        });
    }
    
}
