package lesson.pkg1;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
/**
 *
 * @author John Kirch 
 */
public class IntersectingCircles {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                
                CirclesView view = new CirclesView("Intersecting Circles");
                CirclesPanel circlesPanel = new CirclesPanel();
                CirclesController controller = new CirclesController(view, circlesPanel);
                
                view.setLocationRelativeTo(null);
                view.setSize(600, 600);
                view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                view.setVisible(true);
            }
        });
    }
    
}
