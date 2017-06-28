
package edu.frontrange.csc240.a3;

import edu.frontrange.csc240.util.Debug;
import java.util.Scanner;

import static java.lang.Double.isInfinite;
import static java.lang.Math.abs;
import static java.lang.Math.exp;
import static java.lang.System.in;
import static java.lang.System.out;

/**
 * Prints the value of the e to x expansion to a number of terms input by the
 * user, and an exponent input by the user.
 *
 * @author	Dr. Bruce K. Haddon, Instructor
 * @version	1.0, 2016-08-22, CSC-240 Assignment 3, Exercise 4.37 part(c)
 */
public class Ex
{
/**
 * Compute the value of <i>e</i>, the base of natural logarithms, raised to the
 * given exponent, using the Taylor expansion, as accurately as possible with
 * the given number of terms, which is assumed to be greater than or equal to 1.
 *
 * @param x			the double value to which the base value <i>e</i> is to
 *					be raised
 * @param terms		the number of terms of the Taylor expansion to be used
 *					(greater than or equal to 1).
 * @return			the the value of <i>e<sup>x</sup></i>, as accurately as possible
 *					with the given number of terms, or "infinity" if the computation
 *					cannot be completed without overflow (result becoming too large).
 */
public double exponential(double x, int terms)
{
	double term = 1.0;				// first term
	double estimate = term;			// set estimate of e^x to first term
	double previous = 0.0;			// there is no previous estimate yet
	int n = 1;						// n is the current number of terms computed

	Debug.printf("%7s\t%16s\t%16s%n", "Term No.", "Estimate", "Term Value");
	/* Compute the given number of terms, or until the estimate does not improve. */
	   for(; n != terms && abs(previous - estimate) != 0.0; ++n )
	{
		Debug.printf("%7d\t%.16g\t%.16g%n", n, estimate, term);

		previous = estimate;		// remember previous value of the estimate

		term *= x / n;				// compute next term as the previous term
									// multiplied by exponent and divided by n
		estimate += term;			// add new term to estimate

		if( isInfinite(estimate) ) return estimate;
									// series is not converging quickly enough
	}
	Debug.printf("%7d\t%.16g\t%.16g%n", n, estimate, term);
	return estimate;
}

/**
 * Perform the tasks of the Ex class
 */
public void run()
{
	/* This solution uses "contimue" and "break", which are introduced in the
	   next chapter of the text book. This solution illustrates some uses of
	   these two statements.

	   This solution also uses the technique of definining a method to
	   accomplish a specific part of the computation, a part that can exist by
	   itself to carry out a specific objective. The importance of this
	   decomposition will become more important as the Java language is
	   mastered. This also separates the logic of the interaction with the user
	   for the input of orrect values from the logic of the actual computation
	   to be made. */

	/* Create the Scanner to read from the standard input. This is using the
	   try-with-resources statement that was added to Java in the Java 7 release. It
	   ensures that if an exception is thrown by any of the statements enclosed in
	   the body of the try block, that the Scanner resource will be closed (see page
	   467 in Edition 10 of the textbook). */
	try( Scanner input = new Scanner(in) )
	{
		int numberOfTerms;
		while( true )							// repetition context
		{
			out.print("Enter a positive number of terms (or 0 to quit): ");
			numberOfTerms = input.nextInt();// user input: number of terms
			// to evaluate
			if( numberOfTerms < 0 )
			{
				out.println("That number is not positive; please try again.");
				continue;
			}

			if( numberOfTerms == 0 ) break;		// 0 is the sentinel value

			double exponent;
			while( true )
			{
				out.print("Enter an exponent (positive, zero or fractional): ");
				exponent = input.nextDouble();	// user input exponent
				if( exponent < 0.0 )
				{
					out.println(
							"That exponent is not positive; please try again.");
					continue;
				}
				break;
			}

			double value = exponential(exponent, numberOfTerms);

			/* An infinite value is returned to signal that the result become too
			   large to represent. Inform the user, and allow another attempt.  */
			if( isInfinite(value) )
			{
				out.println("Value of exponent is too large to be " +
				"able to compute that power of e; please try again.");
				continue;
			}

			out.printf("After %d terms estimate of e to the " +
					"power %g is %.15g%n", numberOfTerms, exponent, value);
			out.printf("Best available estimate of e to the " +
					"power %g is %.15g%n", exponent, exp(exponent));
		}
	}
}

/**
 * Main entry point.
 * <p>
 * Execute: </p>
 * <pre>java edu.frontrange.csc240.a3.Ex</pre>
 *
 * @param args	not used. (See "FAQ What is the ... notation" for explanation.)
 */
public static void main(String... args)
{
	Debug.setDebug(true);
	new Ex().run();
}

}
