package com.lagu.eshop.product.dto;

import java.util.Set;

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

    public String getUuid() {
        return uuid;
    }

    public ProductDto setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getModel() {
        return model;
    }

    public ProductDto setModel(String model) {
        this.model = model;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public ProductDto setCategory(CategoryDto category) {
        this.category = category;
        return this;
    }

    public Set<AttributeDto> getAttributes() {
        return attributes;
    }

    public ProductDto setAttributes(Set<AttributeDto> attributes) {
        this.attributes = attributes;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public ProductDto setPrice(Double price) {
        this.price = price;
        return this;
    }

    public String getPath() {
        return path;
    }

    public ProductDto setPath(String path) {
        this.path = path;
        return this;
    }

    public String getCode() {
        return code;
    }

    public ProductDto setCode(String code) {
        this.code = code;
        return this;
    }

    public boolean getCart() {
        return cart;
    }

    public ProductDto setCart(boolean cart) {
        this.cart = cart;
        return this;
    }

    public boolean getWishlist() {
        return wishlist;
    }

    public ProductDto setWishlist(boolean wishlist) {
        this.wishlist = wishlist;
        return this;
    }

    public boolean getCompare() {
        return compare;
    }

    public ProductDto setCompare(boolean compare) {
        this.compare = compare;
        return this;
    }

    public boolean isNew() {
        return uuid == null || uuid.isBlank();
    }

}
