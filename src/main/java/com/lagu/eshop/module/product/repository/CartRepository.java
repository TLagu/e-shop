package com.lagu.eshop.module.product.repository;

import com.lagu.eshop.module.product.entity.CartEntity;
import com.lagu.eshop.module.product.entity.ProductEntity;
import com.lagu.eshop.module.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Cart repository
 *
 * @author Tomasz Łagowski
 * @version 1.0
 */
public interface CartRepository extends JpaRepository<CartEntity, Long>, JpaSpecificationExecutor<CartEntity> {

    /**
     * Find cart by user and product
     *
     * @param user    User entity
     * @param product Product
     * @return Cart
     * @since 1.0
     */
    CartEntity findByUserAndProduct(UserEntity user, ProductEntity product);

    /**
     * Find cart by user order by product
     *
     * @param user User entity
     * @return List of cart
     * @since 1.0
     */
    List<CartEntity> findByUserOrderByProduct(UserEntity user);

}
