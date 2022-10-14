package com.lagu.eshop.product.mapper;

import com.lagu.eshop.product.dto.CategoryDto;
import com.lagu.eshop.product.dto.TemplateDto;
import com.lagu.eshop.product.entity.CategoryEntity;
import com.lagu.eshop.product.entity.TemplateEntity;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryMapper {

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
                    .map(CategoryTemplateMapper::map)
                    .collect(Collectors.toSet()));
        }
        return result;
    }

    public static List<TemplateDto> map(List<TemplateEntity> entities) {
        return entities.stream()
                .map(CategoryTemplateMapper::map)
                .collect(Collectors.toList());
    }

}
