package com.lagu.eshop.product.mapper;

import com.lagu.eshop.product.dto.TemplateDto;
import com.lagu.eshop.product.entity.TemplateEntity;

public class CategoryTemplateMapper {

    public static TemplateDto map(TemplateEntity entity) {
        return new TemplateDto()
                .setId(entity.getId())
                .setCategory(CategoryMapper.map(entity.getCategory(), entity))
                .setName(entity.getName());
    }

}
