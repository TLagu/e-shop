package com.lagu.eshop.product.repository;

import com.lagu.eshop.product.entity.AttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Attribute repository
 * @author Tomasz Łagowski
 * @version 1.0
 */
public interface AttributeRepository extends JpaRepository<AttributeEntity, Long>, JpaSpecificationExecutor<AttributeEntity> {

}
