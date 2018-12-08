package com.barneyb.aoc2018.util;

public class NamedDigraph<Name extends Comparable<Name>> {

    public interface Edge<T> {
        T source();
        T target();
    }

    public static abstract class AbstractEdge<T extends Comparable<T>> implements Edge<T> {
        final T source;
        final T target;

        public AbstractEdge(T source, T target) {
            this.source = source;
            this.target = target;
        }

        @Override
        public T source() {
            return source;
        }

        @Override
        public T target() {
            return target;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof AbstractEdge)) return false;

            //noinspection unchecked
            AbstractEdge edge = (AbstractEdge) o;

            if (!source.equals(edge.source)) return false;
            return this.target.equals(edge.target);
        }

        @Override
        public int hashCode() {
            int result = source.hashCode();
            result = 31 * result + target.hashCode();
            return result;
        }
    }

    private final Name[] toName;
    private final BST<Name, Integer> toSite;
    private final Digraph g;

    public NamedDigraph(Edge<Name>[] ps) {
        TreeSet<Name> names = new TreeSet<>();
        for (Edge<Name> p : ps) {
            names.add(p.source());
            names.add(p.target());
        }
        //noinspection unchecked
        toName = (Name[]) new Comparable[names.size()];
        toSite = new BST<>();
        int nameIdx = 0;
        for (Name name: names) {
            toName[nameIdx] = name;
            toSite.put(name, nameIdx);
            nameIdx += 1;
        }
        g = new Digraph(names.size());
        for (Edge<Name> p : ps) {
            g.addEdge(getSite(p.source()), getSite(p.target()));
        }
    }

    public Digraph graph() {
        return g;
    }

    public Name getName(int site) {
        return toName[site];
    }

    public int getSite(Name name) {
        return toSite.get(name);
    }

}
