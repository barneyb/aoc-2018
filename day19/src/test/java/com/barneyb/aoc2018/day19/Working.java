package com.barneyb.aoc2018.day19;

import com.barneyb.aoc2018.util.FileUtils;
import org.junit.Test;

public class Working {

    @Test
    public void refactor() {
        String input = FileUtils.readFile("formatted.txt");
        Program p = Program.parse(input);
        Interpreter first = new Interpreter(p);
        first.run();
        int one = first.registerZero();
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
        c = 1;
        do {
            f = 1;
            do {
                d = c * f;
                if (d == b) {
                    a = c + a;
                }
                f = f + 1;
            } while (f <= b);
            c = c + 1;
        } while (c <= b);

        // end
        System.out.println(a);
        assert 912 == a;
    }

}
