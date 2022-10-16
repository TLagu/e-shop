package com.lagu.eshop.module.product.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * Form for product categories
 *
 * @author Tomasz Łagowski
 * @version 1.0
 */
@Getter
@EqualsAndHashCode
public class CategoryForm {

    private Long id = 0L;
    @Size(min = 3, max = 25, message = "Długość powinny być pomiędzy 3 i 25")
    private String name;
    @Size(min = 3, max = 200, message = "Długość powinny być pomiędzy 3 i 200")
    private String description;
    private Long parent;
    private List<TemplateForm> templates;

    public CategoryForm setId(Long id) {
        this.id = id;
        return this;
    }

    public CategoryForm setName(String name) {
        this.name = name;
        return this;
    }

    public CategoryForm setDescription(String description) {
        this.description = description;
        return this;
    }

    public CategoryForm setParent(Long parent) {
        this.parent = parent;
        return this;
    }

    public CategoryForm setTemplates(List<TemplateForm> templates) {
        this.templates = templates;
        return this;
    }

    /**
     * Object status verification (new/existing)
     *
     * @return Status whether the object is new or existing
     * @since 1.0
     */
    public boolean isNew() {
        return id == null || id == 0;
    }

}