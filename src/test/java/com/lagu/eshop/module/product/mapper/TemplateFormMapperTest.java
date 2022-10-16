package com.lagu.eshop.module.product.mapper;

import com.lagu.eshop.module.product.dto.TemplateForm;
import com.lagu.eshop.module.product.entity.Status;
import com.lagu.eshop.module.product.entity.TemplateEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class TemplateFormMapperTest {
    private final TemplateForm templateForm = new TemplateForm()
            .setName("Name")
            .setCategory(null);
    private final TemplateEntity templateEntity = new TemplateEntity()
            .setStatus(Status.ACTIVE)
            .setName(templateForm.getName())
            .setCategory(null);

    @Test
    void mapFormToEntity() {
        //given
        //when
        TemplateEntity expected = TemplateFormMapper.map(templateForm, null);
        //then
        assertThat(expected).isEqualTo(templateEntity);
    }

    @Test
    void mapEntityToForm() {
        //given
        //when
        TemplateForm expected = TemplateFormMapper.map(templateEntity);
        //then
        assertThat(expected).isEqualTo(templateForm);
    }

}