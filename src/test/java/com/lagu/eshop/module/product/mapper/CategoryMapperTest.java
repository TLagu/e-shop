package com.lagu.eshop.module.product.mapper;

import com.lagu.eshop.module.product.dto.CategoryDto;
import com.lagu.eshop.module.product.entity.CategoryEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CategoryMapperTest {

    @Test
    void mapEntityToDto() {
        //given
        CategoryEntity categoryEntity = new CategoryEntity()
                .setId(1L)
                .setName("Name")
                .setDescription("Description")
                .setParent(null)
                .setTemplates(null);
        CategoryDto categoryDto = new CategoryDto()
                .setId(categoryEntity.getId())
                .setName(categoryEntity.getName())
                .setDescription(categoryEntity.getDescription())
                .setParent(null)
                .setTemplates(null);
        //when
        CategoryDto expected = CategoryMapper.map(categoryEntity, null);
        //then
        assertThat(expected).isEqualTo(categoryDto);
    }

}