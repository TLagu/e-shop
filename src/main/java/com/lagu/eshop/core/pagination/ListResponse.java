package com.lagu.eshop.core.pagination;

import lombok.Getter;

import java.util.List;

/**
 * An object that stores the data necessary to display paged data
 *
 * @param <T> List of objects displayed on the screen
 * @author Tomasz Łagowski
 * @version 1.0
 */
@Getter
public class ListResponse<T> {

    private final Metadata metadata;
    private final List<T> content;

    public ListResponse(
            List<T> content,
            int totalPages,
            long totalElements,
            int size,
            int page) {
        this.metadata = new Metadata(totalPages, totalElements, size, page);
        this.content = content;
    }

}
