package com.barneyb.aoc2018.day02;

import com.barneyb.aoc2018.util.Answers;
import com.barneyb.aoc2018.util.FileUtils;
import com.barneyb.aoc2018.util.OneShotDay;

public class Day02 extends OneShotDay {

    @Override
    public Answers solve(String input) {
        Warehouse w = Warehouse.parse(input);
        return new Answers(
                w.checksum(),
                w.fabricBoxesCommonLetters()
        );
    }

    public static void main(String[] args)  {
        Day02 d = new Day02();
        String input = FileUtils.readFile("day02/input.txt");
        Answers a = d.solve(input);
        System.out.println(a);
    }

}
