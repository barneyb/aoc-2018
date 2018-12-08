package com.barneyb.aoc2018.day07;

import com.barneyb.aoc2018.util.BST;
import com.barneyb.aoc2018.util.Digraph;
import com.barneyb.aoc2018.util.TreeSet;

public class SDG {

    private final String[] toName;
    private final BST<String, Integer> toSite;
    private final Digraph g;

    public SDG(Prerequisite[] ps) {
        TreeSet<String> names = new TreeSet<>();
        for (Prerequisite p : ps) {
            names.add(p.item);
            names.add(p.prereq);
        }
        toName = new String[names.size()];
        toSite = new BST<>();
        int nameIdx = 0;
        for (String name: names) {
            toName[nameIdx] = name;
            toSite.put(name, nameIdx);
            nameIdx += 1;
        }
        g = new Digraph(names.size());
        for (Prerequisite p : ps) {
            g.addEdge(getSite(p.item), getSite(p.prereq));
        }
    }

    public Digraph graph() {
        return g;
    }

    public String getName(int site) {
        return toName[site];
    }

    public int getSite(String name) {
        return toSite.get(name);
    }

}
