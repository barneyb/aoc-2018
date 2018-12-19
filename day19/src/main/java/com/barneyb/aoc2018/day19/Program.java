package com.barneyb.aoc2018.day19;

import com.barneyb.aoc2018.isa.Instruction;
import com.barneyb.aoc2018.util.BST;
import com.barneyb.aoc2018.util.Scanner;

import static com.barneyb.aoc2018.isa.Op.OP_NAMES;

class Program {

    static Program parse(String input) {
        String[] lines = input.trim().split("\n");
        BST<String, Integer> opMap = new BST<>();
        for (int i = 0; i < OP_NAMES.length; i++) {
            opMap.put(OP_NAMES[i], i);
        }
        int ipr = new Scanner(lines[0]).skip("#ip").skipWS().readInt();
        Instruction[] ins = new Instruction[lines.length - 1];
        for (int i = 1; i < lines.length; i++) {
            ins[i - 1] = Instruction.parseMapped(lines[i], opMap);
        }
        return new Program(ipr, ins);
    }

    final int ipr;
    final Instruction[] instructions;

    Program(int ipr, Instruction[] instructions) {
        this.ipr = ipr;
        this.instructions = instructions;
    }

}
