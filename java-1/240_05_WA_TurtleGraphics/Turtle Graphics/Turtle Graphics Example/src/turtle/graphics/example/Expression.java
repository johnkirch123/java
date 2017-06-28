
package turtle.graphics.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringJoiner;
import java.util.TreeMap;
import java.util.regex.Pattern;

import static java.lang.System.out;

/**
 * The class can "compile" and then execute expressions. The expressions follow
 * the general rules for expressions in Java, but limited to integer values for
 * both literals and variables.
 * <p>
 * The operations supported are - (negation), +, - (addition and subtraction), and
 * *, / and %; (multiplication, division and remaindering). Parentheses may be
 * used to alter the usual priorities and to compute subexpressions.
 * <p>
 * An expression is compiled by passing access to the expression <em>via</em> a
 * {@code Scanner} instance to the method {@code enterExpression}, and, once
 * successfully compiled, may be then be executed as often as desired by calling the
 * method {@code executeExpression}. That method will ask the caller for values for
 * each of the variables that appear in the expression.
 *
 * @author	Dr. Bruce K. Haddon, Instructor
 * @version	1.1, 2016-08-22, CSC-240, Assignment 5, Example
 */
public class Expression
{
/**
 * Operator indications for additive operations.
 */
private static final String ADDITIVE_OPERATORS = "+-";

/**
 * The token indicating the end of an expression.
 */
private static final String END_OF_EXPRESSION_TOKEN = ";";

/**
 * Operator indications for monadic operations.
 */
private static final String MONADIC_OPERATORS = "+-";

/**
 * Operator indications for multiplicative operations.
 */
private static final String MULTIPLICATIVE_OPERATORS = "*/%";
	private static final String NEGATION_INDICATION = "-";

/**
 * A String containing a single space character.
 */
private static final String SPACE_STRING = " ";
/**
 * The current character being examined (held as a String of one character).
 */
private String character;

/**
 * True if an error is found during the source expression analysis.
 */
private boolean error;

/**
 * Accumulation of the expression so far analyzed.
 */
private StringJoiner expressionString;

/**
 * The (internal) instructions generated to evaluate the expression.
 */
private List<String> instructions;

/**
 * The pattern used by hasNext and next methods of Scanner, set to return one char.
 */
private Pattern pattern;

/**
 * The accumulation of messages to pass back to the caller (during the analysis).
 */
private List<String> result;

/**
 * Scanner being used to scan through the source expression.
 */
private Scanner source;

/**
 * Map showing correspondence between input values (as a String) and the actual
 * integer value.
 */
private Map<String, Integer> values;

/**
 * Map showing correspondence between variables in the expression (as a String) and
 * the actual integer value that has been input by the user.
 */
private Map<String, Integer> variables;

/**
 * Accepts a Java-like expression from a Scanner.
 * <p>
 * This method uses a number of additional (private) methods to implement what is
 * known as a recursive descent syntax analyzer. This number of additional methods
 * would not be needed for a simpler source language. The analysis generates a
 * returned list containing a copy of the input, as well as any error messages.
 * <p>
 * If the "compilation" succeeds, a (linear) List of instructions are created
 * so that the {@code executeExpression} method may easily and straightforwardly
 * compute the value of the expression (given the values of the variables).
 *
 * @param source	Scanner delivering Turtle commands
 * @return	List of messages about the compilation (not null)
 */
@SuppressWarnings("ReturnOfCollectionOrArrayField")
public List<String> enterExpression(Scanner source)
{
	/* Remember from whence the expression is coming, and set up the Scanner to be
	   able to deliver just one character at a time. */
	this.source = source;
	source.useDelimiter("");

	/* Initialize the data structures used during the analysis. */
	result = new ArrayList<>();					// messages for the user
	expressionString = new StringJoiner(SPACE_STRING);
												// accumulate a copy of the expression
	instructions = new ArrayList<>();			// the instructions for execution
	values = new HashMap<>();					// all values used (one copy each)
	variables = new TreeMap<>();				// the variables used

	pattern = Pattern.compile("(?s).");			// pattern to match just one char
	error = false;								// no errors seen yet

	/* If there is any input at all, skip any whitespace, and start the analysis
	   by calling the method additiveExpression. The expression must be followed
	   by a ";" to be properly terminated. */
	character = SPACE_STRING;
	if( source.hasNext(pattern) )
	{
		whitespace();
		additiveExpression();
		if( character.equals(END_OF_EXPRESSION_TOKEN) )
		{
			result.add(expressionString.toString() + END_OF_EXPRESSION_TOKEN);
			instructions.add("end");
			return result;
		}
	}
	/* There is no input, or the input expression does not end with a ";".
	   Create a message for the user, note that an error has occurred, and then
	   return to the user. */
	expressionString.add(character);
	result.add(expressionString.toString());
	result.add( character.trim().isEmpty() ?
			"Terminating \'" + END_OF_EXPRESSION_TOKEN +
												"\' missing from expression." :
			"Character \"" + character + "\" not recognized in expresssion.");
	error = true;
	return result;
}

/**
 * Execute the instructions that have been stored in the instruction list.
 *
 * @return	false if the expression cannot be executed, otherwise true
 */
public boolean executeExpression()
{
	/* Do not evaluate expression if there was an error in the expression. */
	if( error ) return false;

	/* Create a stack for doing the arithmetic. */
	Stack<Integer> stack = new Stack<>();

	/* Another (different) scanner to communicate with the user. */
	Scanner another = new Scanner(System.in);

	/* Get values for all the variables. */
	variables.keySet().stream().map((variable) ->
	{
		out.print("Please enter value for variable " + variable + ": ");
		return variable;
	}).	forEachOrdered((variable) ->
	{
		int value = another.nextInt();
		variables.put(variable, value);
	});

	/* Variables for the evaluation. */
	int v;
	int w;

	/* Index into the list of instructions. */
	int i = 0;

	/* While there are instuctions to execute ... */
	while( i != instructions.size() )
	{
		/* Branch on each of the defined operations. */
		switch( instructions.get(i) )
		{
		/* Addition. */
		case "+":
			v = stack.pop();
			w = stack.pop();
			stack.push(w + v);
			break;

		/* Subtraction. */
		case "-":
			v = stack.pop();
			w = stack.pop();
			stack.push(w - v);
			break;

		/* Multiplication. */
		case "*":
			v = stack.pop();
			w = stack.pop();
			stack.push(w * v);
			break;

		/* Division. */
		case "/":
			v = stack.pop();
			w = stack.pop();
			stack.push(w / v);
			break;

		/* Remainder. */
		case "%":
			v = stack.pop();
			w = stack.pop();
			stack.push(w % v);
			break;

		/* Unary negation. */
		case "negate":
			stack.push(-stack.pop());
			break;

		/* Literal integer value. */
		case "value":
			++i;
			stack.push(values.get(instructions.get(i)));
			break;

		/* Access the value of a variable. */
		case "variable":
			++i;
			stack.push(variables.get(instructions.get(i)));
			break;

		/* End of the expression: output the result. */
		case "end":
			System.out.println("The value computed is " + stack.pop());
			break;

		/* Should not, cannot happen. */
		default:
			throw new AssertionError("Cannot happen:" + instructions.get(i));
		}
		/* Step to the next instruction. */
		++i;
	}

	/* Done. */
	return true;
}

/**
 * The term "additiveExpression" is taken from the Java Language Specification. It
 * refers to an expression consisting of one subexpression, or two subexpressions
 * that are added to or subtracted from each other. Additive operations have a
 * lower priority than multiplicative operations.
 */
private void additiveExpression()
{
	/* An additive expression is a multiplicative expression, optionally followed by
	   an additive operator and then another multiplicative expression. */
	multiplicativeExpression();
	while( ADDITIVE_OPERATORS.contains(character) )
	{
		/* Remember the operation specified by the indication. */
		String operation = character;
		expressionString.add(operation);
		/* Move to the next significant character. */
		whitespace();
		/* Look for another multiplicative expression. */
		multiplicativeExpression();
		/* Add the operation to the instructions. */
		instructions.add(operation);
	}
}

/**
 * The term "multiplicativeExpression" is taken from the Java Language
 * Specification. It refers to an expression consisting of one unary subexpression,
 * or two subexpressions that are multiplied or divided by each other, or are formed
 * by a remaindering operation. Multiplicative operations have a higher priority
 * than additive expressions, but a lower priority than monadic operations.
 */
private void multiplicativeExpression()
{
	/* A multiplicative expression is a unary expression, optionally followed by
	   a multiplicative operator and another unary expression. */
	unaryExpression();
	while( MULTIPLICATIVE_OPERATORS.contains(character) )
	{
		/* Remember the operation specified by the indication. */
		String operation = character;
		expressionString.add(operation);
		/* Move to the next significant character. */
		whitespace();
		/* Look for another monadic expression. */
		unaryExpression();
		/* Add the operation to the instructions. */
		instructions.add(operation);
	}
}

/**
 * The term "primaryExpression" is taken from the Java Language Specification. It
 * refers to an expression that relates to an actual value or variable, or a
 * subexpression contained within parenthesis. Actual values or parenthetical
 * expressions must be evaluated before any other operation is allowed to proceed
 * at a previous level. Thus primary expressions have the highest priority.
 */
private void primaryExpression()
{
	/* Place to accumulate a value or variable if needed. */
	StringBuilder item = new StringBuilder();

	/* Get the actual character from the "character" String. */
	char c = character.charAt(0);
	if( Character.isDigit(c) )
	{
		/* A digit indicates the start of an integer value. */
		do
		{
			/* Accumulate the digits until something that is not a digit is seen. */
			item.append(c);
			character = SPACE_STRING;
			if( source.hasNext(pattern) )
			{
				character = source.next(pattern);
				c = character.charAt(0);
			} else break;
		} while( Character.isDigit(c) );
		/* Add the accumulated digits to the expressionString that is being built. */
		expressionString.add(item.toString());
		/* If the scan ended on a whitespace character, skip it any any more. */
		if( Character.isWhitespace(c) ) whitespace();
		/* Check that the number accumulated is parseable. Can fail by being too
		   large. If OK, store the value accessed by its String representation. */
		try
		{
			int value = Integer.parseInt(item.toString());
			instructions.add("value");
			instructions.add(item.toString());
			if( !values.containsKey(item.toString()) )
				values.put(item.toString(), value);
			return;
		} catch( NumberFormatException ex )
		{
			/* The parsing failed. Report the problem, and note the error. */
			result.add("Numeric expression \"" + item.toString() + "\" incorrect.");
			error = true;
			return;
		}
	} else if( Character.isJavaIdentifierStart(c) )
	{
		/* A character allowed as a Java identifier start is the start of the name
		   of a variable. */
		do
		{
			/* Accumulate the characters until something that is not permitted in
			   an identifier is seen. */
			item.append(c);
			character = SPACE_STRING;
			if( source.hasNext(pattern) )
			{
				character = source.next(pattern);
				c = character.charAt(0);
			} else break;
		} while( Character.isJavaIdentifierPart(c) );
		/* Add the accumulated chars to the expressionString that is being built. */
		expressionString.add(item.toString());
		/* If the scan ended on a whitespace character, skip it any any more. */
		if( Character.isWhitespace(c) ) whitespace();
		/* Store the variable accessed by its String representation. The value
		   will be requested when the evaluation is done. */
		instructions.add("variable");
		instructions.add(item.toString());
		if( !variables.containsKey(item.toString()) )
			variables.put(item.toString(), null);
		return;
	} else if( c == '(' )
	{
		/* An opening parenthesis indicates the start of a subexpression. In which
		   case, just call additiveExpression again to evaluate the
		   subexpression. */
		expressionString.add(character);
		whitespace();
		additiveExpression();
		/* The subexpression must end with a closing parenthesis. */
		c = character.charAt(0);
		if( c == ')' )
		{
			expressionString.add(character);
			whitespace();
			return;
		}
	}
	/* Any other alternative is an error. */
	expressionString.add(character);
	result.add("Error in expression");

}

/**
 * The term "unaryExpression" is taken from the Java Language Specification. It
 * refers to an expression consisting of one subexpression, preceded only by
 * a unary (monadic) operator (in this case, just a "+" or a "-" sign). The "+" sign
 * has no effect, and the minus sign only has an effect if there are an odd number.
 * Monadic operations have a higher priority than multiplicative operators, but a
 * lower priority than primary values.
 */
private void unaryExpression()
{
	boolean negate = false;
	/* Monadic operators may follow each other. */
	while( MONADIC_OPERATORS.contains(character) )
	{
		/* Record the indication. */
		expressionString.add(character);
		/* Only negation is significant. */
		if( character.equals(NEGATION_INDICATION) ) negate = !negate;
		/* Move to the next significant character. */
		whitespace();
	}
	/* Analyze the following primary expression. */
	primaryExpression();
	/* If negation is indicated, then add a negation operation. */
	if( negate ) instructions.add("negate");
}

/**
 * Move past any whitespace characters that are in the input source.
 */
private void whitespace()
{
	/* The current character has been used. */
	character = SPACE_STRING;
	/* Continue taking characters until one that is not whitespace is seen. */
	while( source.hasNext(pattern) )
	{
		character = source.next(pattern);
		if( !Character.isWhitespace(character.charAt(0)) ) break;
	}
}
}
