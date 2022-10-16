package com.lagu.eshop.module.product.mapper;

import com.lagu.eshop.module.product.dto.CartForm;
import com.lagu.eshop.module.product.entity.*;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CartFormMapperTest {

    @Test
    void mapEntityToForm() {
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
        CartForm cartForm = new CartForm()
                .setUser(null)
                .setProduct(ProductMapper.map(productEntity, null))
                .setAmount(cartEntity.getAmount())
                .setTotal(cartEntity.getTotal())
                .setUuid(productEntity.getUuid())
                .setModel(productEntity.getModel())
                .setCode(productEntity.getCode())
                .setPrice(productEntity.getPrice())
                .setPath(productEntity.getPath());
        //when
        CartForm expected = CartFormMapper.map(cartEntity);
        //then
        assertThat(expected).isEqualTo(cartForm);
    }

}