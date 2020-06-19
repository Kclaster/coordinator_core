package com.coordinator.core.models;

import com.coordinator.core.annotations.BuilderStyle;
import org.immutables.value.Value;

import java.util.ArrayList;
import java.util.List;

@Value.Immutable
@BuilderStyle
public abstract class QueryOptions {

    @Value.Default
    public Integer getPage() { return 0; }

    @Value.Default
    public Integer getPageSize() { return 48; }

    @Value.Default
    public Integer getStatusId() { return 1; }

    @Value.Default
    public String getQuery() { return ""; }

    @Value.Default
    public String  getSortField() { return ""; }

    @Value.Default
    public Boolean getIsAscending() { return true; }

    @Value.Default
    public List<String> getAllowedSortFields() { return new ArrayList<>();
    }
}
