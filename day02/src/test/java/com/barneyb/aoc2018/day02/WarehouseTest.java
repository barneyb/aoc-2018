package com.barneyb.aoc2018.day02;

import org.junit.Test;

import static com.barneyb.aoc2018.day02.Day02Test.EXAMPLE_1;
import static org.junit.Assert.assertEquals;

public class WarehouseTest {

    @Test
    public void checksum() {
        assertEquals(12, new Warehouse(EXAMPLE_1).checksum());
    }
}