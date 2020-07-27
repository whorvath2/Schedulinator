package com.billhorvath.schedulinator;

import org.junit.Test;

import static org.junit.Assert.*;

public class CompanyScheduleTest {

	@Test
	public void areOverlapping() {

		CompanySchedule one = new CompanySchedule(1, 2);
		CompanySchedule two = new CompanySchedule(3, 2);
		assertTrue(CompanySchedule.areOverlapping(one, two));
	}


	@Test
	public void compare() {
	}
}
