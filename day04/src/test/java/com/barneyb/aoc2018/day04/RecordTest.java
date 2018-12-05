package com.barneyb.aoc2018.day04;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RecordTest {

    @Test
    public void strings() {
        //[1518-11-01 00:00] Guard #10 begins shift
        assertEquals(new Record(
                new Timestamp(1518, 11, 1, 0, 0),
                Action.BEGIN,
                10
        ), Record.parse("[1518-11-01 00:00] Guard #10 begins shift"));
        //[1518-11-01 00:05] falls asleep
        assertEquals(new Record(
                new Timestamp(1518, 11, 1, 0, 5),
                Action.FALL_ASLEEP
        ), Record.parse("[1518-11-01 00:05] falls asleep"));
        //[1518-11-01 00:25] wakes up
        assertEquals(new Record(
                new Timestamp(1518, 11, 1, 0, 25),
                Action.WAKE_UP
        ), Record.parse("[1518-11-01 00:25] wakes up"));
    }
}