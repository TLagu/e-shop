package com.lagu.eshop.module.product.mapper;

import com.lagu.eshop.module.product.dto.OrderDetailsDto;
import com.lagu.eshop.module.product.entity.OrderDetailsEntity;

/**
 * Order details mapper
 *
 * @author Tomasz Łagowski
 * @version 1.0
 */
public class OrderDetailsMapper {

    /**
     * Order details mapper -> DTO
     *
     * @param entity Order detail entity
     * @return Order detail DTO
     * @since 1.0
     */
    public static OrderDetailsDto map(OrderDetailsEntity entity) {
        return new OrderDetailsDto()
                .setOrder(OrderMapper.map(entity.getOrder(), new OrderDetailsDto()))
                .setProduct(ProductMapper.map(entity.getProduct(), null))
                .setPrice(entity.getPrice())
                .setAmount(entity.getAmount())
                .setTotal(entity.getTotal());
    }

}
