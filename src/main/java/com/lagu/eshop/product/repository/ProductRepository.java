package com.lagu.eshop.product.repository;

import com.lagu.eshop.product.entity.ProductEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>, JpaSpecificationExecutor<ProductEntity> {

    @Query("SELECT p FROM ProductEntity p ORDER BY RAND()")
    List<ProductEntity> findRandomForHeader(Pageable pageable);

    @Query("SELECT p FROM ProductEntity p " +
            " WHERE LOWER(p.model) LIKE ('%' || LOWER(:searchText) || '%') " +
            " OR LOWER(p.description) LIKE ('%' || LOWER(:searchText) || '%')")
    List<ProductEntity> searchProduct(String searchText);
}
