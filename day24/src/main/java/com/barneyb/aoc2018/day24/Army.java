package com.barneyb.aoc2018.day24;

import com.barneyb.aoc2018.util.TreeSet;

class Army {

    private final TreeSet<Group> groups;

    Army(TreeSet<Group> groups) {
        this.groups = groups;
    }

    public int units() {
        int units = 0;
        for (Group g : groups) {
            units += g.units();
        }
        return units;
    }

    public boolean alive() {
        for (Group g : groups) {
            if (g.alive()) return true;
        }
        return false;
    }

    public TreeSet<Group> groups() {
        return groups;
    }
}
