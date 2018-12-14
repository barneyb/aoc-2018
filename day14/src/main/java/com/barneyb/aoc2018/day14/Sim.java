package com.barneyb.aoc2018.day14;

import com.barneyb.aoc2018.util.List;
import com.barneyb.aoc2018.util.Stack;

class Sim {

    private List<Integer> board;
    private Elf elfOne;
    private Elf elfTwo;

    private class Elf {
        int pos;

        public Elf(int pos) {
            this.pos = pos;
        }

        int score() {
            return board.get(pos);
        }

        void tick() {
            pos = (pos + score() + 1) % board.size();
        }

        public boolean at(int i) {
            return pos == i;
        }
    }

    public Sim() {
        board = new List<>();
        board.add(3);
        board.add(7);
        elfOne = new Elf(0);
        elfTwo = new Elf(1);
    }

    void tick() {
        int sum = elfOne.score() + elfTwo.score();
        Stack<Integer> stack = new Stack<>();
        if (sum == 0) {
            stack.push(sum);
        } else while (sum > 0) {
            stack.push(sum % 10);
            sum = sum / 10;
        }
        for (Integer i : stack) {
            board.add(i);
        }
        elfOne.tick();
        elfTwo.tick();
    }

    int recipeCount() {
        return board.size();
    }

    int scoreAt(int i ) {
        return board.get(i);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0, l = board.size(); i < l; i++) {
            if (elfOne.at(i)) {
                sb.append('(').append(board.get(i)).append(')');
            } else if (elfTwo.at(i)) {
                sb.append('[').append(board.get(i)).append(']');
            } else {
                sb.append(' ').append(board.get(i)).append(' ');
            }
        }
        return sb.toString();
    }
}
