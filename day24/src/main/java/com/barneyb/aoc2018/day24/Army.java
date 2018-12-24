package com.barneyb.aoc2018.day24;

import com.barneyb.aoc2018.util.Scanner;
import com.barneyb.aoc2018.util.TreeSet;

class Army {

    static Army parse(String input) {
        return scan(new Scanner(input.trim()));
    }

    static Army scan(Scanner s) {
        Army a = new Army();
        while (! s.probe(':')) s.skipWord();
        s.skip(":\n");
        int idx = 0;
        do {
            Group g = Group.scan(s);
            g.index(++idx);
            a.addGroup(g);
            if (! s.probe()) break;
            s.skip('\n');
        } while (s.probe() && ! s.probe('\n'));
        return a;
    }

    private String label;
    private TreeSet<Group> groups = new TreeSet<>();

    public String label() {
        return label;
    }

    public void label(String l) {
        label = l;
    }

    public int units() {
        int units = 0;
        for (Group g : groups) {
            units += g.units();
        }
        return units;
    }

    public void addGroup(Group g) {
        groups.add(g);
    }

    public boolean alive() {
        for (Group g : groups) {
            if (g.alive()) return true;
        }
        return false;
    }

    public TreeSet<Group> groups() {
        return groups;
    }

    TreeSet<TargetSelection> selectTargets(Army defenders) {
        TreeSet<TargetSelection> selections = new TreeSet<>();
        TreeSet<Group> dgs = defenders.groups().duplicate();
        for (Group a : groups) {
            if (dgs.isEmpty()) break;
            int maxDamage = 0;
            Group target = null;
            for (Group d : dgs) {
                int damage = a.calcDamage(d);
                System.out.println(new TargetSelection(label, a, d, damage));
                if (damage > maxDamage) {
                    maxDamage = damage;
                    target = d;
                }
            }
            if (target == null) continue;
            dgs.delete(target);
            selections.add(new TargetSelection(label, a, target, maxDamage));
        }
        return selections;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(label).append(":\n");
        for (Group g : groups)
            sb.append(g).append('\n');
        return sb.toString();
    }

    public void buryTheDead() {
        TreeSet<Group> alive = new TreeSet<>();
        for (Group g : groups) {
            if (! g.alive()) continue;
            alive.add(g);
        }
        groups = alive;
    }

}
