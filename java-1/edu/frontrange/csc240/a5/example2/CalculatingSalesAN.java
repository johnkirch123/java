
package edu.frontrange.csc240.a5.example2;

/* Although this program satisfies the requirements of the exercise from the
   textbook, it must be kept in mind that for a real solution to this kind of
   application, there is almost nothing in this program that would be actually be
   the correct way to solve such a problem. Hopefully, as time goes on, you will see
   better ways to solve each of the challenges posed in the creation of a such a
   program that would be part of a real-life online system. */

import edu.frontrange.csc240.util.Debug;
import java.util.Scanner;

import static java.lang.Integer.max;
import static java.lang.Integer.min;
import static java.lang.System.exit;
import static java.lang.System.in;
import static java.lang.System.out;

/**
 * Prints the totals for a simulated online order of products.
 * <p>
 * This version of the Assignment 3 exercise of calcualting sales uses arrays, and
 * in particular, an enum class to fulfill the role of a "database" of products.
 * This class also uses arrays for keeping track of products and product numbers.
 *
 * @author	Dr. Bruce K. Haddon, Instructor
 * @version	2016-08-22, CSC-240 Assignment 3, Exercise 5.17
 */
public class CalculatingSalesAN
{
/**
 * Maximum number of products per order (prevents the output invoice from losing its
 * formatting.
 */
private static final int MAXIMUM_NUMBER_OF_PRODUCTS = 100_000;

/**
 * Print format for printing a line of product ordered.
 */
private static final String PRODUCT_LINE =
		"%12s |%7d |%7.2f  |%11.2f  |%n";

/**
 * Print format for printing the line for the total order amount.
 */
private static final String TOTAL_LINE =
		"                Total for Order |%11.2f  | %n";

/**
 * Print format for the rule line for printing the invoice.
 */
private static final String RULE_LINE =
		"----------------------------------------------|%n";

/**
 * Print format for the headings used for the invoice.
 */
private static final String HEAD_LINE =
		"     Item    |Quantity|  Price  |  Extension  |%n";

/**
 * Main entry point.
 * <p>
 * Execute:
 * <pre>java edu.frontrange.csc240.a5.example2.OnlineOrder</pre>
 *
 * @param args	unused.
 */
public static void main(String... args)
{
	new CalculatingSalesAN().run();
}

/**
 * Perform the tasks of the OnlineOrder class
 */
public void run()
{
	Debug.setDebug(false);

	/* Array to hold the quantities ordered of the products.
	   Initialize the array, thus setting ordered amounts to 0. */
	int[] ordered = new int[Product.getMaxProductNumber() + 1];

	int totalquantity = 0;			//	total number of items: used to detect
									//	whether any items were ordered at all

	int productId;					// variable to hold the productId input by user

	/* Create the Scanner to read from the standard input. */
	try( Scanner input = new Scanner(in) )
	{
		/* Ask user for product number until sentinel value entered. Remember how
		   much of each product is needed. */
		while( true )					// repetition environment
		{
			/*  Determine the product chosen. */
			out.printf("Enter a product number from %d to %d (or 0 to stop): ",
					Product.getMinProductNumber(), Product.getMaxProductNumber());
			productId = input.nextInt();

			if( productId == 0 ) break;	// 0 is sentinel value

			Product requested = Product.verifyProductNumber(productId);
			if( requested == null )
			{
				out.println("There is no product with that number.");
				out.println("Please try again.");
				continue;
			}

			/* Determine the quantity of the item (product) ordered. */
			int quantity;				//	quantity of that product desired
			while( true )
			{
				out.print("Enter quantity ordered: ");
				quantity = input.nextInt();
				if( quantity < 0 )
				{
					out.println("Cannot order quantity less than zero.");
					out.println("Please try again.");
					continue;
				}
				break;
			}

			/* Accumulate the total number of ordered items. */
			totalquantity += quantity;

			/* Add up the quantity ordered of this product. */
			int quantitySoFar = ordered[productId] += quantity;
			Debug.printf("%s, total ordered so far: %d%n",
										requested.name(), quantitySoFar);

			/* If the total number of items fo all products exceeds the given limit,
			   ask the user to start over. (Should not happen very often.)  */
			if( quantitySoFar > MAXIMUM_NUMBER_OF_PRODUCTS )
			{
				out.println("More than " + MAXIMUM_NUMBER_OF_PRODUCTS +
						" items of any item may not " +
						"be ordered. Please	restart your order.");
				exit(0);
			}
		}
	}

	/* Print a copy of the entire order. If nothing ordered, thank the the customer
	   for interest in the product list. */
	if( totalquantity == 0 )
	{
		out.println("Thank you for considering our products. Good bye!");
		exit(0);
	}

	out.println();
	out.printf(HEAD_LINE);
	out.printf(RULE_LINE);

	double totalOrder = 0.0;			//	total amount for the entire order
	for( int productNumber = 1; productNumber != ordered.length; ++productNumber )
	{									//	loop over the possibly ordered products

		int quantity = ordered[productNumber];
		if( quantity == 0 ) continue;	// this product not ordered

		Product product = Product.verifyProductNumber(productNumber);
		if( product == null ) continue;

		double price = product.getPrice(); //	price of the current product

		double extension = price * quantity;
		totalOrder += extension;
		out.printf(PRODUCT_LINE, product.name(), quantity, price, extension);
	}
	out.printf(RULE_LINE);
	out.printf(TOTAL_LINE, totalOrder);
}

/**
 * Enumeration class to define each product, its product number, and price.
 */
private enum Product
{
SOAP(1, 2.98),
SHAMPOO(2, 4.50),
LOTION(3, 9.98),
CONDITIONER(4, 4.49),
MOISTERISER(5, 6.87),
ASTRINGENT(10, 5.67);

private static Product[] available;

/**
 * The maximum product number used.
 */
private static int maxProductNumber = Integer.MIN_VALUE;

/**
 * The minimum product number used.
 */
private static int minProductNumber = Integer.MAX_VALUE;

/**
 * The price for this product.
 */
private final double price;

/**
 * The integer number for this product.
 */
private final int productNumber;

/**
 * Constructor:
 *
 * @param productNumber	the number used for this product
 * @param price				     price of this product
 */
private Product(int productNumber, double price)
{
	this.productNumber = productNumber;
	this.price = price;
}

/**
 * @return		the price for this product
 */
public double getPrice()
{
	return price;
}

/**
 * @return		the product number for this product
 */
public int getProductNumber()
{
	return productNumber;
}

/**
 * @return		the maxProductNumber
 */
@SuppressWarnings("AccessingNonPublicFieldOfAnotherObject")
public static int getMaxProductNumber()
{
	/* If the maxProductNumber has not already been found, find it. */
	if( maxProductNumber == Integer.MIN_VALUE )
		for( Product product : Product.values() )
			maxProductNumber = max(maxProductNumber, product.productNumber);
	return maxProductNumber;
}

/**
 * @return		the minProductNumber
 */
@SuppressWarnings("AccessingNonPublicFieldOfAnotherObject")
public static int getMinProductNumber()
{
	/* If the minProductNumber has not already been found, find it. */
	if( minProductNumber == Integer.MAX_VALUE )
		for( Product product : Product.values() )
			minProductNumber = min(minProductNumber, product.productNumber);
	return minProductNumber;
}

/**
 * @return the Product, if any, corresponding to this number.
 *
 * @param number	proposed product number
 * @return			the product if it exists, otherwise null
 */
@SuppressWarnings("AccessingNonPublicFieldOfAnotherObject")
public static Product verifyProductNumber(int number)
{
	/* If the list of available products has not yet been formed, create it. */
	if( available == null )
	{
		available = new Product[getMaxProductNumber() + 1];
		for( Product product : Product.values() )
			available[product.productNumber] = product;
	}
	/* This will return null if there is no entry in the array. */
	return number < 1 || number >= available.length ? null :  available[number];
}
}
}
