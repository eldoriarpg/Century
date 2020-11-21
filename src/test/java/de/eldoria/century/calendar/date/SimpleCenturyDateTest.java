package de.eldoria.century.calendar.date;

import de.eldoria.century.calendar.CenturyCalendar;
import de.eldoria.century.calendar.period.CenturyPeriod;
import de.eldoria.century.calendar.units.UnitConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SimpleCenturyDateTest {
	private static final CenturyCalendar CALENDAR = new CenturyCalendar();
	private static final UnitConverter CONVERTER = CALENDAR.getConverter();
	private static final int YEAR_IN_SECONDS = CALENDAR.getDaysOfYear() * CALENDAR.getDay().getDayCycleDuration();
	private static final int DAY_SECONDS = CALENDAR.getDay().getDayCycleDuration();

	@Test
	public void addTest() {
		// Check for month change
		Assertions.assertEquals(new SimpleCenturyDate(2, 1),
				new SimpleCenturyDate(1, 31).add(DAY_SECONDS, CALENDAR));

		// Check for month change
		Assertions.assertEquals(new SimpleCenturyDate(2, 1),
				new SimpleCenturyDate(1, 31).add(CenturyPeriod.of(0, 1), CALENDAR));
	}

	@Test
	public void subtractTest() {
		// Check for month change
		Assertions.assertEquals(new SimpleCenturyDate(1, 31),
				new SimpleCenturyDate(2, 1).subtract(DAY_SECONDS, CALENDAR));

		// Check for month change
		Assertions.assertEquals(new SimpleCenturyDate(1, 31),
				new SimpleCenturyDate(2, 1).subtract(CenturyPeriod.of(0, 1), CALENDAR));
	}

	@Test
	public void toDate() {
		Assertions.assertEquals(new CenturyDate(1,1,1), new SimpleCenturyDate(1,1).toDate(1));
	}
}
