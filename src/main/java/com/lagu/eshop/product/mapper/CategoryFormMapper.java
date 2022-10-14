package com.lagu.eshop.product.mapper;

import com.lagu.eshop.product.dto.CategoryForm;
import com.lagu.eshop.product.dto.TemplateForm;
import com.lagu.eshop.product.entity.CategoryEntity;
import com.lagu.eshop.product.entity.Status;
import com.lagu.eshop.product.entity.TemplateEntity;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Attribute mapper (Form)
 * @author Tomasz Łagowski
 * @version 1.0
 */
public class CategoryFormMapper {

    /**
     * Form mapping -> Entity
     * @since 1.0
     * @param form Category form
     * @param parent Parent category entity
     * @param templates Template entity
     * @return Category entity
     */
    public static CategoryEntity map(CategoryForm form, CategoryEntity parent,
                                     Set<TemplateEntity> templates) {
        return new CategoryEntity()
                .setStatus(Status.ACTIVE)
                .setName(form.getName())
                .setDescription(form.getDescription())
                .setParent(parent)
                .setTemplates(templates);
    }

    /**
     * Entity mapping -> Form
     * @since 1.0
     * @param entity Category entity
     * @return Category form
     */
    public static CategoryForm map(CategoryEntity entity) {
        CategoryForm form = new CategoryForm()
                .setName(entity.getName())
                .setDescription(entity.getDescription())
                .setParent((entity.getParent() == null) ? null : entity.getParent().getId());
        List<TemplateForm> templates = null;
        if (entity.getTemplates() != null) {
            templates = entity.getTemplates().stream()
                    .map(TemplateFormMapper::map)
                    .collect(Collectors.toList());
        }
        form.setTemplates(templates);
        return form;
    }

}
