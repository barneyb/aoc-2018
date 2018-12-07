package com.barneyb.aoc2018.util;

import java.util.Iterator;

public class Queue<E> implements Iterable<E> {

    private final Deque<E> deque = new Deque<>();

    public Queue() {}

    public Queue(E[] elements) {
        for (E e : elements) {
            enqueue(e);
        }
    }

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

    public void clear() {
        deque.clear();
    }

    public Iterator<E> iterator() {
        return deque.iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Queue)) return false;
        Queue<?> queue = (Queue<?>) o;
        return deque.equals(queue.deque);
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
