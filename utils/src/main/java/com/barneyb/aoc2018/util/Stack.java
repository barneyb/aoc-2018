package com.barneyb.aoc2018.util;

import java.util.Iterator;

public class Stack<E> implements Iterable<E> {

    class Link {
        E element;
        Link next;

        public Link(E element, Link next) {
            this.element = element;
            this.next = next;
        }
    }

    private Link head;
    private int size = 0;

    public void push(E element) {
        head = new Link(element, head);
        size += 1;
    }

    public E pop() {
        Link l = head;
        head = l.next;
        size -= 1;
        return l.element;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Link curr = head;

            @Override
            public boolean hasNext() {
                return curr != null;
            }

            @Override
            public E next() {
                Link l = curr;
                curr = curr.next;
                return l.element;
            }
        };
    }

}
