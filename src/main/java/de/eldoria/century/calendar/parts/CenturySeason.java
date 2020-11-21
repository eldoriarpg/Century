package de.eldoria.century.calendar.parts;

import de.eldoria.century.calendar.date.SimpleCenturyDate;
import lombok.Getter;

@Getter
public class CenturySeason {
	private final SimpleCenturyDate start;

	private final String name;

	public CenturySeason(SimpleCenturyDate start, String name) {
		this.start = start;
		this.name = name;
	}
}
