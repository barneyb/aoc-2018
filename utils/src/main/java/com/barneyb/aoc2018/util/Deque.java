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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Deque)) return false;

        Deque<?> deque = (Deque<?>) o;

        if (size != deque.size) return false;

        Iterator<?> ia = iterator();
        Iterator<?> ib = deque.iterator();
        while (ia.hasNext()) {
            if (! ia.next().equals(ib.next())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        for (Link l = head; l != null; l = l.next) {
            result = 31 * result + l.element.hashCode();
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (Iterator<E> itr = iterator(); itr.hasNext(); ) {
            E el = itr.next();
            sb.append(el);
            if (itr.hasNext()) {
                sb.append(',');
            }
        }
        return sb.append(']').toString();
    }
}
