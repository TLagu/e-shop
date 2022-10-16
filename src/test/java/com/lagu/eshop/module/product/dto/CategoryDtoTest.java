package com.lagu.eshop.module.product.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CategoryDtoTest {

    @Test
    void isNew() {
        //given
        CategoryDto category = new CategoryDto().setId(0L);
        //when
        boolean status = category.isNew();
        //then
        assertThat(status).isTrue();
    }

    @Test
    void existing() {
        //given
        CategoryDto category = new CategoryDto().setId(1L);
        //when
        boolean status = category.isNew();
        //then
        assertThat(status).isFalse();
    }

}