package com.barneyb.aoc2018.day04;

import com.barneyb.aoc2018.util.Answers;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day04Test {

    private static String EXAMPLE_INPUT = "[1518-11-01 00:00] Guard #10 begins shift\n" +
            "[1518-11-01 00:05] falls asleep\n" +
            "[1518-11-01 00:25] wakes up\n" +
            "[1518-11-01 00:30] falls asleep\n" +
            "[1518-11-01 00:55] wakes up\n" +
            "[1518-11-01 23:58] Guard #99 begins shift\n" +
            "[1518-11-02 00:40] falls asleep\n" +
            "[1518-11-02 00:50] wakes up\n" +
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
