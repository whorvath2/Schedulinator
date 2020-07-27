package com.billhorvath.schedulinator;

import java.util.*;

/**
 * Tracks the arrival time and duration of stay for a company.
 */
public class CompanySchedule implements Comparator<CompanySchedule> {

	private final int arrival;
	private final int duration;

	public CompanySchedule(int arrival, int duration){
		if (arrival < 0 || duration < 0){
			throw new IllegalArgumentException("Neither arrival nor duration may be less than zero.");
		}
		this.arrival = arrival;
		this.duration = duration;
	}

	/**
	 *
	 * @param one
	 * @param two
	 * @return
	 */
	public static boolean areOverlapping(CompanySchedule one, CompanySchedule two){
		Range oneRange = new Range(one.arrival, one.arrival + one.duration);
		Range twoRange = new Range(two.arrival, two.arrival + two.duration);
		boolean result = (oneRange.inRange(twoRange.top) || oneRange.inRange(twoRange.bottom))
				|| (twoRange.inRange(oneRange.top) || twoRange.inRange(oneRange.bottom));
		return result;
	}

	/**
	 *
	 * @param other
	 * @return
	 */
	public boolean isOverlapping(CompanySchedule other){
		return areOverlapping(this, other);
	}

	/**
	 * Returns -1, 0, or 1 as one.arrival is less than, equal to, or greater than two.arrival. More specifically:
	 *
	 * <ul>
	 *     <li>If one and two are not null, returns the comparison of one's arrival to two's as per
	 *     {@link Integer#compare(int, int)}.</li>
	 *     <li>If one is null and two is not null, returns 1.</li>
	 *     <li>If one is not null and two is null, returns -1.</li>
	 *     <li>If one and two are null, returns 0./li>
	 * </ul>
	 *
	 * @param one The company schedule to be compared to two.
	 * @param two The company schedule to be compared to one.
	 * @return -1, 0, or 1 as one.arrival is less than, equal to, or greater than two.arrival.
	 * @see Integer#compare(int, int)
	 */
	@Override
	public int compare(CompanySchedule one, CompanySchedule two) {
		if (one != null){
			if (two != null){
				return Integer.compare(one.arrival, two.arrival);
			}
			return -1;
		}
		return (two != null)
			? 1
			: 0;
	}


	/**
	 * A simple number range defined by a top and bottom value.
	 */
	private static class Range{

		private final int bottom, top;

		public Range(int a, int b){
			boolean aFirst = a < b;
			bottom = aFirst ? a : b;
			top = aFirst ? b : a;
		}

		public boolean inRange(int value){
			return value > bottom && value < top;
		}

		@Override
		public String toString(){
			return "Range: {" +
					" bottom: " + bottom +
					"; top: " + top + ";}";
		}
	}

	@Override
	public String toString(){
		return "CompanySchedule: {" +
				" arrival: " + arrival +
				"; duration: " + duration + ";}";
	}
}
