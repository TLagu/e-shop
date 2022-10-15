package com.lagu.eshop.module.product.service;

import com.lagu.eshop.module.product.entity.CategoryEntity;
import com.lagu.eshop.module.product.entity.Status;
import com.lagu.eshop.module.product.entity.TemplateEntity;
import com.lagu.eshop.module.product.repository.CategoryRepository;
import com.lagu.eshop.module.product.repository.TemplateRepository;
import org.springframework.stereotype.Service;

/**
 * Template service
 * @author Tomasz Łagowski
 * @version 1.1
 */
@Service
public class TemplateService {

    private final TemplateRepository templateRepository;
    private final CategoryRepository categoryRepository;

    public TemplateService(TemplateRepository templateRepository, CategoryRepository categoryRepository) {
        this.templateRepository = templateRepository;
        this.categoryRepository = categoryRepository;
    }

    /**
     * Delete template
     * @since 1.1
     * @param cid Category ID
     * @param tid Template ID
     */
    public void delete(Long cid, Long tid) {
        CategoryEntity category = categoryRepository.findById(cid)
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono kategorii!!!"));
        TemplateEntity template = templateRepository.findByIdAndCategory(tid, category);
        templateRepository.delete(template);
    }

    /**
     * Add template
     * @since 1.1
     * @param cid Category ID
     */
    public void addTemplate(Long cid) {
        CategoryEntity category = categoryRepository.findById(cid)
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono kategorii!!!"));
        TemplateEntity template = new TemplateEntity()
                .setCategory(category)
                .setStatus(Status.ACTIVE)
                .setCreatedBy(1L)
                .setUpdatedBy(1L);
        templateRepository.saveAndFlush(template);
    }

}
