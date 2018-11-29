package com.barneyb.aoc2018.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public final class FileUtils {

    public static String readFile(String path) {
        File f = new File(path);
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
