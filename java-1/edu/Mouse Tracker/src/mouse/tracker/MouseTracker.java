package mouse.tracker;

import javax.swing.JFrame;
/**
 *
 * @author John
 */
public class MouseTracker {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        MouseTrackerFrame mouseTrackerFrame = new MouseTrackerFrame();
        mouseTrackerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mouseTrackerFrame.setSize(300, 100);
        mouseTrackerFrame.setVisible(true);
    }
    
}
