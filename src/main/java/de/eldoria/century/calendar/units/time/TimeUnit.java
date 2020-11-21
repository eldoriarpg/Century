package de.eldoria.century.calendar.units.time;

import de.eldoria.century.calendar.CenturyCalendar;
import de.eldoria.century.calendar.units.CenturyUnit;

public abstract class TimeUnit implements CenturyUnit {
	protected final int years;
	protected final int days;
	protected final int seconds;

	public TimeUnit(int years, int days, int seconds) {
		this.years = years;
		this.days = days;
		this.seconds = seconds;
	}

	@Override
	public long toSeconds(CenturyCalendar calendar) {
		int dayCycleDuration = calendar.getDay().getDayCycleDuration();
		long seconds = calendar.convert().yearsToSecondsPlain(years);
		seconds += days * dayCycleDuration;
		seconds += this.seconds;
		return seconds;
	}
}
