package edu.frontrange.csc240.a6.test;

import edu.frontrange.csc240.a6.Date;
import edu.frontrange.csc240.a6.Degree;
import edu.frontrange.csc240.a6.Student;
import edu.frontrange.csc240.a6.Time2;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

import static java.lang.System.gc;
import static java.lang.System.out;
import static java.lang.System.runFinalization;

/**
 * Test program for the features of the college system which is the subject of
 * Assignment 6. This test program tests (very mildly) some of the features of
 * the Prefix, Course, and Section classes.
 *
 * @author	Dr. Bruce K. Haddon, Instructor
 * @version	2.0, 2016-08-22, CSC-240 Assignment 6, per the Assignment
 * Instructions
 */
@SuppressWarnings( { "UnusedAssignment",
								"NestedAssignment", "ProtectedInnerClass" } )
public class Assignment6Test
{
/**
 * The CSC-241 Advanced Java Course
 */
private static final String ADVANCED = "\u0032\u0034\u0031";

/**
 * Computer Informations Systems department/
 */
private static final String COMPUTER_INFORMATION_SYSTEMS = "\u0043\u0049\u0053";

/**
 * The Computer Networking department.
 */
private static final String COMPUTER_NETWORKING = "\u0043\u004E\u0047";

/**
 * The Computer Science department.
 */
private static final String COMPUTER_SCIENCE = "\u0043\u0053\u0043";

/**
 * The Computer Web-Based department.
 */
private static final String COMPUTER_WEB_BASED = "\u0043\u0057\u0042";

/**
 * Letters to use for student names.
 */
private static final String LETTERS = "bcdfghjklmnpqrstvwx";

/**
 * Generator for random numbers.
 */
private static final Random RANDOM = new Random(-1);

/**
 * Specific part of the package name to remove.
 */
private static final String SPECIFIC_PACKAGE = "test";

/**
 * The CSC-240 Java Course
 */
private static final String STANDARD = "\u0032\u0034\u0030";

/**
 * Count of error messages produced.
 */
private static int errorCount;

/**
 * The package for this assignment.
 */
private static String package_name;

//----------------------------- The actual tests -------------------------------
/**
 * Run the actual tests. The output is both compared visually to the expected
 * output, as well as the test checking anything it can.
 */
public void runTest()
{
	errorCount = 0;

	/* Deduce the name of the package containing the classes to be tested. */
	package_name = getClass().getCanonicalName();
	String simpleName = getClass().getSimpleName();
	if( package_name.endsWith(simpleName))
		package_name =
				package_name.substring(0, package_name.length() -
												simpleName.length() - 1);
	if( package_name.endsWith(SPECIFIC_PACKAGE))
		package_name =
				package_name.substring(0, package_name.length() -
												SPECIFIC_PACKAGE.length() - 1);

	/* Set up the exceptions that may be expected in some implementations. */
	Class<?> illegalStateException = null;
	Class<?> assertionError = null;
	Class<?> illegalArgumentException = null;
	Class<?> validationException = null;
	Class<?> courseException = null;
	Class<?> sectionException= null;
	/* If validationException is defined, then expect these exceptions as
	   well. */
	if( validationException != null )
	{
		illegalStateException = IllegalStateException.class;
		assertionError = AssertionError.class;
		illegalArgumentException = IllegalArgumentException.class;
	}

// --------------------- Test program for the assignment. ----------------------
	markline();
	message("Test Program for Assignment 6");
	markline();

// ----------------------- Test setting up the prefixes. -----------------------
	message("-- Check the Prefix Enumeration class --");
	Object coursePrefix = null;
	ArrayList<String> valueStrings = new ArrayList<>();

	/* Make a list of names of the expected Prefixes, and collect the
	   objects. */
	valueStrings.addAll(Arrays.asList(COMPUTER_INFORMATION_SYSTEMS,
			COMPUTER_SCIENCE, COMPUTER_NETWORKING, COMPUTER_WEB_BASED));
	Object[] objects = Methods.VALUES.objectArrayInvoke(null, null);
	if( objects != null )
		/* For each of the collected objects, check the name. If the name is
		   recognized, remove it from the list (keeping track of the computer
		   science prefix) and printing the actual title. */
		for( Object object : objects )
		{
			String stringName = Methods.NAME.stringInvoke(object, null);
			/* If the name is not recognized, inform the user. */
			if( !valueStrings.contains(stringName) )
				error("Prefix value " + stringName + " not recognized.");
			else
			{
				/* Otherwise, list the class and its name and the corresponding
				   title of the course. */
				if( stringName.equals(COMPUTER_SCIENCE) )
					coursePrefix = object;
				valueStrings.remove(stringName);
				message(stringName + " " +
								Methods.GET_TITLE.stringInvoke(object, null));
			}
		}
	/* Note any prefix values that were not defined. */
	valueStrings.forEach((remains) ->
			{	error("Prefix value " + remains + " not found."); } );
	line();

// -------------------------- Check the Course class ---------------------------
	message("-- Checking the Course class --");
	/* Prepare to create two correctly formed courses. */
	int credits;
	Object firstCourse = null;
	Object secondCourse = null;
	if( coursePrefix == null ) error("Prefix value CSC not defined");
	else
	{
		String name = "Java Programming";
		credits = 3;
		message("Create first course");
		firstCourse = Constructors.COURSE4.instance(null,
								coursePrefix, STANDARD, name, credits);
		String nameAsis = Fields.COURSE_NAME.stringAccess(firstCourse);
		if( !nameAsis.equals(name) )
			error("Name of course is incorrect: found \"" +
					nameAsis + "\", should be \"" + name + "\"");
		int creditsAsis = Fields.CREDITS.intAccess(firstCourse);
		if( creditsAsis != credits )
			error("Credits for course is incorrect: found \"" +
					creditsAsis + "\", should be \"" + credits + "\"");
		name = "Advanced Java Programming";
		credits = 0;
		message("Create second course");
		secondCourse = Constructors.COURSE3.instance(null,
								coursePrefix, ADVANCED, name);
		nameAsis = Fields.COURSE_NAME.stringAccess(secondCourse);
		if( !nameAsis.equals(name) )
			error("Name of course is incorrect: found \"" +
					nameAsis + "\", should be \"" + name + "\"");
		creditsAsis = Fields.CREDITS.intAccess(secondCourse);
		if( creditsAsis != credits )
			error("Credits for course is incorrect: found \"" +
					creditsAsis + "\", should be \"" + credits + "\"");
	}

	int courseCount = 0;
	message("Number of Course objects: " +
				(courseCount =
						Methods.GET_COURSE_COUNT.intInvoke(firstCourse, null)));
		if( courseCount != 2 )
			error("Number of Course objects is " + courseCount + "; should be 2");
	/* Check the details of the first course. */
	if( firstCourse != null )
	{
		message("First Course: ");
		message(Methods.COURSE_GET_DETAILS.stringInvoke(firstCourse, null));
	} else
		error("First course not constructed");

	/* Check the details of the second course. */
	if( secondCourse != null )
	{
		message("Second Course: ");
		message(Methods.COURSE_GET_DETAILS.stringInvoke(secondCourse, null));
	} else
		error("Second course not constructed");
	markline();

	message("-- Checking the set and get credits methods --");
	/* Check that setting credits works correctly. */
	if( Methods.GET_CREDITS.intInvoke(secondCourse, null) != 0 )
		error("Course not initializing credits correctly.");
	Methods.SET_CREDITS.voidInvoke(secondCourse, validationException, -10);
	if( Methods.GET_CREDITS.intInvoke(secondCourse, null) != 0 )
		error("Method not setting credits to zero correctly " +
				"with negative input.");

	credits = 3;
	Methods.SET_CREDITS.voidInvoke(secondCourse, null, credits);
	if( Methods.GET_CREDITS.intInvoke(secondCourse, null) != credits )
		error("Method not setting credits to " + credits + " correctly.");

	message("-- Checking the value returned by toString --");
	String result = Methods.TO_STRING.stringInvoke(firstCourse, null);
	String intended = coursePrefix + "\u002D" + STANDARD;
	if( !intended.equals(result) )
		error("Incorrect value \"" + result + "\" for Course toString");
	result = Methods.TO_STRING.stringInvoke(secondCourse, null);
	intended = coursePrefix + "\u002D" + ADVANCED;
	if( !intended.equals(result) )
		error("Incorrect value \"" + result + "\" for Course toString");
	line();

// -------------------------- Check the Section class --------------------------
	message("-- Checking the Section class --");
	/* Create a first section. */
	Object firstSection = Constructors.SECTION2.instance(
										null, firstCourse, "001");
	if( firstSection == null )
		error("First section not constructed.");
	else
	{
		/* The construction worked. */
		message("First section constructed.");
		Methods.SET_DATES.voidInvoke(firstSection, null,
				new Date(8, 21, 2007), new Date(12, 10, 2007));
		Methods.SET_TIMES.voidInvoke(firstSection, null,
				new Time2(10, 0, 0), new Time2(11, 15, 0));
		int studentCount = 5;
		/* Add some students to the section. */
		Student[] sectionStudents = getStudents(studentCount);
		checkStudents(sectionStudents, firstSection, "First");
	}

	/* Create a second section. */
	Object secondSection = Constructors.SECTION2.instance(
											null, firstCourse, "002");
	if( secondSection == null )
		error("Second section not constructed.");
	else
	{
		/* The construction worked. */
		message("Second section constructed.");
		Methods.SET_DATES.voidInvoke(secondSection, null,
				new Date(8, 21, 2007), new Date(12, 10, 2007));
		Methods.SET_TIMES.voidInvoke(secondSection, null,
				new Time2(10, 0, 0), new Time2(11, 15, 0));

		/* Add some students to the section. */
		Student[] sectionStudents =
		{
			studentGenerator(new Date(10, 12, 1985)),
			studentGenerator(new Date(11, 1, 1984)),
			studentGenerator(new Date(5, 24, 1980))
		};
		checkStudents(sectionStudents, secondSection, "Second");
	}

	/* Create a second section. */
	Object thirdSection = Constructors.SECTION2.instance(
										null, secondCourse, "001");
	if( thirdSection == null )
		error("Third section not constructed.");
	else
	{
		/* The construction worked. */
		message("Third section constructed.");
		Methods.SET_DATES.voidInvoke(thirdSection, null,
				new Date(8, 21, 2007), new Date(12, 10, 2007));
		Methods.SET_TIMES.voidInvoke(thirdSection, null,
				new Time2(10, 0, 0), new Time2(11, 15, 0));

		/* Add some students to the section. */
		Student[] sectionStudents =
		{
			studentGenerator(new Date(8, 13, 1982), Degree.AS),
			studentGenerator(new Date(5, 24, 1980), Degree.ASBUS)
		};
		checkStudents(sectionStudents, thirdSection, "Third");

		int sectionCount;
		message("Number of Section objects: " +
				(sectionCount = Methods.GET_SECTION_COUNT.intInvoke(thirdSection, null)));
		if( sectionCount != 3 )
			error("Number of Section objects is " + sectionCount +
					"; should be 3");
	}
	line();

	/* The fourth Section is created with an invalid section number. */
	String invalidSectionNumber = "987654";
	Object fourthSection = Constructors.SECTION2.instance(
							sectionException, secondCourse, invalidSectionNumber);
	if( fourthSection == null )
	{
		if( sectionException ==  null )
			error("Fourth section not constructed.");
	}
	else
	{
		/* The section was created; check that the invalid section number was
		   noticed. */
		message("Fourth section constructed with invalid section number.");
		if( !Methods.GET_SECTION_NUMBER.stringInvoke(fourthSection, null).isEmpty() )
			error("Section number for section should be empty.");
	}

	/* Do the fourth section again, this time with a valid section number. */
	fourthSection = Constructors.SECTION2.instance(null, secondCourse, "002");
	if( fourthSection == null )
		error("Fourth section not constructed.");
	else
	{
		/* The section was created; check that the valid section number was
		   noticed. */
		message("Fourth section constructed with valid section number.");
		if( Methods.GET_SECTION_NUMBER.stringInvoke(fourthSection, null) == null )
			error("Section number for section should not be \"null\".");
		Methods.SET_DATES.voidInvoke(fourthSection, null,
				new Date(8, 21, 2007), new Date(12, 10, 2007));
		Methods.SET_TIMES.voidInvoke(fourthSection, null,
				new Time2(10, 0, 0), new Time2(11, 15, 0));

// ------------------ Check on adding students to a section. -------------------
		int studentCount = 30;
		Student[] sectionStudents = getStudents(studentCount);
		checkStudents(sectionStudents, fourthSection, "Fourth");
		/* Attempt to add 10 more students. */
		for( Student student : getStudents(10) )
			Methods.ADD_STUDENT.voidInvoke(fourthSection,
								illegalStateException, student);
		int getCount = Methods.GET_ROSTER_COUNT.intInvoke(fourthSection, null);
		if( getCount != sectionStudents.length )
			error("Fourth" + " section student count " + getCount +
					" incorrect; " + "should be " + sectionStudents.length);
		/* Add a null student. */
		message("Adding a null student to the roster.");
		Methods.ADD_STUDENT.voidInvoke(fourthSection,
							illegalArgumentException, new Object[1]);
	}
	markline();
// ---------------------- Check setting dates and times. ----------------------
	message("-- Testing setting dates and times --");
	/* Check the assertions on the setDates and setTimes methods. */

	message("Set date with null first date");
	Methods.SET_DATES.voidInvoke(fourthSection, assertionError,
			null, new Date(12, 10, 2007));
	message("Set date with null second date");
	Methods.SET_DATES.voidInvoke(fourthSection, assertionError,
			new Date(8, 21, 2007), null);
	message("Set time with null first time");
	Methods.SET_TIMES.voidInvoke(thirdSection, assertionError,
			null, new Time2(11, 15, 0));
	message("Set time with null second time");
	Methods.SET_TIMES.voidInvoke(thirdSection, assertionError,
			new Time2(10, 0, 0), null);
	markline();

// ------------- Check garbage collection and remaining instances --------------

	/* The code below uses System.gc() and System.runFinalization(). System.gc()
	   should only ever be used in a testing and debugging situation, it should
	   never be part of a normal program except in the most unusual
	   circumstance. Calling System.gc() does not guarantee that a garbage
	   collection has been run, only that a "best effort" has been expended.
	   However, if it does run, a full garbage collection will be done.

	   Similarly, System.runFinalization()should only ever be used in a testing
	   and debugging situation, it should rarely be part of a normal program
	   except in the most unusual circumstance. Part of the problem is that it
	   can only be fully effective if a gc has just completed, but calling
	   System.gc() does not guarantee that a garbage collection has been run.
	   Hence, System.runFinalization() can also only say that a "best effort"
	   has been expended. */
	message("-- Testing counting Students, Courses and Sections --");

	/* Print the state of affairs as it currently stands. */
	message("StudentCount = " + Student.getStudentCount());
	message("SectionCount = " +
			Methods.GET_SECTION_COUNT.intInvoke(thirdSection, null));
	message("CourseCount = " + Methods.GET_COURSE_COUNT.intInvoke(firstCourse, null));

	message("--There has to be special programming to force finalization --");

	message("Set first, second and fourth Sections to " +
			"null then garbage collect (Section count should be 1)");
	firstSection = null;
	secondSection = null;
	fourthSection = null;
	message("Garbage collection and finalization executed.");
	gc();				//	A gc "forces" items to be finalized to be placed on
						//  the finalization queue.
	runFinalization();	// This "forces" the finalization queue to be processed
	int sectionCount = Methods.GET_SECTION_COUNT.intInvoke(thirdSection, null);
						// Section count should now be 1, for  all
						// Sections except the third have been deleted, and
						// a garbage collection has happened.
	message("Section count = " + sectionCount);
	if( sectionCount != 1 )
		error("Section count at this point should be 1");

	/* This ensures that there is still a remaining reference to a course.  */
	message("Set first Course to null then garbage collect " +
			"(Course count should be 1)");
	firstCourse = null;
	message("Garbage collection and finalization executed.");
	gc();				//	A gc "forces" items to be finalized to be placed on
						//  the finalization queue.
	runFinalization();	// This "forces" the finalization queue to be processed
	courseCount = Methods.GET_COURSE_COUNT.intInvoke(null, null);
	message("CourseCount = " + courseCount);
	if( courseCount != 1 ) error("Course count at this point should be 1");

	message("Set second Course to null then garbage collect " +
			"(Course count should still be 1)");
	secondCourse = null;
	message("Garbage collection and finalization executed.");
	gc();				//	A gc "forces" items to be finalized to be placed on
						//  the finalization queue.
	runFinalization();	// This "forces" the finalization queue to be processed
	courseCount = Methods.GET_COURSE_COUNT.intInvoke(null, null);
	message("CourseCount = " + courseCount);
						// Course count should still be 1, as the third Section
						// still holds a reference to the second course.
	if( courseCount != 1 )
		error("Course count at this point should be 1");

	/* A couple of students should remain referenced by a section. */
	message("What has happened to the student count with these " +
			"collections? (Should be 2)");
	int studentCount = Student.getStudentCount();
	message("StudentCount = " + studentCount);
	if( studentCount != 2 )
		error("Some students have been kept or lost if answer != 2");
	markline();

// ----------------- Check on constructing incorrect courses. ------------------
	message("-- Testing operations with some invalid values --");
	/* Test with invalid course number. */
	String courseNumber = "2201";
	Object another = Constructors.COURSE3.instance(courseException,
								coursePrefix, courseNumber, "No such course");
	if( another == null )
	{
		if( courseException ==  null )
			error("Course with course number " +
								courseNumber + " not constructed.");
	}
	else
	{
		/* The course was created; check that the invalid course number was
		   noticed. */
		message("Course constructed with invalid course number.");
		if( Methods.TO_STRING.stringInvoke(another, null).endsWith(courseNumber) )
			error("Course number for course should be empty.");
	}

	String courseName = null;
	/* Test with invalid course name. */
	another = Constructors.COURSE3.instance(courseException,
								coursePrefix, ADVANCED, courseName);
	if( another == null )
	{
		if( courseException ==  null )
			error("Course with null course name not constructed.");
	}
	else
	{
		/* The course was created; check that the invalid course name was
		   noticed. */
		message("Course constructed with invalid course name.");
		if( Methods.COURSE_GET_DETAILS.stringInvoke(another, null).contains("null") )
			error("Course name for course should be empty.");
	}

	another = Constructors.COURSE4.instance(courseException,
								coursePrefix, courseNumber, "No such course", 0);
	if( another == null )
	{
		if( courseException ==  null )
			error("Course with course number " +
								courseNumber + " not constructed.");
	}
	else
	{
		/* The course was created; check that the invalid course number was
		   noticed. */
		message("Course constructed with invalid course number.");
		if( Methods.TO_STRING.stringInvoke(another, null).endsWith(courseNumber) )
			error("Course number for course should be empty.");
	}

	courseName = null;
	/* Test with invalid course name. */
	another = Constructors.COURSE4.instance(courseException,
								coursePrefix, ADVANCED, courseName, 0);
	if( another == null )
	{
		if( courseException ==  null )
			error("Course with null course name not constructed.");
	}
	else
	{
		/* The course was created; check that the invalid course name was
		   noticed. */
		message("Course constructed with invalid course name.");
		if( Methods.COURSE_GET_DETAILS.stringInvoke(another, null).contains("null") )
			error("Course name for course should be empty.");
	}

	/* Test with invalid number of credits, */
	another = Constructors.COURSE4.instance(courseException,
								coursePrefix, ADVANCED, "Invalid credits", -6);
	if( another == null )
	{
		if( courseException ==  null )
			error("Course with invalid number of credits not constructed.");
	}
	else
	{
		/* The course was created; check that the invalid credits name were
		   noticed. */
		message("Course constructed with invalid number of credits.");
		if( Methods.GET_CREDITS.intInvoke(another, null) != 0 )
			error("Credits for course should be 0.");
	}

	/* Create another section with an invalid section number. */

	String sectionNumber = "WXYZ";
	Object anotherCourse = Constructors.COURSE4.instance(null,
								coursePrefix, STANDARD, "Test", 3);
	if( anotherCourse != null )
	{
		another = Constructors.SECTION2.instance(sectionException,
				anotherCourse, sectionNumber);
		if( another == null )
		{
			if( sectionException == null )
				error("Section with empty section number not constructed.");
		} else
		{
			/* Give this section some dates. */
			Methods.SET_DATES.voidInvoke(another, null,
				new Date(8, 21, 2007), new Date(12, 10, 2007));
			Methods.SET_TIMES.voidInvoke(another, null,
				new Time2(10, 0, 0), new Time2(11, 15, 0));
			/* The section was created; check that the invalid course number was
			   noticed. */
			message("Section constructed with invalid section number.");
			if( Methods.SECTION_GET_DETAILS.stringInvoke(another, null).
													contains(sectionNumber) )
				error("Section number for course should be empty.");
		}
	}
	if( errorCount != 0 )
		error(errorCount + " error" +
							(errorCount == 1 ? "" : "s") + " reported ??**");
}

/**
 * Add the given students to a section, and check that they were correctly
 * added.
 *
 * @param sectionStudents	an array of students
 * @param section			the section to which to add them
 * @param reference			the way to refer to this section
 */
private void checkStudents(Student[] sectionStudents, Object section,
		String reference)
{
	for( Student student : sectionStudents )
		Methods.ADD_STUDENT.voidInvoke(section, null, student);
	int getCount = Methods.GET_ROSTER_COUNT.intInvoke(section, null);
	if( getCount != sectionStudents.length )
		error(reference + " section student count " + getCount +
				" incorrect; should be " + sectionStudents.length);
	message("Details of the " +
			reference.toLowerCase(Locale.getDefault()) + " section");
	copy(Methods.SECTION_GET_DETAILS.stringInvoke(section, null));
	message("Roster of the " +
			reference.toLowerCase(Locale.getDefault()) + " section");
	copy(Methods.GET_ROSTER.stringInvoke(section, null));
	line();
}

/**
 * Create a given number of Student objects.
 *
 * @param count	the given number
 * @return	array of that many students
 */
private Student[] getStudents(int count)
{
	Student[] result = new Student[count];
	for( int i = 0; i != count; ++i ) result[i] = studentGenerator();
	return result;
}

/**
 * Factory method for creating what may be reasonable "students." This method
 * use one of the three available Student constructors, depending upon the
 * information supplied to the method. The values supplied may be any of (1)
 * none, meaning the Student is instantiated without either a date of birth or a
 * declared major; (2) with a date of birth, or (3) a date of birth and a
 * declared major. The given parameters are checked to be of the correct type.
 *
 *
 * @param dateDegree	an array, which may be empty, contain a date of birth, or
 *                   contain a date of birth and a declared major.
 * @return	an instantiated Student object.
 */
private Student studentGenerator(Object... dateDegree)
{
	/*  Random number for student number. */
	int rnumber = RANDOM.nextInt(100000000);
	String studentNumber = "S" + String.format("%08d", rnumber);

	/* Four to eight random letters for a family name. */
	rnumber = RANDOM.nextInt(5) + 4;
	StringBuilder familyName = new StringBuilder(4);
	for( int j = 0; j != rnumber; ++j )
		familyName.append(LETTERS.charAt(RANDOM.nextInt(LETTERS.length())));
	familyName.setCharAt(0, Character.toUpperCase(familyName.charAt(0)));

	/* Three to seven random letters for a given name. */
	rnumber = RANDOM.nextInt(5) + 3;
	StringBuilder givenName = new StringBuilder(3);
	for( int j = 0; j != rnumber; ++j )
		givenName.append(LETTERS.charAt(RANDOM.nextInt(LETTERS.length())));
	givenName.setCharAt(0, Character.toUpperCase(givenName.charAt(0)));

	/* Instantiate a Student using one of the three constructors. */

	return dateDegree.length == 0 ?
			new Student(studentNumber,
					givenName.toString(), familyName.toString()) :
			dateDegree.length == 1 && dateDegree[0] instanceof Date ?
					new Student(studentNumber,
							givenName.toString(), familyName.toString(),
							(Date) dateDegree[0]) :
					dateDegree.length == 2 && dateDegree[0] instanceof Date &&
					dateDegree[1] instanceof Degree ?
							new Student(studentNumber,
									givenName.toString(), familyName.
									toString(),
									(Date) dateDegree[0],
									(Degree) dateDegree[1]) :
							null;
}

/**
 * Program entry point.
 * <p>
 * Execute:</p>
 * <pre> java edu.frontrange.csc240.a6.Assignment6Test</pre>
 *
 * @param args	unused.
 */
public static void main(String... args)
{
	try
	{
		/* Run the actual tests. */
		new Assignment6Test().runTest();
	} catch( Throwable th )
	{
		/* Some exception other than those expected has happened. */
		error("Unexpected exception: " + th.toString());
		throw th;
	}
}

//--------------------- Message and other output methods -----------------------
/**
 * Used for printing copies of messages from the object being tested. Prints the
 * message surrounded by quotes.
 *
 * @param copy	the String to copy to the output
 */
protected static void copy(String copy)
{
	out.println("\"" + copy + "\"");
}

/**
 * Print an error message--looks like a regular message, but with two question
 * marks..
 *
 * @param message	the message to the user.
 */
protected static void error(String message)
{
	++errorCount;
	out.println("**?? " + message);
}

/**
 * Print an empty line.
 */
protected static void line()
{
	out.println();
}

/**
 * Print a row of asterisks to mark off different parts of the output.
 */
protected static void markline()
{
	out.println("***********************************************");
}

/**
 * Output a message with the test marker prepended.
 *
 * @param message	the message
 */
protected static void message(String message)
{
	out.println("** " + message);
}

//------------------------ Reflective calling support --------------------------

/**
 * Enumeration of the different classes involved in the exercise
 */
@SuppressWarnings("ProtectedInnerClass")
protected static enum Classes
{
PREFIX("Prefix"),
COURSE("Course"),
SECTION("Section"),
STUDENT("Student"),
DATE("Date"),
VALIDATION_EXCEPTION("ValidationException"),
COURSE_EXCEPTION("CourseException"),
SECTION_EXCEPTION("SectionException");

/**
 * The class of the class.
 */
private Class<?> clazz;

/**
 * The class loader for this application.
 */
private final ClassLoader classLoader;

/**
 * The name of the class.
 */
private final String name;

/**
 * What has been reported
 */
private final Set<String> reported = new HashSet<>();

/**
 * Constructor: private to prevent external instantiation.
 *
 * @param name	the name of the class
 */
private Classes(String name)
{
	/* Remember the name, and identify the class loader. */
	this.name = name;
	this.classLoader = this.getClass().getClassLoader();
}

/**
 * Access the actual class of the Classes enumeration object.
 *
 * @param report	if absence of the class is to be reported
 * @return the actual class of the Classes enumeration object
 */
public Class<?> classClass(boolean report)
{
	/* If this class has not yet been loaded, then load the class. */
	if( clazz == null )
		try
		{
			clazz = classLoader.loadClass(package_name + "." + name);
		} catch( ClassNotFoundException ex )
		{
			/* Report the class cannot be found if report is desired, and it
			   has not yet been reported. */
			if( report & !reported.contains(name) )
			{
				error(ex.toString());
				reported.add(name);
			}
		}
	return clazz;
}
	}

/**
 * Enumerate and define the constructors for the classes.
 */
protected static enum Constructors
{
COURSE3(Classes.COURSE, Classes.PREFIX.classClass(true),
					String.class, String.class),
COURSE4(Classes.COURSE, Classes.PREFIX.classClass(true),
					String.class, String.class, int.class),
SECTION2(Classes.SECTION, Classes.COURSE.classClass(true),
					String.class),
STUDENT3(Classes.STUDENT, String.class,
					String.class, String.class),
STUDENT4(Classes.STUDENT, String.class,
					String.class, String.class, Classes.DATE.classClass(true));

/**
 * The enumeration value designating the class.
 */
private final Classes classic;

/**
 * The actual constructor for the enumeration value.
 */
private Constructor<?> constructor;

/**
 * The types of the parameters needed for this constructor.
 */
private final Class<?>[] types;

/**
 * Constructor: private to prevent external instantiation.
 *
 * @param classic	the enumeration value for the class
 * @param types	the types of the parameters to the constructor
 */
private Constructors(Classes classic, Class<?>... types)
{
	this.classic = classic;
	this.types = types;
}

/**
 * Obtain an instance of the class.
 *
 * @param arguments		the arguments to the constructor for this instance
 * @param exception		if not null, the class of an expected exception
 *
 *
 * @return			the instance
 */
public Object instance(Class<?> exception, Object... arguments)
{
	Object result = null;
	/* If the constructor has not yet been found ... */
	if( constructor == null )
		try
		{
			Class<?> classClass = classic.classClass(true);
			if( classClass == null )
				throw new InstantiationException(
						classic.name() + ": constructor not found");
			constructor = classClass.getConstructor(types);
			if( constructor == null )
				throw new InstantiationException(
						classic.name() + ": no available constructor");
			constructor.setAccessible(true);
		} catch( NoSuchMethodException | SecurityException |
				InstantiationException ex )
		{
			error("Error: " + ex.toString());
		}
	/* If the constructor is successfully found, then create an instance of
	   that class.  */
	if( constructor != null)
		try
		{
			result = constructor.newInstance(arguments);
			if( exception != null )
				error( exception.getSimpleName() + " expected but did not happen.");
		} catch( InvocationTargetException ex)
		{
			if( ex.getCause() == null ) error(ex.toString());
			else if( ex.getCause().getClass() == exception )
			{
					message("Expected: " + ex.getCause().toString());

					Throwable furtherCause = ex.getCause().getCause();
					if( furtherCause != null )
					{
						message("Cause of the exception is:");
						message("   " +	furtherCause.toString());
					}
			}
			else
				error("Unexpected exception: " + ex.getCause().toString());
		} catch( InstantiationException | IllegalAccessException |
				IllegalArgumentException | 	NullPointerException ex )
		{
			error("Error: " + ex.toString());
		}
	return result;
}

}

/**
 * Enumeration of the methods used in the tests.
 */
protected static enum Methods
{
ADD_STUDENT(Classes.SECTION, "addStudent", Student.class),
COURSE_GET_DETAILS(Classes.COURSE, "getDetails"),
GET_COURSE_COUNT(Classes.COURSE, "getCourseCount"),
GET_CREDITS(Classes.COURSE, "getCredits"),
GET_ROSTER(Classes.SECTION, "getRoster"),
GET_SECTION_COUNT(Classes.SECTION, "getSectionCount"),
GET_SECTION_NUMBER(Classes.SECTION, "getSectionNumber"),
GET_ROSTER_COUNT(Classes.SECTION, "getRosterCount"),
GET_TITLE(Classes.PREFIX, "getTitle"),
NAME(Classes.PREFIX, "name"),
SECTION_GET_DETAILS(Classes.SECTION, "getDetails"),
SET_CREDITS(Classes.COURSE, "setCredits", int.class),
SET_DATES(Classes.SECTION, "setDates", Date.class, Date.class),
SET_TIMES(Classes.SECTION, "setTimes", Time2.class, Time2.class),
TO_STRING(Classes.COURSE, "toString"),
VALUES(Classes.PREFIX, "values");

/**
 * The actual class containing the method.
 */
private final Class<?> clazz;

/**
 * Reflective access to the method.
 */
private Method method = null;

/**
 * The known name of the method.
 */
private final String name;

/**
 * The array of the types of the parameters to this method.
 */
private final Class<?>[] parameters;

private Methods(Object classo, String name, Class<?>... parameters)
{
	this.name = name;
	this.parameters = parameters;

	clazz = classo instanceof Class ? (Class<?>) classo :
			classo instanceof Classes ? ((Classes) classo).classClass(true) : null;

}

/**
 * Call a method returning an int value.
 *
 * @param instance	the instance of the class
 * @param exception		if not null, the class of an expected exception
 * @return			the value returned by the method call
 */
public int intInvoke(Object instance, Class<?> exception)
{
	int result = 0;
	if( setMethod() )
		try
		{
			result = (int) method.invoke(instance);
		if( exception != null )
				error( exception.getSimpleName() +
											" expected but did not happen.");
		} catch( InvocationTargetException ex)
		{
			if( ex.getCause() == null ) error(ex.toString());
			else if( ex.getCause().getClass() == exception )
					message("Expected: " + ex.getCause().toString());
			else
				error("Unexpected exception: " + ex.getCause().toString());
		} catch( SecurityException | IllegalAccessException |
				IllegalArgumentException | 	NullPointerException ex )
		{
			error("Error: " + ex.toString());
		}
	return result;
}

/**
 * Call a method returning an Object value.
 *
 * @param instance	the instance of the class
 * @param exception		if not null, the class of an expected exception
 * @return			the value returned by the method call
 */
public Object[] objectArrayInvoke(Object instance, Class<?> exception)
{
	Object[] result = new Object[0];
	if( setMethod() )
		try
		{
			result = (Object[]) method.invoke(instance);
			if( exception != null )
				error( exception.getSimpleName() +
											" expected but did not happen.");
		} catch( InvocationTargetException ex)
		{
			if( ex.getCause() == null ) error(ex.toString());
			else if( ex.getCause().getClass() == exception )
					message("Expected: " + ex.getCause().toString());
			else
				error("Unexpected exception: " + ex.getCause().toString());
		} catch( SecurityException | IllegalAccessException |
				IllegalArgumentException | 	NullPointerException ex )
		{
			error("Error: " + ex.toString());
		}
	return result;
}

/**
 * Call a method returning a String value.
 *
 * @param instance		the instance of the class
 * @param exception		if not null, the class of an expected exception
 * @return				the value returned by the method call
 */
public String stringInvoke(Object instance, Class<?> exception)
{
	String result = null;
	if( setMethod() )
		try
		{
			result =  (String) method.invoke(instance);
			if( exception != null )
				error( exception.getSimpleName() +
											" expected but did not happen.");
		} catch( InvocationTargetException ex)
		{
			if( ex.getCause() == null ) error(ex.toString());
			else if( ex.getCause().getClass() == exception )
					message("Expected: " + ex.getCause().toString());
			else
				error("Unexpected exception: " + ex.getCause().toString());
		} catch( SecurityException | IllegalAccessException |
				IllegalArgumentException | 	NullPointerException ex )
		{
			error("Error: " + ex.toString());
		}
	return result;
}

/**
 * Call a method returning no value.
 *
 * @param instance		the instance of the class
 * @param exception		if not null, the class of an expected exception
 * @param parameters	the values for the parameters of the call
 */
public void voidInvoke(Object instance, Class<?> exception, Object... parameters)
{
	if( setMethod() )
		try
		{
			method.invoke(instance, parameters);
			if( exception != null )
				error( exception.getSimpleName() +
											" expected but did not happen.");
		} catch( InvocationTargetException ex)
		{
			if( ex.getCause() == null ) error(ex.toString());
			else if( ex.getCause().getClass() == exception )
					message("Expected: " + ex.getCause().toString());
			else
				error("Unexpected exception: " + ex.getCause().toString());
		} catch( SecurityException | IllegalAccessException |
				IllegalArgumentException | 	NullPointerException ex )
		{
			error("Error: " + ex.toString());
		}
}

/**
 * Delayed setting of the method variable
 *
 * @return	true if the method variable is successfully set
 */
private boolean setMethod()
{
	/* If the variable is not already set... */
	if( method == null )
		try
		{
			/* Get the Method object by reflection on the class. */
			method = clazz.getMethod(name, parameters);
		} catch( NoSuchMethodException | SecurityException |
				IllegalArgumentException | NullPointerException ex )
		{
			/* The reflection did not succeed in finding the method. */
			error("Method \"" + name + "\" not found.");
			return false;
		}
	/* Method was already set, or was found on this call. */
	return true;
}
	}

/**
 * Enumeration of the methods used in the tests.
 */
protected static enum Fields
{
COURSE_NAME(Classes.COURSE, "courseName"),
COURSE_NUM(Classes.COURSE, "courseNum"),
CREDITS(Classes.COURSE, "credits");

/**
 * The actual class containing the field.
 */
private final Class<?> clazz;

/**
 * Reflective access to the field.
 */
private Field field = null;

/**
 * The known name of the field.
 */
private final String name;

private Fields(Object classo, String name)
{
	this.name = name;
	clazz = classo instanceof Class ? (Class<?>) classo :
			classo instanceof Classes ?
								((Classes) classo).classClass(true) : null;

}

/**
 * Access an int field.
 *
 * @param instance	the instance of the class
 * @return			the value accessed
 */
public int intAccess(Object instance)
{
	if( setField() )
		try
		{
			return (int) field.get(instance);
		} catch( SecurityException | IllegalAccessException |
				NullPointerException ex )
		{
			error(ex.toString());
		}
	return 0;
}

/**
 * Access a String field.
 *
 * @param instance	the instance of the class
 * @return			the value accessed
 */
public String stringAccess(Object instance)
{
	if( setField() )
		try
		{
			return (String) field.get(instance);
		} catch( SecurityException | IllegalAccessException |
				NullPointerException ex )
		{
			error(ex.toString());
		}
	return null;
}

/**
 * Delayed setting of the method variable
 *
 * @return	true if the method variable is successfully set
 */
private boolean setField()
{
	/* If the variable is not already set... */
	if( field == null )
		try
		{
			/* Get the Method object by reflection on the class. */
			field = clazz.getDeclaredField(name);
			field.setAccessible(true);
		} catch( NoSuchFieldException | SecurityException |
				IllegalArgumentException | NullPointerException ex )
		{
			/* The reflection did not succeed in finding the method. */
			error("Method \"" + name + "\" not found.");
			return false;
		}
	/* Field was already set, or was found on this call. */
	return true;
}

}
}
