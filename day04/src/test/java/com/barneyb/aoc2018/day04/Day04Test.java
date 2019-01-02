package com.barneyb.aoc2018.day04;

import com.barneyb.aoc2018.util.Answers;
import com.barneyb.aoc2018.util.Queue;
import org.junit.Test;

import static java.lang.Integer.valueOf;
import static org.junit.Assert.assertEquals;

public class Day04Test {

    static final String GUARD_10 = "[1518-11-01 00:00] Guard #10 begins shift\n" +
            "[1518-11-01 00:05] falls asleep\n" +
            "[1518-11-01 00:25] wakes up\n" +
            "[1518-11-01 00:30] falls asleep\n" +
            "[1518-11-01 00:55] wakes up\n";
    static final String GUARD_99 = "[1518-11-01 23:58] Guard #99 begins shift\n" +
            "[1518-11-02 00:40] falls asleep\n" +
            "[1518-11-02 00:50] wakes up\n";
    static final Record[] RECORDS_10 = {
            new Record(new Timestamp(1518, 11, 1, 0, 0), Action.BEGIN, 10),
            new Record(new Timestamp(1518, 11, 1, 0, 5), Action.FALL_ASLEEP),
            new Record(new Timestamp(1518, 11, 1, 0, 25), Action.WAKE_UP),
            new Record(new Timestamp(1518, 11, 1, 0, 30), Action.FALL_ASLEEP),
            new Record(new Timestamp(1518, 11, 1, 0, 55), Action.WAKE_UP),
    };
    static final Record[] RECORDS_99 = {
            new Record(new Timestamp(1518, 11, 1, 23, 58), Action.BEGIN, 99),
            new Record(new Timestamp(1518, 11, 2, 0, 40), Action.FALL_ASLEEP),
            new Record(new Timestamp(1518, 11, 2, 0, 50), Action.WAKE_UP),
    };
    static String EXAMPLE_INPUT = GUARD_10 +
            GUARD_99 +
            "[1518-11-03 00:05] Guard #10 begins shift\n" +
            "[1518-11-03 00:24] falls asleep\n" +
            "[1518-11-03 00:29] wakes up\n" +
            "[1518-11-04 00:02] Guard #99 begins shift\n" +
            "[1518-11-04 00:36] falls asleep\n" +
            "[1518-11-04 00:46] wakes up\n" +
            "[1518-11-05 00:03] Guard #99 begins shift\n" +
            "[1518-11-05 00:45] falls asleep\n" +
            "[1518-11-05 00:55] wakes up";

    @Test
    public void solve() {
        Answers a = new Day04().solve(EXAMPLE_INPUT);
        assertEquals("240", a.getPartOne());
        assertEquals("4455", a.getPartTwo());
    }

    @Test
    public void sleepiestGuard() {
        Iterable<Nap> naps = Log.parse(EXAMPLE_INPUT).naps();
        assertEquals(valueOf(10), Day04.sleepiestGuard(naps));
    }

    @Test
    public void getNaps() {
        assertEquals(new Queue<>(new Nap[] {
                new Nap(10, 5, 25),
                new Nap(10, 30, 55),
        }), new Log(RECORDS_10).naps());
    }

    @Test
    public void guardMinute() {
        assertEquals((25 << 8) + 59, Day04.guardMinute(25, 59));
    }

    @Test
    public void guardMinuteProduct() {
        assertEquals(4455, Day04.guardMinuteProduct((99 << 8) + 45));
    }

    @Test
    public void guardMinuteChain() {
        assertEquals(4455, Day04.guardMinuteProduct(Day04.guardMinute(99, 45)));
    }
}
