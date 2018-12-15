package com.barneyb.aoc2018.day15;

import com.barneyb.aoc2018.util.Point;

class Unit implements Comparable<Unit> {

    private final char label;
    private Point at;
    private int hitPoints = 300;
    private int attack = 3;

    public Unit(char label, Point p) {
        this.label = label;
        this.at = p;
    }

    // units can't share a location
    @Override
    public int compareTo(Unit o) {
        return at.compareTo(o.at);
    }

    public void attack(Unit attacker) {
        hitPoints -= attacker.attack;
    }

    public boolean alive() {
        return hitPoints > 0;
    }

    public char label() {
        return label;
    }

    public Point at() {
        return at;
    }

    public int hitPoints() {
        return hitPoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Unit)) return false;

        Unit unit = (Unit) o;

        return at.equals(unit.at);
    }

    @Override
    public int hashCode() {
        return at.hashCode();
    }
}
