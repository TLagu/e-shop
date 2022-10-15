package com.lagu.eshop.module.product.repository;

import com.lagu.eshop.module.product.entity.OrderDetailsEntity;
import com.lagu.eshop.module.product.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Order details repository
 * @author Tomasz Łagowski
 * @version 1.0
 */
public interface OrderDetailsRepository extends JpaRepository<OrderDetailsEntity, Long>, JpaSpecificationExecutor<OrderDetailsEntity> {

    /**
     * Find orders by product
     * @since 1.0
     * @param order Order entity
     * @return List of order details
     */
    List<OrderDetailsEntity> findByOrderOrderByProduct(OrderEntity order);

}
