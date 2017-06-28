
package edu.frontrange.csc240.a4.guess;

import java.util.Random;
import java.util.Scanner;

import static edu.frontrange.csc240.a4.guess.Ordinal.ordinal;
import static java.lang.Math.log;
import static java.lang.System.out;

/**
 * The game of Guess is a game where the player guesses a number that has been
 * selected in advance (say, in the range 1 to 1000 inclusive, or any other pair of
 * numbers), and is told whether that guess is high or low. The player continues to
 * guess until the the correct number is found (or, in this version, is allowed to
 * resign).
 * <p>
 * The simplest strategy is to start at the lowest (or the highest) number, and just
 * guess all the possible numbers in sequence. For the range 1 to 1000, this could
 * take 1000 guesses. There are much better strategies. </p>
 * <p>
 * In this implementation of the game the computer plays the role of the selector of
 * the number, and the user (the "player") tries to find the number. </p>
 * <p>
 * At the end of the game, the player's strategy is evaluated, and is informed of
 * whether is was luck, strategy, or whether the strategy can be improved. </p>
 *
 * @author		Dr. Bruce K. Haddon, Instructor
 * @version		1.2, 2016-08-22, CSC-240, Assignment 4, Exercise 6.31
 */
public class Guess
{
/**
 * The random number generator to be used to "think" of an answer.
 */
private static final Random RANDOM = new Random();

/**
 * The input class to get values from the user.
 */
private final Scanner input;

/**
 * The lower limit of the game (included in the game).
 */
private final int lowerLimit;

/**
 * The upper limit of the game (also included in the game).
 */
private final int upperLimit;


/**
 * Constructor for the each game to be played.
 *
 * @param input			source of input from the user
 * @param lowerLimit	the lower limit for the answer (inclusive)
 * @param upperLimit	the upper limit for the answer (inclusive)
 */
public Guess(Scanner input, int lowerLimit, int upperLimit)
{
	/* Remember where to get the input. */
	this.input = input;

	/* Check that the limits make sense. */
	if( lowerLimit <= 0 || lowerLimit > upperLimit )
		throw new IllegalStateException();

	/* Remember them. */
	this.lowerLimit = lowerLimit;
	this.upperLimit = upperLimit;
}

/**
 * Plays the guessing game where the user tries to input the number that is
 * being held by the class.
 */
public void play()
{
	/* Variables used to implement a debugging Easter egg. */
	boolean auto = false;		// play game automatically
	boolean answer = false;		// show the answer
	boolean hint = false;		// give a hint for the next guess

	/* "Think" of a number between the lower limit (included) and the upper
	   limit (also included). */
	int thinking = RANDOM.nextInt(upperLimit + 1 - lowerLimit) + lowerLimit;

	int guessCount = 0;					// tracks total number of user guesses
	int lastLowGuess = lowerLimit - 1;	// track guesses for helping the player
	int lastHighGuess = upperLimit + 1;	// track guesses for helping the player

	/* Keep playing until the secret number has been found, or the user opts to
	   quit. */
	while( true )						// repetition environment
	{
		/* Additional code for the auto, hint and answer Easter eggs. This in
		   not part of the assignment: it just helps debugging. */
		if( answer ) out.printf("Answer is %d; ", thinking);
		int suggested =(lastHighGuess + lastLowGuess) / 2;
		if( hint ) out.printf("Suggested guess = %d; ", suggested);
		if( answer | hint ) out.println();

		/* Prompt the player to input the guess. The player is helped by being
		   reminded of the best lower and upper guesses so far in the game.  */
		out.printf("Guess a number from %d to %d (0 to resign): ",
				lastLowGuess + 1, lastHighGuess - 1);
										// tell the player limits of the guess

		String playerInput;
		/* Auto is used to get through a game quickly, and thus to debug the
		   messages given at the end. */
		if( auto )
			out.println((playerInput = Integer.toString(suggested)));
		else
			playerInput = input.next();	// get the player's input

		/* Code to support the debugging Easter egg. */
		while( playerInput.startsWith("+") )
		{
			playerInput = playerInput.substring(1);	// discard the "+" sign
			auto = answer;			// auto play if three "+" signs have been seen,
			answer = hint;			// show answer if there two "+" signs seen,
			hint = true;			// hint on guess if any input starts with "+"
		}

		int guess = Integer.parseInt(playerInput);

		/* The player has been given the opportunity to resign. */
		if( guess == 0 )				// if zero, the player resigns
		{
			out.println("The answer was " + thinking + ". " +
							"Thanks for trying!");
			break;
		}

		/* Help the player if the input is outside the known limits.  */
		if( guess <= lastLowGuess || guess >= lastHighGuess )
		{
			out.println("Your guess should be between the limits");
			out.println("This guess will not count");
			continue;
		}

		/* The guess is acceptable: tell the player whether it is good or
		   not. */
		++guessCount;					// increment count of player guesses
		String ordinal = ordinal(guessCount); // get count as an ordinal

		if( guess < thinking )
		{
			/* The guess was too low. */
			out.println("Your " + ordinal + " guess is too low. Try again.");
			lastLowGuess = guess;
		} else if( guess > thinking )
		{
			/* The guess was too high. */
			out.println("Your " + ordinal + " guess is too high. Try again.");
			lastHighGuess = guess;
		} else
		{
			/* The player has guessed the "secret" number. */
			out.println("Congratulations. You guessed the number on your " +
					ordinal + " guess!");

			/* Compute how many guesses it should take with these limits. and tell
			   the player that number. */
			int bestGuess = (int) (log(upperLimit - lowerLimit + 1) / log(2.0)) + 1;
			out.println("Expected best count of guesses = " + bestGuess);

			/* Give an evaluation of how good was the performance. */
			if( guessCount < bestGuess )
				out.println("Either you know the secret or you got lucky!");
			else if( guessCount == bestGuess )
				out.println("Aha! You know the secret!");
			else
				out.println("You should be able to do better!");

			/* The player is finished this game--exit the repetition environment. */
			break;
		}
	}
}
}
