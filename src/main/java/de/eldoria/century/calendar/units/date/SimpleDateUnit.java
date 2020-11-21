package de.eldoria.century.calendar.units.date;

import de.eldoria.century.calendar.CalendarMeta;
import de.eldoria.century.calendar.CenturyCalendar;
import de.eldoria.century.calendar.units.CenturyTimeUnit;
import lombok.Getter;

import java.util.Objects;

@Getter
public class SimpleDateUnit implements CenturyTimeUnit {
	/**
	 * Month of a undefined year.
	 */
	protected final int month;
	/**
	 * Day of a undefined month.
	 */
	protected final int day;

	public SimpleDateUnit(int month, int day) {
		this.month = month;
		this.day = day;
	}

	/**
	 * Converts the current date to seconds.
	 * <p>
	 * The current day is excluded since it hasn passed yet.
	 * <p>
	 * The seconds are the seconds since the first year defined in {@link CalendarMeta#getYearOffset()}}
	 *
	 * @param calendar calendar as reference
	 *
	 * @return date in seconds since first day.
	 */
	@Override
	public long toSeconds(CenturyCalendar calendar) {
		return calendar.convert().monthToSeconds(month) + calendar.convert().dayToSeconds(day);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof SimpleDateUnit)) return false;
		SimpleDateUnit that = (SimpleDateUnit) o;
		return month == that.month &&
				day == that.day;
	}

	@Override
	public int hashCode() {
		return Objects.hash(month, day);
	}

	@Override
	public String toString() {
		return "SimpleDateUnit{" +
				"month=" + month +
				", day=" + day +
				'}';
	}
}
