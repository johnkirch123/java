package edu.frontrange.csc240.a4.coin4;

import java.util.Random;

import static java.lang.Math.abs;
import static java.lang.Math.max;

/**
 * The Coin class enumerates the sides of a coin, naming them HEADS and TAILS.
 * <p>
 * This version of the Coin class incorporates all the counting logic of flips into
 * the class itself. (This could have been taken one step further, and had the
 * counting be done in the {@code flip} method when the result of the flip was
 * known, but in fact, this implementation leaves it to the user of the class to
 * decide whether each flip is to be counted, or not.) </p>
 * <p>
 * Note that the methods that operate on each instance of the side of the coin
 * are <strong>not</strong> designated static, whereas the methods the use data
 * that involve all the instances of the class (including the {@code flip} method
 * itself) are indeed static.
 */
public enum Coin
{
/**
 * Value representing the heads side of the coin.
 */
HEADS,

/**
 * Value representing the tails side of the coin.
 */
TAILS;

/**
 * Source of random values for deciding the flip of a coin.
 */
private static final Random RANDOM = new Random();

/**
 * Counter for each enum value of the class.
 */
private int counter;

/**
 * Longest run for each enum value of the class.
 */
private int longestRun;

/**
 * Get the current value of the counter associated for this enum value.
 *
 * @return		the counter value
 */
public int getCounter()
{
	return counter;
}

/**
 * Get the current value of the longest run counter for this enum value.
 *
 * @return		the current longest run
 */
public int getLongestRun()
{
	return longestRun;
}

/**
 * If the current run is longer that any previous, then set this as the longest run.
 *
 * @param currentRun	the current value of the current run.
 */
public void setLongestRun(int currentRun)
{
	longestRun = max(longestRun, currentRun);
}

/**
 * Increment the counter associated with this enum value.
 *
 * @return		true if the count has reached the maximum value.
 */
public boolean increment()
{
	++counter;
	return counter == Integer.MAX_VALUE;

}

/**
 * Get the difference of the counts (i.e., the difference in number of counted
 * flips) for the two sides of the coin.
 *
 * @return		the total counts
 */
@SuppressWarnings("AccessingNonPublicFieldOfAnotherObject")
public static int getDifferenceCount()
{
	return abs(HEADS.counter - TAILS.counter);
}

/**
 * Get the total of the counts (i.e., the total number of counted flips) for the
 * two sides of the coin.
 *
 * @return		the total counts
 */
@SuppressWarnings("AccessingNonPublicFieldOfAnotherObject")
public static long getTotalCount()
{
	return HEADS.counter + TAILS.counter;
}

/**
 * Simulate the flip of a coin.
 *
 * @return	HEADS or TAILS, uniformly, but randomly distributed.
 */
static Coin flip()
{
	return RANDOM.nextBoolean() ? HEADS : TAILS;
}
}