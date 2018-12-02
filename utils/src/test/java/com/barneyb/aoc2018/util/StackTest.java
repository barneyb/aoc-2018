package com.barneyb.aoc2018.util;

import org.junit.Test;

import java.util.Iterator;

public class StackTest {

    @Test
    public void allInOne() {
        Stack<Integer> s = new Stack<Integer>();
        assert s.size() == 0;
        assert s.isEmpty();
        s.push(1);
        s.push(2);
        s.push(3);
        assert s.size() == 3;
        assert ! s.isEmpty();
        Iterator<Integer> itr = s.iterator();
        assert itr.hasNext();
        assert itr.next() == 3;
        assert itr.hasNext();
        assert itr.next() == 2;
        assert itr.hasNext();
        assert itr.next() == 1;
        assert ! itr.hasNext();
        assert s.pop() == 3;
        assert s.pop() == 2;
        assert s.pop() == 1;
        assert s.size() == 0;
        assert s.isEmpty();
    }

}