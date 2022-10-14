package com.lagu.eshop.product.mapper;

import com.lagu.eshop.product.dto.TemplateForm;
import com.lagu.eshop.product.entity.CategoryEntity;
import com.lagu.eshop.product.entity.Status;
import com.lagu.eshop.product.entity.TemplateEntity;

public class TemplateFormMapper {

    /**
     * Form mapping -> Entity
     * @since 1.0
     * @param form Template form
     * @param category Category entity
     * @return Template entity
     */
    public static TemplateEntity map(TemplateForm form, CategoryEntity category) {
        return new TemplateEntity()
                .setStatus(Status.ACTIVE)
                .setName(form.getName())
                .setCategory(category);

    }

    /**
     * Entity mapping -> Form
     * @since 1.0
     * @param entity Template entity
     * @return Template form
     */
    public static TemplateForm map(TemplateEntity entity) {
        return new TemplateForm()
                .setId(entity.getId())
                .setName(entity.getName())
                .setCategory((entity.getCategory() == null) ? null : entity.getCategory().getId());
    }

}
