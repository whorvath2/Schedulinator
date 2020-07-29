package com.billhorvath.schedulinator;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class ScheduleAnalyzerTest {

	@Test
	public void maxMeetings() {
		List<Integer> arrivals = Arrays.asList(1, 3, 5, 7);
		List<Integer> durations = Arrays.asList(2, 2, 2, 2);
		ScheduleAnalyzer analyzer = new ScheduleAnalyzer(arrivals, durations);
		assertEquals(4, analyzer.maxMeetings());

		durations = Arrays.asList(3, 2, 2, 2);
		analyzer = new ScheduleAnalyzer(arrivals, durations);
		assertEquals(3, analyzer.maxMeetings());

		durations = Arrays.asList(3, 3, 2, 2);
		analyzer = new ScheduleAnalyzer(arrivals, durations);
		assertEquals(3, analyzer.maxMeetings());

		durations = Arrays.asList(3, 3, 3, 2);
		analyzer = new ScheduleAnalyzer(arrivals, durations);
		assertEquals(2, analyzer.maxMeetings());


		CompanySchedule one = new CompanySchedule(2, 1);
		CompanySchedule two = new CompanySchedule(3, 1);
		CompanySchedule three = new CompanySchedule(4, 1);
		CompanySchedule four = new CompanySchedule(5, 1);
		CompanySchedule five = new CompanySchedule(6, 3);
		CompanySchedule six = new CompanySchedule(2, 2);
		CompanySchedule seven = new CompanySchedule(4, 2);
		CompanySchedule eight = new CompanySchedule(5, 2);
		CompanySchedule nine = new CompanySchedule(1, 4);
		CompanySchedule ten = new CompanySchedule(2, 7);

		arrivals = Arrays.asList(
				one.arrival(),
				two.arrival(),
				three.arrival(),
				four.arrival(),
				five.arrival(),
				six.arrival(),
				seven.arrival(),
				eight.arrival(),
				nine.arrival(),
				ten.arrival()
		);
		durations = Arrays.asList(
				one.duration(),
				two.duration(),
				three.duration(),
				four.duration(),
				five.duration(),
				six.duration(),
				seven.duration(),
				eight.duration(),
				nine.duration(),
				ten.duration()
		);
		analyzer = new ScheduleAnalyzer(arrivals, durations);
		assertEquals(5, analyzer.maxMeetings());
	}

	@Test
	public void createScheduleListSortedWorks(){

		CompanySchedule one = new CompanySchedule(1, 4);
		CompanySchedule two = new CompanySchedule(3, 3);
		CompanySchedule three = new CompanySchedule(5, 2);
		CompanySchedule four = new CompanySchedule(7, 1);

		List<Integer> arrivals = Arrays.asList(two.arrival(), three.arrival(), four.arrival(), one.arrival());
		List<Integer> durations = Arrays.asList(two.duration(), three.duration(), four.duration(), one.duration());

		ScheduleAnalyzer analyzer = new ScheduleAnalyzer(arrivals, durations);
		List<CompanySchedule> result = new ArrayList<>(analyzer.createSchedulesFromData.apply(arrivals, durations));
		assertEquals(result.get(0), one);
		assertEquals(result.get(1), two);
		assertEquals(result.get(2), three);
		assertEquals(result.get(3), four);
	}

	@Test
	public void sortsSchedulesWithSameArrivalTimesAndDifferentDurationsCorrectly(){

		CompanySchedule one = new CompanySchedule(1, 2);
		CompanySchedule two = new CompanySchedule(1, 3);
		CompanySchedule three = new CompanySchedule(1, 4);
		CompanySchedule four = new CompanySchedule(7, 1);

		List<Integer> arrivals = Arrays.asList(four.arrival(), three.arrival(), two.arrival(), one.arrival());
		List<Integer> durations = Arrays.asList(four.duration(), three.duration(), two.duration(), one.duration());

		ScheduleAnalyzer analyzer = new ScheduleAnalyzer(arrivals, durations);
		List<CompanySchedule> schedules = new ArrayList<>(analyzer.createSchedulesFromData.apply(arrivals, durations));
		assertEquals(one, schedules.get(0));
		assertEquals(two, schedules.get(1));
		assertEquals(three, schedules.get(2));
		assertEquals(four, schedules.get(3));
	}
}
