package intersectingcircles;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 *
 * @author John Kirch S01581562
 * Lesson 1 Intersecting Circles v1.0
 */
public class Circle {
    
    private Random random;                              // Random generator for colors, coordinates, and radius.
    private Color color;                                // Color of the circle.
    private int xCoord;                                 // X-Coordinate of the circle, top left.
    private int yCoord;                                 // Y-Coordinate of the circle, top left.
    private int diameter;                                 // Radius of the circle.
    private Graphics g;                                 // Graphics object for the circle.
    private boolean intersect;                          // Does the circle intersect with another
    /**
     * Constructor to initialize a circle object.
     * @param g circle graphics object.
     */
    public Circle(Graphics g) {
        
        random = new Random();                          // instantiates the Random class.
        xCoord = random.nextInt(400) + 50;              // initializes the x-coordinate for this circle.
        yCoord = random.nextInt(350) + 50;              // initializes the y-coordinate fo this circle.
        diameter = random.nextInt(150) + 5;             // initializes the diameter for this circle.
        this.g = g;                                     // initializes the graphics object for this circle.
        color = Color.BLACK;                            // initializes the color for this circle.
        intersect = false;                              // initially assume that the circles do not intersect.
    }
    /**
     * Sets the color of the circle to the appropriate color.
     * @param intersect sets value of if circle intersects.
     */
    public void setCircleColor(boolean intersect) {
        
        if (intersect) {
            do {
                
                color = new Color(random.nextFloat() - 155 < 0 ? 0 : random.nextFloat() - 155, random.nextFloat(), random.nextFloat());
                
            } while (color == Color.RED);
            
            
        } else {
            color = Color.RED;
        }
    }
    
    public void setIntersect(boolean intersect) {
        if (!this.intersect) {
            
            this.intersect = intersect;
        }
    }
    /**
     * Draws the circle object.
     * @param g graphics object of the circle.
     * @param x x coordinate starting point for the circle graphic.
     * @param y y coordinate starting point for the circle graphic.
     * @param radius radius of the circle.
     */
    public void drawCircle(Graphics g, int x, int y, int radius) {
        
        setCircleColor(intersect);
        g.setColor(color);
        g.drawOval(x, y, radius, radius);
        
    }
    /**
     * Gets the color of the circle.
     * @return color returns the Color of the circle.
     */
    public Color getCircleColor() {
        return color;
    }
    /**
     * gets the X-Coordinate of the circle, top left.
     * @return x coordinate of circle.
     */
    public int getXCoordinate() {
        return xCoord;
    }
    /**
     * gets Y-Coordinate of the circle, top left.
     * @return y coordinate of circle.
     */
    public int getYCoordinate() {
        return yCoord;
    }
    /**
     * gets the radius of the circle.
     * @return radius of circle.
     */
    public int getRadius() {
        return diameter / 2;
    }
    /**
     * gets the graphics object of the circle.
     * @return graphics object of circle.
     */
    public Graphics getGraphics() {
        return g;
    }
    /**
     * gets the boolean value of if the circles intersect.
     * @return boolean intersect.
     */
    public boolean getIntersect() {
        return intersect;
    }
}
