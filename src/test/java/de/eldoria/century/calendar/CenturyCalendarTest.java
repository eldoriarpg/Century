package de.eldoria.century.calendar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

class CenturyCalendarTest {
	private static final CenturyCalendar CALENDAR = new CenturyCalendar();

	@Test
	void getCurrentDate() {
		Assertions.assertEquals(CALENDAR.getSecondsPassed(),
				CALENDAR.getCurrentDate().toSeconds(CALENDAR));
	}

	@Test
	void getSecondsPassed() {
		Assertions.assertEquals(CALENDAR.getMeta().getRefDate().toInstant().until(Instant.now(), ChronoUnit.SECONDS),
				CALENDAR.getSecondsPassed());
	}

	@Test
	void getDaysOfYear() {
		Assertions.assertEquals(365, CALENDAR.getDaysOfYear());

	}

	@Test
	void getMonth() {
		Assertions.assertTrue(CALENDAR.getMonth(1).getName().equalsIgnoreCase("January"));
	}
}