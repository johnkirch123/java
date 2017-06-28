
package edu.frontrange.csc240.a5.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Objects.requireNonNull;

/**
 * Assembles a list of available Turtle programs, and, as needed, provides
 * access to those programs.
 *
 * @author	Dr. Bruce K. Haddon, Instructor
 * @version	2.0, 2016-08-22, CSC-240, Assignment 5, Exercise 7.21 (alternative)
 */
public class Programs
{
/**
 * Allow the "order of execution" value be 00 to 99.
 */
private static final int DIGITS_IN_ORDER_VALUE = 2;

/**
 * Pattern to extract the order of execution from the property value.
 */
private static final Pattern PATTERN = Pattern.compile("^\\[(\\d+)\\]");

/**
 * The name of the properties file containing the list of programs.
 */
private static final String PROPERTY_FILE = "programs.properties";

/**
 * Map to hold titles of programs, and the location of the corresponding file.
 */
private Map<String, String> filesnames = new TreeMap<>();

/**
 * Map to hold titles of programs, and whether that program is to be repeated.
 */
private Map<String, Boolean> repeats = new HashMap<>();

/**
 * Any currently open scanner.
 */
private Scanner scanner;

/**
 * Title of currently open program.
 */
private String title;

/**
 * Constructor.
 * <p>
 * Initializes the list of found programs, and keeps an association between the
 * title of the program, and the file containing the program.
 */
public Programs()
{
	/* Place to load the properties file. */
	Properties programList = new Properties();

	/* Get stream to read the properties file that lists the available (and
	   useable) Turtle programs: the files containing the programs are in the
	   same location. */
	try( InputStream programProperties =
			getClass().getResourceAsStream(PROPERTY_FILE) )
	{
		programList.load(requireNonNull(programProperties));
	} catch( NullPointerException | IllegalArgumentException | IOException ex )
	{
		throw new AssertionError(PROPERTY_FILE + " cannot be accessed.", ex);
	}

	/* Sequence through the properties, getting the name of the property (i.e,
	   the title of the program), the order or presentation, and check that the
	   correponding file exists. Also determine whether the program is to be
	   repeated. */
	for( Enumeration<?> names =
			programList.propertyNames(); names.hasMoreElements(); )
	{
		/* Get the name of the property, and the value of the String to which it
		   refers. */
		String name = (String) names.nextElement();
		String propertyValue = programList.getProperty(name).trim();

		/* The property value contains the order in which the drawing is to be
		   executed, the name of the file in which the program is stored, and a
		   flag to indicate whether the diagram is to be repeated. */
		Matcher matcher = PATTERN.matcher(propertyValue);
		String match;
		if( matcher.find() )
		{
			/* Get the digits matched, and delete from the property value. */
			match = matcher.group(1);
			propertyValue = propertyValue.replace("[" + match + "]", "").trim();

			/* Remove leading zeroes, and then replace them until the value is
			   is exactly two digits. Give up if this cannot be done. */
			while( match.startsWith("0") ) match = match.substring(1);
			while( match.length() < DIGITS_IN_ORDER_VALUE ) match = "0" + match;
			if( match.length() > DIGITS_IN_ORDER_VALUE ) continue;
			name = "[" + match + "] " + name;
		}
		/* If there is no execution order value, then ignore the entry. */
		else
			continue;

		/* Remove any indication that a repeat is needed. */
		String resourceName = propertyValue.replace("(repeat)", "").trim();

		/* Open the resource as an input stream. If successful, record the title
		   of the program, the name of the resource, and whether there should be
		   a repeated drawing output. */
		try( InputStream trial = getClass().getResourceAsStream(resourceName) )
		{
			if( trial != null )
			{
				this.title = name.replace('_', ' ');

				/* If anything but null was the previous value, then the title
				   has been duplicated. */
				String value = filesnames.put(this.title, resourceName);
				if( value != null )
					throw new AssertionError("Title \"" + this.title +
							"\" is repeated");
				repeats.put(this.title, !propertyValue.equals(resourceName));
			}
		} catch( NullPointerException | IOException ex )
		{
			/* Do nothing. */
		}
	}
	this.title = null;
}

/**
 * Close and currently open program (i.e., scanner).
 *
 * @param scanner	the scanner for the currently open program.
 */
public void close(Scanner scanner)
{
	if( this.scanner == null )
		throw new AssertionError("Close called with no currently open program.");

	if( this.scanner != scanner )
		throw new AssertionError("Close called with not currently open program.");

	this.scanner.close();
	this.scanner = null;
	this.title = null;
}

/**
 * List of program titles.
 *
 * @return	array containing program titles
 */
public String[] getProgramTitles()
{
	return filesnames.keySet().toArray(new String[filesnames.size()]);
}

/**
 * Is repeat set for this title?
 *
 * @return	true if repeat is set
 */
public boolean isProgramRepeated()
{
	Boolean result = repeats.get(this.title);
	return result != null && result;
}

/**
 * Obtain an open scanner referring to the given program.
 *
 * @param title	title of the program
 * @return	open scanner
 */
public Scanner open(String title)
{
	/* If the is a current scanner, then another program cannot be opened. */
	if( scanner != null )
		throw new AssertionError("A program is already open");

	/* Remember this title. */
	this.title = title;

	/* Get the name of the resource (a file) corresponding to the title. */
	String filename = filesnames.get(this.title);
	try
	{
		/* Create as scanner, and set the delimiter. */
		scanner = new Scanner(getClass().getResourceAsStream(filename));
		scanner.useDelimiter("(\\s)+|,(\\s)*");
	} catch( Exception ex )
	{
		throw new AssertionError(filename + " could not be accessed.");
	}
	return scanner;
}

///**
// * Trivial test program.
// *
// * @param args		not used
// */
//public static void main(String... args)
//{
//	Programs p = new Programs();
//	String[] list = p.getProgramTitles();
//	if( list.length != 10 )
//		throw new AssertionError("Not all files found");
//	Scanner one = p.getProgram(list[0]);
//	while( one.hasNextLine() )
//		System.out.println(one.nextLine());
//	p.closeProgram();
//}
}
