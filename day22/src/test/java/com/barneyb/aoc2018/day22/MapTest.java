package com.barneyb.aoc2018.day22;

import com.barneyb.aoc2018.util.Point;
import org.junit.Test;

import static com.barneyb.aoc2018.day22.Day22Test.EXAMPLE_INPUT;
import static org.junit.Assert.assertEquals;

public class MapTest {

    @Test
    public void strings() {
        Map m = Map.parse(EXAMPLE_INPUT);
        assertEquals(
                "M=.|=.|.|=.\n" +
                ".|=|=|||..|\n" +
                ".==|....||=\n" +
                "=.|....|.==\n" +
                "=|..==...=.\n" +
                "=||.=.=||=|\n" +
                "|.=.===|||.\n" +
                "|..==||=.|=\n" +
                ".=..===..=|\n" +
                ".======|||=\n" +
                ".===|=|===T",
                m.toString());
        assertEquals(
                "M=.|=.|.|=.|=|=.\n" +
                ".|=|=|||..|.=...\n" +
                ".==|....||=..|==\n" +
                "=.|....|.==.|==.\n" +
                "=|..==...=.|==..\n" +
                "=||.=.=||=|=..|=\n" +
                "|.=.===|||..=..|\n" +
                "|..==||=.|==|===\n" +
                ".=..===..=|.|||.\n" +
                ".======|||=|=.|=\n" +
                ".===|=|===T===||\n" +
                "=|||...|==..|=.|\n" +
                "=.=|=.=..=.||==|\n" +
                "||=|=...|==.=|==\n" +
                "|=.=||===.|||===\n" +
                "||.|==.|.|.||=||",
                m.toString(new Point(15,15)));
    }
}