package com.barneyb.aoc2018.day15;

class Unit {

    private final char label;
    private int hitPoints = 300;
    private int attack = 3;

    Unit(char label) {
        this.label = label;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Unit)) return false;

        Unit unit = (Unit) o;

        return label == unit.label;
    }

    @Override
    public int hashCode() {
        return (int) label;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "label=" + label +
                ", hitPoints=" + hitPoints +
                '}';
    }

}
