package com.barneyb.aoc2018.day19;

import com.barneyb.aoc2018.isa.Interpreter;
import com.barneyb.aoc2018.isa.Program;
import com.barneyb.aoc2018.util.Resources;
import org.junit.Test;

import static com.barneyb.aoc2018.day19.Day19.sumOfFactorPairs;

public class Working {

    @Test
    public void refactor() {
        String input = Resources.asText("formatted.txt");
        Program p = Program.parse(input);
        Interpreter first = new Interpreter(p);
        first.run();
        int one = first.register(0);
        System.out.println(one);
        assert 912 == one;
    }

    @Test
    public void reversed() {
        int a=0, b=0, c=0, d=0, f=0;
        // begin

        b = b + 2;
        b = b * b;
        b = 19 * b;
        b = b * 11;
        d = d + 3;
        d = d * 22;
        d = d + 9;
        b = b + d;
        if (a == 1) {
            d = 27;
            d = d * 28;
            d = 29 + d;
            d = 30 * d;
            d = d * 14;
            d = d * 32;
            b = b + d;
            a = 0;
        }
        // setup complete!



        for (c = 1, f = (int) Math.sqrt(b); c <= f; c++) {
            if (b % c == 0) {
                a += c;
                a += b / c;
            }
        }

        System.out.printf("sum of factors of %d: %d%n", b, a);
        System.out.printf("sum of factors of %d: %d%n", b, sumOfFactorPairs(b));
        a = 0;


        for (c = 1; c <= b; c++) {
            for (f = 1; f <= b; f++) {
                d = c * f;
                if (d == b) {
                    System.out.println(c);
                    a += c;
                }
            }
        }

        // end
        System.out.printf("sum of factors of %d: %d%n", b, a);
//        assert 912 == a;
    }

}
