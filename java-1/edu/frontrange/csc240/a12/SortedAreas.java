package edu.frontrange.csc240.a12;

import edu.frontrange.csc240.util.InputData;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static edu.frontrange.csc240.a12.Sortings.BY_NATURAL;
import static edu.frontrange.csc240.a12.Sortings.BY_TOTAL_AREA_REVERSED;
import static edu.frontrange.csc240.a12.Sortings.BY_WATER_AREA;
import static java.lang.String.format;
import static java.lang.System.out;

/**
 * This is an application to read the file named &ldquo;Areas.csv&rdquo;, and
 * then to produce three listings, one by natural ordering (alphabetical by
 * state or territory), one by decreasing total area, and one by increasing
 * water area.
 *
 * @author	Dr. Bruce K. Haddon, 2016-08-22
 * @version	1.2, 2016-08-22, CSC-241, Assignment 5 (see Instructions)
 */
public class SortedAreas
{

private static final Sortings[] REQUESTED = {
			BY_NATURAL,
			BY_TOTAL_AREA_REVERSED,
			BY_WATER_AREA};
/**
 * Do the work of the SortedAreas program.
 */
public void run()
{
	/* List to hold the input data. */
	List<AreaData> areaDataList = readInput("Areas.csv");

	/* Find and run method for each of the needed sorted lists, print that list,
	   ordered by the appropriate comparator. */
	for( Sortings sorting : REQUESTED )
	{
		/* Set up a sorted set, implemented by a TreeSet that has the
		   appropriate comparator. This statement uses the "diamond" operator,
		   since it is possible for the compiler to work out the required
		   generic type. */
		Set<AreaData> sorted = new TreeSet<>(sorting.getComparator());

		/* Add to the tree set the data (giving the specified sort order). */
		sorted.addAll(areaDataList);

		/* Print a title, just to identify the output table. */
		out.println(sorting.getListHeading());

		/* Print headings to identify the columns of the output. */
		out.println("     " + AreaData.toHeadingString());

		/* For each entry in the sorted set, print it out. */
		int counter = 0;
		for( AreaData areaData : sorted )
			out.println(format("%3d  ", ++counter) + areaData.toString());
		out.println();								// blank line
	}
}

/**
 * Copy the data using the given file, create an AreaData object for each line,
 * and put that object into the list that is returned.
 *
 * @param filename	file from which to read input, one Area definition per line
 * @return			list of all the area data
 */
private List<AreaData> readInput(String filename) throws IllegalStateException,
		NumberFormatException
{
	/* This statement uses the "diamond" operator, since it is possible for
	   the compiler to work out the required generic type. */
	final List<AreaData> result = new LinkedList<>();
	try( InputData inputData =
			new InputData(filename, AreaData.class, null,
					Fields.values().length, ",") )
	{
		/* Iterate through each of the lines of values. */
		for( String[] values : inputData )
		{
			/* Create a new AreaData object, and add it to the list. */
			AreaData next = new AreaData(values);
//			out.println(next.toString());			// for debugging
			result.add(next);
		}
	}
	return result;
}

/**
 * Main entry point.
 * <p>
 * Execute:</p>
 * <pre>java edu.frontrange.csc240.a12.SortedAreas</pre>
 *
 * @param args	unused
 */
public static void main(String... args)
{
	new SortedAreas().run();
}
}
