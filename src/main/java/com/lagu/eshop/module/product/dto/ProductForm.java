package com.lagu.eshop.module.product.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * Product form
 *
 * @author Tomasz Łagowski
 * @version 1.0
 */
@Getter
@EqualsAndHashCode
public class ProductForm implements Serializable {

    private String uuid;
    @Size(min = 3, max = 200, message = "Długość powinny być pomiędzy 3 i 200")
    private String model;
    @Size(min = 3, max = 10000, message = "Długość powinny być pomiędzy 3 i 10000")
    private String description;
    private Long category;
    private List<AttributeForm> attributes;
    @DecimalMin(value = "0.01", message = "Wartość minimalna to 0.01")
    @DecimalMax(value = "100000.0", message = "Wartość maksymalna to 1000000.0")
    private Double price;
    @Size(max = 200, message = "Długość powinna być mniejsza niż 200")
    private String path;
    @Size(max = 50, message = "Długość powinna być mniejsza niż 50")
    private String code;

    public ProductForm setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public ProductForm setModel(String model) {
        this.model = model;
        return this;
    }

    public ProductForm setDescription(String description) {
        this.description = description;
        return this;
    }

    public ProductForm setCategory(Long category) {
        this.category = category;
        return this;
    }

    public ProductForm setAttributes(List<AttributeForm> attributes) {
        this.attributes = attributes;
        return this;
    }

    public ProductForm setPrice(Double price) {
        this.price = price;
        return this;
    }

    public ProductForm setPath(String path) {
        this.path = path;
        return this;
    }

    public ProductForm setCode(String code) {
        this.code = code;
        return this;
    }

    /**
     * Object status verification (new/existing)
     *
     * @return Status whether the object is new or existing
     * @since 1.0
     */
    public boolean isNew() {
        return uuid == null || uuid.isBlank();
    }

}
