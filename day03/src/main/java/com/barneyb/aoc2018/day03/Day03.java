package com.barneyb.aoc2018.day03;

import com.barneyb.aoc2018.util.Answers;
import com.barneyb.aoc2018.util.FileUtils;
import com.barneyb.aoc2018.util.OneShotDay;

public class Day03 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        Fabric s = new Fabric(parse(input));
        return new Answers(
                s.multiClaimedSquares(),
                s.standaloneClaimId()
        );
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
