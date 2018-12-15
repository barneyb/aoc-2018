package com.barneyb.aoc2018.day15;

import com.barneyb.aoc2018.util.Point;

class Unit implements Comparable<Unit> {

    private final char label;
    private Point loc;
    private int hitPoints = 300;
    private int attack = 3;

    Unit(char label, Point loc) {
        this.label = label;
        this.loc = loc;
    }

    void attack(Unit attacker) {
        hitPoints -= attacker.attack;
    }

    boolean alive() {
        return hitPoints > 0;
    }

    char label() {
        return label;
    }

    int hitPoints() {
        return hitPoints;
    }

    boolean isGoblin() {
        return label >= 'a';
    }

    boolean isElf() {
        return ! isGoblin();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Unit)) return false;

        Unit unit = (Unit) o;

        return loc.equals(unit.loc);
    }

    @Override
    public int hashCode() {
        return loc.hashCode();
    }

    @Override
    public String toString() {
        return String.format("Unit{%s (%d) %s}", label, hitPoints, loc);
    }

    @Override
    public int compareTo(Unit o) {
        return loc.compareTo(o.loc);
    }
}
