package com.barneyb.aoc2018.day02;

import org.junit.Test;

public class HistogramTest {

    @Test
    public void everythingExists() {
        Histogram<Integer> h = new Histogram<Integer>();
        assert h.contains(12345);
        assert h.get(5432) == 0;
    }

    @Test
    public void fromString() {
        Histogram<Character> h = Histogram.fromString("abbccc");
        assert h.get('a') == 1;
        assert h.get('b') == 2;
        assert h.get('c') == 3;

        h = Histogram.fromString("cbacbc");
        assert h.get('a') == 1;
        assert h.get('b') == 2;
        assert h.get('c') == 3;
    }

}