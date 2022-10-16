package com.lagu.eshop.module.product.mapper;

import com.lagu.eshop.module.product.dto.AttributeDto;
import com.lagu.eshop.module.product.entity.AttributeEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AttributeMapperTest {

    @Test
    void mapEntityToDto() {
        //given
        AttributeEntity attributeEntity = new AttributeEntity()
                .setId(1L)
                .setName("Pojemność modułu")
                .setDescription("8 GB");
        AttributeDto attributeDto = new AttributeDto()
                .setId(attributeEntity.getId())
                .setName(attributeEntity.getName())
                .setDescription(attributeEntity.getDescription());
        //when
        AttributeDto expected = AttributeMapper.map(attributeEntity);
        //then
        assertThat(expected).isEqualTo(attributeDto);
    }

}