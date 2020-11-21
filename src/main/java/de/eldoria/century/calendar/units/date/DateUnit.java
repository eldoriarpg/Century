package de.eldoria.century.calendar.units.date;

import de.eldoria.century.calendar.CenturyCalendar;
import lombok.Getter;

import java.util.Objects;

@Getter
public class DateUnit extends SimpleDateUnit {
	/**
	 * Year of the date.
	 */
	protected final int year;

	public DateUnit(int year, int month, int day) {
		super(month, day);
		this.year = year;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof DateUnit)) return false;
		if (!super.equals(o)) return false;
		DateUnit that = (DateUnit) o;
		return year == that.year;
	}


	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), year);
	}

	@Override
	public long toSeconds(CenturyCalendar calendar) {
		long seconds = calendar.convert().yearsToSeconds(year);
		return seconds + super.toSeconds(calendar);
	}

	@Override
	public String toString() {
		return "DateUnit{" +
				"year=" + year +
				", month=" + month +
				", day=" + day +
				'}';
	}
}
