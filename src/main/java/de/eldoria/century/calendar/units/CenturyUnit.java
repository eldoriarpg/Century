package de.eldoria.century.calendar.units;

import de.eldoria.century.calendar.CenturyCalendar;

public interface CenturyUnit {
	long toSeconds(CenturyCalendar calendar);

	default int toDays(CenturyCalendar calendar) {
		return (int) (toSeconds(calendar) / calendar.getDay().getDayCycleDuration());
	}
}
