package com.billhorvath.schedulinator;

import org.junit.Test;

import static org.junit.Assert.*;

public class CompanyScheduleTest {

	@Test
	public void areOverlappingWorks() {

		CompanySchedule one = new CompanySchedule(1, 3); // 1-4
		CompanySchedule two = new CompanySchedule(3, 2); // 3-5
		assertTrue(CompanySchedule.areOverlapping(one, two)); // 1-4 overlaps with 3-5

		two = new CompanySchedule(4, 2); // 4-6
		assertFalse(CompanySchedule.areOverlapping(one, two)); // 1-4 doesn't overlap with 4-6

		two = new CompanySchedule(2, 1); // 2-3

		assertTrue(CompanySchedule.areOverlapping(one, two)); // 1-4 overlaps with 2-3
	}

	@Test
	public void compareWorks() {
		CompanySchedule one = new CompanySchedule(1, 2);
		CompanySchedule two = new CompanySchedule(3, 2);
		assertEquals(-1, one.compare(one, two));
		assertEquals(1, one.compare(two, one));
		assertEquals(0, one.compare(null, null));
		assertEquals(-1, one.compare(one, null));
		assertEquals(1, one.compare(null, one));
	}
}
