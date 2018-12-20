package com.barneyb.aoc2018.util;

import java.util.Iterator;

public class Stack<E> implements Iterable<E> {

    private final Deque<E> deque = new Deque<>();

    public Stack() {}

    public Stack(E[] elements) {
        for (E e : elements) {
            push(e);
        }
    }

    public E peek() {
        return deque.peekFirst();
    }

    public void push(E element) {
        deque.addFirst(element);
    }

    public E pop() {
        return deque.removeFirst();
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
        if (!(o instanceof Stack)) return false;
        Stack<?> stack = (Stack<?>) o;
        return deque.equals(stack.deque);
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
