package com.barneyb.aoc2018.isa;

import com.barneyb.aoc2018.util.Resources;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DisassembleTest {

    @Test
    public void nineteen() {
        Program p = Program.parse(Resources.asText("19_in.txt"));
        String s = new Disassemble(p).toString();
        assertEquals(Resources.asText("19_out.txt"), s);
    }

    @Test
    public void twentyOne() {
        Program p = Program.parse(Resources.asText("21_in.txt"));
        String s = new Disassemble(p).toString();
        assertEquals(Resources.asText("21_out.txt"), s);
    }

}