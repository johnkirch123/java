
package edu.frontrange.csc240.a3;

/* Although this program satisfies the requirements of the exercise from the
   textbook, it must be kept in mind that for a real solution to this kind of
   application, there is almost nothing in this program that would be actually
   be the correct way to solve such a problem. Hopefully, as time goes on, you
   will see better ways to solve each of the challenges posed in the creation of
   a such a program that would be part of a real-life online system. */

import edu.frontrange.csc240.util.Debug;
import java.util.Scanner;

import static java.lang.System.exit;
import static java.lang.System.in;
import static java.lang.System.out;

/**
 * Prints the totals for a simulated online order of products.
 *
 * @author	Dr. Bruce K. Haddon, Instructor
 * @version	1.0, 2016-08-22, CSC-240 Assignment 3, Exercise 5.17
 */
public class CalculatingSales
{
/**
 * Print format for the headings used for the invoice.
 */
private static final String HEAD_LINE =
		"      Item   |Quantity|  Price  |  Extension  |%n";

/**
 * Maximum number of products per order (prevents the output invoice from losing
 * its formatting.
 */
private static final int MAXIMUM_NUMBER_OF_PRODUCTS = 100_000;

/**
 * The number of products in the list.
 */
private static final int MAXIMUM_PRODUCT_NUMBER = 5;

/**
 * Prices for each kind of product. These are set as constants.
 */
private static final double PRICE1 = 2.98;		// price of first product

private static final double PRICE2 = 4.50;		// price of second product

private static final double PRICE3 = 9.98;		// price of third product

private static final double PRICE4 = 4.49;		// price of fourth product

private static final double PRICE5 = 6.87;		// price of fifth product

private static final String PRODUCT1 = "SOAP";

private static final String PRODUCT2 = "SHAMPOO";

private static final String PRODUCT3 = "LOTION";

private static final String PRODUCT4 = "CONDITIONER";

private static final String PRODUCT5 = "MOISTERISER";

/**
 * Print format for printing a line of product ordered.
 */
private static final String PRODUCT_LINE = "%12s |%7d |%7.2f  |%11.2f  |%n";

/**
 * Print format for the rule line for printing the invoice.
 */
private static final String RULE_LINE =
		"----------------------------------------------|%n";

/**
 * Print format for printing the line for the total order amount.
 */
private static final String TOTAL_LINE =
		"                Total for Order |%11.2f  | %n";

/**
 * Perform the tasks of the OnlineOrder class
 */
@SuppressWarnings("NestedAssignment")
public void run()
{
	int quantity1 = 0;				//	amount ordered of first product
	int quantity2 = 0;				//	amount ordered of second product
	int quantity3 = 0;				//	amount ordered of third product
	int quantity4 = 0;				//	amount ordered of fourth product
	int quantity5 = 0;				//	amount ordered of fifth product

	int totalquantity = 0;			//	total number of items: used to detect
									//	whether any items were ordered at all

	int productId;					// variable to hold the productId

	/* Create the Scanner to read from the standard input. */
	try( Scanner input = new Scanner(in) )
	{
		/* Ask user for product number until sentinel value entered. Remember
		   how much of each product is needed. */
		while( true )					// repetition environment
		{
			// determine the product chosen
			out.print("Enter a product number from 1 to " +
					MAXIMUM_PRODUCT_NUMBER + " (or 0 to stop): ");
			productId = input.nextInt();
			Debug.println("Product number requested = " + productId);

			/* The zero product number is the sentinel value, so stop requesting. */
			if( productId == 0 ) break;

			/* Determine the quantity of the item (product) ordered. */
			int quantity;				// quantity of that product desired
			while( true )				// loop until satisfactory answer
			{
				out.print("Enter quantity ordered: ");
				quantity = input.nextInt();
				if( quantity >= 0 ) break;

				out.println("Cannot order quantity less than zero.");
				out.println("Please try again.");
			}
			Debug.println("Quantity requested = " + quantity);
			/* Ignore orders for zero quantity. */
			if( quantity == 0 ) continue;

			/* Accumulate the total number of ordered items. */
			totalquantity += quantity;

			/* Increment the total for the particular item (by productId). */
			int quantitySoFar;
			switch( productId )
			{
			case 1:
				quantitySoFar = quantity1 += quantity;
				break;

			case 2:
				quantitySoFar = quantity2 += quantity;
				break;

			case 3:
				quantitySoFar = quantity3 += quantity;
				break;

			case 4:
				quantitySoFar = quantity4 += quantity;
				break;

			case 5:
				quantitySoFar = quantity5 += quantity;
				break;

			default:
				out.println("No product available with that " +
													"Id. Please continue.");
				continue;
			}

			Debug.printf("Product%d, total ordered so far: %d%n",
					productId, quantitySoFar);

			/* If the total number of items for any one product exceeds the
			   given limit, ask the user to start over. (Should not happen very
			   often. */
			if( quantitySoFar > MAXIMUM_NUMBER_OF_PRODUCTS )
			{
				out.println("More than " + MAXIMUM_NUMBER_OF_PRODUCTS +
						" items of any item may not " +
						"be ordered. Please	restart your order.");
				exit(0);
			}
		}
	}

	/* Print a copy of the entire order. If nothing ordered, thank the the
	   customer for interest in the product list. */
	if( totalquantity == 0 )
	{
		out.println("Thank you for considering our products. Good bye!");
		exit(0);
	}

	/* Start the printing of the invoice. */
	out.println();
	out.printf(HEAD_LINE);
	out.printf(RULE_LINE);

	double totalOrder = 0.0;		//	total amount for the entire order
	for( int i = 1; i != MAXIMUM_PRODUCT_NUMBER + 1; ++i )
	{								//	loop over all the products
		double price;				//	price of the current product

		String name;

		productId = i;				//	current productId of product
		int quantity;				//	quantity of current product

		switch( productId )			//	select quantity and price for each item
		{
		case 1:
			quantity = quantity1;
			price = PRICE1;
			name = PRODUCT1;
			break;

		case 2:
			quantity = quantity2;
			price = PRICE2;
			name = PRODUCT2;
			break;

		case 3:
			quantity = quantity3;
			price = PRICE3;
			name = PRODUCT3;
			break;

		case 4:
			quantity = quantity4;
			price = PRICE4;
			name = PRODUCT4;
			break;

		case 5:
			quantity = quantity5;
			price = PRICE5;
			name = PRODUCT5;
			break;

		default:
			quantity = 0;
			price = 0.0;
			name = null;
			break;
		}

		if( quantity != 0 )			// do not create line for an unordered item
		{
			double extension = price * quantity;
			totalOrder += extension;
			out.printf(PRODUCT_LINE, name, quantity, price, extension);
		}
	}
	/* Finish printing the invoice. */
	out.printf(RULE_LINE);
	out.printf(TOTAL_LINE, totalOrder);
}

/**
 * Main entry point.
 * <p>
 * Execute: </p>
 * <pre>java edu.frontrange.csc240.a3.CalculatingSales</pre>
 *
 * @param args	not used. (See "FAQ What is the ... notation" for explanation.)
 */
public static void main(String... args)
{
	Debug.setDebug(false);
	new CalculatingSales().run();
}
}
