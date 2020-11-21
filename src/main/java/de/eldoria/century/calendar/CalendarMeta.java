package de.eldoria.century.calendar;

import de.eldoria.century.calendar.date.CenturyDateTime;
import lombok.Getter;

import java.util.Calendar;
import java.util.Date;

@Getter
public class CalendarMeta {

	/**
	 * The reference date where the calendar starts.
	 */
	protected int yearOffset = 1;

	protected Date refDate = getDateOf(2020, 1, 1);

	private static Date today() {
		return Calendar.getInstance().getTime();
	}

	private static Date getDateOf(int year, int month, int day) {
		Calendar c = Calendar.getInstance();
		c.set(year, month, day);
		return c.getTime();
	}

	public CenturyDateTime getFirstDay() {
		return new CenturyDateTime(yearOffset, 1, 1, 0);
	}
}
