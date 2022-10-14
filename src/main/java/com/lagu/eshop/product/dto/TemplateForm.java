package com.lagu.eshop.product.dto;

import lombok.Getter;

import javax.validation.constraints.Size;

/**
 * Form for template
 * @author Tomasz Łagowski
 * @version 1.0
 */
@Getter
public class TemplateForm {

    private Long id;
    private String status;
    private Long category;
    @Size(min = 3, max = 200, message = "Długość powinny być pomiędzy 3 i 200")
    private String name;

    public TemplateForm setId(Long id) {
        this.id = id;
        return this;
    }

    public TemplateForm setStatus(String status) {
        this.status = status;
        return this;
    }

    public TemplateForm setCategory(Long category) {
        this.category = category;
        return this;
    }

    public TemplateForm setName(String name) {
        this.name = name;
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
