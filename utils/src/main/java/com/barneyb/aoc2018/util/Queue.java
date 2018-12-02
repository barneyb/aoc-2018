package com.barneyb.aoc2018.util;

import java.util.Iterator;

public class Queue<E> implements Iterable<E> {

    private Deque<E> deque = new Deque<>();

    public void enqueue(E element) {
        deque.enqueue(element);
    }

    public E dequeue() {
        return deque.dequeue();
    }

    public int size() {
        return deque.size();
    }

    public boolean isEmpty() {
        return deque.isEmpty();
    }

    public Iterator<E> iterator() {
        return deque.iterator();
    }

}
