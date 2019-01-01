package com.barneyb.aoc2018.api;

public interface Collection<E> extends Iterable<E> {

    int size();

    default boolean isEmpty() {
        return size() == 0;
    }

}
