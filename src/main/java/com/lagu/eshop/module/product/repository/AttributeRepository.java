package com.lagu.eshop.module.product.repository;

import com.lagu.eshop.module.product.entity.AttributeEntity;
import com.lagu.eshop.module.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Attribute repository
 * @author Tomasz Łagowski
 * @version 1.1
 */
public interface AttributeRepository extends JpaRepository<AttributeEntity, Long>, JpaSpecificationExecutor<AttributeEntity> {

    /**
     * Delete all attributes by product
     * @since 1.1
     * @param product Product entity
     */
    void deleteAllByProduct(ProductEntity product);

}
