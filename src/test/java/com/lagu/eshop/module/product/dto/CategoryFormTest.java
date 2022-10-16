package com.lagu.eshop.module.product.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CategoryFormTest {

    @Test
    void isNew() {
        //given
        CategoryForm category = new CategoryForm().setId(0L);
        //when
        boolean status = category.isNew();
        //then
        assertThat(status).isTrue();
    }

    @Test
    void existing() {
        //given
        CategoryForm category = new CategoryForm().setId(1L);
        //when
        boolean status = category.isNew();
        //then
        assertThat(status).isFalse();
    }

}