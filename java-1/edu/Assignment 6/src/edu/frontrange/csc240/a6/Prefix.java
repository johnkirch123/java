package edu.frontrange.csc240.a6;

/**
 * This is a supportive enum to associate different prefixTitles with the prefixes.
 * This enum is used by other classes to set the department of which to use other 
 * information for the community college, i.e. - course, section, students...
 * 
 * @author John Kirch, S01581562
 * @version 1.0, 03-02-2017, CSC-240 Assignment 6
 */
public enum Prefix 
{
CIS("Computer Information Systems"), 
CSC("Computer Science"),
CNG("Computer Networking"),
CWB("Computer Web-based");

private final String prefixTitle;
/**
 * Constructor to set the enum value with the String type.
 * 
 * @param prefixTitle   string value used to set enum state
 */
Prefix (String prefixTitle) 
{
    this.prefixTitle = prefixTitle;
    
}
/**
 * Returns a String type of the current prefixTitle.
 * 
 * @return prefixTitle
 */
public String getTitle()
{
    return prefixTitle;
}
}
