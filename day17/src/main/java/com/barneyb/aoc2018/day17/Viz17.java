package com.barneyb.aoc2018.day17;

import com.barneyb.aoc2018.util.FileUtils;
import com.barneyb.aoc2018.util.Point;

import javax.swing.*;
import java.awt.*;
import java.util.function.BiConsumer;

public class Viz17 {

    public static final Font FONT = Font.decode(Font.MONOSPACED).deriveFont(20f);
    public static final int ROWS = 42;

    public static void main(String[] args) {
        String input = FileUtils.readFile("day17/input.txt");
        Earth earth = new Earth(Day17.parse(input));
        JFrame f = new JFrame("Day 17");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JTextArea ta = new JTextArea(earth.toString(), ROWS, earth.width());
        ta.setFont(FONT);
        ta.setEditable(false);
        ta.setText("hello, world!");
        Container cp = f.getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(ta, BorderLayout.CENTER);
        JLabel st = new JLabel();
        st.setFont(FONT.deriveFont(30f));
        cp.add(st, BorderLayout.SOUTH);
        f.pack();
        f.setPreferredSize(new Dimension(1000, 800));
        f.setVisible(true);
        new Thread(() -> {
            earth.setObserver(new BiConsumer<Point, Character>() {
                int level = 0;
                @Override
                public void accept(Point p, Character c) {
                    level = Math.max(level, p.y);
                    int minY = Math.max(0, level - ROWS / 3);
                    minY = Math.min(minY, p.y - 2);
                    ta.setText(earth.toString(minY, minY + ROWS - 1, true));
                    st.setText(earth.wetTiles() + " wet tiles | " + earth.resTiles() + " res tiles");
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException ie) {
                        throw new RuntimeException(ie);
                    }
                }
            });
            earth.runWater();
        }).start();
    }
    
}
