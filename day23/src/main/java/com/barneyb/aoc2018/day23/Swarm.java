package com.barneyb.aoc2018.day23;

import com.barneyb.aoc2018.util.Bag;

class Swarm {

    static Swarm parse(String input) {
        String[] lines = input.trim().split("\n");
        Bot[] bots = new Bot[lines.length];
        for (int i = 0; i < lines.length; i++) {
            bots[i] = Bot.parse(lines[i]);
        }
        return new Swarm(bots);
    }

    final Bot[] bots;
    final Bot strongest;

    public Swarm(Bot[] bots) {
        this.bots = bots;
        Bot s = bots[0];
        for (Bot b : bots) {
            if (b.range > s.range) s = b;
        }
        this.strongest = s;
    }

    Bot strongest() {
        return strongest;
    }

    // includes self!
    Bag<Bot> inRangeOf(Bot bot) {
        Bag<Bot> bs = new Bag<>();
        for (Bot b : bots) {
            if (bot.inRange(b)) bs.add(b);
        }
        return bs;
    }

}
