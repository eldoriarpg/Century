package de.eldoria.century.calendar.date;

import de.eldoria.century.calendar.CenturyCalendar;
import de.eldoria.century.calendar.period.CenturyPeriod;
import de.eldoria.century.calendar.units.date.SimpleDateUnit;
import lombok.Getter;

@Getter
public class SimpleCenturyDate extends SimpleDateUnit {

	public SimpleCenturyDate(int month, int day) {
		super(month, day);
	}

	public SimpleCenturyDate add(CenturyPeriod period, CenturyCalendar calendar) {
		return add(period.toSeconds(calendar), calendar);
	}

	public SimpleCenturyDate add(long seconds, CenturyCalendar calendar) {
		long currentSeconds = toSeconds(calendar);
		return calendar.convert().secondsToDateTime(currentSeconds + seconds).toDate().toSimpleDate();
	}

	public SimpleCenturyDate subtract(CenturyPeriod period, CenturyCalendar calendar) {
		return subtract(period.toSeconds(calendar), calendar);
	}

	public SimpleCenturyDate subtract(long seconds, CenturyCalendar calendar) {
		long currentSeconds = toSeconds(calendar);
		return calendar.convert().secondsToDateTime(currentSeconds - seconds).toDate().toSimpleDate();
	}

	public CenturyDate toDate(int year) {
		return new CenturyDate(year, month, day);
	}
}
