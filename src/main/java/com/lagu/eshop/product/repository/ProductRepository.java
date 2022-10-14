package com.lagu.eshop.product.repository;

import com.lagu.eshop.product.entity.CategoryEntity;
import com.lagu.eshop.product.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Product repository
 * @author Tomasz Łagowski
 * @version 1.1
 */
public interface ProductRepository extends JpaRepository<ProductEntity, Long>, JpaSpecificationExecutor<ProductEntity> {

    /**
     * Getting products in random order
     * @since 1.0
     * @param pageable Paging metadata
     * @return List of products
     */
    @Query("SELECT p FROM ProductEntity p ORDER BY RAND()")
    List<ProductEntity> findRandomForHeader(Pageable pageable);

    /**
     * Search for a product by name or description
     * @since 1.0
     * @param searchText Search text
     * @return List of products
     */
    @Query("SELECT p FROM ProductEntity p " +
            " WHERE LOWER(p.model) LIKE ('%' || LOWER(:searchText) || '%') " +
            " OR LOWER(p.description) LIKE ('%' || LOWER(:searchText) || '%')")
    List<ProductEntity> searchProduct(String searchText);

    /**
     * Search by categories
     * @since 1.1
     * @param ids Category ID list
     * @param pageable Paging metadata
     * @return List of products
     */
    @Query("SELECT p FROM ProductEntity p WHERE p.category IN :ids")
    Page<ProductEntity> findByCategories(List<CategoryEntity> ids, Pageable pageable);

    /**
     * Getting product by UUID
     * @since 1.1
     * @param uuid Product UUID
     * @return Product
     */
    ProductEntity getByUuid(String uuid);

}
