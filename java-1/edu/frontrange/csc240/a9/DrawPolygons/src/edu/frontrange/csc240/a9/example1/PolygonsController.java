
package edu.frontrange.csc240.a9.example1;

import javax.swing.JComponent;

/**
 * Controller for the exercise of displaying a tic-tac-toe board on the screen.
 *
 * @author		Dr. Bruce K. Haddon, Instructor
 * @version		2.0, 2016-11-17, CSC-240 Assignment 9, Sample
 */
public class PolygonsController
{
/**
 * (GUI) The panel holding and showing the actual board.
 */
private PolygonsJPanel polygonsPanel;

/**
 * (GUI) Title
 */
private String title;

/**
 * The model defining the data and the polygons.
 */
private final PolygonsModel model;

/**
 * The view for which this is the controller.
 */
private final PolygonsView view;

/**
 * Constructor.
 *
 * @param view			the view for which this is the controller
 */
public PolygonsController(PolygonsView view)
{
	/* The view for which this is the controller. */
	this.view = view;
	/* Set up the model. */
	model = new PolygonsModel();
}

/**
 * Get the panel on which the tic tac toe board is to be drawn.
 *
 * @return the boardPanel
 */
public JComponent getCanvas()
{
	return polygonsPanel;
}

/**
 * @return the title
 */
public String getTitle()
{
	return title;
}

/**
 * Create the components needed by the view.
 */
public void initialize()
{
	/* Create a panel to act as the drawing canvas. */
	polygonsPanel = new PolygonsJPanel(model);

	/* Set the title. */
	title = "Draw Polygons";
}
}
