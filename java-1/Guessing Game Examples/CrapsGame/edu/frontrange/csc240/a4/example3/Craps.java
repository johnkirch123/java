
package edu.frontrange.csc240.a4.example3;

import java.util.Random;
import java.util.Scanner;

import static java.lang.System.out;

/**
 * This class definition is based on Figure 6.8: Craps.java (Craps class simulates
 * the dice game craps) of the Edition 10 textbook, as modified by Exercise 6.33.
 * <p>
 * It has been modified to make the flow of play more obvious, and to document
 * the code in a more usual Java style.
 *
 * @author		Deitel &amp; Associates (see copyright notice at end)
 * @author		Dr. Bruce K. Haddon, Instructor
 * @version		1.0, 2016-08-22, CSC-240 Assignment 4, Sample
 */
public class Craps
{
/**
 * Constant that represent a common roll of the dice (one dice 1, the other is 2)
 */
private static final int ACE_DEUCE = 3;

/**
 * Definition of "up big" for the bank balance.
 */
private static final double BALANCE_UP_BIG = 1.5;

/**
 * Constant that represent a common roll of the dice (both dice are 6)
 */
private static final int BOX_CARS = 12;

/**
 * Frequency to break in with discouraging chatter.
 */
private static final int PERCENT_DISCOURAGING_CHATTER = 20;

/**
 * Frequency to break in with encouraging chatter.
 */
private static final int PERCENT_ENCOURAGING_CHATTER = 30;

/**
 * Definition of per cent.
 */
private static final int PER_CENT = 100;

/**
 * Random number generator for use in method rollDice
 */
private static final Random RANDOM = new Random();

/**
 * Constant that represent a common roll of the dice (the sum of the dice is 7)
 */
private static final int SEVEN = 7;

/**
 * Constant that represent a common roll of the dice (each dice is one)
 */
private static final int SNAKE_EYES = 2;


/**
 * Constant that represent a common roll of the dice (one dice 5, the other is 6)
 */
private static final int YO_LEVEN = 11;

/**
 * The bank balance for the player of this game.
 */
private double bankBalance;

/**
 * The initial bank balance for the player of this game
 */
private final double initialBankBalance;

/**
 * The Scanner from which to accept input.
 */
private final Scanner input;

/**
 * Constructor.
 *
 * @param bankBalance	the initial bank balance for the player of this game
 * @param input			the Scanner from with to read user responses
 */
@SuppressWarnings("NestedAssignment")
public Craps(double bankBalance, Scanner input)
{
	this.bankBalance = this.initialBankBalance = bankBalance;
	this.input = input;
}

/**
 * Play one game of craps
 */
@SuppressWarnings("NestedAssignment")
public void play()
{
	out.println();	// Start of new game

	while( bankBalance > 0.0 )
	{
		Status gameStatus = Status.CONTINUE;	// The current state of the game
		double wager;
		while( true )
		{
			chatter1();
			out.printf("You have $%.2f: " +
								"how much do you wish to wager? ", bankBalance);
			wager = input.nextDouble();
			if( wager > bankBalance)
			{	out.println("You do not have that much available to wager!");
				continue;
			}
			if( wager < 0.0)
			{
				out.printf("You cannot bet with the house's money");
				continue;
			}
			if( wager == 0.0 )
			{
				out.printf("You retire with $%.2f " +
								"remaining. Good Luck!%n", bankBalance);
				return;
			}
			chatter2(wager);
			break;
		}

		/* Do the first roll, and determine win, lose or continuing. */
		int sumOfDice = rollDice();
		switch( sumOfDice )
		{
		case SEVEN:									// win with 7 on first roll
		case YO_LEVEN:								// win with 11 on first roll
			gameStatus = Status.WON;
			break;

		case SNAKE_EYES:							// lose with 2 on first roll
		case ACE_DEUCE:								// lose with 3 on first roll
		case BOX_CARS:								// lose with 12 on first roll
			gameStatus = Status.LOST;
			break;

		default:									// "on point"
			int myPoint = sumOfDice;				// point value
			System.out.printf("Point is %d%n", myPoint);

			/* Continue throwing to make the "point", or lose on a SEVEN. */
			while( gameStatus == Status.CONTINUE )	// not WON or LOST
			{
				sumOfDice = rollDice();				// roll dice again

				/* Determine  outcome of the later rolls. */
				if( sumOfDice == myPoint )			// win by making point
					gameStatus = Status.WON;
				else if( sumOfDice == SEVEN )		// lose by rolling 7 before point
					gameStatus = Status.LOST;
			}
		}
		/* Show the final outcome of this throw. */
		if( gameStatus == Status.WON )
		{
			System.out.println("Player wins");
			bankBalance += wager;
		}
		else
		{
			System.out.println("Player loses");
			bankBalance -= wager;
		}
	}
	out.println("Sorry, you are busted!");
}


/**
 * Encouraging chatter before making a wager.
 */
private void chatter1()
{
	String result = null;
	if( RANDOM.nextInt(PER_CENT) < PERCENT_ENCOURAGING_CHATTER)
		result = bankBalance > BALANCE_UP_BIG * initialBankBalance ?
				"You're up big. Now's the time to cash in your chips!" :
				"Aw, c'mon, take a chance!";
	if( result != null) out.println(result);
}

/**
 * Discouraging chatter after making a wager.
 *
 * @param wager		the wager that was made
 */
private void chatter2(double wager)
{
	String result = null;
	if( RANDOM.nextInt(PER_CENT) < PERCENT_DISCOURAGING_CHATTER)
		result = wager == bankBalance ?
				"Oh, you'r going for broke, huh?" : null;
	if( result != null) out.println(result);
}

/**
 * Simulate the rolls of two dice, and return the sum of the rolls. The outcome
 * of the rolls is reported on System.out.
 *
 * @return		the sum of two dice rolls
 */
public static int rollDice()
{
	/* The class Random is use to create the random outcomes.
	   The occurrence of "6" here is fixed by the geometry of a die. */
	int die1 = 1 + RANDOM.nextInt(6);			// first die roll
	int die2 = 1 + RANDOM.nextInt(6);			// second die roll

	int sum = die1 + die2; // sum of die values

	/* Display results of the rolls of two dice. */
	out.printf("  Player rolled %d + %d = %d%n", die1, die2, sum);

	return sum;
}

/**
 * Enum type with constants that represent the game status
 */
private enum Status
{
CONTINUE, WON, LOST;
}
}

/*-************************************************************************
 * (C) Copyright 1992-2014 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 **************************************************************************/

