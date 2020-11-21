package de.eldoria.century.calendar.units.date;

import de.eldoria.century.calendar.CenturyCalendar;
import lombok.Getter;

import java.util.Objects;

@Getter
public class DateTimeUnit extends DateUnit {
	/**
	 * Seconds of a day
	 */
	protected final int seconds;

	public DateTimeUnit(int year, int month, int day, int seconds) {
		super(year, month, day);
		this.seconds = seconds;
	}

	@Override
	public long toSeconds(CenturyCalendar calendar) {
		return seconds + super.toSeconds(calendar);
	}

	@Override
	public String toString() {
		return "DateTimeUnit{" +
				"year=" + year +
				", month=" + month +
				", day=" + day +
				", seconds=" + seconds +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof DateTimeUnit)) return false;
		if (!super.equals(o)) return false;
		DateTimeUnit that = (DateTimeUnit) o;
		return seconds == that.seconds;
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), seconds);
	}
}
