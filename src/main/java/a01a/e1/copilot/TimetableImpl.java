package a01a.e1.copilot;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TimetableImpl implements Timetable {
	private final Map<Pair<String, String>, Integer> data;

	public TimetableImpl() {
		this.data = new HashMap<>();
	}

	public TimetableImpl(Map<Pair<String, String>, Integer> data) {
		this.data = data;
	}

	@Override
	public Timetable addHour(String activity, String day) {
		final Map<Pair<String, String>, Integer> newData = new HashMap<>(this.data);
		newData.put(new Pair<>(activity, day), newData.getOrDefault(new Pair<>(activity, day), 0) + 1);
		return new TimetableImpl(newData);
	}

	@Override
	public Set<String> activities() {
		return this.data.keySet().stream().map(Pair::get1).collect(HashSet::new, HashSet::add, HashSet::addAll);
	}

	@Override
	public Set<String> days() {
		return this.data.keySet().stream().map(Pair::get2).collect(HashSet::new, HashSet::add, HashSet::addAll);
	}

	@Override
	public int getSingleData(String activity, String day) {
		return this.data.getOrDefault(new Pair<>(activity, day), 0);
	}

	@Override
	public int sums(Set<String> activities, Set<String> days) {
		return this.data.entrySet().stream()
				.filter(e -> activities.contains(e.getKey().get1()) && days.contains(e.getKey().get2()))
				.mapToInt(Map.Entry::getValue).sum();

	}

}
