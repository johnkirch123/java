
package edu.frontrange.csc240.a4.coin3;

import java.util.Random;
import java.util.Scanner;

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
 * This variant defines the Coin class as an inner class of the CoinTossing
 * class, and makes the flip method a static method of the same class.
 *
 * @author	Dr. Bruce K. Haddon, Instructor
 * @version	1.1, 2016-08-22, CSC-240, Assignment 4, Exercise 6.29 (variant 3)
 */
public class CoinTossing
{
/**
 * Source of random values for deciding the flip of a coin.
 */
private static final Random RANDOM = new Random();

/**
 * Perform the simulation, and keep count of the number of heads and tails that
 * appear. (As an extra task, the length of the longest run of heads and tails
 * is also tracked--this too, in the long run, should tend to be equal.)
 */
public void run()
{
	int heads = 0;							// the counts of outcomes
	int tails = 0;

	int headsRun = 0;						// the longest run of each coin side
	int tailsRun = 0;

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
				Coin result = flip();		//	otherwise flip a coin
				if( result == null ) continue;
				run = (lastResult == result ? run + 1 : 1);
											//	increment or reset run count
				lastResult = result;		//	remember the last seen

				switch( result )
				{
				case HEADS:
					++heads;				//  count outcomes
					headsRun = max(run, headsRun);
					break;					//	remember the longest run

				case TAILS:
					++tails;				//  count outcomes
					tailsRun = max(run, tailsRun);
					break;					//	remember the longest run

				default:
					throw new IllegalStateException("Not HEADS or TAILS");
				}
			}

			/* Output to the user the current counts, and an indication of the
			   difference as a percentage. */
			int diff = abs(heads - tails);
			long sum = heads + tails;
			out.printf("Counts: Total=%d; %s=%d; %s=%d difference=%1.3g%%%n",
					heads + tails, Coin.HEADS.name(), heads,
					Coin.TAILS.name(), tails, diff * 100.0 / sum);
		}
	}

	/* Output to the user the longest runs. Compare those values to the
	   logarithm (base2) - 1 of the total number of outcomes, as this is the
	   predicted longest run values if the coin is "fair." */
	out.printf("Longest runs: %s=%d; %s=%d  (Expected %d) %n",
			Coin.HEADS.name(), headsRun, Coin.TAILS.name(), tailsRun,
			max(0, round((log(heads + tails) / log(2.0) - 1.0))));
}

/**
 * Main entry point.
 * <p>
 * Execute:</p>
 * <pre> java edu.frontrange.csc240.a4.coin3.CoinTossing</pre>
 *
 * @param args	unused
 */
public static void main(String... args)
{
	new CoinTossing().run();
}

/**
 * Simulate the flip of a coin.
 *
 * @return	HEADS or TAILS, uniformly, but randomly distributed.
 */
private static Coin flip()
{
	return RANDOM.nextBoolean() ? Coin.HEADS : Coin.TAILS;
}

/**
 * The Coin class enumerates the sides of a coin, naming them HEADS and TAILS.
 */
private static enum Coin
{
/**
 * Value representing the heads side of the coin.
 */
HEADS,

/**
 * Value representing the tails side of the coin.
 */
TAILS;
}

}
