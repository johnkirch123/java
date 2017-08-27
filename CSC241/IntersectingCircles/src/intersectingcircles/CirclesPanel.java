package intersectingcircles;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
/**
 *
 * @author John Kirch S01581562
 * Lesson 1 Intersecting Circles v1.0
 */
public class CirclesPanel extends JPanel{
    
    private static final int NUMBER_OF_CIRCLES = 5;                    // Number of circles to draw on canvas.
    private Circle[] circlesArray = new Circle[NUMBER_OF_CIRCLES];      // Array that hold each circle object.
    public Circle circle;                                               // Instatiate a new Circle Object.
    // Constructor that sets the background panel to white.
    public CirclesPanel() {
        
        setBackground(Color.WHITE);
        
    }
    /**
     * Changes the colors of the circles in circlesArray depending on if they intersect another circle.
     */
    private void doCirclesIntersect() {
        
        double x1;                                          // x coordinate of circle 1, center circle.           
        double y1;                                          // y coordinate of circle 1, center circle.
        double x2;                                          // x coordinate of circle 2, center circle.
        double y2;                                          // y coordinate of circle 2, center circle.
        double distance;                                    // distance between circles, center to center.
        double radius1;                                     // radius of circle 1.
        double radius2;                                     // radius of circle 2.
        
        // Iterates through the circlesArray, starting with the first circle.
        for (int i = 0; i < circlesArray.length - 1; i++) {
            // Sets the radius, x and y coordinates for the first circle.
            radius1 = circlesArray[i].getRadius();
            x1 = circlesArray[i].getXCoordinate() + circlesArray[i].getRadius();
            y1 = circlesArray[i].getYCoordinate() + circlesArray[i].getRadius();
            
            System.out.println("Circle " + (i + 1));
            System.out.println("Radius: " + radius1 + " xCoord: " + x1 + " yCoord: " + y1);
            // Iterates through every other circle in the array to compare to circle 1 for intersection.
            for (int j = i + 1; j < circlesArray.length; j++) {
                // Sets the radius, x and y coordinates, and distance for circle 2.
                x2 = circlesArray[j].getXCoordinate() + circlesArray[j].getRadius();
                y2 = circlesArray[j].getYCoordinate() + circlesArray[j].getRadius();
                distance = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1)*(y2 - y1));
                radius2 = circlesArray[j].getRadius();
                System.out.printf(" distance: %.2f", distance);
                // Checks to see if the circle is inside, outside, or intersecting with circle 1.
                System.out.println("  Does not Intersect with circle " + (j + 1) + ": distance > (radius1 + radius2): " + (distance > radius1 + radius2) + " and distance < Math.abs(radius1 - radius2): " + (distance < Math.abs(radius1 - radius2)));
                if (!(distance > (radius1 + radius2) || distance < Math.abs(radius1 - radius2))) {
                    
                    circlesArray[i].setIntersect(true);
                    circlesArray[j].setIntersect(true);
                }
            }
        }
    }
    /**
     * Draws the circles on the Panel.
     * @param g graphics object for the circles.
     */
    @Override
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
                             
        // Creates 20 circles in an array.
        for (int counter = 0; counter < NUMBER_OF_CIRCLES; counter++) {
            
            circle = new Circle(g);
            circlesArray[counter] = circle;
        }
        // Checks to see if circles intersect or not.
        doCirclesIntersect();
        // Iterates through the circle array to draw them to the CirclesPanel.
        for (Circle cir : circlesArray) {
            cir.setCircleColor(cir.getIntersect());
            cir.drawCircle(cir.getGraphics(), cir.getXCoordinate() + cir.getRadius(), 
                    cir.getYCoordinate() + cir.getRadius(), cir.getRadius());
        }
    }
}