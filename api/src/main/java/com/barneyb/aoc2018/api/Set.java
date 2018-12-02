package com.barneyb.aoc2018.api;

import java.util.Iterator;

public interface Set<E> {

    // Set()

    void add(E element);

    void clear();

    int size();

    boolean isEmpty();

    boolean contains(Object o);

    Iterator<E> iterator();

}
