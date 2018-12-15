package com.barneyb.aoc2018.day15;

class Engine {

    private final Map map;
    private int rounds;

    Engine(Map map) {
        this.map = map;
        while (doRound()) {
            rounds++;

            if (rounds > 50) break; // todo: go away!

        }
    }

    /**
     * I run a single round, indicating if combat is still ongoing
     * @return whether combat is ongoing.
     */
    boolean doRound() {
        for (Unit u : map.units()) {
            if (! u.alive()) continue;
            // move, if needed
            // attack, if possible
            if (map.isOver()) return false; // short circuit
        }
        return true;
    }

    int rounds() {
        return rounds;
    }
}
