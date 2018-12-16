package com.barneyb.aoc2018.day15;

import com.barneyb.aoc2018.util.Point;

class Unit implements Comparable<Unit> {

    private final char label;
    private Point loc;
    private int hitPoints = 200;
    private int attack = 3;

    Unit(char label, Point loc) {
        this.label = label;
        this.loc = loc;
    }

    void defend(Unit attacker) {
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

    Point location() {
        return loc;
    }

    boolean isGoblin() {
        return label < 'a';
    }

    boolean isElf() {
        return ! isGoblin();
    }

    boolean adjacent(Unit u) {
        return loc.adjacent(u.loc);
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

    void update(Point p) {
        assert loc.adjacent(p);
        loc = p;
    }

    void increaseAttack(int n) {
        attack += n;
    }

}
