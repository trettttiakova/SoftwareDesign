package HW8;

import java.time.Instant;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class EventsStatisticImpl implements EventsStatistic {
    private final Map<String, List<Instant>> events;
    private final Clock clock;

    public EventsStatisticImpl(Clock clock) {
        this.events = new HashMap<>();
        this.clock = clock;
    }

    @Override
    public void incEvent(String name) {
        events.putIfAbsent(name, new LinkedList<>());
        events.get(name).add(clock.now());
    }

    @Override
    public double getEventStatisticByName(String name) {
        Instant now = clock.now();
        Instant hourAgo = now.minusSeconds(60 * 60);

        if (!events.containsKey(name)) {
            return 0;
        }

        var eventLastHourStats = events.get(name).stream()
            .filter(i -> i.isAfter(hourAgo) && i.isBefore(now))
            .toList();

        return eventLastHourStats.size() / 60.0;
    }

    @Override
    public Map<String, Double> getAllEventStatistic() {
        HashMap<String, Double> result = new HashMap<>();

        for (var name : events.keySet()) {
            result.put(name, getEventStatisticByName(name));
        }

        return result;
    }

    @Override
    public void printStatistic() {
        var stats = getAllEventStatistic();

        System.out.println("Events statistics for the last hour");
        for (var entry : stats.entrySet()) {
            System.out.printf("%s: rpm = .2%f", entry.getKey(), entry.getValue());
        }
    }
}
