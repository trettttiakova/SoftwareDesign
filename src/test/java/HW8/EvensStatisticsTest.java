package HW8;

import org.junit.Before;
import org.junit.Test;

import java.time.Instant;

import static org.junit.Assert.assertEquals;

public class EvensStatisticsTest {
    private final SetableClock fakeClock = new SetableClock(Instant.parse("2022-12-24T18:00:00.00Z"));
    private EventsStatistic es;
    double EPSILON = 0.000001d;
    private final String EVENT_1 = "event-1";
    private final String EVENT_2 = "event-2";

    @Before
    public void setup() {
        es = new EventsStatisticImpl(fakeClock);
    }

    @Test
    public void getEventStatisticByNameNoEventsTest() {
        incrementWithTime(es, EVENT_1, Instant.parse("2022-12-24T18:00:00.00Z"));
        incrementWithTime(es, EVENT_1, Instant.parse("2022-12-24T18:01:00.00Z"));
        incrementWithTime(es, EVENT_1, Instant.parse("2022-12-24T18:05:00.00Z"));

        fakeClock.setNow(Instant.parse("2022-12-24T18:59:00.00Z"));

        double rpm_2 = es.getEventStatisticByName(EVENT_2);
        assertEquals(0, rpm_2, EPSILON);
    }

    @Test
    public void getEventStatisticByNameTest() {
        incrementWithTime(es, EVENT_1, Instant.parse("2022-12-24T18:00:00.00Z"));
        incrementWithTime(es, EVENT_1, Instant.parse("2022-12-24T18:01:00.00Z"));
        incrementWithTime(es, EVENT_2, Instant.parse("2022-12-24T18:01:00.00Z"));
        incrementWithTime(es, EVENT_1, Instant.parse("2022-12-24T18:05:00.00Z"));

        fakeClock.setNow(Instant.parse("2022-12-24T18:59:00.00Z"));

        double rpm_1 = es.getEventStatisticByName(EVENT_1);
        assertEquals(3 / 60.0, rpm_1, EPSILON);

        double rpm_2 = es.getEventStatisticByName(EVENT_2);
        assertEquals(1 / 60.0, rpm_2, EPSILON);
    }

    @Test
    public void getEventStatisticByNameOldEventsNotCountedTest() {
        incrementWithTime(es, EVENT_1, Instant.parse("2022-12-24T18:00:00.00Z"));
        incrementWithTime(es, EVENT_1, Instant.parse("2022-12-24T18:01:00.00Z"));
        incrementWithTime(es, EVENT_1, Instant.parse("2022-12-24T19:01:00.00Z"));
        incrementWithTime(es, EVENT_1, Instant.parse("2022-12-24T19:05:00.00Z"));

        fakeClock.setNow(Instant.parse("2022-12-24T20:00:00.00Z"));
        double rpm_1 = es.getEventStatisticByName(EVENT_1);
        assertEquals(2 / 60.0, rpm_1, EPSILON);
    }

    @Test
    public void getAllEventStatisticTest() {
        incrementWithTime(es, EVENT_1, Instant.parse("2022-12-24T15:00:00.00Z"));
        incrementWithTime(es, EVENT_1, Instant.parse("2022-12-24T16:01:00.00Z"));
        incrementWithTime(es, EVENT_1, Instant.parse("2022-12-24T18:01:00.00Z"));
        incrementWithTime(es, EVENT_1, Instant.parse("2022-12-24T18:05:00.00Z"));

        incrementWithTime(es, EVENT_2, Instant.parse("2022-12-24T13:00:00.00Z"));
        incrementWithTime(es, EVENT_2, Instant.parse("2022-12-24T17:01:00.00Z"));
        incrementWithTime(es, EVENT_2, Instant.parse("2022-12-24T18:02:00.00Z"));
        incrementWithTime(es, EVENT_2, Instant.parse("2022-12-24T18:05:00.00Z"));
        incrementWithTime(es, EVENT_2, Instant.parse("2022-12-24T18:54:00.00Z"));

        fakeClock.setNow(Instant.parse("2022-12-24T19:00:00.00Z"));
        var allStats = es.getAllEventStatistic();

        double rpm_1 = allStats.get(EVENT_1);
        assertEquals(2 / 60.0, rpm_1, EPSILON);

        double rpm_2 = allStats.get(EVENT_2);
        assertEquals(3 / 60.0, rpm_2, EPSILON);
    }

    private void incrementWithTime(EventsStatistic es, String name, Instant time) {
        fakeClock.setNow(time);
        es.incEvent(name);
    }
}
