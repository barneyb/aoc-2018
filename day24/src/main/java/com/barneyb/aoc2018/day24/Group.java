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

    private int index;
    private int units;
    private int hitPoints;
    private int attack;
    private String attackType;
    private int initiative;
    private TreeSet<String> weaknesses = new TreeSet<>();
    private TreeSet<String> immunities = new TreeSet<>();

    public Group duplicate() {
        Group g = new Group();
        g.index = index;
        g.units = units;
        g.hitPoints = hitPoints;
        g.attack = attack;
        g.attackType = attackType;
        g.initiative = initiative;
        g.weaknesses = weaknesses.duplicate();
        g.immunities = immunities.duplicate();
        return g;
    }

    public int index() {
        return index;
    }

    public void index(int i) {
        index = i;
    }

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

    public void attack(Group d) {
        d.dealDamage(calcDamage(d));
    }

    public void dealDamage(int damage) {
        units = Math.max(0, units - (damage / hitPoints));
    }

    @Override
    public String toString() {
        // 18 units each with 729 hit points (weak to fire; immune to cold, slashing) with an attack that does 8 radiation damage at initiative 10
        return "Group " + index + " contains " + units + " units w/ " + hitPoints + " hit points (weak to " + weaknesses + "; immune to " + immunities + ") with " + attack + " " + attackType + " damage at initiative " + initiative;
    }

    public void boost(int boost) {
        attack += boost;
    }
}
