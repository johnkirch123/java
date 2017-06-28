
package edu.frontrange.csc240.a12;

import java.util.Comparator;

/**
 * A comparator to reverse order by the given double field of a state or 
 * territory. If the double values are equal, then the ordering is done by the 
 * natural ordering of the AreaData object.
 * <p>
 * Note: this comparator imposes orderings that are inconsistent with equals.
 * 
 * @author	Dr. Bruce K. Haddon, Instructor
 * @version	1.2, 2016-08-22, CSC-241, Assignment 5 (see Instructions)
 */
public class ByReverseDoubleField implements Comparator<AreaData>
{
/**
 * The field to be accessed.
 */
private final Fields field;

/**
 * Constructor.
 * 
 * @param field		the field to use for the comparison
 */
public ByReverseDoubleField(Fields field)
{
	this.field = field;
}

/**
 * Compares its two arguments for order. Returns a negative integer, zero, or a
 * positive integer as the first argument is less than, equal to, or greater
 * than the second.
 *
 * @param first		the first AreaData object to be compared
 * @param second	the second AreaData object to be compared
 * @return			a negative integer, zero, or a positive integer as the first 
 *					argument is greater than, equal to, or less than the second.
 */
@Override
public int compare(AreaData first, AreaData second)
{
	int result = 
			first.getDoubleField(field).compareTo(second.getDoubleField(field));
	return result != 0 ? -result : first.compareTo(second);
}
}

