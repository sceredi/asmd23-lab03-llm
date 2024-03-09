package a01a.e1.copilot;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;

class TimetableFactoryImpl implements TimetableFactory {

    @Override
    public Timetable empty() {
        return new TimetableImpl();
    }

    @Override
    public Timetable single(String activity, String day) {
        return new TimetableImpl().addHour(activity, day);
    }

    @Override
    public Timetable join(Timetable table1, Timetable table2) {
        Set<String> activities = new HashSet<>(table1.activities());
        activities.addAll(table2.activities());
        Set<String> days = new HashSet<>(table1.days());
        days.addAll(table2.days());
        Timetable mergedTable = new TimetableImpl();
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
    public Timetable cut(Timetable table, BiFunction<String, String, Integer> bounds) {
        Map<Pair<String, String>, Integer> sums = new HashMap<>();
        for (String activity : table.activities()) {
            for (String day : table.days()) {
                sums.put(new Pair<>(activity, day),
                        Math.min(table.sums(Set.of(activity), Set.of(day)), bounds.apply(activity, day)));
            }
        }
        return new TimetableImpl(sums);
    }

}
