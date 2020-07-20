package com.coordinator.core.general.main.helpers;

import com.coordinator.core.general.main.models.QueryOptions;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class SqlHelper {
    public static String sql(String fileName) {
        try {
            String resourcePath = String.format("sql/%s.sql", fileName);
            return readSqlFromFile(fileName, resourcePath);
        } catch (IOException e) {
            throw new IllegalStateException("Unable to load SQL from file.", e);
        }
    }

    private static void validateSortField(QueryOptions queryOptions) {
        List<String> allowedSortFields = queryOptions.getAllowedSortFields();

        if (allowedSortFields.size() == 0) {
            throw new IllegalStateException("There are no allowed sortFields defined.");
        }

        if (allowedSortFields.indexOf(queryOptions.getSortField()) == -1) {
            throw new IllegalArgumentException("sortField must be one of " + allowedSortFields.toString());
        }
    }

    public static String sql(String fileName, QueryOptions queryOptions) {
        String sql = sql(fileName);

        int limit = queryOptions.getPageSize();
        int pageSize = queryOptions.getPageSize() * queryOptions.getPage();
        String sortField = queryOptions.getSortField();
        boolean isAscending = queryOptions.getIsAscending();

        if (sortField.isEmpty()) {
            return String.format("%s%nLIMIT %d%nOFFSET %d", sql.trim(), limit, pageSize);
        }

        validateSortField(queryOptions);

        return String.format(
                "%s%nORDER BY %s, id ASC%nLIMIT %d%nOFFSET %d",
                sql.trim(),
                sortField,
                isAscending ? "ASC" : "DESC",
                limit,
                pageSize
        );
    }

    private static String readSqlFromFile(String fileName, String resourcePath) throws IOException {
        try (InputStream in = SqlHelper.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (in == null) {
                throw new IllegalStateException("Unable to load SQL from file " + fileName);
            }
            return new String(in.readAllBytes());
        }
    }

    public static String sqlUpdateTable(String fileName, String tableName) {
        String sql = sql(fileName);

        return String.format("UPDATE %s e %s", tableName, sql.trim());
    }
}
