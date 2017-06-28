
package edu.frontrange.csc240.a12;

/**
 * Define the fields expected in the input. These definitions must be in
 * the order of the expected values.
 */
public enum Fields
{
/**
 * Name of the entity (state or territory).
 */
NAME,

/**
 * Total area of the entity (state or territory).
 */
TOTAL_AREA,

/**
 * Land area of the entity (state or territory).
 */
LAND_AREA,

/**
 * Water area of the entity (state or territory).
 */
WATER_AREA,

/**
 *  Water area as a percentage of total area of the entity (state or territory).
 */
PERCENTAGE;

/**
 * @return the field heading
 */
@SuppressWarnings("NestedAssignment")
public String getFieldHeading()
{
	char[] fieldName = this.name().toCharArray();
	boolean capitalize = true;
	for( int i = 0; i != fieldName.length; ++i )
	{
		if( !capitalize ) fieldName[i] = Character.toLowerCase(fieldName[i]);
		if( (capitalize = fieldName[i] == '_') ) fieldName[i] = ' ';
	}
	return new String(fieldName);
}
}
