package com.barneyb.aoc2018.day07;

import com.barneyb.aoc2018.util.BST;
import com.barneyb.aoc2018.util.Digraph;
import com.barneyb.aoc2018.util.TreeSet;

public class NamedDigraph<Name extends Comparable<Name>> {

    public interface Edge<T> {
        T getSource();
        T getTarget();
    }

    private final Name[] toName;
    private final BST<Name, Integer> toSite;
    private final Digraph g;

    public NamedDigraph(Edge<Name>[] ps) {
        TreeSet<Name> names = new TreeSet<>();
        for (Edge<Name> p : ps) {
            names.add(p.getSource());
            names.add(p.getTarget());
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
            g.addEdge(getSite(p.getSource()), getSite(p.getTarget()));
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
