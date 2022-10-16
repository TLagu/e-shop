package com.lagu.eshop.module.product.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class TemplateFormTest {

    @Test
    void isNew() {
        //given
        TemplateForm template = new TemplateForm().setId(0L);
        //when
        boolean status = template.isNew();
        //then
        assertThat(status).isTrue();
    }

    @Test
    void existing() {
        //given
        TemplateForm template = new TemplateForm().setId(1L);
        //when
        boolean status = template.isNew();
        //then
        assertThat(status).isFalse();
    }

}