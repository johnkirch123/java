
package edu.frontrange.csc.a5;

import data.Programs;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

import static java.lang.Math.max;
import static java.lang.System.out;

/**
 * @author John Kirch, S01581562
 * @version 02-20-2017, CSC-240 Assignment 5, Exercise 7.21
 */
public class TurtleGraphicsTest
{
/**
 * The width of the space used for output of test results.
 */
private static final int OUTPUT_WIDTH = 48;

/**
 * Execute the tests.
 */
public void run()
{
	/* Instantiate one copy of the TurtleGraphics class. */
	TurtleGraphics drawing = new TurtleGraphics();

	/* Instantiate the source of the list of programs. */
	Programs programs = new Programs();

	for( String title : programs.getProgramTitles() )
	{
		/* Open the next program. */
		Scanner scanner = programs.open(title);
	
		/* Have the TurtleGraphics class "oompile" the program. It returns an
		   array of messages. */
		List<String> messages = drawing.enterCommands(scanner);

		/*  The output has a header with a title before each program, so that the
		   drawing may be identified. */
		writeHeader("A Program", title);
		writeMessage(messages);
		writeHeader("The Drawing", title);
		if( drawing.executeCommands() )
			/* Repeat a drawing (if there is one) to show that a drawing can be
			   repeated without entering the commands again. */
			if( programs.isProgramRepeated() )
			{
				writeHeader("Repeat Drawing", title);
				drawing.executeCommands();
			}
		programs.close(scanner);
	}
}

/**
 * Main entry point for the test program.
 * <p>
 * Execute: </p>
 * <pre>java edu.frontrange.csc240.a5.TurtleGraphicsTest</pre>
 *
 * @param args	unused
 */
public static void main(String[] args)
{
	new TurtleGraphicsTest().run();
}

/**
 * Write a header. This consists of a tag (e.g., The Drawing), together with an
 * actual title (as given by the user). The header is written nominally centered
 * on the given width of the floor, preceded and followed by a mid- level line
 * (made up of '-' characters.
 *
 * @param tag		 the header tag
 * @param title	the given title
 */
private static void writeHeader(String tag, String title)
{
	/* Make up the string with the tag and the title. */
	String header = " " + tag + (title != null ? ": " + title + " " : " ");

	/* The number of '-' characters is the floor size, less the length of the
	   string. To center, use just half of that on each side. Ensure that there
	   is at least one. */
	int center = max((OUTPUT_WIDTH - header.length()) / 2, 1);

	out.println();
	for( int i = 0; i != center; ++i ) out.print("-");
	out.print(header);
	for( int i = 0; i != center; ++i ) out.print("-");
	out.println();
}

/**
 * Write a program listing. The result from the TurtleGraphics enterCommands is
 * broken into lines.
 *
 * @param tag		 the header tag
 * @param title	the given title
 */
private static void writeMessage(List<String> messages)
{
	StringJoiner joiner = new StringJoiner(" ");
	for( String next : messages )
	{
		/* If the next addition will go beyond the limit ... */
		if( joiner.length() + next.length() > OUTPUT_WIDTH )
		{
			/* ... then print the current line, and start a new one. */
			out.println(joiner.toString());
			joiner = new StringJoiner(" ");
		}
		joiner.add(next);
	}
	if( joiner.length() > 0 ) out.println(joiner.toString());
}
}
