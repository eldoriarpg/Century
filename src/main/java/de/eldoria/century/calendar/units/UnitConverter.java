package de.eldoria.century.calendar.units;

import de.eldoria.century.calendar.CalendarMeta;
import de.eldoria.century.calendar.CenturyCalendar;
import de.eldoria.century.calendar.date.CenturyDate;
import de.eldoria.century.calendar.date.CenturyDateTime;
import de.eldoria.century.calendar.parts.CenturyDay;
import de.eldoria.century.calendar.parts.CenturyMonth;
import de.eldoria.century.calendar.parts.CenturySeason;
import de.eldoria.century.calendar.parts.CenturyWeek;
import de.eldoria.century.calendar.period.CenturyPeriod;

public class UnitConverter {
	private final CenturyCalendar calendar;

	public UnitConverter(CenturyCalendar calendar) {
		this.calendar = calendar;
	}

	private CenturyDay getDay() {
		return calendar.getDay();
	}

	private CenturyWeek getWeek() {
		return calendar.getWeek();
	}

	private CenturyMonth[] getMonths() {
		return calendar.getMonths();
	}

	private CenturySeason[] getSeasons() {
		return calendar.getSeasons();
	}

	private CalendarMeta getMeta() {
		return calendar.getMeta();
	}

	private int getDaysOfYear() {
		return calendar.getDaysOfYear();
	}

	private CenturyMonth getMonth(int month) {
		return calendar.getMonth(month);
	}

	public int secondsToYears(long seconds) {
		return (int) (seconds / (getDaysOfYear() * getDay().getDayCycleDuration()));
	}

	/**
	 * Get the date with the base of {@link CalendarMeta#getYearOffset()} ()}
	 *
	 * @param seconds seconds since init.
	 *
	 * @return Century date time since start.
	 */
	public CenturyDateTime secondsToDateTime(long seconds) {
		// Lets add the first day as seconds. To start from 0.
		long secondsLeft = seconds;

		int years = secondsToYears(seconds);
		secondsLeft -= yearsToSecondsPlain(years);

		int days = secondsToDay(secondsLeft);
		int month = dayToMonth(days);
		int day = dayToDayOfMonth(days);

		secondsLeft -= dayToSeconds(days);

		return new CenturyDateTime(years + getMeta().getYearOffset(), month, day, (int) secondsLeft);
	}

	/**
	 * Get the seconds as day of a year.
	 *
	 * @param seconds seconds of year.
	 *
	 * @return the day from seconds. 0 seconds will result in day 1
	 */
	public int secondsToDay(long seconds) {
		return 1 + (int) (seconds / getDay().getDayCycleDuration());
	}

	/**
	 * Get the years in seconds.
	 * <p>
	 * The year offset will be excluded.
	 *
	 * @param years year to convert
	 *
	 * @return years in seconds excluding offset.
	 */
	public long yearsToSeconds(int years) {
		long secondsPerYear = (getDaysOfYear() * (long) getDay().getDayCycleDuration());
		years -= getMeta().getYearOffset();
		return years * secondsPerYear;
	}

	/**
	 * Get the years in seconds.
	 *
	 * @param years year to convert
	 *
	 * @return years in seconds.
	 */
	public long yearsToSecondsPlain(int years) {
		return (years) * (long) (getDaysOfYear() * getDay().getDayCycleDuration());
	}

	/**
	 * Get the amount of every month before this month.
	 *
	 * @param month month to which the days should be returned.
	 *
	 * @return amount of days passed during this year.
	 */
	public int monthToDays(int month) {
		int days = 0;
		for (int currentMonth = 1; currentMonth < month; currentMonth++) {
			days += getMonth(currentMonth).getDays();
		}
		return days;
	}

	/**
	 * Get the amount of every month before this month.
	 *
	 * @param month month to which the days should be returned.
	 *
	 * @return amount of seconds passed during this year till end of last month.
	 */
	public int monthToSeconds(int month) {
		if (month < 1) throw new IndexOutOfBoundsException("Month " + month + " is out of Range.");
		return monthToDays(month) * getDay().getDayCycleDuration();
	}

	/**
	 * Returns the time passed since this day.
	 * <p>
	 * This is exclusive for the current day. So day 1 will always be 0.
	 *
	 * @param day day of month
	 *
	 * @return time till day in seconds
	 */
	public long dayToSeconds(int day) {
		if (day < 1) throw new IndexOutOfBoundsException("Day " + day + " is out of Range.");
		return (day - 1) * this.getDay().getDayCycleDuration();
	}

	/**
	 * Get the month from a day of a year.
	 *
	 * @param day day of the year
	 *
	 * @return month of the day
	 */
	public int dayToMonth(int day) {
		if (day == 0) return 1;
		if (day % getDaysOfYear() == 0) return getMonths().length;
		day %= getDaysOfYear();
		int days = 0;
		int month = 1;
		for (CenturyMonth m : getMonths()) {
			if (days + m.getDays() >= day) return month;
			month++;
			days += m.getDays();
		}
		return month;
	}

	/**
	 * Get the day of the month from the day of a year.
	 *
	 * @param day day of the year.
	 *
	 * @return day of the year.
	 */
	public int dayToDayOfMonth(int day) {
		// this will be the last day of the last month.
		if (day % getDaysOfYear() == 0) return getMonths()[getMonths().length - 1].getDays();
		day %= getDaysOfYear();
		int month = 1;
		for (CenturyMonth m : getMonths()) {
			if (day <= m.getDays()) return day;
			day -= m.getDays();
		}
		return day;
	}

	/**
	 * Get the year from a day
	 *
	 * @param days days since start of the calendar
	 *
	 * @return year of the day since start of the calendar.
	 */
	public int dayToYear(int days) {
		return dayToDate(days).getYear();
	}

	/**
	 * Converts the day to a date.
	 *
	 * @param days day of the year
	 *
	 * @return date of the day.
	 */
	public CenturyDate dayToDate(int days) {
		return getMeta().getFirstDay().add(CenturyPeriod.of(0, days - 1), calendar).toDate();
	}
}
