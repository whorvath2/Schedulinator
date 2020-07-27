package com.billhorvath.schedulinator;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;


public class SchedulinatorTest {

	@Test
	public void returnsOneForOddNumberOfArguments(){
		String[] args = new String[]{"1", "2", "3", "4", "5"};
		assertEquals(1, Schedulinator.validate(args));
	}

	@Test
	public void returnsOneForNoArguments(){
		String[] args = new String[]{};
		assertEquals(1, Schedulinator.validate(args));
		args = null;
		assertEquals(1, Schedulinator.validate(args));
	}

	@Test
	public void returnsOneForTooManyArguments(){
		String[] args = new String[]{"1", "2", "3", "4", "5", "1", "2", "3", "4", "5", "1", "2", "3", "4", "5", "1",
				"2", "3", "4", "5", "1", "2", "3", "4", "5", "1", "2", "3", "4", "5", "1", "2", "3", "4", "5", "1",
				"2", "3", "4", "5", "1", "2", "3", "4", "5", "1", "2", "3", "4", "5", "1", "2", "3", "4", "5"};
		assertEquals(1, Schedulinator.validate(args));
	}

	@Test
	public void returnsTwoForNonIntegerArguments(){
		String[] args = new String[]{"1", "2", "3", "4", "5", "foobar"};
		assertEquals(2, Schedulinator.validate(args));
	}
}
