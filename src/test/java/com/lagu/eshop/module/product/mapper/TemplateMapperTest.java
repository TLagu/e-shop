package com.lagu.eshop.module.product.mapper;

import com.lagu.eshop.module.product.dto.TemplateDto;
import com.lagu.eshop.module.product.entity.TemplateEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class TemplateMapperTest {

    @Test
    void mapEntityToDto() {
        //given
        TemplateEntity templateEntity = new TemplateEntity()
                .setCategory(null)
                .setName("Name");
        TemplateDto templateDto = new TemplateDto()
                .setId(templateEntity.getId())
                .setCategory(null)
                .setName(templateEntity.getName());
        //when
        TemplateDto expected = TemplateMapper.map(templateEntity);
        //then
        assertThat(expected).isEqualTo(templateDto);
    }

}