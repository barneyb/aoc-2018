package com.barneyb.aoc2018.day24;

import com.barneyb.aoc2018.util.BST;
import com.barneyb.aoc2018.util.TreeSet;

class Combat {

    private final Army immune;
    private final Army infection;

    public Combat(Army immune, Army infection) {
        this.immune = immune;
        this.infection = infection;
    }

    public boolean isOver() {
        return ! immune.alive() || ! infection.alive();
    }

    public void fight() {
        System.out.println(immune);
        System.out.println(infection);
        System.out.println();
        TreeSet<TargetSelection> selections = infection.selectTargets(immune);
        for (TargetSelection s : immune.selectTargets(infection)) {
            selections.add(s);
        }
        BST<Integer, TargetSelection> byInitiative = new BST<>();
        for (TargetSelection s : selections) {
            byInitiative.put(-s.group().initiative(), s);
        }
        for (Integer init : byInitiative.keys()) {
            byInitiative.get(init).doAttack();
        }
        immune.buryTheDead();
        infection.buryTheDead();
    }

}
