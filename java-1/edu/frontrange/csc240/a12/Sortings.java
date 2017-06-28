
package edu.frontrange.csc240.a12;

import java.util.Comparator;

import static edu.frontrange.csc240.a12.Fields.LAND_AREA;
import static edu.frontrange.csc240.a12.Fields.PERCENTAGE;
import static edu.frontrange.csc240.a12.Fields.TOTAL_AREA;
import static edu.frontrange.csc240.a12.Fields.WATER_AREA;

/**
 * Define the sort orderings that may be made of the AreaData instances.
 */
public enum Sortings
{
/**
 * The natural sort order of the state or territory (provided by default by the
 * Comparable interface). Hence, no comparator is provided.
 */
BY_NATURAL("Natural Sort Order (by name)", null),

/**
 * The (external) sort order by total area of the state or territory
 * (in ascending order).
 */
BY_TOTAL_AREA("Data Sorted by Total Area", new ByDoubleField(TOTAL_AREA)),

/**
 * The (external) sort order by total area of the state or territory
 * (in descending order).
 */
BY_TOTAL_AREA_REVERSED("Data Reverse Sorted by Total Area",
										new ByReverseDoubleField(TOTAL_AREA)),
/**
 * The (external) sort order by land area of the state or territory.
 */
BY_LAND_AREA("Data Sorted by Land Area", new ByDoubleField(LAND_AREA)),

/**
 * The (external) sort order by water area of the state or territory.
 */
BY_WATER_AREA("Data Sorted by Water Area", new ByDoubleField(WATER_AREA)),

/**
 * The (external) sort order by percentage of water area of the state or
 * territory.
 */
BY_PERCENTAGE("Data Sorted by Water Percentage", new ByDoubleField(PERCENTAGE));

/**
 * The comparator for this sorting.
 */
private final Comparator<AreaData> comparator;

/**
 * The heading for this sort order when printed.
 */
private final String listHeading;

/**
 * Private constructor for the enum values.
 *
 * @param listHeading	the heading to be used for printing
 * @param comparator	 the comparator to be used for external ordering
 */
private Sortings(String listHeading, Comparator<AreaData> comparator)
{
	this.listHeading = listHeading;
	this.comparator = comparator;
}

/**
 * @return the comparator
 */
public Comparator<AreaData> getComparator()
{
	return this.comparator;
}

/**
 * @return the listHeading
 */
public String getListHeading()
{
	return listHeading;
}
}
