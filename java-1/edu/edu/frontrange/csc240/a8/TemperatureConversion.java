
package edu.frontrange.csc240.a8;

/**
 * Main application. This sets up the Model, View and Controller (using the MVC
 * design paradigm) for the temperature conversion application. Further details
 * on this design pattern may be found at
 *  <a href=http://en.wikipedia.org/wiki/Model%E2%80%93View%E2%80%93Controller>
 * http://en.wikipedia.org/wiki/Model%E2%80%93View%E2%80%93Controller</a>.
 * <p>
 * The converter is actually an enum class which defines the temperature scales
 * (named {@link Scale} in this application). This converter is used by the
 * Model to do the needed conversions. The Controller is an instance of a class
 * to which the View delegates all user actions (since the actual Swing
 * architecture is also view/delegate.) The View is displayed in a frame
 * ({@link javax.swing.JFrame}) created by the main application entry point,
 * which is in this class.</p>
 * <p>
 * Swing elements all include a warning that they are not (with few exceptions)
 * designed for multi-threaded execution, that all operations must happen on
 * a single &ldquo;dispatch&rdquo; thread. While it is possible and quite
 * workable for a Swing application to be constructed and run using the main
 * thread as the dispatch thread (so long as the main thread does nothing more
 * after realizing (<i>i.e.</i>, packing and/or making visible) the frame, it is
 * always recommended to schedule activities on the View to happen on the
 * dispatch thread, which is done using the {@link javax.swing.SwingUtilities}
 * class, and the {@code invokeLater} method. This permits the initial main
 * thread to end. The application will stay alive so long as the Swing dispatch
 * thread continues to be alive.
 *
 * @author	Dr. Bruce K. Haddon, Instructor
 * @version	2.0, 2016-10-15, Assignment 8, Exercise 12.13
 */
public class TemperatureConversion
{
/**
 * Private constructor to prevent instantiation other than here in the main
 * method.
 */
private TemperatureConversion() {}

/**
 * Main entry point.
 * <p>
 * Execute:</p>
 * <pre>java edu.frontrange.csc240.a8.TemperatureConversion</pre>
 *
 * @param args	Not used.
 */
public static void main(String... args)
{
	/* Create an instance of the main application class, and start it
	   running. There is no need to keep a reference to the instance of the
	   main application class, as the main thread ends as soon as the
	   application is initiated. */
	View.launch();
}
}

