package com.barneyb.aoc2018.util;

import org.junit.Test;

import java.util.Iterator;

public class BSTTest {

    @Test
    public void allInOne() {
        BST<Integer, Integer> st = new BST<>();
        assert st.isEmpty();
        assert st.size() == 0;
        st.put(3, 4);
        st.put(1, 2);
        st.put(3, 5);
        assert st.contains(1);
        assert ! st.contains(2);
        assert ! st.isEmpty();
        assert st.size() == 2;
        assert st.get(1) == 2;
        assert st.get(2) == null;
        assert st.get(3) == 5;
        st.clear();
        assert st.isEmpty();
        assert st.size() == 0;
    }

    @Test
    public void iterator() {
        BST<Integer, Integer> st = new BST<>();
        st.put(1, 2);
        st.put(3, 4);
        st.put(5, 6);
        Iterator<Integer> itr = st.keys().iterator();
        assert itr.hasNext();
        assert st.contains(itr.next());
        assert itr.hasNext();
        assert st.contains(itr.next());
        assert itr.hasNext();
        assert st.contains(itr.next());
        assert ! itr.hasNext();
    }

}