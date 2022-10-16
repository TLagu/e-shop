package com.lagu.eshop.module.product.repository;

import com.lagu.eshop.module.product.entity.CategoryEntity;
import com.lagu.eshop.module.product.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * Product repository
 *
 * @author Tomasz Łagowski
 * @version 1.2
 */
public interface ProductRepository extends JpaRepository<ProductEntity, Long>, JpaSpecificationExecutor<ProductEntity> {

    /**
     * Getting products in random order
     *
     * @param pageable Paging metadata
     * @return List of products
     * @since 1.0
     */
    @Query("SELECT p FROM ProductEntity p ORDER BY RAND()")
    List<ProductEntity> findRandomForHeader(Pageable pageable);

    /**
     * Search for a product by name or description
     *
     * @param searchText Search text
     * @return List of products
     * @since 1.0
     */
    @Query("SELECT p FROM ProductEntity p " +
            " WHERE LOWER(p.model) LIKE ('%' || LOWER(:searchText) || '%') " +
            " OR LOWER(p.description) LIKE ('%' || LOWER(:searchText) || '%')")
    List<ProductEntity> searchProduct(String searchText);

    /**
     * Search by categories
     *
     * @param ids      Category ID list
     * @param pageable Paging metadata
     * @return List of products
     * @since 1.1
     */
    @Query("SELECT p FROM ProductEntity p WHERE p.category IN :ids")
    Page<ProductEntity> findByCategories(List<CategoryEntity> ids, Pageable pageable);

    /**
     * Getting product by UUID
     *
     * @param uuid Product UUID
     * @return Product
     * @since 1.1
     */
    ProductEntity getByUuid(String uuid);

    /**
     * Find product entity by UUID
     *
     * @param uuid Product UUID
     * @return Product entity
     * @since 1.2
     */
    Optional<ProductEntity> findByUuid(String uuid);

}
