package de.eldoria.century.calendar.units;

import de.eldoria.century.calendar.CenturyCalendar;
import de.eldoria.century.calendar.date.CenturyDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CenturyTimeUnitTest {
	private static final CenturyCalendar CALENDAR = CenturyCalendar.DEFAULT;
	private final CenturyTimeUnit first = new CenturyDateTime(1, 1, 1, 1);
	private final CenturyTimeUnit second = new CenturyDateTime(1, 1, 1, 2);

	@Test
	public void isAfterTest() {
		Assertions.assertTrue(second.isAfter(first, CALENDAR));
		Assertions.assertFalse(first.isAfter(second, CALENDAR));
		Assertions.assertFalse(first.isAfter(first, CALENDAR));
	}

	@Test
	public void isBeforeTest() {
		Assertions.assertTrue(first.isBefore(second, CALENDAR));
		Assertions.assertFalse(second.isBefore(first, CALENDAR));
		Assertions.assertFalse(first.isBefore(first, CALENDAR));
	}
}
