package com.barneyb.aoc2018.util;

import java.util.Iterator;

public class Stack<E> implements Iterable<E> {

    private Deque<E> deque = new Deque<>();

    public void push(E element) {
        deque.push(element);
    }

    public E pop() {
        return deque.pop();
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
