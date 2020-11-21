package de.eldoria.century.calendar.date;

import de.eldoria.century.calendar.CenturyCalendar;
import de.eldoria.century.calendar.period.CenturyPeriod;
import de.eldoria.century.calendar.units.UnitConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CenturyDateTimeTest {
	private static final CenturyCalendar CALENDAR = CenturyCalendar.DEFAULT;
	private static final UnitConverter CONVERTER = CALENDAR.getConverter();
	private static final int YEAR_IN_SECONDS = CALENDAR.getDaysOfYear() * CALENDAR.getDay().getDayCycleDuration();
	private static final int DAY_SECONDS = CALENDAR.getDay().getDayCycleDuration();

	@Test
	public void addTest() {
		// Check for month change
		Assertions.assertEquals(new CenturyDateTime(1, 2, 1, 0),
				new CenturyDateTime(1, 1, 31, 0).add(DAY_SECONDS, CALENDAR));

		// check for year change
		Assertions.assertEquals(new CenturyDateTime(2, 1, 1, 0),
				new CenturyDateTime(1, 1, 1, 0).add(YEAR_IN_SECONDS, CALENDAR));

		// check for seconds change
		Assertions.assertEquals(new CenturyDateTime(1, 1, 2, 0),
				new CenturyDateTime(1, 1, 1, DAY_SECONDS - 1).add(1, CALENDAR));

		// Check for month change
		Assertions.assertEquals(new CenturyDateTime(1, 2, 1, 0),
				new CenturyDateTime(1, 1, 31, 0).add(CenturyPeriod.of(0, 1), CALENDAR));

		// check for year change
		Assertions.assertEquals(new CenturyDateTime(2, 1, 1, 0),
				new CenturyDateTime(1, 1, 1, 0).add(CenturyPeriod.of(1), CALENDAR));

		// check for seconds change
		Assertions.assertEquals(new CenturyDateTime(1, 1, 2, 0),
				new CenturyDateTime(1, 1, 1, DAY_SECONDS - 1).add(CenturyPeriod.of(0, 0, 1), CALENDAR));
	}

	@Test
	public void subtractTest() {
		// Check for month change
		Assertions.assertEquals(new CenturyDateTime(1, 1, 31, 0),
				new CenturyDateTime(1, 2, 1, 0).subtract(DAY_SECONDS, CALENDAR));

		// Check for year change
		Assertions.assertEquals(new CenturyDateTime(1, 1, 1, 0),
				new CenturyDateTime(2, 1, 1, 0).subtract(YEAR_IN_SECONDS, CALENDAR));

		// Check for second change
		Assertions.assertEquals(new CenturyDateTime(1, 2, 28, DAY_SECONDS - 1),
				new CenturyDateTime(1, 3, 1, 0).subtract(1, CALENDAR));

		// Check for month change
		Assertions.assertEquals(new CenturyDateTime(1, 1, 31, 0),
				new CenturyDateTime(1, 2, 1, 0).subtract(CenturyPeriod.of(0, 1), CALENDAR));

		// Check for year change
		Assertions.assertEquals(new CenturyDateTime(1, 1, 1, 0),
				new CenturyDateTime(2, 1, 1, 0).subtract(CenturyPeriod.of(1, 0), CALENDAR));

		// Check for second change
		Assertions.assertEquals(new CenturyDateTime(1, 2, 28, DAY_SECONDS - 1),
				new CenturyDateTime(1, 3, 1, 0).subtract(CenturyPeriod.of(0, 0, 1), CALENDAR));
	}

	@Test
	public void toDateTest() {
		Assertions.assertEquals(new CenturyDate(1, 1, 1), new CenturyDateTime(1, 1, 1, 2500).toDate());
	}
}
