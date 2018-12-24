package com.barneyb.aoc2018.util;

import com.barneyb.aoc2018.api.Set;

import java.util.Iterator;

public class TreeSet<E extends Comparable<E>> implements Set<E> {

    private static final Object VALUE = new int[0]; // this is serializable

    private final BST<E, Object> tree = new BST<>();

    public TreeSet() {}

    public TreeSet(E[] elements) {
        for (E e : elements) {
            add(e);
        }
    }

    public void add(E element) {
        tree.put(element, VALUE);
    }

    public void clear() {
        tree.clear();
    }

    public int size() {
        return tree.size();
    }

    public boolean contains(Object o) {
        if (o == null) return false;
        @SuppressWarnings("unchecked")
        E element = (E) o;
        return tree.contains(element);
    }

    @Override
    public void delete(E element) {
        tree.delete(element);
    }

    public E min() {
        return tree.min();
    }

    public void deleteMin() {
        tree.deleteMin();
    }

    public E max() {
        return tree.max();
    }

    public void deleteMax() {
        tree.deleteMax();
    }

    @Override
    public Iterator<E> iterator() {
        return tree.keys().iterator();
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

    public TreeSet<E> duplicate() {
        TreeSet<E> dupe = new TreeSet<>();
        for (E e : this) {
            dupe.add(e);
        }
        return dupe;
    }
}
