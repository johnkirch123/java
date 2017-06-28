
package edu.frontrange.csc240.a2;

import static java.lang.System.out;

/**
 * Employee class, to meet the requirements stated in the Assignment
 * Instructions for Assignment 2.
 * <p>
 * The following UML is given for this class: </p>
 * <p>
 * <strong><u>Employee</u> <br>
 * - firstNameField: String <br>
 * - lastNameField: String <br>
 * - salaryField: double <br>
 * <br>
 * +&lt;constructor&gt; Employee(firstName: String, lastName: String,
 * initialSalary: double) <br>
 * + setFirst(firstName: String) <br>
 * + setLast(lastName: String) <br>
 * + setMonthlySalary(salary: double) <br>
 * + getFirst(): String <br>
 * + getLast(): String <br>
 * + getMonthlySalary(): double <br>
 * + displayValues() <br>
 * + toString() : String </strong></p>
 *
 * @author	Dr. Bruce K. Haddon, Instructor
 * @version	2016-08-22, CSC-240 Assignment 2, Exercise 3.13
 */
public class Employee
{
/**
 * The employee's first name (assuming Western World naming).
 */
private String firstNameField;

/**
 * The employee's last name (assuming Western World naming).
 */
private String lastNameField;

/**
 * Employee's monthly salary (assuming USA dollars).
 */
private double salaryField;

private double yearlySalaryField;

/**
 * Constructor.
 *
 * @param firstName     employee's first name (assuming Western World naming)
 * @param lastName      employee's last name (assuming Western World naming)
 * @param initialSalary	employee's monthly initial salary (assuming USA dollars)
 */
public Employee(String firstName, String lastName, double initialSalary)
{
	firstNameField = firstName;
        lastNameField = lastName;
        
        if (firstName == null || firstName.equals("")) 
            firstNameField = null;
        if (lastName == null || lastName.equals("")) 
            lastNameField = null;
        
        if (initialSalary > 0) {
            salaryField = initialSalary;
            yearlySalaryField = initialSalary * 12;
        } else {
            System.out.println("The salary entered is negative and not a valid value.");
            salaryField = 0.00;
        }
}

/**
 * Print on the standard output the details (as specified elsewhere) of this
 * employee.
 */
public void displayValues()
{
    /* String values returned if first or last name are invalid */
    if (firstNameField == null)
        System.out.println("<Invalid first name>");
    if (lastNameField == null)
        System.out.println("<Invalid last name>");
}

/**
 * Getter for the employee's first name (assuming Western World naming).
 *
 * @return	employee's first name.
 */
public String getFirst()
{
	/* returns private instance variable firstNameField */
        
	return firstNameField;
}

/**
 * Setter for the employee's first name (assuming Western World naming).
 *
 * @param firstName	employee's first name (assuming Western World naming).
 */
public void setFirst(String firstName)
{
	/* Sets firstNameField with parameter firstName */
	firstNameField = firstName;
        
        if (firstName == null || firstName.equals("")) {
            firstNameField = null;
            displayValues();
        }
}

/**
 * Getter for the employee's last name (assuming Western World naming).
 *
 * @return	employee's last name.
 */
public String getLast()
{
	/* returns private instance variable lastNameField */
	return lastNameField;
}

/**
 * Setter for the employee's last name (assuming Western World naming).
 *
 * @param lastName	employee's last name (assuming Western World naming).
 */
public void setLast(String lastName)
{
	/* sets lastNameField with parameter lastName */
	lastNameField = lastName;
        
        if (lastName == null || lastName.equals("")) {
            lastNameField = null;
            displayValues();
        }
}

/**
 * Getter for the monthly salary.
 *
 * @return	the value of the monthly salary
 */
public double getMonthlySalary()
{
	/* Returns private instance variable salaryField */
	return salaryField;
}

/**
 * Setter for the monthly salary.
 * <p>
 * @param salary	the monthly salary
 */
public void setMonthlySalary(double salary)
{
	/* Sets salaryField with parameter salary */
	salaryField = salary;
        if (salary < 0)
            salaryField = 0;
}

/**
 * String identifying and describing this employee (as specified elsewhere).
 *
 * @return the string
 */
@Override
public String toString()
{
	/* Replace this statement by the needed code for this method. */
	return super.toString() + " " + firstNameField + " " + lastNameField;
}


}
