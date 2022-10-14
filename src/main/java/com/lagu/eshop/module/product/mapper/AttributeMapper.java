package com.lagu.eshop.module.product.mapper;

import com.lagu.eshop.module.product.dto.AttributeDto;
import com.lagu.eshop.module.product.entity.AttributeEntity;

/**
 * Attribute mapper (DTO)
 * @author Tomasz Łagowski
 * @version 1.0
 */
public class AttributeMapper {

    /**
     * Entity mapping to the DTO
     * @since 1.0
     * @param entity Attribute entity
     * @return Attribute DTO
     */
    public static AttributeDto map(AttributeEntity entity) {
        return new AttributeDto()
                .setId(entity.getId())
                .setName(entity.getName())
                .setDescription(entity.getDescription());
    }

}
