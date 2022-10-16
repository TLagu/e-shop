package com.lagu.eshop.module.product.mapper;

import com.lagu.eshop.module.product.dto.CategoryForm;
import com.lagu.eshop.module.product.entity.CategoryEntity;
import com.lagu.eshop.module.product.entity.Status;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CategoryFormMapperTest {

    @Test
    void mapFormToEntity() {
        //given
        CategoryForm categoryForm = new CategoryForm()
                .setName("Category")
                .setDescription("Description")
                .setParent(null)
                .setTemplates(null);
        CategoryEntity categoryEntity = new CategoryEntity()
                .setStatus(Status.ACTIVE)
                .setName(categoryForm.getName())
                .setDescription(categoryForm.getDescription())
                .setParent(null)
                .setTemplates(null);
        //when
        CategoryEntity expected = CategoryFormMapper.map(categoryForm, null, null);
        //then
        assertThat(expected).isEqualTo(categoryEntity);
    }

    @Test
    void mapEntityToForm() {
        //given
        CategoryEntity categoryEntity = new CategoryEntity()
                .setId(1L)
                .setName("Category")
                .setDescription("Description")
                .setParent(null);
        CategoryForm categoryForm = new CategoryForm()
                .setId(categoryEntity.getId())
                .setName(categoryEntity.getName())
                .setDescription(categoryEntity.getDescription())
                .setParent(null);
        //when
        CategoryForm expected = CategoryFormMapper.map(categoryEntity);
        //then
        assertThat(expected).isEqualTo(categoryForm);
    }

}