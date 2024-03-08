package a01a.e1.gpt;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;

public class TimetableFactoryImpl implements TimetableFactory {
    @Override
    public Timetable empty() {
        return new TimetableImpl();
    }

    @Override
    public Timetable single(String activity, String day) {
        Timetable timetable = new TimetableImpl();
        timetable.addHour(activity, day);
        return timetable;
    }

    @Override
    public Timetable join(Timetable timetable1, Timetable timetable2) {
        Timetable joinedTimetable = new TimetableImpl();
        joinedTimetable.merge(timetable1);
        joinedTimetable.merge(timetable2);
        return joinedTimetable;
    }

    @Override
    public Timetable cut(Timetable timetable, BiFunction<String, String, Integer> maxHoursFunction) {
        Timetable cutTimetable = new TimetableImpl();
        for (String activity : timetable.activities()) {
            for (String day : timetable.days()) {
                int maxHours = maxHoursFunction.apply(activity, day);
                int currentHours = timetable.getSingleData(activity, day);
                int hoursToCut = currentHours - maxHours;
                if (hoursToCut > 0) {
                    for (int i = 0; i < hoursToCut; i++) {
                        cutTimetable.removeHour(activity, day);
                    }
                }
            }
        }
        return cutTimetable;
    }

    // You can add additional methods here if necessary
}

