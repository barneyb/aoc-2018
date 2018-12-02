package com.barneyb.aoc2018.util;

import org.junit.Test;

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
        assert st.size() == 0;}
}