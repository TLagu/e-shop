package com.lagu.eshop.module.product.mapper;

import com.lagu.eshop.module.product.dto.CartDto;
import com.lagu.eshop.module.product.entity.CartEntity;
import com.lagu.eshop.module.product.entity.ProductEntity;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CartMapperTest {

    @Test
    void mapEntityToDto() {
        //given
        ProductEntity productEntity = new ProductEntity()
                .setUuid(UUID.randomUUID().toString())
                .setModel("G.SKILL Aegis 16GB")
                .setDescription("2x8GB 3000MHz DDR4 CL16 1.35V DIMM")
                .setCategory(null)
                .setPrice(279.00)
                .setPath("/img/1/foto.jpg")
                .setCode("F4-3000C16D-16GISB");
        CartEntity cartEntity = new CartEntity()
                .setUser(null)
                .setProduct(productEntity)
                .setAmount(1)
                .setTotal(10.0);
        CartDto cartDto = new CartDto()
                .setUser(null)
                .setProduct(ProductMapper.map(productEntity, null))
                .setAmount(cartEntity.getAmount())
                .setTotal(cartEntity.getTotal());
        //when
        CartDto expected = CartMapper.map(cartEntity);
        //then
        assertThat(expected).isEqualTo(cartDto);
    }

}