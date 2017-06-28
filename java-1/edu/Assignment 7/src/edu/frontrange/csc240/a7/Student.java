
package edu.frontrange.csc240.a7;

/**
 * This class models students in a Community College. Each instance keeps certain
 * details of one student. The details include the student ID, given and
 * family name, date-of-birth, and the target degree for the student. Invalid
 * data leads to Exceptions being thrown.
 *
 * @author		Dr. Bruce K. Haddon, Instructor
 * @version		2.5, 2016-08-22, CSC-240 Assignment 6, per the Assignment
 *				Instructions
 */
public class Student
{
/**
 * String to identify the first (given) name in messages.
 */
private static final String A_FIRST_NAME_LABEL = "first name";

/**
 * String to identify the last (family) name in messages.
 */
private static final String A_LAST_NAME_LABEL = "last name";

/**
 * Default name and ID value
 */
private static final String DEFAULT_NAME_AND_ID = "";

/**
 * Constant across all Student instances, for the length of the student ID. This
 * value must be 1 or greater.
 */
private static final int ID_LENGTH = 9;

/**
 * String to use if the first name is not validly set.
 */
private static final String INVALID_FIRST_NAME =
								"<Invalid " + A_FIRST_NAME_LABEL + ">";

/**
 * String to use if the last name is not validly set.
 */
private static final String INVALID_LAST_NAME =
								"<Invalid " + A_LAST_NAME_LABEL + ">";

/**
 * The system-specific character sequence used to separate lines.
 */
private static final String LINE_SEPARATOR = System.getProperty("line.separator");

/**
 * Position in the ID where the letter S will be found.
 */
private static final int S_POSITION = 0;

/**
 * The number of Student objects currently in memory. Static variable
 * incremented by each use of a constructor, decremented when any instance is
 * finalized.
 */
private static int count = 0;

/**
 * The student's date of birth (DOB), or null.
 */
private Date birthDate;

/**
 * The student's given name.
 */
private String firstName = DEFAULT_NAME_AND_ID;

/**
 * The student's family name.
 */
private String lastName = DEFAULT_NAME_AND_ID;

/**
 * Student ID for this student. The length must be ID_LENGTH.
 */
private String studentID = DEFAULT_NAME_AND_ID;

/**
 * The student's current objective degree, or null.
 */
private Degree targetDegree;

//-------------------------------- Constructors  -----------------------------------
/**
 * @param studentID		student ID for this student. The length must be ID_LENGTH.
 * @param firstName		the student's given name.
 * @param lastName		the student's family name.
 * @throws StudentException if the constructor cannot be completed
 */
public Student(String studentID, String firstName, String lastName)
			throws StudentException
{
	/* Increment the count of Student objects. Do this first, as the object has
	   already been created. */
	Student.count++;

	/* Initialize with the provided data using the validated values. */
	try
	{
		if( isValidStudentID(studentID) )
			this.studentID = studentID;
		if( isValidFirstName(firstName) )
			this.firstName = firstName;
		if( isValidLastName(lastName) )
			this.lastName = lastName;
	} catch( ValidationException ex )
	{
		throw new StudentException("Error in constructor", ex);
	}
}

/**
 * @param studentID		student ID for this student. The length must be ID_LENGTH.
 * @param firstName		the student's given name.
 * @param lastName		the student's family name.
 * @param birthDate		the student's date-of-birth (precondition: not null)
 * @throws StudentException if the constructor cannot be completed
 */
public Student(String studentID, String firstName, String lastName,
		Date birthDate) throws StudentException
{
	this(studentID, firstName, lastName);
	setBirthDate(birthDate);
}

// Provide a constructor that accepts all data values
/**
 * @param studentID		student ID for this student. The length must be
 *						ID_LENGTH.
 * @param firstName		the student's given name.
 * @param lastName		the student's family name.
 * @param birthDate		the student's date-of-birth (precondition: not null)
 * @param targetDegree	the student's degree program (precondition: not null)
 * @throws StudentException if the constructor cannot be completed
 */
public Student(String studentID, String firstName, String lastName,
		Date birthDate, Degree targetDegree) throws StudentException
{
	this(studentID, firstName, lastName, birthDate);
	setDegree(targetDegree);
}

/**
 * Get the Student's date-of-birth.
 *
 * @return	student's date-of-birth (null if not supplied)
 */
public Date getBirthDate()
{
	return birthDate;
}

/**
 * Set the birth date of the Student.
 *
 * @param birthDate		the birth date of the Student (precondition: not null)
 */
public final void setBirthDate(Date birthDate)
{
	this.birthDate = birthDate;
}

/**
 * Get the target degree towards which the Student is working.
 *
 * @return	student's currently targeted degree (null if not supplied)
 */
public Degree getDegree()
{
	return targetDegree;
}

/**
 * Set the target degree towards which this student is working.
 *
 * @param targetDegree	the target degree (precondition: not null)
 */
public final void setDegree(Degree targetDegree)
{
	this.targetDegree = targetDegree;
}

/**
 * Create a String detailing the Student's name, student ID, date of birth, and
 * the degree towards which the Student is working. Each item is separated from
 * the next by the system designated line.separator.
 *
 * @return	all details of student (name, ID, DOB, and degree).
 */
public String getDetails()
{
	/* The literal strings in this output occur only in this method. */
	return String.join(LINE_SEPARATOR,
			"Student: " + getFullName(),
			"Student ID: " + (studentID.isEmpty() ?
					"<No valid StudentID supplied>" : studentID),
			"Birth Date: " + (birthDate == null ?
					"<Birth date not provided>" : birthDate),
			"Degree: " + (targetDegree == null ?
					"<No Degree specified)>" : targetDegree));
}

/**
 * Get the Student's first (given) name.
 *
 * @return	student's given name (null if no valid name supplied)
 */
public String getFirstName()
{
	return firstName;
}

/**
 * Set the first (given) name of the Student.
 *
 * @param firstName the given name of the Student
 * @throws ValidationException if the name is invalid
 */
public void setFirstName(String firstName) throws ValidationException
{
	this.firstName = DEFAULT_NAME_AND_ID;
	if( isValidFirstName(firstName) )
		this.firstName = firstName;
}

/**
 * Get the full name of the student. If parts of the name have not been validly
 * supplied, this will be noted in the returned String.
 *
 * @return	full name (given name followed by family name).
 */
public String getFullName()
{
	return (firstName.isEmpty() ? INVALID_FIRST_NAME : firstName) + " " +
			(lastName.isEmpty() ? INVALID_LAST_NAME : lastName);
}

/**
 * Get the Student's last (family) name.
 *
 * @return	student's family name (null if no valid name supplied)
 */
public String getLastName()
{
	return lastName;
}

/**
 * Set the last (family) name of the Student.
 *
 * @param lastName the family name of the Student
 * @throws ValidationException if the name is invalid
 */
public void setLastName(String lastName) throws ValidationException
{
	this.lastName = DEFAULT_NAME_AND_ID;
	if( isValidLastName(lastName) )
		this.lastName = lastName;
}

/**
 * Get the student ID of the Student.
 *
 * @return	student ID (S and eight digits).
 */
public String getStudentID()
{
	return studentID;
}

/**
 * Set the studentID for this student.
 *
 * @param studentID	the student ID (must be of format Snnnnnnnn)
 * @throws ValidationException if the student ID is invalid
 */
public void setStudentID(String studentID) throws ValidationException
{
	this.studentID = DEFAULT_NAME_AND_ID;
	if( isValidStudentID(studentID) )
		this.studentID = studentID;
}

/**
 * Student ID and name.
 *
 * @return	student ID and name
 */
@Override
public String toString()
{
	return getStudentID() + " " + getFullName();
}

/**
 * Reduce the instance count by one.
 *
 * @throws Throwable	standard interface
 */
@SuppressWarnings("FinalizeDeclaration")
@Override
protected void finalize() throws Throwable
{
	--count;				// decrement the count of existing Student objects
	super.finalize();
}

/**
 * Access the current count of all Student objects.
 *
 * @return	the current count of all Student objects
 */
public static int getStudentCount()
{
	return count;
}

/**
 * Validate a first name. The name must not be null or empty.
 *
 * @param name		the name to validate
 * @return			true if the name if valid
 * @throws ValidationException if the name is invalid
 */
private static boolean isValidFirstName(String name) throws ValidationException
{
	if( name != null && !name.isEmpty() ) return true;
	throw new ValidationException("Invalid " + A_FIRST_NAME_LABEL + ": " + name);
}

/**
 * Validate a last name. The name must not be null or empty.
 *
 * @param name		the name to validate
 * @return			true if the name if valid
 * @throws ValidationException if the name is invalid
 */
private static boolean isValidLastName(String name) throws ValidationException
{
	if( name != null && !name.isEmpty() ) return true;
	throw new ValidationException("Invalid " + A_LAST_NAME_LABEL + ": " + name);
}

/**
 * Validate a student ID. It must consist of the letter 'S' followed by exactly
 * the number of digits to make up the total length.
 *
 * @param studentID	the student ID to validate
 * @return			true if the student ID is valid
 * @throws ValidationException if the student ID is invalid
 */
private static boolean isValidStudentID(String studentID) throws ValidationException
{
	/* If the ID is not null, and of the correct length, and starts with the letter
	   'S', then  check that the remaining characters are digits. This is done by
	   checking that all the remaining characters satisfy the isDigit condition. */
	boolean result = false;
	if( studentID != null &&
				studentID.length() == ID_LENGTH &&
							studentID.charAt(S_POSITION) == 'S' )
	{
		/* The ID has passed the initial tests, now, are all the remaining
		   characters digits? */
		result = true;
		for( int i = S_POSITION + 1; i != ID_LENGTH; ++i )
			if( !Character.isDigit(studentID.charAt(i)) ) result = false;
	}
	/* Throw the exception if the ID is not valid. */
	if( !result ) throw new ValidationException("Invalid student ID: " + studentID);

	/* Return the final result. */
	return result;
}
}
