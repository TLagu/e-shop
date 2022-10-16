package com.lagu.eshop.module.product.dto;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ProductFormTest {

    @Test
    void isNew() {
        //given
        ProductForm product = new ProductForm().setUuid(null);
        //when
        boolean status = product.isNew();
        //then
        assertThat(status).isTrue();
    }

    @Test
    void existing() {
        //given
        ProductForm category = new ProductForm().setUuid(UUID.randomUUID().toString());
        //when
        boolean status = category.isNew();
        //then
        assertThat(status).isFalse();
    }

}