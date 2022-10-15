package com.lagu.eshop.module.product.mapper;

import com.lagu.eshop.module.product.dto.AttributeForm;
import com.lagu.eshop.module.product.dto.ProductForm;
import com.lagu.eshop.module.product.entity.AttributeEntity;
import com.lagu.eshop.module.product.entity.CategoryEntity;
import com.lagu.eshop.module.product.entity.ProductEntity;
import com.lagu.eshop.module.product.entity.Status;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Product form mapper
 * @author Tomasz Łagowski
 * @version 1.0
 */
public class ProductFormMapper {

    /**
     * Map product form to product entity
     * @since 1.0
     * @param form Product Form
     * @param category Category entity
     * @param attributes List of attribute entities
     * @return Product entity
     */
    public static ProductEntity map(ProductForm form, CategoryEntity category, Set<AttributeEntity> attributes) {
        return new ProductEntity()
                .setCreatedBy(1L)
                .setUpdatedBy(1L)
                .setStatus(Status.ACTIVE)
                .setUuid(UUID.randomUUID().toString())
                .setModel(form.getModel())
                .setDescription(form.getDescription())
                .setCategory(category)
                .setPrice(form.getPrice())
                .setCode(form.getCode())
                .setAttributes(attributes);
    }

    /**
     * Map Product entity to product form
     * @since 1.0
     * @param entity Product entity
     * @return Product form
     */
    public static ProductForm map (ProductEntity entity) {
        ProductForm form = new ProductForm()
                .setUuid(entity.getUuid())
                .setModel(entity.getModel())
                .setDescription(entity.getDescription())
                .setCategory((entity.getCategory() == null) ? null : entity.getCategory().getId())
                .setPrice(entity.getPrice())
                .setCode(entity.getCode())
                .setPath(entity.getPath());
        List<AttributeForm> attributes = null;
        if (entity.getAttributes() != null) {
            attributes = entity.getAttributes().stream()
                    .map(AttributeFormMapper::map)
                    .collect(Collectors.toList());
        }
        form.setAttributes(attributes);
        return form;
    }

}
