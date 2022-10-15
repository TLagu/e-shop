package com.lagu.eshop.module.product.mapper;

import com.lagu.eshop.module.product.dto.OrderDetailsDto;
import com.lagu.eshop.module.product.dto.OrderDto;
import com.lagu.eshop.module.product.entity.OrderEntity;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Order mapper
 * @author Tomasz Łagowski
 * @version 1.0
 */
public class OrderMapper {

    /**
     * Map order entity to order DTO
     * @since 1.0
     * @param entity Order entity
     * @param details Order details DTO
     * @return Order DTO
     */
    public static OrderDto map(OrderEntity entity, OrderDetailsDto details) {
        OrderDto order = new OrderDto()
                .setCreatedOn(entity.getCreatedOn())
                .setUuid(entity.getUuid())
                .setUser(entity.getUser())
                .setStreet(entity.getStreet())
                .setPostCode(entity.getPostCode())
                .setPost(entity.getPost())
                .setTotal(entity.getTotal());
        if (details == null) {
            Set<OrderDetailsDto> orderDetails = null;
            if (entity.getOrderDetails() != null) {
                orderDetails = entity.getOrderDetails().stream()
                        .map(OrderDetailsMapper::map)
                        .collect(Collectors.toSet());
            }
            return order.setOrderDetails(orderDetails);
        }
        return order.setOrderDetails(Set.of(details));
    }

    /**
     * Map list of order entities to list of order DTOs
     * @since 1.0
     * @param entities List of order entities
     * @return List of order DTOs
     */
    public static List<OrderDto> map(List<OrderEntity> entities) {
        return entities.stream()
                .map(p -> OrderMapper.map(p, null))
                .collect(Collectors.toList());
    }

}
