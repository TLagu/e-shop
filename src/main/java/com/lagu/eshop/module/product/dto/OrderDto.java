package com.lagu.eshop.module.product.dto;

import com.lagu.eshop.module.user.entity.UserEntity;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Order DTO
 *
 * @author Tomasz Łagowski
 * @version 1.0
 */
@Getter
public class OrderDto {

    private LocalDateTime createdOn;
    private String uuid;
    private UserEntity user;
    private String street;
    private String postCode;
    private String post;
    private Double total;
    private Set<OrderDetailsDto> orderDetails;

    public OrderDto setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public OrderDto setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public OrderDto setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public OrderDto setStreet(String street) {
        this.street = street;
        return this;
    }

    public OrderDto setPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }

    public OrderDto setPost(String post) {
        this.post = post;
        return this;
    }

    public OrderDto setTotal(Double total) {
        this.total = total;
        return this;
    }

    public OrderDto setOrderDetails(Set<OrderDetailsDto> orderDetails) {
        this.orderDetails = orderDetails;
        return this;
    }

}
