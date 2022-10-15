package com.lagu.eshop.module.product.repository;

import com.lagu.eshop.module.product.entity.OrderEntity;
import com.lagu.eshop.module.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Order repository
 * @author Tomasz Łagowski
 * @version 1.0
 */
public interface OrderRepository extends JpaRepository<OrderEntity, Long>, JpaSpecificationExecutor<OrderEntity> {

    /**
     * Find order by user and order by created date
     * @since 1.0
     * @param user User entity
     * @return List of order entity
     */
    List<OrderEntity> findByUserOrderByCreatedOn(UserEntity user);

    /**
     * Get order entity by user end UUID
     * @since 1.0
     * @param user User entity
     * @param uuid UUID
     * @return Order entity
     */
    OrderEntity getByUserAndUuid(UserEntity user, String uuid);
}
