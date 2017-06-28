
package edu.frontrange.csc240.a4.guess;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

/**
 * Set up and play the traditional guessing game for a number selected between
 * known limits.
 *
 * @author	Dr. Bruce K. Haddon, Instructor
 * @version	1.3, 2016-08-22, CSC-240, Assignment 4, Exercise 6.31
 */
public class GuessingGame
{
private static final int LOWER_LIMIT = 1;		// inclusive

private static final int UPPER_LIMIT = 1_000;	// inclusive

/**
 * Main entry point.
 * <p>
 * Execute:</p>
 * <pre>java edu.frontrange.csc240.a4.guess.GuessingGame</pre>
 *
 * @param args	unused.
 */
public static void main(String... args)
{
	new GuessingGame().run();
}

/**
 * Set up the conditions of the game (i.e., the limits, and the Scanner from
 * which the input will come), and then initiate play.
 */
public void run()
{
	/* Create the Scanner to read from the standard input. This is using the
	   try-with-resources statement that was added to Java in the Java 7 release. It
	   ensures that even if an exception is thrown by any of the statements enclosed
	   in the body of the try block, that the Scanner resource will be closed (see
	   page 467 in Edition 10 of the textbook). */
	try( Scanner input = new Scanner(in) )
	{
		/* The limits of the game are used to find a random number within
		   (inclusive of both the lower value and the upper value) of those 
		   limits, and to instantiate a Guess object to play that game. */ 
		Guess game = new Guess(input, LOWER_LIMIT, UPPER_LIMIT);
		do
		{
			game.play();
			/* After the first, the user is asked whether to play another game. */
			out.print("Do you want to play again? (Y/N):");
		} while( !input.next().toLowerCase().contains("n") );
		/* The answer is taken as "yes" so long as it does not contain the
		   letter "n". This works for "no," "non", "nein," "nyet," "ni," "nee,"
		   "nie," "ne", "nohi," "nem," "nai," "naha," "hapana," and many
		   negatives in other languages. */
	}
}
}
