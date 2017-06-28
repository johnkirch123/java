
package edu.frontrange.csc240.a3;

import edu.frontrange.csc240.util.Debug;
import java.util.Scanner;

import static java.lang.Math.abs;
import static java.lang.System.in;
import static java.lang.System.out;

/**
 * Prints an estimate of the value of the constant e by evaluating the series
 * expansion to a number of terms given by the user.
 *
 * @author	Dr. Bruce K. Haddon, Instructor
 * @version	1.0, 2016-08-22, CSC-240 Assignment 3, Exercise 4.37 part(b)
 */
public class E
{
/**
 * Compute the value of <i>e_estimate</i>, the base of natural logarithms, using
 * the Taylor expansion, as accurately as possible to the given number of terms,
 * which is assumed (precondition) to be greater than or equal to 1.
 * <p>
 * @param terms		the number of terms of the Taylor expansion to be used
 *                  (greater than or equal to 1)
 * @return          the the value of <i>e_estimate</i>, as accurately as
 *					possible with the given number of terms
 */
public double e_estimate(int terms)
{
    double term = 1.0;				// the first term of the Taylor series
    double estimate = term;			// set estimate of e_estimate to first term
	double previous = 0.0;			// no previuss term yet
	int n = 1;						// n is the current number of terms computed

	Debug.printf("%7s\t%16s\t%16s%n", "Term No.", "Estimate", "Term Value");
	/* Compute the given number of terms, or until the estimate does not improve. */
    for(; n != terms && abs(previous - estimate) != 0.0; ++n )
    {
		Debug.printf("%7d\t%.16g\t%.16g%n", n, estimate, term);

        previous = estimate;		// remember previous value of the estimate

        term /= n;					// the value of the next term is the
									// previous term divided by the value of i
        estimate += term;			// add the next term to the estimated value
    }
	Debug.printf("%7d\t%.16g\t%.16g%n", n, estimate, term);
    return estimate;
}

/**
 * Perform the tasks of the E class. <i>i.e.</i>, compute and estimate of the
 * value of the constant e to the input number of terms.
 */
public void run()
{
    /* Create the Scanner to read from the standard input.  */
    try( Scanner input = new Scanner(in) )
    {
		int numberOfTerms;
        while( true )							// repetition environment
        {
            out.print("Enter a positive number of terms (or 0 to quit): ");
            numberOfTerms = input.nextInt();// user input

            if( numberOfTerms < 0 )
			{
				/* User entered a negative number; message, then continue at
				   the top of the loop. */
                out.println("That number is not positive; please try again.");
				continue;
			}

			/* 0 is the sentinel value, so leave the loop. */
			if( numberOfTerms ==  0 ) break;

			/* Compute the estimate. */
			double value = e_estimate(numberOfTerms);

			/* Output the results. */
			out.printf("After %2d terms estimate of e is %.15g%n",
					numberOfTerms, value);
			out.printf("Best available estimate of e is %.15g%n", Math.E);
        }
    }
}

/**
 * Main entry point.
 * <p>
 * Execute: </p>
 * <pre>java edu.frontrange.csc240.a3.E</pre>
 *
 * @param args	not used. (See "FAQ What is the ... notation" for explanation.)
 */
public static void main(String... args)
{
	Debug.setDebug(true);
    new E().run();
}

}
