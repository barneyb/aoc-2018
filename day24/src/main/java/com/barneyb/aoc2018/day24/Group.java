package com.barneyb.aoc2018.day24;

import com.barneyb.aoc2018.api.Set;

class Group implements Comparable<Group> {

    private int units;
    private int hitPoints;
    private int attack;
    private AttackType attackType;
    private int initiative;
    private Set<AttackType> weaknesses;
    private Set<AttackType> immunities;

    public int effectivePower() {
        return units * attack;
    }

    public int units() {
        return units;
    }

    public void units(int u) {
        units = u;
    }

    public int hitPoints() {
        return hitPoints;
    }

    public void hitPoints(int hp) {
        hitPoints = hp;
    }

    public int attack() {
        return attack;
    }

    public void attack(AttackType t, int a) {
        attackType = t;
        attack = a;
    }

    public AttackType attackType() {
        return attackType;
    }

    public int initiative() {
        return initiative;
    }

    public void initiative(int i) {
        initiative = i;
    }

    public boolean alive() {
        return units > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Group)) return false;
        Group group = (Group) o;
        if (effectivePower() != group.effectivePower()) return false;
        return initiative == group.initiative;
    }

    @Override
    public int hashCode() {
        int result = units;
        result = 31 * result + attack;
        result = 31 * result + initiative;
        return result;
    }

    @Override
    public int compareTo(Group o) {
        int c = o.effectivePower() - effectivePower();
        if (c != 0) return c;
        c = o.initiative - initiative;
        if (c != 0) return c;
        return hashCode() - o.hashCode();
    }

    public int calcDamage(Group d) {
        if (d.immunities.contains(attackType)) return 0;
        int damage = effectivePower();
        if (d.weaknesses.contains(attackType)) damage *= 2;
        return damage;
    }

    public void dealDamage(int damage) {
        units -= damage / hitPoints;
    }
}
