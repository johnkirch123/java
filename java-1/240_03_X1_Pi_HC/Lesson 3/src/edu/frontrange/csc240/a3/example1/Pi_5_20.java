
package edu.frontrange.csc240.a3.example1;

import edu.frontrange.csc240.util.Debug;
import java.util.Scanner;

import static java.lang.Math.PI;
import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.lang.System.in;
import static java.lang.System.out;

/**
 * Prints an estimate of the value of the constant pi obtained by a series expansion
 * to the number of terms given by the user.
 * <p>
 * This implementation illustrates a separation of concerns, by having the
 * interaction with the user in one method, and the computation of the needed value
 * in its own method. </p>
 * <p>
 * The series uses is based on the formula given in the text book in Exercise 5.20.
 * This series converges EXTREMELY slowly, so even allowing the number of terms to
 * be entered as a long value, with many billions or even trillions of terms, the
 * value of pi is still not correct to 15 decimal places. This is to illustrate
 * that not all series are suitable for computational use, even if mathematically
 * correct and accurate.
 *
 * @author		Dr. Bruce K. Haddon, Instructor
 * @version		2016-08-22, CSC-240 Assignment 3, Sample
 */
public class Pi_5_20
{
/**
 * Compute the value of <i>pi_estimate</i>, the ratio of the circumference of a
 * circle to its diameter, using the so-called Gregory series, as accurately as
 * possible to the given number of terms, which is assumed (precondition) to be
 * greater than or equal to 1.
 * <p>
 * Each term in the series is only a little smaller than the one before. But the
 * terms do get smaller, so there is no danger of overflow in the addition. However,
 * the value 2.0 * n is computed, which, for very large values of n, could overflow
 * and produce an incorrect result.
 * <p>
 * @param numberOfTerms	the number of terms of the Gregory series to be
 *                      used (greater than or equal to 1)
 * @return the the value of <i>pi_estimate</i>, computed as accurately as possible
 *						with the given number of terms
 */
public double pi_estimate(long numberOfTerms)
{
	double term = 4.0;				// the first (zeroth) term of the series
	double estimate = term;			// set estimate of pi_estimate to first term
	double previous = 0.0;			// previous value of the half_estimate

	 /* The variable n is the number of terms that have been computed (not the
	    number of the term). */
	long n = 1;

	/* Compute the given number of terms, or until the estimate does not improve. */
	Debug.printf("%12s\t%16s\t%16s%n", "Term No.", "Estimate", "Term Value");
	for( ; n != numberOfTerms && abs(previous - estimate) != 0.0; ++n )
	{
		if( n % max(numberOfTerms / 100, 1) == 0 )
			Debug.printf("%7d\t%.16g\t%.16g%n", n, estimate, term);
									// watch the estimate converge
		previous = estimate;		// remember previous value of the estimate

		term = ((n & 1) == 1 ? -1.0 : 1.0) // if n is odd, multiply by -1.0
						* 4.0 / (2.0 * n + 1.0); // determine the next term
		estimate += term;			// add the term to the current estimated value
	}
	Debug.printf("%7d\t%.16g\t%.16g%n", n, estimate, term);
	return estimate;
}

/**
 * Perform the tasks of the Pi class. <i>i.e.</i>, compute and estimate of the
 * value of the constant &pi; to the input number of terms, and print the
 * results.
 * <p>
 * The {@code run} method handles the details of the interaction with the user,
 * asking again for input if an unusable value is obtained. Once the number of
 * terms has been successfully obtained, this method delegates to the method that
 * actually performs the computation. The estimate obtained is printed, and a value
 * obtained from the JDK {@code Math} class is also printed for comparison.
 */
public void run()
{
	/* Create the Scanner to read from the standard input. The try-with-resources
	   statement will ensure the Scanner is closed when the loop terminates. */
	try( Scanner input = new Scanner(in) )
	{
		while( true )							// repetition environment
		{
			out.print("Enter a positive number of terms (or 0 to quit): ");
			long numberOfTerms = input.nextLong();// user input

			/* 0 is the sentinel value, so leave the loop which will allow the
			   program to terminate. */
			if( numberOfTerms == 0 ) break;

			if( numberOfTerms < 0 )
			{
				/* User entered a negative number; show the user a message, then
				   continue at the top of the loop. */
				out.println("That number is not positive; please try again.");
				continue;
			}

			/* Compute the estimate based on the number of terms. */
			double value = pi_estimate(numberOfTerms);

			/* Output the results from the estimation, as well as a comparison
			   value obtained from the {@code Math} class in the JDK. */
			out.printf("After %d terms estimate of pi is %.15g%n",
													numberOfTerms, value);
			out.printf("Best available estimate of pi is %.15g%n", PI);
		}
	}
}

/**
 * Main entry point.
 * <p>
 * Execute: </p>
 * <pre>java edu.frontrange.csc240.a3.example1.Pi_5_20</pre>
 *
 * @param args	not used.
 */
public static void main(String... args)	// See "FAQ What is the ... notation"
{										// for explanation.

	Debug.setDebug(true);
	new Pi_5_20().run();
}
}
