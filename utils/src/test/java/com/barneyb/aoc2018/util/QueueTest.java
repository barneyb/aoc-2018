package com.barneyb.aoc2018.util;

import org.junit.Test;

import java.util.Iterator;

public class QueueTest {

    @Test
    public void allInOne() {
        Queue<Integer> s = new Queue<Integer>();
        assert s.size() == 0;
        assert s.isEmpty();
        s.enqueue(1);
        s.enqueue(2);
        s.enqueue(3);
        assert s.size() == 3;
        assert ! s.isEmpty();
        Iterator<Integer> itr = s.iterator();
        assert itr.hasNext();
        assert itr.next() == 1;
        assert itr.hasNext();
        assert itr.next() == 2;
        assert itr.hasNext();
        assert itr.next() == 3;
        assert ! itr.hasNext();
        assert s.dequeue() == 1;
        assert s.dequeue() == 2;
        assert s.dequeue() == 3;
        assert s.size() == 0;
        assert s.isEmpty();
    }

}