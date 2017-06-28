
package edu.frontrange.csc240.a9.example1;

import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Subclass of JFrame to provide the frame for the the JPanel showing the various
 * lines and shapes. A class for this purpose is not present in the original
 * program in the textbook.
 *
 * @author		Dr. Bruce K. Haddon, Instructor
 * @version		1.0, 2016-08-22, CSC-240 Assignment 9, Sample
 */
@SuppressWarnings("serial")
public class PolygonsView extends JFrame
{
/**
 * The initial (default) height of the window.
 */
private static final int FRAME_HEIGHT = 270;

/**
 * The initial (default) width of the window.
 */
private static final int FRAME_WIDTH = 290;

/**
 * Constructor: a window to hold a PolygonsJPanel. Private to prevent uncontrolled
 * instantiation.
 */
private PolygonsView() {}

/**
 * Build and start the GUI within this JFrame.
 */
@SuppressWarnings("Convert2Lambda")
public void start()
{
	/* The Controller for this view. Get it, and initialize it. */
	PolygonsController controller = new PolygonsController(this);
	controller.initialize();

	/* Set the title of the window. */
	setTitle(controller.getTitle());

	/* Add the drawing panel to the window. */
	JComponent canvas = controller.getCanvas();
	add(canvas);

	/* Initiate the showing of the window using the Event Dispatch Thread. */
	SwingUtilities.invokeLater(new Runnable()
			{
				/**
				 * The run method of the anonymous class.
				 */
				@Override
				public void run()
				{
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
					pack();
					setMinimumSize(getSize());
					setVisible(true);
				}
			});
}

/**
 * Launch the application by building and starting the GUI.
 */
public static void launch()
{
	PolygonsView view = new PolygonsView();
	view.start();
}
}
