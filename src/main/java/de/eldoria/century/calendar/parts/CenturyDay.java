package de.eldoria.century.calendar.parts;

import de.eldoria.century.calendar.date.SimpleCenturyDate;
import lombok.Getter;

@Getter
public class CenturyDay {
	/**
	 * Summer Solstice when the longest day will take place.
	 */
	protected SimpleCenturyDate longestDay = new SimpleCenturyDate(6, 20);

	/**
	 * Summer Solstice when the longest day will take place.
	 */
	protected SimpleCenturyDate winterSolstice = new SimpleCenturyDate(12, 21);

	/**
	 * The time from dawn till dusk during the longest day.
	 */
	protected double longestDayTime = 0.66;
	/**
	 * The time from dawn till dusk during the shortest day.
	 */
	protected double shortestDayTime = 0.41;

	/**
	 * Time of dawn in ticks to define the sun position.
	 */
	protected int dawn = 23000;

	/**
	 * Time of dusk in ticks to define the sun position.
	 */
	protected int dusk = 13000;

	/**
	 * The total time of a day in seconds.
	 */
	protected int dayCycleDuration = 3600;
}
