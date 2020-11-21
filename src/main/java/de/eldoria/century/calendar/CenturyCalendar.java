package de.eldoria.century.calendar;

import de.eldoria.century.calendar.date.CenturyDateTime;
import de.eldoria.century.calendar.date.SimpleCenturyDate;
import de.eldoria.century.calendar.parts.CenturyDay;
import de.eldoria.century.calendar.parts.CenturyMonth;
import de.eldoria.century.calendar.parts.CenturySeason;
import de.eldoria.century.calendar.parts.CenturyWeek;
import de.eldoria.century.calendar.units.UnitConverter;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

@Getter
@Builder
public class CenturyCalendar {
	public static final CenturyCalendar DEFAULT = new CenturyCalendar();
	private CalendarMeta meta;

	/**
	 * Defines the seasons of this calendar.
	 */
	private CenturySeason[] seasons;

	/**
	 * Defines the month of this calendar
	 */
	private CenturyMonth[] months;

	/**
	 * Defines a wee of this calendar.
	 */
	private CenturyWeek week;

	private CenturyDay day;

	private UnitConverter converter;

	private CenturyCalendar() {
		meta = new CalendarMeta();
		seasons = new CenturySeason[]{
				new CenturySeason(new SimpleCenturyDate(12, 21), "Winter"),
				new CenturySeason(new SimpleCenturyDate(3, 20), "Spring"),
				new CenturySeason(new SimpleCenturyDate(6, 21), "Summer"),
				new CenturySeason(new SimpleCenturyDate(9, 22), "Autumn")
		};

		months = new CenturyMonth[]{
				new CenturyMonth(31, "January"),
				new CenturyMonth(28, "February"),
				new CenturyMonth(31, "March"),
				new CenturyMonth(30, "April"),
				new CenturyMonth(31, "May"),
				new CenturyMonth(30, "June"),
				new CenturyMonth(31, "July"),
				new CenturyMonth(31, "August"),
				new CenturyMonth(30, "September"),
				new CenturyMonth(31, "October"),
				new CenturyMonth(30, "November"),
				new CenturyMonth(31, "December")
		};
		week = new CenturyWeek(
				"Monday",
				"Tuesday",
				"Wednesday",
				"Thursday",
				"Friday",
				"Saturday",
				"Sunday");
		day = new CenturyDay();
		converter = new UnitConverter(this);
	}

	public CenturyDateTime getCurrentDate() {
		return meta.getFirstDay().add(getSecondsPassed(), this);
	}

	/**
	 * Gets the seconds passed since the initial date of the calendar.
	 *
	 * @return amount of seconds since {@link CalendarMeta#getRefDate()}
	 */
	public long getSecondsPassed() {
		return meta.getRefDate().toInstant().until(Instant.now(), ChronoUnit.SECONDS);
	}

	public int getDaysOfYear() {
		return Arrays.stream(months).mapToInt(CenturyMonth::getDays).sum();
	}

	public CenturyMonth getMonth(int month) {
		return months[(month - 1) % months.length];
	}

	public UnitConverter convert() {
		return converter;
	}
}
