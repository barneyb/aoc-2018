package com.barneyb.aoc2018.day24;

class TargetSelection implements Comparable<TargetSelection> {

    private final Group group;
    private final Group target;
    private final int damage;

    TargetSelection(Group group, Group target, int damage) {
        this.group = group;
        this.target = target;
        this.damage = damage;
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
        return group.compareTo(o.group);
    }

    public void dealDamage() {
        if (! group().alive()) return;
        target.dealDamage(damage);
    }
}
