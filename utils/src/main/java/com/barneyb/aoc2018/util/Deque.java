package com.barneyb.aoc2018.util;

import java.util.Iterator;

public class Deque<E> implements Iterable<E> {

    class Link {
        final E element;
        Link next;

        public Link(E element) {
            this.element = element;
        }

        public Link(E element, Link next) {
            this.element = element;
            this.next = next;
        }
    }

    private Link head;
    private Link tail;
    private int size = 0;

    public void push(E element) {
        head = new Link(element, head);
        if (tail == null) {
            tail = head;
        }
        size += 1;
    }

    public E pop() {
        Link l = head;
        head = l.next;
        if (head == null) {
            tail = null;
        }
        size -= 1;
        return l.element;
    }

    public void enqueue(E element) {
        if (isEmpty()) { // no "direction" yet
            push(element);
        } else {
            Link l = new Link(element);
            tail.next = l;
            tail = l;
            size += 1;
        }
    }

    public E dequeue() {
        return pop();
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
