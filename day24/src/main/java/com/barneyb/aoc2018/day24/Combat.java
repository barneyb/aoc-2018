package com.barneyb.aoc2018.day24;

import com.barneyb.aoc2018.util.TreeSet;

public class Combat {

    private final Army immune;
    private final Army infection;

    public Combat(Army immune, Army infection) {
        this.immune = immune;
        this.infection = infection;
    }

    private void fight() {
        TreeSet<TargetSelection> selections = selectTargets(immune, infection);
        for (TargetSelection s : selectTargets(infection, immune)) {
            selections.add(s);
        }
        for (TargetSelection s : selections) {
            s.dealDamage();
        }
    }

    private TreeSet<TargetSelection> selectTargets(Army attackers, Army defenders) {
        TreeSet<TargetSelection> selections = new TreeSet<>();
        TreeSet<Group> dgs = defenders.groups().duplicate();
        for (Group a : attackers.groups()) {
            if (dgs.isEmpty()) break;
            int maxDamage = 0;
            Group target = null;
            for (Group d : dgs) {
                int damage = a.calcDamage(d);
                if (damage > maxDamage) {
                    maxDamage = damage;
                    target = d;
                }
            }
            if (target == null) continue;
            dgs.delete(target);
            selections.add(new TargetSelection(a, target, maxDamage));
        }
        return selections;
    }

}
