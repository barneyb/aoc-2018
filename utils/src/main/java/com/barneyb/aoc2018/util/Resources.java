package com.barneyb.aoc2018.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Resources {

    public static String asText(String path) {
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
        BufferedReader r = new BufferedReader(new InputStreamReader(in));
        try {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

}
