package com.lagu.eshop.module.product.mapper;

import com.lagu.eshop.module.product.dto.OrderDetailsDto;
import com.lagu.eshop.module.product.entity.OrderDetailsEntity;
import com.lagu.eshop.module.product.entity.OrderEntity;
import com.lagu.eshop.module.product.entity.ProductEntity;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class OrderDetailsMapperTest {

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
        ProductEntity productEntity = new ProductEntity()
                .setUuid(UUID.randomUUID().toString())
                .setModel("G.SKILL Aegis 16GB")
                .setDescription("2x8GB 3000MHz DDR4 CL16 1.35V DIMM")
                .setCategory(null)
                .setPrice(279.00)
                .setPath("/img/1/foto.jpg")
                .setCode("F4-3000C16D-16GISB");
        OrderDetailsEntity orderDetailsEntity = new OrderDetailsEntity()
                .setOrder(orderEntity)
                .setProduct(productEntity)
                .setPrice(100.0)
                .setAmount(1)
                .setTotal(100.0);
        OrderDetailsDto orderDetailsDto = new OrderDetailsDto()
                .setOrder(OrderMapper.map(orderEntity, new OrderDetailsDto()))
                .setProduct(ProductMapper.map(productEntity, null))
                .setPrice(orderDetailsEntity.getPrice())
                .setAmount(orderDetailsEntity.getAmount())
                .setTotal(orderDetailsEntity.getTotal());
        //when
        OrderDetailsDto expected = OrderDetailsMapper.map(orderDetailsEntity);
        //then
        assertThat(expected).isEqualTo(orderDetailsDto);
    }

}