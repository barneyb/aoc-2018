package com.barneyb.aoc2018.day13;

import com.barneyb.aoc2018.util.FileUtils;
import com.barneyb.aoc2018.util.Point;

import javax.swing.*;
import java.awt.*;

public class Viz13 {

    public static final Font FONT = Font.decode(Font.MONOSPACED).deriveFont(25.0f);

    public static void main(String[] args) {
        String input = FileUtils.readFile("day13/input.txt");
        Map m = Map.parse(input);
        JFrame f = new JFrame("Day 13");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JTextArea ta = new JTextArea(m.toString(), m.height(), m.width());
        ta.setFont(FONT);
        ta.setEditable(false);
        Container cp = f.getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(new JScrollPane(ta), BorderLayout.CENTER);
        JLabel st = new JLabel();
        st.setFont(FONT);
        cp.add(st, BorderLayout.SOUTH);
        f.pack();
        f.setVisible(true);
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(75);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                m.tick();
                ta.setText(m.toString());
                int nCart = m.cartCount();
                int nCrash = m.crashCount();
                StringBuilder sb = new StringBuilder();
                sb.append(nCart).append(" cart");
                if (nCart != 1) sb.append("s");
                sb.append(" | ").append(nCrash).append(" crash");
                if (nCrash != 1) sb.append("es");
                if (nCrash > 0) {
                    Point firstCrash = m.locationOfFirstCrash();
                    sb.append(" | first crash: ").append(firstCrash);
                }
                if (nCart == 1) {
                    Point finalCart = m.locationOfFinalCart();
                    sb.append(" | final cart: ").append(finalCart);
                }
                st.setText(sb.toString());
                if (nCart <= 1) break;
            }
        }).start();
    }

}
