
package edu.frontrange.csc240.a5.example1;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;

/**
 * Program to conduct some tests of the Expression class.
 *
 * @author	Dr. Bruce K. Haddon, Instructor
 * @version	1.1, 2016-08-22, CSC-240, Assignment 5, Example
 */
public class ExpressionTest
{
/**
 * Expressions to test the operation of the Expression class.
 */
private static final String[][] TEST_EXPRESSIONS =
	{
		{	"a + - b+  (c - def)  * 34  ;", "YES" },
		{	"a + - b+  (c - def)  * 34", "NO" },
		{	"x * x + y * y;", "YES" },
		{	"--+-25 + 1;", "NO" },
		{	"35;", "NO" },
		{	"3.5;", "NO" },
		{	"2147483647;", "NO" },
		{	"2147483648;", "NO" },
		{	"2147483648", "NO" }
	};

/**
 * Do the work for the testing.
 */
private void run()
{
	/* Create an instance of the class Expression. */
	Expression expressionObject = new Expression();

	for( String[] expression : TEST_EXPRESSIONS )
	{
		try( Scanner scanner = new Scanner(expression[0]) )
		{
			/* Pass the scanner with the expression to enterExpression. */
			List<String> report = expressionObject.enterExpression(scanner);
			/* Print out the report from the analysis. */
			out.println();
			report.forEach((String item) -> { out.println(item); });
		}

		/* Attempt the execution of the expression. */
		expressionObject.executeExpression();

		/* If the word "YES" is present, evaluate the expression a second time. */
		if( expression[1].equals("YES") ) expressionObject.executeExpression();
	}
}

/**
 * Main entry point.
 * <p>
 * Execute: </p>
 * <pre>java edu.frontrange.csc240.a5.example1.ExpressionTest</pre>
 *
 * @param args	not used (See "FAQ What is the ... notation" for explanation.)
 */
public static void main(String... args)
{
	new ExpressionTest().run();
}
}
