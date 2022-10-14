package com.lagu.eshop.module.product.dto;

import lombok.Getter;

import java.util.Set;

/**
 * DTO for product categories
 * @author Tomasz Łagowski
 * @version 1.0
 */
@Getter
public class CategoryDto {
    private Long id;
    private String name;
    private String description;
    private CategoryDto parent;
    private Set<TemplateDto> templates;

    public CategoryDto setId(Long id) {
        this.id = id;
        return this;
    }

    public CategoryDto setName(String name) {
        this.name = name;
        return this;
    }

    public CategoryDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public CategoryDto setParent(CategoryDto parent) {
        this.parent = parent;
        return this;
    }

    public CategoryDto setTemplates(Set<TemplateDto> templates) {
        this.templates = templates;
        return this;
    }

    /**
     * Object status verification (new/existing)
     * @since 1.0
     * @return Status whether the object is new or existing
     */
    public boolean isNew() {
        return id == null || id == 0;
    }
}
