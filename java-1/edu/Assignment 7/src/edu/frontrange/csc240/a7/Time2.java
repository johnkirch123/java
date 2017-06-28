
package edu.frontrange.csc240.a7;

/**
 * Keep times of the day for use in the college system.
 *
 * @author		Dr. Bruce K. Haddon, Instructor
 * @version		2.0, 2016-08-22, for CSC-240 Assignment 7, per the Assignment
 *				Instructions
 */
public class Time2
{
/**
 * Hours in a day.
 */
private static final int HOURS_IN_DAY = 24;

/**
 * Minutes in an hour.
 */
private static final int MINUTES_IN_HOUR = 60;

/**
 * Seconds in a minute.
 */
private static final int SECONDS_IN_MINUTE = 60;

/**
 * AM/PM divide
 */
private static final int AM_PM_DIVIDE = HOURS_IN_DAY / 2;

/**
 * Hour of the day, 0 - HOURS_IN_DAY-1.
 */
private int hour;

/**
 * Minute of the hour, 0 - MINUTES_IN_HOUR-1.
 */
private int minute;

/**
 * Second of the minute, 0 - SECONDS_IN_MINUTE-1
 */
private int second;

/**
 * Ensure a zero-parameter constructor, so empty Time2 records may be created.
 */
public Time2()
{
	this(0, 0, 0);
}

/**
 * Time2 object, set to the exact given hour.
 *
 * @param hour		the given hour.
 */
public Time2(int hour)
{
	this(hour, 0, 0);
}

/**
 * Time2 object, set to the exact given hour and minute.
 *
 * @param hour		the given hour.
 * @param minute	the given minute.
 */
public Time2(int hour, int minute)
{
	this(hour, minute, 0);
}

/**
 * Time2 object, set the exact given hour, minute and second.
 *
 * @param hour		the given hour.
 * @param minute	the given minute.
 * @param second	the given second.
 */
public Time2(int hour, int minute, int second)
{
	setTime(hour, minute, second);
}

/**
 * Copy constructor.
 *
 * @param time	time instance to copy
 */
public Time2(Time2 time)
{
	this(time.getHour(), time.getMinute(), time.getSecond());
}

/**
 * Set the time to the given hour, minute and second.
 *
 * @param hour		the given hour.
 * @param minute	the given minute.
 * @param second	the given second.
 */
public final void setTime(int hour, int minute, int second)
{
	setHour(hour);
	setMinute(minute);
	setSecond(second);
}

/**
 * Set hour to the given hour. If the given value is not in the range
 * 0 - HOURS_IN_DAY-1, then the value is set to 0 (midnight).
 *
 * @param hour		the given hour.
 */
public final void setHour(int hour)
{
	this.hour = hour >= 0 && hour < HOURS_IN_DAY ? hour : 0;
}

/**
 * Set minutes to the given minute. If the given value is not in the range
 * 0 - MINUTES_IN_HOUR-1, then the value is set to 0 (on the hour).
 *
 * @param minute		the given minute.
 */
public final void setMinute(int minute)
{
	this.minute = minute >= 0 && minute < MINUTES_IN_HOUR ? minute : 0;
}

/**
 * Set seconds to the given second. If the given value is not in the range
 * 0 - SECONDS_IN_MINUTE-1, then the value is set to 0 (on the minute).
 *
 * @param second		the given second.
 */
public final void setSecond(int second)
{
	this.second = second >= 0 && second < SECONDS_IN_MINUTE ? second : 0;
}

/**
 * Get the set hour.
 *
 * @return		the set hour.
 */
public int getHour()
{
	return hour;
}

/**
 * Get the set minute.
 *
 * @return		the set minute.
 */
public int getMinute()
{
	return minute;
}

/**
 * Get the set second.
 *
 * @return		the set second.
 */
public int getSecond()
{
	return second;
}

/**
 * @return		time in the universal 24-hour format (HH:mm:ss).
 */
public String toUniversalString()
{
	return String.format(
			"%02d:%02d:%02d", getHour(), getMinute(), getSecond());
}

 /**
  * @return		time in the AM/PM time format (h:mm:ss AM/PM).
  */
@Override
public String toString()
{
	int h = getHour();
	return String.format("%d:%02d:%02d %s",
			h == 0 || h == AM_PM_DIVIDE ? AM_PM_DIVIDE : h % AM_PM_DIVIDE,
					getMinute(), getSecond(), h < AM_PM_DIVIDE ? "AM" : "PM");
}
}

