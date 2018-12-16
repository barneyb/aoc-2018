package com.barneyb.aoc2018.day15;

import com.barneyb.aoc2018.util.Answers;
import org.junit.Test;

import static com.barneyb.aoc2018.day15.Map.ELF;
import static com.barneyb.aoc2018.day15.Map.GOBLIN;
import static org.junit.Assert.assertEquals;

public class Day15Test {

    static final String DEMO_INPUT =
            "#######\n" +
            "#.G...#\n" +
            "#...EG#\n" +
            "#.#.#G#\n" +
            "#..G#E#\n" +
            "#.....#\n" +
            "#######";

    @Test
    public void solve() {
        Day15 d = new Day15();
        Answers a = d.solve(DEMO_INPUT);
        // 47 rounds, goblins win w/ 200+131+59+200 = 590 HP
        assertEquals("27730", a.getPartOne());
        assertEquals("4988", a.getPartTwo());
    }

    private String nextTo(String a, String b) {
        String[] al = a.trim().split("\n");
        String[] bl = b.trim().split("\n");
        if (al.length != bl.length) {
            throw new IllegalArgumentException("gotta be the same number of lines, yo!");
        }
        int ml = 0;
        for (String s : al) {
            ml = Math.max(ml, s.length());
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < al.length; i++) {
            sb.append(String.format("%-" + ml + "s   %s%n", al[i], bl[i]));
        }
        return sb.toString();
    }

    @Test
    public void demo() {
        Map m = Map.parse(DEMO_INPUT);
        Day15.Result r = Day15.partOne(m);
        System.out.println(nextTo(
                "#######   \n" +
                "#G....#   G(200)\n" +
                "#.G...#   G(131)\n" +
                "#.#.#G#   G(59)\n" +
                "#...#.#   \n" +
                "#....G#   G(200)\n" +
                "#######   ",
                m.toString(true)));
        assertEquals(GOBLIN, r.winner());
        assertEquals(47, r.rounds());
        assertEquals(590, r.hitPoints());
        assertEquals(27730, r.result());
    }

    @Test
    public void exampleOne() {
        Map m = Map.parse(
                "#######\n" +
                "#G..#E#\n" +
                "#E#E.E#\n" +
                "#G.##.#\n" +
                "#...#E#\n" +
                "#...E.#\n" +
                "#######");
        Day15.Result r = Day15.partOne(m);
        System.out.println(nextTo(
                "#######\n" +
                "#...#E# E(200)\n" +
                "#E#...# E(197)\n" +
                "#.E##.# E(185)\n" +
                "#E..#E# E(200) E(200)\n" +
                "#.....#\n" +
                "#######",
                m.toString(true)));
        assertEquals(ELF, r.winner());
        assertEquals(37, r.rounds());
        assertEquals(982, r.hitPoints());
        assertEquals(36334, r.result());
    }

    @Test
    public void exampleTwo() {
        String input =
                "#######\n" +
                "#E..EG#\n" +
                "#.#G.E#\n" +
                "#E.##E#\n" +
                "#G..#.#\n" +
                "#..E#.#\n" +
                "#######";
        Map m = Map.parse(input);
        Day15.Result r = Day15.partOne(m);
        System.out.println(nextTo(
                "#######\n" +
                "#.E.E.#   E(164), E(197)\n" +
                "#.#E..#   E(200)\n" +
                "#E.##.#   E(98)\n" +
                "#.E.#.#   E(200)\n" +
                "#...#.#\n" +
                "#######",
                m.toString(true)));
        assertEquals(ELF, r.winner());
        assertEquals(46, r.rounds());
        assertEquals(859, r.hitPoints());
        assertEquals(39514, r.result());
        Day15.ArmedResult ar = Day15.partTwo(input);
        assertEquals(4 , ar.elvishAttack());
        assertEquals(31284, ar.result());
    }

    @Test
    public void exampleThree() {
        String input =
                "#######\n" +
                "#E.G#.#\n" +
                "#.#G..#\n" +
                "#G.#.G#\n" +
                "#G..#.#\n" +
                "#...E.#\n" +
                "#######";
        Map m = Map.parse(input);
        Day15.Result r = Day15.partOne(m);
        System.out.println(nextTo(
                "#######\n" +
                "#G.G#.#   G(200), G(98)\n" +
                "#.#G..#   G(200)\n" +
                "#..#..#\n" +
                "#...#G#   G(95)\n" +
                "#...G.#   G(200)\n" +
                "#######",
                m.toString(true)));
        assertEquals(GOBLIN, r.winner());
        assertEquals(35, r.rounds());
        assertEquals(793, r.hitPoints());
        assertEquals(27755, r.result());
        Day15.ArmedResult ar = Day15.partTwo(input);
        assertEquals(15 , ar.elvishAttack());
        assertEquals(3478, ar.result());
    }

    @Test
    public void exampleFour() {
        String input =
                "#######\n" +
                "#.E...#\n" +
                "#.#..G#\n" +
                "#.###.#\n" +
                "#E#G#G#\n" +
                "#...#G#\n" +
                "#######";
        Map m = Map.parse(input);
        Day15.Result r = Day15.partOne(m);
        System.out.println(nextTo(
                "#######\n" +
                "#.....#\n" +
                "#.#G..#   G(200)\n" +
                "#.###.#\n" +
                "#.#.#.#\n" +
                "#G.G#G#   G(98), G(38), G(200)\n" +
                "#######",
                m.toString(true)));
        assertEquals(GOBLIN, r.winner());
        assertEquals(54, r.rounds());
        assertEquals(536, r.hitPoints());
        assertEquals(28944, r.result());
        Day15.ArmedResult ar = Day15.partTwo(input);
        assertEquals(12 , ar.elvishAttack());
        assertEquals(6474, ar.result());
    }

    @Test
    public void exampleFive() {
        String input =
                "#########\n" +
                "#G......#\n" +
                "#.E.#...#\n" +
                "#..##..G#\n" +
                "#...##..#\n" +
                "#...#...#\n" +
                "#.G...G.#\n" +
                "#.....G.#\n" +
                "#########";
        Map m = Map.parse(input);
        Day15.Result r = Day15.partOne(m);
        System.out.println(nextTo(
                "#########\n" +
                "#.G.....#   G(137)\n" +
                "#G.G#...#   G(200), G(200)\n" +
                "#.G##...#   G(200)\n" +
                "#...##..#\n" +
                "#.G.#...#   G(200)\n" +
                "#.......#\n" +
                "#.......#\n" +
                "#########",
                m.toString(true)));
        assertEquals(GOBLIN, r.winner());
        assertEquals(20, r.rounds());
        assertEquals(937, r.hitPoints());
        assertEquals(18740, r.result());
        Day15.ArmedResult ar = Day15.partTwo(input);
        assertEquals(34 , ar.elvishAttack());
        assertEquals(1140, ar.result());
    }

}
