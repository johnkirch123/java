
package assignment.frontrange.csc240.util;

import java.io.PrintStream;

/**
 * This class wraps the functionality of PrintStream (printing to a given
 * PrintStream) making the output conditional upon the state of the variable
 * "debug". By setting this false, all debug output can be suppressed.
 * <p>
 * These methods are used by writing the form: </p>
 * <blockquote><pre>
 * Debug.printf( ... )
 * </pre></blockquote>
 * <p>
 * or equivalently, the methods print, and println.</p>
 * <p>
 * As many other specific print methods of a given type may be added as needed
 * using the same pattern. The implemented methods should include adding the "debug"
 * indicator to the beginning of each line.
 *
 * @author	Dr. Bruce K. Haddon
 * @version 1.1, 2016-10-21.
 */
public class Debug
{
/**
 * Debug indicator.
 * */
private static final String INDICATOR = "*db ";

/**
 * The state of the "debug" flag.
 */
private static boolean debug = true;

/**
 * The PrintStream to which to send the output.
 */
private static PrintStream output = System.out;

/**
 * Constructor - to prevent instantiation
 */
private Debug() {}
/**
 * Get the current state of the debug flag.
 *
 * @return		the current state
 */
public static boolean getDebug()
{
	return Debug.debug;
}

/**
 * Delegate to print for the type String.
 *
 * @param string the string to print
 */
public static void print(String string)
{
	if( debug ) output.print(INDICATOR + string);
}

/**
 * Delegate to print a formatted output.
 *
 * @param format the formatter String
 * @param args   the arguments to the formatter String
 * @return the PrintStream used
 */
public static PrintStream printf(String format, Object... args)
{
	if( debug )
	{
		output.print(INDICATOR);
		output.printf(format, args);
	}
	return output;
}

/**
 * Delegate to print an end-of-line.
 */
public static void println()
{
	if( debug ) output.println(INDICATOR);
}

/**
 * Delegate to print a string followed by an end-of-line.
 *
 * @param string	the string to print on this line
 */
public static void println(String string)
{
	if( debug ) output.println(INDICATOR + string);
}

/**
 * Change the debug state.
 *
 * @param debug the desired debug state
 * @return the previous value (so it may be restored if needed)
 */
public static boolean setDebug(boolean debug)
{
	boolean was = Debug.debug;
	Debug.debug = debug;
	return was;
}

/**
 * Change the PrintStream.
 *
 * @param output the desired PrintStream to use
 * @return the previous value (so it may be restored if needed)
 */
public static PrintStream setPrintStream(PrintStream output)
{
	PrintStream was = Debug.output;
	Debug.output = output;
	return was;
}
}
