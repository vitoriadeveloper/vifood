package com.vitoriadeveloper.vifood.domain.model.dto;

import java.util.List;

public record Pagination<T>(
    List<T> content,
    int pageNumber,
    int pageSize,
    long totalElements,
    int totalPages,
    boolean first,
    boolean last,
    boolean hasNext,
    boolean hasPrevious
) {
    public Pagination(List<T> content, int pageNumber, int pageSize, long totalElements) {
        this(
            content,
            pageNumber,
            pageSize,
            totalElements,
            (int) Math.ceil((double) totalElements / pageSize),
            pageNumber == 0,
            pageNumber >= Math.ceil((double) totalElements / pageSize) - 1,
            pageNumber < Math.ceil((double) totalElements / pageSize) - 1,
            pageNumber > 0
        );
    }
}

