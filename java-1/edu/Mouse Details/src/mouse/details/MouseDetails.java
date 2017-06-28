package mouse.details;

import javax.swing.JFrame;

/**
 *
 * @author John
 */
public class MouseDetails {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        MouseDetailsFrame mouseDetailsFrame = new MouseDetailsFrame();
        mouseDetailsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mouseDetailsFrame.setSize(400, 150);
        mouseDetailsFrame.setVisible(true);
    }
    
}
