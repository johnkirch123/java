
package edu.frontrange.csc240.a4.guess;

import static java.lang.Math.abs;
import static java.lang.Math.min;

/**
 * Interface for determining an ordinal string in English.
 *
 * @author	Dr. Bruce K. Haddon
 * @version	1.0, 2016-08-22
 */
public interface Ordinal
{
/**
 * The SPECIAL value, plus or minus 1, always gets the "th" SUFFIX.
 */
static final int SPECIAL = 12;

/**
 * In the normal course (specials aside) suffixes repeat every DECIMAL_BASE.
 */
static final int DECIMAL_BASE = 10;

/**
 * The special cases, see SPECIAL, recur on a cycle of every RECURRENCE.
 */
static final int RECURRENCE = 100;

/**
 * In the normal course, every value from the TH_LIMIT upward gets the "th" SUFFIX.
 */
static final int TH_LIMIT = 4;

/**
 * The list of suffixes.
 */
static final String[] SUFFIX = { "th", "st", "nd", "rd", "th" };

/**
 * Given a count, return a short string that represents the ordinal value
 * corresponding to that count.
 *
 * @param count		the count
 * @return			string representing the count as an ordinal
 */
public static String ordinal(int count)
{
	/* There are normally only 4 different suffixes ("th" applies to zero as
	   well as 4 and above), but values ending in zero are treated as a
	   separate case. */
	int ordinal = min(count % DECIMAL_BASE, TH_LIMIT);

	/* However, 11, 12, and 13 (recurring each 100) are special cases. */
	if( abs(count % RECURRENCE - SPECIAL) <= 1 ) ordinal = TH_LIMIT;

	/* Construct the string. */
	return count + SUFFIX[ordinal];
}
}
