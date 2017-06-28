
package edu.frontrange.csc240.a3.example1;

import edu.frontrange.csc240.util.Debug;
import java.util.Scanner;

import static java.lang.Double.isInfinite;
import static java.lang.Math.abs;
import static java.lang.Math.cosh;
import static java.lang.System.in;
import static java.lang.System.out;

/**
 * Prints an estimate of the value of the hyperbolic cosine function (usually
 * written "cosh") using a series evaluation to the selected number of terms.
 * <p>
 * This implementation illustrates a separation of concerns, by having the
 * interaction with the user in one method, and the computation of the needed value
 * in its own method. </p>
 * <p>
 * It also illustrates the common technique in the evaluation of series of
 * computing the value of the next term in the series from the previous term. This
 * approach to evaluation minimizes the number of arithmetic operations, and also
 * by the order of arithmetic operations limits the magnitude of the intermediate
 * values needed in the computation.
 *
 * @author		Dr. Bruce K. Haddon, Instructor
 * @version		2016-08-22, CSC-240 Assignment 3, Sample
 */
public class HyperbolicCosine
{
/**
 * Compute the value of <i>cosh_estimate</i>, the value of the hyperbolic cosine
 * function, cosh(x), using the given number of terms of the series expansion.
 * <p>
 * Each term in the series can be calculated from the previous term by multiplying
 * by the square of <em>x</em> (the argument), that is, multiplying by <em>x</em>
 * twice, and dividing by the next two values that go to make up the factorial
 * divisor. That is: </p>
 * <blockquote>
 * <em>t<sub>n</sub> = t<sub>n-1</sub> * (x * x) / ((2*n  - 1) * 2*n)</em>
 * </blockquote>
 * <p>
 * It is not expected that <em>x<sup>2</sup></em> or that <em>2*n</em> will be
 * very large, but the magnitude of the values in the computation can be further
 * limited by expressing that equation in the form: </p>
 * <blockquote>
 * <em>t<sub>n</sub> = t<sub>n-1</sub> * (x / (n  - 0.5) / 2.0) * (x / n / 2.0)</em>
 * </blockquote>
 *
 * @param x			the argument
 * @param terms		the number of terms of the series expansion to be used
 *					(precondition: greater than or equal to 1)
 * @return			the the value of <i>cosh_estimate</i>, computed as accurately
 *					as possible with the given number of terms
 */
public double cosh_estimate(double x, int terms)
{
	double term = 1.0;				// the first (zeroth) term of the Taylor series
	double estimate = term;			// set estimate of cosh_estimate to first term
	double previous = 0.0;			// previous value of estimate (there is none)

	/* The variable n is the number of terms that have been computed (not the
	   number of the term). */
	int n = 1;

	/* Compute the given number of terms, or until the estimate does not improve. */
	Debug.printf("%7s\t%16s\t%16s%n", "Term No.", "Estimate", "Term Value");
	for( ; n != terms && abs(previous - estimate) != 0.0; ++n )
	{
		Debug.printf("%7d\t%.16g\t%.16g%n", n, estimate, term);
									// watch the estimate converge
		previous = estimate;		// remember previous value of the estimate

		term *= ( x / (n  - 0.5) / 2.0 ) * (x / n / 2.0);
									// Determine the next term from the value of
									// the previous term. Multiply by the value of
									// x sqaured and divide by 2*i and  2*i + 1,
									// but done so as to minimize the magnitude of
									// the intermediate values
		estimate +=  term;			// add the signed term to the estimated value

		if( isInfinite(estimate) ) return estimate;
									// series is not converging quickly enough
	}

	Debug.printf("%7d\t%.16g\t%.16g%n", n, estimate, term);
	return estimate;
}

/**
 * Perform the tasks of the HyperbolicCosine class. <i>i.e.</i>, to compute and
 * estimate the value of the cosh function, to the input number of terms from the
 * user, and print the results.
 * <p>
 * The {@code run} method handles the details of the interaction with the user,
 * asking again for input if an unusable value is obtained. Once the number of
 * terms has been successfully obtained, and a value for the argument, this method
 * delegates to the method that actually performs the computation. The estimate
 * obtained is printed, and a value obtained from the JDK {@code Math} class is
 * also printed for comparison.
 */
public void run()
{
	/* Create the Scanner to read from the standard input. The try-with-resources
	   statement will ensure the Scanner is closed when the loop terminates. */
	try( Scanner input = new Scanner(in) )
	{
		int numberOfTerms;
		while( true )							// repetition context
		{
			out.print("Enter a positive number of terms (or 0 to quit): ");
			numberOfTerms = input.nextInt();	// user input: number of terms
												// to evaluate

			if( numberOfTerms == 0 ) break;		// 0 is the sentinel value

			if( numberOfTerms < 0 )
			{
				out.println("That number is not positive; please try again.");
				continue;
			}

			double argument;
			while( true )
			{
				out.print("Enter an value of the argument: ");
				argument = input.nextDouble();	// user input of argument to cosh

//				This check is not being used, as in this application, negative
//				values of the argument are permitted.
//				if( argument < 0.0 )
//				{
//					out.println(
//							"That argument is not positive; please try again.");
//					continue;
//				}
				break;
			}

			/* Computer the estimate with the given argument and number of terms. */
			double value = cosh_estimate(argument, numberOfTerms);

			/* An infinite value is returned to signal that the result become too
			   large to represent. Inform the user, and allow another attempt.  */
			if( isInfinite(value) )
			{
				out.println("Value of argument is too large to be " +
				"able to compute that value of cosh; please try again.");
				continue;
			}

			/* Output the results from the estimation, as well as a comparison
			   value obtained from the {@code Math} class in the JDK. */
			out.printf("After %6d terms estimate of cosh(%g) " +
					"is %.15g%n", numberOfTerms, argument, value);
			out.printf("The best available estimate of cosh(%g) " +
					"is %.15g%n", argument, cosh(argument));
		}
	}
}

/**
 * Main entry point.
 * <p>
 * Execute: </p>
 * <pre>java edu.frontrange.csc240.a3.example1.HyperbolicCosine</pre>
 *
 * @param args	not used.
 */
public static void main(String... args) // See "FAQ What is the ... notation"
{										// for explanation.)
	Debug.setDebug(true);
	new HyperbolicCosine().run();
}
}