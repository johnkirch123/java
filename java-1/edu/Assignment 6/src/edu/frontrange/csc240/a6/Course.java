package edu.frontrange.csc240.a6;

/**
 * This class models Courses at a community college. Each instance keeps certain details
 * of a course.
 * 
 * @author John Kirch, S01581562
 * @version 1.0, 03-02-2017, CSC-240 Assignment 6
 */
public class Course 
{
private int credits;                                                // number of credits for the course
private static int COURSE_COUNT;                                    // number of courses created
private String courseNum;                                           // the course number - can be alpha-numeric
private String courseName;                                          // course name
private static final String EMPTY_STRING = "";                      // empty string to set null values
static final int COURSE_NUM_LENGTH = 3;                             // the length of the course num
static final int ZERO = 0;                                          // final value of 0
private Prefix coursePrefix;                                        // enum course prefix

public Course (Prefix coursePrefix, String courseNum, String courseName)
{
    COURSE_COUNT++;
    this.coursePrefix = coursePrefix;
    this.courseNum = ((courseNum != null && courseNum.length() == COURSE_NUM_LENGTH) ? courseNum : EMPTY_STRING);
    this.courseName = ((courseName != null) ? courseName : EMPTY_STRING);
}

public Course (Prefix coursePrefix, String courseNum, String courseName, int credits)
{
    this(coursePrefix, courseNum, courseName);
    setCredits(credits);
}

public int getCredits()
{
    return credits;
}

public int setCredits(int numCredits)
{
    return credits = ((1 <= numCredits && numCredits <= 5) ? numCredits : ZERO);
}

public String getDetails() 
{
    return coursePrefix + "-" + courseNum + " " + courseName + " " + credits + " Credits";
}

@Override
public String toString()
{
    return coursePrefix + "-" + courseNum;
}

@SuppressWarnings("FinalizeDeclaration")
@Override
protected void finalize() throws Throwable
{
	--COURSE_COUNT;				// decrement the count of existing Student objects
	super.finalize();
}

public static int getCourseCount()
{
    return COURSE_COUNT;
}
}
