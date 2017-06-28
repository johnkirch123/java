
package edu.frontrange.csc240.a4.coin2;

import java.util.EnumMap;
import java.util.Scanner;
import java.util.StringJoiner;

import static edu.frontrange.csc240.a4.coin2.Coin.HEADS;
import static edu.frontrange.csc240.a4.coin2.Coin.TAILS;
import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.abs;
import static java.lang.Math.log;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.round;
import static java.lang.System.in;
import static java.lang.System.out;

/**
 * Class simulates tossing a coin, and counting the outcomes. For a true
 * simulation of a "fair" coin, the number of heads and tails after each toss
 * should tend (in the long run) to be equal.
 * <p>
 * This variant defines the Coin class as an external class, and makes the flip
 * a method of that same external class. The approach taken with the alternative
 * Dice program is also used in this class.
 *
 * @author	Dr. Bruce K. Haddon, Instructor
 * @version	1.1, 2016-08-22, CSC-240, Assignment 4, Exercise 6.29 (variant 2)
 */
public class CoinTossing
{
/**
 * Marker for the maximum count.
 */
private static final String MAX = " (max)";

/**
 * Marker for the minimum count.
 */
private static final String MIN = " (min)";

/**
 * Constant to convert to percent.
 */
private static final double PERCENT = 100.0;

/**
 * Perform the simulation, and keep count of the number of heads and tails that
 * appear. (As an extra task, the length of the longest run of heads and tails
 * is also tracked--this too, in the long run, should tend to be equal.)
 */
public void run()
{
	/* The counts of outcomes. */
	EnumMap<Coin, Integer> counts = new EnumMap<>(Coin.class);
	for( Coin coin : Coin.values() ) counts.put(coin, 0);

	/* The longest run of each coin side. */
	EnumMap<Coin, Integer> runs = new EnumMap<>(Coin.class);
	for( Coin coin : Coin.values() ) runs.put(coin, 0);

	Coin lastResult = null;					// the last coin side seen (if any)
	int run = 0;							// the length of the current run

	/* Create the Scanner to read from the standard input. */
	try( Scanner input = new Scanner(in) )
	{
		while( true )							// repetition environment
		{
			/* Prompt the user with a "menu" of permitted inputs. */
			out.print(" 0 = Done, or Number of Tosses: ");
			long repeats = abs(input.nextLong());
			if( repeats == 0L ) break;			//	end if requested

			while( repeats-- != 0L )
			{
				Coin coin = Coin.flip();		//	otherwise flip a coin
				if( coin == null ) continue;
				run = (lastResult == coin ? run + 1 : 1);
												//	increment or reset run count
				lastResult = coin;				//	remember the last seen

				/* Count the outcomes. */
				int count = counts.get(coin) + 1;
				counts.put(coin, count);
				runs.put(coin, max(run, runs.get(coin)));
				if( count == Integer.MAX_VALUE ) break;
			}

			/* Maximum and minumum of the counts. */
			long sum = 0L;
			int maximum = 0;
			int minimum = MAX_VALUE;
			for( Coin coin : Coin.values() )
			{
				int count =  counts.get(coin);
				sum += count;
				maximum = max(maximum, count);
				minimum = min(minimum, count);
			}
			
			/* Output to the user the current counts. */
			StringJoiner output = new StringJoiner(System.lineSeparator());
			output.add("Counts").
			add("Total=" + sum);
			for( Coin coin : Coin.values() )		
				output.add(report(coin.name(), counts.get(coin), maximum, minimum));
			output.add("Variation=" + String.format("%.3g%%",
							(maximum - minimum) * PERCENT / 
									((long) maximum + minimum) / 2.0 ));
			out.println(output.toString());
		}
	}

	/* Output to the user the longest runs. Compare those values to the
	   logarithm (base2) - 1 of the total number of outcomes, as this is the
	   predicted longest run values if the coin is "fair." */
	out.printf("Longest runs: %s=%d; %s=%d  (Expected %d) %n",
			HEADS.name(), runs.get(HEADS), TAILS.name(), runs.get(HEADS),
					max(0, round((log(counts.get(HEADS) + 
										counts.get(TAILS)) / log(2.0) - 1.0))));
}

/* Create the report string for each count. */
private String report(String name, int count, int max, int min)	
{
	return name + "=" + count + 
						(count == max ? MAX : "") +	(count == min ? MIN : "");
}

/**
 * Main entry point.
 * <p>
 * Execute: </p>
 * <pre> java edu.frontrange.csc240.a4.coin2.CoinTossing</pre>
 *
 * @param args	unused
 */
public static void main(String... args)
{
	new CoinTossing().run();
}
}
