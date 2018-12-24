package com.barneyb.aoc2018.day24;

class TargetSelection implements Comparable<TargetSelection> {

    private final String label;
    private final Group group;
    private final Group target;
    private final int damage;

    TargetSelection(String label, Group group, Group target, int damage) {
        this.label = label;
        this.group = group;
        this.target = target;
        this.damage = damage;
    }

    public String label() {
        return label;
    }

    public Group group() {
        return group;
    }

    public Group target() {
        return target;
    }

    public int damage() {
        return damage;
    }

    @Override
    public int compareTo(TargetSelection o) {
        int c = group.compareTo(o.group);
        if (c != 0) return c;
        c = target.compareTo(o.target);
        if (c != 0) return c;
        return toString().compareTo(o.toString());
    }

    public void doAttack() {
        int before = target.units();
        group.attack(target);
        int after = target.units();
//        System.out.println(label + " group " + group.index() + " attacks defending group " + target.index() + ", killing " + (before - after) + " units");
    }

    @Override
    public String toString() {
        return label + " group " + group.index() + " would deal defending group " + target.index() + " " + damage + " damage";
    }
}
