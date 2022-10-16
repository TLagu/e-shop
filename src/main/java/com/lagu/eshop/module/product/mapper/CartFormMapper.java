package com.lagu.eshop.module.product.mapper;

import com.lagu.eshop.module.product.dto.CartForm;
import com.lagu.eshop.module.product.entity.CartEntity;
import com.lagu.eshop.module.user.mapper.UserMapper;

/**
 * Cart form mapper
 *
 * @author Tomasz Łagowski
 * @version 1.0
 */
public class CartFormMapper {

    /**
     * Form entity -> form
     *
     * @param entity Cart entity
     * @return Cart form
     * @since 1.0
     */
    public static CartForm map(CartEntity entity) {
        return new CartForm()
                .setUser(UserMapper.map(entity.getUser()))
                .setProduct(ProductMapper.map(entity.getProduct(), null))
                .setAmount(entity.getAmount())
                .setTotal(entity.getTotal())
                .setUuid(entity.getProduct().getUuid())
                .setModel(entity.getProduct().getModel())
                .setCode(entity.getProduct().getCode())
                .setPrice(entity.getProduct().getPrice())
                .setPath(entity.getProduct().getPath());
    }

}
