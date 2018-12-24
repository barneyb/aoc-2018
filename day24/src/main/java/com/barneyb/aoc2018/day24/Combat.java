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

    public boolean infected() {
        return infection.alive();
    }

    public int unitsOnField() {
        return immune.units() + infection.units();
    }

    public void fight() {
//        System.out.println(immune);
//        System.out.println(infection);
//        System.out.println();
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

    @Override
    public String toString() {
        return immune.toString() + '\n' + infection;
    }

    public boolean run() {
        int last = -1;
        while (! isOver()) {
            fight();
            int uof = unitsOnField();
            if (last == uof) return false; // stalemate
            last = uof;
        }
        return true;
    }
}
