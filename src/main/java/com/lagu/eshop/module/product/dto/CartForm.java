package com.lagu.eshop.module.product.dto;

import com.lagu.eshop.module.user.dto.UserDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.validation.constraints.*;

/**
 * Cart form
 *
 * @author Tomasz Łagowski
 * @version 1.0
 */
@Getter
@EqualsAndHashCode
public class CartForm {

    private UserDto user;
    private ProductDto product;
    @Min(1)
    @Max(100)
    private Integer amount;
    @DecimalMin(value = "0.01", message = "Wartość minimalna to 0.01")
    @DecimalMax(value = "1000000.0", message = "Wartość maksymalna to 1000000.0")
    private Double total;
    private String uuid;
    @Size(min = 3, max = 200, message = "Długość powinny być pomiędzy 3 i 200")
    private String model;
    @Size(max = 50, message = "Długość powinna być mniejsza niż 50")
    private String code;
    @DecimalMin(value = "0.01", message = "Wartość minimalna to 0.01")
    @DecimalMax(value = "100000.0", message = "Wartość maksymalna to 100000.0")
    private Double price;
    @Size(max = 200, message = "Długość powinna być mniejsza niż 200")
    private String path;

    public CartForm setUser(UserDto user) {
        this.user = user;
        return this;
    }

    public CartForm setProduct(ProductDto product) {
        this.product = product;
        return this;
    }

    public CartForm setAmount(Integer amount) {
        this.amount = amount;
        return this;
    }

    public CartForm setTotal(Double total) {
        this.total = total;
        return this;
    }

    public CartForm setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public CartForm setModel(String model) {
        this.model = model;
        return this;
    }

    public CartForm setCode(String code) {
        this.code = code;
        return this;
    }

    public CartForm setPrice(Double price) {
        this.price = price;
        return this;
    }

    public CartForm setPath(String path) {
        this.path = path;
        return this;
    }

}
