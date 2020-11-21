package de.eldoria.century.calendar.units;

import de.eldoria.century.calendar.CenturyCalendar;
import de.eldoria.century.calendar.date.CenturyDate;
import de.eldoria.century.calendar.date.CenturyDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UnitConverterTest {
	private static final CenturyCalendar CALENDAR = CenturyCalendar.DEFAULT;
	private static final UnitConverter CONVERTER = CALENDAR.getConverter();
	private static final int YEAR_IN_SECONDS = CALENDAR.getDaysOfYear() * CALENDAR.getDay().getDayCycleDuration();
	private static final int DAY_SECONDS = CALENDAR.getDay().getDayCycleDuration();

	@Test
	public void secondsToYearTest() {
		Assertions.assertEquals(0, CONVERTER.secondsToYears(0));
		Assertions.assertEquals(1, CONVERTER.secondsToYears(YEAR_IN_SECONDS));
		Assertions.assertEquals(10, CONVERTER.secondsToYears(YEAR_IN_SECONDS * 10));
	}

	@Test
	public void secondsToDateTimeTest() {
		Assertions.assertEquals(CALENDAR.getMeta().getFirstDay(), CONVERTER.secondsToDateTime(0));

		Assertions.assertEquals(new CenturyDateTime(11, 1, 1, 0),
				CALENDAR.convert().secondsToDateTime(YEAR_IN_SECONDS * 10));

		Assertions.assertEquals(new CenturyDateTime(1, 1, 31, 0),
				CALENDAR.convert().secondsToDateTime(DAY_SECONDS * 30));

		Assertions.assertEquals(new CenturyDateTime(1, 1, 1, 0),
				CALENDAR.convert().secondsToDateTime(0));

		Assertions.assertEquals(new CenturyDateTime(1, 1, 1, 1000),
				CALENDAR.convert().secondsToDateTime(1000));


		Assertions.assertEquals(new CenturyDateTime(11, 1, 11, 0),
				CALENDAR.convert().secondsToDateTime(YEAR_IN_SECONDS * 10 + CALENDAR.getDay().getDayCycleDuration() * 10));
	}

	@Test
	public void secondsToDay() {
		Assertions.assertEquals(1, CONVERTER.secondsToDay(0));
		Assertions.assertEquals(365, CONVERTER.secondsToDay(YEAR_IN_SECONDS - DAY_SECONDS));
		Assertions.assertEquals(365 * 10, CONVERTER.secondsToDay((YEAR_IN_SECONDS * 10 - DAY_SECONDS)));
	}

	@Test
	public void yearsToSecondsTest() {
		Assertions.assertEquals(0, CONVERTER.yearsToSeconds(1));
		Assertions.assertEquals(YEAR_IN_SECONDS, CONVERTER.yearsToSeconds(2));
		Assertions.assertEquals(YEAR_IN_SECONDS * 2, CONVERTER.yearsToSeconds(3));
		Assertions.assertEquals(YEAR_IN_SECONDS * 9, CONVERTER.yearsToSeconds(10));
		Assertions.assertEquals(YEAR_IN_SECONDS * 99, CONVERTER.yearsToSeconds(100));
		Assertions.assertEquals(YEAR_IN_SECONDS * 999, CONVERTER.yearsToSeconds(1000));
		Assertions.assertEquals(YEAR_IN_SECONDS * 1499, CONVERTER.yearsToSeconds(1500));
		// Long overflow
		Assertions.assertNotEquals(YEAR_IN_SECONDS * 1999, CONVERTER.yearsToSeconds(2000));
	}

	@Test
	public void yearsToSecondsPlainTest() {
		Assertions.assertEquals(0, CONVERTER.yearsToSecondsPlain(0));
		Assertions.assertEquals(YEAR_IN_SECONDS, CONVERTER.yearsToSecondsPlain(1));
		Assertions.assertEquals(YEAR_IN_SECONDS * 2, CONVERTER.yearsToSecondsPlain(2));
		Assertions.assertEquals(YEAR_IN_SECONDS * 10, CONVERTER.yearsToSecondsPlain(10));
		Assertions.assertEquals(YEAR_IN_SECONDS * 100, CONVERTER.yearsToSecondsPlain(100));
		Assertions.assertEquals(YEAR_IN_SECONDS * 1000, CONVERTER.yearsToSecondsPlain(1000));
		Assertions.assertEquals(YEAR_IN_SECONDS * 1500, CONVERTER.yearsToSecondsPlain(1500));
		// Long overflow
		Assertions.assertNotEquals(YEAR_IN_SECONDS * 2000, CONVERTER.yearsToSecondsPlain(2000));
	}

	@Test
	public void monthToDaysTest() {
		Assertions.assertEquals(0, CONVERTER.monthToDays(0));
		Assertions.assertEquals(31, CONVERTER.monthToDays(2));
		Assertions.assertEquals(31 + 28, CONVERTER.monthToDays(3));
		Assertions.assertEquals(365 - 31, CONVERTER.monthToDays(12));
		Assertions.assertEquals(365, CONVERTER.monthToDays(13));
	}

	@Test
	public void monthToSecondsTest() {
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> CONVERTER.monthToSeconds(0));
		Assertions.assertEquals(0, CONVERTER.monthToSeconds(1));
		Assertions.assertEquals(31 * DAY_SECONDS, CONVERTER.monthToSeconds(2));
		Assertions.assertEquals((31 + 28) * DAY_SECONDS, CONVERTER.monthToSeconds(3));
		Assertions.assertEquals((365 - 31) * DAY_SECONDS, CONVERTER.monthToSeconds(12));
		Assertions.assertEquals(365 * DAY_SECONDS, CONVERTER.monthToSeconds(13));
	}

	@Test
	public void dayToSecondsTest() {
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> CONVERTER.dayToSeconds(0));
		Assertions.assertEquals(0, CONVERTER.dayToSeconds(1));
		Assertions.assertEquals(DAY_SECONDS, CONVERTER.dayToSeconds(2));
		Assertions.assertEquals(DAY_SECONDS * 9, CONVERTER.dayToSeconds(10));
		Assertions.assertEquals(DAY_SECONDS * 29, CONVERTER.dayToSeconds(30));
	}

	@Test
	public void dayToMonthTest() {
		Assertions.assertEquals(1, CONVERTER.dayToMonth(1));
		Assertions.assertEquals(1, CONVERTER.dayToMonth(31));
		Assertions.assertEquals(2, CONVERTER.dayToMonth(32));
		Assertions.assertEquals(12, CONVERTER.dayToMonth(365));
	}

	@Test
	public void dayToDayOfMonthTest() {
		Assertions.assertEquals(1, CONVERTER.dayToDayOfMonth(1));
		Assertions.assertEquals(31, CONVERTER.dayToDayOfMonth(31));
		Assertions.assertEquals(1, CONVERTER.dayToDayOfMonth(32));
		Assertions.assertEquals(31, CONVERTER.dayToDayOfMonth(365));
	}

	@Test
	public void dayToYearTest() {
		Assertions.assertEquals(1, CONVERTER.dayToYear(1));
		Assertions.assertEquals(1, CONVERTER.dayToYear(32));
		Assertions.assertEquals(2, CONVERTER.dayToYear(366));

	}

	@Test
	public void daysToDateTest() {
		Assertions.assertEquals(new CenturyDate(1, 1, 1), CONVERTER.dayToDate(1));
		Assertions.assertEquals(new CenturyDate(1, 2, 1), CONVERTER.dayToDate(32));
		Assertions.assertEquals(new CenturyDate(2, 1, 1), CONVERTER.dayToDate(366));
	}
}
