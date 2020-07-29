package com.billhorvath.schedulinator;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;
import static java.util.stream.Collectors.*;

public class ScheduleAnalyzer {

	private final List<CompanySchedule> schedules;

	public ScheduleAnalyzer(List<Integer> arrivals, List<Integer> durations){

		assert arrivals != null && !arrivals.isEmpty() && arrivals.size() % 2 == 0;
		assert durations != null && !durations.isEmpty() && durations.size() % 2 == 0;
		this.schedules = Collections.unmodifiableList(
				createSchedulesFromData.apply(arrivals, durations));
	}

	final BiFunction<List<Integer>, List<Integer>, List<CompanySchedule>> createSchedulesFromData =
			(arrivals, durations) -> IntStream.range(0, arrivals.size())
					.mapToObj(inc -> new CompanySchedule(arrivals.get(inc), durations.get(inc)))
					.distinct()
					.sorted()
					.collect(Collectors.toList());


	final Function<List<CompanySchedule>, Map<Integer, List<CompanySchedule>>> organizeSchedulesByEndTime =
			(someSchedules) -> someSchedules
					.stream()
					.sorted(Comparator.comparingInt(CompanySchedule::endTime))
					.collect(groupingBy(CompanySchedule::endTime));


	final Function<Map<Integer, List<CompanySchedule>>, Integer> getLastEndTime = (map) -> map
			.keySet()
			.stream()
			.max(Integer::compareTo)
			.orElse(0);


	final BiFunction<List<CompanySchedule>, Integer, Integer> findLastEndTimeBefore =
			(someSchedules, startTime) -> someSchedules
			.stream()
			.filter(schedule -> schedule.endTime() <= startTime)
			.max(Comparator.comparingInt(CompanySchedule::endTime))
			.orElseGet(CompanySchedule::new)
			.endTime();


	final Function<List<CompanySchedule>, Integer> findShortestDuration = (someSchedules) ->
			someSchedules.stream()
			.min(Comparator.comparingInt(CompanySchedule::duration))
			.orElseGet(CompanySchedule::new)
			.duration();


	public int maxMeetings(){
		if (schedules.isEmpty()) return 0;
		List<CompanySchedule> mutableList = new ArrayList<>(schedules);
		Map<Integer, List<CompanySchedule>> map = organizeSchedulesByEndTime.apply(mutableList);

		int count = 0;
		int lastTime = getLastEndTime.apply(map);
		List<CompanySchedule> endAtLastTime = map.get(lastTime);

		while (endAtLastTime != null && !endAtLastTime.isEmpty()) {
			count++;
			mutableList.removeAll(endAtLastTime);
			int shortestDuration = findShortestDuration.apply(endAtLastTime);
			lastTime = findLastEndTimeBefore.apply(mutableList, lastTime - shortestDuration);
			endAtLastTime = map.get(lastTime);
		}
		return count;
	}
}
