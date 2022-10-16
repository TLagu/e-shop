package com.lagu.eshop.module.product.repository;

import com.lagu.eshop.module.product.entity.OrderEntity;
import com.lagu.eshop.module.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Order repository
 *
 * @author Tomasz Łagowski
 * @version 1.0
 */
public interface OrderRepository extends JpaRepository<OrderEntity, Long>, JpaSpecificationExecutor<OrderEntity> {

    /**
     * Find order by user and order by created date
     *
     * @param user User entity
     * @return List of order entity
     * @since 1.0
     */
    List<OrderEntity> findByUserOrderByCreatedOn(UserEntity user);

    /**
     * Get order entity by user end UUID
     *
     * @param user User entity
     * @param uuid UUID
     * @return Order entity
     * @since 1.0
     */
    OrderEntity getByUserAndUuid(UserEntity user, String uuid);
}
