
package craps.game;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

/**
 * Setup and play a game of craps. After each game, the player is asked whether
 * to play another game.
 *
 * @author		Dr. Bruce K. Haddon, Instructor
 * @version		1.1, 2016-08-22, CSC-240 Assignment 4, Sample based on Figure 6.8
 *				and modified by Exercise 6.33 from the textbook.
 */
public class CrapsGame
{
private static final double INITIAL_BANK_BALANCE = 1000.0;
/**
 * Do the work of the CrapsGame class.
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
		/* This loop calls for a game to be played. When that game ends, the player
		   is asked whether another game is desired. Any answer containing the
		   letter "n" (or "N") means no, otherwise the program loops and starts
		   another game. */
		do
		{
			/* Create an instance of the Craps class, that is the class that using
			   the play method, plays each game.  */
			Craps game = new Craps(INITIAL_BANK_BALANCE, input);
			game.play();

			/* After the first, the user is asked whether to play another game. */
			out.print("Do you want to play again? (Y/N):");
		} while( !input.next().toLowerCase().contains("n") );
		/* The answer is taken as "yes" so long as it does not contain the letter
		   "n". This works for "no," "non", "nein," "nyet," "ni," "nee," "nie,"
		   "ne", "nohi," "nem," "nai," "naha," "hapana," and many negatives in other
		   languages.  */
	}
}

/**
 * Main entry point.
 * <p>
 * Execute:</p>
 * <pre>java edu.frontrange.csc240.a4.example3.CrapsGame</pre>
 *
 * @param args	unused.
 */
public static void main(String... args)
{
	new CrapsGame().run();
}
}
