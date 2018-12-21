package com.barneyb.aoc2018.isa;

import com.barneyb.aoc2018.util.BST;
import com.barneyb.aoc2018.util.Scanner;

import java.io.PrintWriter;
import java.io.StringWriter;

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
        StringWriter sw = new StringWriter();
        PrintWriter out = new PrintWriter(sw);
        int il = ("" + instructions.length).length();
        int al = 0, bl = 0, cl = 0, dl = 0;
        String[] dis = new String[instructions.length];
        for (int i = 0; i < instructions.length; i++) {
            Instruction ins = instructions[i];
            al = Math.max(al, ("" + ins.a()).length());
            bl = Math.max(bl, ("" + ins.b()).length());
            cl = Math.max(cl, ("" + ins.c()).length());
            String di = ins.disassemble(ipr);
            dis[i] = di;
            dl = Math.max(dl, di.length());
        }
        out.printf("%" + il + "s  #ip %d %" + (-1 + al + bl + cl + 2 + dl + 3) + "s  | ", "", ipr, "");
        boolean started = false;
        for (int i = 0; i < 6; i++) {
            if (i == ipr) continue;
            out.print(started ? ", " : "int ");
            started = true;
            out.printf("%s=0", (char) ('a' + i));
        }
        out.println();
        for (int i = 0; i < instructions.length; i++) {
            Instruction ins = instructions[i];
            out.printf(
                    "%" + il + "d  %4s %" + al + "d %" + bl + "d %" + cl + "d  | %-" + dl + "s |%n",
                    i, ins.opName(), ins.a(), ins.b(), ins.c(), dis[i]);
        }
        return sw.toString();
    }

}
