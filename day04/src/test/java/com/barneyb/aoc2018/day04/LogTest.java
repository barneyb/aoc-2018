package com.barneyb.aoc2018.day04;

import com.barneyb.aoc2018.util.Queue;
import org.junit.Test;

import static com.barneyb.aoc2018.day04.Day04Test.*;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class LogTest {

    @Test
    public void parse() {
        assertArrayEquals(RECORDS_10, Log.parse(GUARD_10).records());
        assertArrayEquals(RECORDS_99, Log.parse(GUARD_99).records());
    }

    @Test
    public void getNaps() {
        assertEquals(new Queue<>(new Nap[] {
                new Nap(10, 5, 25),
                new Nap(10, 30, 55),
        }), new Log(RECORDS_10).naps());
    }

    @Test
    public void sleepiestGuard() {
        Log log = Log.parse(EXAMPLE_INPUT);
        assertEquals(10, log.sleepiestGuard());
    }

    @Test
    public void guardMinute() {
        assertEquals((25 << 8) + 59, Log.guardMinute(25, 59));
    }

    @Test
    public void guardMinuteProduct() {
        assertEquals(4455, Log.guardMinuteProduct((99 << 8) + 45));
    }

    @Test
    public void guardMinuteChain() {
        assertEquals(4455, Log.guardMinuteProduct(Log.guardMinute(99, 45)));
    }

}