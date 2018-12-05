package com.barneyb.aoc2018.day03;

import com.barneyb.aoc2018.util.Answers;
import com.barneyb.aoc2018.util.FileUtils;
import com.barneyb.aoc2018.util.Histogram;
import com.barneyb.aoc2018.util.OneShotDay;

public class Day03 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        Fabric s = new Fabric(parse(input));
        return new Answers(
                s.partOne(),
                s.partTwo()
        );
    }

    static class Fabric {

        Claim[] claims;
        int width, height;
        Histogram<Integer> claimHistogram;

        Fabric(Claim[] claims) {
            this.claims = claims;
            for (Claim c : claims) {
                width = Math.max(width, c.left + c.width);
                height = Math.max(height, c.top + c.height);
            }
            claimHistogram = buildClaimHistogram();
        }

        private int partTwo() {
            claimLoop:
            for (Claim c : claims) {
                for (int x = c.left, mx = c.left + c.width; x < mx; x++) {
                    for (int y = c.top, my = c.top + c.height; y < my; y++) {
                        if (claimHistogram.get(index(x, y)) > 1) {
                            continue claimLoop;
                        }
                    }
                }
                return c.id;
            }
            throw new RuntimeException("No claim is isolated");
        }

        private int partOne() {
            int mulitClaimCount = 0;
            for (Integer i : claimHistogram.keys()) {
                if (claimHistogram.get(i) > 1) {
                    mulitClaimCount += 1;
                }
            }
//            for (int y = 0; y < height; y++) {
//                for (int x = 0; x < width; x++) {
//                    int n = h.get(index(x, y));
//                    System.out.print(n == 0 ? "." : n == 1 ? "c" : "X");
//                }
//                System.out.println();
//            }
            return mulitClaimCount;
        }

        private Histogram<Integer> buildClaimHistogram() {
            Histogram<Integer> h = new Histogram<>();
            for (Claim c : claims) {
                lay(c, h);
            }
            return h;
        }

        private void lay(Claim c, Histogram<Integer> h) {
            for (int x = c.left, mx = c.left + c.width; x < mx; x++) {
                for (int y = c.top, my = c.top + c.height; y < my; y++) {
                    h.count(index(x, y));
                }
            }
        }

        private Integer index(int x, int y) {
            return y * width + x;
        }
    }

    static Claim[] parse(String input) {
        String[] lines = input.trim().split("\n");
        Claim[] claims = new Claim[lines.length];
        for (int i = 0, l = lines.length; i < l; i++) {
            claims[i] = Claim.parse(lines[i]);
        }
        return claims;
    }

    public static void main(String[] args)  {
        Day03 d = new Day03();
        String input = FileUtils.readFile("day03/input.txt");
        Answers a = d.solve(input);
        System.out.println(a);
    }

}
