package com.lagu.eshop.core.pagination;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Metadata for displaying data on the page
 *
 * @author Tomasz Łagowski
 * @version 1.0
 */
@Getter
@AllArgsConstructor
public class Metadata {

    private final int totalPages;
    private final long totalElements;
    private int page;
    private int size;

    public Metadata setPage(int page) {
        this.page = page;
        return this;
    }

    public Metadata setSize(int size) {
        this.size = size;
        return this;
    }

}
