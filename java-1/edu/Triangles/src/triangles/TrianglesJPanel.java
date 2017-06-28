package triangles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * JPanel for the Simple random triangle generating program. 
 *
 * @author    John Kirch, S01581562
 * @version   04-07-2017, CSC-240 Assignment 9, Exercise 13.9
 */
public class TrianglesJPanel extends JPanel
{
    private final JFrame frame;                             // reference to JFrame.
    private final static int TRIANGLE_POINTS = 3;           // # of points on a triangle.
    private final static int MINIMUM_TRIANGLE_COUNT = 10;   // minimum # of triangles.
    /**
     * 
     * @param frame reference to the JFrame for height and width.
     */
    public TrianglesJPanel(JFrame frame)
    {
        this.frame = frame;
    }
    /**
     * Creates and draws the triangles of random sizes and colors.
     * 
     * @param g graphics object to be manipulated.
     */
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Random random = new Random();
        Graphics2D g2d = (Graphics2D) g;
        // traingle count, and x and y coordinates for the 3 points of the triangles.
        int triangleCount = random.nextInt(25) + MINIMUM_TRIANGLE_COUNT;
        int xCoords[] = new int[TRIANGLE_POINTS];
        int yCoords[] = new int[TRIANGLE_POINTS];
        // loops to build each triangle.
        for(int i = 0; i < triangleCount; i++)
        { 
            GeneralPath triangle = new GeneralPath();
            // builds the triangles coordinates and moves it to the initial position.
            for(int j = 0; j < xCoords.length; j++)
            {
                xCoords[j] = random.nextInt(frame.getWidth());
                yCoords[j] = random.nextInt(frame.getHeight());
            }
            triangle.moveTo(xCoords[0], yCoords[0]);
            // creates the triangle shape and closes the path.
            for (int k = 1; k < xCoords.length; k++)
            {
                triangle.lineTo(xCoords[k], yCoords[k]);
            }
            triangle.closePath();
            // sets the random color and fills the triangle.
            g2d.setColor(new Color(random.nextInt(256), random.nextInt(256), 
                    random.nextInt(256)));
            g2d.fill(triangle);
            
        }   // end triangle build loop.
    }   // end paintComponent.
}   // end TrianglesJPanel class.
