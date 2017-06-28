
package edu.frontrange.csc240.a5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class provides methods for processing "turtle graphics" programs into
 * an executable form, and for executing that form.
 * <p>
 * This is only a "skeleton." The statements seen in the skeleton methods are there
 * just to make the class compilable--they are not part of any intended solution.
 *
 * @author		Dr. Bruce K. Haddon, Instructor
 * @version		1.1, 2016-08-22, CSC-240, Assignment 5, Exercise 7.21
 */
public class TurtleGraphics
{
/**
 * This is just as an example.
 */
private static final int INPUT_WITH_COMMA = 5;
/**
 * Accepts a turtle program from a Scanner.
 *
 * @param source	Scanner that is the source of Turtle commands
 * @return			List of messages about the compilation (not null)
 */
public List<String> enterCommands(Scanner source)
{
	List<String> messages = new ArrayList<>();
	while( source.hasNextInt() )
	{
		/* This code is just an example. This code will satisfy the test program,
		   and demonstrates that the input is available from the given Scanner
		   instance. This code does not do the work that is specified in the
		   Assignment Instructions. Replace by the reuired code. */
		int next = source.nextInt();
		String input = Integer.toString(next);
		if( next == INPUT_WITH_COMMA && source.hasNextInt() )
				 messages.add(input + "," + source.nextInt());
		else
			messages.add(input);
	}
	return messages;
}

/**
 * Execute the commands that have been stored in the command array.
 *
 * @return		false if the program cannot be executed, otherwise true
 */
public boolean executeCommands()
{
	/* Code just to satisfy the test code. Replace by real code. */
	return false;
}
}
