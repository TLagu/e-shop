package com.lagu.eshop.product.dto;

import com.lagu.eshop.product.entity.Status;

public class TemplateDto {
    private Long id;
    private Status status;
    private CategoryDto category;
    private String name;

    public Long getId() {
        return id;
    }

    public TemplateDto setId(Long id) {
        this.id = id;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public TemplateDto setStatus(Status status) {
        this.status = status;
        return this;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public TemplateDto setCategory(CategoryDto category) {
        this.category = category;
        return this;
    }

    public String getName() {
        return name;
    }

    public TemplateDto setName(String name) {
        this.name = name;
        return this;
    }
}
