package com.barneyb.aoc2018.day09;

import com.barneyb.aoc2018.util.Answers;
import com.barneyb.aoc2018.util.FileUtils;
import com.barneyb.aoc2018.util.OneShotDay;

public class Day09 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        String[] parts = input.trim().split(" ");
        int playerCount = Integer.parseInt(parts[0]);
        int lastMarble = Integer.parseInt(parts[6]);
        return new Answers(
                partOne(playerCount, lastMarble)
        );
    }

    private static class Marble {
        final int value;
        Marble left, right;

        public Marble(int value) {
            this.value = value;
        }
    }

    private static class Ring {
        Marble curr;

        public Ring() {
            this.curr = new Marble(0);
            curr.left = curr;
            curr.right = curr;
        }

        public void cw() {
            curr = curr.right;
        }

        public void ccw() {
            curr = curr.left;
        }

        public void add(int i) {
            Marble m = new Marble(i);
            curr.right.left = m;
            m.left = curr;
            m.right = curr.right;
            curr.right = m;
            curr = m;
        }

        public int remove() {
            int v = curr.value;
            curr.left.right = curr.right;
            curr.right.left = curr.left;
            curr = curr.right;
            return v;
        }
    }

    static int partOne(int playerCount, int lastMarble) {
        Ring ring = new Ring();
        int[] scores = new int[playerCount];
        for (int i = 1; i <= lastMarble; i++) {
            if (i % 23 == 0) {
                int p = (i - 1) % playerCount;
                scores[p] += i;
                for (int j = 0; j < 7; j++) {
                    ring.ccw();
                }
                scores[p] += ring.remove();
            } else {
                ring.cw();
                ring.add(i);
            }
        }
        int max = 0;
        for (int s : scores) {
            if (s > max) {
                max = s;
            }
        }
        return max;
    }

    public static void main(String[] args)  {
        Day09 d = new Day09();
        String input = FileUtils.readFile("day09/input.txt");
        Answers a = d.solve(input);
        System.out.println(a);
    }

}
