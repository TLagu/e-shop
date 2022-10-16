package com.lagu.eshop.module.product.dto;

import com.lagu.eshop.module.product.entity.Status;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * DTO for template
 *
 * @author Tomasz Łagowski
 * @version 1.0
 */
@Getter
@EqualsAndHashCode
public class TemplateDto {
    private Long id;
    private Status status;
    private CategoryDto category;
    private String name;

    public TemplateDto setId(Long id) {
        this.id = id;
        return this;
    }

    public TemplateDto setStatus(Status status) {
        this.status = status;
        return this;
    }

    public TemplateDto setCategory(CategoryDto category) {
        this.category = category;
        return this;
    }

    public TemplateDto setName(String name) {
        this.name = name;
        return this;
    }

}
