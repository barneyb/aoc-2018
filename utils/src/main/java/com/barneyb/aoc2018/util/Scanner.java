package com.barneyb.aoc2018.util;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.function.Predicate;

/**
 * I provide a way to construct extremely simple string parsing routines.
 */
public class Scanner {

    private final PushbackReader in;

    public Scanner(String s) {
        this(new StringReader(s));
    }

    public Scanner(Reader r) {
        this.in = new PushbackReader(r);
    }

    public Scanner skip(char c) {
        // kinda silly
        return skip(new String(new char[] {c}));
    }

    public Scanner skip(int l) {
        try {
            int c;
            for (int i = 0; i < l; i++) {
                c = in.read();
                if (c < 0) {
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    public Scanner skip(String s) {
        try {
            int c;
            for (int i = 0, l = s.length(); i < l; i++) {
                c = in.read();
                if (c < 0) {
                    throw new NoMatchException("End of stream reached, expected '" + s.charAt(i) + "'");
                }
                if (c != s.charAt(i)) {
                    throw new NoMatchException("Found '" + (char) c + "', expected '" + s.charAt(i) + "'");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    public boolean probe(char c) {
        try {
            int glerg = in.read();
            boolean result = c == glerg;
            in.unread(glerg);
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Scanner skipWS() {
        readWhile(Character::isWhitespace);
        return this;
    }

    public String read(int l) {
        StringBuilder sb = new StringBuilder();
        try {
            int c;
            for (int i = 0; i < l; i++) {
                c = in.read();
                if (c < 0) {
                    break;
                }
                sb.append((char) c);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

    public String readWord() {
        String s = readUntil(Character::isWhitespace);
        readWhile(Character::isWhitespace);
        return s;
    }

    public Scanner skipWord() {
        readWord();
        return this;
    }

    public int readInt() {
        int c;
        boolean negative;
        try {
            c = in.read();
            negative = c == '-';
            if (! negative) in.unread(c);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String s = readWhile(Character::isDigit);
        if (s.length() == 0) {
            throw new NoMatchException("No integer was found");
        }
        return (negative ? -1 : 1) * Integer.parseInt(s);
    }

    private String readUntil(Predicate<Integer> test) {
        return readWhile(test.negate());
    }

    private String readWhile(Predicate<Integer> test) {
        StringBuilder sb = new StringBuilder();
        readIntoWhile(sb, test);
        return sb.toString();
    }

    private void readIntoUntil(StringBuilder sb, Predicate<Integer> test) {
        readIntoWhile(sb, test.negate());
    }

    private void readIntoWhile(StringBuilder sb, Predicate<Integer> test) {
        try {
            int c;
            while (true) {
                c = in.read();
                if (c < 0) {
                    break;
                }
                if (! test.test(c)) {
                    in.unread(c);
                    break;
                }
                sb.append((char) c);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String rest() {
        return read(Integer.MAX_VALUE);
    }
}
