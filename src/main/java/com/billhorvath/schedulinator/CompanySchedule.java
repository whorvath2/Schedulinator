package com.billhorvath.schedulinator;

import java.util.*;

/**
 * Tracks the arrival time and duration of stay for a company's representatives.
 * Company Schedule objects are immutable by design.
 */
public class CompanySchedule implements Comparator<CompanySchedule>, Comparable<CompanySchedule> {

	private final int arrival;
	private final int duration;


	/**
	 * Default (no-arg) constructor.
	 */
	public CompanySchedule(){
		this(0, 0);
	}


	/**
	 * Constructor which defines all of this company schedule's field values.
	 *
	 * @param arrival The time at which the company's representatives will arrive.
	 * @param duration The length of time of the company's presentation.
	 */
	public CompanySchedule(int arrival, int duration){
		if (arrival < 0 || duration < 0){
			throw new IllegalArgumentException("Neither arrival nor duration may be less than zero.");
		}
		this.arrival = arrival;
		this.duration = duration;
	}


	/**
	 * Returns <code>true</code> if one and two are overlapping, such that either the end of one's schedule lies in
	 * two's schedule, or the beginning of two's schedule lies in one's schedule; <code>false</code> otherwise. The
	 * schedule is demarcated by each value's arrival (at the beginning) and the sum of the arrival and the duration
	 * (at the end.) Note that if one's beginning is equal to two's end, or one's end is equal to two's beginning,
	 * this method will return false.
	 *
	 * @param one The schedule to be compared for overlap with two.
	 * @param two The schedule to be compared for overlap with one.
	 * @return true if one and two overlap.
	 */
	public static boolean areOverlapping(CompanySchedule one, CompanySchedule two){
		if (one.equals(two)){
			return true;
		}
		Range oneRange = new Range(one.arrival, one.arrival + one.duration);
		Range twoRange = new Range(two.arrival, two.arrival + two.duration);
		return (oneRange.inRange(twoRange.top) || oneRange.inRange(twoRange.bottom))
				|| (twoRange.inRange(oneRange.top) || twoRange.inRange(oneRange.bottom));
	}


	/**
	 * <p></p>Returns <code>true</code> under any of the following conditions:</p>
	 *
	 * <ul>
	 *     <li>The supplied company schedule is equal to this company schedule;</li>
	 *     <li>The supplied company schedule's arrival is between this company's arrival and end time;</li>
	 *     <li>The supplied company schedule's end time is between this company's arrival and end time.</li>
	 * </ul>
	 *
	 * <p>This method returns <code>false</code> otherwise.</p>
	 *
	 * @param other The other company schedule whose scheduled arrival and end time may overlap with this company
	 *                 schedule's arrival and end time.
	 * @return <code>true</code> if the supplied company schedule is equal to this company schedule, or if the
	 * timing of their arrivals or end times overlap.
	 */
	public boolean overlaps(CompanySchedule other){

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
				return one.compareTo(two);
			}
			return -1;
		}
		return (two != null)
			? 1
			: 0;
	}

	@Override
	public int compareTo(CompanySchedule other) {
		if (other != null){
			return this.arrival == other.arrival
					? Integer.compare(this.duration, other.duration)
					: Integer.compare(this.arrival, other.arrival);
		}
		return 1;
	}

	public int endTime(){
		return this.arrival + this.duration;
	}

	public int arrival(){
		return this.arrival;
	}

	public int duration(){
		return this.duration;
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


		/**
		 * Returns <code>true</code> if the supplied value is exclusively inside of this range; <code>false</code>
		 * otherwise. I.e., returns true if the supplied value is greater than this range's bottom and less than
		 * its top.
		 *
		 * @param value The value to be check to see if it falls between the bottom and top of this range.
		 * @return true if value falls within this range; false otherwise.
		 */
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


	@Override
	public int hashCode() {
		return arrival * 17 + duration * 17;
	}


	@Override
	public boolean equals(Object obj) {
		return (obj == this || obj instanceof CompanySchedule && equals((CompanySchedule)obj));
	}

	private boolean equals(CompanySchedule other){
		return other.duration == this.duration
				&& other.arrival == this.arrival;
	}
}
