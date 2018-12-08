package com.barneyb.aoc2018.util;

import java.util.Iterator;

public class List<E> implements Iterable<E> {

    private E[] elements;
    private int size;

    public List() {
        clear();
    }

    public List(E[] elements) {
        clear();
        for (E e : elements) {
            add(e);
        }
    }

    public void add(E element) {
        ensureCapacity(size + 1);
        elements[size++] = element;
    }

    public void insert(E element, int i) {
        ensureCapacity(size + 1);
        System.arraycopy(elements, i, elements, i + 1, size - i);
        size += 1;
        elements[i] = element;
    }

    private void ensureCapacity(int c) {
        if (c <= elements.length) {
            return; // we're good
        }
        //noinspection unchecked
        E[] next = (E[]) new Object[elements.length * 2];
        System.arraycopy(elements, 0, next, 0, size);
        elements = next;
    }

    public E get(int i) {
        return elements[i];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void clear() {
        //noinspection unchecked
        elements = (E[]) new Object[10];
        size = 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int i = 0;

            @Override
            public boolean hasNext() {
                return i < size;
            }

            @Override
            public E next() {
                return elements[i++];
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof List)) return false;

        List<?> list = (List<?>) o;

        if (size != list.size) return false;
        Iterator<E> ai = iterator();
        Iterator<?> bi = list.iterator();
        while (ai.hasNext()) {
            Object a = ai.next();
            Object b = bi.next();
            if (a == null) return b == null;
            if (! a.equals(b)) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        for (E el : this) {
            if (el != null) {
                result = 31 * result + el.hashCode();
            }
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
