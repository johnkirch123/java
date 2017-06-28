
package edu.frontrange.csc240.a9.example1;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

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
public class PolygonsJPanel extends JPanel
{
private final PolygonsModel model;

/**
 * Constructor.
 * <p>
 * The creation and initialization of data and objects has been moved out of
 * the paintComponent method. This method is called by the Swing system (often
 * at the request of the operating system), and should not include unnecessary
 * object (array and shape) construction when this can be avoided.
 *
 * @param model		the model for the application
 */
public PolygonsJPanel(PolygonsModel model)
{
	/* The model for the drawing data. */
	this.model = model;
}

/**
 * paintComponent is called by the Swing system whenever there is a need to
 * update this JPanel on the screen. This may happen for many reasons and quite
 * frequently.
 *
 * @param g		the Graphics object (environment) being used for the JPanel
 */
@Override
public void paintComponent(Graphics g)
{
	/* Cast the graphics instance to Graphics2D, since it really is a Graphics2D
	   object. */
	Graphics2D g2d = (Graphics2D) g;

	/* Call the method of the super class. */
	super.paintComponent(g2d);

	/* Allow the model to draw the shapes. */
	model.draw(g2d);
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
