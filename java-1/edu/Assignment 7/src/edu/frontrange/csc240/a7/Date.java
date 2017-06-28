
package edu.frontrange.csc240.a7;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.out;

/**
 * Project specific holder for dates.
 *
 * @author	Dr. Bruce K. Haddon, Instructor
 * @version	2.1, 2016-08-22, CSC-240 Assignment 6, per the Assignment Instructions
 */
public class Date
{
/**
 * Days for each month (except February).
 * <p>
 * Note there is no zeroth month, and the first entry in this array corresponds to
 * the month numbered 1.
 */
private static final int DAYS_PER_MONTH[] =
{
	31, 0, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
};

/**
 * Default value for the day if given day not acceptable.
 */
private static final int DEFAULT_DAY_VALUE = 1;

/**
 * Default value for the month if given month not acceptable.
 */
private static final int DEFAULT_MONTH_VALUE = 1;

/**
 * The month of February is the second month.
 */
private static final int FEBRUARY = 2;

/**
 * February days in a year that is a leap year.
 */
private static final int FEB_DAYS_LEAP_YEAR = 29;

/**
 * February days in a year that is not a leap year.
 */
private static final int FEB_DAYS_USUALLY = 28;

/**
 * Formatting format for American date format (interpreted as month/day/year).
 */
private static final String FORMAT_AMERICAN = "%1$d/%2$d/%3$d";

/**
 * Formatting format for ISO8601 date format (interpreted as year-month-day).
 */
private static final String FORMAT_ISO8601 = "%3$04d-%1$02d-%2$02d";

/**
 * The major cycle of years on which a year is a leap year.
 */
private static final int MAJOR_LEAP_CYCLE = 400;

/**
 * The minor cycle of years on which a year is a leap year (except for the cycle of
 * years that are skipped).
 */
private static final int MINOR_LEAP_CYCLE = 4;

/**
 * The cycle of years where a leap year is skipped out of the minor cycle.
 */
private static final int MINOR_LEAP_SKIP = 100;

/**
 * The number of months in a standard Gregorian year.
 */
private static final int MONTHS_IN_YEAR = 12;

/**
 * For the factory method, corresponding pattern to match the American format
 * (interpreted as month/day/year).
 */
private static final Pattern PATTERN_AMERICAN =
		Pattern.compile("(\\d{1,2})/(\\d{1,2})/(\\d{4})");

/**
 * For the factory method, corresponding pattern to match ISO8601 date format
 * (interpreted as year-month-day).
 */
private static final Pattern PATTERN_ISO8601 =
		Pattern.compile("(\\d{4})-(\\d{2})-(\\d{2})");

static
{
	assert DAYS_PER_MONTH.length == MONTHS_IN_YEAR :
			"Incorrect initialization of daysPerMonth array";
}
/**
 * Flag to show date string in ISO8601 format (or not)
 */
private boolean ISO8601;

/**
 * The day of the month (range is month and year dependent),
 */
private final int day;

/**
 * The month of the year (1 to MONTHS_IN_YEAR).
 */
private final int month;

/**
 * Year (full year number, including century).
 */
private final int year;

/**
 * The given date is verified, and if unusable day or month numbers are provided,
 * they are replaced by usable values (and error messages are produced).
 *
 * @param month	the month of the year (1 - MONTHS_IN_YEAR).
 * @param day		 the day of the month (range is month and year dependent)
 * @param year		year (full year number, including century).
 * @see	#checkMonth(int)
 * @see	#checkDay(int, int, int)
 */
public Date(int month, int day, int year)
{
	this.month = checkMonth(month);			// validate month
	this.year = year;						// could validate year (but do not)
	this.day = checkDay(this.year, this.month, day);	// validate day
}

/**
 * Set how {@code toString} will show the date.
 *
 * @param ISO8601	true if ISO8601 format is desired, false otherwise.
 * @return	this Date object
 */
public Date setISO8601(boolean ISO8601)
{
	this.ISO8601 = ISO8601;
	return this;
}

/**
 * Show string form in the selected format.
 *
 * @return	date of the form m/d/year or year-mm-dd.
 */
@Override
public String toString()
{
	return String.format(ISO8601 ? FORMAT_ISO8601 : FORMAT_AMERICAN,
															month, day, year);
}

/**
 * Factory method for creating dates from a String form. The form is expected to be
 * either ISO8601 format, or the standard American format. This method is using
 * regular expressions to recognize the form of the given string, a topic that will
 * be taken up in a later lesson.
 *
 * @param form	the string representing the date
 * @return	the corresponding Date object
 */
public static Date instance(String form)
{
	/* Regular expression matcher for the given date form. */
	Matcher m_ISO8601 = PATTERN_ISO8601.matcher(form);

	/* Regular expression matcher for the given date form. */
	Matcher m_american = PATTERN_AMERICAN.matcher(form);

	/* Depending on which format the given String form matches, create the Date
	   appropriately, and return that Date. */
	return m_ISO8601.matches() ?
			new Date(Integer.parseInt(m_ISO8601.group(2)),
					Integer.parseInt(m_ISO8601.group(3)),
					Integer.parseInt(m_ISO8601.group(1))).
			setISO8601(true) :
			m_american.matches() ?
					new Date(Integer.parseInt(m_american.group(1)),
							Integer.parseInt(m_american.group(2)),
							Integer.parseInt(m_american.group(3))) :
					new Date(DEFAULT_MONTH_VALUE, DEFAULT_DAY_VALUE, 1);
}

/**
 * Determines if a given year is a leap year, if (and only if) the year is the
 * number of a year within the Gregorian calendar (some date after October 15, 1582,
 * or later depending upon the country).
 *
 * @param year	the given year
 * @return	true if a leap year
 */
public static boolean isLeapYear(int year)
{
	return year % MAJOR_LEAP_CYCLE == 0 ||
			year % MINOR_LEAP_CYCLE == 0 && year % MINOR_LEAP_SKIP != 0;
}

/**
 * Utility method to confirm valid day value based on month and year.
 *
 * @param year		the given year
 * @param month	the given month
 * @param day		 the day number to test.
 * @return	a usable day number, based on the month and year. An error message is
 *         produced if this is not the given number.
 */
private static int checkDay(int year, int month, int day)
{
	int daysInThisMonth = DAYS_PER_MONTH[month - 1];

	/* Set days in February, depending upon whether year is a leap year or not. */
	if( month == FEBRUARY ) daysInThisMonth = isLeapYear(year) ?
				FEB_DAYS_LEAP_YEAR : FEB_DAYS_USUALLY;

	int result = DEFAULT_DAY_VALUE;
							// default value to maintain object in consistent state

	/* Check if day in range for the currently set month. */
	if( day > 0 && day <= daysInThisMonth )
		result = day;
	else
		out.printf("Invalid day (%d) set to %d.", day, result);

	return result;
}

/**
 * Utility method to confirm proper month value. Values of month are permitted to be
 * 1 to MONTHS_IN_YEAR inclusive. Month must be checked before day, as the month
 * value is used by the checkDay method.
 *
 * @param month		a month number to test
 * @return			a usable month number. An error message is produced if this is
 *					not the given number.
 */
private static int checkMonth(int month)
{
	int result = DEFAULT_MONTH_VALUE;
							// default value to maintain object in consistent state
	if( month > 0 && month <= MONTHS_IN_YEAR )			// validate month
		result = month;
	else												// month is invalid
		out.printf("Invalid month (%d) set to %d.", month, result);

	return result;
}

///**
// * Main method to conduct some tests on the data class.
// *
// * @param args		not used.
// */
//public static void main(String[] args)
//{
//	Date d1 = new Date(2, 29, 2008);
//	out.println(d1.toString());
//	out.println(d1.setISO8601(true).toString());
//	out.println(d1.setISO8601(false).toString());
//
//	Date d2 = Date.instance(d1.toString());
//	out.println(d2.toString());
//	Date d3 = Date.instance(d1.setISO8601(true).toString());
//	out.println(d3.toString());
//
//	Date d4 = new Date(5, 31, 2009);
//	out.println(d4.toString());
//	d4.setISO8601(true);
//	out.println(d4.toString());
//	d4.setISO8601(false);
//	out.println(d4.toString());
//
//	Date d5 = Date.instance(d4.toString());
//	out.println(d5.toString());
//	d4.setISO8601(true);
//	Date d6 = Date.instance(d4.toString());
//	out.println(d6.toString());
//
//	Date d7 = new Date(6, 31, 2010);
//	out.println();
//	Date d8 = new Date(13, 12, 2010);
//	out.println();
//	Date d9 = new Date(2, 29, 2010);
//	out.println();
//}
}
