package com.barneyb.aoc2018.isa;

import com.barneyb.aoc2018.util.BST;
import com.barneyb.aoc2018.util.List;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Disassemble {

    private final Program prog;
    private final List<Line> lines;
    private final BST<Integer, Integer> posMap;

    public Disassemble(Program p) {
        this.prog = p;
        this.posMap = new BST<>();
        this.lines = new List<>();
        Instruction[] instructions = p.instructions();
        for (int i = 0; i < instructions.length; i++) {
            posMap.put(i, i);
            Instruction ins = instructions[i];
            lines.add(new Line(i, ins));
        }
        initializer();
        unconditionalJumps();
        unknownJumps();
        simpleSets();
    }

    private void initializer() {
        StringWriter sw = new StringWriter();
        PrintWriter out = new PrintWriter(sw);
        boolean started = false;
        for (int i = 0; i < 6; i++) {
            if (i == prog.ipr) continue;
            out.print(started ? ", " : "int ");
            started = true;
            out.printf("%s=0", (char) ('a' + i));
        }
        out.print(';');
        insertLine(new Line("#ip " + prog.ipr, "int ip = 0;"), 0);
        insertLine(new Line(sw.toString()), 1);
    }

    private void insertLine(Line line, int at) {
        // scoot everything after the insert point down by one
        for (int i = at, l = lines.size(); i < l; i++) {
            Line ln = lines.get(i);
            if (! ln.hasInstruction()) continue;
            int idx = ln.index();
            posMap.put(idx, posMap.get(idx) + 1);
        }
        lines.insert(line, at);
    }

    private void unconditionalJumps() {
        Instruction[] instructions = prog.instructions;
        for (int ip = 0; ip < instructions.length; ip++) {
            Line l = lines.get(posMap.get(ip));
            Instruction ins = instructions[ip];
            if ("seti".equals(ins.opName()) && ins.c() == prog.ipr) {
                // an unconditional jump!
                int to = ins.a();
                if (to > l.index()) {
                    l.code("// goto " + (to + 1));
                    continue; // forward jump :(
                }
                l.code("} while (true);");
                insertLine(new Line("do {"), posMap.get(to) + 1); // cuzza "ip++" after instruction
            } else if ("addi".equals(ins.opName()) && ins.a() == prog.ipr && ins.c() == prog.ipr) {
                l.code("// goto " + (ip + ins.b() + 1));
            }
        }
    }

    private void unknownJumps() {
        Instruction[] instructions = prog.instructions;
        for (int ip = 0; ip < instructions.length; ip++) {
            Line l = lines.get(posMap.get(ip));
            if (l.hasCode()) continue;
            Instruction ins = instructions[ip];
            if (ins.c() == prog.ipr) {
                l.code("// todo: " + ins.disassemble(ip, prog.ipr));
            }
        }
    }

    private void simpleSets() {
        Instruction[] instructions = prog.instructions;
        for (int ip = 0; ip < instructions.length; ip++) {
            Line l = lines.get(posMap.get(ip));
            if (l.hasCode()) continue;
            Instruction ins = instructions[ip];
            l.code(ins.disassemble(ip, prog.ipr));
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int il = ("" + (prog.instructions.length - 1)).length();
        int al = 0, bl = 0, cl = 0, dl = 0;
        String[] dis = new String[prog.instructions.length];
        for (int ip = 0, l = prog.instructions.length; ip < l; ip++) {
            Instruction ins = prog.instructions[ip];
            al = Math.max(al, ("" + ins.a()).length());
            bl = Math.max(bl, ("" + ins.b()).length());
            cl = Math.max(cl, ("" + ins.c()).length());
            String di = ins.disassemble(ip, prog.ipr);
            dis[ip] = di;
            dl = Math.max(dl, di.length());
        }
        int tl = 0;
        for (Line l : lines) {
            if (l.hasInstruction()) {
                Instruction ins = l.instruction();
                l.text(String.format(
                        "%4s %" + al + "d %" + bl + "d %" + cl + "d  | %-" + dl + "s",
                        ins.opName(), ins.a(), ins.b(), ins.c(), dis[l.index()]));
            }
            tl = Math.max(tl, l.text().length());
        }
        int indent = 0;
        for (Line l : lines) {
            if (l.hasInstruction()) {
                sb.append(String.format("%" + il + "d", l.index()));
            } else {
                sb.append(String.format("%" + il + "s", ""));
            }
            sb.append("  ")
                    .append(String.format("%-" + tl + "s", l.text()))
                    .append(" |");
            if (l.hasCode()) {
                if (l.code().contains("}")) indent -= 1;
                sb.append(String.format("%" + (indent * 2 + 1) + "s", "")).append(l.code());
                if (l.code().contains("{")) indent += 1;
            } else if (l.hasInstruction()) {
                Instruction ins = l.instruction();
            }
            sb.append('\n');
        }
        return sb.toString();
    }

}
