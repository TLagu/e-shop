package com.lagu.eshop.module.product.mapper;

import com.lagu.eshop.module.product.dto.ProductForm;
import com.lagu.eshop.module.product.entity.CategoryEntity;
import com.lagu.eshop.module.product.entity.ProductEntity;
import com.lagu.eshop.module.product.entity.Status;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

class ProductFormMapperTest {

    @Test
    void mapFormToEntity() {
        //given
        final UUID mock = mock(UUID.class);
        mockStatic(UUID.class);
        given(UUID.randomUUID()).willReturn(mock);

        CategoryEntity categoryEntity = new CategoryEntity()
                .setId(1L);
        ProductForm productForm = new ProductForm()
                .setModel("Model")
                .setDescription("Description")
                .setPath("Path")
                .setCode("Code");
        ProductEntity productEntity = new ProductEntity()
                .setCreatedBy(1L)
                .setUpdatedBy(1L)
                .setStatus(Status.ACTIVE)
                .setUuid(UUID.randomUUID().toString())
                .setModel(productForm.getModel())
                .setDescription(productForm.getDescription())
                .setCategory(categoryEntity)
                .setPrice(productForm.getPrice())
                .setCode(productForm.getCode())
                .setAttributes(null);
        //when
        ProductEntity expected = ProductFormMapper.map(productForm, categoryEntity, null);
        //then
        assertThat(expected).isEqualTo(productEntity);
    }

    @Test
    void mapEntityToForm() {
        //given
        ProductEntity productEntity = new ProductEntity()
                .setUuid(UUID.randomUUID().toString())
                .setModel("Model")
                .setDescription("Description")
                .setCategory(null)
                .setPrice(100.0)
                .setCode("Code")
                .setPath("Path")
                .setAttributes(null);
        ProductForm productForm = new ProductForm()
                .setUuid(productEntity.getUuid())
                .setModel(productEntity.getModel())
                .setDescription(productEntity.getDescription())
                .setCategory(null)
                .setPrice(productEntity.getPrice())
                .setCode(productEntity.getCode())
                .setPath(productEntity.getPath())
                .setAttributes(null);
        //when
        ProductForm expected = ProductFormMapper.map(productEntity);
        //then
        assertThat(expected).isEqualTo(productForm);
    }

}