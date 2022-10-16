package com.lagu.eshop.module.product.mapper;

import com.lagu.eshop.module.product.dto.OrderDto;
import com.lagu.eshop.module.product.entity.OrderEntity;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class OrderMapperTest {

    @Test
    void mapEntityToDto() {
        //given
        OrderEntity orderEntity = new OrderEntity()
                .setUuid(UUID.randomUUID().toString())
                .setUser(null)
                .setStreet("Street")
                .setPostCode("00-000")
                .setPost("Post")
                .setTotal(100.0)
                .setOrderDetails(null);
        OrderDto orderDto = new OrderDto()
                .setCreatedOn(orderEntity.getCreatedOn())
                .setUuid(orderEntity.getUuid())
                .setUser(orderEntity.getUser())
                .setStreet(orderEntity.getStreet())
                .setPostCode(orderEntity.getPostCode())
                .setPost(orderEntity.getPost())
                .setTotal(orderEntity.getTotal())
                .setOrderDetails(null);
        //when
        OrderDto expected = OrderMapper.map(orderEntity, null);
        //then
        assertThat(expected).isEqualTo(orderDto);
    }

    @Test
    void mapListEntityToListDto() {
        //given
        OrderEntity orderEntity = new OrderEntity()
                .setUuid(UUID.randomUUID().toString())
                .setUser(null)
                .setStreet("Street")
                .setPostCode("00-000")
                .setPost("Post")
                .setTotal(100.0)
                .setOrderDetails(null);
        List<OrderEntity> orderEntities = List.of(orderEntity);
        OrderDto orderDto = new OrderDto()
                .setCreatedOn(orderEntity.getCreatedOn())
                .setUuid(orderEntity.getUuid())
                .setUser(orderEntity.getUser())
                .setStreet(orderEntity.getStreet())
                .setPostCode(orderEntity.getPostCode())
                .setPost(orderEntity.getPost())
                .setTotal(orderEntity.getTotal())
                .setOrderDetails(null);
        List<OrderDto> orderDtos = List.of(orderDto);
        //when
        List<OrderDto> expected = OrderMapper.map(orderEntities);
        //then
        assertThat(expected).isEqualTo(orderDtos);
    }

}