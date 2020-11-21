package de.eldoria.century.calendar.comparator;

import de.eldoria.century.calendar.CenturyCalendar;
import de.eldoria.century.calendar.date.CenturyDateTime;
import de.eldoria.century.calendar.units.CenturyTimeUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CenturyUnitComparatorTest {

	@Test
	void compare() {
		CenturyUnitComparator comparator = new CenturyUnitComparator(new CenturyCalendar());

		CenturyTimeUnit first = new CenturyDateTime(1, 1, 1, 1);
		CenturyTimeUnit second = new CenturyDateTime(1, 1, 1, 2);

		Assertions.assertEquals(1, comparator.compare(second, first));
		Assertions.assertEquals(-1, comparator.compare(first, second));
		Assertions.assertEquals(0, comparator.compare(first, first));
	}
}