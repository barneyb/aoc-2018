package com.barneyb.aoc2018.day24;

import com.barneyb.aoc2018.util.TreeSet;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GroupTest {

    @Test
    public void parseOne() {
        Group g = Group.parse("18 units each with 729 hit points (weak to fire; immune to cold, slashing) with an attack that does 8 radiation damage at initiative 10");
        assertEquals(18, g.units());
        assertEquals(729, g.hitPoints());
        assertEquals(new TreeSet<>(new String[] {"fire"}), g.weaknesses());
        assertEquals(new TreeSet<>(new String[] {"cold", "slashing"}), g.immunities());
        assertEquals(8, g.attack());
        assertEquals("radiation", g.attackType());
        assertEquals(10, g.initiative());
    }

    @Test
    public void parseWeaknessesAndImmunities() {
        String prefix = "18 units each with 729 hit points";
        String suffix = "with an attack that does 8 radiation damage at initiative 10";
        Group g = Group.parse(prefix + " (weak to fire; immune to cold, slashing) " + suffix);
        assertEquals(new TreeSet<>(new String[] {"fire"}), g.weaknesses());
        assertEquals(new TreeSet<>(new String[] {"cold", "slashing"}), g.immunities());

        g = Group.parse(prefix + " (weak to fire, cold; immune to slashing) " + suffix);
        assertEquals(new TreeSet<>(new String[] {"fire", "cold"}), g.weaknesses());
        assertEquals(new TreeSet<>(new String[] {"slashing"}), g.immunities());

        g = Group.parse(prefix + " (immune to slashing; weak to fire, cold) " + suffix);
        assertEquals(new TreeSet<>(new String[] {"fire", "cold"}), g.weaknesses());
        assertEquals(new TreeSet<>(new String[] {"slashing"}), g.immunities());

        g = Group.parse(prefix + " (weak to fire, cold) " + suffix);
        assertEquals(new TreeSet<>(new String[] {"fire", "cold"}), g.weaknesses());
        assertEquals(new TreeSet<>(), g.immunities());

        g = Group.parse(prefix + " (immune to slashing) " + suffix);
        assertEquals(new TreeSet<>(), g.weaknesses());
        assertEquals(new TreeSet<>(new String[] {"slashing"}), g.immunities());
    }

    @Test
    public void parseTwo() {
        Group g = Group.parse("272 units each with 10137 hit points with an attack that does 331 slashing damage at initiative 10");
        assertEquals(272, g.units());
        assertEquals(10137, g.hitPoints());
        assertEquals(new TreeSet<>(), g.weaknesses());
        assertEquals(new TreeSet<>(), g.immunities());
        assertEquals(331, g.attack());
        assertEquals("slashing", g.attackType());
        assertEquals(10, g.initiative());
    }

    @Test
    public void dealDamage() {
        Group g = new Group();
        g.hitPoints(10);
        g.units(10);
        g.dealDamage(75);
        assertEquals(3, g.units());
        assertEquals(10, g.hitPoints());
    }

}