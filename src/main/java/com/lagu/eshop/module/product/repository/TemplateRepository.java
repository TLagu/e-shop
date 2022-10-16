package com.lagu.eshop.module.product.repository;

import com.lagu.eshop.module.product.entity.CategoryEntity;
import com.lagu.eshop.module.product.entity.TemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Set;

/**
 * Template repository
 *
 * @author Tomasz Łagowski
 * @version 1.0
 */
public interface TemplateRepository extends JpaRepository<TemplateEntity, Long>, JpaSpecificationExecutor<TemplateEntity> {

    /**
     * Search by templates
     *
     * @param ids Template ID list
     * @return List of templates
     * @since 1.0
     */
    Set<TemplateEntity> findByIdIn(List<Long> ids);

    /**
     * Search by ID and category
     *
     * @param id       Template ID
     * @param category Category
     * @return List of templates
     * @since 1.0
     */
    TemplateEntity findByIdAndCategory(Long id, CategoryEntity category);

}
