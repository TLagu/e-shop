package com.lagu.eshop.module.product.mapper;

import com.lagu.eshop.module.product.dto.AttributeForm;
import com.lagu.eshop.module.product.entity.AttributeEntity;
import com.lagu.eshop.module.product.entity.ProductEntity;
import com.lagu.eshop.module.product.entity.Status;

/**
 * Attribute form mapper
 *
 * @author Tomasz Łagowski
 * @version 1.0
 */
public class AttributeFormMapper {

    /**
     * Map the attribute form to the attribute entity
     *
     * @param form    Attribute form
     * @param product Product entity
     * @return Attribute entity
     * @since 1.0
     */
    public static AttributeEntity map(AttributeForm form, ProductEntity product) {
        return new AttributeEntity()
                .setCreatedBy(1L)
                .setUpdatedBy(1L)
                .setStatus(Status.ACTIVE)
                .setProduct(product)
                .setName(form.getName())
                .setDescription(form.getDescription());
    }

    /**
     * Map the attribute entity to the attribute form
     *
     * @param entity Attribute entity
     * @return Attribute form
     * @since 1.0
     */
    public static AttributeForm map(AttributeEntity entity) {
        return new AttributeForm()
                .setId(entity.getId())
                .setProduct(entity.getProduct().getId())
                .setName(entity.getName())
                .setDescription(entity.getDescription());
    }

}
