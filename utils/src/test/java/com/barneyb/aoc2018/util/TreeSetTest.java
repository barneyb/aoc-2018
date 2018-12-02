package com.barneyb.aoc2018.util;

import org.junit.Test;

public class TreeSetTest {

    @Test
    public void allInOne() {
        TreeSet<Integer> set = new TreeSet<>();
        assert set.isEmpty();
        assert set.size() == 0;
        set.add(3);
        set.add(1);
        set.add(3);
        assert set.contains(1);
        assert ! set.contains(2);
        assert ! set.isEmpty();
        assert set.size() == 2;
        set.clear();
        assert set.isEmpty();
        assert set.size() == 0;
    }

}