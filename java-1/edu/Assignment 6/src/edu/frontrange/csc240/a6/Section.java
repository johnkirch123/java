package edu.frontrange.csc240.a6;

import java.util.ArrayList;
import java.util.List;
/**
 * This class models sections at a community college. It keeps details of the section 
 * and tracks the roster of students for each of the different sections. 
 * 
 * @author John Kirch, S01581562
 * @version 1.0, 03-02-2017, CSC-240 Assignment 6
 */
public class Section 
{
private final Course COURSE;
private final String SECTION_NUMBER;
private static int sectionCount;
private static final int MAX_STUDENTS = 30;
private int rosterCount;
private static final String LINE_SEPARATOR = System.getProperty("line.separator");
private final List<Student> students = new ArrayList<>();
private Date startDate;
private Date endDate;
private Time2 startTime;
private Time2 endTime;
private final String EMPTY_STRING = "";
/**
 * Sets the course and section number for the class.
 * 
 * @param course the course for which the section pertains
 * @param sectionNumber the section number that is created
 */    
public Section (Course course, String sectionNumber)
{
    sectionCount++;
    this.COURSE = course;
    SECTION_NUMBER = ((sectionNumber != null && sectionNumber.length() == 3) ? sectionNumber : EMPTY_STRING);
    
}
/**
 * Adds a student object to the student array and increments rosterCount.
 * 
 * @param thisStudent Student object to add
 */
public void addStudent (Student thisStudent)
{
    /* If number of students in class is less than 30 and student is not null, add student. */
    if(rosterCount != MAX_STUDENTS && thisStudent != null)
    {
        rosterCount++;
        students.add(thisStudent);
    }
}
/**
 * Create a String detailing the Student's Section, Course, Dates of class, Times of class, and
 * the number of enrolled students. Each item is separated from the next by the system designated 
 * line.separator.
 * 
 * @return  all the students details (Section, Course, Dates, Times, and Enrollment).
 */
public String getDetails() 
{
    return String.join(LINE_SEPARATOR,
        "Section: " + COURSE + "-" + SECTION_NUMBER,
            "Course: " + COURSE.getDetails(),
                "Dates: " + startDate + " to " + endDate,
                    "Times: " + startTime + " to " + endTime,
                        "Enrollment: " + rosterCount);
}
/**
 * Return the String of the students information stored in students array.
 * 
 * @return  each student in the array of students 
 */
public String getRoster()
{
    String studentInfo = "";
    for (Student student : students)
        studentInfo += String.join(LINE_SEPARATOR, student.toString()) + "\n";
    return studentInfo;
}
/**
 * Return the int rosterCount (The number of students in a particular section).
 * 
 * @return  rosterCount
 */
public int getRosterCount()
{
    return rosterCount;
}
/**
 * Return the section number of the section.
 * 
 * @return  SECTION_NUMBER
 */
public String getSectionNumber()
{
    return SECTION_NUMBER;
}
/**
 * Sets the start and end dates for the class section with the Date types.
 * 
 * @param startDate the Date type startDate is set
 * @param endDate the Date type endDate is set
 */
public void setDates (Date startDate, Date endDate)
{
    this.startDate = startDate;
    this.endDate = endDate;
}
/**
 * Sets the start and end times for the class section with the Time2 types.
 * 
 * @param startTime the Time2 type startTime is set
 * @param endTime   the Time2 type endTime is set
 */
public void setTimes (Time2 startTime, Time2 endTime)
{
    this.startTime = startTime;
    this.endTime = endTime;
}
/**
 * Course number and Section number.
 * 
 * @return  COURSE and SECTION_NUMBER
 */
@Override
public String toString()
{
    return COURSE.toString() + "-" + SECTION_NUMBER;
}

@SuppressWarnings("FinalizeDeclaration")
@Override
protected void finalize() throws Throwable
{
	--sectionCount;				// decrement the count of existing section count
	super.finalize();
}
/**
 * Returns the int of the Section count
 * 
 * @return sectionCount
 */
public static int getSectionCount()
{
    return sectionCount;
}
}
