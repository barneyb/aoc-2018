package com.barneyb.aoc2018.day15;

import com.barneyb.aoc2018.util.Point;

class Unit implements Comparable<Unit> {

    public static final int FULL_HEALTH = 200;

    private final char label;
    private Point loc;
    private int hitPoints = FULL_HEALTH;
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

    float health() {
        return 1f * hitPoints / FULL_HEALTH;
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

        if (label != unit.label) return false;
        return loc.equals(unit.loc);
    }

    @Override
    public int hashCode() {
        int result = (int) label;
        result = 31 * result + loc.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return String.format("Unit{%s (%d) %s}", label, hitPoints, loc);
    }

    @Override
    public int compareTo(Unit o) {
        int c = loc.compareTo(o.loc);
        if (c != 0) return c;
        return label - o.label;
    }

    void update(Point p) {
        assert loc.adjacent(p);
        loc = p;
    }

    void updateArmament(int attack) {
        this.attack = attack;
    }

}
