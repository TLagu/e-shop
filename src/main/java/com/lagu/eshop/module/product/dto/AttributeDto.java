package com.lagu.eshop.module.product.dto;

import lombok.Getter;

/**
 * DTO for additional product attributes
 *
 * @author Tomasz Łagowski
 * @version 1.0
 */
@Getter
public class AttributeDto {
    private Long id;
    private String name;
    private String description;

    public AttributeDto setId(Long id) {
        this.id = id;
        return this;
    }

    public AttributeDto setName(String name) {
        this.name = name;
        return this;
    }

    public AttributeDto setDescription(String description) {
        this.description = description;
        return this;
    }

}
