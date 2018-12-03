package com.barneyb.aoc2018.util;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.Reader;
import java.io.StringReader;

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
                    throw new NoMatchException("Found '" + c + "', expected '" + s.charAt(i) + "'");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    public int readInt() {
        StringBuilder sb = new StringBuilder();
        try {
            int c;
            while (true) {
                c = in.read();
                if (c < 0) {
                    break;
                }
                if (! Character.isDigit(c)) {
                    in.unread(c);
                    break;
                }
                sb.append((char) c);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (sb.length() == 0) {
            throw new NoMatchException("No integer was found");
        }
        return Integer.parseInt(sb.toString());
    }

    public String rest() {
        return read(Integer.MAX_VALUE);
    }

}
