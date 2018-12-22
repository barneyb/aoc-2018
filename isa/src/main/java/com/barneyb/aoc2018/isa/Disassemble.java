package com.barneyb.aoc2018.isa;

import com.barneyb.aoc2018.isa.util.Disassembly;
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
        forLoops();
        doWhileLoops();
        whileTrueLoops();
        gotoJumps();
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

    private void forLoops() {
        Instruction[] instructions = prog.instructions;
        // start later, because we need five instructions
        for (int ip = 4; ip < instructions.length; ip++) {
            Instruction inc = instructions[ip - 3];
            if (! "addi".equals(inc.opName())) continue;
            int countReg = inc.c();
            Instruction cmp = instructions[ip - 2];
            if (!cmp.opName().startsWith("gt")) {
                continue;
            }
            if (cmp.a() != countReg) continue;
            int cmpReg = cmp.c();
            if (cmpReg == prog.ipr) continue;
            Instruction add = instructions[ip - 1];
            if (!"addr".equals(add.opName())) continue;
            if (add.a() == prog.ipr && add.b() != cmpReg) continue;
            if (add.b() == prog.ipr && add.a() != cmpReg) continue;
            Instruction set = instructions[ip];
            if (! "seti".equals(set.opName())) continue;
            if (set.c() != prog.ipr) continue;
            Instruction init = instructions[set.a()];
            if (! "seti".equals(init.opName())) continue;
            if (init.c() != countReg) continue;

            Line l = lines.get(posMap.get(ip - 3));
            l.code("// increment");
            l = lines.get(posMap.get(ip - 2));
            l.code("// test");
            l = lines.get(posMap.get(ip - 1));
            l.code("// break");
            l = lines.get(posMap.get(ip));
            l.code("}");
            l = lines.get(posMap.get(set.a()));
            String cn = Disassembly.registerName(prog.ipr, countReg);
            String ln = Disassembly.registerName(prog.ipr, cmp.b());
            l.code("for (" + cn + " = " + init.a() + "; " + cn + " <= " + ln + " ; " + cn + " += " + inc.b() + ") {");
        }
    }

    private void doWhileLoops() {
        Instruction[] instructions = prog.instructions;
        // start later, because we need three instructions
        for (int ip = 2; ip < instructions.length; ip++) {
            Instruction cmp = instructions[ip - 2];
            if (! cmp.opName().startsWith("eq") && !cmp.opName().startsWith("gt")) {
                continue;
            }
            int r = cmp.c();
            if (r == prog.ipr) continue;
            Instruction add = instructions[ip - 1];
            if (!"addr".equals(add.opName())) continue;
            if (add.a() == prog.ipr && add.b() != r) continue;
            if (add.b() == prog.ipr && add.a() != r) continue;
            Instruction set = instructions[ip];
            if (! "seti".equals(set.opName())) continue;
            if (set.c() != prog.ipr) continue;
            Line l = lines.get(posMap.get(ip));
            if (l.hasCode()) continue;
            String expr = cmp.disassemble(ip - 2, prog.ipr)
                    .replaceFirst("^\\s*[a-f]\\s*=\\s*", "")
                    .replaceFirst("\\s*\\? 1 : 0", "")
                    .replace(">", "<=")
                    .replace("==", "!=");
            l.code("} while (" + expr + ");");
            l = lines.get(posMap.get(ip - 1));
            l.code("// break");
            insertLine(new Line("do {"), posMap.get(set.a()) + 1);
        }
    }

    private void whileTrueLoops() {
        Instruction[] instructions = prog.instructions;
        for (int ip = 0; ip < instructions.length; ip++) {
            Instruction ins = instructions[ip];
            if ("seti".equals(ins.opName()) && ins.c() == prog.ipr) {
                Line l = lines.get(posMap.get(ip));
                if (l.hasCode()) continue;
                // an unconditional jump!
                int to = ins.a();
                if (to > l.index()) {
                    l.code("// goto " + (to + 1));
                    continue; // forward jump :(
                }
                l.code("} while (true);");
                insertLine(new Line("do {"), posMap.get(to) + 1);
            }
        }
    }

    private void gotoJumps() {
        Instruction[] instructions = prog.instructions;
        for (int ip = 0; ip < instructions.length; ip++) {
            Instruction ins = instructions[ip];
            if ("addi".equals(ins.opName()) && ins.a() == prog.ipr && ins.c() == prog.ipr) {
                Line l = lines.get(posMap.get(ip));
                if (l.hasCode()) continue;
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
        int al = 0, bl = 0, cl = 0;
        String[] dis = new String[prog.instructions.length];
        for (int ip = 0, l = prog.instructions.length; ip < l; ip++) {
            Instruction ins = prog.instructions[ip];
            al = Math.max(al, ("" + ins.a()).length());
            bl = Math.max(bl, ("" + ins.b()).length());
            cl = Math.max(cl, ("" + ins.c()).length());
            String di = ins.disassemble(ip, prog.ipr);
            dis[ip] = di;
        }
        int rl = 0;
        int hl = 0;
        for (Line l : lines) {
            if (l.hasInstruction()) {
                Instruction ins = l.instruction();
                l.raw(String.format(
                        "%4s %" + al + "d %" + bl + "d %" + cl + "d",
                        ins.opName(), ins.a(), ins.b(), ins.c()));
                l.human(dis[l.index()]);
            }
            rl = Math.max(rl, l.raw().length());
            hl = Math.max(hl, l.human().length());
        }
        int indent = 0;
        for (Line l : lines) {
            if (l.hasInstruction()) {
                sb.append(String.format("%" + il + "d", l.index()));
            } else {
                sb.append(String.format("%" + il + "s", ""));
            }
            sb.append("  ")
                    .append(String.format("%-" + rl + "s", l.raw()))
                    .append("".equals(l.human()) ? "     " : "  |  ")
                    .append(String.format("%-" + hl + "s", l.human()))
                    .append("  |  ");
            if (l.hasCode()) {
                if (l.code().contains("}")) indent -= 1;
                if (indent > 0) {
                    sb.append(String.format("%" + (indent * 2) + "s", ""));
                }
                sb.append(l.code());
                if (l.code().contains("{")) indent += 1;
            }
            sb.append('\n');
        }
        return sb.toString();
    }

}
