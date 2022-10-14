package com.lagu.eshop.product.entity;

public enum Status {
    DELETED("Usunięte"),
    INACTIVE("Nieaktywne"),
    ACTIVE("Aktywne");

    private final String description;

    private Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

