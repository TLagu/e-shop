package com.lagu.eshop.module.product.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.validation.constraints.Size;

/**
 * Attribute form
 *
 * @author Tomasz Łagowski
 * @version 1.0
 */
@Getter
@EqualsAndHashCode
public class AttributeForm {

    private Long id;
    private Long product;
    @Size(min = 3, max = 25, message = "Długość powinny być pomiędzy 3 i 25")
    private String name;
    @Size(min = 3, max = 200, message = "Długość powinny być pomiędzy 3 i 200")
    private String description;

    public AttributeForm setId(Long id) {
        this.id = id;
        return this;
    }

    public AttributeForm setProduct(Long product) {
        this.product = product;
        return this;
    }

    public AttributeForm setName(String name) {
        this.name = name;
        return this;
    }

    public AttributeForm setDescription(String description) {
        this.description = description;
        return this;
    }

}
