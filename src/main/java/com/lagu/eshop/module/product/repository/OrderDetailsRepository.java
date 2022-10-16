package com.lagu.eshop.module.product.repository;

import com.lagu.eshop.module.product.entity.OrderDetailsEntity;
import com.lagu.eshop.module.product.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Order details repository
 *
 * @author Tomasz Łagowski
 * @version 1.0
 */
public interface OrderDetailsRepository extends JpaRepository<OrderDetailsEntity, Long>, JpaSpecificationExecutor<OrderDetailsEntity> {

    /**
     * Find orders by product
     *
     * @param order Order entity
     * @return List of order details
     * @since 1.0
     */
    List<OrderDetailsEntity> findByOrderOrderByProduct(OrderEntity order);

}
