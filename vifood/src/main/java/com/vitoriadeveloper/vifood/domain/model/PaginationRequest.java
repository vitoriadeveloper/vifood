package com.vitoriadeveloper.vifood.domain.model;

public record PaginationRequest(
    int page,
    int size,
    String sort
) {
    public PaginationRequest(int page, int size) {
        this(page, size, null);
    }
}

