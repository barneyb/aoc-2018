package com.barneyb.aoc2018.isa;

import com.barneyb.aoc2018.util.BST;
import com.barneyb.aoc2018.util.Scanner;

public class Instruction {

    public static Instruction parse(String input) {
        Scanner s = new Scanner(input);
        return new Instruction(
                s.skipWS().readInt(),
                s.skipWS().readInt(),
                s.skipWS().readInt(),
                s.skipWS().readInt()
        );
    }

    public static Instruction parseMapped(String input, BST<String, Integer> opMap) {
        Scanner s = new Scanner(input);
        return new Instruction(
                opMap.get(s.skipWS().readWord()),
                s.skipWS().readInt(),
                s.skipWS().readInt(),
                s.skipWS().readInt()
        );
    }

    private final int opcode, a, b, c;

    public Instruction(int opcode, int a, int b, int c) {
        this.opcode = opcode;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public final int opcode() {
        return opcode;
    }

    public final Op op() {
        return Op.OPS[opcode];
    }

    public final String opName() {
        return op().name();
    }

    public final int a() {
        return a;
    }

    public final int b() {
        return b;
    }

    public final int c() {
        return c;
    }

    public final void execute(int[] rs) {
        op().execute(rs, this);
    }

    public String disassemble(int ip, int ipr) {
        int a = this.a, b = this.b;
        String name = opName();
        if (this.b == this.c && (name.startsWith("add") || name.startsWith("mul"))) {
            a = this.b;
            b = this.a;
        }
        if (a == ipr && "setr".equals(name)) {
            return Op.OPS[opcode + 1].disassemble(ipr, ip, b, c);
        } else if (b == ipr && ("addr".equals(name) || "mulr".equals(name))) {
            return Op.OPS[opcode + 1].disassemble(ipr, a, ip, c);
        }
        return op().disassemble(ipr, a, b, c);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Instruction)) return false;

        Instruction that = (Instruction) o;

        if (opcode != that.opcode) return false;
        if (a != that.a) return false;
        if (b != that.b) return false;
        return c == that.c;
    }

    @Override
    public int hashCode() {
        int result = opcode;
        result = 31 * result + a;
        result = 31 * result + b;
        result = 31 * result + c;
        return result;
    }
}
