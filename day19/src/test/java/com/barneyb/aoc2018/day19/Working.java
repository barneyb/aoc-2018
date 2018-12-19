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

//           #ip 4
//        /*  0  addi 4 [16] 4 */ ip = 0 + 16;
        /* 17  addi 1  [2] 1 */ b = b + 2;
        /* 18  mulr 1   1  1 */ b = b * b;
        /* 19  mulr 4   1  1 */ b = 19 * b;
        /* 20  muli 1 [11] 1 */ b = b * 11;
        /* 21  addi 3  [3] 3 */ d = d + 3;
        /* 22  mulr 3   4  3 */ d = d * 22;
        /* 23  addi 3  [9] 3 */ d = d + 9;
        /* 24  addr 1   3  1 */ b = b + d;
//        /* 25  addr 4   0  4 */ ip = 25 + a;
        if (a == 1) {
//        /* 26  seti 0  [1] 4 */ ip = 0;
        /* 27  setr 4   9  3 */ d = 27;
        /* 28  mulr 3   4  3 */ d = d * 28;
        /* 29  addr 4   3  3 */ d = 29 + d;
        /* 30  mulr 4   3  3 */ d = 30 * d;
        /* 31  muli 3 [14] 3 */ d = d * 14;
        /* 32  mulr 3   4  3 */ d = d * 32;
        /* 33  addr 1   3  1 */ b = b + d;
        /* 34  seti 0  [6] 0 */ a = 0;
        }
//        /* 35  seti 0  [7] 4 */ ip = 0;
        /*  1  seti 1  [7] 2 */ c = 1;
        do {
        /*  2  seti 1  [1] 5 */ f = 1;
            do {
        /*  3  mulr 2   5  3 */ d = c * f;
        /*  4  eqrr 3   1  3 */ d = d == b ? 1 : 0;
//        /*  5  addr 3   4  4 */ ip = d + 5;
//        /*  6  addi 4  [1] 4 */ ip = 6 + 1;
                if (d == 1) {
        /*  7  addr 2   0  0 */ a = c + a;
                }
        /*  8  addi 5  [1] 5 */ f = f + 1;
        /*  9  gtrr 5   1  3 */ d = f > b ? 1 : 0;
//        /* 10  addr 4   3  4 */ ip = 10 + d;
//        /* 11  seti 2  [7] 4 */ ip = 2;
            } while (d == 0);
        /* 12  addi 2  [1] 2 */ c = c + 1;
        /* 13  gtrr 2   1  3 */ d = c > b ? 1 : 0;
//        /* 14  addr 3   4  4 */ ip = d + 14;
//        /* 15  seti 1  [3] 4 */ ip = 1;
        } while (d == 0);
//        /* 16  mulr 4   4  4 */ ip = 16 * 16;

        // end
        System.out.println(a);
    }
}
