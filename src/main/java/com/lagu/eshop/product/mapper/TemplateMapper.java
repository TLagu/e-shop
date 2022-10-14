package com.lagu.eshop.product.mapper;

import com.lagu.eshop.product.dto.TemplateDto;
import com.lagu.eshop.product.entity.TemplateEntity;

/**
 * Template mapper (DTO)
 * @author Tomasz Łagowski
 * @version 1.0
 */
public class TemplateMapper {

    /**
     * Entity mapping -> DTO
     * @since 1.0
     * @param entity Template entity
     * @return Template DTO
     */
    public static TemplateDto map(TemplateEntity entity) {
        return new TemplateDto()
                .setId(entity.getId())
                .setCategory(CategoryMapper.map(entity.getCategory(), entity))
                .setName(entity.getName());
    }

}
