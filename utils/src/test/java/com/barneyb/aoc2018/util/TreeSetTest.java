package com.barneyb.aoc2018.util;

import org.junit.Test;

import java.util.Iterator;

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

    @Test
    public void iterator() {
        TreeSet<Integer> st = new TreeSet<>();
        st.add(1);
        st.add(3);
        st.add(5);
        Iterator<Integer> itr = st.iterator();
        assert itr.hasNext();
        assert st.contains(itr.next());
        assert itr.hasNext();
        assert st.contains(itr.next());
        assert itr.hasNext();
        assert st.contains(itr.next());
        assert ! itr.hasNext();
    }

}