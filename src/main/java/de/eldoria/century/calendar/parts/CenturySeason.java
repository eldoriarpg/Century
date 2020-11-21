package de.eldoria.century.calendar.parts;

import de.eldoria.century.calendar.date.SimpleCenturyDate;

public class CenturySeason {
	private SimpleCenturyDate start;

	private String name;

	public CenturySeason(SimpleCenturyDate start, String name) {
		this.start = start;
		this.name = name;
	}
}
