package com.lagu.eshop.module.product.entity;

import lombok.Getter;

/**
 * Product status
 * @author Tomasz Łagowski
 * @version 1.0
 */
@Getter
public enum Status {
    DELETED("Usunięte"),
    INACTIVE("Nieaktywne"),
    ACTIVE("Aktywne");

    private final String description;

    private Status(String description) {
        this.description = description;
    }

}

