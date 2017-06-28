
package edu.frontrange.csc240.a8;

import java.awt.Component;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import static javax.swing.JOptionPane.ERROR_MESSAGE;

/**
 * The View will pass input data to the Controller which will decide whether that
 * data represents new information, and will take display information from the
 * Model. In both cases, the View will register with the Controller and the Model as
 * an observer.
 * <p>
 * The View panel holds two <strong>SelectionPanel</strong>s. One is editable,
 * and allows input of temperatures, the other is not editable, and is used to
 * display the converted temperature.
 * <p>
 * Any time any event happens on either of the <strong>SelectionPanel</strong>s,
 * they are both updated, the input Temperature Panel being done first in case
 * the change was to the entered temperature value.</p>
 *
 * @author	Dr. Bruce K. Haddon, Instructor
 * @version	2.0, 2016-10-15, Assignment 8, Exercise 12.13
 */
@SuppressWarnings("serial")
public class View extends JFrame implements Observer
{
/**
 * Controller for this view.
 */
private Controller controller;

/**
 * The editable (see argument editable) temperaturePanel.
 */
private ViewSelectionPanel inputTemperaturePanel;

/**
 * The un-editable (see argument editable) temperaturePanel.
 */
private ViewSelectionPanel outputTemperaturePanel;

/**
 * Used to ensure that only one message pane at a time is shown.
 */
private boolean showing;

/**
 * Constructor.
 */
public View() {}

/**
 * Called when the view should display an error message.
 *
 * @param location		component near which to display the message (null if none)
 * @param title			title to appear with the message
 * @param message		the message to display
 */
@SuppressWarnings("Convert2Lambda")
public void displayErrorMessage(Component location,
											String title, final String message)
{
	if( !showing )
	{
		final Component position = location == null ? this : location;
		final String dialogTitle = title == null ? "" : title;
		showing = true;
		SwingUtilities.invokeLater(new Runnable()
		{
			/**
			 * This run method is executed on the EDT
			 */
			@Override
			public void run()
			{
				JOptionPane.showMessageDialog(
				position,						// location
				message,						// message
				dialogTitle,					// dialog title
				ERROR_MESSAGE);					// message type
				showing = false;
			}
		});
	}
}

/**
 * Start the view: set up the layout and invoke the dispatch thread.
 */
@SuppressWarnings("Convert2Lambda")
public void start()
{
	/* The Controller is set up and initialized to use this Model for the
	   information that it needs to control (set or change), and to be the
	   controller to validate and pass on to the Model any relevant information
	   entered by the user. */
	controller = new Controller(this);
	controller.initialize();

	setTitle(controller.getTitle());

	/* The main window has two panels placed upon it, one below the other.
	   This layout is done with a BoxLayout. */
	setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

	/* The first panel has a textfield that is editable
	   (i.e., the "from" panel). */
	inputTemperaturePanel =	new ViewSelectionPanel(controller, true);
	add(inputTemperaturePanel);
	inputTemperaturePanel.initialize();


	/* The second panel has a textfield that's not editable
	   (i.e., the "to" panel). */
	outputTemperaturePanel =
			new ViewSelectionPanel(controller, false);
	add(outputTemperaturePanel);
	outputTemperaturePanel.initialize();

	/* Start the GUI using only the dispatch thread. */
	SwingUtilities.invokeLater(new Runnable()
	{
		/**
		 * The run method is executed on the EDT
		 */
		@Override
		public void run()
		{
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			pack();
			setMinimumSize(getSize());
			setVisible(true);		// display frame
		}
	});
}

/**
 * The callback from the observed model whenever the Model changes.
 *
 * @param observable the object being observed
 * @param object any report from that object (not used)
 */
@Override
public void update(Observable observable, Object object)
{
	repaint();
}

/**
 * Launch the view, by creating the view object, and calling start.
 */
public static void launch()
{
	View view = new View();
	view.start();
}
}
