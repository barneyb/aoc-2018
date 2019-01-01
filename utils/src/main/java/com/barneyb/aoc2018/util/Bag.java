package com.barneyb.aoc2018.util;

import com.barneyb.aoc2018.api.Collection;

import java.util.Iterator;

public class Bag<E> implements Collection<E> {

    private final Deque<E> deque = new Deque<>();

    public Bag() {}

    public Bag(E[] elements) {
        for (E e : elements) {
            add(e);
        }
    }

    public void add(E element) {
        deque.addFirst(element);
    }

    public int size() {
        return deque.size();
    }

    public boolean isEmpty() {
        return deque.isEmpty();
    }

    public void clear() {
        deque.clear();
    }

    public Iterator<E> iterator() {
        return deque.iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bag)) return false;
        Bag<?> bag = (Bag<?>) o;
        return deque.equals(bag.deque);
    }

    @Override
    public int hashCode() {
        return deque.hashCode();
    }

    @Override
    public String toString() {
        return deque.toString();
    }

}
