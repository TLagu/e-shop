package com.lagu.eshop.module.product.mapper;

import com.lagu.eshop.module.product.entity.CategoryEntity;
import com.lagu.eshop.module.product.entity.TemplateEntity;
import com.lagu.eshop.module.product.dto.TemplateForm;
import com.lagu.eshop.module.product.entity.Status;

public class TemplateFormMapper {

    /**
     * Form mapping -> Entity
     *
     * @param form     Template form
     * @param category Category entity
     * @return Template entity
     * @since 1.0
     */
    public static TemplateEntity map(TemplateForm form, CategoryEntity category) {
        return new TemplateEntity()
                .setStatus(Status.ACTIVE)
                .setName(form.getName())
                .setCategory(category);
    }

    /**
     * Entity mapping -> Form
     *
     * @param entity Template entity
     * @return Template form
     * @since 1.0
     */
    public static TemplateForm map(TemplateEntity entity) {
        return new TemplateForm()
                .setId(entity.getId())
                .setName(entity.getName())
                .setCategory((entity.getCategory() == null) ? null : entity.getCategory().getId());
    }

}
