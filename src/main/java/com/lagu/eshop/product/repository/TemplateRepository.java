package com.lagu.eshop.product.repository;

import com.lagu.eshop.product.entity.CategoryEntity;
import com.lagu.eshop.product.entity.TemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Set;

/**
 * Template repository
 * @author Tomasz Łagowski
 * @version 1.0
 */
public interface TemplateRepository extends JpaRepository<TemplateEntity, Long>, JpaSpecificationExecutor<TemplateEntity> {

    /**
     * Search by templates
     * @since 1.0
     * @param ids Template ID list
     * @return List of templates
     */
    Set<TemplateEntity> findByIdIn(List<Long> ids);

    /**
     * Search by ID and category
     * @since 1.0
     * @param id Template ID
     * @param category Category
     * @return List of templates
     */
    TemplateEntity findByIdAndCategory(Long id, CategoryEntity category);

}
