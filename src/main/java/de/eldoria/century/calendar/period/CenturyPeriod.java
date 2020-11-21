package de.eldoria.century.calendar.period;

import de.eldoria.century.calendar.CenturyCalendar;
import de.eldoria.century.calendar.units.CenturyUnit;
import de.eldoria.century.calendar.units.time.TimeUnit;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString
public class CenturyPeriod extends TimeUnit {

	public CenturyPeriod(int year, int day, int seconds) {
		super(year, day, seconds);
	}


	/**
	 * Create a new period from seconds
	 *
	 * @param seconds  seconds of period
	 * @param calendar calendar reference
	 *
	 * @return period with length of seconds
	 */
	public static CenturyPeriod of(long seconds, CenturyCalendar calendar) {
		int secondsOfYear = calendar.getDaysOfYear() * calendar.getDay().getDayCycleDuration();
		int years = (int) (seconds / secondsOfYear);
		seconds -= years * secondsOfYear;

		int days = (int) (seconds / calendar.getDay().getDayCycleDuration());
		seconds -= days * calendar.getDay().getDayCycleDuration();

		return CenturyPeriod.of(years, days, (int) seconds);
	}

	/**
	 * Create a new period from the difference between two {@link CenturyUnit}s
	 *
	 * @param from     first unit
	 * @param to       target unit
	 * @param calendar calendar reference
	 *
	 * @return century period with the length of the difference of the two century units
	 */
	public static CenturyPeriod of(CenturyUnit from, CenturyUnit to, CenturyCalendar calendar) {
		return of(to.toSeconds(calendar) - from.toSeconds(calendar), calendar);
	}

	/**
	 * Creates a period of the length of years.
	 *
	 * @param years years
	 *
	 * @return period with length of years.
	 */
	public static CenturyPeriod of(int years) {
		return of(years, 0);
	}

	/**
	 * Creates a period of the length of days and years.
	 *
	 * @param years years
	 * @param days  days
	 *
	 * @return period with length of years and days.
	 */
	public static CenturyPeriod of(int years, int days) {
		return of(years, days, 0);
	}

	/**
	 * Creates a period of the length of days and years and seconds.
	 *
	 * @param years   years
	 * @param days    days
	 * @param seconds seconds
	 *
	 * @return period with length of years and days and seconds.
	 */
	public static CenturyPeriod of(int years, int days, int seconds) {
		return new CenturyPeriod(years, days, seconds);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof CenturyPeriod)) return false;
		CenturyPeriod period = (CenturyPeriod) o;
		return days == period.days &&
				years == period.years &&
				seconds == period.seconds;
	}

	@Override
	public int hashCode() {
		return Objects.hash(days, years, seconds);
	}
}
