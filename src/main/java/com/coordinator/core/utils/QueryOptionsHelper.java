package com.coordinator.core.utils;

import com.coordinator.core.models.ImmutableQueryOptions;
import com.coordinator.core.models.QueryOptions;

import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class QueryOptionsHelper {
    static final int DEFAULT_PAGE = 0;
    static final int DEFAULT_PAGE_SIZE = 48;
    static final int DEFAULT_STATUS_ID = 1;
    static final String DEFAULT_QUERY = "";
    static final String DEFAULT_ORDER_BY = "";
    static final Boolean DEFAULT_IS_ASCENDING = true;

    private static ImmutableQueryOptions.Builder getBuilderFromQueryParameters(Map<String, String> params) {
        ImmutableQueryOptions.Builder builder = ImmutableQueryOptions.builder()
                .withPage(getParam(params, "page", DEFAULT_PAGE))
                .withPageSize(getParam(params, "pageSize", DEFAULT_PAGE_SIZE));

        if (params.containsKey("statusId")) {
            builder.withStatusId(getParam(params, "statusId", DEFAULT_STATUS_ID));
        }

        if (params.containsKey("query")) {
            builder.withQuery(getParam(params, "query", DEFAULT_QUERY));
        }

        if (params.containsKey("sortField")) {
            builder.withSortField(getParam(params, "sortField", DEFAULT_ORDER_BY));
            builder.withIsAscending(getParam(params, "isAscending", DEFAULT_IS_ASCENDING));
        }
        return builder;
    }

    public static QueryOptions fromQueryParameters(Map<String, String> params) {
        ImmutableQueryOptions.Builder builder = getBuilderFromQueryParameters(params);

        return builder.build();
    }

    public static QueryOptions fromQueryParamters(Map<String, String> params, List<String> allowedSortFields) {
        ImmutableQueryOptions.Builder builder = getBuilderFromQueryParameters(params);

        builder.withAllowedSortFields(allowedSortFields);

        return builder.build();
    }

    private static boolean getParam(Map<String, String> params, String name, boolean defaultValue) {
        if (params.containsKey(name)) {
            return Boolean.parseBoolean(params.get(name));
        }
        return defaultValue;
    }

    private static String getParam(Map<String, String> params, String name, String defaultValue) {
        if (params.containsKey(name)) {
            return params.get(name);
        }
        return defaultValue;
    }

    private static int getParam(Map<String, String> params, String name, int defaultValue) {
        if (params.containsKey(name)) {
            try {
                return parseInt(params.get(name));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException((String.format("Invalid value for %s: %s", name, params.get("page"))));
            }
        }
        return defaultValue;
    }
}
