package de.eldoria.century.calendar.date;

import de.eldoria.century.calendar.CenturyCalendar;
import de.eldoria.century.calendar.period.CenturyPeriod;
import de.eldoria.century.calendar.units.date.DateTimeUnit;

public class CenturyDateTime extends DateTimeUnit {

	public CenturyDateTime(int year, int month, int day, int seconds) {
		super(year, month, day, seconds);
	}

	public CenturyDateTime add(CenturyPeriod period, CenturyCalendar calendar) {
		return add(period.toSeconds(calendar), calendar);
	}

	public CenturyDateTime add(long seconds, CenturyCalendar calendar) {
		long currentSeconds = toSeconds(calendar);
		return calendar.convert().secondsToDateTime(currentSeconds + seconds);
	}


	public CenturyDateTime subtract(CenturyPeriod period, CenturyCalendar calendar) {
		return subtract(period.toSeconds(calendar), calendar);
	}

	public CenturyDateTime subtract(long seconds, CenturyCalendar calendar) {
		long currentSeconds = toSeconds(calendar);
		return calendar.convert().secondsToDateTime(currentSeconds - seconds);
	}

	public CenturyDate toDate() {
		return new CenturyDate(year, month, day);
	}
}
