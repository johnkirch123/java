
package edu.frontrange.csc240.a4.coin4;

import java.util.Scanner;

import static edu.frontrange.csc240.a4.coin4.Coin.HEADS;
import static edu.frontrange.csc240.a4.coin4.Coin.TAILS;
import static edu.frontrange.csc240.a4.coin4.Coin.getDifferenceCount;
import static edu.frontrange.csc240.a4.coin4.Coin.getTotalCount;
import static java.lang.Math.abs;
import static java.lang.Math.log;
import static java.lang.Math.max;
import static java.lang.Math.round;
import static java.lang.System.in;
import static java.lang.System.out;

/**
 * Class simulates tossing a coin, and counting the outcomes. For a true
 * simulation of a "fair" coin, the number of heads and tails after each toss
 * should tend (in the long run) to be equal.
 * <p>
 * This variant defines the Coin class as an external class to the CoinTossing
 * class. This Coin class includes variables and methods for counting the outcomes
 * of coin flips, in addition to the method {@code flip}.
 *
 * @author	Dr. Bruce K. Haddon, Instructor
 * @version	1.1, 2016-08-22, CSC-240, Assignment 4, Exercise 6.29 (variant 4)
 */
public class CoinTossing
{
/**
 * Perform the simulation, and keep count of the number of heads and tails that
 * appear. (As an extra task, the length of the longest run of heads and tails
 * is also tracked--this too, in the long run, should tend to be equal.)
 */
public void run()
{
	Coin lastResult = null;					// the last coin side seen (if any)
	int run = 0;							// the length of the current run

	/* Create the Scanner to read from the standard input. */
	try( Scanner input = new Scanner(in) )
	{
		while( true )						// repetition environment
		{
			/* Prompt the user with a "menu" of permitted inputs. */
			out.print(" 0 = Done, or Number of Tosses: ");
			long repeats = abs(input.nextLong());
			if( repeats == 0 ) break;		//	end if requested

			while( repeats-- != 0L )
			{
				Coin result = Coin.flip();		//	otherwise flip a coin

				if( result == null ) continue;
												//	increment or reset run count
				run = (lastResult == result ? run + 1 : 1);
				lastResult = result;			//	remember the last seen

				/* Increment the counts for the result of the toss, including the
				   the longest run and the count. */
				result.setLongestRun(run);
				if( result.increment() ) break; // cannot count any further
			}

			/* Output to the user the current counts, and an indication of the
			   difference as a percentage. */
			out.printf("Counts: Total=%d; %s=%d; %s=%d difference=%1.3g%%%n",
					getTotalCount(), Coin.HEADS.name(), HEADS.getCounter(),
					Coin.TAILS.name(), TAILS.getCounter(),
									getDifferenceCount() * 100.0 / getTotalCount());
		}
	}

	/* Output to the user the longest runs. Compare those values to the
	   logarithm (base2) - 1 of the total number of outcomes, as this is the
	   predicted longest run values if the coin is "fair." */
	out.printf("Longest runs: %s=%d; %s=%d  (Expected %d) %n",
			HEADS.name(), HEADS.getLongestRun(),
					TAILS.name(), TAILS.getLongestRun(),
							max(0, round((log(getTotalCount()) / log(2.0) - 1.0))));
}

/**
 * Main entry point.
 * <p>
 * Execute:</p>
 * <pre> java edu.frontrange.csc240.a4.coin4.CoinTossing</pre>
 *
 * @param args	unused
 */
public static void main(String... args)
{
	new CoinTossing().run();
}
}
