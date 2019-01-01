package com.barneyb.aoc2018.api;

public interface Set<E> extends Collection<E> {

    // Set()

    void add(E element);

    void clear();

    boolean contains(Object o);

    void delete(E element);
}
