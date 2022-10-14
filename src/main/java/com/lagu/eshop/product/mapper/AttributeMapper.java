package com.lagu.eshop.product.mapper;

import com.lagu.eshop.product.dto.AttributeDto;
import com.lagu.eshop.product.entity.AttributeEntity;

public class AttributeMapper {

    public static AttributeDto map(AttributeEntity entity) {
        return new AttributeDto()
                .setId(entity.getId())
                .setName(entity.getName())
                .setDescription(entity.getDescription());
    }

}
