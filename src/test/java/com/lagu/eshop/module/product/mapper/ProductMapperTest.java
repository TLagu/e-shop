package com.lagu.eshop.module.product.mapper;

import com.lagu.eshop.module.product.dto.ProductDto;
import com.lagu.eshop.module.product.entity.ProductEntity;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ProductMapperTest {

    private final ProductEntity productEntity = new ProductEntity()
            .setUuid(UUID.randomUUID().toString())
            .setModel("Model")
            .setDescription("Description")
            .setCategory(null)
            .setPrice(100.0)
            .setPath("Path")
            .setCode("Code")
            .setAttributes(null);
    private final ProductDto productDto = new ProductDto()
            .setUuid(productEntity.getUuid())
            .setModel(productEntity.getModel())
            .setDescription(productEntity.getDescription())
            .setCategory(null)
            .setPrice(productEntity.getPrice())
            .setPath(productEntity.getPath())
            .setCode(productEntity.getCode())
            .setAttributes(null);

    @Test
    void mapEntityToDto() {
        //given
        //when
        ProductDto expected = ProductMapper.map(productEntity, null);
        //then
        assertThat(expected).isEqualTo(productDto);
    }

    @Test
    void mapEntityListToDtoList() {
        //given
        List<ProductEntity> productEntities = List.of(productEntity);
        List<ProductDto> productDtos = List.of(productDto);
        //when
        List<ProductDto> expected = ProductMapper.map(productEntities);
        //then
        assertThat(expected).isEqualTo(productDtos);
    }

}