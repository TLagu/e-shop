package com.lagu.eshop.module.product.dto;

import com.lagu.eshop.module.user.dto.UserDto;
import lombok.Getter;

/**
 * Cart
 * @author Tomasz Łagowski
 * @version 1.0
 */
@Getter
public class CartDto {

    private UserDto user;

    private ProductDto product;

    private Integer amount;

    private Double total;

    public CartDto setUser(UserDto user) {
        this.user = user;
        return this;
    }

    public CartDto setProduct(ProductDto product) {
        this.product = product;
        return this;
    }

    public CartDto setAmount(Integer amount) {
        this.amount = amount;
        return this;
    }

    public CartDto setTotal(Double total) {
        this.total = total;
        return this;
    }

}
