
package edu.frontrange.csc240.a8;

/**
 * This class is a simple converter for temperature scales, where the scales
 * are defined as enumeration constants. It also specifies the order in which
 * the scales are to be presented to any user.
 * <p>
 * For each scale, two conversions are provided as members of each class, thus
 * avoiding a long and complex switch statement. The discrimination relies of
 * the method dispatch of the Java language and the JVM. </p>.
 * <p> The toKelvin method of each instance converts any given temperature in
 * one of the scales to the  corresponding temperature in the Kelvin scale.
 * The fromKelvn method converts from any given temperature in the Kelvin scale
 * to one of the corresponding temperature values in the given scale.</p>
 * <p>
 * Used together, and temperature on one scale can be converted to a temperature
 * in any other scale. Given an number of scales, <em>n</em>, this involves just
 * 2 * <em>n</em> conversions (including the identity conversions) rather than
 * the <i>n * n</i>  conversion calculations that would otherwise be required
 * (including the identity conversions).
 *
 * @author	Dr. Bruce K. Haddon, Instructor
 * @version	1.3, 2016-08-22, Assignment 8, Exercise 12.13
 */

@SuppressWarnings("AccessingNonPublicFieldOfAnotherObject")
public enum Scale
{
/**
 * The Celsius scale, and its title.
 */
CELSIUS("Celsius", 1)
{
@Override
public double toKelvin(double t) { return t + C_TO_K_DIFFERENCE; }
@Override
public double fromKelvin(double t) { return t - C_TO_K_DIFFERENCE; }
},

/**
 * The Delisle scale, and its title.
 */
DELISLE("Delisle", 4)
{
@Override
public double toKelvin(double t) { return D_ZERO_POINT - t / K_TO_D_CONVERSION; }
@Override
public double fromKelvin(double t) { return (D_ZERO_POINT - t) * K_TO_D_CONVERSION; }
},

/**
 * The Fahrenheit scale, and its title.
 */
FAHRENHEIT("Fahrenheit", 0)
{
@Override
public double toKelvin(double t)
{
	return (t - C_TO_F_EQUIPOINT) / C_TO_F_CONVERSION +
			C_TO_F_EQUIPOINT + C_TO_K_DIFFERENCE;
}

@Override
public double fromKelvin(double t)
{
	return (t - C_TO_K_DIFFERENCE - C_TO_F_EQUIPOINT) *
			C_TO_F_CONVERSION + C_TO_F_EQUIPOINT;
}
},

/**
 * The Kelvin scale, and its title.
 */
KELVIN("Kelvin", 2)
{
@Override
public double toKelvin(double t) { return t; }
@Override
public double fromKelvin(double t) { return t; }
},

/**
 * The Rankine scale, and its title.
 */
RANKINE("Rankine", 3)
{
@Override
public double toKelvin(double t) { return t / C_TO_F_CONVERSION; }
@Override
public double fromKelvin(double t) { return t * C_TO_F_CONVERSION; }
};

/**
 * Constant for converting Celsius (Kelvin) degree steps into Fahrenheit steps.
 */
private static final double C_TO_F_CONVERSION = 9.0 / 5.0;

/**
 * Constant for the difference between Celsius and Kelvin.
 */
private static final double C_TO_K_DIFFERENCE = 273.15;

/**
 * The numeric value at which Celsius and Fahrenheit are the same.
 */
private static final double C_TO_F_EQUIPOINT = -40.0;

/**
 * Zero point on Delisle scale in Kelvin
 */
private static final double D_ZERO_POINT = C_TO_K_DIFFERENCE + 100;

/**
 * Constant for converting Kelvin (Celsius) degree steps into DeLisle steps.
 */
private static final double K_TO_D_CONVERSION = 1.5;


/**
 * The string to be shown for this scale toString.
 */
private final String string;

/**
 * Serial position in the selector array.
 */
private int serial;

/* Check that no serial value is repeated, and all are within the count of the
   number of scales. */
static
{
	/* Array for checking, set initially to all boolean false values. */
	boolean[] serialCheck = new boolean[Scale.values().length];

	/* For each scale in the enumeration ... */
	for( Scale scale: Scale.values() )
	{
		/* Assert that this value has not already been seen. The assignment of
		   the true value also checks that the serial value given lies within
		   the values length. */
		assert !serialCheck[scale.serial]: "Serial value repeated in Scale";
		serialCheck[scale.serial] = true;
	}
}

/**
 * Constructor.
 *
 * @param string		the title to be shown for this scale toString
 * @param serial		position in the selector array
 */
Scale(String string, int serial)
{
	this.string = string;
	this.serial = serial;
}

/**
 * Convert the temperature in this scale to Kelvin degrees. Each enumeration
 * value must implement this method.
 *
 * @param t		the temperature to convert
 * @return		that temperature in Kelvin
 */
public abstract double toKelvin(double t);

/**
 * Convert the temperature in degrees Kelvin to degrees in this scale. Each
 * enumeration value must implement this method.
 *
 * @param t		the temperature in Kelvin to convert
 * @return		that temperature in this scale
 */
public abstract double fromKelvin(double t);

/**
 * Convert a temperature from a given scale to this scale.
 *
 * @param t		the given temperature
 * @param scale	the temperature scale of that temperature
 * @return		the value of that temperature in the requested units
 * @throws		IllegalStateException  if the temperature is less than absolute
 *						zero
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
 * Get (a copy of) the scale instances in serial order.
 *
 * @return		an array of the scales in the given serial order
 */
public static Scale[] getSerialOrder()
{
	Scale[] ordinalOrder = new Scale[Scale.values().length];
	for( Scale scale : Scale.values() )
		ordinalOrder[scale.serial] = scale;

	return ordinalOrder;
}

/**
 * Get the string for this temperature scale.
 *
 * @return		the string for this temperature scale
 */
@Override
public String toString() { return string; }

/**
 * Round the temperature to five digits (to wash out rounding errors).
 *
 * @param t		the unrounded value
 * @return		the value rounded
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
