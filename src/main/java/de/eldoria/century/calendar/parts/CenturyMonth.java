package de.eldoria.century.calendar.parts;

import de.eldoria.century.calendar.CenturyCalendar;
import lombok.Getter;

@Getter
public class CenturyMonth {
	/**
	 * Days of this month.
	 */
	private final int days;

	/**
	 * Name of Month
	 */
	private final String name;

	public CenturyMonth(int days, String name) {
		this.days = days;
		this.name = name;
	}

	public int toSeconds(CenturyCalendar calendar) {
		return days * calendar.getDay().getDayCycleDuration();
	}
}