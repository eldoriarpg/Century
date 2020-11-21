package de.eldoria.century.calendar.period;

import de.eldoria.century.calendar.CenturyCalendar;
import de.eldoria.century.calendar.date.CenturyDateTime;
import de.eldoria.century.calendar.units.UnitConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CenturyPeriodTest {
	private static final CenturyCalendar CALENDAR = new CenturyCalendar();
	private static final UnitConverter CONVERTER = CALENDAR.getConverter();
	private static final int YEAR_IN_SECONDS = CALENDAR.getDaysOfYear() * CALENDAR.getDay().getDayCycleDuration();
	private static final int DAY_SECONDS = CALENDAR.getDay().getDayCycleDuration();

	@Test
	public void fromSecondsTest() {
		Assertions.assertEquals(CenturyPeriod.of(0, 0, 0), CenturyPeriod.of(0, CALENDAR));
		Assertions.assertEquals(CenturyPeriod.of(1, 0, 0), CenturyPeriod.of(YEAR_IN_SECONDS, CALENDAR));
		Assertions.assertEquals(CenturyPeriod.of(0, 1, 1), CenturyPeriod.of(DAY_SECONDS + 1, CALENDAR));
	}

	@Test
	public void ofCenturyUnitsTest() {
		Assertions.assertEquals(CenturyPeriod.of(0, 0, 1),
				CenturyPeriod.of(
						new CenturyDateTime(1, 1, 1, 0),
						new CenturyDateTime(1, 1, 1, 1), CALENDAR));

		Assertions.assertEquals(CenturyPeriod.of(1, 0, 0),
				CenturyPeriod.of(
						new CenturyDateTime(1, 1, 1, 0),
						new CenturyDateTime(2, 1, 1, 0), CALENDAR));

		Assertions.assertEquals(CenturyPeriod.of(0, 31, 0),
				CenturyPeriod.of(
						new CenturyDateTime(1, 1, 1, 0),
						new CenturyDateTime(1, 2, 1, 0), CALENDAR));
	}

	@Test
	public void toSecondsTest() {
		Assertions.assertEquals(0,
				CenturyPeriod.of(0, 0, 0).toSeconds(CALENDAR));
		Assertions.assertEquals(20,
				CenturyPeriod.of(0, 0, 20).toSeconds(CALENDAR));
		Assertions.assertEquals(YEAR_IN_SECONDS,
				CenturyPeriod.of(1, 0, 0).toSeconds(CALENDAR));
		Assertions.assertEquals(DAY_SECONDS * 25,
				CenturyPeriod.of(0, 25, 0).toSeconds(CALENDAR));
		Assertions.assertEquals(YEAR_IN_SECONDS + DAY_SECONDS * 5 + 25,
				CenturyPeriod.of(1, 5, 25).toSeconds(CALENDAR));
	}

	@Test
	public void ofSecondsTest() {
		Assertions.assertEquals(CenturyPeriod.of(0, 0, 10),
				CenturyPeriod.of(10, CALENDAR));
		Assertions.assertEquals(CenturyPeriod.of(1, 1, 1000),
				CenturyPeriod.of(YEAR_IN_SECONDS + DAY_SECONDS + 1000, CALENDAR));
	}

	@Test
	public void ofYear() {
		Assertions.assertEquals(CenturyPeriod.of(1, 0, 0),
				CenturyPeriod.of(1));
		Assertions.assertEquals(CenturyPeriod.of(10, 0, 0),
				CenturyPeriod.of(10));
	}

	@Test
	public void ofYearDay() {
		Assertions.assertEquals(CenturyPeriod.of(1, 10, 0),
				CenturyPeriod.of(1, 10));
		Assertions.assertEquals(CenturyPeriod.of(10, 25, 0),
				CenturyPeriod.of(10, 25));
	}

	@Test
	public void ofYearDaySecond() {
		Assertions.assertEquals(CenturyPeriod.of(1, 10, 50),
				CenturyPeriod.of(1, 10, 50));
		Assertions.assertEquals(CenturyPeriod.of(10, 25, 800),
				CenturyPeriod.of(10, 25, 800));
	}
}
