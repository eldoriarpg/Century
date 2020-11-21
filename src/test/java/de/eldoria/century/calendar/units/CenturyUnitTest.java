package de.eldoria.century.calendar.units;

import de.eldoria.century.calendar.CenturyCalendar;
import de.eldoria.century.calendar.date.CenturyDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CenturyUnitTest {
	private static final CenturyCalendar CALENDAR = CenturyCalendar.DEFAULT;
	private static final int DAY_SECONDS = CALENDAR.getDay().getDayCycleDuration();
	private final CenturyUnit first = new CenturyDateTime(1, 1, 1, 1);
	private final CenturyUnit second = new CenturyDateTime(1, 1, 2, 0);

	@Test
	public void toSecondsTest() {
		Assertions.assertEquals(1, first.toSeconds(CALENDAR));
		Assertions.assertEquals(DAY_SECONDS, second.toSeconds(CALENDAR));
	}

	@Test
	public void toDays() {
		Assertions.assertEquals(0, first.toDays(CALENDAR));
		Assertions.assertEquals(1, second.toDays(CALENDAR));
	}
}
