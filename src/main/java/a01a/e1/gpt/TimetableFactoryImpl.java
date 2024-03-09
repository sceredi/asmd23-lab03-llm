package a01a.e1.gpt;

import java.util.*;
import java.util.function.BiFunction;

public class TimetableFactoryImpl implements TimetableFactory {

	private static class TimetableImpl implements Timetable {
		private final Pair<Set<String>, Set<String>> data;
		private final Map<Pair<String, String>, Integer> sums;

		private TimetableImpl(Pair<Set<String>, Set<String>> data, Map<Pair<String, String>, Integer> sums) {
			this.data = data;
			this.sums = sums;
		}

		@Override
		public Set<String> activities() {
			return Collections.unmodifiableSet(data.get1());
		}

		@Override
		public Set<String> days() {
			return Collections.unmodifiableSet(data.get2());
		}

		@Override
		public int getSingleData(String activity, String day) {
			return sums.getOrDefault(new Pair<>(activity, day), 0);
		}

		@Override
		public int sums(Set<String> activities, Set<String> days) {
			int sum = 0;
			for (String activity : activities) {
				for (String day : days) {
					sum += getSingleData(activity, day);
				}
			}
			return sum;
		}

		public TimetableImpl addHour(String activity, String day) {
			Set<String> newActivities = new HashSet<>(data.get1());
			newActivities.add(activity);
			Set<String> newDays = new HashSet<>(data.get2());
			newDays.add(day);
			Map<Pair<String, String>, Integer> newSums = new HashMap<>(sums);
			Pair<String, String> key = new Pair<>(activity, day);
			newSums.put(key, newSums.getOrDefault(key, 0) + 1);
			return new TimetableImpl(new Pair<>(newActivities, newDays), newSums);
		}

	}

	@Override
	public Timetable empty() {
		return new TimetableImpl(new Pair<>(Collections.emptySet(), Collections.emptySet()), new HashMap<>());
	}

	@Override
	public Timetable single(String activity, String day) {
		TimetableImpl timetable = (TimetableImpl) empty();
		return timetable.addHour(activity, day);
	}

	@Override
	public Timetable join(Timetable table1, Timetable table2) {
		Set<String> activities = new HashSet<>(table1.activities());
		activities.addAll(table2.activities());
		Set<String> days = new HashSet<>(table1.days());
		days.addAll(table2.days());
		TimetableImpl mergedTable = (TimetableImpl) empty();
		for (String activity : activities) {
			for (String day : days) {
				int sum = table1.getSingleData(activity, day) + table2.getSingleData(activity, day);
				for (int i = 0; i < sum; i++) {
					mergedTable = mergedTable.addHour(activity, day);
				}
			}
		}
		return mergedTable;
	}

	@Override
	public Timetable cut(Timetable timetable, BiFunction<String, String, Integer> cutoff) {
		TimetableImpl timetableImpl = (TimetableImpl) timetable;
		Map<Pair<String, String>, Integer> newSums = new HashMap<>();
		for (String activity : timetableImpl.data.get1()) {
			for (String day : timetableImpl.data.get2()) {
				int hours = cutoff.apply(activity, day);
				if (hours > 0) {
					Pair<String, String> key = new Pair<>(activity, day);
					newSums.put(key, Math.min(timetableImpl.sums.getOrDefault(key, 0), hours));
				}
			}
		}
		return new TimetableImpl(timetableImpl.data, newSums);
	}
}
