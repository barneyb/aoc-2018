package com.barneyb.aoc2018.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public final class FileUtils {

    public static String readFile(String path) {
        return readFile(new File(path));
    }

    public static String readFile(File f) {
        if (! f.exists()) {
            throw new IllegalArgumentException("File not found '" + f.getAbsolutePath() + '\'');
        }
        try {
            return new String(Files.readAllBytes(f.toPath()));
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }
}
