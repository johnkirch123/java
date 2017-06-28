
package edu.frontrange.csc240.a4.coin2;

import java.util.Random;

/**
 * The Coin class enumerates the sides of a coin, naming them HEADS and TAILS.
 * It also provides a method to simulate the tossing of the coin.
 *
 * @author	Dr. Bruce K. Haddon, Instructor
 * @version	1.1, 2016-08-22, CSC-240, Assignment 4, Exercise 6.29 (variant 2)
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
 * Simulate the flip of a coin.
 *
 * @return	HEADS or TAILS, uniformly, but randomly distributed.
 */
public static Coin flip()
{
	return RANDOM.nextBoolean() ? HEADS : TAILS;
}
}
