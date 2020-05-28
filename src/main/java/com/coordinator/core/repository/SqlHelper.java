package com.coordinator.core.repository;

import java.io.IOException;
import java.io.InputStream;

public class SqlHelper {
    public static String sql(String fileName) {
        try {
            String resourcePath = String.format("sql/%s.sql", fileName);
            return readSqlFromFile(fileName, resourcePath);
        } catch (IOException e) {
            throw new IllegalStateException("Unable to load SQL from file.", e);
        }
    }
    private static String readSqlFromFile(String fileName, String resourcePath) throws IOException {
        try (InputStream in = SqlHelper.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (in == null) {
                throw new IllegalStateException("Unable to load SQL from file " + fileName);
            }
            return new String(in.readAllBytes());
        }
    }
}
