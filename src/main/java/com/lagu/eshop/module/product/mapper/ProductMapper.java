package com.lagu.eshop.module.product.mapper;

import com.lagu.eshop.module.product.dto.AttributeDto;
import com.lagu.eshop.module.product.dto.ProductDto;
import com.lagu.eshop.module.product.entity.ProductEntity;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Product mapper (DTO)
 *
 * @author Tomasz Łagowski
 * @version 1.0
 */
public class ProductMapper {

    public static ProductDto map(ProductEntity entity, AttributeDto attribute) {
        ProductDto result = new ProductDto()
                .setUuid(entity.getUuid())
                .setModel(entity.getModel())
                .setDescription(entity.getDescription())
                .setCategory(CategoryMapper.map(entity.getCategory(), null))
                .setPrice(entity.getPrice())
                .setPath(entity.getPath())
                .setCode(entity.getCode());
        if (attribute == null) {
            Set<AttributeDto> attributes = null;
            if (entity.getAttributes() != null) {
                attributes = entity.getAttributes().stream()
                        .map(AttributeMapper::map)
                        .collect(Collectors.toSet());
            }
            return result.setAttributes(attributes);
        }
        return result.setAttributes(Set.of(attribute));
    }

    public static List<ProductDto> map(List<ProductEntity> entities) {
        return entities.stream()
                .map(p -> ProductMapper.map(p, null))
                .collect(Collectors.toList());
    }

}
