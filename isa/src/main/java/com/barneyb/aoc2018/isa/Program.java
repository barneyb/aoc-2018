package com.barneyb.aoc2018.isa;

import com.barneyb.aoc2018.util.BST;
import com.barneyb.aoc2018.util.Scanner;

import static com.barneyb.aoc2018.isa.Op.OPS;

public class Program {

    public static Program parse(String input) {
        String[] lines = input.trim().split("\n");
        BST<String, Integer> opMap = new BST<>();
        for (int i = 0; i < OPS.length; i++) {
            opMap.put(OPS[i].name(), i);
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

    public Instruction[] instructions() {
        return instructions;
    }

    public Instruction instruction(int i) {
        return instructions[i];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("#ip ").append(ipr).append('\n');
        for (Instruction i : instructions) {
            sb.append(i.opName())
                    .append(' ').append(i.a())
                    .append(' ').append(i.b())
                    .append(' ').append(i.c())
                    .append('\n');
        }
        return sb.toString();
    }

}
