package com.barneyb.aoc2018.day07;

import com.barneyb.aoc2018.util.NamedDigraph;

public class Prerequisite extends NamedDigraph.AbstractEdge<String> {

    Prerequisite(String source, String target) {
        super(source, target);
    }

    @Override
    public String toString() {
        return source() + " is prereq of " + target();
    }

}
