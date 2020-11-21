package de.eldoria.century.calendar.comparator;

import de.eldoria.century.calendar.CenturyCalendar;
import de.eldoria.century.calendar.units.CenturyUnit;

import java.util.Comparator;

public class CenturyUnitComparator implements Comparator<CenturyUnit> {
	private final CenturyCalendar calendar;

	public CenturyUnitComparator(CenturyCalendar calendar) {
		this.calendar = calendar;
	}

	@Override
	public int compare(CenturyUnit o1, CenturyUnit o2) {
		return Long.compare(o1.toSeconds(calendar), o2.toSeconds(calendar));
	}
}
