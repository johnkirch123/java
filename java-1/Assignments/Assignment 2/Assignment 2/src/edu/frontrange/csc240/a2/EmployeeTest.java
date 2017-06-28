
package edu.frontrange.csc240.a2;

import java.lang.reflect.Field;

import static java.lang.Math.abs;
import static java.lang.System.out;

/**
 * Class to test some of the features of the class Employee.
 *
 * @author	Dr. Bruce K. Haddon, Instructor
 * @version	2016-08-22, CSC-240 Assignment 2, Exercise 3.13
 */
public class EmployeeTest
{
/**
 * The initial monthly salary for the first employee.
 */
private static final double EMPLOYEE1_MONTHLY_SALARY = 2985.00;

/**
 * The initial monthly salary for the second employee.
 */
private static final double EMPLOYEE2_MONTHLY_SALARY = 3361.75;

/**
 * The prefix for all error messages.
 */
private static final String ERROR = "    **?? Error: ";

/**
 * The prefix for all informative messages.
 */
private static final String MESSG = "    **";

/**
 * Count of error messages produced.
 */
private int errorCount;

/**
 * Check that the given employee has the given attributes. Used to check that
 * displayValues does not have unexpected side effects.
 * <p>
 * @param given			  the given Employee object (which is not null)
 * @param firstName	the expected value to be returned by getFirst
 * @param lastName		the expected value to be returned by getLast
 * @param salary		  the expected value to be returned by getMonthlySalary
 */
private void checkEmployee(Employee given, String firstName, String lastName,
		double salary)
{
	boolean OK = true;

	/* Accumulate the start of the toString value expected. */
	String str1 = given.getClass().getName();

	/* Identify what can be identified of the employee. */
	printfMessage("Checking the nominal employee ");
	out.print(firstName == null ? "<null>" :
							firstName.isEmpty() ? "<empty>" : firstName);
	out.print(" ");
	out.print(lastName == null ? "<null>" :
							lastName.isEmpty() ? "<empty>" : lastName);
	out.println();

	/* If both the actual and the expected first name are null, then OK,
	   otherwise if the expected value is null (and thus the actual found value
	   was not) report this, otherwise check to see if they are equal, and if
	   not, report that. */
	String firstFromObject = null;
	try
	{
		firstFromObject = this.<String>getField(given, "firstNameField");
	} catch( NoSuchFieldException | IllegalArgumentException |
			IllegalAccessException | SecurityException ex ) {}

	if( firstFromObject != null || firstName != null )
		if( firstName == null )
		{
			OK = false;
			printfError("First name expected to be null," +
					" found to be \"%s\"%n", firstFromObject);
		} else if( !firstName.equals(firstFromObject) )
		{
			OK = false;
			printfError("First name expected to be \"%s\"," +
					" found to be \"%s\"%n", firstName, firstFromObject);
		}

	String lastFromObject = null;
	try
	{
		lastFromObject = this.<String>getField(given, "lastNameField");
	} catch( NoSuchFieldException | IllegalArgumentException |
			IllegalAccessException | SecurityException ex )
	{
	}

	/* If both the actual and the expected last name are null, then OK,
	   otherwise if the expected value is null (and thus the actual found value
	   was not) report this, otherwise check to see if they are equal, and if
	   not, report that. */
	if( lastFromObject != null || lastName != null )
		if( lastName == null )
		{
			OK = false;
			printfError("Last name expected to be null," +
					" found to be \"%s\"%n", lastFromObject);
		} else if( !lastName.equals(lastFromObject) )
		{
			OK = false;
			printfError("Last name expected to be \"%s\"," +
					" found to be \"%s\"%n", lastName, lastFromObject);
		}

	/* Check to see if the salary value is close enough to what is expected If
	   not, report it. */
	double salaryFromObject = 0.0;
	try
	{
		salaryFromObject = this.<Double>getField(given, "salaryField");
	} catch( NoSuchFieldException | IllegalArgumentException |
			IllegalAccessException | SecurityException ex ) {}

	if( abs(salaryFromObject - salary) > 0.0001 )
	{
		OK = false;
		printfError("Monthly salary expected to be \"%.2f\"," +
				" found to be \"%.2f\"%n", salary, salaryFromObject);
	}

	str1 += "\u0040" + Integer.toHexString(System.identityHashCode(given));

	if( OK )
	{
		printfMessage("");
		out.print(firstName == null ? "<null>" :
				firstName.isEmpty() ? "<empty>" : firstName);
		out.print(" ");
		out.print(lastName == null ? "<null>" :
				lastName.isEmpty() ? "<empty>" : lastName);
		out.println(" is OK");
	}

	/* Check that displayValue gives an acceptable result. */
	printfMessage("The result of \"displayValues\" for that employee%n");
	given.displayValues();

	/* Print error only if the toString values to not match. */
	if( !(str1 + " " + firstName + " " + lastName).equals(given.toString()) )
		printfError("toString value \"" + given.toString() + "\" incorrect%n");
}

/**
 * Get the value of a field of the object without using the defined getter. Use
 * reflection to reach straight into the object.
 *
 * @param <T>		the type of the field
 * @param object	the object containing the field
 * @param name		the name of the field
 * @return			the value found
 * @throws NoSuchFieldException     if there is no such field
 * @throws IllegalArgumentException	should not happen
 * @throws IllegalAccessException   should not happen
 * @throws SecurityException        should not happen
 */
@SuppressWarnings("unchecked")
private <T> T getField(Object object, String name) throws NoSuchFieldException,
		IllegalArgumentException, IllegalAccessException
{
	/* Get the class object for the instance, and create the variable to hold
	   the result of accessing the field. */
	Class<?> clazz = object.getClass();
	T value = null;
	try
	{
		/* Get the field object for the declared field, make sure it is
		   accessible, and then grab the value of the field. */
		Field field = clazz.getDeclaredField(name);
		field.setAccessible(true);
		value = (T) field.get(object);
	} catch( IllegalArgumentException | IllegalAccessException |
			NoSuchFieldException | SecurityException ex )
	{
		/* The only exception expected to happen is that the field does not have
		   the correct name. */
		printfError("Cannot find field: " + ex.getMessage() + "%n");
		throw ex;
	}

	/* Return the value found. */
	return value;
}

/**
 * Do the work for the EmployeeTest.
 */
private void run()
{
	/* Create some empty strings which are not the constant "". */
	String emptyString1 = new String();
	String emptyString2 = new String();
	String emptyString3 = new String();

	/* Check the compiler has not done too much optimization. */
	assert !((Object) emptyString1 == (Object) emptyString2 ||
			(Object) emptyString1 == (Object) "" ||
			(Object) emptyString2 == (Object) emptyString3 ||
			(Object) emptyString2 == (Object) "" ||
			(Object) emptyString3 == (Object) emptyString1 ||
			(Object) emptyString3 == (Object) "") :
			ERROR + "Empty test strings not unique!";

	/* This try block is intended to catch any exceptions that may occur. Of
	   course, there should not be any. */
	try
	{
		/* This is required to ensure that the output directed to the err file
		   appears in the correct sequence with the output directed to the out
		   file. Otherwise the two files are independent, and the output does
		   not necessarily appear in the correct time order. */
		System.setErr(out);

		/* Initialize the count of error messages. */
		errorCount = 0;

		printfMessage("CSC-240 Assignment 2%n");
		out.println();
		printfMessage(" --- TESTING CREATING VALID Employee OBJECTS ---%n");
		out.println();

		/* Instantiate two valid Employee objects.  */
		printfMessage("Creating two valid employees%n");
		Employee employee1 =
				new Employee("Bob", "Jones", EMPLOYEE1_MONTHLY_SALARY);
		checkEmployee(employee1, "Bob", "Jones", EMPLOYEE1_MONTHLY_SALARY);

		Employee employee2 =
				new Employee("Susan", "Bakker", EMPLOYEE2_MONTHLY_SALARY);
		checkEmployee(employee2, "Susan", "Bakker", EMPLOYEE2_MONTHLY_SALARY);

		printfMessage("Changing first name of Bob Jones to Robert%n");
		try
		{
			employee1.setFirst("Robert");
		} catch( NullPointerException ex )
		{
			printfError("Erroneous \"setFirst\": resulted in NullPointerException%n");
		}
		if( employee1.getFirst() == null ||
				!employee1.getFirst().equals("Robert") )
			printfError("Erroneous \"getFirst\": " +
					"did not result in returning expected value%n");
		checkEmployee(employee1, "Robert", "Jones", EMPLOYEE1_MONTHLY_SALARY);

		printfMessage("Changing last name of Susan Bakker to Baker%n");
		try
		{
			employee2.setLast("Baker");
		} catch( NullPointerException ex )
		{
			printfError("Erroneous \"setLast\": " +
											"resulted in NullPointerException%n");
		}
		if( employee2.getLast() == null ||
				!employee2.getLast().equals("Baker") )
			printfError("Erroneous \"getLast\": " +
						"did not result in returning expected value%n");
		checkEmployee(employee2, "Susan", "Baker", EMPLOYEE2_MONTHLY_SALARY);

		/* Increase employee salaries by 10%. */
		out.println();
		printfMessage(" --- INCREASING Employee SALARIES BY 10%% ---%n");
		out.println();

		/* Increase the salary of employee1 by 10%. */
		employee1.setMonthlySalary(employee1.getMonthlySalary() * 1.10);
		/* Check that the salary increase was correctly recorded. */
		checkEmployee(employee1, "Robert", "Jones",
				EMPLOYEE1_MONTHLY_SALARY * 1.10);

		/* Increase the salary of employee2 by 10%. */
		employee2.setMonthlySalary(employee2.getMonthlySalary() * 1.10);
		/* Check that the salary increase was correctly recorded. */
		checkEmployee(employee2, "Susan", "Baker",
				EMPLOYEE2_MONTHLY_SALARY * 1.10);

		/* Test that bad Employee values cannot be set. */
		out.println();
		printfMessage(" --- TESTING INVALID CALLS TO Employee METHODS ---%n");
		out.println();

		/* Try to set some non-acceptable values. */
		printfMessage("Setting last name of Robert Jones to \"\"%n");
		employee1.setLast(emptyString1);
		checkEmployee(employee1, "Robert", null, EMPLOYEE1_MONTHLY_SALARY * 1.10);
		if( employee1.getLast() != null )
			printfError("\"getLast\" did not return a null value%n");

		printfMessage("Setting first name of Robert Jones to null%n");
		try
		{
			employee1.setFirst(null);
		} catch( NullPointerException ex )
		{
			printfError("\"setFirst\" resulted in NullPointerException%n");
		}
		checkEmployee(employee1, null, null, EMPLOYEE1_MONTHLY_SALARY * 1.10);
		if( employee1.getFirst() != null )
			printfError("\"getFirst\" did not return a null value%n");

		printfMessage("Setting first name of Susan Baker to \"\"%n");
		employee2.setFirst(emptyString2);
		checkEmployee(employee2, null, "Baker", EMPLOYEE2_MONTHLY_SALARY * 1.10);
		if( employee2.getFirst() != null )
			printfError("\"getFirst\" did not return a null value%n");

		printfMessage("Setting last name of Susan Baker to null%n");
		try
		{
			employee2.setLast(null);
		} catch( NullPointerException npe )
		{
			printfError("\"setLast\" resulted in NullPointerException%n");
		}
		checkEmployee(employee2, null, null, EMPLOYEE2_MONTHLY_SALARY * 1.10);
		if( employee2.getLast() != null )
			printfError("\"getLast\" did not return a null value%n");

		printfMessage("Setting salary of Susan Baker to -1000.0");
		employee2.setMonthlySalary(-1000.0);
		checkEmployee(employee2, null, null, 0.0);
		if( employee2.getMonthlySalary() != 0.0 )
			printfMessage("\"getMonthlySalary\" did not return a zero value%n");

		/* Try to create Employee objects with non-acceptable initial values. */
		printfMessage("Creating \"\" Smith, with salary of -500.0%n");
		Employee employee3 = new Employee(emptyString3, "Smith", -500.0);
		checkEmployee(employee3, null, "Smith", 0.0);
		if( employee3.getFirst() != null )
			printfError("After construction: \"getFirst\" " +
											"did not return a null value&n");
		if( employee3.getMonthlySalary() != 0.0 )
			printfError("After construction: \"getMonthlySalary\" " +
										"did not result in zero value%n");

		printfMessage("Creating <null> Baker%n");
		Employee employee4 = null;
		try
		{
			employee4 = new Employee(null, "Baker", 500.0);
		} catch( NullPointerException ex )
		{
			printfError("in constructor: null " +
						"\"first name\" resulted in NullPointerException%n");
		}
		if( employee4 != null )
		{
			checkEmployee(employee4, null, "Baker", 500.0);
			if( employee4.getFirst() != null )
				printfError("After construction: \"getFirst\" " +
						"did not return a null value%n");
			if( employee4.getMonthlySalary() != 500.0 )
				printfError("After construction: \"getMonthlySalary\"  " +
						"did not result in the value set%n");
		}
	} catch( Throwable th )
	{
		/* The application under test should not throw any Exceptions or Errors.
		   The use of Exceptions will not be studied until a later lesson, but
		   they are nevertheless an inescapable part of the Java programming
		   system. */
		printfError("An exception or error has been thrown%n");
		printfError("The exception is: " + th.toString() + "%n");
		printfError("The source of the error can be found " +
													"in this stacktrace:%n");
		th.printStackTrace(out);
	}

	if( errorCount != 0 )
		printfError(errorCount + " error" +
					(errorCount == 1 ? "" : "s") + " reported ??**%n");
}

/**
 * Print a formatted message with an error marker.
 *
 * @param messageFormat	message in format form
 * @param values		substitution values
 */
private void printfError(String messageFormat, Object... values)
{
	++errorCount;
	out.printf(ERROR + messageFormat, values);
}

/**
 * Print a formatted message with an message marker.
 *
 * @param messageFormat	message in format form
 * @param values		substitution values
 */
private void printfMessage(String messageFormat, Object... values)
{
	out.printf(MESSG + messageFormat, values);
}

/**
 * Main entry point.
 * <p>
 * Execute:</p>
 * <pre>java edu.frontrange.csc240.a2.EmployeeTest</pre>
 *
 * @param args	not used. (See "FAQ What is the ... notation" for explanation.)
 */
public static void main(String... args)
{
	new EmployeeTest().run();		// Run the test
}
}
