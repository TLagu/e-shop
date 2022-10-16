package com.lagu.eshop.module.product.dto;

import lombok.Getter;

/**
 * Order details DTO
 *
 * @author Tomasz Łagowski
 * @version 1.0
 */
@Getter
public class OrderDetailsDto {

    private OrderDto order;

    private ProductDto product;

    private Double price;

    private Integer amount;

    private Double total;

    public OrderDetailsDto setOrder(OrderDto order) {
        this.order = order;
        return this;
    }

    public OrderDetailsDto setProduct(ProductDto product) {
        this.product = product;
        return this;
    }

    public OrderDetailsDto setPrice(Double price) {
        this.price = price;
        return this;
    }

    public OrderDetailsDto setAmount(Integer amount) {
        this.amount = amount;
        return this;
    }

    public OrderDetailsDto setTotal(Double total) {
        this.total = total;
        return this;
    }

}
