package com.lagu.eshop.module.product.mapper;

import com.lagu.eshop.module.product.dto.CartDto;
import com.lagu.eshop.module.product.entity.CartEntity;
import com.lagu.eshop.module.user.mapper.UserMapper;

/**
 * Cart mapper
 *
 * @author Tomasz Łagowski
 * @version 1.0
 */
public class CartMapper {

    /**
     * Form entity -> DTO
     *
     * @param entity Cart entity
     * @return Cart DTO
     * @since 1.0
     */
    public static CartDto map(CartEntity entity) {
        return new CartDto()
                .setUser(UserMapper.map(entity.getUser()))
                .setProduct(ProductMapper.map(entity.getProduct(), null))
                .setAmount(entity.getAmount())
                .setTotal(entity.getTotal());
    }

}
