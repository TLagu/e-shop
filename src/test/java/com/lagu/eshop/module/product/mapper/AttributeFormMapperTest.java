package com.lagu.eshop.module.product.mapper;

import com.lagu.eshop.module.product.dto.AttributeForm;
import com.lagu.eshop.module.product.entity.AttributeEntity;
import com.lagu.eshop.module.product.entity.ProductEntity;
import com.lagu.eshop.module.product.entity.Status;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AttributeFormMapperTest {

    @Test
    void mapFormToEntity() {
        //given
        ProductEntity product = new ProductEntity()
                .setId(1L);
        AttributeForm attributeForm = new AttributeForm()
                .setName("Pojemność modułu")
                .setDescription("8 GB");
        AttributeEntity attributeEntity = new AttributeEntity()
                .setCreatedBy(1L)
                .setUpdatedBy(1L)
                .setStatus(Status.ACTIVE)
                .setProduct(product)
                .setName(attributeForm.getName())
                .setDescription(attributeForm.getDescription());
        //when
        AttributeEntity expected = AttributeFormMapper.map(attributeForm, product);
        //then
        assertThat(expected).isEqualTo(attributeEntity);
    }

    @Test
    void mapEntityToForm() {
        //given
        ProductEntity product = new ProductEntity()
                .setId(1L);
        AttributeForm attributeForm = new AttributeForm()
                .setId(1L)
                .setProduct(product.getId())
                .setName("Pojemność modułu")
                .setDescription("8 GB");
        AttributeEntity attributeEntity = new AttributeEntity()
                .setId(attributeForm.getId())
                .setProduct(product)
                .setName(attributeForm.getName())
                .setDescription(attributeForm.getDescription());
        //when
        AttributeForm expected = AttributeFormMapper.map(attributeEntity);
        //then
        assertThat(expected).isEqualTo(attributeForm);
    }

}