
package edu.frontrange.csc240.a12;

import java.util.EnumMap;

import static edu.frontrange.csc240.a12.Fields.NAME;
import static java.lang.Math.log10;
import static java.lang.Math.max;
import static java.lang.String.format;

/**
 * This is the class (data access object) used to record and provide the needed
 * function on the data items represented by the lines of the original file. The
 * fields of this object represent each of the input values.
 * <p>
 * Since the <code>compareTo</code> method defines a meaning of equality for
 * this class, the <code>equals</code> method and the <code>hashCode</code>
 * method have been overridden in order to be consistent to the results provided
 * by <code>compareTo</code>.
 *
 * @author	Dr. Bruce K. Haddon, Instructor
 * @version	1.2, 2016-08-22, CSC-241, Assignment 5 (see Instructions)
 */
public class AreaData implements Comparable<AreaData>
{
/**
 * The actual format strings to be used by toString for the field values.
 * Filled with values during each constructor initialization.
 */
private static final EnumMap<Fields, String> FORMATS = new EnumMap<>(Fields.class);

/**
 * Extra space for headings other than the name field.
 */
private static final int HEADING_SEPARATION = 2;

/**
 * Minimum field width for numeric data
 */
private static final int MINIMUM = 5;

/**
 * The widths of each column in the String created by toString.
 */
private static final EnumMap<Fields, Integer> WIDTHS = new EnumMap<>(Fields.class);

/**
 * Initialize the static WIDTHS enum map to hold zero values.
 */
static
{
	for( Fields field : Fields.values() ) WIDTHS.put(field, 0);
}

/**
 * Record of the Double values for this named state or territory.
 */
private final EnumMap<Fields, Double> doubles = new EnumMap<>(Fields.class);

/**
 * The name of the state or territory without any case differences.
 */
private final String nameCaseless;

/**
 * Record of the String values for this named state or territory.
 */
private final EnumMap<Fields, String> strings = new EnumMap<>(Fields.class);

/**
 * @param data	an array of strings representing the five items of data from the
 *             input file.
 * @throws NumberFormatException if any of the numeric fields are not actually
 *                               representations of double values
 */
public AreaData(String... data)
{
	/* Assert to check that the data is of the expected number of items. */
	assert data.length == Fields.values().length : "Incorrect number of fields";

	for( Fields field : Fields.values() )
	{
		int width;
		String formatString;
		if( field == NAME )
		{
			/* Get the name value and store it away. */
			String value = data[field.ordinal()];
			strings.put(field, value);
			/* Get the needed width of the field to hold the name. */
			width = max(value.length(), field.getFieldHeading().length());
			formatString = "s";
		} else
		{
			/* If the value is of the wrong form, allow the NumberFormatException
			   to be thrown. */
			Double value = Double.parseDouble(data[field.ordinal()]);
			/* Assertion to check value given is positive.  */
			assert value.compareTo(0.0) >= 0 :
					"invalid " + field.name() + " value=" + value.toString();
			/* Get the field value and store it away. */
			doubles.put(field, value);
			/* Get needed width of the field to hold the heading or value. */
			width = max((int) log10(value) + MINIMUM,
					field.getFieldHeading().length() + HEADING_SEPARATION);
			formatString = ".2f";
		}
		/* Keep the widest value seen, and record the corresponding format. */
		if( width > WIDTHS.get(field) )
		{
			WIDTHS.put(field, width);
			FORMATS.put(field, "%" + width + formatString);
		}
	}
	/* Optimization: to avoid doing this every time a comparison is made. */
	this.nameCaseless = strings.get(NAME).toUpperCase().toLowerCase();
}

/**
 * The compareTo method defines the natural ordering of the AreaData. In this
 * case, the natural ordering is defined to be an alphabetic ordering of the
 * name of the state or territory, ordered ignoring letter case.
 *
 * @param that	the AreaData to which the comparison of this is being made
 * @return	a negative integer, zero, or a positive integer as this object is
 *         less than, equal to, or greater than the specified object.
 */
// side effect is benign
@Override
@SuppressWarnings("AccessingNonPublicFieldOfAnotherObject")
public int compareTo(AreaData that)
{
	return this.nameCaseless.compareTo(that.nameCaseless);
}

/**
 * Implementation of equals that meets the requirements of equals as defined in
 * Object, and is consistent with the definition of compareTo.
 *
 * @param that	the object to which the comparison is to be made
 * @return		true if and only if the given object is equal to this object
 */
@Override
public boolean equals(Object that)
{
	return this == that ||
		(that instanceof AreaData &&
				this.compareTo(this.getClass().cast(that)) == 0);
}

/**
 * The value of the named field as a Double value.
 *
 * @param field	enum value designating the appropriate field
 * @return	value of the named field
 */
public Double getDoubleField(Fields field)
{
	return doubles.get(field);
}

/**
 * The value of the named field as a String
 *
 * @param field	enum value designating the appropriate field
 * @return		value of the named field represented as a String using the
 *				format for that field.
 */
public String getStringField(Fields field)
{
	return format(FORMATS.get(field),
			(field == NAME ? strings : doubles).get(field));
}

/**
 * Implementation of hashCode that meets the requirement for hashCode in Object,
 * which is that instances that are equal must have equal hashCode values.
 *
 * @return	a consistent hash code value
 */
@Override
public int hashCode()
{
	/* Use the case-free version of the name to get the hashCode. */
	return nameCaseless.hashCode();
}

/**
 * A String to represent the values of the area data, laid out for easy listing.
 *
 * @return a String, representing the values of the area data.
 */
@Override
public String toString()
{
	StringBuilder result = new StringBuilder();
	for( Fields field : Fields.values() ) result.append(getStringField(field));
	return result.toString();
}

/**
 * Generate a heading for the tables of values.
 *
 * @return		the appropriate heading String
 */
public static String toHeadingString()
{
	StringBuilder result = new StringBuilder();
	for( Fields field : Fields.values() )
		result.append(
				format("%" + (WIDTHS.get(field)) + "s", field.getFieldHeading()));
	return result.toString();
}

/**
 * Main method to lightly test the implementation of AreaData.
 *
 * @param args	unused
 */
//@SuppressWarnings( { "ObjectEqualsNull", "IncompatibleEquals" } )
//public static void main(String... args)
//{
//	AreaData areadata1 = new AreaData("Australia",
//			"7617.93", "7841.75", "76.18", "1.00");
//	AreaData areadata2 = new AreaData("New Zealand",
//			"268.68", "263.04", "5.64", "2.10");
//
//	System.out.println(areadata1);
//	System.out.println(areadata2);
//
//	System.out.println("Australia hashcode = " + areadata1.hashCode());
//	System.out.println("New Zealand hashcode = " + areadata2.hashCode());
//	System.out.println("Australia hashcode = " + areadata1.hashCode());
//	System.out.println("New Zealand hashcode = " + areadata2.hashCode());
//
//	System.out.println("Expected \"false\": " + areadata1.equals(areadata2));
//	System.out.println("Expected \"true\": " + areadata1.equals(areadata1));
//	System.out.println("Expected \"false\": " + areadata1.equals(null));
//	System.out.println("Expected \"false\": " + areadata2.equals("x"));
//	System.out.println("Expected -13: " + areadata1.compareTo(areadata2));
//
//	System.out.println("Expected -1: " +
//			new ByReverseTotalArea().compare(areadata1, areadata2));
//	System.out.println("Expected -1: " +
//			new ByPercentage().compare(areadata1, areadata2));
//
//}
}
