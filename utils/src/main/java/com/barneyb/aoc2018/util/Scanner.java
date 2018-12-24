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

    public static final int PREVIEW_LEN = 50;
    private final PushbackReader in;

    public Scanner(String s) {
        this(new StringReader(s));
    }

    public Scanner(Reader r) {
        this.in = new PushbackReader(r, PREVIEW_LEN);
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

    public boolean probe() {
        try {
            int glerg = in.read();
            if (glerg >= 0) in.unread(glerg);
            return glerg >= 0;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean probe(char c) {
        try {
            int glerg = in.read();
            boolean result = c == glerg;
            if (glerg >= 0) in.unread(glerg);
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
        readWhile(Character::isWhitespace);
        String s = readWhile(Character::isLetter);
        readWhile(Character::isWhitespace);
        return s;
    }

    public String readNonWhitespace() {
        readWhile(Character::isWhitespace);
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

    public String preview() {
        try {
            char[] buff = new char[PREVIEW_LEN];
            int len = in.read(buff);
            String result = new String(buff, 0, len);
            in.unread(buff, 0, len);
            return result;
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
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
