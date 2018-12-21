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
        for (int i = 0; i < instructions.length; i++) {
            posMap.put(i, i);
            Instruction ins = instructions[i];
            lines.add(new Line(i, ins));
        }
        initializer(al + bl + cl + 2 + dl);
        unconditionalJumps();
    }

    private void initializer(int tl) {
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
        for (int i = 0; i < lines.size(); i++) {
            Line l = lines.get(i);
            if (! l.hasInstruction()) continue;
            Instruction ins = l.instruction();
            if ("seti".equals(ins.opName()) && ins.c() == prog.ipr) {
                // an unconditional jump!
                int to = ins.a();
                if (to > l.index()) continue; // forward jump :(
                l.code("} while (true);");
                insertLine(new Line("do {"), posMap.get(to) + 1); // cuzza "ip++" after instruction
                i += 1; // don't reprocess this one
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int il = ("" + (prog.instructions.length - 1)).length();
        int al = 0, bl = 0, cl = 0, dl = 0;
        String[] dis = new String[prog.instructions.length];
        for (int i = 0, l = prog.instructions.length; i < l; i++) {
            Instruction ins = prog.instructions[i];
            al = Math.max(al, ("" + ins.a()).length());
            bl = Math.max(bl, ("" + ins.b()).length());
            cl = Math.max(cl, ("" + ins.c()).length());
            String di = ins.disassemble(prog.ipr);
            dis[i] = di;
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
                sb.append(' ').append(l.code());
            }
            sb.append('\n');
        }
        return sb.toString();
    }

}
