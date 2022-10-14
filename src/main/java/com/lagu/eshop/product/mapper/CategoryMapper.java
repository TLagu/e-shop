package com.lagu.eshop.product.mapper;

import com.lagu.eshop.product.dto.CategoryDto;
import com.lagu.eshop.product.dto.TemplateDto;
import com.lagu.eshop.product.entity.CategoryEntity;
import com.lagu.eshop.product.entity.TemplateEntity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Category mapper (DTO)
 * @author Tomasz Łagowski
 * @version 1.0
 */
public class CategoryMapper {

    /**
     * Entity mapping -> DTO
     * @since 1.0
     * @param entity Category entity
     * @param template Template entity
     * @return Category DTO
     */
    public static CategoryDto map(CategoryEntity entity, TemplateEntity template) {
        if (entity == null) {
            return null;
        }
        CategoryDto result = new CategoryDto()
                .setId(entity.getId())
                .setName(entity.getName())
                .setDescription(entity.getDescription())
                .setParent(CategoryMapper.map(entity.getParent(), null));
        if (template == null && entity.getTemplates() != null) {
            return result.setTemplates(entity.getTemplates().stream()
                    .map(TemplateMapper::map)
                    .collect(Collectors.toSet()));
        }
        return result;
    }

    /**
     * List of entity mapping -> list of DTO
     * @since 1.0
     * @param entities Template entity
     * @return Template DTO
     */
    public static List<TemplateDto> map(List<TemplateEntity> entities) {
        return entities.stream()
                .map(TemplateMapper::map)
                .collect(Collectors.toList());
    }

}
