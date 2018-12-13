package com.barneyb.aoc2018.day13;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MapTest {

    @Test
    public void strings() {
        Map m = Map.parse(Day13Test.EXAMPLE_INPUT);
        assertEquals(
                // this looks janky cuzza escapes
                "/---\\\n" +
                        "|   |  /----\\\n" +
                        "| /-+--+-\\  |\n" +
                        "| | |  | |  |\n" +
                        "\\-+-/  \\-+--/\n" +
                        "  \\------/",
                m.toString(false));
        assertEquals(
                // this looks janky cuzza escapes
                "/-A-\\\n" +
                "|   |  /----\\\n" +
                "| /-+--+-\\  |\n" +
                "| | |  | B  |\n" +
                "\\-+-/  \\-+--/\n" +
                "  \\------/",
                m.toString());
    }

    @Test
    public void tick() {
        Map m = Map.parse(Day13Test.EXAMPLE_INPUT);
        m.tick();
        assertEquals(
                "/--A\\\n" +
                "|   |  /----\\\n" +
                "| /-+--+-\\  |\n" +
                "| | |  | |  |\n" +
                "\\-+-/  \\-B--/\n" +
                "  \\------/",
                m.toString());
        m.tick();
        assertEquals(
                "/---A\n" +
                "|   |  /----\\\n" +
                "| /-+--+-\\  |\n" +
                "| | |  | |  |\n" +
                "\\-+-/  \\-+B-/\n" +
                "  \\------/",
                m.toString());
        m.tick();
        assertEquals(
                "/---\\\n" +
                "|   A  /----\\\n" +
                "| /-+--+-\\  |\n" +
                "| | |  | |  |\n" +
                "\\-+-/  \\-+-B/\n" +
                "  \\------/",
                m.toString());
        m.tick();
        assertEquals(
                "/---\\\n" +
                "|   |  /----\\\n" +
                "| /-A--+-\\  |\n" +
                "| | |  | |  |\n" +
                "\\-+-/  \\-+--B\n" +
                "  \\------/",
                m.toString());
        m.tick();
        assertEquals(
                "/---\\\n" +
                "|   |  /----\\\n" +
                "| /-+A-+-\\  |\n" +
                "| | |  | |  B\n" +
                "\\-+-/  \\-+--/\n" +
                "  \\------/",
                m.toString());
        m.tick();
        assertEquals(
                "/---\\\n" +
                "|   |  /----\\\n" +
                "| /-+-A+-\\  B\n" +
                "| | |  | |  |\n" +
                "\\-+-/  \\-+--/\n" +
                "  \\------/",
                m.toString());
    }
}