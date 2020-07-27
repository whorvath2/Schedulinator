package com.billhorvath.schedulinator;

import java.util.*;

public class ScheduleAnalyzer {

	private List<Integer> arrivals;
	private List<Integer> durations;

	public ScheduleAnalyzer(List<Integer> arrivals, List<Integer> durations){

		assert arrivals != null && !arrivals.isEmpty() && arrivals.size() % 2 == 0;
		assert durations != null && !durations.isEmpty() && durations.size() % 2 == 0;

		this.arrivals = Collections.unmodifiableList(arrivals);
		this.durations = Collections.unmodifiableList(durations);
	}

	public int maxMeetings(){
		return 0;
	}
}
