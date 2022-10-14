package com.lagu.eshop.module.product.service;

import com.lagu.eshop.module.product.repository.CategoryRepository;
import com.lagu.eshop.module.product.repository.TemplateRepository;
import org.springframework.stereotype.Service;

/**
 * Template service
 * @author Tomasz Łagowski
 * @version 1.0
 */
@Service
public class TemplateService {

    private final TemplateRepository templateRepository;
    private final CategoryRepository categoryRepository;

    public TemplateService(TemplateRepository templateRepository, CategoryRepository categoryRepository) {
        this.templateRepository = templateRepository;
        this.categoryRepository = categoryRepository;
    }

}
