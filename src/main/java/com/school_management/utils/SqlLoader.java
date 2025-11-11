package com.school_management.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class SqlLoader {
    public static String load(String path) throws IOException {
        InputStream is = SqlLoader.class.getClassLoader().getResourceAsStream(path);
        if (is == null) throw new FileNotFoundException("Fichier SQL introuvable: " + path);
        return new String(is.readAllBytes(), StandardCharsets.UTF_8);
    }
}
