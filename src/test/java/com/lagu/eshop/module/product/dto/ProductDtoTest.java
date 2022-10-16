package com.lagu.eshop.module.product.dto;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ProductDtoTest {

    @Test
    void isNew() {
        //given
        ProductDto product = new ProductDto().setUuid(null);
        //when
        boolean status = product.isNew();
        //then
        assertThat(status).isTrue();
    }

    @Test
    void existing() {
        //given
        ProductDto category = new ProductDto().setUuid(UUID.randomUUID().toString());
        //when
        boolean status = category.isNew();
        //then
        assertThat(status).isFalse();
    }

}