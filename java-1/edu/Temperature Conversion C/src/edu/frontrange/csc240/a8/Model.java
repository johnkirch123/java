
package edu.frontrange.csc240.a8;

import java.util.Observable;

import static java.lang.Math.abs;

/**
 * This class acts as the &ldquo;model&rdquo; for the computation of the
 * temperatures, in the MVC (model-view-controller) design pattern used here
 * to create this application. All of the information about temperatures
 * to be converted is contained in this class.
 * <p>
 * The model uses the {@code Scale} class to do the actual
 * conversions between the source temperature scale and the target temperature
 * scale.
 *
 * @author	Dr. Bruce K. Haddon, Instructor
 * @version	2.0, 2016-10-15, Assignment 8, Exercise 12.13
 */
public class Model extends Observable
{
/**
 * Indicator of whether the temperature set as the input is known to be good, or
 * initially, the user has not entered any temperature.
 */
private boolean invalidTemperature = true;


private final Scale[] scales;

/**
 * The source temperature scale, initialized to the initial setting.
 */
private Scale sourceScale;

/**
 * The target temperature scale, initialized to the initial setting.
 */
private Scale targetScale;

/**
 * Temperature input by the user.
 */
private double temperature;

/**
 * @return the sourceScale
 */
public Scale getSourceScale()
{
	return sourceScale;
}

/**
 * Constructor. Set initial values.
 */
public Model()
{
	scales = Scale.getSerialOrder();
	sourceScale = scales[0];
	targetScale = scales[0];
}

/**
 * Get the defined scales in the defined serial order.
 *
 * @return	the scales
 */
@SuppressWarnings("ReturnOfCollectionOrArrayField")
public Scale[] getScales()
{
	return scales;
}

/**
 * @param sourceScale the sourceScale to set
 * @throws IllegalStateException if the conversion cannot succeed because
 *						the temperature is less than absolute zero
 * @throws IllegalArgumentException if the current temperature value is not
 *						set to any value
 */
public void setSourceScale(Scale sourceScale)
								throws IllegalStateException
{
	this.sourceScale = sourceScale;
	validate();
}

/**
 * Get the source temperature that has been set
 *
 * @return the temperature that has been set
 * @throws IllegalArgumentException if the source temperature is not valid
 */
public double getSourceTemperature()
{
	if( invalidTemperature ) throw new IllegalArgumentException();
	return temperature;
}

/**
 * Set the source temperature (the temperature to be converted).
 *
 * @param temperature	the temperature to set
 * @throws IllegalStateException    if the conversion cannot succeed because the
 *                                  temperature is less than absolute zero
 * @throws IllegalArgumentException if the current temperature value is not set
 *                                  to any value
 */
public void setSourceTemperature(double temperature)
		throws IllegalStateException, IllegalArgumentException
{
	if( abs(temperature) == 0.0 ) temperature = 0.0;
											// remove any negative zero results
	this.temperature = temperature;
	/* Assume the given temperature is good. */
	invalidTemperature = false;
	validate();
}

/**
 * @return the targetScale
 */
public Scale getTargetScale()
{
	return targetScale;
}

/**
 * @param targetScale the targetScale to set
 * @throws IllegalStateException    if the conversion cannot succeed because the
 *                                  current temperature is now less than
 *                                  absolute zero
 * @throws IllegalArgumentException if the current temperature value is not set
 *                                  to any value
 */
public void setTargetScale(Scale targetScale)
		throws IllegalStateException
{
	this.targetScale = targetScale;
	validate();
}

/**
 * @return the target (converted) temperature
 * @throws IllegalStateException    if the conversion cannot succeed because of.
 *                                  the temperature being less than absolute
 *                                  zero
 * @throws IllegalArgumentException if the current temperature value is not set
 *                                  to any value
 */
public double getTargetTemperature() throws IllegalStateException,
		IllegalArgumentException
{
	if( invalidTemperature ) throw new IllegalArgumentException();
	double result =  targetScale.convert(temperature, sourceScale);
	if( abs(result) == 0.0 ) result = 0.0;		// remove any negative zero results
	return result;
}

/**
 * Mark model invalid while changes are happening
 */
public void setInvalid()
{
	invalidTemperature = true;
	setChanged();
	notifyObservers();
}

/**
 * This method is used internally to validate new values.
 *
 * @throws IllegalStateException if the conversion cannot succeed because
 *						the temperature is less than absolute zero
 * @throws IllegalArgumentException if the current temperature value is not
 *						set to any value
 */
private void validate() throws IllegalStateException, IllegalArgumentException
{
	/* If no ostensibly valid temperature is present, then do not do any check.
	   Otherwise check that the temperature can be converted. */
	if( invalidTemperature ) return;
	try
	{
		/* By converting the temperature, it is ensured that the temperature is
		   an acceptable value, in the given source and target scales. The
		   conversion will throw an  exception if the combination is not
		   acceptable. Note that even if the value is not acceptable, a change
		   is still noted. */
		getTargetTemperature();
	}
	catch( IllegalStateException | IllegalArgumentException ex)
	{
		/* Catch the exception so that the flag can be set, and then throw it
		   again. */
		invalidTemperature = true;
		throw ex;
	}
	finally
	{
		setChanged();
		notifyObservers();
	}
}

///**
// * Main entry point: for testing of the model.
// *
// * @param args		not used
// */
//@SuppressWarnings({ "CallToThreadDumpStack", "CallToPrintStackTrace" })
//public static void main(String... args)
//{
//	Model model = new Model();
//	try
//	{
//	for( Scale scale1 : Scale.values() )
//		for( Scale scale2 : Scale.values() )
//			for( double temperature :
//					new double[]{0.0, 10.0, 32.0, 33.0, 100.0, 150.0, 212.0 })
//			{
//				model.setSourceTemperature(temperature);
//				if( temperature != model.getSourceTemperature())
//					System.out.println("temperature = "  + temperature +
//								" Model = " + model.getSourceTemperature());
//				model.setSourceScale(scale1);
//				if( scale1 != model.getSourceScale())
//					System.out.println("source scale  = "  + scale1 +
//								" Model = " + model.getSourceScale());
//				model.setTargetScale(scale2);
//				if( scale2 != model.getTargetScale())
//					System.out.println("target scale  = "  + scale2 +
//								" Model = " + model.getTargetScale());
//				System.out.printf("%g %s = %g %s%n", temperature, scale1,
//						model.getTargetTemperature(), scale2);
//			}
//	}
//	catch( IllegalStateException | IllegalArgumentException e )
//	{
//		e.printStackTrace();
//	}
//}
}
