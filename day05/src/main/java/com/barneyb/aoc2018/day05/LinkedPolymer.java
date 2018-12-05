package com.barneyb.aoc2018.day05;

// both parts: ~75 ms
public class LinkedPolymer implements Polymer {

    public static LinkedPolymer parse(String str) {
        return new LinkedPolymer(str.toCharArray());
    }

    private Link head;
    private Link tail;
    private int length;

    private static class Link {
        int c;
        Link left, right;

        public Link(int c, Link left, Link right) {
            this.c = c;
            this.left = left;
            this.right = right;
        }
    }

    public LinkedPolymer(char[] cs) {
        head = tail = new Link(cs[0], null, null);
        for (int i = 1, l = cs.length; i < l; i++) {
            tail.right = new Link(cs[i], tail, null);
            tail = tail.right;
        }
        length = cs.length;
    }

    @Override
    public void reduce() {
        int a, b;
        Link l = head;
        while (l != null && l.right != null) {
            a = l.c;
            b = l.right.c;
            if (a - b == 32 || b - a == 32) {
                Link next = l.left == null ? head : l.left;
                delete(l.right);
                delete(l);
                l = next;
            } else {
                l = l.right;
            }
        }
    }

    private void delete(Link l) {
        if (head == l) {
            head = l.right;
        }
        if (tail == l) {
            tail = l.left;
        }
        if (l.right != null) {
            l.right.left = l.left;
        }
        if (l.left != null) {
            l.left.right = l.right;
        }
        length -= 1;
    }

    @Override
    public int length() {
        return length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Link l = head; l != null; l = l.right) {
            sb.append((char) l.c);
        }
        return sb.toString();
    }
}
