package key.demo;

import javax.swing.JFrame;
/**
 *
 * @author John
 */
public class KeyDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        KeyDemoFrame keyDemoFrame = new KeyDemoFrame();
        keyDemoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        keyDemoFrame.setSize(350, 100);
        keyDemoFrame.setVisible(true);
    }
    
}
