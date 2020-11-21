package de.eldoria.century.calendar.units;

import de.eldoria.century.calendar.CenturyCalendar;

public interface CenturyTimeUnit extends CenturyUnit {

	/**
	 * Checks if this time unit is before the other time unit
	 *
	 * @param o        other time unit
	 * @param calendar reference calendar
	 *
	 * @return true if this date id before the other date
	 */
	default boolean isBefore(CenturyUnit o, CenturyCalendar calendar) {
		if (o.equals(this)) return false;

		long otherSeconds = o.toSeconds(calendar);
		long thisSeconds = toSeconds(calendar);

		return thisSeconds < otherSeconds;
	}

	/**
	 * Checks if this time unit is after the other time unit.
	 *
	 * @param o        other time unit
	 * @param calendar reference calendar
	 *
	 * @return true if this time unit is after the other time unit.
	 */
	default boolean isAfter(CenturyUnit o, CenturyCalendar calendar) {
		if (o.equals(this)) return false;

		long otherSeconds = o.toSeconds(calendar);
		long thisSeconds = toSeconds(calendar);

		return otherSeconds < thisSeconds;
	}
}
