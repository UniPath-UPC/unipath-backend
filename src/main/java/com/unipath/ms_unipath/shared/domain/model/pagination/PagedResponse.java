package com.unipath.ms_unipath.shared.domain.model.pagination;

import lombok.Getter;

import java.util.List;

@Getter
public class PagedResponse<T> {
    private final List<T> content;
    private final long totalElements;
    private final int pageSize;
    private final int totalPages;
    private final int currentPage;

    public PagedResponse(List<T> content, long totalElements, int pageSize, int totalPages, int currentPage) {
        this.content = content;
        this.totalElements = totalElements;
        this.pageSize = pageSize;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
    }
}
