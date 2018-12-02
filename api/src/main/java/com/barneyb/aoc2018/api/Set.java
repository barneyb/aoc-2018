package com.barneyb.aoc2018.api;

import java.util.Iterator;

public interface Set<E> extends Iterable<E> {

    // Set()

    void add(E element);

    void clear();

    int size();

    default boolean isEmpty() {
        return size() == 0;
    }

    boolean contains(Object o);

    Iterator<E> iterator();

}
