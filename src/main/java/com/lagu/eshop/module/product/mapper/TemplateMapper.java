package com.lagu.eshop.module.product.mapper;

import com.lagu.eshop.module.product.dto.TemplateDto;
import com.lagu.eshop.module.product.entity.TemplateEntity;

/**
 * Template mapper (DTO)
 *
 * @author Tomasz Łagowski
 * @version 1.0
 */
public class TemplateMapper {

    /**
     * Entity mapping -> DTO
     *
     * @param entity Template entity
     * @return Template DTO
     * @since 1.0
     */
    public static TemplateDto map(TemplateEntity entity) {
        return new TemplateDto()
                .setId(entity.getId())
                .setCategory(CategoryMapper.map(entity.getCategory(), entity))
                .setName(entity.getName());
    }

}
