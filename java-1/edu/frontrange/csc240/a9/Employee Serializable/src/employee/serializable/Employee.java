
package employee.serializable;

import java.io.Serializable;

/**
 * Sample class with which to demonstrate serialization. This is a very simple
 * class with no checks or other fancy stuff.
 *
 * @author      John Kirch, S01581562
 * @author	Dr. Bruce K. Haddon, Instructor
 * @version	2.1, 2016-08-22, CSC-240 Assignment 6, per the Assignment Instructions
 */
public class Employee implements Serializable
{
/**
 * Employee's first (or given) name.
 */
private final String firstName;

/**
 * Employee's last (or family) name.
 */
private final String lastName;

/**
 * Employee's date-of-birth.
 */
private final Date birthDate;

/**
 * This Employee's date-of-hire.
 */
private final Date hireDate;

/**
 * Constructor.
 * 
 * @param firstName		employee's first (or given) name
 * @param lastName		employee's last (or family) name
 * @param birthDate		employee's date-of-birth
 * @param hireDate		employee's date-of-hire
 */
public Employee(String firstName, String lastName, Date birthDate, Date hireDate)
{
	this.firstName = firstName;
	this.lastName = lastName;
	this.birthDate = birthDate;
	this.hireDate = hireDate;
}

/**
 * Employee details (name, hire date, birth date)
 *
 * @return	employee details (name, hire date, birth date)
 */
@Override
public String toString()
{
	return String.format("%s, %-8s  Hired: %-12s  Birthday: %s", lastName, firstName,
			hireDate, birthDate);
}
}
