package com.lagu.eshop.module.product.dto;

import lombok.Getter;

import java.util.Set;

/**
 * Basic parameters of products
 * @author Tomasz Łagowski
 * @version 1.0
 */
@Getter
public class ProductDto {

    private String uuid;
    private String model;
    private String description;
    private CategoryDto category;
    private Set<AttributeDto> attributes;
    private Double price;
    private String path;
    private String code;
    private boolean cart;
    private boolean wishlist;
    private boolean compare;

    public ProductDto setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public ProductDto setModel(String model) {
        this.model = model;
        return this;
    }

    public ProductDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public ProductDto setCategory(CategoryDto category) {
        this.category = category;
        return this;
    }

    public ProductDto setAttributes(Set<AttributeDto> attributes) {
        this.attributes = attributes;
        return this;
    }

    public ProductDto setPrice(Double price) {
        this.price = price;
        return this;
    }

    public ProductDto setPath(String path) {
        this.path = path;
        return this;
    }

    public ProductDto setCode(String code) {
        this.code = code;
        return this;
    }

    public ProductDto setCart(boolean cart) {
        this.cart = cart;
        return this;
    }

    public ProductDto setWishlist(boolean wishlist) {
        this.wishlist = wishlist;
        return this;
    }

    public ProductDto setCompare(boolean compare) {
        this.compare = compare;
        return this;
    }

    /**
     * Object status verification (new/existing)
     * @since 1.0
     * @return Status whether the object is new or existing
     */
    public boolean isNew() {
        return uuid == null || uuid.isBlank();
    }

}
