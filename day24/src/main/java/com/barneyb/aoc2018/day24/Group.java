package com.barneyb.aoc2018.day24;

import com.barneyb.aoc2018.api.Set;
import com.barneyb.aoc2018.util.Scanner;
import com.barneyb.aoc2018.util.TreeSet;

class Group implements Comparable<Group> {

    static Group parse(String input) {
        return scan(new Scanner(input.trim()
                .replace(" unit ", " units ")
                .replace(" point ", " points ")));
    }

    static Group scan(Scanner s) {
        System.out.println("Scan group: " + s.preview());
        // 18 units each with 729 hit points (weak to fire; immune to cold, slashing) with an attack that does 8 radiation damage at initiative 10
        // 272 units each with 10137 hit points with an attack that does 331 slashing damage at initiative 10
        Group g = new Group();
        g.units(s.readInt());
        g.hitPoints(s.skip(" units each with ").readInt());
        s.skip(" hit points ");
        boolean started = false;
        while (s.probe('(') || s.probe(';')) {
            started = true;
            s.skip(1).skipWS();
            if (s.probe('w')) {
                g.addWeakness(s.skip("weak to ").readWord());
                while (s.probe(',')) g.addWeakness(s.skip(", ").readWord());
            } else if (s.probe('i')) {
                g.addImmunity(s.skip("immune to ").readWord());
                while (s.probe(',')) g.addImmunity(s.skip(", ").readWord());
            }
        }
        if (started) s.skip(") ");
        g.attack(
                s.skip("with an attack that does ").readInt(),
                s.skipWS().readWord());
        g.initiative(s.skip("damage at initiative ").readInt());
        return g;
    }

    private int units;
    private int hitPoints;
    private int attack;
    private String attackType;
    private int initiative;
    private Set<String> weaknesses = new TreeSet<>();
    private Set<String> immunities = new TreeSet<>();

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

    public void attack(int a, String type) {
        attack = a;
        attackType = type;
    }

    public String attackType() {
        return attackType;
    }

    public int initiative() {
        return initiative;
    }

    public void initiative(int i) {
        initiative = i;
    }

    public Set<String> weaknesses() {
        return weaknesses;
    }

    public void addWeakness(String type) {
        weaknesses.add(type);
    }

    public Set<String> immunities() {
        return immunities;
    }

    public void addImmunity(String type) {
        immunities.add(type);
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
