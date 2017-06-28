
package edu.frontrange.csc240.a9.example1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Path2D;
import javax.swing.JPanel;

import static java.awt.Color.CYAN;

/**
 * This class is based on the class of Figure 13.27 PolygonsJPanel.java (Drawing
 * polygons) of Edition 10 of the textbook.
 * <p>
 * It has been revised to ensure (where required) the arrays are of the same
 * length, and that the actual lengths are used appropriately as parameters.
 * In addition, each drawing is colored differently, to clearly show which code
 * sequence generates which outcome.</p>
 * <p>
 *
 * @author		Deitel &amp; Associates (see copyright at end)
 * @author		Dr. Bruce K. Haddon, Instructor
 * @version		1.0, 2016-08-22, CSC-240 Assignment 9, Sample
 */
@SuppressWarnings("serial")
public class PolygonsModel extends JPanel
{
/**
 * x and y values for polygon 1.
 */
private final int[] X_VALUES1;
private final int[] Y_VALUES1;

/**
 * x and y values for a series of lines.
 */
private final int[] X_VALUES2;
private final int[] Y_VALUES2;

/**
 * x and y values for series of lines forming a closed polygon.
 */
private final int[] X_VALUES3;
private final int[] Y_VALUES3;

/**
 * The first polygon.
 */
private final Polygon polygon1;

/**
 * The second polygon.
 */
private final Path2D polygon2;

/**
 * Constructor.
 * <p>
 * The data and the shapes to be drawn are initialized.
 * <p>
 * The draw method, when given a graphic environment, draws the shapes.
 */
public PolygonsModel()
{
	X_VALUES1 = new int[] {20, 40, 50, 30, 20, 15};
	Y_VALUES1 = new int[]{50, 50, 60, 80, 80, 60};

	X_VALUES2 = new int[]{70, 90, 100, 80, 70, 65, 60};
	Y_VALUES2 = new int[]{100, 100, 110, 110, 130, 110, 90};

	X_VALUES3 = new int[]{110, 140, 150, 190};
	Y_VALUES3 = new int[]{40, 70, 100, 60};

	/* Create polygon1 from the given x and y values. The polygon is automatically
																closed.*/
	assert X_VALUES1.length == Y_VALUES1.length :
								"Number of x and y values not the same";
	polygon1 = new Polygon(X_VALUES1, Y_VALUES1, X_VALUES1.length);


	assert X_VALUES2.length == Y_VALUES2.length :
								"Number of x and y values not the same";

	assert X_VALUES3.length == Y_VALUES3.length :
								"Number of x and y values not the same";

	/* Create polygon2 by adding points. The polgyon is closed using closePath. */
	polygon2 = new Path2D.Float();
	polygon2.moveTo(165, 135);				// moveTo the initial position
	polygon2.lineTo(175, 150);
	polygon2.lineTo(270, 200);
	polygon2.lineTo(200, 220);
	polygon2.lineTo(130, 180);
	polygon2.closePath();
}

/**
 * Draw the shapes upon request.
 *
 * @param g2d		the Graphics object (environment)
 */
public void draw(Graphics2D g2d)
{
	/* Cast the given Graphics object to an object of type Graphics2D. */
//	Graphics2D g2 = (Graphics2D) g;

	/* Draw the first polygon object. */
	g2d.setColor(Color.GREEN);
	g2d.drawPolygon(polygon1);

	/* Draw polylines the given x and y values. */
	g2d.setColor(Color.MAGENTA);
	g2d.drawPolyline(X_VALUES2, Y_VALUES2, X_VALUES2.length);

	/* Draw and fill a polygon with the given x and y values. */
	g2d.setColor(CYAN);
	g2d.fillPolygon(X_VALUES3, Y_VALUES3, X_VALUES3.length);

	/* Draw a filled polygon with the defined polygon object. */
	g2d.setColor(Color.RED);
	g2d.fill(polygon2);
}
}

/*-************************************************************************
 * (C) Copyright 1992-2014 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/
