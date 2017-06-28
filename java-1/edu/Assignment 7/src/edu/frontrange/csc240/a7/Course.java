
package edu.frontrange.csc240.a7;

/**
 * A Course is an offering in the College system. A number of Sections may be
 * associated with each course. A Course has a standard prefix (to indicate the
 * department, a number within that department, and a name. Each course is worth
 * a given number of credit hours per semester.
 *
 * @author		John Kirch, S01581562
 * @version		1.0, 03/08/2017, CSC-240 Assignment 7, per the Assignment
 *				Instructions
 */
public class Course
{
/**
 * Valid length for a courseNum string.
 */
private static final int COURSE_NUMBER_LENGTH = 3;

/**
 * Default name value
 */
private static final String DEFAULT_COURSE_NAME_AND_NUMBER = "";

/**
 * Default credits value.
 */
private static final int DEFAULT_CREDITS_VALUE = 0;

/**
 * Maximum permitted credits for a course
 */
private static final int MAXIMUM_CREDITS = 5;

/**
 * Shared variable for keeping count of the number of course objects in
 * existence.
 */
private static int count = 0;

/**
 * The name given to the course.
 */
private String courseName = DEFAULT_COURSE_NAME_AND_NUMBER;

/**
 * The three-character &ldquo;course number&rdquo;.
 */
private String courseNum = DEFAULT_COURSE_NAME_AND_NUMBER;

/**
 * The prefix (i.e., department) for the course.
 */
private final Prefix coursePrefix;

/**
 * The number of credits allocated to this course.
 */
private int credits = DEFAULT_CREDITS_VALUE;

/**
 * Set the course prefix (i.e., the department in which the course is being
 * taught), the number of the course within the department, and the name of the
 * course. Set the number of credits by default to zero to indicate that the
 * number of credits has not been set.
 *
 * @param coursePrefix	the designated department prefix
 * @param courseNum		the three-character course number within the department
 * @param courseName	the name of the course
 * @throws CourseException if the constructor cannot be completed
 */
public Course(Prefix coursePrefix, String courseNum, String courseName) throws CourseException
{
	this(coursePrefix, courseNum, courseName, DEFAULT_CREDITS_VALUE);
}

/**
 * Set the course prefix (i.e., the department in which the course is being
 * taught), the number of the course within the department, and the name of the
 * course. Set the number of credits to the given value..
 *
 * @param coursePrefix	the three-letter department prefix (represented by a
 *						member of the Prefix enumeration
 * @param courseNum		the three-character course &ldquo;number&rdquo; within
 *						the department (replaced by null if not three characters)
 * @param courseName	the name of the course (replaced by null if the string is
 *						empty
 * @param credits		the number of semester credits for the course, 0 to 5; if
 *						outside this range, the number of credits is replaced by
 *						zero
 * @throws CourseException if the constructor cannot be completed
 */
public Course(Prefix coursePrefix, String courseNum, String courseName, int credits) throws CourseException
{
	/* Increment the collective count of all course objects that have been created.
	   This is done first, since the object already exists. */
	++count;

	this.coursePrefix = coursePrefix;
        
	/* If the course number is valid, set courseNum, else throw CourseException. */
	if( isValidCourseNum(courseNum) )
		this.courseNum = courseNum;
        else
            throw new CourseException("Error setting CourseNum");

	/* If the course name is valid, set courseName, else throw CourseException. */
	if( isValidCourseName(courseName) )
		this.courseName = courseName;
        else
            throw new CourseException("Error setting courseName");

	/* If the number of credits is valid, set credits, else throw CourseException. */
	if( isValidCredits(credits) )
		this.credits = credits;
        else
            throw new CourseException("Error setting credits");
}

/**
 * The number of credits for the course.
 *
 * @return	the number of credits for the course (0 if no credits, or if an
 *			incorrect number of credits has been indicated)
 */
public int getCredits()
{
	return this.credits;
}

/**
 * Set the number of credits for the course.
 *
 * @param credits	the number of credits for the course. If the number is outside
 *					the permitted range, the course credit is set to 0
 * @throws ValidationException if the credits cannot be set
 */
public void setCredits(int credits) throws ValidationException
{
	this.credits = DEFAULT_CREDITS_VALUE;
	if( isValidCredits(credits) )
		this.credits = credits;
        else
            throw new ValidationException("Not a valid credit count");
}

/**
 * Create a String detailing the name of the course, and the number of credits.
 *
 * @return			the name of the course, and the number of credits
 */
public String getDetails()
{
	return String.join(" ",
			toString(), courseName, String.valueOf(credits), "Credits");
}

/**
 * Course number (prefixed)
 *
 * @return			Course number (prefixed)
 */
@Override
public String toString()
{
	return coursePrefix.name() + "-" + courseNum;
}

/**
 * Finalization. Reduce the instance count by one.
 *
 * @throws Throwable	standard interface.
 */
@SuppressWarnings("FinalizeDeclaration")
@Override
protected void finalize() throws Throwable
{
	/* Decrement the count of the collective total of all Course objects. */
	--count;
	super.finalize();
}

/**
 * Get the number of instances of the course class known to exist at this time.
 *
 * @return	the number of instances of the course class known to exist at this
 *         time
 */
public static int getCourseCount()
{
	return count;
}

/**
 * Validate the courseName string. It must not be empty.
 *
 * @param courseName	the courseName string
 * @return				true if the string if valid, otherwise false
 */
private static boolean isValidCourseName(String courseName)
{
	return courseName != null && !courseName.isEmpty();
}

/**
 * Validate the courseNum string. It must be exactly three characters.
 *
 * @param courseNum		the courseNum string
 * @return				true if the string if valid, otherwise false
 */
private static boolean isValidCourseNum(String courseNum)
{
	return courseNum != null && courseNum.length() == COURSE_NUMBER_LENGTH;
}

/**
 * Validate the value of the course credits. The value must be in the range
 * 0&nbsp;&ndash;&nbsp;5.
 *
 * @param credits		the credits value
 * @return				true if the value if valid, otherwise false
 */
private static boolean isValidCredits(int credits)
{
	return credits >= 0 && credits <= MAXIMUM_CREDITS;
}
}
