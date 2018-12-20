package com.barneyb.aoc2018.day20;

import com.barneyb.aoc2018.util.Answers;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day20Test {

    static final String EXAMPLE_INPUT = "^WSSEESWWWNW(S|NENNEEEENN(ESSSSW(NWSW|SSEN)|WSWWN(E|WWS(E|SS))))$";

    @Test
    public void solve() {
        Answers a = new Day20().solve(EXAMPLE_INPUT);
        assertEquals("31", a.getPartOne());
        assertEquals("-", a.getPartTwo());
    }

    @Test
    public void exampleOne_a() {
        assertEquals(3, Day20.partOne("^WNE$"));
    }

    @Test
    public void exampleOne_b() {
        assertEquals(10, Day20.partOne("^ENWWW(NEEE|SSE(EE|N))$"));
    }

    @Test
    public void exampleOne_c() {
        assertEquals(18, Day20.partOne("^ENNWSWW(NEWS|)SSSEEN(WNSE|)EE(SWEN|)NNN$"));
    }

    @Test
    public void exampleOne_d() {
        assertEquals(23, Day20.partOne("^ESSWWN(E|NNENN(EESS(WNSE|)SSS|WWWSSSSE(SW|NNNE)))$"));
    }

}
