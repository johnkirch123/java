package triangles;

import javax.swing.JFrame;

/**
 * Simple random triangle generating program. Upon resizing screen, new triangles
 * are generated.
 *
 * @author    John Kirch, S01581562
 * @version   04-07-2017, CSC-240 Assignment 9, Exercise 13.9
 */
public class Triangles
{
    /**
     * Main entry point.
     * <p>
     * Execute:</p>
     * <pre>java some.package.name.HelloWorld</pre>
     *
     * @param args   not used.
     */
    public static void main(String[] args) 
    {
        // Creates a new JFrame and adds the instance of JPanel to it, sets size.
        JFrame frame = new JFrame("Drawing Triangles");
        TrianglesJPanel trianglesJPanel = new TrianglesJPanel(frame);
        frame.add(trianglesJPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setVisible(true);
    }
    
}
