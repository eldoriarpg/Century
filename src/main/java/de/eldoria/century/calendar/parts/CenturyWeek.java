package de.eldoria.century.calendar.parts;

import lombok.Getter;

/**
 * Represents a custom Week.
 */
@Getter
public class CenturyWeek {
	/**
	 * Defines the days of a week.
	 * <p>
	 * Each day has a name as well.
	 */
	public String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

	public CenturyWeek() {
	}

	public CenturyWeek(String... days) {
		this.days = days;
	}

	/**
	 * Get the days of a week.
	 *
	 * @return the number of days per week.
	 */
	public int getDays() {
		return days.length;
	}

	public String getDayName(int i) {
		return days[i % getDays()];
	}
}
