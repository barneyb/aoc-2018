package com.barneyb.aoc2018.day24;

import com.barneyb.aoc2018.util.Scanner;
import com.barneyb.aoc2018.util.TreeSet;

class Army {

    static Army parse(String input) {
        return scan(new Scanner(input.trim()));
    }

    static Army scan(Scanner s) {
        Army a = new Army();
        while (! s.probe(':')) s.skipWord();
        s.skip(":\n");
        do {
            a.addGroup(Group.scan(s));
            if (! s.probe()) break;
            s.skip('\n');
        } while (s.probe() && ! s.probe('\n'));
        return a;
    }

    private final TreeSet<Group> groups = new TreeSet<>();

    public int units() {
        int units = 0;
        for (Group g : groups) {
            units += g.units();
        }
        return units;
    }

    public void addGroup(Group g) {
        groups.add(g);
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
