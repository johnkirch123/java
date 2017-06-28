
package edu.frontrange.csc240.a8;

import edu.frontrange.util.InputData;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * This class is a simple converter for temperature scales, where the scales are
 * defined as class values. The class values are determined by the file
 * "scales.txt".
 * <p>
 * For each scale, two conversions are provided as members of each class, thus
 * avoiding a long and complex switch statement. The discrimination relies of
 * the method dispatch of the Java language and the JVM. </p>.
 * <p>
 * The toKelvin method of each instance converts any given temperature in one of
 * the scales to the corresponding temperature in the Kelvin scale. The
 * fromKelvn method converts from any given temperature in the Kelvin scale to
 * one of the corresponding temperature values in the given scale.</p>
 * <p>
 * Used together, and temperature on one scale can be converted to a temperature
 * in any other scale. Given an number of scales, <em>n</em>, this involves just
 * 2 * <em>n</em> conversions (including the identity conversions) rather than
 * the <i>n * n</i> conversion calculations that would otherwise be required
 * (including the identity conversions). </p>
 * <p>
 * The data for the conversions are read from a file. Additional scales may be
 * added at will. The data is comma-separated, and consists of the following
 * values, in order: </p>
 * <ol>
 * <li>Relative ordinal value for the position of the button on the view;</li>
 * <li>Name of the scale as it is to be displayed;</li>
 * <li>The value of absolute zero when represented in this scale; and, </li>
 * <li>	A pair of values (comma-separated) giving some other (preferably distant
 * point from absolute zero) translation from Kelvin to a temperature on this
 * scale, <i>e.g.</i>, for Celsius, 373.15,100.0.</li>
 * </ol>
 *
 * @author	Dr. Bruce K. Haddon, Instructor
 * @version	1.5, 2016-08-22, Assignment 8, Exercise 12.13
 */
@SuppressWarnings("AccessingNonPublicFieldOfAnotherObject")
public final class Scale
{
/**
 * The value in the Kelvin scale of nominal boiling point of water.
 */
private static final double BOILING_POINT_K = 373.1500;		// Kelvin

/**
 * The number of entries per line of a scales file.
 */
private static final int ENTRIES_PER_LINE = 4;

/**
 * List of scales, in declaration order;
 */
private static final Scale[] VALUES;

/* Initialize the constant values for the scale conversions. This is done by
   reading the entries from the "scales.txt" resource file, and creating a Scale
   object for each entry. */
static
{
	/* The scales information is in the file "scales.txt". */
	try( InputData inputData = new InputData("scales.txt", Scale.class, null,
															ENTRIES_PER_LINE, ",") )
	{
		/* List of scales, in the input order. */
		List<Scale> list = new LinkedList<>();

		/* Read through the file of scale definitions. */
		for( String[] items : inputData )
		{
			/* Index. */
			int i = 0;

			/* The first item is the relative serial position of the
			   corresponding button. It will work out that if two line have the
			   same value of relative serial position, the input order is
			   preserved. */
			int serialposition = Integer.parseInt(items[i++]);

			/* The second item is the name of the scale. */
			String string = items[i++];

			/* The third item is the value of absolute zero on this scale. */
			double absoluteZero = Double.parseDouble(items[i++]);

			/* The next values represent the nominal boiling point on this scale,
			   i.e., the value that corresponds to 373.1500 Kelvin. */
			double boiling = Double.parseDouble(items[i++]);

			/* Compute the conversion ratio for the size of the degree. */
			double ratio = (boiling - absoluteZero) / BOILING_POINT_K;

			/* Create a new Scale instance. */
			Scale scale = new Scale(serialposition, string, ratio, absoluteZero);

			/* Create and add a new scale instance to the list. */
			if( !list.add(scale) )
				assert false : "Error in adding new scale";
		}

		/* Create the values array by converting the List to an array. The
		   values array has each of the scale values in declaration order, i.e.,
		   in the order in which they appear in the file. */
		VALUES = list.toArray(new Scale[list.size()]);

		/* Re-order the declarations into the specified relative order. */
		List<Scale> ordinalOrder = new LinkedList<>();

		/* Equal relative order numbers are kept in declaration order. */
		for( Scale scale : VALUES )
		{
			int j = 0;
			for( Scale next : ordinalOrder )
				if( next.serial <= scale.serial ) ++j;
				else break;
			/* Put this one in the correct place, which may be at the end. */
			ordinalOrder.add(j, scale);
		}

		/* Close up the ordinal values (turning the relative ordinals into a
		   closed ordinal set of values. */
		int ordinal = 0;
		for( Scale scale : ordinalOrder ) scale.serial = ordinal++;
	}
}
/**
 * Ratio of the size of the degree of this scale to Kelvin.
 */
private final double ratio;

/**
 * The serial position for this scale.
 */
private int serial;

/**
 * The string to be shown for this scale toString.
 */
private final String string;

/**
 * Value of zero degrees Kelvin on this scale.
 */
private final double zeroK;

/**
 * Constructor. This is private to prevent instantiations other that the ones
 * generated internally by reading the scale.txt file.
 *
 * @param serial	serial position in the selector array
 * @param string	the string to be shown for this scale toString
 * @param ratio		ratio of the size of the degree of this scale to Kelvin
 * @param zeroK		value of zero degrees Kelvin on this scale
 */
private Scale(int serial, String string, double ratio, double zeroK)
{
	this.serial = serial;
	this.string = string;
	this.ratio = ratio;
	this.zeroK = zeroK;
}

/**
 * Convert a temperature from a given scale to this scale.
 *
 * @param t		   the given temperature
 * @param scale the temperature scale of that temperature
 * @return	the value of that temperature in the requested units
 * @throws	IllegalStateException if the temperature is less than absolute zero
 */
public double convert(double t, Scale scale) throws IllegalStateException
{
	/* Convert the original temperature into Kelvin. */
	t = scale.toKelvin(t);
	/* If the result is negative, then this is an unacceptable temperature. */
	if( t < 0.0 ) throw new IllegalStateException();
	/* Return the Kelvin temperature converted to the desired temperature. */
	return round(fromKelvin(t));
}

/**
 * Convert the temperature in degrees Kelvin to degrees in this scale.
 *
 * @param t	the temperature in Kelvin to convert
 * @return	that temperature in this scale
 */
public double fromKelvin(double t)
{
	return ratio * t + zeroK;
}

/**
 * Convert the temperature in this scale to Kelvin degrees.
 *
 * @param t	the temperature to convert
 * @return	that temperature in Kelvin
 */
public double toKelvin(double t)
{
	return (t - zeroK) / ratio;
}

/**
 * Get the string for this temperature scale.
 *
 * @return	the string of this temperature scale
 */
@Override
public String toString()
{
	return string;
}

/**
 * Get (a copy of) the scale instances in serial order.
 *
 * @return	an array of the scales in the given serial order
 */
public static Scale[] getSerialOrder()
{
	Scale[] ordinalOrder = new Scale[Scale.values().length];
	for( Scale scale : Scale.values() )
		ordinalOrder[scale.serial] = scale;

	return ordinalOrder;
}

/**
 * Return (a copy of) the array of all the instances of this class. This method
 * replicates the functionality of the same name found in enum classes.
 *
 * @return	(a copy of) the array of all the instances of this class
 */
public static Scale[] values()
{
	return Arrays.copyOf(VALUES, VALUES.length);
}

/**
 * Round the temperature to five digits (to wash out rounding errors).
 *
 * @param t	the unrounded value
 * @return	the value rounded
 */
private static double round(double t)
{
	return Math.rint(t * 10000.0) / 10000.0;
}

///**
// * Main entry point: for testing of the converter.
// */
//public static void main(String... args)
//{
//	try
//	{
//	for( Scale scale1 : Scale.values() )
//		for( Scale scale2 : Scale.values() )
//			for( double temperature :
//					new double[]{0.0, 10.0, 32.0, 33.0, 100.0, 150.0, 212.0 })
//			{
//				System.out.printf("%g %s = %g %s%n", temperature, scale1,
//						scale2.convert(temperature, scale1), scale2);
//			}
//	}
//	catch( Exception e)
//	{}
//}
}
