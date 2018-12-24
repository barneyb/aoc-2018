package com.barneyb.aoc2018.day24;

import com.barneyb.aoc2018.util.Answers;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day24Test {

    static final String EXAMPLE_INPUT =
            "Immune System:\n" +
            "17 units each with 5390 hit points (weak to radiation, bludgeoning) with an attack that does 4507 fire damage at initiative 2\n" +
            "989 units each with 1274 hit points (immune to fire; weak to bludgeoning, slashing) with an attack that does 25 slashing damage at initiative 3\n" +
            "\n" +
            "Infection:\n" +
            "801 units each with 4706 hit points (weak to radiation) with an attack that does 116 bludgeoning damage at initiative 1\n" +
            "4485 units each with 2961 hit points (immune to radiation; weak to fire, cold) with an attack that does 12 slashing damage at initiative 4";

    @Test
    public void solve() {
        Answers a = new Day24().solve(EXAMPLE_INPUT);
        assertEquals("7", a.getPartOne());
        assertEquals("-", a.getPartTwo());
    }

}
