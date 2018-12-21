package com.barneyb.aoc2018.isa;

import com.barneyb.aoc2018.util.BST;
import com.barneyb.aoc2018.util.List;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Disassemble {

    private static class Line {
        final StringBuilder sb = new StringBuilder();
        Instruction instruction;

        public Line() {
        }

        public Line(Instruction instruction) {
            this.instruction = instruction;
        }

        public Line(Instruction instruction, CharSequence text) {
            this.instruction = instruction;
            sb.append(text);
        }

        public Line(CharSequence text) {
            sb.append(text);
        }

        public String text() {
            return sb.toString();
        }

        @Override
        public String toString() {
            return text();
        }
    }

    List<Line> lines = new List<>();

    public Disassemble(Program p) {
        Instruction[] instructions = p.instructions();
        BST<Integer, Integer> position = new BST<>();
        for (int i = 0; i < instructions.length; i++) {
            position.put(i, i + 1); // for the header row
        }
        int il = ("" + instructions.length).length();
        int al = 0, bl = 0, cl = 0, dl = 0;
        String[] dis = new String[instructions.length];
        for (int i = 0; i < instructions.length; i++) {
            Instruction ins = instructions[i];
            al = Math.max(al, ("" + ins.a()).length());
            bl = Math.max(bl, ("" + ins.b()).length());
            cl = Math.max(cl, ("" + ins.c()).length());
            String di = ins.disassemble(p.ipr);
            dis[i] = di;
            dl = Math.max(dl, di.length());
        }
        StringWriter sw = new StringWriter();
        PrintWriter out = new PrintWriter(sw);
        out.printf("%" + il + "s  #ip %d %" + (-1 + al + bl + cl + 2 + dl + 3) + "s  | ", "", p.ipr, "");
        boolean started = false;
        for (int i = 0; i < 6; i++) {
            if (i == p.ipr) continue;
            out.print(started ? ", " : "int ");
            started = true;
            out.printf("%s=0", (char) ('a' + i));
        }
        lines.add(new Line(sw.toString()));
        for (int i = 0; i < instructions.length; i++) {
            Instruction ins = instructions[i];
            lines.add(new Line(ins, String.format(
                    "%" + il + "d  %4s %" + al + "d %" + bl + "d %" + cl + "d  | %-" + dl + "s |",
                    i, ins.opName(), ins.a(), ins.b(), ins.c(), dis[i])));
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Line l : lines) {
            sb.append(l).append('\n');
        }
        return sb.toString();
    }

}
