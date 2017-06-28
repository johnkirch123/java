
package edu.frontrange.csc240.a3;

import edu.frontrange.csc240.util.Debug;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

/**
 * Prints the factorial function of the numbers input by the user.
 *
 * @author	Dr. Bruce K. Haddon, Instructor
 * @version	1.0, 2016-08-22, CSC-240 Assignment 3, Exercise 4.37 part(a)
 */
public class Factorial
{
/**
 * Compute the factorial of the given number, <i>n</i>, which must be greater
 * than or equal to zero. If the value cannot be computed, due to reaching the
 * limits of the long data type, -1 is returned.
 *
 * @param number	the given number, <i>n</i>
 * @return			the factorial of <i>number</i>,
 *									-1 if the value cannot be computed.
 */
public long factorial(int number)
{
	long result = 1L;						// initialize the factorial
	int n = 0;								// n is the current factorial value

	/* Multiply out the factorial until n equals the requested number. */
	for(;  n != number; ++n )
	{
		Debug.println(n + "\t" + result);

		long headroom = Long.MAX_VALUE / (n + 1L);
		Debug.println("HR\t" + headroom);
		if( result > headroom ) return -1L;	// cannot compute next factorial value
		result *= n + 1L;
	}
	Debug.println(n + "\t" + result);
	return result;
}

/**
 * Perform the tasks of the Factorial class
 */
public void run()
{
	/* Create the Scanner to read from the standard input. */
	try( Scanner input = new Scanner(in) )
	{
		int number;
		while( true )								//	repetition environment
		{
			/* Get the number of which the user wants the factorial. */
			out.print("Enter a non-negative integer (0 to quit): ");
			number = input.nextInt();

			if( number < 0 )
			{
				/* The input is negative, so continue at the top of the loop. */
				out.println("That number is negative; please try again.");
				continue;
			}

			long factorial = factorial(number);

			/*  A negative value is returned to signal that the result is
				too large to be contained in a long value. Inform the user,
				and allow another attempt. */
			if( factorial < 0L )
			{
				out.println("That number, " + number +
						", was too large to be " +
						"able to compute the factorial; please try again.");
				continue;
			}

			/* Print the computed value for the user. */
			out.printf("%d! is equal to %d%n", number, factorial);

			/* If the value requested was the factorial of zero, do not
			   repeat the process. */
			if( number == 0 ) break;
		}
	}
}

/**
 * Main entry point.
 * <p>
 * Execute: </p>
 * <pre>java edu.frontrange.csc240.a3.Factorial</pre>
 *
 * @param args	not used. (See "FAQ What is the ... notation" for explanation.)
 */
public static void main(String... args)
{
	Debug.setDebug(true);
	new Factorial().run();
}
}
