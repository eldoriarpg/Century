package de.eldoria.century.calendar.date;

import de.eldoria.century.calendar.CenturyCalendar;
import de.eldoria.century.calendar.period.CenturyPeriod;
import de.eldoria.century.calendar.units.date.DateUnit;
import lombok.Getter;

@Getter
public class CenturyDate extends DateUnit {

	public CenturyDate(int year, int month, int day) {
		super(year, month, day);
	}

	public CenturyDate add(CenturyPeriod period, CenturyCalendar calendar) {
		return add(period.toSeconds(calendar), calendar);
	}

	public CenturyDate add(long seconds, CenturyCalendar calendar) {
		long currentSeconds = toSeconds(calendar);
		return calendar.convert().secondsToDateTime(currentSeconds + seconds).toDate();
	}


	public CenturyDate subtract(CenturyPeriod period, CenturyCalendar calendar) {
		return subtract(period.toSeconds(calendar), calendar);
	}

	public CenturyDate subtract(long seconds, CenturyCalendar calendar) {
		long currentSeconds = toSeconds(calendar);
		return calendar.convert().secondsToDateTime(currentSeconds - seconds).toDate();
	}

	public CenturyDateTime toDateTime() {
		return new CenturyDateTime(year, month, day, 0);
	}

	public SimpleCenturyDate toSimpleDate() {
		return new SimpleCenturyDate(month, day);
	}
}